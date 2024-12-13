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

(defn- add-panel-column [plot-spec layer-spec]
  (let [facet-vars (get-facet-vars plot-spec)
        panel-map (-> layer-spec
                      :data
                      (tc/select-columns facet-vars)
                      (tc/unique-by facet-vars)
                      (tc/order-by facet-vars)
                      (tc/rows)
                      (zipmap (range)))]
    (-> layer-spec
        (update :data (fn [ds]
                        (tc/map-columns ds :panel facet-vars
                                        (fn [& args] (get panel-map args))))))))

(defn setup [plot-spec]
  (-> plot-spec
      (utils/update-layers (partial add-panel-column plot-spec))))
