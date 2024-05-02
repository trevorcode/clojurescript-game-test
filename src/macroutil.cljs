(ns macroutil)

(defn multimethodfn [dispatch]
  (let [multimethods (atom {})]
    (fn [& args]
      (if (= :attach-method (first args))
        multimethods
        (let [multimethods @multimethods
              method (or (get multimethods (apply dispatch args)) (:default methods))]
          (if method
            (apply method args)
            (throw (js/Error. (str "No method found for dispatch values: " args)))))))))