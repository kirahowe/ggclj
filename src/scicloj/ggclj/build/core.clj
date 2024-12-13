(ns scicloj.ggclj.build.core
  (:require
   [scicloj.ggclj.build.aesthetics :as aes]
   [scicloj.ggclj.build.layers :as layers]
   [scicloj.ggclj.build.facets :as facets]))

(defn ggplot-build
  "Build ggplot for rendering"
  [plot-spec]
  ;; maybe not necessary.. assoc a blank layer if there are none
  ;; (let [layers (if (seq layers) layers [gb/geom-blank])]
  ;;   (-> plot-spec
  ;;       (assoc :layers layers )))

  ;; source (data) -> variables -> algebra -> scales -> statistics -> geometry -> coordinates -> aesthetics -> (graphic) renderer
  (-> plot-spec

      ;; - source (data) -> variables -> algebra -> scales -> statistics -> geometry -> coordinates -> aesthetics -> (graphic) renderer

      ;; - get data (argument, inherit, or compute from inherited)
      (layers/process)

      ;; - plot coordinate system first
      ;; - faceting system (add `PANEL` column)

      ;; TODO: can some/all of this be done for each layer from here rather than in each build step?
      (facets/setup)


      ;; - convert layer data into calculated aesthetic values
      ;;   - calculate `group` from interaction of all non-continuous aesthetics

      (aes/evaluate)

      ;; Give each layer a copy of the data, the mappings and the
      ;; execution environment, done on the fly

      ;; Initialise panels, add extra data for margins & missing
      ;; facetting variables, and add on a PANEL variable to data
      ;; (update :layout layout/setup-layout)
      ;; layout/setup-layout

      ;; Compute aesthetics to produce data with generalised variable names
      ;; aes/compute-aesthetics

      ;; - 1. apply scale transformations
      ;; - 2. map position aesthetics using position scales
      ;;   - apply oob args, remove missing values, apply limits, breaks
      ;; - 3. statistical transformation
      ;; - 4. geom application
      ;; geom/setup-data
      ;; - 5. train and map non-positional aesthetics
      ;; - 6. last-chance stat and facet mods
      ))

(comment
  (require '[scicloj.ggclj.api :as gg])
  (require '[scicloj.ggclj.api.facet :as facet])
  (require '[scicloj.ggclj.api.layout :as layout])
  (require '[scicloj.ggclj.data :refer [mtcars mpg]])

  (-> (gg/plot mtcars {:x "wt" :y "mpg" :colour "gear"})
      (gg/layer {:geom :point})
      (gg/layer {:geom :line :stat :lm})
      (facet/wrap "gear")
      ggplot-build)

  ;; ggplot (mpg, aes (displ, hwy, colour = drv)) +
  ;;   geom_point (position = "jitter") +
  ;;   geom_smooth (method = "lm", formula = y ~x) +
  ;;   facet_wrap (vars (year)) +
  ;;   ggtitle ("A plot for expository purposes")

  (-> (gg/plot mpg {:x "displ" :y "hwy" :colour "drv"})
      (gg/layer {:geom :point :opts {:position :jitter}})
      (gg/layer {:stat :smooth :opts {:method :lm :formula (fn [x b a] (-> (* b x) (+ a)))}})
      (facet/wrap "year")
      (layout/title "A plot for expository purposes")
      ggplot-build
      )

  (-> (gg/plot mtcars)
      (gg/layer {:geom :point
                 :mapping {:x "wt" :y "mpg"}}))

  #_(-> (ggplot data {:x "carat" :y "price"})
        (geom-point)
        (add-scale :x :log10)
        build-plot))
