(require '[clojure.string :as str])
(println (apply max
                (map (fn [numlist] (reduce + (map read-string numlist)))
                     (map (fn [strlist] (str/split strlist #"\n"))
                          (str/split (slurp (first *command-line-args*))
                                     #"\n\n")))))
