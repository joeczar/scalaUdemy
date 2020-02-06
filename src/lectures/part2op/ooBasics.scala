package lectures.part2op

object ooBasics extends App {

  val person = new Person("John", 26)
  println(person.age)
  person.greet("Daniel")
  person.greet()

  val author = new Writer("Thomas", "Harris", 1940)
  val book = new Novel("Hannibal", 1999, author)
  println(author.fullName, book.authorAge)

  val counter = new Counter
  counter.inc.print
  counter.inc.inc.inc.print
  counter.dec(10).print
}
// constructor
class Person(val name: String, val age: Int = 0) {
  // class parameters, (name: String, age: Int) are not class FIELDS (val name: String, val age: Int)

  // body
  val x = 2 // val, var definitions
  println(1 + 3)

  // Method
  def greet(name: String): Unit = println(s"${this.name} says, Hi $name") // here $name refers to the parameter
  def greet(): Unit = println(s"Hi, I'm $name") // here $name actually refers to ${this.name}
  // Overloading: supplying methods with the same name but different signatures (different numbers & types of parameters
  // def greet(): Int = 42 *** not overloading. This causes a conflict

  // Multiple constructors or Overloading Constructors
  def this(name: String) = this(name, 0) // Auxiliary constructor to initiate the age to 0 (pretty useless because we can use default values...
  // the implementation of an auxiliary constructor has to be another... CONSTRUCTOR!
  def this() = this("John Doe")
}
  /*
  Novel and Writer class

  Writer: first name, last name, year
    -method full name

  Novel: name, year of release, author
    - authorAge
    - isWrittenBy
    - copy (New year of release) = new instance of Novel

  Counter
    - receives and Int Value
    - method currentCount
    - method increment/decrement = new instance of Counter
    - overload inv/dec to receive an amount

   */
class Writer(val firstName: String, val lastName: String, val birthYear: Int = 0) {
    def fullName: String = s"$firstName $lastName"
  }

class Novel(val title: String, val releaseYear: Int, val author: Writer) {
  def authorAge= releaseYear - author.birthYear
  def isWrittenBy(author: Writer) = author == this.author
  def copy(newYear: Int) = new Novel(this.title, newYear, this.author)
}

class Counter(val count: Int = 0) {
  // Starting with immutability. each increment returns a new instance of counter plus x
  def inc = {
    println("Incrementing")
    new Counter(count + 1)
  }
  def dec = {
    println("Decrementing")
    new Counter(count - 1)
  }

  def inc(n: Int): Counter = {
    if (n <= 0) this
    else inc.inc(n - 1) // Recursive call to the above/first inc
  }
  def dec(n: Int): Counter = {
    if (n <= 0) this
    else dec.dec(n - 1)
  }
  val print = println(count)
}

//val ThomasHarris: Writer = new Writer("Thomas", "Harris", )