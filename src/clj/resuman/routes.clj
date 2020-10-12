(ns resuman.routes
  (:require [schema.core :as s]
            [resuman.handlers :refer [get-users
                                      get-user-by-id
                                      create-user
                                      update-user-by-id
                                      delete-user-by-id
                                      get-projects
                                      get-projects-by-user
                                      get-project-by-id
                                      create-project
                                      update-project-by-id
                                      delete-project-by-id]]))

(def ping-route
  ["/ping" {:get (fn [req]
                   {:status 200
                    :body {:ping "pong"}})}])

(def users-route
  ["/users"
   ["" {:get get-users
        :post {:parameters {:body {:name s/Str
                                   :email s/Str}}
               :handler create-user}}]
   ["/:id"
    ["" {:parameters {:path {:id s/Int}}
         :get get-user-by-id
         :put {:parameters {:body {:name s/Str
                                   :email s/Str
                                   :linkPicture s/Str}}
               :handler update-user-by-id}
         :delete delete-user-by-id}]
    ["/projects" {:parameters {:path {:id s/Int}}
                  :get get-projects-by-user}]]])

(def projects-route
  ["/projects"
   ["" {:get get-projects
        :post {:parameters {:body {:title s/Str
                                   :description s/Str
                                   :source s/Str
                                   :info s/Str
                                   :thumbnail s/Str
                                   :user s/Int}}
               :handler create-project}}]
   ["/:id" {:parameters {:path {:id s/Int}}
            :get get-project-by-id
            :put {:parameters {:body {:title s/Str
                                      :description s/Str
                                      :source s/Str
                                      :info s/Str
                                      :thumbnail s/Str
                                      :user s/Int}}
                  :handler update-project-by-id}
            :delete delete-project-by-id}]])
