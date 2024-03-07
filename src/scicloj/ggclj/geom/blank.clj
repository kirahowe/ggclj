(ns scicloj.ggclj.geom.blank)

(defn geom-blank
  [plot]
  (update plot :layers conj {:mapping nil
                             :data nil
                             :geom "blank"
                             :stat "identity"
                             :position "identity"
                             :legend false
                             :inherit-aes true}))
