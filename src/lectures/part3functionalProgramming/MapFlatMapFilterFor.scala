package lectures.part3functionalProgramming

import scala.collection.mutable.ListBuffer

object MapFlatMapFilterFor extends App {

  val list = List(1, 2, 3)
  println(list)
  println(list.head)
  println(list.tail)

  // map
  println(list.map(_+1))
  println(list.map(_ +" is a number"))

  // filter
  println(list.filter(_ % 2 != 0))

  // flatmap
  val toPair = (x: Int) => List(x, x+1)
  println(list.flatMap(toPair))

  // Print all combinations between all lists
  val nums = List(1, 2, 3, 4)
  val chars: List[Char] = List('a', 'b', 'c', 'd')
  val colors: List[String] = List("black", "white", "green", "blue", "red")

  // I did it! Double tail recursive solution. (Huge, but still an accomplishment)
  def combinator[A, B, C](l1: List[A], l2: List[B]): List[String] = {
    @scala.annotation.tailrec
    def loop(l1s: List[A], l2s: List[B], acc: List[String] = Nil): List[String] ={
      if(l1s.isEmpty) acc
      else {
        @scala.annotation.tailrec
        def loop2(l1x: List[A], l2x: List[B], acc: List[String] = Nil): List[String] ={
        if (l2x.isEmpty) acc
        else loop2(l1x, l2x.tail, s"${l2x.head}${l1x.head}":: acc)
      }
        loop(l1s.tail, l2s,  loop2(l1s, l2s, acc))
      }
    }
    loop(l1, l2).reverse
  }



  def combinatorFor[A, B, String](l1: List[A], l2: List[B]): List[String] = {
    val combine = new ListBuffer[String]()
    //val combine: List[String] = Nil
    for (i <- l1) {
      for (j <- l2) {
        combine :+ s"$i$j"
        //WHY CAN'T I GET THIS TO ADD TO THE LIST!!!!!!!
      }
    }

    combine.toList
  }
  println(combinator(nums, chars))

  // Answer... way easier
  val combineFlatMap = nums.flatMap(n => chars.flatMap(c => colors.map(color => s"$c$n-$color")))
  println(combineFlatMap)

  list.foreach(println)

  val forCombinations = for {
    n <- nums if n % 2 != 0 // filter call!
    c <- chars
    color <- colors
  } yield s"$c$n-$color"

  println(forCombinations)

  // syntax overload

  list.map { x =>
    x * 2
  }

  /*
  Exercises
    1: See if MyList supports for comprehensions
    YES!
    2. implement a small collection of at most ONE element Maybe[+T]
      -map, flatMap, filter
   */


}
