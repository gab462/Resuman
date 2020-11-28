(ns resuman.components.login
  (:require [ajax.core :refer [GET POST]]
            [helix.core :refer [defnc $]]
            [helix.dom :as d]
            [helix.hooks :as hooks]
            [resuman.state :refer [use-app-state]]))

(defnc login []
  (let [[state actions] (use-app-state)
        set-loggedin (:set-loggedin actions)
        set-suser (:set-user actions)
        set-page (:set-page actions)
        [user set-user] (hooks/use-state {:username ""
                                            :password ""})]
    (d/div {:class '["w-2/3" m-auto bg-white rounded shadow p-6]}
           (d/p {:class '[text-xl text-left]} "Login")
           (d/div {:class '[flex-none "w-2/3" m-auto]}
                   (d/input {:class '[shadow border border-gray-300 rounded w-full text-xl mt-5 pl-2 h-10]
                             :type "text"
                             :id "username"
                             :placeholder "Username"
                             :on-change #(set-user
                                           (assoc user
                                                  :username
                                                  (.. %
                                                      -target
                                                      -value)))})
                   (d/input {:class '[shadow border border-gray-300 rounded w-full text-xl mt-5 pl-2 h-10]
                             :type "password"
                             :id "password"
                             :placeholder "Password"
                             :on-change #(set-user
                                           (assoc user
                                                  :password
                                                  (.. %
                                                      -target
                                                      -value)))})
           (d/div {:class '[flex flex-row-reverse]}
                  (d/button {:class '[bg-blue-500 text-white font-bold py-2 px-4 mr-4 mt-5 rounded]
                             :on-click #(POST "http://localhost:4000/api/login"
                                              {:params user
                                               :format :json
                                               :handler (fn [response]
                                                          (GET (str "http://localhost:4000/api/users/" response)
                                                               {:handler (fn [nuser]
                                                                           (set-suser (first nuser)))})
                                                          (set-loggedin true)
                                                          (set-page "home"))})}
                            "Log In"))))))
