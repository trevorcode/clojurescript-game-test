(ns scene
  (:require [ship :as ship]))


(defn scene-draw [scene context]
  (doseq [game-obj (:objects scene)]
    (game-obj.draw game-obj context)))

(defn scene-update [scene dt]
  (doseq [game-obj (:objects scene)]
    (game-obj.update game-obj dt)))

(def scene1 {:objects [(ship/create-ship {:x 50 :y 50 :rotation 10})
                       (ship/create-ship {:x 50 :y 50 :rotation 10})]})