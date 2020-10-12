(ns resuman.core
  (:require [org.httpkit.server :refer [run-server]]
            [reitit.ring :as ring]
            [ring.middleware.cors :refer [wrap-cors]]
            [reitit.ring.middleware.exception :refer [exception-middleware]]
            [reitit.ring.middleware.parameters :refer [parameters-middleware]]
            [reitit.ring.middleware.muuntaja :refer [format-negotiate-middleware
                                                     format-request-middleware
                                                     format-response-middleware]]
            [reitit.ring.coercion :refer [coerce-exceptions-middleware
                                          coerce-request-middleware
                                          coerce-response-middleware]]
            [reitit.coercion.schema]
            [schema.core :as s]
            [muuntaja.core :as m]
            [resuman.routes :refer [ping-route users-route projects-route]]
            [resuman.handlers :refer [create-tables]]))

(defonce server (atom nil))

(def app
  (ring/ring-handler
   (ring/router
    [["/api"
      ping-route
      users-route
      projects-route]]
    {:data {:coercion reitit.coercion.schema/coercion
            :muuntaja m/instance
            :middleware [[wrap-cors
                          :access-control-allow-origin [#"http://localhost:4200"]
                          :access-control-allow-methods [:get :put :post :delete]]
                         parameters-middleware
                         format-negotiate-middleware
                         format-request-middleware
                         exception-middleware
                         format-response-middleware
                         coerce-exceptions-middleware
                         coerce-request-middleware
                         coerce-response-middleware]}})
   (ring/routes
    (ring/redirect-trailing-slash-handler)
    (ring/create-default-handler
     {:not-found (constantly {:status 404
                              :body "Route not found"})}))))

(defn check-db []
  (when-not
    (.exists (clojure.java.io/file "resources/resuman.db"))
    (create-tables)))

(defn -main []
  (check-db)
  (println "Server started")
  (run-server app {:port 4000}))


(defn stop-server []
  (when-not (nil? @server)
    (@server :timeout 100)
    (reset! server nil)))

(defn restart-server []
  (stop-server)
  (-main))
