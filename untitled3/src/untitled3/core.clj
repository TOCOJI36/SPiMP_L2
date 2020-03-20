(ns untitled3.core
  (:require [clojure.core.async :refer [go <! >!] :as a]))

(def queue (a/chan 100))

(defn writing [chan_ n]
  (go (doseq [x (range 0 n) ]
      (let [value (rand-int 10)]
        (>! chan_ value)))))

(defn reading [chan_]
  (go [] (while true
           (loop [i (<! chan_) v [] ]
             (if (< 0 i) (recur (dec i) (conj v (<! chan_)))
                         (println v))))))

(defn -main [] (println "gogog"))

