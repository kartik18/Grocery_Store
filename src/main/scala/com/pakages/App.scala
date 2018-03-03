package com.pakages

import scala.collection.mutable
import scala.xml.XML

/**
 * @author ${user.name}
 */
object App  {
  
 // def foo(x : Array[String]) = x.foldLeft("")((a,b) => a + b)
  
  def main(args : Array[String]) {
    val catalogueXML = XML.loadFile("D:/grocery_store/src/catalogue.xml")
   // val grocery_list = mutable.Map.empty[Int, (String, (Double, String), Int)]
    val grocery_list: mutable.Map[Int, (String, (Double, String), Int)] = Grocery_Object.fromXML(catalogueXML)
    println(grocery_list)
  }

}
