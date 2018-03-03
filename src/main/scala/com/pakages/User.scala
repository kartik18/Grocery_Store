package com.pakages

import scala.collection.mutable

case class User() {

  def add(i:Int,x:mutable.Map[Int, (String, (Double, String), Int)]): Unit = {
    val key = i
    val value =  App.grocery_list(i)
    val temp_map: mutable.Map[Int, (String, (Double, String), Int)] = mutable.Map(key -> value)
    x ++= temp_map
  }

  def remove(i:Int,x:mutable.Map[Int, (String, (Double, String), Int)]):Unit = {
    val key = i
    x --= List(key)
  }

  def update(from:Int,to:Int,x:mutable.Map[Int, (String, (Double, String), Int)]): Unit = {
    remove(from,x)
    add(to,x)
  }
}
