(ns scicloj.ggclj.api.layout)

(defn title [plot-spec title]
  (assoc-in plot-spec [:layout :title] title))
