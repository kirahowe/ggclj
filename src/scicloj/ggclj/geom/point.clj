(ns scicloj.ggclj.geom.point
  (:require [scicloj.ggclj.util :as util]
            ))

;; (defn geom-point
;;   ([plot-spec]
;;    (geom-point plot-spec (:mapping plot-spec) (:data plot-spec)))

;;   ([plot-spec mapping]
;;    (geom-point plot-spec mapping (:data plot-spec)))

;;   ([plot-spec mapping data]
;;    (util/add-layer plot-spec {:mapping mapping
;;                               :data data
;;                               :geom (geom/default-aes "point")
;;                               :stat "identity"
;;                               :position "identity"}))
;;   ;; support passing data/mappings in as an opts map?
;;   )



;; {:aes_params [],
;;  :stat {:compute_layer :ggproto-method, :super :ggproto-method},
;;  :show.legend [nil],
;;  :mapping nil,
;;  :super :ggproto-method,
;;  :inherit.aes [true],
;;  :geom_params {:na.rm [false]},
;;  :geom
;;  {:non_missing_aes ["size" "shape" "colour"],
;;   :draw_key :ggproto-method,
;;   :default_aes
;;   {:shape [19.0],
;;    :colour ["black"],
;;    :size [1.5],
;;    :fill [nil],
;;    :alpha [nil],
;;    :stroke [0.5]},
;;   :super :ggproto-method,
;;   :required_aes ["x" "y"],
;;   :draw_panel :ggproto-method},
;;  :stat_params {:na.rm [false]},
;;  :constructor [geom_point],
;;  :position {:compute_layer :ggproto-method, :super :ggproto-method},
;;  :data []}
