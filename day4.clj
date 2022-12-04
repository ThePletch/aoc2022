(ns org.thepletch.aoc)
(require '[clojure.string :as str])
(require '[org.thepletch.aoc.utils :as aoc])

; splits a string of the form num-num,num-num into two two-element seqs
; didn't bother using clojure's range function because ranges have no special semantics,
; they're just lazy seqs of integers
(defn range-pair [line]
  (map (fn [range-str] (map read-string (str/split range-str #"-"))) (str/split line #",")))

; first range is contained by second range
(defn range-within [within without]
  (and (>= (first within) (first without))
       (<= (last within) (last without))))

; either range is contained by the other
(defn range-within-either [range-a range-b]
  (or (range-within range-a range-b)
      (range-within range-b range-a)))

(println (aoc/linesum (first *command-line-args*)
                      (fn [line] (if (apply range-within-either (range-pair line)) 1 0))))
