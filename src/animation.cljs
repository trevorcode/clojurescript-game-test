(ns animation
  (:require [assets :refer [images]]))

(defn increment-frame [{:keys [rows columns frame loop] :as animation}]
  (set! animation.frame (if (< (inc frame) (* rows columns))
                          (inc frame)
                          (if loop
                            0
                            frame))))

(defn get-frame-x-y [frame columns]
  [(int (/ frame columns)) (mod frame columns)])

(defn step-animation
  [{:keys [duration
           durationCounter] :as animation}]
  (set! animation.durationCounter (if (>= durationCounter duration)
                                    (do
                                      (increment-frame animation)
                                      0)
                                    (inc durationCounter))))

(defn draw-frame [ctx
                  image
                  {:keys [width height frame columns]}]
  (let [[frame-x frame-y] (get-frame-x-y frame columns)]
    (ctx.drawImage image
                   (* frame-y width)
                   (* frame-x height)
                   width
                   height
                   (/ (- width) 2)
                   (/ (- height) 2)
                   width
                   height)))

(defn draw-animation
  [{:keys [x y rotation scale] :as this}
   {:keys [frame columns sheet] :as animation}
   ctx]
  (let [image (get-in images [sheet :image])]
    (ctx.setTransform scale 0 0 scale x y)
    (when rotation
      (ctx.rotate rotation))
    (draw-frame ctx image animation)
    (ctx.setTransform 1 0 0 1 0 0)
    (step-animation animation)))