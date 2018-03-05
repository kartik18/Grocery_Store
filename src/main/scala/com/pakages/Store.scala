package com.pakages
import xml._
import scala.util.control.Breaks._

abstract class Requirement {
  val name:String
  val unitSize:Int
  val unitPrice:(Double,String)
  val stock:Int
}

case class Beverages(name:String, unitSize: Int, unitPrice:(Double,String), stock:Int) extends Requirement{
  override def toString: String = name +" "+unitSize+" "+unitPrice._1+" "+unitPrice._2+" "+stock
}
case class Foodrains(name:String, unitSize: Int, unitPrice:(Double,String), stock:Int) extends Requirement
case class OilAndMasala(name:String, unitSize: Int, unitPrice:(Double,String), stock:Int) extends Requirement
case class BeautyAndHygiene(name:String, unitSize: Int, unitPrice:(Double,String), stock:Int) extends Requirement
case class FruitsAndVegetables(name:String, unitSize: Int, unitPrice:(Double,String), stock:Int) extends Requirement


class Store  {
  def fromXML(node: scala.xml.NodeSeq):Unit =
    node match {
      case <catalogue>{content @ _*}</catalogue> => {
         for (con @ <items>{_*}</items> <- content) {
           val category = con \\ "id"
           // if (category == "Beverage") {
           // println((con \\ "name").text, (con \\ "unitSize").text.toInt, ((con \\ "amount").text.toDouble, (con \\ "currency").text), (con \\ "stock").text.toInt)
          //}
          category.foreach(x => println(x.text))
        }
      }
    }


}
