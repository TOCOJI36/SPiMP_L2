(ns untitled3.core
  (:require [clojure.core.async :refer [go <! >!] :as a]))

(def queue (a/chan 100))

(defn writing [n]
  (go (doseq [x (range 0 n) ]
      (let [value (rand-int 10)]
        (println (str "put -> " value))
        (>! queue value)))))

(defn reading []
  (go [] (while true
           (loop [i (<! queue) v [] ]
             (if (< 0 i) (recur (dec i) (conj v (<! queue)))
                         (println v))))))

(defn -main [] (println "gogog"))

