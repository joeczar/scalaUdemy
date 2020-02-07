package lectures.part2op

object MethodNotations extends App {
  import scala.language.postfixOps
  class Person(val name: String, favMovie: String, val age: Int = 0) {
    def likes(movie: String): Boolean = movie == favMovie
    def +(person: Person): String = s"${this.name} is kissing ${person.name}"
    def +(nickName: String): Person = new Person(s"$name ($nickName)", favMovie)
    def unary_! : String = s"$name, what the heck?!"
    def unary_+ : Person = new Person(name, favMovie, age + 1)
    def isAlive: Boolean = true
    def learns(thing: String): String = s"$name is learning $thing"
    def learnsScala: String = this learns "Scala"
    def apply(): String = s"Hi, my name is $name, and I like $favMovie."
    def apply(howMany: Int): String = s"$name watched $favMovie $howMany times."
  }

  val Ronja = new Person("Ronja", "Girls Club")
  val Joe = new Person("Joe", "Dune")

  val mary = new Person("Mary", "Inception")
  println(mary.likes("Inception"))
  println(mary likes "Inception") //both are equivalent
  //infix notation = operator notation (syntactic sugar)... Only works on methods with a single parameter

  // "Operators" in scala
  val tom = new Person("Tom", "The Lighthouse")
  println(Ronja + Joe)
  println(Ronja likes "Girls Club")

  println(1 + 2)
  println(1.+(2))
  // ALL OPERATORS ARE METHODS

  // Prefix notation
  val x = -1 // equivalent with 1.unary_-
  val y = 1.unary_-
  // Unary prefix only works with - + ~ !
  println(!Ronja)
  println(Ronja.unary_!)

  // Postfix notation
  println(Ronja isAlive)
  println(Ronja.isAlive)

  // apply()
  println(Ronja.apply)
  println(Ronja())

  /*  EXERCISES!!!!
    1. Overload the + operator:
       mary + "The Rockstar"  => new Person "Mary The Rockstar"

      * I can't see how to do this without creating a new val each time.... solution!
   */
  println((Ronja + "Cutie Pie")()) // SOLUTION!
  val Mary = mary + "The Rockstar"
  println(Mary.name)
  /*
    2. Add an age to the Person Class.
       Add a unary + operator =>  new Person with Age + 1
       +mary => mary with age incremented
   */
    println((+Ronja).age)
    val oldMary = +mary
    println(oldMary.age)

  /*
  3. Add a "learns" method in the Person class => "Mary is learning (learns)"
     Add a learnsScala method, calls the learns method with Scala
     Use it in postfix notation
   */
    println(mary learns "knitting")
    println(mary learnsScala)
  /*
    4. Overload the apply method
       mary.apply(2) => "Mary watched inception 2 times"
   */
    print(mary(2))
}

