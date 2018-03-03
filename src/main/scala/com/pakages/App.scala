package com.pakages

//import com.pakages.Timer__Object.{new_tags_list, updated_grocery_list}
import java.util.Timer
import scala.collection.mutable
import scala.xml.XML
/**
 * @author ${user.name}
 */
object App  {
  
 // def foo(x : Array[String]) = x.foldLeft("")((a,b) => a + b)
  val catalogueXML = XML.loadFile("D:/grocery_store/src/catalogue.xml")
  val grocery_list: mutable.Map[Int, (String, (Double, String), Int)] = Grocery_Object.fromXML(catalogueXML)


  def main(args : Array[String]) {
    // val grocery_list = mutable.Map.empty[Int, (String, (Double, String), Int)]
    println(grocery_list)
    val time = new Timer()
    time.schedule(com.pakages.ScheduledTask(),0,10000)
    Thread.sleep(10000)
    println(grocery_list)

  }



}
