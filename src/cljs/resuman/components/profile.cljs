(ns resuman.components.profile
  (:require [helix.core :refer [defnc <> $]]
            [resuman.components.profile-side :refer [profile-side]]
            [resuman.components.project-list :refer [project-list]]))

(defnc profile []
  (<>
         ($ profile-side)
         ($ project-list)))
