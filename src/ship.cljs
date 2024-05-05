(ns ship
  (:require-macros [macros :refer [def-method]])
  (:require [assets :as assets]
            [engine.animation :refer [draw-image]]
            [gamestate :refer [render-entity update-entity]]))

(def-method update-entity :ship [ship dt]
  (assoc! ship :rotation (+ 0.02 (:rotation ship)))
  (assoc! ship :x (+ 0.21 (:x ship))))

(def-method render-entity :ship [ship ctx]
  (let [image (get-in assets/images [:ship :image])]
    (draw-image ctx image ship)))

(defn create-ship [{:keys [x y rotation]}]
  {:type :ship
   :x x
   :y y
   :rotation (or rotation 0)
   :scale 3 })

