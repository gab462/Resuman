(ns resuman.components.register
  (:require [ajax.core :refer [GET POST]]
            [helix.core :refer [defnc <> $]]
            [helix.dom :as d]
            [helix.hooks :as hooks]
            [resuman.state :refer [use-app-state]]))

(defnc register []
  (d/p "Register Works!"))
