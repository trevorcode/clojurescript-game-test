(ns greencap
  (:require
   [squint.core :refer [defclass js-await]]
   [assets :as assets]))

;;https://dev.to/martyhimmel/animating-sprite-sheets-with-javascript-ag3
(defn update [ship dt]
  (assoc! ship :rotation (+ 0.02 (:rotation ship)))
  #_(assoc! ship :x (+ 0.21 (:x ship))))

(defn increment-frame [{:keys [rows columns frame] :as sprite}]
  (set! sprite.frame (if (< (inc frame) (* rows columns))
                       (inc frame)
                       0)))

(defn get-frame-x-y [frame columns]
  [(int (/ frame columns)) (mod frame columns)])
(get-frame-x-y 0 3)

(defn update-duration-counter
  [{:keys [duration
           durationCounter] :as sprite}]
  (set! sprite.durationCounter (if (>= durationCounter duration)
                                 (do
                                   (increment-frame sprite)
                                   0)
                                 (inc durationCounter))))

(defn draw-frame [ctx {:keys [image
                              width
                              height]}
                  frame-x
                  frame-y]
  (ctx.drawImage image
                 (* frame-y width)
                 (* frame-x height)
                 width
                 height
                 (/ (- width) 2)
                 (/ (- height) 2)
                 width
                 height))

(defn draw-image [{:keys [x y rotation sprite scale]} ctx]
  (let [{:keys [frame columns]} sprite
        [frame-x frame-y] (get-frame-x-y frame columns)]
    (ctx.setTransform scale 0 0 scale x y)
    (when rotation
      (ctx.rotate rotation))
    (draw-frame ctx sprite frame-x frame-y)
    (update-duration-counter sprite)
    (ctx.setTransform 1 0 0 1 0 0)))

(defn draw [ship ctx]
  (draw-image ship ctx))

(defn create [{:keys [x y rotation sprite]}]
  {:x x
   :y y
   :rotation (or rotation 0)
   :sprite (:greencap assets/images)
   :scale 3
   :update update
   :draw draw})

