(ns scicloj.ggclj.api
  (:require
   [scicloj.ggclj.api.facet :as facet]
   [scicloj.ggclj.api.theme :as theme]
   [tablecloth.api :as tc]))

(defmulti ->dataset type)

(defmethod ->dataset  tech.v3.dataset.impl.dataset.Dataset [data]
  data)

(defmethod ->dataset :default [data]
  (tc/dataset data))

(defn plot
  "Entry point to create a new plot

  Three arities:

  [] - initializes a skeleton plot to be fleshed out as layers are added, useful
  when layers use different datasets

  [data] - useful when all layers use the same data but have different aesthetics

  [data mapping] - recommended if all layers use the same data and same aesthetics,
  also useful when a single layer uses different data

  **Parameters**

  * data - Default dataset to use for plot. Expects a `tech.ml.dataset` (note tablebloth
  datasets are `tech.ml.dataset`s). If not already a `tech.ml.dataset`, `ggplot` will attempt
  to make it one by passing the data to [`tablecloth.api/dataset`](https://scicloj.github.io/tablecloth/index.html#dataset).
  If not specified, `data` must be supplied in each individual layer.
  * mapping - Default list of aesthetic mappings to use for a plot. If not specified, must
  be supplied in each individual layer."
  ([]
   (plot {} {}))
  ([data]
   (plot data {}))
  ([data mapping]
   (let [ds (->dataset data)]
     {:data ds
      :mapping mapping
      :layers []
      :scales {}
      :coords :cartesian #_coords/default-coords
      ;; :guides {}
      :facets nil
      :theme theme/default-theme
      #_#_:layout {}
      #_#_:labels (aes/make-labels mapping)})))

(defn layer [plot-spec layer-spec]
  (update plot-spec :layers conj layer-spec))
