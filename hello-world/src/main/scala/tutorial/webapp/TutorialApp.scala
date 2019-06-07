package tutorial.webapp

//import org.scalajs.dom
//import dom.document
//import scala.scalajs.js.annotation.JSExportTopLevel
import google.maps.Data.Feature
import google.maps.LatLng
import org.scalajs.dom._

import scala.scalajs.js
import js.annotation.JSExport
import org.scalajs.dom
import org.querki.jquery._

import org.scalajs.dom.html

object TutorialApp extends js.JSApp {
   // $(() => setupUI())

   //lazy val lat = $("#latitude").value
   //lazy val lon = $("#longitude").value

 def setupUI(): Unit = {
   $("#button_main").click(() => addMap())
 }

  def addMap(): Unit = {

    val latValue = document.getElementById("latitude").asInstanceOf[html.Input].value.toFloat
    val lonValue = document.getElementById("longitude").asInstanceOf[html.Input].value.toFloat

    println(latValue + lonValue)

    google.maps.event.addDomListener(window, "load", initialize(latValue, lonValue))
    $("body").append("Hey")
  }

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

    val contentString = """<div id="content">
    <div id="siteNotice">
    </div>
    <h1 id="firstHeading" class="firstHeading">Uluru</h1>
    <div id="bodyContent">
    <p><b>Uluru</b>, also referred to as <b>Ayers Rock</b>, is a large sandstone rock formation in the southern part of the '+
    Northern Territory, central Australia. It lies 335&#160;km (208&#160;mi)
    south west of the nearest large town, Alice Springs; 450&#160;km
    (280&#160;mi) by road. Kata Tjuta and Uluru are the two major
    features of the Uluru - Kata Tjuta National Park. Uluru is
    sacred to the Pitjantjatjara and Yankunytjatjara, the
    Aboriginal people of the area. It has many springs, waterholes,
    rock caves and ancient paintings. Uluru is listed as a World
    Heritage Site.</p>
    <p>Attribution: Uluru, <a href="https://en.wikipedia.org/w/index.php?title=Uluru&oldid=297882194">
    https://en.wikipedia.org/w/index.php?title=Uluru</a>
    (last visited June 22, 2009).</p>
    </div>
    </div>"""

    val infowindow = new google.maps.InfoWindow(google.maps.InfoWindowOptions(
      content=contentString
    ))

    google.maps.event.addListener(marker, "click", () => {
      println("Marker click !")
      infowindow.open(gmap,marker)
    })


    ""

  }


  def main(): Unit = {
  $(() => setupUI())


    println("About to init map...")

  }

/*
def setupUI(): Unit = {
  $("body").append("<p>Hello World</p>")
  $("#click-me-button").click(() => addClickedMessage())
}

def addClickedMessage(): Unit = {
  $("body").append("<p>You clicked the button!</p>")
}
*/
/*
@JSExportTopLevel("addClickedMessage")
def addClickedMessage(): Unit = {
 // appendPar(document.body, "You clicked the button!")
  $("body").append("<p>You clicked the button!</p>")
}

/*
def appendPar(targetNode: dom.Node, text: String): Unit = {
  val parNode = document.createElement("p")
  val textNode = document.createTextNode(text)
  parNode.appendChild(textNode)
  targetNode.appendChild(parNode)
}*/

def main(args: Array[String]): Unit = {
  $("body").append("<p>Hello World</p>")
}
*/
}

