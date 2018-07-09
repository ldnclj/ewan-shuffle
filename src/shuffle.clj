(ns shuffle)

(def products [{:rank 1 :provider "BT" :product "fast"}
               {:rank 2 :provider "BT" :product "faster"}
               {:rank 3 :provider "BT" :product "fastest"}
               {:rank 4 :provider "Sky" :product "fast"}
               {:rank 5 :provider "BT" :product "fastestest"}
               {:rank 6 :provider "BT" :product "fastestestest"}
               {:rank 7 :provider "Virgin" :product "fast"}
               ; {:rank 8 :provider "Virgin" :product "faster"}
               ; {:rank 9 :provider "Virgin" :product "fastest"}
               ; {:rank 10 :provider "BT" :product "fastestestestest"}
               ])

(defn shuffle [products]
  (loop [result      []
         unprocessed products]

    (if (< 3 (count unprocessed))

      (let [[window tail] (split-at 3 unprocessed)
            shuffle?      (= 1 (count (partition-by :provider window)))]

        (recur (conj result (first unprocessed))
               (if shuffle?
                 (concat (drop 1 window) (take 1 tail) (drop 2 window) (drop 1 tail))
                 (drop 1 unprocessed))))

      (concat result unprocessed))))

; (defn shuffle [products]
;   (let [shuffled (shuffler products)]
;     (if (= shuffled products)
;       shuffled
;       (shuffle shuffled))))
