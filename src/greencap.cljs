(ns greencap
  (:require-macros [macros :refer [def-method]])
  (:require
   [engine.animation :as animation]
   [engine.input :as input]
   [gamestate :refer [render-entity update-entity]]))

;;https://dev.to/martyhimmel/animating-sprite-sheets-with-javascript-ag3

(defn greencap-animation []
  {:sheet :greencap
   :height 18
   :width 16
   :duration 10
   :durationCounter 0
   :frame 0
   :rows 4
   :columns 3
   :cells [0 1 2 3 4 5 6 7 8 9 10 11]
   :loop false})

(defn greencap-idle []
  {:sheet :greencap
   :height 18
   :width 16
   :duration 40
   :durationCounter 0
   :frame 0
   :rows 4
   :columns 3
   :cells [0 1 2]
   :loop true})

(def-method render-entity :greencap [this ctx]
  (let [current-animation (get-in this [:animation-component :current-animation])
        animation (get-in this [:animation-component :animations current-animation])]
    (animation/draw-animation this animation ctx)))

(def-method update-entity :greencap
  [this _dt]
  (let [vx (aget (.-velocity this) 0)
        vy (aget (.-velocity this) 1)
        {x :x
         y :y} this]
    (when (input/key-down? (:A input/keys))
      (aset (.-velocity this) 0 (- vx 0.25)))
    (when (input/key-down? (:D input/keys))
      (aset (.-velocity this) 0 (+ vx 0.25)))
    (when (input/key-down? (:S input/keys))
      (aset (.-velocity this) 1 (+ vy 0.25)))
    (when (input/key-down? (:W input/keys))
      (aset (.-velocity this) 1 (- vy 0.25)))

    (set! (.-x this) (+ vx x))
    (set! (.-y this) (+ vy y))

    (let [x-sign (if (> vx 0) - +)
          y-sign (if (> vy 0) - +)]
      (when (> (js/Math.abs vx) 0)
        (aset (.-velocity this) 0 (x-sign (aget (.-velocity this) 0) 0.1)))
      (when (> (js/Math.abs vy) 0)
        (aset (.-velocity this) 1 (y-sign (aget (.-velocity this) 1) 0.1))))))

(defn create [{:keys [x y rotation]}]
  {:type :greencap
   :x x
   :y y
   :velocity [0 0]
   :rotation (or rotation 0)
   :animation-component {:animations {:run (greencap-animation)
                                      :idle (greencap-idle)}
                         :current-animation :idle}
   :scale 3})

