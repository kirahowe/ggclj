(ns scicloj.ggclj.layout
  (:require
   [scicloj.ggclj.util :as util]
   [tablecloth.api :as tc]))

;; (defn setup
;;   ;; Initialise panels, add extra data for margins & missing
;;   ;; facetting variables, and add on a PANEL variable to data
;;   [plot-spec]

;;   (-> layout
;;       ;; setup facets
;;       (assoc :facet-params facet/setup-params)
;;       (assoc :facet-data facet/setup-data)
;;       ;; setup coords
;;       (assoc :coord-params coords/setup-params)
;;       (assoc :coord-data coords/setup-data)
;;       ;; generate panel layout
;;       ;; (assoc :)
;;       ;; add panel coords to data in each layer
;;       ))

(defn- add-panel-col [layer]
  ;; Add actual panel here in each layer
  (update layer :data #(tc/add-column % :panel 1)))

(defn setup-layout
  [plot-spec]
  ;; this is where facets would be supported -- add panel value other than
  ;; just 1 for every row -- parse the facet value into panel vals
  (util/update-layers plot-spec add-panel-col))
