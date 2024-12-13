(ns scicloj.ggclj.build.layers
  (:require
   [meta-merge.core :as mm]
   [scicloj.ggclj.build.facets :as facets]
   [scicloj.ggclj.build.utils :as utils]
   [scicloj.ggclj.geom :as geom]
   [scicloj.ggclj.stat :as stat]
   [tablecloth.api :as tc]))

(defn extract-relevant-layer-data [{:keys [mapping] :as layer-mapping} plot-spec]
  (let [relevant-columns (-> (vals mapping)
                             (conj (facets/get-facet-vars plot-spec))
                             flatten
                             distinct)]
    (update layer-mapping :data #(tc/select-columns % relevant-columns))))

(defn- apply-defaults [{:keys [geom stat] :as layer-spec} plot-spec]
  ;; TODO: support not inheriting mapping as an option, useful for e.g. annotation layers
  (mm/meta-merge
   {:data (:data plot-spec)
    :mapping (:mapping plot-spec)
    :geom (geom/infer stat)
    :stat (stat/infer geom)}
   layer-spec))

(defn- process-layer [plot-spec layer-spec]
  (-> layer-spec
      (apply-defaults plot-spec)
      (extract-relevant-layer-data plot-spec)))

(defn process [plot-spec]
  ;; TODO: add an empty layer if none are specified
  (utils/update-layers plot-spec (partial process-layer plot-spec)))
