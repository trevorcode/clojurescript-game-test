(ns scene
  (:require [ship :as ship]
            [assets :as assets]
            [gamestate :as gs]))

(defn scene-draw [scene context]
  (doseq [game-obj (:objects scene)]
    (game-obj.draw game-obj context))
  
  (when (gs/key-down? gs/game-state 68)
    (context.strokeText "Hello world!" 50 50)))

(defn scene-update [scene dt]
  (doseq [game-obj (:objects scene)]
    (game-obj.update game-obj dt)))

(defn scene1 []
  {:objects [(ship/create-ship {:x 50 :y 50 :rotation 10 :sprite (:ship assets/images)})
             (ship/create-ship {:x 30 :y 80 :rotation 90 :sprite (:ship assets/images)})]})