(ns scicloj.ggclj.data
  (:require
   [tablecloth.api :as tc]))

(def mpg
  (tc/dataset "data/mpg.csv" {:key-fn keyword}))
