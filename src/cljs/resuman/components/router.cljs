(ns resuman.components.router
  (:require [ajax.core :refer [GET POST]]
            [helix.core :refer [defnc <> $]]
            [helix.dom :as d]
            [helix.hooks :as hooks]
            [resuman.state :refer [use-app-state]]
            [resuman.components.home :refer [home]]
            [resuman.components.login :refer [login]]
            [resuman.components.register :refer [register]]
            [resuman.components.profile :refer [profile]]))

(defnc router []
  (let [[state actions] (use-app-state)
        page (:page state)]
    (d/div {:class '[container pt-4 flex h-screen]}
           (case page
             "home" ($ home)
             "login" ($ login)
             "register" ($ register)
             "profile" ($ profile)
             ($ home)
             ))))
