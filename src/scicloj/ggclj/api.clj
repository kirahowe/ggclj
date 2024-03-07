(ns scicloj.ggclj.api
  (:require [tablecloth.api :as tc]
            [scicloj.ggclj.aes.evaluation :as aes]
            [scicloj.ggclj.coords.coords :as coords]
            [scicloj.ggclj.theme.theme :as theme]
            [scicloj.ggclj.geom.geom :as geom]
            [scicloj.ggclj.layout :as layout]))

(defn ->dataset [data]
  (if (= (type data) tech.v3.dataset.impl.dataset.Dataset)
    data
    (tc/dataset data)))

(defn ggplot
  "Entry point to create a new plot

  Three arities:

  [] - initializes a skeleton plot to be fleshed out as layers are added, useful
  when layers use different datasets

  [data] - useful when all layers use the same data but have different aesthetics

  [data mapping] - recommended if all layers use the same data and same aesthetics,
  also useful when a single layer uses different data

  **Parameters**

  * data - Default dataset to use for plot. Expects a `tech.ml.dataset` (note tablebloth
  datasets are `tech.ml.dataset`s). If not already a `tech.ml.dataset`, we will attempt
  to make it one by passing the data to [`tablecloth.api/dataset`](https://scicloj.github.io/tablecloth/index.html#dataset). If not specified, `data` must be supplied in each individual layer.
  * mapping - Default list of aesthetic mappings to use for a plot. If not specified, must
  be supplied in each individual layer.
  "
  ([]
   (ggplot {} {}))
  ([data]
   (ggplot data {}))
  ([data mapping]
   (let [ds (->dataset data)]
     {:data ds
      :layers []
      :scales {}
      :guides {}
      :mapping mapping
      :theme theme/default-theme
      :coords coords/default-coords
      :facet {}
      :layout {}
      :labels (aes/make-labels mapping)})))

(defn aes [& args]
  (apply aes/make-mapping args))

(defn ggplot-build
  "Build ggplot for rendering"
  [plot-spec]
  ;; maybe not necessary.. assoc a blank layer if there are none
  ;; (let [layers (if (seq layers) layers [gb/geom-blank])]
  ;;   (-> plot-spec
  ;;       (assoc :layers layers )))
  (-> plot-spec
      ;; - get data (argument, inherit, or compute from inherited)
      ;; - plot coordinate system first
      ;; - faceting system (add `PANEL` column)
      ;; - convert layer data into calculated aesthetic values
      ;;   - calculate `group`

      ;; Give each layer a copy of the data, the mappings and the
      ;; execution environment, done on the fly

      ;; Initialise panels, add extra data for margins & missing
      ;; facetting variables, and add on a PANEL variable to data
      ;; (update :layout layout/setup-layout)
      layout/setup-layout

      ;; Compute aesthetics to produce data with generalised variable names
      aes/compute-aesthetics

      ;; - 1. apply scale transformations
      ;; - 2. map position aesthetics using position scales
      ;;   - apply oob args, remove missing values, apply limits, breaks
      ;; - 3. statistical transformation
      ;; - 4. geom application
      geom/setup-data
      ;; - 5. train and map non-positional aesthetics
      ;; - 6. last-chance stat and facet mods
      ))



(comment
  (require '[scicloj.ggclj.geom.point :as point])
  (require '[scicloj.ggclj.data :refer [mtcars]])

  ;; (-> (ggplot mtcars (aes "wt" "mpg"))
  ;;     point/geom-point
  ;;     ggplot-build
  ;;     ggplot-draw)
  )



  ;; (:buffer)
  ;; (c2d/with-canvas-> canvas ;; prepare drawing context in canvas
;;     (c2d/set-background 10 5 5) ;; clear background
;;     (c2d/set-color 210 210 200) ;; set color
;;     (c2d/rect 100 100 400 400) ;; draw rectangle
;;     (c2d/set-color 50 50 60) ;; set another color
;;     (c2d/set-stroke 2.0) ;; set line width
;;     ;; (c2d/line 50 300 550 300) ;; draw line
;;     (c2d/set-font-attributes 30.0) ;; set font size
;;     (c2d/set-color :maroon) ;; set current color
;;     ;; (c2d/text "Hello World!" 110 130)
;; )
  ;; draw line
