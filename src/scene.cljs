(ns scene
  (:require [background :as background]
            [engine.animation :as animation]
            [gamestate :as gs]
            [greencap :as greencap]
            [runguy :as runguy]
            [ship :as ship]))

(defn scene-draw [scene context]
  (doseq [game-obj (:objects scene)]
    (game-obj.draw game-obj context))

  (when (gs/key-down? gs/game-state 68)
    (doseq [greencap (->> (get-in gs/game-state [:currentScene :objects])
                          (filterv #(= (:type %) :greencap))
                          #_(take 2))]
      (animation/play-animation greencap :run))
    (context.strokeText "Hello world!" 50 50)))

(defn scene-update [scene dt]
  (doseq [game-obj (:objects scene)]
    (game-obj.update game-obj dt)))

(defn scene1 []
  {:objects
   (into [
          (background/create {:x 250 :y 250})
          
          (ship/create-ship {:x 50 :y 50 :rotation 10})
          (ship/create-ship {:x 30 :y 80 :rotation 90})
          (runguy/create {:x 180 :y 180})]
         (take 2 (repeatedly
                   #(greencap/create
                     {:x (* (js/Math.random) 350)
                      :y (* (js/Math.random) 350)
                      :rotation (* (js/Math.random) 350)}))))})
