(ns runguy
  (:require-macros [macros :refer [def-method]])
  (:require
   [engine.animation :as animation]
   [engine.input :as input]
   [engine.assets :as ea]
   [gamestate :refer [render-entity update-entity]]))

(def-method update-entity :runguy
  [this _dt]
  (when (input/key-down? (:A input/keys))
    (set! (.-x this) (dec (.-x this))))
  (when (input/key-down? (:D input/keys))
    (set! (.-x this) (inc (.-x this))))
  (when (input/key-down? (:W input/keys))
    (set! (.-y this) (dec (.-y this))))
  (when (input/key-down? (:S input/keys))
    (set! (.-y this) (inc (.-y this)))
    (ea/play-audio :fireball)))

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
   :cells [0 2 1 2 3 2 1]
   :loop true})

(defn create [{:keys [x y rotation]}]
  {:type :runguy
   :x x
   :y y
   :rotation (or rotation 0)
   :animation-component {:animations {:run (runguy-anim)}
                         :current-animation :run}
   :scale 0.5})


