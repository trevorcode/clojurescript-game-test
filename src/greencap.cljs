(ns greencap
  (:require
   [squint.core :refer [defclass js-await]]
   [assets :as assets]
   [animation :as animation]))

(defn greencap-animation []
  {:sheet :greencap
   :height 18
   :width 16
   :duration 10
   :durationCounter 0
   :frame 0
   :rows 4
   :columns 3
   :loop true})

(defn greencap-idle []
  {:sheet :greencap
   :height 18
   :width 16
   :duration 40
   :durationCounter 0
   :frame 0
   :rows 1
   :columns 3
   :loop true})

;;https://dev.to/martyhimmel/animating-sprite-sheets-with-javascript-ag3
(defn update [ship dt]
  #_(assoc! ship :rotation (+ 0.02 (:rotation ship)))
  #_(assoc! ship :x (+ 0.21 (:x ship))))

(defn draw [this ctx]
  (let [current-animation (get-in this [:animation-component :current-animation])
        animation (get-in this [:animation-component :animations current-animation])]
    (animation/draw-animation this animation ctx)))

(defn create [{:keys [x y rotation sprite]}]
  {:type :greencap
   :x x
   :y y
   :rotation (or rotation 0)
   :animation-component {:animations {:run (greencap-animation)
                                      :idle (greencap-idle)}
                         :current-animation :idle}
   :scale 3
   :update update
   :draw draw})

