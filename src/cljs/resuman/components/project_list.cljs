(ns resuman.components.project-list
  (:require [ajax.core :refer [GET POST]]
            [helix.core :refer [defnc <> $]]
            [helix.dom :as d]
            [helix.hooks :as hooks]
            [resuman.components.project-item :refer [project-item]]
            [resuman.state :refer [use-app-state]]))

(defnc project-list []
  (let [[new set-new] (hooks/use-state false)
        [state actions] (use-app-state)
        init-projects (:init-projects actions)
        [nproject set-nproject] (hooks/use-state {:user 1
                                                  :source "#"
                                                  :thumbnail ""
                                                  :info ""})
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
       (d/div {:class '[bg-white shadow p-4 mb-4]}
              (d/div {:class '[flex]}
                     (d/img {:src "https://camo.githubusercontent.com/5656aa6cc7a441294142817cf8fdeccb27ebe768/687474703a2f2f692e696d6775722e636f6d2f464958626737562e706e67"
                             :class '["w-1/3" rounded-lg mr-4]})
                     (d/form {:class '[flex-none "w-2/3"]}
                             (d/input {:class '[shadow border border-gray-300 rounded w-full text-xl]
                                       :type "text"
                                       :id "title"
                                       :placeholder "Title"
                                       :on-change #(set-nproject
                                                     (assoc nproject
                                                            :title
                                                            (.. %
                                                                -target
                                                                -value)))})
                             (d/input {:class '[shadow border border-gray-300 rounded w-full text-sm]
                                       :type "text"
                                       :placeholder "Description"
                                       :on-change #(set-nproject
                                                     (assoc nproject
                                                            :description
                                                            (.. %
                                                                -target
                                                                -value)))})))
              (d/div {:class '[flex flex-row-reverse]}
                     (d/button {:class '[bg-blue-500 text-white font-bold py-2 px-4 mr-4 rounded]
                                :on-click
                                ; (js/console.log (select-keys nproject [:user :title :description]))
                                #(POST "http://localhost:4000/api/projects"
                                                {:params (select-keys nproject [:title :description :user :info :source :thumbnail])
                                                 :format :json
                                                 :handler (fn [response]
                                                            (set-new (not new)))})
                                }
                               "Add")))))))
