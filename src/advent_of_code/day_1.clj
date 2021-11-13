(ns advent-of-code.day-1
  (:require [clojure.string :as str]
            [clojure.pprint :as p]))

(defn load-input
  []
  (map #(Integer/parseInt %) (str/split-lines (slurp "txts/day-1.txt"))))

(defn part-one
  []
  (let [input (load-input)]
    (for [x input
          y input
          :when (= (+ x y) 2020)]
      (* x y))))

(defn part-two
  []
  (let [input (load-input)]
    (for [x input
          y input
          z input
          :when (= (+ x y z) 2020)]
      (* x y z))))

(defn -main
  []
  (p/pprint (part-one))
  (p/pprint (part-two)))
