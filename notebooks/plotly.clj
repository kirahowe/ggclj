(ns scicloj.ggclj.plotly
  (:require [scicloj.kindly.v4.kind :as kind]
            [charred.api :as charred]
            [scicloj.ggclj.geom.point :as point]
            [scicloj.ggclj.data :refer [mtcars]]
            [scicloj.ggclj.api :as gg]))

(->> ["notebooks/lib/crosstalk-1.2.1/css/crosstalk.min.css"
      "notebooks/lib/plotly-htmlwidgets-css-2.11.1/plotly-htmlwidgets.css"]
     (map (fn [path]
            (kind/hiccup
             [:link {:href path
                     :rel "stylesheet"}])))
     kind/fragment)


(->> ["notebooks/lib/htmlwidgets-1.6.2/htmlwidgets.js"
      "notebooks/lib/plotly-binding-4.10.4/plotly.js"
      "notebooks/lib/typedarray-0.1/typedarray.min.js"
      "notebooks/lib/jquery-3.5.1/jquery.min.js"
      "notebooks/lib/crosstalk-1.2.1/js/crosstalk.min.js"
      "notebooks/lib/plotly-main-2.11.1/plotly-latest.min.js"]
     (map (fn [path]
            (kind/hiccup
             [:script {:src path}])))
     kind/fragment)

(def *id (atom 0))

(defn next-id! []
  (swap! *id inc))

(defn plotly [spec]
  (let [id (str "htmlwidget-1" (next-id!))]
    (kind/hiccup
     [:div {:class "plotly html-widget html-fill-item-overflow-hidden html-fill-item"
            :id id
            :style "width:100%;height:400px;"}
      [:script {:type "application/htmlwidget-sizing"
                :data-for id}
       (charred/write-json-str {:viewer {:width "100%"
                                         :height 400
                                         :padding "0"
                                         :fille true}
                                :browser {:width "100%"
                                          :height 400
                                          :padding "0"
                                          :fille true}})]
      [:script {:type "application/json"
                :data-for id}
       (charred/write-json-str spec)]])))


(defn ->spec [ggplot]
  ;; Ignoring the plot for now, using a hardcoded one.
  {:x
   {:visdat {:1f2fea5b54d146 ["function (y) " "x"]},
    :config
    {:doubleClick "reset",
     :modeBarButtonsToAdd ["hoverclosest" "hovercompare"],
     :showSendToCloud false},
    :layout
    {:plot_bgcolor "rgba(235,235,235,1)",
     :paper_bgcolor "rgba(255,255,255,1)",
     :legend
     {:bgcolor "rgba(255,255,255,1)",
      :bordercolor "transparent",
      :borderwidth 1.88976377952756,
      :font {:color "rgba(0,0,0,1)", :family "", :size 11.689497716895}},
     :xaxis
     {:linewidth 0,
      :nticks nil,
      :linecolor nil,
      :ticklen 3.65296803652968,
      :tickcolor "rgba(51,51,51,1)",
      :tickmode "array",
      :gridcolor "rgba(255,255,255,1)",
      :automargin true,
      :type "linear",
      :tickvals [2 3 4 5],
      :zeroline false,
      :title
      {:text "wt",
       :font {:color "rgba(0,0,0,1)", :family "", :size 14.6118721461187}},
      :tickfont {:color "rgba(77,77,77,1)", :family "", :size 11.689497716895},
      :autorange false,
      :showticklabels true,
      :showline false,
      :showgrid true,
      :ticktext ["2" "3" "4" "5"],
      :ticks "outside",
      :gridwidth 0.66417600664176,
      :anchor "y",
      :domain [0 1],
      :hoverformat ".2f",
      :tickangle 0,
      :tickwidth 0.66417600664176,
      :categoryarray ["2" "3" "4" "5"],
      :categoryorder "array",
      :range [1.31745 5.61955]},
     :font {:color "rgba(0,0,0,1)", :family "", :size 14.6118721461187},
     :showlegend false,
     :barmode "relative",
     :yaxis
     {:linewidth 0,
      :nticks nil,
      :linecolor nil,
      :ticklen 3.65296803652968,
      :tickcolor "rgba(51,51,51,1)",
      :tickmode "array",
      :gridcolor "rgba(255,255,255,1)",
      :automargin true,
      :type "linear",
      :tickvals [10 15 20 25 30 35],
      :zeroline false,
      :title
      {:text "mpg",
       :font {:color "rgba(0,0,0,1)", :family "", :size 14.6118721461187}},
      :tickfont {:color "rgba(77,77,77,1)", :family "", :size 11.689497716895},
      :autorange false,
      :showticklabels true,
      :showline false,
      :showgrid true,
      :ticktext ["10" "15" "20" "25" "30" "35"],
      :ticks "outside",
      :gridwidth 0.66417600664176,
      :anchor "x",
      :domain [0 1],
      :hoverformat ".2f",
      :tickangle 0,
      :tickwidth 0.66417600664176,
      :categoryarray ["10" "15" "20" "25" "30" "35"],
      :categoryorder "array",
      :range [9.225 35.075]},
     :hovermode "closest",
     :margin
     {:t 25.7412480974125,
      :r 7.30593607305936,
      :b 39.6955859969559,
      :l 37.2602739726027},
     :shapes
     [{:yref "paper",
       :fillcolor nil,
       :xref "paper",
       :y1 1,
       :type "rect",
       :line {:color nil, :width 0, :linetype []},
       :y0 0,
       :x1 1,
       :x0 0}]},
    :highlight
    {:on "plotly_click",
     :persistent false,
     :dynamic false,
     :selectize false,
     :opacityDim 0.2,
     :selected {:opacity 1},
     :debounce 0},
    :base_url "https://plot.ly",
    :cur_data "1f2fea5b54d146",
    :source "A",
    :shinyEvents
    ["plotly_hover"
     "plotly_click"
     "plotly_selected"
     "plotly_relayout"
     "plotly_brushed"
     "plotly_brushing"
     "plotly_clickannotation"
     "plotly_doubleclick"
     "plotly_deselect"
     "plotly_afterplot"
     "plotly_sunburstclick"],
    :attrs {:1f2fea5b54d146 {:x {}, :y {}, :type "scatter"}},
    :data
    [{:y
      [21
       21
       22.8
       21.4
       18.7
       18.1
       14.3
       24.4
       22.8
       19.2
       17.8
       16.4
       17.3
       15.2
       10.4
       10.4
       14.7
       32.4
       30.4
       33.9
       21.5
       15.5
       15.2
       13.3
       19.2
       27.3
       26
       30.4
       15.8
       19.7
       15
       21.4],
      :hoveron "points",
      :frame nil,
      :hoverinfo "text",
      :marker
      {:autocolorscale false,
       :color "rgba(0,0,0,1)",
       :opacity 1,
       :size 5.66929133858268,
       :symbol "circle",
       :line {:width 1.88976377952756, :color "rgba(0,0,0,1)"}},
      :mode "markers",
      :type "scatter",
      :xaxis "x",
      :showlegend false,
      :yaxis "y",
      :x
      [2.62
       2.875
       2.32
       3.215
       3.44
       3.46
       3.57
       3.19
       3.15
       3.44
       3.44
       4.07
       3.73
       3.78
       5.25
       5.424
       5.345
       2.2
       1.615
       1.835
       2.465
       3.52
       3.435
       3.84
       3.845
       1.935
       2.14
       1.513
       3.17
       2.77
       3.57
       2.78],
      :text
      ["wt: 2.620<br />mpg: 21.0"
       "wt: 2.875<br />mpg: 21.0"
       "wt: 2.320<br />mpg: 22.8"
       "wt: 3.215<br />mpg: 21.4"
       "wt: 3.440<br />mpg: 18.7"
       "wt: 3.460<br />mpg: 18.1"
       "wt: 3.570<br />mpg: 14.3"
       "wt: 3.190<br />mpg: 24.4"
       "wt: 3.150<br />mpg: 22.8"
       "wt: 3.440<br />mpg: 19.2"
       "wt: 3.440<br />mpg: 17.8"
       "wt: 4.070<br />mpg: 16.4"
       "wt: 3.730<br />mpg: 17.3"
       "wt: 3.780<br />mpg: 15.2"
       "wt: 5.250<br />mpg: 10.4"
       "wt: 5.424<br />mpg: 10.4"
       "wt: 5.345<br />mpg: 14.7"
       "wt: 2.200<br />mpg: 32.4"
       "wt: 1.615<br />mpg: 30.4"
       "wt: 1.835<br />mpg: 33.9"
       "wt: 2.465<br />mpg: 21.5"
       "wt: 3.520<br />mpg: 15.5"
       "wt: 3.435<br />mpg: 15.2"
       "wt: 3.840<br />mpg: 13.3"
       "wt: 3.845<br />mpg: 19.2"
       "wt: 1.935<br />mpg: 27.3"
       "wt: 2.140<br />mpg: 26.0"
       "wt: 1.513<br />mpg: 30.4"
       "wt: 3.170<br />mpg: 15.8"
       "wt: 2.770<br />mpg: 19.7"
       "wt: 3.570<br />mpg: 15.0"
       "wt: 2.780<br />mpg: 21.4"]}]},
   :evals [],
   :jsHooks []})

(defn ggplotly [ggplot]
  (-> ggplot
      gg/ggplot-build
      ->spec
      plotly))

(-> mtcars
    (gg/ggplot (gg/aes "wt" "mpg"))
    point/geom-point
    ggplotly)
