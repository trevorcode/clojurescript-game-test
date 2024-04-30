(ns ship
  (:require [assets :as assets]
            [engine.animation :refer [draw-image]]))

(defn update [ship dt]
  (assoc! ship :rotation (+ 0.02 (:rotation ship)))
  (assoc! ship :x (+ 0.21 (:x ship))))

(defn draw [ship ctx]
  (let [image (get-in assets/images [:ship :image])]
    (draw-image ctx image ship)))

(defn create-ship [{:keys [x y rotation]}]
  {:x x
   :y y
   :rotation (or rotation 0)
   :scale 3
   :update update
   :draw draw})

