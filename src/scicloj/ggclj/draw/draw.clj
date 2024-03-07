(ns scicloj.ggclj.draw.draw
  (:require
   [clojure2d.core :as c2d]
   [tablecloth.api :as tc]))

(defn ggplot-draw [{:keys [theme layers] :as ggplot-built}]
  (let [width (Math/ceil (apply max (map :xmax layers)))
        height (Math/ceil (apply max (map :ymax layers)))
        canvas (c2d/canvas 600 400)]
    (c2d/with-canvas [c canvas]
      (c2d/set-background c (:panel-background theme))

      (doseq [{:keys [data]} layers]
        (doseq [{:keys [x y stroke colour size shape] :as row} (tc/rows data :as-maps)]
          (c2d/set-stroke c stroke)

          ;; handle colours properly here
          (c2d/set-color c (keyword colour))

          ;; switch on shape here..
          (c2d/ellipse c x y size size))
        )

      (:buffer c))))


(->
    (gg/ggplot mtcars (gg/aes "wt" "mpg"))
   ;; (gg/ggplot)
    point/geom-point
    gg/ggplot-build
    ggplot-draw
    )


(comment
  (require '[scicloj.ggclj.api :as gg])
  (require '[scicloj.ggclj.geom.point :as point])
  (require '[scicloj.ggclj.data :refer [mtcars]])
  )

(comment
  ;; colour   x  y PANEL group shape size fill alpha stroke
  ;; 1   #C49A00 1.8 29     1     2    19  1.5   NA    NA    0.5
  ;; 2   #C49A00 1.8 29     1     2    19  1.5   NA    NA    0.5
  ;; 3   #C49A00 2.0 31     1     2    19  1.5   NA    NA    0.5
  ;; 4   #C49A00 2.0 30     1     2    19  1.5   NA    NA    0.5
  ;; 5   #C49A00 2.8 26     1     2    19  1.5   NA    NA    0.5
  ;; 6   #C49A00 2.8 26     1     2    19  1.5   NA    NA    0.5
  ;; 7   #C49A00 3.1 27     1     2    19  1.5   NA    NA    0.5
  ;; 8   #C49A00 1.8 26     1     2    19  1.5   NA    NA    0.5
  ;; 9   #C49A00 1.8 25     1     2    19  1.5   NA    NA    0.5
  ;; 10  #C49A00 2.0 28     1     2    19  1.5   NA    NA    0.5
  ;; 11  #C49A00 2.0 27     1     2    19  1.5   NA    NA    0.5
  )
