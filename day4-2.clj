(ns org.thepletch.aoc)
(require '[clojure.set :as set])
(require '[clojure.string :as str])
(require '[org.thepletch.aoc.utils :as aoc])

; splits a string of the form num-num,num-num into two ranges, expressed
; as sets instead of seqs for inclusion testing (since seqs are hard to test for
; inclusion in clojure)
(defn range-pair [line]
  ; we need to do this conj to make the range inclusive of its end
  (map (fn [base-range-pair]
         (conj (set (apply range base-range-pair)) (last base-range-pair)))
    (map (fn [range-str]
           (map read-string
                (str/split range-str
                           #"-")))
       (str/split line #","))))

; any overlap between the two provided ranges
; assumes ranges are expressed as sets of sequential integers
(defn range-overlaps [range-a range-b]
  (not-empty (set/intersection range-a
                         range-b)))

(println (aoc/linesum (first *command-line-args*)
                      (fn [line] (if (apply range-overlaps (range-pair line))
                                   1
                                   0))))
