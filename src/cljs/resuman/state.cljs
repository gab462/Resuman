(ns resuman.state
  (:require [helix.core :refer [create-context]]
            [helix.hooks :as hooks]))

(def initial-state {:user nil
                    :projects []})

(def app-state (create-context nil))

(defmulti app-reducer
  (fn [_ action]
    (first action)))

(defmethod app-reducer
  ::set-projects [state [_ payload]]
  (assoc state :projects payload))

(defmethod app-reducer
  ::add-project [state [_ payload]]
  (assoc state :projects (conj (:projects state) (first payload))))

(defmethod app-reducer
  ::remove-project [state [_ payload]]
  (let [not-matching #(not (= (:rowid %) (:rowid payload)))]
    (assoc state :projects (filter not-matching (:projects state)))))

(defmethod app-reducer
  ::update-project [state [_ payload]]
  (let [update-project #(if (= (:rowid %) (:rowid payload))
                          payload
                          %)]
    (assoc state :projects (map update-project (:projects state)))))

(defmethod app-reducer
  ::set-user [state [_ payload]]
  (assoc state :user payload))

(defn use-app-state []
  (let [[state dispatch] (hooks/use-context app-state)]
    [state {:init-projects (fn [response]
                             (dispatch [::set-projects response]))
            :init-user (fn [response]
                         (dispatch [::set-user response]))
            :add-project #(dispatch [::add-project %])
            :remove-project #(dispatch [::remove-project %])
            :update-project #(dispatch [::update-project %])}]))
