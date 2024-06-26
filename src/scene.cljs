(ns scene
  (:require [background :as background]
            [gamestate :as gs]
            [engine.input :as input]
            [greencap :as greencap]
            [runguy :as runguy]
            [ship :as ship]))

(defn scene-draw [scene context]
  (doseq [game-obj (:objects scene)]
    (gs/render-entity game-obj context))

  (when (input/key-down? 68)
    (context.strokeText "Hello world!" 50 50)))

(defn scene-update [scene dt]
  (doseq [game-obj (:objects scene)]
    (gs/update-entity game-obj dt)))

(defn scene1 []
  {:objects
   (into [(background/create)
          (ship/create-ship {:x 50 :y 50 :rotation 10})
          (ship/create-ship {:x 30 :y 80 :rotation 90})
          (runguy/create {:x 180 :y 180})]
         (take 2 (repeatedly
                  #(greencap/create
                    {:x (* (js/Math.random) 350)
                     :y (* (js/Math.random) 350)
                     :rotation (* (js/Math.random) 350)}))))})
