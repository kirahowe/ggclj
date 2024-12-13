(ns scicloj.ggclj.build.aesthetics
  (:require
   [scicloj.ggclj.build.utils :as utils]
   [tablecloth.api :as tc]
   [clojure.set :as set]))

(defn- apply-aesthetics [ds mapping]
  (tc/rename-columns ds (set/map-invert mapping)))

(defn- identify-grouping-vars [ds mapping]
  ;; TODO: figure out how to identify which columns should be used for grouping
  (let [all-vars (set (keys mapping))]
    ;; TODO: Is there really a list of hard-coded vars like this?
    (set/intersection all-vars #{:colour})))

(defn- add-group-column [ds mapping]
  (let [group-vars (identify-grouping-vars ds mapping)]
    (if (empty? group-vars)
      (tc/add-column ds :group 1)

        ;; TODO: Is this really so similar to adding the panel column? Same thing just a different set of vars?
      (let [group-map (-> ds
                          (tc/select-columns group-vars)
                          (tc/unique-by group-vars)
                          (tc/order-by group-vars)
                          (tc/rows)
                          (zipmap (range)))]
        (tc/map-columns ds :group group-vars
                        (fn [& args] (get group-map args)))))))

(defn- evaluate-aesthetics [{:keys [mapping] :as layer-spec}]
  (-> layer-spec
      (update :data (fn [ds]
                      (-> ds
                          (apply-aesthetics mapping)
                          ;; (add-computed-aesthetics )
                          (add-group-column mapping)
                          )))))

(defn evaluate [plot-spec]
  (-> plot-spec
      (utils/update-layers evaluate-aesthetics)))
