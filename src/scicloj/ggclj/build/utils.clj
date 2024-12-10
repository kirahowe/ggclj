(ns scicloj.ggclj.build.utils
  )

(defn update-layers [plot-spec fn]
  (update plot-spec :layers (partial map fn)))
