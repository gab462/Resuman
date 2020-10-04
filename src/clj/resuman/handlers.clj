(ns resuman.handlers
  (:require [resuman.db :as db]))


(defn get-users [_]
  {:status 200
   :body (db/get-users db/config)})

(defn create-user
  [{:keys [parameters]}]
  (let [data (:body parameters)]
    (db/insert-user db/config data)
    {:status 201
     :body "POST successful"}))

(defn get-user-by-id
  [{:keys [parameters]}]
  (let [id (:path parameters)]
    {:status 201
     :body (db/get-user-by-id db/config id)}))

(defn update-user-by-id
  [{:keys [parameters]}]
  (let [id (get-in parameters [:path :id])
        body (:body parameters)
        data (assoc body :id id)]
    (db/update-user-by-id db/config data)
    {:status 200
     :body (db/get-user-by-id db/config id)})) ;;FIXME returns exception

(defn delete-user-by-id
  [{:keys [parameters]}]
  (let [id (:path parameters)
        before-deleted (db/get-user-by-id db/config id)]
    (db/delete-user-by-id db/config id)
    {:status 200
     :body {:deleted true
            :user before-deleted}}))
