(ns scicloj.ggclj.build.facets
  (:require
   [scicloj.ggclj.build.utils :as utils]
   [tablecloth.api :as tc]))

;; figure out how many panels the plot should have and how they should be organized
;; add the PANEL column

(defn get-facet-vars [plot-spec]
  ;; TODO handle grid vars here too, not just wrap
  (get-in plot-spec [:facets :vars])
  )

(defn setup-facets [plot-spec layer-spec]
  ;; TODO: Add PANEL column to each layer's dataset here
  (let [facet-vars (get-facet-vars plot-spec)
        panel-map (-> layer-spec
                      :data
                      (tc/select-columns facet-vars)
                      (tc/unique-by facet-vars)
                      (tc/order-by facet-vars)
                      (tc/rows)
                      (zipmap (range)))]

    (-> layer-spec
        :data
        (tc/map-columns :panel facet-vars (fn [& args] (get panel-map args))))))

(defn setup [plot-spec]
  (-> plot-spec
      (utils/update-layers (partial setup-facets plot-spec))))
