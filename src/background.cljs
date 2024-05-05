(ns background
  (:require-macros [macros :refer [def-method]])
  (:require [assets :as assets]
            [engine.animation :refer [draw-image]]
            [gamestate :refer [render-entity]]))

(def-method render-entity :bg [this ctx]
  (draw-image ctx (get-in assets/images [:bg :image]) this))

(defn create [{:keys [x y rotation]}]
  {:type :bg
   :x x
   :y y
   :rotation (or rotation 0)
   :scale 0.5})
