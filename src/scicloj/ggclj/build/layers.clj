(ns scicloj.ggclj.build.layers
  (:require
   [tablecloth.api :as tc]
   [scicloj.ggclj.stat :as stat]
   [scicloj.ggclj.geom :as geom]
   [meta-merge.core :as mm]))

(defn- extract-relevant-layer-data [{:keys [data mapping] :as layer-mapping}]
  (update layer-mapping :data #(tc/select-columns % (vals mapping))))

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
      extract-relevant-layer-data))

(defn update-all [plot-spec fn]
  (update plot-spec :layers (partial map fn)))

(defn process [plot-spec]
  ;; TODO: add an empty layer if none are specified
  (update-all plot-spec (partial process-layer plot-spec)))
