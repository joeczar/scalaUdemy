package lectures.part2op

import java.nio.{BufferOverflowException, BufferUnderflowException}

object Exceptions extends App {

  val x: String = null
  // println(x.length) // Will crash with a null pointer exception

  // 1.  Throwing exceptions
  //val aWeirdVal: String = throw new NullPointerException // Assignable but holds value: Nothing

    // throwable extends Throwable class.
    // Exception and Error are the major throwable subtypes

  // 2. Catching exceptions
  def getInt(withExceptions: Boolean): Int =
    if(withExceptions) throw new RuntimeException("No Int for you!")
    else 42

  val potentialFail = try {
    // Code that might throw an exception
    getInt(false)
  } catch {
    case e: RuntimeException => println("Caught a runtime exception")
  } finally {
    //code that will get executed no matter what
    // Optional
    // does not influence the return value of this expression
    // use finally only for side effects
    println("Finally")

    // How to define your own exceptions
    class MyException extends Exception
    // val exception = new MyException

    // throw exception
  }
  val ooMemErr = new OutOfMemoryError()
  //throw ooMemErr
  //val array = Array.ofDim(Int.MaxValue) // that is how you create OOMError

  val soError = new StackOverflowError()
  //throw soError
  def infinite: Int = 1 + infinite
  //val noLimit = infinite
  class OverflowException extends Exception
  class UnderflowException extends Exception
  class MathCalculationException extends Exception

  object PocketCalculator {
    def add(n: Int, x: Int): Int = {
      val result = n + x
      if(n > 0 && x > 0 && result < 0) throw new OverflowException// if Int.MaxValue is exceeded it returns a negative number. This checks against that.
      else if(n < 0 && x < 0 && result > 0) throw new UnderflowException // same thing in negative
      else result
    }
    def subtract(n: Int, x: Int): Int = {
      val result = n - x
      if(n > 0 && x < 0 && result < 0) throw new OverflowException
      else if(n < 0 && x > 0 && result > 0) throw new UnderflowException
      else result
    }
    def multiply(n: Int, x: Int): Int = {
      val result = n * x
      if(n > 0 && x > 0 && result < 0) throw new OverflowException
      else if(n < 0 && x < 0 && result < 0) throw new OverflowException
      else if(n > 0 && x < 0 && result > 0) throw new UnderflowException
      else if(n < 0 && x > 0 && result > 0) throw new UnderflowException
      else result
    }
    def divide(n: Int, x: Int): Int = {
      if(n == 0 | x == 0) throw new MathCalculationException
      else n / x
    }
  }
  val pc = PocketCalculator
  println(pc.add(1, 2))
  //println(pc.add(Int.MaxValue, 2))
  println(pc.subtract(666, 432))
  println(pc.multiply(2, 333))
  println(pc.divide(1998, 3))
}
