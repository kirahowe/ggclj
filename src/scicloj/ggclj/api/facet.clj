(ns scicloj.ggclj.api.facet)

(def default-facet-opts {:scales :fixed})

(defn wrap
  ([plot-spec facet-vars]
   (wrap plot-spec facet-vars {}))
  ([plot-spec facet-vars opts]
   ;; TODO: Should this clobber any existing facet?
   (assoc plot-spec :facets {:type :wrap
                             :vars facet-vars
                             :opts (merge default-facet-opts opts)})))

(defn grid
  ([plot-spec facet-rows facet-cols]
   (grid plot-spec facet-rows facet-cols {}))
  ([plot-spec facet-rows facet-cols opts]
   (assoc plot-spec :facets {:type :grid
                             :rows facet-rows
                             :cols facet-cols
                             :opts (merge default-facet-opts opts)})))
