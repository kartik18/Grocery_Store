package com.pakages

import java.util.Timer
import scala.collection.mutable
import scala.xml.XML
/**
 * @author ${user.name}
 */
object App  {
  
  val catalogueXML = XML.loadFile("D:/grocery_store/src/catalogue.xml")
  var grocery_list: mutable.Map[Int, (String, (Double, String), Int)] = Grocery_Object.fromXML(catalogueXML)
 // var user_demand: mutable.Map[Int, (String, (Double, String), Int)] = mutable.Map.empty[Int,(String,(Double,String),Int)]

  def login():Unit = {
    println("Select from the given options: ")
    println("Admin")
    println("User")
    scala.io.StdIn.readLine("") match {
      case "Admin" => {
          Admin().adminWindow()
      }
      case "User"  => {
        val time = new Timer()
        time.schedule(com.pakages.ScheduledTask(),0,2000)
        Thread.sleep(2000)
        User().userWindow()
      }
    }
  }

  def main(args : Array[String]) {
    login()



  }



}
