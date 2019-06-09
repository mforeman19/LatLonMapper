//File: LatLonApp.scala
//Author: mforeman19
//Date: 6/9/19
//Desc: Takes user input for latitude and longitude and displays map corresponding to location
//Libraries: Uses ScalaJS Google Maps library (https://github.com/coreyauger/scalajs-google-maps)

package tutorial.webapp

import google.maps.Data.Feature
import google.maps.LatLng
import org.scalajs.dom._

import scala.scalajs.js
import js.annotation.JSExport
import org.scalajs.dom
import org.querki.jquery._

import org.scalajs.dom.html

object LatLonApp extends js.JSApp {
  //activates on button click
 def setupUI(): Unit = {
   $("#button_main").click(() => addMap())
 }
  //takes lat and lon values from user input and initializes map
  def addMap(): Unit = {
    //gets lat and lon values from input and converts to float values
    val latValue = document.getElementById("latitude").asInstanceOf[html.Input].value.toFloat
    val lonValue = document.getElementById("longitude").asInstanceOf[html.Input].value.toFloat

    //initializes map with latValue and lonValue
    google.maps.event.addDomListener(window, "load", initialize(latValue, lonValue))
  }
  //takes in lat and lon values and constructs a map based on location
  def initialize(x : Float, y : Float) = js.Function {
    val opts = google.maps.MapOptions(
      center = new LatLng(x, y),
      zoom = 8,
      panControl = false,
      streetViewControl = false,
      mapTypeControl = false)
    val gmap = new google.maps.Map(document.getElementById("map-canvas"), opts)

    val data = new google.maps.Data(google.maps.Data.DataOptions(gmap))

    val latLng = new google.maps.LatLng(x, y)
    val feature = new google.maps.Data.Feature(google.maps.Data.FeatureOptions(
      geometry = latLng,
      id = "Test feature",
      properties = null
    ))

    val feature2 = new google.maps.Data.Feature(google.maps.Data.FeatureOptions(
      geometry = new google.maps.LatLng(x, y),
      id = "Test feature2",
      properties = null
    ))

    data.add(feature)

    data.forEach((feature: google.maps.Data.Feature) => {
      println(feature.getId());
    })

    val style = google.maps.Data.StyleOptions(
      clickable= true,
      cursor= "pointer",
      fillColor= "#79B55B",
      fillOpacity= 1,
      icon = null,
      shape = google.maps.MarkerShape(coords = js.Array(1, 2, 3), shape = "circle" ),
      strokeColor = "#79B55B",
      strokeOpacity = 1,
      strokeWeight= 1,
      title = "string",
      visible = true,
      zIndex=  1
    )


    data.overrideStyle(feature2, style)
    data.setStyle(style)
    println(s"Added new style", style)

    data.add(feature2)

    data.revertStyle(feature)

    def featureToGeoJson(f:Object):Unit = {
      println("featureToGeoJson")
      println(f)
    }

    data.toGeoJson(featureToGeoJson(_:Object))

    val marker = new google.maps.Marker(google.maps.MarkerOptions(
      position = gmap.getCenter(),
      map = gmap,
      title = "Click to zoom"
    ))

    google.maps.event.addListener(gmap, "center_changed", () => {
      // 3 seconds after the center of the map has changed, pan back to the
      // marker.
      window.setTimeout(() => {
        gmap.panTo(marker.getPosition());
      }, 3000)
    })

    ""

  }

  //main method sets up for button click
  def main(): Unit = {
  $(() => setupUI())
  }
}

