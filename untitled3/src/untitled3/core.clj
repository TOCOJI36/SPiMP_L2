(ns untitled3.core
  (:require [clojure.core.async :refer [go <! >!] :as a]))

(def queue (a/chan 100))

(defn writing [chan n]
  (go (doseq [x (range 0 n) ]
      (let [value (rand-int 10)]
        (>! chan value)))))

(defn reading [chan]
  (go [] (while true
           (loop [i (<! chan) v [] ]
             (if (< 0 i) (recur (dec i) (conj v (<! chan)))
                         (println v))))))

(defn -main [] (println "gogog"))

