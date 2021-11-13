(ns advent-of-code.day-2
  (:require [clojure.string :as str]
            [clojure.pprint :as p]))

(defn parse-password
  [s]  
  (let [[range letter password] (str/split s #" ")
        [min max] (str/split range #"-")]
    {:min (Long/parseLong min)   
     :max (Long/parseLong max)
     :letter (subs letter 0 1)
     :password password}))

(defn load-input
  []
  (map parse-password (str/split-lines (slurp "txts/day-2.txt"))))

(defn validate-password
  [p]
  (let [times (count (filter #(= (get p :letter) (str %)) (get p :password)))]
    (when (>= times (get p :min))
      (<= times (get p :max)))))

(defn correct-passwords
  []
  (let [input (load-input)]
    (count (filter validate-password input))))
                                   
(defn validate-password-2
  [c]
  (let [min (- (get c :min) 1)
        max (- (get c :max) 1)
        a (= (get c :letter) (str (nth (get c :password) min)))
        b (= (get c :letter) (str (nth (get c :password) max)))]
    (or (and (= a true) (= b false)) (and (= a false) (= b true)))))

(defn correct-passwords-2
  []
  (let [input (load-input)]
    (count (filter validate-password-2 input))))

(defn -main
  []
  (p/pprint (correct-passwords))
  (p/pprint (correct-passwords-2)))
