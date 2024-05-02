(ns macros)

(defmacro def-multi
  [name dispatch]
  `(def ~name (macroutil/multimethodfn ~dispatch)))

(defmacro def-method
  [name dispatch-val args & body]
  `(swap! (~name :attach-method) assoc ~dispatch-val (fn [~@args] ~@body)))