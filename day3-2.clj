(ns org.thepletch.aoc)
(require '[clojure.set :as set])
(require '[org.thepletch.aoc.utils :as aoc])

; gets the single character appearing in all passed strings.
; assumes there's only one such character.
(defn get-common-char [sack-strs]
  (first (apply set/intersection
                (map (fn [sack-str] (set (char-array sack-str)))
                     sack-strs))))

; a-z is 1-26, A-Z is 27-52.
; only functions correctly for alphabetical characters
(defn priority [char]
  (let [charindex (int char)]
    (cond
      (>= charindex 97) (- charindex 96) ; a-z is indices 97-122
      :else (- charindex 38))))          ; A-Z is indices 65-90

(println (aoc/linesum (first *command-line-args*)
                      (fn [line] (priority (get-common-char line)))
                      (fn [lines-seq] (partition 3 lines-seq))))
