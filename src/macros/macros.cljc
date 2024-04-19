(ns macros.macros)

(defmacro defcomp [name fields]
  `(defn ~name [~@fields]
     (merge (zipmap ~(vec (map str fields)) ~fields)
            {:component-type ~(str name)})))
