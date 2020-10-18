(ns resuman.components.project-list
  (:require [ajax.core :refer [GET]]
            [helix.core :refer [defnc <> $]]
            [helix.dom :as d]
            [helix.hooks :as hooks]
            [resuman.components.project-item :refer [project-item]]
            [resuman.state :refer [use-app-state]]))

(defnc project-list []
  (let [[new set-new] (hooks/use-state false)
        [state actions] (use-app-state)
        init-projects (:init-projects actions)
        projects (:projects state)]
    (hooks/use-effect
     :once
     (GET "http://localhost:4000/api/users/1/projects"
          {:handler init-projects}))
    (d/div {:class '[flex-none "w-2/3"]}
     (map-indexed
      (fn [i project]
        ($ project-item {:key i
                         :project project}))
      projects)
     (if (not new)
       (d/button {:class '[bg-blue-500 text-white text-xl font-bold py-2 px-4 rounded w-full]
                  :on-click #(set-new (not new))}
                 "+ Add Project")
       ; ($ new-project)
       ))))
