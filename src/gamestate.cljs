(ns gamestate)

(def game-state
  {:dt 0
   :lastUpdate 0
   :mouse {:x 0
           :y 0
           :btn [false false false]}
   :keyboard #{}
   :canvas nil
   :context nil
   :loading true
   :currentScene {:objects []}})

(defn key-down? [game-state keycode]
  (get-in game-state [:keyboard keycode]))

(defn subscribe-to-keyboard-down-events [game-state]
  (js/document.addEventListener
   "keydown"
   (fn [e]
     (when (not (key-down? game-state (:keyCode e)))
       (conj! (:keyboard game-state) (:keyCode e))))))

(defn subscribe-to-keyboard-up-events [game-state]
  (js/document.addEventListener
   "keyup"
   (fn [e]
     (disj! (:keyboard game-state) (:keyCode e)))))

(defn subscribe-to-keyboard-events [game-state]
  (subscribe-to-keyboard-down-events game-state)
  (subscribe-to-keyboard-up-events game-state))