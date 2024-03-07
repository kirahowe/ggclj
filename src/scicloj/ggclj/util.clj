(ns scicloj.ggclj.util)

(defn add-layer [plot-spec layer]
  (update plot-spec :layers conj layer))

(defn update-layers [plot-spec fn]
  (update plot-spec :layers (partial map fn)))
