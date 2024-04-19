(ns ship
  (:require
   [squint.core :refer [defclass js-await]]
   [assets :as assets]))

(defn update [ship dt]
  (assoc! ship :rotation (inc (:rotation ship))))

(defn draw [{:keys [x y rotation sprite]} ctx]
  (let [width (:width sprite)])
  (ctx.translate (+ (:width (:ship assets/images))) (:width (:ship assets/images)))
  (ctx.rotate rotation)
  (ctx.drawImage sprite x y 200 200))

(defn create-ship []
  {:x 50
   :y 50
   :rotation 70
   :sprite (:ship assets/images)
   :update update
   :draw draw})


