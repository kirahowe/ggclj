(ns  scicloj.ggclj.stat)

(def none :identity)

(defn infer [geom]
  (or
   ({:line :smooth} geom)
   none))
