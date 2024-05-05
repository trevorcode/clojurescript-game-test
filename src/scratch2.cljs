(ns scratch
  (:require-macros [macros :refer [def-multi def-method]])
  (:require [macros :refer [multimethodfn]]
            [scratch :refer [testing]]))

(def-method testing :papas [x] (println x))
(def-method testing :potate [x] (println x))
(testing {:type :papas})
(testing {:type :potate})