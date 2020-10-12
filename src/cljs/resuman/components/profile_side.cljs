(ns resuman.components.profile-side
  (:require [ajax.core :refer [GET]]
            [helix.hooks :as hooks]
            [helix.core :refer [defnc <> $]]
            [helix.dom :as d]))

(defnc profile-side []
  (let [[state set-state] (hooks/use-state nil)
        user (first state)]
    (hooks/use-effect
     :once
     (GET "http://localhost:4000/api/users/4"
          {:handler (fn [response]
                      (set-state response))}))
    (d/div {:class '["w-1/3" bg-white rounded shadow mr-4 p-6]}
           (d/img {:src "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcSRPPXXIAlsXERlbpVPX-OWozOFLNQm4f5Mqw&usqp=CAU"
                   :class '[rounded-lg]})
     (d/p {:class '[text-xl text-center]} (:name user))
     (d/p {:class '[text-sm text-center]} (:email user)))))
