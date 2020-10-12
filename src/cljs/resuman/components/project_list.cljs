(ns resuman.components.project-list
  (:require [ajax.core :refer [GET]]
            [helix.core :refer [defnc <> $]]
            [helix.dom :as d]
            [helix.hooks :as hooks]))

(defnc project-item [{:keys [project]}]
  (d/div {:class '[bg-white shadow p-4 mb-4 flex]}
         (d/img {:src "https://camo.githubusercontent.com/5656aa6cc7a441294142817cf8fdeccb27ebe768/687474703a2f2f692e696d6775722e636f6d2f464958626737562e706e67"
                 :class '["w-1/3" rounded-lg max-w-xs mr-4]})
         (d/div {:class '[flex-none]}
                (d/h2 {:class '[text-xl]} (:title project))
                (d/p {:class '[text-sm]} (:description project)))))

(defnc project-list []
  (let [[state set-state] (hooks/use-state nil)]
    (hooks/use-effect
     :once
     (GET "http://localhost:4000/api/users/4/projects"
          {:handler (fn [response]
                      (set-state response))}))
    (d/div {:class '[flex-none "w-2/3"]}
     (map-indexed
      (fn [i project]
        ($ project-item {:key i
                         :project project}))
      state))))
