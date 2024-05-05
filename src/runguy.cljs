(ns runguy
  (:require-macros [macros :refer [def-method]])
  (:require
   [engine.animation :as animation]
   [gamestate :refer [render-entity update-entity]]))

(def-method render-entity :runguy
  [this ctx]
  (let [current-animation (get-in this [:animation-component :current-animation])
        animation (get-in this [:animation-component :animations current-animation])]
    (animation/draw-animation this animation ctx)))


(defn runguy-anim []
  {:sheet :runguy
   :height 500
   :width 210
   :duration 40
   :durationCounter 0
   :frame 0
   :rows 2
   :columns 5
   :loop true})

(defn update [this dt]
  #_(assoc! ship :rotation (+ 0.02 (:rotation ship)))
  #_(assoc! ship :x (+ 0.21 (:x ship))))

(defn create [{:keys [x y rotation]}]
  {:type :runguy
   :x x
   :y y
   :rotation (or rotation 0)
   :animation-component {:animations {:run (runguy-anim)}
                         :current-animation :run}
   :scale 0.5 })


