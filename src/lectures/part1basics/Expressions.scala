package lectures.part1basics

object Expressions extends App {
  val x = 1 + 2 // Expressions
  println(x)

  println(2 + 3 * 4)
  // Order of operations
  // + - * / & | ^ (bitwise exclusive OR) << >> (bitwise left & right shift) >>> ( right shift with zero extension)

  println(1==x)
  // == != < <= > =>

  println(!(1 == x))
  // ! && ||

  var aVariable = 2
  aVariable += 3 // -= *= /= .... side effects

  // Instructions (DO) vs Expressions (VALUE)

  // IF expression
  val aCondition = true
  val aConditionedValue = if (aCondition) 5 else 3 // IF EXPRESSION
  println(aConditionedValue)
  println(if (aCondition) 5 else 3)

  var i = 0
  val aWhile: Unit = while (i < 10) {
    println(i)
    i += 1
  }

  // NEVER WRITE THIS AGAIN!

  // EVERYTHING in Scala is an Expressions
  val aWeirdValue = aVariable = 3 // Unit == void
  println(aWeirdValue)

  // side effects: println(), whiles, reassigning

  val aCodeBlock = { // Code Blocks are EXPRESSIONS
    val y = 2 // values are not accessible outside the code block
    val z = y + 1

    if (z > 2) "Hello" else "goodbye!" // the value of the Code Block is the value of its last expression
  }
  // Do not use while loops

  // 1. What is the difference between "Hello World!" and println("Hello World!")
    // a string is a VALUE where println() is a unit an instruction.
  // 2. What are the values of:
  val someValue = { // true
    2 < 3
  }

  val someOtherValue = { // 427
    if(someValue) 239 else 986
    42
  }
  println(someValue, someOtherValue)

 print(aCodeBlock)
}
