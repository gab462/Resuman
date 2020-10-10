(ns resuman.components.profile-side
  (:require [helix.core :refer [defnc <> $]]
            [helix.dom :as d]))

(defnc profile-side-item [{:keys [user]}]
  (d/li
    (d/div
      (d/p (str (:name user) " " (:email user))))))

(defnc profile-side [{:keys [users]}]
  (<>
    (d/ul
      (map-indexed
        (fn [i user]
          ($ profile-side-item {:key i
                                :user user}))
        users))))
