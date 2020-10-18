(ns resuman.components.nav
  (:require [helix.core :refer [defnc]]
            [helix.dom :as d]))

(defnc nav []
  (d/nav {:class '[py-4 shadow bg-white]}
    (d/div {:class '[container flex]}
           (d/h2 {:class '[text-xl w-full block flex-grow]} "Resuman")
           (d/button {:class '[px-4 py-2 leading-none whitespace-no-wrap]} "Log in")
           (d/button {:class '[px-4 py-2 leading-none whitespace-no-wrap]} "Sign up"))))
