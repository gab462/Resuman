(ns resuman.components.nav
  (:require [helix.core :refer [defnc]]
            [helix.dom :as d]
            [helix.hooks :as hooks]
            [resuman.state :refer [use-app-state]]))

(defnc nav []
  (let [[state actions] (use-app-state)
        set-page (:set-page actions)]
    (d/nav {:class '[py-4 shadow bg-white]}
           (d/div {:class '[container flex]}
                  (d/button {:class '[text-xl text-left block flex-grow]
                         :on-click #(set-page "home")} "Resuman")
                  (d/button {:class '[px-4 py-2 leading-none whitespace-no-wrap]
                             :on-click #(set-page "profile")} "Profile")
                  (d/button {:class '[px-4 py-2 leading-none whitespace-no-wrap]
                             :on-click #(set-page "login")} "Log in")
                  (d/button {:class '[px-4 py-2 leading-none whitespace-no-wrap]
                             :on-click #(set-page "register")} "Sign up")))))
