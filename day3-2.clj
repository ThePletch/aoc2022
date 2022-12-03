(require '[clojure.java.io :as io])
(require '[clojure.set :as set])

; gets the single character appearing in all passed strings.
; assumes there's only one such character.
(defn get-common-char [sack-strs]
  (first (apply set/intersection
                (map (fn [substr] (set (char-array substr)))
                     sack-strs))))

; a-z is 1-26, A-Z is 27-52.
; only functions correctly for alphabetical characters
(defn priority [char]
  (let [charindex (int char)]
    (cond
      (>= charindex 97) (- charindex 96) ; a-z is indices 97-122
      :else (- charindex 38))))          ; A-Z is indices 65-90

(with-open [rdr (io/reader (first *command-line-args*))]
  (println (reduce + (map (fn [line] (priority (get-common-char line)))
                          (partition 3
                                     (line-seq rdr))))))
