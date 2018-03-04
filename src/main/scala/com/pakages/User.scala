package com.pakages
import scala.collection.mutable

case class User() {
  var total = 0.0d
  var user_demand: mutable.Map[Int, (String, (Double, String), Int)] = mutable.Map.empty[Int,(String,(Double,String),Int)]

  def userWindow(): Unit = {
      println("Select from the options given below")
      println("1) Items Available")
      println("2) Items in your cart")
      println("3) LogOut")

      scala.io.StdIn.readLine("Your Option please: ") match {
        case "1)" => show();Thread.sleep(2000);inputDataFromUser()
        case "2)" =>  listCartItems()
        case "3)" => App.login()
        case  _   => println("""Enter option with a number followed by ')'""");userWindow()
      }
  }
  def show():Unit = {
      App.grocery_list.foreach(v => println(v._1+" "+v._2._1+" @"+v._2._2._1+" "+v._2._2._2))
  }
  def inputDataFromUser():Unit = {
      val key = scala.io.StdIn.readLine("Please select the ID to buy an item: ")
      if(App.grocery_list.contains(key.toInt)){
          val quantity = scala.io.StdIn.readLine("Enter the quantity: ")
          add(key.toInt,user_demand,quantity.toInt)
          user_demand.values.foreach(v => println(v._1+" "+v._3+" units"))
        val flag = scala.io.StdIn.readLine("Want to add more (Y/N): ")
        if(flag == "Y"){
          inputDataFromUser()
        }
        else userWindow()
      }
      else {
        println("Sorry, please enter the valid key")
        userWindow()
      }

  }

  def listCartItems():Unit = {
      if(user_demand.keys.size == 0 ){
        println("Oops, your cart is empty")
      }
      else user_demand.values.foreach(v => println(v._1+" of price "+v._2._1+" "+v._2._2+" for "+v._3+" stock is "+v._2._1*v._3));Thread.sleep(2000)

    println("Select from the following options: ")
    println("1) Update the item quantity")
    println("2)Remove an item")
    println("3)Checkout your order")

    scala.io.StdIn.readLine("Enter your choice: ") match {
        case "1)" => {
          val id = scala.io.StdIn.readLine("Enter the item key: ").toInt
          if(user_demand.contains(id)){
            val from = scala.io.StdIn.readLine("From: ").toInt
            val to = scala.io.StdIn.readLine("To: ").toInt
              update(id,user_demand,from,to)
          }
          else println("Please enter the valid key");userWindow()
        }
        case "2)" => {
          val id = scala.io.StdIn.readLine("Enter the item key: ").toInt
          if(user_demand.contains(id)){
            val quantity = scala.io.StdIn.readLine("How much to remove: ").toInt
            remove(id,user_demand,quantity)
          }
          else println("Please enter the valid key");userWindow()

        }
        case "3)" => {
          checkout(user_demand)
          summary(user_demand)
          scala.io.StdIn.readLine("Press enter to continue...");App.login()
        }
    }
  }

  def add(i:Int,x:mutable.Map[Int, (String, (Double, String), Int)],quantity:Int): Unit = {
    if(App.grocery_list(i)._3 > quantity) {
      val key = i
      val stock = quantity
      var value = App.grocery_list(i)
      value = value.copy(_3 = stock)
      val temp_map: mutable.Map[Int, (String, (Double, String), Int)] = mutable.Map(key -> value)
      user_demand ++= temp_map
    }
  }
  def remove(i:Int,x:mutable.Map[Int, (String, (Double, String), Int)],quantity:Int):Unit = {
    val key = i
    val stock= quantity
    var value = x(i)
    value = value.copy(_3 = value._3 - stock)
    user_demand -= key
    user_demand ++= mutable.Map(key -> value)
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

  def summary(x:mutable.Map[Int, (String, (Double, String), Int)]) = {
   println("Your today's shopping is about "+total+" INR. And that includes")
   x.values.foreach(v => println("*  "+v._1))
  }
}

