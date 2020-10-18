(ns resuman.components.profile-side
  (:require [ajax.core :refer [GET]]
            [helix.hooks :as hooks]
            [helix.core :refer [defnc <> $]]
            [helix.dom :as d]
            [resuman.state :refer [use-app-state]]))

(defnc profile-side []
  (let [[state actions] (use-app-state)
        init-user (:init-user actions)
        user (first (:user state))]
    (hooks/use-effect
     :once
     (GET "http://localhost:4000/api/users/1"
          {:handler init-user}))
    (d/div {:class '["w-1/3" bg-white rounded shadow mr-4 p-6 min-h-screen]}
           (d/img {:src "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcSRPPXXIAlsXERlbpVPX-OWozOFLNQm4f5Mqw&usqp=CAU"
                   :class '[rounded-lg "w-2/3" mb-4 ml-auto mr-auto]})
     (d/p {:class '[text-xl text-center]} (:name user))
     (d/p {:class '[text-sm text-center text-gray-600]} (:email user)))))
