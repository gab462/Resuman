(ns resuman.core
  (:require [ajax.core :refer [GET]]
            [helix.core :refer [defnc $ <>]]
            [helix.hooks :as hooks]
            [helix.dom :as d]
            ["react-dom" :as dom]
            [resuman.components.nav :refer [nav]]
            [resuman.components.profile-side :refer [profile-side]]))

(defnc app []
  (let [[state set-state] (hooks/use-state nil)]
    (hooks/use-effect
      :once
      (GET "http://localhost:4000/api/users"
        {:handler (fn [response]
                    (set-state response))}))
    (js/console.log state)
    (<>
      ($ nav)
      (d/div {:class '[container pt-4]}
        ($ profile-side {:users state})))))

(defn ^:export ^dev/after-load init []
  (dom/render ($ app) (js/document.getElementById "app")))
