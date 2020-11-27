(ns resuman.components.register
  (:require [ajax.core :refer [GET POST]]
            [helix.core :refer [defnc <> $]]
            [helix.dom :as d]
            [helix.hooks :as hooks]
            [resuman.state :refer [use-app-state]]))

(defnc register []
  (let [[state actions] (use-app-state)
        set-user (:set-user actions)
        set-loggedin (:set-loggedin actions)
        set-page (:set-page actions)
        [luser set-luser] (hooks/use-state {:name ""
                                            :username ""
                                            :password ""
                                            :email ""})]
    (d/div {:class '["w-2/3" m-auto bg-white rounded shadow p-6]}
           (d/p {:class '[text-xl text-left]} "Register")
           (d/div {:class '[flex-none "w-2/3" m-auto]}
                   (d/input {:class '[shadow border border-gray-300 rounded w-full text-xl mt-5 pl-2 h-10]
                             :type "text"
                             :id "username"
                             :placeholder "Username"
                             :on-change #(set-luser
                                           (assoc luser
                                                  :username
                                                  (.. %
                                                      -target
                                                      -value)))})
                   (d/input {:class '[shadow border border-gray-300 rounded w-full text-xl mt-5 pl-2 h-10]
                             :type "text"
                             :id "name"
                             :placeholder "Name"
                             :on-change #(set-luser
                                           (assoc luser
                                                  :name
                                                  (.. %
                                                      -target
                                                      -value)))})
                   (d/input {:class '[shadow border border-gray-300 rounded w-full text-xl mt-5 pl-2 h-10]
                             :type "email"
                             :id "email"
                             :placeholder "name@email.com"
                             :on-change #(set-luser
                                           (assoc luser
                                                  :email
                                                  (.. %
                                                      -target
                                                      -value)))})
                   (d/input {:class '[shadow border border-gray-300 rounded w-full text-xl mt-5 pl-2 h-10]
                             :type "password"
                             :id "password"
                             :placeholder "Password"
                             :on-change #(set-luser
                                           (assoc luser
                                                  :password
                                                  (.. %
                                                      -target
                                                      -value)))})
           (d/div {:class '[flex flex-row-reverse]}
                  (d/button {:class '[bg-blue-500 text-white font-bold py-2 px-4 mr-4 mt-5 rounded]
                             :on-click
                             #(POST "http://localhost:4000/api/users"
                                    {:params luser
                                     :format :json
                                     :handler (fn [response]
                                                (GET (str "http://localhost:4000/api/users/" (get (first response) 1))
                                                      {:handler (fn [nuser]
                                                                  (set-user (first nuser))
                                                                  (set-loggedin true)
                                                                  (set-page "profile"))}))})}
                            "Register"
                            ))))))
