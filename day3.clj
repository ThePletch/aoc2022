(ns org.thepletch.aoc)
(require '[clojure.set :as set])
(require '[org.thepletch.aoc.utils :as aoc])

; gets the single character appearing in both halves of the string.
; assumes there's only one such character.
(defn get-doubled-char [sack-str]
  (first (apply set/intersection
                (map (fn [substr] (set (char-array substr)))
                     (split-at (/ (count sack-str)
                                  2)
                               sack-str)))))

; a-z is 1-26, A-Z is 27-52.
; only functions correctly for alphabetical characters
(defn priority [char]
  (let [charindex (int char)]
    (cond
      (>= charindex 97) (- charindex 96) ; a-z is indices 97-122
      :else (- charindex 38))))          ; A-Z is indices 65-90

(println (aoc/linesum (first *command-line-args*)
                      (fn [line] (priority (get-doubled-char line)))))
