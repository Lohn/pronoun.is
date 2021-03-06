(ns pronouns.pages-test
  (:require [pronouns.pages :as pages]
            [clojure.test :refer [deftest testing is are]]))

(deftest prose-comma-list
  (testing "`prose-comma-list` turns a list of strings into a prose list"
    (are [v s] (= (pages/prose-comma-list v) s)
      ["foo" "bar" "baz" "bobble"] "foo, bar, baz, and bobble"
      ["foo" "bar" "baz"]          "foo, bar, and baz"
      ["foo" "bar"]                "foo and bar"
      ["foo"]                      "foo"
      []                           "")))

(deftest lookup-pronouns
  (are [pronoun-strs pronouns]
      (= (pages/lookup-pronouns pronoun-strs)
         pronouns)
    ["she/her"]           '(["she" "her" "her" "hers" "herself"])
    ["she" "they"]        '(["she" "her" "her" "hers" "herself"]
                            ["they" "them" "their" "theirs" "themselves"])
    ["she/her" "foo/bar"] '(["she" "her" "her" "hers" "herself"])
    ["foo/bar"]           '()
    ["a/b/c/d/e"]         '(("a" "b" "c" "d" "e"))))
