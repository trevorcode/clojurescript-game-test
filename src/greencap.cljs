(ns greencap
  #_(:require-macros [macros :refer [def-method]])
  (:require [engine.animation :as animation]
            [gamestate :refer [render-entity] :as gs]))

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
   :loop false})

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

#_(def-method render-entity :greencap [this ctx]
    (let [current-animation (get-in this [:animation-component :current-animation])
          animation (get-in this [:animation-component :animations current-animation])]
      (animation/draw-animation this animation ctx)))

(deftype Potato [])

(deftype GreenCap [this]
  gs/IDrawable
  (draw [this ctx]
    (println (instance? GreenCap this))
    (let [this (.-this$ this)
          current-animation (get-in this [:animation-component :current-animation])
          animation (get-in this [:animation-component :animations current-animation])]
        (animation/draw-animation this animation ctx))))

(defn create [{:keys [x y rotation]}]
  (->> {:type :greencap
        :x x
        :y y
        :rotation (or rotation 0)
        :animation-component {:animations {:run (greencap-animation)
                                           :idle (greencap-idle)}
                              :current-animation :idle}
        :scale 3}
       (new GreenCap)))


