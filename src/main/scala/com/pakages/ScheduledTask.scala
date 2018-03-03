package com.pakages
import scala.collection.mutable
import scala.xml.XML
import java.util.TimerTask

case class ScheduledTask() extends TimerTask{
  override def run(): Unit = {
    val updatedXML = XML.loadFile("D:/grocery_store/src/updated.xml")
    val updated_grocery_list: mutable.Map[Int, (String, (Double, String), Int)] = Grocery_Object.fromXML(updatedXML)
    App.grocery_list.keys.map(i => updated_grocery_list -= i)
    App.grocery_list ++= updated_grocery_list

  }
}


