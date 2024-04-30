(ns background
  (:require [assets :as assets]
            [engine.animation :refer [draw-image]]))

(defn update [this dt])

(defn draw [this ctx]
  (draw-image ctx (get-in assets/images [:bg :image]) this))

(defn create [{:keys [x y rotation]}]
  {:x x
   :y y
   :rotation (or rotation 0)
   :scale 0.5
   :update update
   :draw draw})

