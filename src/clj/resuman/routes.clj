(ns resuman.routes
  (:require [libraries]))

(def ping-route
  ["/ping" {:get (fn [req]
                   {:status 200
                    :body {:ping "pong"}})}])

(def users-route
  ["/users" {:get (fn [req]
                    {:status 200
                     :body (db/get-users db/config)})}])
