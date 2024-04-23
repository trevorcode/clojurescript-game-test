(ns ship
  (:require
   [squint.core :refer [defclass js-await]]
   [assets :as assets]))

(defn update [ship dt]
  (assoc! ship :rotation (+ 0.02 (:rotation ship)))
  (assoc! ship :x (+ 0.21 (:x ship))))

(defn draw-image [{:keys [x y rotation sprite scale]} ctx]
  (ctx.setTransform scale 0 0 scale x y)
  (when rotation
    (ctx.rotate rotation))
  (ctx.drawImage sprite (/ (- sprite.width) 2) (/ (- sprite.height) 2))
  (ctx.setTransform 1 0 0 1 0 0))

(defn draw [ship ctx]
  (draw-image ship ctx))

(defn create-ship [{:keys [x y rotation sprite]}]
  {:x x
   :y y
   :rotation (or rotation 0)
   :sprite sprite
   :scale 3
   :update update
   :draw draw})

