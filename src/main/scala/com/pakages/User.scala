package com.pakages

import scala.collection.mutable

case class User() {

  def add(i:Int,x:mutable.Map[Int, (String, (Double, String), Int)],quantity:Int): Unit = {
    val key = i
    val stock = quantity
    var value =  App.grocery_list(i)
    value = value.copy(_3 =  stock)

    val temp_map: mutable.Map[Int, (String, (Double, String), Int)] = mutable.Map(key -> value)
    x ++= temp_map
  }

  def remove(i:Int,x:mutable.Map[Int, (String, (Double, String), Int)],quantity:Int):Unit = {
    val key = i
    val stock= quantity
    var value = x(i)
    value = value.copy(_3 = value._3 - stock)
    App.user_demand -= key
    App.user_demand ++= mutable.Map(key -> value)
  }

  def update(i:Int,x:mutable.Map[Int, (String, (Double, String), Int)],from:Int,to:Int): Unit = {
    if(from - to > 0) {
      remove(i,x,from - to)
    }
    else{
     add(i,x,math.abs(from)+math.abs(to - from))
    }


  }

  def checkout(x:mutable.Map[Int, (String, (Double, String), Int)])  ={
    var total = 0.0
     x.keys.map(i => {
       val product = x(i)._1
       val price = x(i)._2._1
       val currency = x(i)._2._2
       val stock = x(i)._3
       println("Bill for the "+ product +" is "+ price*stock+" "+currency)
       total += price*stock
       val key = i
       val value = (App.grocery_list(i).copy(_3 = App.grocery_list(i)._3 - x(i)._3))
       App.grocery_list -= key
       App.grocery_list ++= mutable.Map(key -> value)
     })
    println("Your total bill is "+total)
  }
}

