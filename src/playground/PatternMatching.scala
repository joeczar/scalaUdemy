package playground

import scala.util.Random

object PatternMatching extends App {

  // Switch on steroids
  val random = new Random
  val x = random.nextInt(10)

  val description = x match {
    case 1 => "One"
    case 2 => "Two"
    case 3 => "three"
    case 4 => "four"
    case _ => "five to ten"
  }
  println(x)
  println(description)

  // decompose values
  case class Person(name: String, age: Int)
  val bob = new Person("Bob", 62)

  val greeting = bob match {
    case Person(n, a) if a < 21 => s"I'm $n and I can't drink in the U.S."
    case Person(n, a)  => s"Howdy, I'm $n, I'm $a years old!"
    case _ => """ ¯\_(ツ)_/¯ """
  }
  println(greeting)

  // 1. cases are matched in order
  // 2. if no cases match => Match error
  // 3. PM Type = unified type of all cases

  // pm on sealed hierarchies
  sealed class Animal
  case class Dog(breed: String) extends Animal
  case class Parrot(greeting: String) extends Animal

  val animal: Animal = Dog("Beagle")
  animal match {
    case Dog(breed) => println(s"I'm a $breed")
  }

  trait Expr
  case class Number(n: Int) extends Expr
  case class Sum(e1: Expr, e2: Expr) extends Expr
  case class Prod(e1: Expr, e2: Expr) extends Expr

  def show(e: Expr): String = e match {
    case Number(n) => s"$n"
    case Sum(e1, e2) => s"${show(e1)} + ${show(e2)}"
    case Prod(e1, e2) => {
      def maybeShowParenthesis(exp: Expr) = exp match {
        case Number(_) => show(exp)
        case Prod(_, _) => show(exp)
        case _ => s"(${show(exp)})"
      }
      maybeShowParenthesis(e1) + " * " + maybeShowParenthesis(e2)
    }
  }
  val one = Number(1)
  val two = Number(1)
  val three = Number(3)
  val four = Number(4)
  val six = Number(6)
  println(show(six))


  val sixPlusTwo = Sum(six, two)
  val onePlusFour = Sum(one, four)
  val prodOfFiveAndEight = Prod(sixPlusTwo, onePlusFour)
  println(show(sixPlusTwo))
  print(show(prodOfFiveAndEight))
}
