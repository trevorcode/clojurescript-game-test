(ns engine.assets)

(def images {})

(defn register-image [kw image-url]
  (assoc! images kw image-url))

(defn register-images [image-map]
  (set! images (merge images image-map)))

(defn load-image [url]
  (-> (new js/Image)
      (assoc! :src url)))

(defn load-images []
  (->> images
       (mapv (fn [[k v]] {k (assoc v :image (load-image (:url v)))}))
       (into {})
       (set! images)))
