package lectures.part1basics

object ValuesVariablesTypes extends App {

  val x: Int = 42 // val x = 42 is ok as well. Compiler can INFER types!
  val y = 666
  println(x)
  //x = 2  doesn't work! Val's are immutable!

  val aString: String = "hello"; // Semicolons are ok but unnecessary unless combining expressions, which is bad style
  val anotherString = "goodbye"

  val aBoolean: Boolean = true
  val aChar: Char = 'a'
  val anInt: Int = x
  val aShort: Short = 14316
  val aLong: Long = 20950189584701634L
  val aFloat: Float = 2.0f
  val aDouble: Double = 3.14

  // Variables
  var aVariable = 5 // side effects
// prefer vals over vars
}
