(ns main
  (:require
   [squint.core :refer [defclass js-await]]
   [assets :as assets]
   [gamestate :as gs]
   [scene :as scene]))



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

(defn main-loop [game-state time]
  (set! game-state.dt (/ (- time (:last-update game-state)) 1000))
  (set! game-state.last-update time)

  (.save (:context game-state))
  (game-update game-state)
  (draw game-state)
  (.restore (:context game-state))

  (js/window.requestAnimationFrame (partial main-loop game-state)))

(defn init-game []
  (let [canvas (create-canvas! "2" 500 500)
        context (create-context! canvas)]
    
    (gs/subscribe-to-keyboard-events gs/game-state)
    
    (println gs/game-state)
    (set! gs/game-state.canvas canvas)
    (set! gs/game-state.context context)
    (-> (js/document.querySelector "#app")
        (.append canvas)))

  (load)

  (assoc! gs/game-state :current-scene (scene/scene1))

  (js/window.requestAnimationFrame (partial main-loop gs/game-state)))

(init-game)
