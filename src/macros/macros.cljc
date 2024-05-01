(ns macros.macros)


(defn multimethod [dispatch]
  (let [methods {}
        meta {:dipatch dispatch :methods methods}]))

(defmacro defcomp [name fields]
  `(defn ~name [~@fields]
     (merge (zipmap ~(vec (map str fields)) ~fields)
            {:component-type ~(str name)})))
