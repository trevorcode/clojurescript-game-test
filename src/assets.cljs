(ns assets)

(def images {})
(def image-list {:ship "assets/ship.png"})

(defn load-image [url]
  (-> (new js/Image)
      (assoc! :src url)))

(defn load-images []
  (->> image-list
       (mapv (fn [[k v]] {k (load-image v)}))
       (into {})
       (set! images))) 
