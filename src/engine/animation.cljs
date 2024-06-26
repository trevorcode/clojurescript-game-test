(ns engine.animation
  (:require [engine.assets :refer [images animations]]))

(defn increment-frame [{:keys [rows columns frame loop cells] :as animation}]
  (set! animation.frame (if (< (inc frame) (count cells))
                          (inc frame)
                          (if loop
                            0
                            frame))))

(defn get-cell-x-y [cell columns]
  [(int (/ cell columns)) (mod cell columns)])

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
                  {:keys [width height frame columns cells]}]
  (let [[frame-x frame-y] (get-cell-x-y (nth cells frame) columns)]
    (ctx.drawImage image
                   (* frame-y width)
                   (* frame-x height)
                   width
                   height
                   (/ (- width) 2)
                   (/ (- height) 2)
                   width
                   height)))

(defn play-animation [entity animation]
  (assoc! entity :animation (merge {} (get animations animation))))

(defn draw-animation
  [{{:keys [x y rotation scale]} :transform}
   {:keys [sheet] :as animation}
   ctx]
  (let [image (get-in images [sheet :image])]
    (ctx.setTransform scale 0 0 scale x y)
    (when rotation
      (ctx.rotate rotation))
    (draw-frame ctx image animation)
    (ctx.setTransform 1 0 0 1 0 0)
    (step-animation animation)))

(defn draw-image [ctx image {:keys [x y rotation scale]}]
  (ctx.setTransform scale 0 0 scale x y)
  (when rotation
    (ctx.rotate rotation))
  (ctx.drawImage image (/ (- image.width) 2) (/ (- image.height) 2))
  (ctx.setTransform 1 0 0 1 0 0))