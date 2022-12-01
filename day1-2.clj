(require '[clojure.string :as str])
(println (reduce +
                 (take 3
                       (sort >
                             (map (fn [numlist] (reduce + (map read-string numlist)))
                                  (map (fn [strlist] (str/split strlist #"\n"))
                                       (str/split (slurp (first *command-line-args*))
                                                  #"\n\n")))))))
