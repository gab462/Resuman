(ns resuman.core
  (:require [helix.core :refer [defnc $ <> provider]]
            [helix.dom :as d]
            ["react-dom" :as dom]
            [resuman.state :refer [app-state initial-state app-reducer]]
            [resuman.components.nav :refer [nav]]
            [resuman.components.router :refer [router]]
            [helix.hooks :as hooks]))

(defnc app []
  (d/div {:class '[bg-gray-300]}
   ($ nav)
   ($ router)))

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
