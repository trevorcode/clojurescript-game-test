(ns main
  (:require
   [squint.core :refer [defclass js-await]]
   [assets :as assets]
   [ship :as ship]
   [scene :as scene]))

(def game-state
  {:dt 0
   :last-update 0
   :mouse {:x 0
           :y 0
           :btn [false false false]}
   :keyboard []
   :canvas nil
   :context nil
   :loading true
   :current-scene {:objects []}})

(defn create-canvas! [id w h]
  (let [id-string (str "#canvas_" id)
        try-canv (js/document.querySelector id-string)
        canv (if try-canv
               try-canv
               (js/document.createElement "canvas"))]
    (assoc! canv :width w)
    (assoc! canv :height h)
    (assoc! canv :id id-string)
    canv))

(defn create-context! [canvas]
  (let [context (canvas.getContext "2d")]
    (assoc! context :imageSmoothingEnabled false)
    context))

(defn load []
  (assets/load-images))

(defn draw [{:keys [canvas context current-scene]}]
  (assoc! context :fillStyle "#f0f0e2")
  (context.fillRect 0 0 canvas.width canvas.height)
  (scene/scene-draw current-scene context))

(defn game-update [{:keys [current-scene dt]}]
  (scene/scene-update current-scene dt))


(defn main-loop [time]
  (assoc! game-state :dt (/ (- time (:last-update game-state)) 1000))
  (assoc! game-state :last-update time)

  (.save (:context game-state))
  (game-update game-state)
  (draw game-state)
  (.restore (:context game-state))

  (js/window.requestAnimationFrame main-loop))


(defn init-game []
  (let [canvas (create-canvas! "2" 500 500)
        context (create-context! canvas)]
    (assoc! game-state :canvas canvas)
    (assoc! game-state :context context)
    (-> (js/document.querySelector "#app")
        (.append canvas)))

  (load)

  (println assets/images)
  (assoc! game-state :current-scene (scene/scene1))
  (println game-state)
  
  

  (js/window.requestAnimationFrame main-loop))

(init-game)
