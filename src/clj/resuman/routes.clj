(ns resuman.routes
  (:require [schema.core :as s]
            [resuman.handlers :refer [get-users
                                      get-user-by-id
                                      create-user
                                      update-user-by-id
                                      delete-user-by-id]]))

(def ping-route
  ["/ping" {:get (fn [req]
                   {:status 200
                    :body {:ping "pong"}})}])

(defn dummy []
  {:status 200
   :body {:ping "pong"}})


(def users-route
  ["/users"
   ["/" {:get get-users
         :post {:parameters {:body {:name s/Str
                                    :email s/Str}}
                :handler create-user}}]
   ["/:id" {:parameters {:path {:id s/Int}}
            :get get-user-by-id
            :put {:parameters {:body {:name s/Str
                                      :email s/Str
                                      :linkPicture s/Str}}
                  :handler update-user-by-id}
            :delete delete-user-by-id}]])
