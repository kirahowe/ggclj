(ns scicloj.ggclj.api
  (:require [tablecloth.api :as tc]
            [scicloj.ggclj.aes.evaluation :as aes]))

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
      :theme []
      :coords {}
      :facet {}
      :layout {}
      :labels (aes/make-labels mapping)})))

(defn aes [& args]
  (apply aes/make-mapping args))

(defn ggplot-build [plot-spec]
  ;; prepares user input for conversion into graphical primitives

  ;; - get data (argument, inherit, or compute from inherited)
  ;; - plot coordinate system first
  ;; - faceting system (add `PANEL` column)
  ;; - convert layer data into calculated aesthetic values
  ;;   - calculate `group`

  ;; DATA TRANSFORMATION
  ;; - 1. apply scale transformations
  ;; - 2. map position aesthetics using position scales
  ;;   - apply oob args, remove missing values, apply limits, breaks
  ;; - 3. statistical transformation
  ;; - 4. geom application
  ;; - 5. train and map non-positional aesthetics
  ;; - 6. last-chance stat and facet mods
  )

(defn gtable [ggplot-built]
   )

;; {:layers [],
;;  :scales nil,
;;  :guides nil,
;;  :mapping {:x [~cty], :y [~hwy]},
;;  :theme [],
;;  :coordinates nil,
;;  :facet nil,
;;  :plot_env nil,
;;  :layout nil,
;;  :labels {:x ["cty"], :y ["hwy"]}}
