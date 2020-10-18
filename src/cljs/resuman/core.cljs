(ns resuman.core
  (:require [helix.core :refer [defnc $ <> provider]]
            [helix.dom :as d]
            ["react-dom" :as dom]
            [resuman.state :refer [app-state initial-state app-reducer]]
            [resuman.components.nav :refer [nav]]
            [resuman.components.profile-side :refer [profile-side]]
            [resuman.components.project-list :refer [project-list]]
            [helix.hooks :as hooks]))

(defnc app []
  (d/div {:class '[bg-gray-300]}
   ($ nav)
   (d/div {:class '[container pt-4 flex]}
          ($ profile-side)
          ($ project-list))))

(defnc provided-app []
  (provider {:context app-state
             :value (hooks/use-reducer app-reducer initial-state)}
   ($ app)))

(defn start []
  (dom/render ($ provided-app) (js/document.getElementById "app")))

(defn stop []
  (js/console.log "stop"))

(defn ^:export ^dev/after-load init []
  (start))
