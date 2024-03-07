(ns scicloj.ggclj.data
  (:require
   [tablecloth.api :as tc]))

(defn- load-dataset [file]
  (let [[_ ds-name] (re-find #".*\/(.+)\.csv" file)]
    (tc/dataset file {:dataset-name ds-name})))

(def mpg
  (load-dataset "resources/data/mpg.csv"))

(def mtcars
  (load-dataset "resources/data/mtcars.csv"))
