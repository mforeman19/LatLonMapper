package tutorial.webapp

//import org.scalajs.dom
//import dom.document
//import scala.scalajs.js.annotation.JSExportTopLevel
import org.querki.jquery._

object TutorialApp {
  def main(args: Array[String]): Unit = {
    $(() => setupUI())
  }

  def setupUI(): Unit = {
    $("body").append("<p>Hello World</p>")
    $("#click-me-button").click(() => addClickedMessage())
  }

  def addClickedMessage(): Unit = {
    $("body").append("<p>You clicked the button!</p>")
  }
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

