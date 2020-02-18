package lectures.part3functionalProgramming

object AnonymousFunctions extends App {

  // Anonymous Function (LAMBDA)
  // Type call after name is optional
  val doubler: Int => Int = (x: Int) => x * 2
  val doubler2 = (x: Int) => x * 2

  // Multiple params
  val adder: (Int, Int) => Int = (a: Int, b: Int) => a + b

  val justDoIt = () => 3
  val justDoItTyped: () => Int = () => 3

  // Lambdas must be called with parenthesis
  println(justDoIt)
  println(justDoIt())

  // Curly braces with lambda
  val stringToInt = { (str: String) =>
    str.toInt
  }

  // MOAR Syntatic sugar
  val niceIncrement: Int => Int = _ + 1 // equivalent to x => x + 1
  val niceAdder: (Int, Int) => Int = _ + _ // Equivalant to (a, b) => a + b

}
