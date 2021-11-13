(ns advent-of-code.day-4
  (:require [clojure.string :as str]
            [clojure.pprint :as p]))

(defn passport-data
  [s]
  (let [spl (str/split s #"\s+")
        something (mapcat #(str/split % #":") spl)]
    (apply hash-map something)))

(defn load-input
  []
  (str/split (slurp "txts/day-4.txt") #"\n\n"))

(defn valid-passport?
  [passport]
  (let [result (dissoc passport "cid")]
    (= (count result) 7)))

(defn correct-passports
  []
  (let [input (load-input)]
    (count (filter valid-passport? (map passport-data input)))))

(defn valid-byr?
  [s]
  (let [byr (Long/parseLong (get s "byr"))]
    (<= 1920 byr 2002)))

(defn valid-iyr?
  [s]
  (let [iyr (Long/parseLong (get s "iyr"))]
    (<= 2010 iyr 2020)))

(defn valid-eyr?
  [s]
  (let [eyr (Long/parseLong (get s "eyr"))]
    (<= 2020 eyr 2030)))

(defn parse-long
  [s]
  (Long/parseLong (re-find #"\d+" s)))

(defn valid-hgt?
  [s]
  (let [hgt (get s "hgt")]
    (cond (str/ends-with? hgt "n")
          (<= 59 (parse-long hgt) 76)
          (str/ends-with? hgt "m")         
          (<= 150 (parse-long hgt) 193))))

(defn valid-hcl?
  [s]
  (let [hcl (get s "hcl")]
    (when (str/starts-with? hcl "#")
      (re-matches #"#[a-f0-9]{6}" hcl))))

(defn valid-pid?
  [s]
  (let [pid (get s "pid")]
    (re-matches #"[0-9]{9}" pid)))

(defn valid-ecl?
  [s]
  (let [ecl (get s "ecl")
        options #"amb|blu|brn|gry|grn|hzl|oth"]
    (re-matches options ecl)))
    
(defn valid-passports
  []
  (let [input (load-input)
        func (every-pred valid-passport? valid-byr? valid-eyr? valid-hgt? valid-iyr? valid-hcl? valid-pid? valid-ecl?)]
    (count (filter func (map passport-data input)))))

(defn -main
  []
  (p/pprint (correct-passports))
  (p/pprint (valid-passports)))
