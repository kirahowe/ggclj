(ns scicloj.ggclj.api-test
  (:require [clojure.test :refer [deftest testing is]]
            [scicloj.ggclj.api :as sut]))

(deftest ggplot
  (testing "converts data to datasets"
    (is (= tech.v3.dataset.impl.dataset.Dataset
           (type (sut/ggplot {:a [1 2 3]
                              :b [4 5 6]}))))))
