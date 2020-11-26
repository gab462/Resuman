(ns resuman.handlers
  (:require [resuman.db :as db]
            [buddy.hashers :refer [encrypt check]]))

(defn login
  [{:keys [parameters]}]
  (let [data (:body parameters)
        username (:username data)
        password (:password data)
        user (first (db/get-user-by-username db/config data))]
    (if (and user (check password (:password user)))
      {:status 200
       :body (str (:rowid user))}
      {:status 404})))

(defn get-users [_]
  {:status 200
   :body (db/get-users db/config)})

(defn create-user
  [{:keys [parameters]}]
  (let [data (:body parameters)]
    {:status 201
     :body (db/insert-user db/config (update data :password encrypt))}))

(defn get-user-by-id
  [{:keys [parameters]}]
  (let [id (:path parameters)]
    {:status 201
     :body (db/get-user-by-id db/config id)}))

(defn update-user-by-id
  [{:keys [parameters]}]
  (let [id (get-in parameters [:path :id])
        body (:body parameters)
        data (assoc body :id id)
        updated-count (db/update-user-by-id db/config data)]
    (if (= 1 updated-count)
      {:status 200
       :body {:updated true
              :user (db/get-user-by-id db/config (:path parameters))}}
      {:status 404
       :body {:error "Unable to update user"}})))

(defn delete-user-by-id
  [{:keys [parameters]}]
  (let [id (:path parameters)
        before-deleted (db/get-user-by-id db/config id)
        deleted-count (db/delete-user-by-id db/config id)]
    (if (= 1 deleted-count)
      {:status 200
       :body {:deleted true
              :user before-deleted}}
      {:status 404
       :body {:error "Unable to delete user"}})))

(defn get-projects [_]
  {:status 200
   :body (db/get-projects db/config)})

(defn create-project
  [{:keys [parameters]}]
  (let [data (:body parameters)]
    {:status 201
     :body (db/insert-project db/config data)}))

(defn get-projects-by-user
  [{:keys [parameters]}]
  (let [id (:path parameters)]
    {:status 201
     :body (db/get-projects-by-user db/config id)}))

(defn get-project-by-id
  [{:keys [parameters]}]
  (let [id (:path parameters)]
    {:status 201
     :body (db/get-project-by-id db/config id)}))

(defn update-project-by-id
  [{:keys [parameters]}]
  (let [id (get-in parameters [:path :id])
        body (:body parameters)
        data (assoc body :id id)
        updated-count (db/update-project-by-id db/config data)]
    (if (= 1 updated-count)
      {:status 200
       :body {:updated true
              :project (db/get-project-by-id db/config (:path parameters))}}
      {:status 404
       :body {:error "Unable to update project"}})))

(defn delete-project-by-id
  [{:keys [parameters]}]
  (let [id (:path parameters)
        before-deleted (db/get-project-by-id db/config id)
        deleted-count (db/delete-project-by-id db/config id)]
    (if (= 1 deleted-count)
      {:status 200
       :body {:deleted true
              :project before-deleted}}
      {:status 404
       :body {:error "Unable to delete project"}})))

(defn create-tables []
  (db/create-users-table db/config)
  (db/create-projects-table db/config))
