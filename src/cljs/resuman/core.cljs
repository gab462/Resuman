(ns resuman.core
  (:require [helix.core :refer [defnc $ <>]]
            [helix.dom :as d]
            ["react-dom" :as dom]
            [resuman.components.nav :refer [nav]]
            [resuman.components.profile-side :refer [profile-side]]
            [resuman.components.project-list :refer [project-list]]))

(defnc app []
  (d/div {:class '[bg-gray-300]}
   ($ nav)
   (d/div {:class '[container pt-4 flex]}
          ($ profile-side)
          ($ project-list))))

(defn start []
  (dom/render ($ app) (js/document.getElementById "app")))

(defn stop []
  (js/console.log "stop"))

(defn ^:export ^dev/after-load init []
  (start))
