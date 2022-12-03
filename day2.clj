(ns org.thepletch.aoc)
(require '[org.thepletch.aoc.utils :as aoc])

(def scoremap {"A X" 4   ; 1 + 3
               "A Y" 8   ; 2 + 6
               "A Z" 3   ; 3 + 0
               "B X" 1   ; 1 + 0
               "B Y" 5   ; 2 + 3
               "B Z" 9   ; 3 + 6
               "C X" 7   ; 1 + 6
               "C Y" 2   ; 2 + 0
               "C Z" 6}) ; 3 + 3
(println (aoc/linesum (first *command-line-args*)
         (fn [match] (get scoremap match))))
