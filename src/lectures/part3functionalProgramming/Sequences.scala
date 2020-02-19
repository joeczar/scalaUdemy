package lectures.part3functionalProgramming

import scala.util.Random

object Sequences extends App {

  val aSequence = Seq(1,3,2,4)
  println(aSequence) // returns a list!
  println(aSequence.reverse)
  println(aSequence(2))
  println(aSequence ++ Seq(5, 6,7))
  println(aSequence.sorted)

  // Ranges
  val aRange: Seq[Int] = 1 to 10
  aRange.foreach(println)
  (1 to 10).foreach(x => println(x))

  // Lists
  val aList = List(1,2,3)
  val prepended = 666 +: aList :+ 42
  println(prepended)

  val apples5 = List.fill(5)("apples")
  println(apples5)
  println(apples5.mkString("_|_"))

  // Arrays
  val numbers = Array(1,2,3,4)
  val threeElements = Array.ofDim[Int](3)
  println(numbers) // Arrays don't have Scala's toString method
  println(threeElements)
  threeElements.foreach(println) // Empty arrays are initiated with default values

  // mutation
  numbers(2) = 0 // syntactic sugar for numbers,update(2, 0)
  println(numbers.mkString(" "))

  // arrays and seq
  val numbersSeq: Seq[Int] = numbers
  println(numbersSeq)

  // Vectors
  val vector: Vector[Int] = Vector(1,2,3,4)

  // Vectors vs Lists

  val maxRuns = 1000
  val maxCapacity = 1000000

  def getWriteTime(collection: Seq[Int]): Double = {
    val r = new Random
    val times = for{
      it <- 1 to maxRuns
    } yield {
      val CurrentTime = System.nanoTime()
      collection.updated(r.nextInt(maxCapacity), r.nextInt())
      System.nanoTime() - CurrentTime
    }
    times.sum * 1.0 / maxRuns
  }

  val numbersList = (1 to maxCapacity).toList
  val numbersVector = (1 to maxCapacity).toVector

  // + keeps reference to tail
  // - updating an element in the middle or end is expensive
  println(getWriteTime(numbersList))

  // + depth of tree is small
  // - needs to replace entire 32 element chunk
  println(getWriteTime(numbersVector))
}
