(ns resuman.components.project-item
  (:require [ajax.core :refer [DELETE PUT]]
            [helix.core :refer [defnc <>]]
            [helix.dom :as d]
            [helix.hooks :as hooks]
            [resuman.state :refer [use-app-state]]))

(defnc project-item [{:keys [project]}]
  (let [[edit set-edit] (hooks/use-state false)
        [_ actions] (use-app-state)
        remove-project (:remove-project actions)
        update-project (:update-project actions)
        [state set-state] (hooks/use-state project)]
    (d/div {:class '[bg-white shadow p-4 mb-4]}
           (d/div {:class '[flex]}
                  (if (not edit)
                    (<>
                      (d/img {:src "https://camo.githubusercontent.com/5656aa6cc7a441294142817cf8fdeccb27ebe768/687474703a2f2f692e696d6775722e636f6d2f464958626737562e706e67"
                              :class '["w-1/3" rounded-lg mr-4]})
                      (d/div {:class '[flex-none "w-2/3"]}
                             (d/h2 {:class '[text-xl]} (:title project))
                             (d/p {:class '[text-sm]} (:description project))))
                    (<>
                      (d/img {:src "https://camo.githubusercontent.com/5656aa6cc7a441294142817cf8fdeccb27ebe768/687474703a2f2f692e696d6775722e636f6d2f464958626737562e706e67"
                              :class '["w-1/3" rounded-lg mr-4]})
                      (d/form {:class '[flex-none "w-2/3"]}
                              (d/input {:class '[shadow border border-gray-300 rounded w-full text-xl]
                                        :type "email"
                                        :id "title"
                                        :value (:title state)
                                        :placeholder "Title"
                                        :on-change #(set-state
                                                      (assoc state
                                                             :title
                                                             (.. %
                                                                 -target
                                                                 -value)))})
                              (d/input {:class '[shadow border border-gray-300 rounded w-full text-sm]
                                        :type "email"
                                        :value (:description state)
                                        :placeholder "Description"
                                        :on-change #(set-state
                                                      (assoc state
                                                             :description
                                                             (.. %
                                                                 -target
                                                                 -value)))})))))
           (d/div {:class '[flex flex-row-reverse]}
                  (d/button {:class '[bg-red-500 text-white font-bold py-2 px-4 rounded]
                             :on-click #(DELETE (str "http://localhost:4000/api/projects/" (:rowid project))
                                                {:handler (fn [_]
                                                            (remove-project project))})}
                            "Delete")
                  (d/button {:class '[bg-blue-500 text-white font-bold py-2 px-4 mr-4 rounded]
                             :on-click #(if edit
                                           (PUT (str "http://localhost:4000/api/projects/" (:rowid project))
                                                {:params (select-keys state [:title :description :user :info :thumbnail :source])
                                                 :format :json
                                                 :handler(fn [response]
                                                           (set-edit (not edit))
                                                           (update-project (first (:project response))))})
                                           (set-edit (not edit)))}
                            (if edit "Save" "Edit"))))))
