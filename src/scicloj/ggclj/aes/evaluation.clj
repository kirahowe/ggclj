(ns scicloj.ggclj.aes.evaluation)

(defn make-mapping
  "Extracts the aesthetic mapping from user input.

  Three arities:

  [opts] - A single argument is interpreted as a map that must contain at
  least `:x` and `:y` keys.

  [x y] - Two arguments are interpreted as x and y mappings.

  [x y opts] - Three arguments are interpreted as x and y mappings followed by
  an options map with all other keys"
  ([opts]
   opts)
  ([x y]
   {:x x :y y})
  ([x y opts]
   (assoc opts :x x :y y)))

(defn make-labels
  "Convert aesthetic mapping into text labels"
  [mapping]
  (-> mapping
      (select-keys [:x :y])
      (update-vals name)))
