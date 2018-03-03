package com.pakages

import java.util.Timer
import scala.collection.mutable
import scala.xml.XML
/**
 * @author ${user.name}
 */
object App  {
  
  val catalogueXML = XML.loadFile("D:/grocery_store/src/catalogue.xml")
  val grocery_list: mutable.Map[Int, (String, (Double, String), Int)] = Grocery_Object.fromXML(catalogueXML)


  def main(args : Array[String]) {
    println(grocery_list)
    val time = new Timer()
    time.schedule(com.pakages.ScheduledTask(),0,2000)
    Thread.sleep(2000)
    println(grocery_list)
    val user_demand: mutable.Map[Int, (String, (Double, String), Int)] = mutable.Map.empty[Int,(String,(Double,String),Int)]
    User().add(1,user_demand)
    User().add(2,user_demand)
    println(user_demand)
    User().remove(2,user_demand)
    println(user_demand)
    User().update(1,3,user_demand)
    println(user_demand)

  }



}
