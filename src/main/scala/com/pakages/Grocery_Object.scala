package com.pakages
import scala.collection.mutable
object Grocery_Object {


  def fromXML(node: scala.xml.NodeSeq): mutable.Map[Int, (String, (Double, String), Int)] = {
    val grocery_list: mutable.Map[Int, (String, (Double, String), Int)] = mutable.HashMap.empty[Int,(String,(Double,String),Int)]
    node match {
      case <catalogue>{content @ _*}</catalogue> => {
        for (con @ <items>{_*}</items> <- content) {
          val category = con \\ "id"
          val name = con \\ "name"
          val amount = con \\ "amount"
          val currency = con \\ "currency"
          val stock = con \\ "stock"
          val id_length: Int = (con \\ "id").length

          val category_list = mutable.ListBuffer.empty[Int]
          val name_list = mutable.ListBuffer.empty[String]
          val amount_list = mutable.ListBuffer.empty[Double]
          val currency_list = mutable.ListBuffer.empty[String]
          val stock_list = mutable.ListBuffer.empty[Int]


          category.foreach(x => { category_list.+=(x.text.toInt).toList})
          name.foreach(x => { name_list.+=(x.text).toList})
          amount.foreach(x => { amount_list.+=(x.text.toDouble).toList})
          currency.foreach(x => { currency_list.+=(x.text).toList})
          stock.foreach(x => { stock_list.+=(x.text.toInt).toList})

          //(category_list,name_list,amount_list,stock_list).zipped.toList
      //   grocery_list =  grocery_list.++((List(category_list,name_list,amount_list,currency_list,stock_list).transpose))
      //  grocery_list.++(final_list)

          val final_list =   category_list zip name_list zip amount_list zip currency_list zip stock_list map {
            case (((((a, b), c), d), e)) => (a,b,c,d,e)
          }
          for(i <- 0 until(id_length)) {
              grocery_list ++= List(final_list(i)._1 -> (final_list(i)._2, (final_list(i)._3,final_list(i)._4),final_list(i)._5))

          }
          //println(grocery_list)
          //println(final_list)
          //print(List(category_list,name_list,amount_list,currency_list,stock_list).transpose)
         //val l = List(category_list,name_list,amount_list,currency_list,stock_list).transpose
         // println(l)
        }

      }
    }
 grocery_list }
}
