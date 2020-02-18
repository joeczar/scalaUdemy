package lectures.part3functionalProgramming

object HOFsCurries extends App{

  //val superFunction: (Int, (String, (Int => Boolean)) => Int) => (Int, Int) = ???
  // High Order Function === HOF

  // Examples: map, flatMap, & filter in MyList

  // function that applies a function n times over x
  // nTimes(f, n, x)
  // nTimes(f, 3, x) = f(f(f(x))) = nTimes(f, 2, x) = f(f(x))
  //
  @scala.annotation.tailrec
  def nTimes(f: Int => Int, n: Int, x: Int): Int =
    if (n <= 0) x
    else nTimes(f, n-1, f(x))

  val plusOne = (x: Int) => x + 1
  println(nTimes(plusOne, 10, 1))

  // ntb(f, n) = x=> f(f(f....(x)))
  // increment10 = ntb(plusOne, 10) = x => plusOns(plusOne...(x))
  // val y = increment10(1)
  def nTimesBetter(f: Int => Int, n: Int): (Int => Int) =
    if (n<=0) (x: Int) => x
    else (x: Int) => nTimesBetter(f, n-1) (f(x))

  val plus10 = nTimesBetter(plusOne, 10)
  println(plus10(1))
  println(plus10(3))
  // Curried functions
  val superAdder = (x: Int) => (y: Int) => x + y
  val add3 = superAdder(3)
  println(add3(10))
  println(superAdder(3)(10))

  // functions with multiple parameter lists
  def curriedFormatter(c: String)(x: Double): String = c.format(x)

  val standardFormat: (Double => String) = curriedFormatter("%4.2f")
  val preciseFormat: (Double => String) = curriedFormatter(("%10.8f"))

  println(standardFormat(Math.PI))
  println(preciseFormat(Math.PI))
  /* Exercises
    1. Expand MyList
      - forEach method A => Unit
         [1, 2, 3].forEach(X => println(x))
      - sort function ((A, A) => Int) => MyList
   */
  /*
    toCurry(f: (Int, Int) => Int (Int => Int => Int)
    fromCurry(f: (Int => Int => Int)): (Int, Int) => Int
   */
  def toCurry(f: (Int, Int) => Int): (Int => Int => Int) = x => y => f(x, y)
  def unCurry(f: Int => Int => Int): (Int, Int) => Int = (x, y) => f(x)(y)
  // curry tests
  def superMultiply: (Int => Int => Int) = toCurry(_ * _)
  def timesTen = superMultiply(10)
  println(timesTen(99))
  val simpleMultiply = unCurry(superMultiply)
  println(simpleMultiply(10, 99))
/*
    compose(f, g) => x => f(g(x))
    andThen(f, g) => x => g(f(x))
 */
  def compose[A, B, T](f: A => B, g: T => A):T => B = x => f(g(x))
  def andThen[A, B, C](f: A => B, g: B => C): A => C = x => g(f(x))
  // compose, andThen tests
  val add2 = (x: Int) => x + 2
  val times3 = (x: Int) => x *3

  val composed = compose(add2, times3)
  val ordered = andThen(add2, times3)

  println(composed(6))
  println(ordered(6))

}
