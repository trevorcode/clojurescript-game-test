(ns scene
  (:require [ship :as ship]
            [greencap :as greencap]
            [assets :as assets]
            [gamestate :as gs]))

(defn scene-draw [scene context]
  (doseq [game-obj (:objects scene)]
    (game-obj.draw game-obj context))

  (when (gs/key-down? gs/game-state 68)
    (doseq [greencap (->> (get-in gs/game-state [:current-scene :objects])
                          (filterv #(= (:type %) :greencap)))]
      (assoc! (-> greencap :animation-component) :current-animation :run))
    (context.strokeText "Hello world!" 50 50)))

(defn scene-update [scene dt]
  (doseq [game-obj (:objects scene)]
    (game-obj.update game-obj dt)))

(defn scene1 []
  {:objects
   (into [(ship/create-ship {:x 50 :y 50 :rotation 10 :sprite (:ship assets/images)})
          (ship/create-ship {:x 30 :y 80 :rotation 90 :sprite (:ship assets/images)})]
         (take 3 (repeatedly
                  #(greencap/create
                    {:x (* (js/Math.random) 350)
                     :y (* (js/Math.random) 350)
                     :rotation (* (js/Math.random) 350)}))))})
