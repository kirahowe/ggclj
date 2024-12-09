(ns scicloj.ggclj.build.facets
  (:require
   [scicloj.ggclj.build.layers :as layers]))

;; figure out how many panels the plot should have and how they should be organized
;; add the PANEL column

(defn setup [plot-spec]
  (layers/update-all plot-spec identity)
)
