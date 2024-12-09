(ns scicloj.ggclj.aes.evaluation
  (:require
   [clojure.set :as set]
   [scicloj.ggclj.util :as util]
   [tablecloth.api :as tc]))

;; (defn make-mapping
;;   "Extracts the aesthetic mapping from user input.

;;   Three arities:

;;   [opts] - A single argument is interpreted as a map that must contain at
;;   least `:x` and `:y` keys.

;;   [x y] - Two arguments are interpreted as x and y mappings.

;;   [x y opts] - Three arguments are interpreted as x and y mappings followed by
;;   an options map with all other keys"
;;   ([opts]
;;    opts)
;;   ([x y]
;;    {:x x :y y})
;;   ([x y opts]
;;    (assoc opts :x x :y y)))

(defn make-labels
  "Convert aesthetic mapping into text labels"
  [mapping]
  (-> mapping
      (select-keys [:x :y])
      (update-vals name)))

(defn- apply-mappings [{:keys [mapping] :as layer}]
  (let [output-mapping (set/map-invert mapping)
        cols-to-keep (-> output-mapping keys (conj :panel))]
    (update layer :data #(-> %
                             (tc/select-columns cols-to-keep)
                             (tc/rename-columns output-mapping)))))

(defn compute-aesthetics [plot-spec]
  (util/update-layers plot-spec apply-mappings))
