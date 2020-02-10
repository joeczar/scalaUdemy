package lectures.part3functionalProgramming

object whatsAFunction extends App {
  // DREAM:use functions as first class elements
  // problem: OOP world

  val doubler = new MyFunction[Int, Int] {
    override def apply(element: Int): Int = element * 2
  }
  println(doubler(333))

  trait MyFunction[A, B] {
    def apply(element: A): B
  }

  // Function Types = Function[A, B]
  val stringToIntConverter = new Function[String, Int] {
    override def apply(string: String): Int = string.toInt
  }
  println(stringToIntConverter("3") + 4)

  // Full syntax
  val adder: ((Int, Int) => Int) = new Function2[Int, Int, Int] {
    override def apply(v1: Int, v2: Int): Int = v1 + v2
  }
  // Shortened syntax
  val adder2: ((Int, Int) => Int) = (v1: Int, v2: Int) => v1 + v2

  //Function types Function2[A, B, R] === (A, B) => R
  // ALL SCALA FUNCTIONS ARE OBJECTS, OR; Instances of classes of Function1-22

  /*
  Exercises:
    1. A function that takes two strings and concatenates them.
    2. transform MyPredicate and MyTransformer into function types
    3. define a function that takes an Int and returns another function that takes an Int and retunrs an int
   */
  val concat:((String, String) => String) = (str1: String, str2: String) => str1 + str2
  println(concat("Alpha", "Omega"))

  val funkyReturn: ((Int) => (Int) => Int) = (a: Int) => (b: Int) => a + b
  val test = funkyReturn(3)
  println(test(4))
  println(funkyReturn(3)(4))

  val specialFunction: Function1[Int, Function1[Int, Int]] = new Function1[Int, Function1[Int, Int]] {
    override def apply(a: Int): Int => Int = new Function1[Int, Int] {
      override def apply(b: Int): Int = a + b
    }
  }
  //HA! I did it!!!!!
  val specialFunctionShort: (Int) => ((Int) => Int) = (a: Int) => (b: Int) => a + b


}
