(ns untitled3.core
  (:require [clojure.core.async :refer [go <! >! take!] :as a]))

(def in_queue (a/chan 100))
(def out_queue (a/chan 100))

(defn writing [chan_ n]
  (go (doseq [x (range 0 n) ]
      (let [value (rand-int 10)]
        (>! chan_ value)))))

(defn reading [input_chan output_chan]
  (go [] (while true
           (loop [i (<! input_chan) v [] ]
             (if (< 0 i) (recur (dec i) (conj v (<! input_chan)))
                         (>! output_chan v))))))
(defn read_chan [chan]
  (take! chan (fn [r] (println "r->" r) (when (not= r nil) (read_chan chan)))))

(defn -main [] (println "gogog"))

