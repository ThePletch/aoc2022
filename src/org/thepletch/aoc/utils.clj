(ns org.thepletch.aoc.utils)
(require '[clojure.java.io :as io])

; reads a sequence of lines from the specified file,
; processes each one into a number with the provided function,
; and sums the result.
; optionally accepts a line preprocessor to (e.g.) collect lines into groups
(defn linesum
  ([filename line-processor] (linesum filename line-processor identity))
  ([filename line-processor lines-preprocessor]
   (with-open [rdr (io/reader filename)]
     (reduce (fn [sum line] (+ sum (line-processor line)))
             0
             (lines-preprocessor (line-seq rdr))))))
