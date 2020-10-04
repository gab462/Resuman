(ns resuman.db
  (:require [hugsql.core :as hugsql]))

(def config
  {:classname   "org.sqlite.JDBC"
   :subprotocol "sqlite"
   :subname     "resources/resuman.db"}) ;; FIXME wrong path?

(hugsql/def-db-fns "resuman.sql") ;; FIXME wrong path?
