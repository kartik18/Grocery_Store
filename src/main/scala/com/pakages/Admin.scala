package com.pakages
import java.util.Timer

import scala.collection.mutable

object Admin {
  var buy_get_Discount = scala.collection.mutable.Map.empty[Int,(Int,Int)] //ItemKey Min Free
  def adminWindow():Unit = {
    val time = new Timer()
    time.schedule(com.pakages.ScheduledTask(),0,2000)
    val pass = "admin"
    if( pass == "admin"){
      println("Welcome Admin")
      println("Select from the option given below")
      println("1) Create offers and discounts")
      println("2) View offers and discounts")
      println("3) LogOut")

      scala.io.StdIn.readLine("Enter you choice: ") match {
        case "1)" =>  offerWindow()
        case "2)" =>  viewOffer()
        case "3)" => println("Wrong Input");App.login()
      }
    }
    else println("Invalid Password");App.login()
  }

  def offerWindow():Unit = {
    println("Select from the option given below")
    println("1) Buy some get some offer")
    //pr
    scala.io.StdIn.readLine("Enter your choice: ") match {
      case "1)" => buy_get_offer()
      case _ => adminWindow()
    }
  }

  def buy_get_offer():Unit = {
    println(App.grocery_list)
    val id = scala.io.StdIn.readLine("Enter the ID to create a discount: ").toInt
    if(App.grocery_list.contains(id)){
      val min = scala.io.StdIn.readLine("Enter the minimum quantity required to create an offer: ").toInt
      val free = scala.io.StdIn.readLine("Enter the quantity of free product: ").toInt
      buy_get_Discount ++= mutable.Map(id -> (min,free))
      println(buy_get_Discount)
      val flag = scala.io.StdIn.readLine("Want to add more (Y/N): ")
      if(flag == "Y"){
        offerWindow()
      }
      else adminWindow()
    }
  }

  def viewOffer():Unit = {
    if (buy_get_Discount.keys.size > 0){
          buy_get_Discount.foreach(o => println("Id "+o._1+" Min. quantity "+o._2._1+" and free quantity is "+o._2._2))
    }
    else println("Sorry! No offers available");Thread.sleep(1000);adminWindow()
  }
}
