(ns scratch
  (:require-macros [macros :refer [def-multi def-method]])
  (:require [macroutil :as macroutil]))

(def-multi testing (fn [x] (:type x)))
(testing :attach-method)
;; (def-method testing :papas [x] (println x))
;; (def-method testing :potate [x] (println x))
;; (testing {:type :papas})
;; (testing {:type :potate})