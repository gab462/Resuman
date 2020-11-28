(ns resuman.components.home
  (:require [ajax.core :refer [GET POST]]
            [helix.core :refer [defnc <> $]]
            [helix.dom :as d]
            [helix.hooks :as hooks]
            [resuman.state :refer [use-app-state]]))

(defnc home []
  (d/div {:class '[w-full bg-white p-10]}
         (d/h1 {:class '[text-xl]} "Resuman")
         (d/h2 {:class '[text-md]} "Manage your portfolio")
         (d/h3 {:class '[text-xs]} "Project by Gustavo Araujo Borges RA180995 @ Faculdade de Engenharia de Sorocaba FACENS, 2020")))
