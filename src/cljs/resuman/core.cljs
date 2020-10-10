(ns resuman.core
  (:require [helix.core :refer [defnc $ <>]]
            [helix.dom :as d]
            ["react-dom" :as dom]))

(defnc nav []
  (d/nav {:class '[py-4 shadow]}
    (d/div {:class '[container]}
      (d/h2 {:class '[text-xl]} "Resuman"))))

(defnc app []
  (<>
    ($ nav)))

(defn ^:export init []
  (dom/render ($ app) (js/document.getElementById "app")))
