(ns assets)

(def images {:ship {:type :single
                    :url "assets/ship.png"}
             :greencap {:type :sheet
                        :url "assets/greencap.png"}})

(defn load-image [url]
  (-> (new js/Image)
      (assoc! :src url)))

(defn load-images []
  (->> images
       (mapv (fn [[k v]] {k (assoc v :image (load-image (:url v)))}))
       (into {})
       (set! images)))
