(ns resuman.components.profile
  (:require [ajax.core :refer [GET POST]]
            [helix.core :refer [defnc <> $]]
            [helix.dom :as d]
            [helix.hooks :as hooks]
            [resuman.state :refer [use-app-state]]
            [resuman.components.profile-side :refer [profile-side]]
            [resuman.components.project-list :refer [project-list]]))

(defnc profile []
  (<>
         ($ profile-side)
         ($ project-list)))
