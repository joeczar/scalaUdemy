package lectures.part2op

object CaseClasses extends App {
  /*
  Equals, HashCode, ToString
   */
  case class Person(name: String, age: Int)
    // Class parameters are promoted to fields
    val joe = new Person("Joe", 39)
    println(joe.name)
  // sensible toString
    println(joe.toString)
  // println(instance) == println(instance.soString)
    println(joe)

  // equals and hash code implemented ootb
  val joe2 = new Person("Joe", 39)
  println(joe == joe2)// Returns true in case class

  // CC's have handy copy method
  val joe3 = joe.copy(age = 666)
  println(joe3)

  // CC's have companion objects
  val thePerson = Person
  val mary = Person("Mary", 27)

  // CC's are serializable
  // Akka

  // CC's have extractor patterns == CC's can be used in pattern matching

  case object UnitedKingdom {
    def name: String = "The UK of GB and NI"
  } //case objects are like CC's except they are their own companion Object



}
