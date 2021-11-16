(ns advent-of-code.day-3
  (:require [clojure.string :as str]
            [clojure.pprint :as p]))

(defn character
  [c]
  (if (= c \.) :space
      :tree))

(defn parse-line
  [c]
  (cycle (map character c)))

(defn parse-input
  [c]
  (let [ch (str/split c #"\n")]
    (map parse-line ch)))

(defn pos
  [grid x y]
  (let [row (nth grid y)]
    (nth row x)))

(defn find-pos
  [c]
  (let [grid (parse-input c)
        rows (count (parse-input c))]
  (loop [x 0
         y 0
         path '()]
    (let [new-x (+ x 3)      
          new-y (+ y 1)]
      (if (>= new-y rows)
        path
        (recur new-x new-y (conj path (pos grid new-x new-y))))))))

(defn load-input
  []
  (slurp "txts/day-3.txt"))

(defn trees
  []
  (let [input (load-input)]
    (count (filter #(= :tree %) (find-pos input)))))

(defn find-pos-2
  [move-x move-y]
  (let [input (load-input)
        grid (parse-input input)
        rows (count (parse-input input))]
    (loop [x 0
           y 0
           path '()]
      (let [new-x (+ x move-x)
            new-y (+ y move-y)]
        (if (>= new-y rows)
          path
          (recur new-x new-y (conj path (pos grid new-x new-y))))))))

(defn trees-2
  [x y]
  (count (filter #(= :tree %) (find-pos-2 x y))))

(defn solution
  []
  (map (fn ([[x y]]
            (trees-2 x y)))
       [[1 1]
        [3 1]
        [5 1]
        [7 1]
        [1 2]]))

(defn -main
  []
  (p/pprint (trees))
  (p/pprint (solution)))
