(ns org.thepletch.aoc)
(require '[clojure.string :as str])

(defn get-value-from-column [column-char-array]
  (let [column-str (str/join column-char-array)]
    (if (= column-str "   ") nil (get column-str 1))))

(defn split-stack-line-by-column [stack-line]
  (map-indexed (fn [idx itm] [idx itm]) (map get-value-from-column (partition 3 4 stack-line))))

; stack lines are from top to bottom and of the form `([\A]|   ) `+
(defn parse-initial-stacks [stack-lines]
  (reduce-kv (fn [stacks k v]
               (assoc stacks k (reverse v)))
             (sorted-map)
             (reduce (fn [stacks stack-line]
                       (reduce (fn [stacks-inner stack-value]
                                 (let [stack-index (+ (get stack-value 0) 1)
                                       stack-item (get stack-value 1)]
                                   (if (nil? stack-item)
                                     stacks-inner
                                     (assoc stacks-inner
                                            stack-index
                                            (cons stack-item
                                                  (get stacks-inner
                                                       stack-index
                                                       (seq nil)))))))
                               stacks
                               (split-stack-line-by-column stack-line)))
                     (sorted-map)
                     stack-lines)))

(def instruction-regex #"move (\d+) from (\d+) to (\d+)")

(defn parse-instructions [instruction-lines]
  (map (fn [instruction-line]
         (let [match (re-matches instruction-regex instruction-line)]
           {:quantity (read-string (get match 1))
            :src (read-string (get match 2))
            :dest (read-string (get match 3))}))
       instruction-lines))

(defn perform-instruction [stacks instruction]
  (if (<= (get instruction :quantity) 0)
    stacks
    (let [src (get instruction :src)
          dest (get instruction :dest)
          quantity (get instruction :quantity)
          src-top (first (get stacks src))]
      (perform-instruction (assoc stacks
                                  src (rest (get stacks src))
                                  dest (cons src-top (get stacks dest)))
                           (assoc instruction :quantity (- quantity 1))))))

(let [split-input (str/split (slurp (first *command-line-args*)) #"\n\n")
      initial-stacks (parse-initial-stacks (str/split (get split-input 0) #"\n"))
      instructions (parse-instructions (str/split (get split-input 1) #"\n"))]
  (println (str/join (map (fn [key-value-pair] (first (get key-value-pair 1))) (reduce perform-instruction initial-stacks instructions)))))
