package lectures.part1basics

import scala.annotation.tailrec

object Recursion extends App {
  def factorial(n: Int): Int =
    if (n <= 1) 1
    else {
      println("Computing factorial of " + n + " - I first need factorial of " + (n-1))
      val result = n * factorial(n - 1)
      println("Computed factorial of " + n)

      result
    }
  println(factorial(10))
  //println(factorial(500))

  def anotherFactorial(n: BigInt): BigInt = {
    @tailrec
    def factHelper(x: BigInt, accumulator: BigInt): BigInt =
      if (x <= 1) accumulator
      else factHelper(x - 1, x * accumulator) // TAIL RECURSION!!!!! = use recursive call as the LAST expression

    factHelper(n, 1)
  }
  //println(anotherFactorial(20000))
  /*
  anotherFactorial(10) = factHelper(10, 1)
  = factHelper(9, 10 * 1)
  = factHelper(8, 9 * 10 * 1)
  = factHelper(7, 8 * 9 * 10 * 1)
    ......
  = factHelper(2, 3 * 4 * 5... * 10 * 1)
  = factHelper(1, 1 * 2 * 3 *.............* 10)
  = 1 * 2 * 3.... *9 *10
   */

  // WHEN YOU NEED LOOPS USE _TAIL_RECURSION
  /*
  1. Concatenate a string n times
  2. IsPrime function tail recursive
  3. Fibonacci function, tail recursive.
   */
  def concatTail(s: String, x: Int): String = {
    @scala.annotation.tailrec
    def concatHelper(accumulator: String, counter: Int): String = //Looks like I got tail rec & a closure here
      if (counter <= 1) {
        accumulator
      }  else {
        concatHelper(accumulator + s, counter - 1)
      }

    concatHelper(s, x)
  }
  println(concatTail("Poop", 6))
  // Course solution
  @scala.annotation.tailrec
  def concatenateTailRec(aString: String, n: Int, accumulator: String): String = if (n <= 0) accumulator
  else concatenateTailRec(aString, n-1, aString + accumulator)

  println(concatenateTailRec("Say something nice! ", 6, ""))

  def isPrime(num: Int): Boolean = {
    @scala.annotation.tailrec
    def isPrimeTailRec(t: Int, isStillPrime: Boolean): Boolean =
      if (!isStillPrime) false
      else if(t <= 1) true
      else isPrimeTailRec(t - 1, num % t != 0 && isStillPrime)
    isPrimeTailRec(num / 2, true)
  }
  println(isPrime(2003))
  println(isPrime(666))
  println(isPrime(19847561))

  def fibonazi(n: Int): Int = {
    @scala.annotation.tailrec
    def fiboTailRec(i: Int, last: Int, nextToLast: Int): Int =
      if (i >= n) last
      else fiboTailRec(i + 1, last + nextToLast, last)

    if (n <= 2) 1
    else fiboTailRec(2, 1, 1)
  }
  println("fibonazi", fibonazi(8))

}
