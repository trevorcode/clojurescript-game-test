(ns assets)

(def images {:ship {:type :single
                    :url "assets/ship.png"}
             :greencap {:type :sheet
                        :url "assets/greencap.png"}
             :bg {:type :single
                  :url "assets/bg.jpg"}
             :runguy {:type :sheet
                      :url "assets/runguy.jpg"}})

(defn load-image [url]
  (-> (new js/Image)
      (assoc! :src url)))

(defn load-images []
  (->> images
       (mapv (fn [[k v]] {k (assoc v :image (load-image (:url v)))}))
       (into {})
       (set! images)))
