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
  var user_demand: mutable.Map[Int, (String, (Double, String), Int)] = mutable.Map.empty[Int,(String,(Double,String),Int)]


  def main(args : Array[String]) {
    println(grocery_list)
    val time = new Timer()
    time.schedule(com.pakages.ScheduledTask(),0,2000)
    Thread.sleep(2000)
    println(grocery_list)
   // val user_demand: mutable.Map[Int, (String, (Double, String), Int)] = mutable.Map.empty[Int,(String,(Double,String),Int)]
    User().add(1,user_demand,3)
    User().add(2,user_demand,5)
    println(user_demand)
    User().remove(1,user_demand,2)
    User().remove(2,user_demand,1)
    println(user_demand)
    User().update(1,user_demand,1,5)
    println(user_demand)
    User().checkout(user_demand)
    println(grocery_list)

  }



}
