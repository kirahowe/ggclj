(ns scicloj.ggclj.geom
  (:require
   [scicloj.ggclj.util :as util]
   [tablecloth.api :as tc]))

;; (def default-aes
;;   {"point" {:shape "point"
;;             :colour "black"
;;             :size 1.5
;;             :stroke 0.5}})

(def point :point)
(def none :none)

(defn infer [stat]
  (or
   ({:smooth :line} stat)
   none))

;; (defn- add-geom-cols [{:keys [data geom] :as layer}]
;;   (-> layer
;;       (update :data
;;           ;; make this a multimethod dependent on geom type
;;           ;; -- make geoms know how to add their own data to layer data
;;               #(-> %
;;                    (tc/add-column :shape (:shape geom))
;;                    (tc/add-column :colour (:colour geom))
;;                    (tc/add-column :size (:size geom))
;;                    (tc/add-column :stroke (:stroke geom))))
;;       (assoc :xmax (apply max (:x data)))
;;       (assoc :xmin (apply min (:x data)))
;;       (assoc :ymax (apply max (:y data)))
;;       (assoc :ymin (apply min (:y data)))))

;; (defn setup-data [plot-spec]
;;   (util/update-layers plot-spec add-geom-cols))
