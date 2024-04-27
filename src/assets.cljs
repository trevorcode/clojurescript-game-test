(ns assets)

(def images {})
(def image-list {:ship {:type :single
                        :url "assets/ship.png"}
                 :greencap {:type :sheet
                            :url "assets/greencap.png"
                            :height 18
                            :width 16
                            :count 5
                            :duration 30
                            :durationCounter 0
                            :frame 0
                            :rows 4
                            :columns 3}})

(defn load-image [url]
  (-> (new js/Image)
      (assoc! :src url)))

(defn load-images []
  (->> image-list
       (mapv (fn [[k v]] {k (assoc v :image (load-image (:url v)))}))
       (into {})
       (set! images))) 
