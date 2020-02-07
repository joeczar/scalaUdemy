package lectures.part2op

object Objects extends App {

  // SCALA DOES NOT HAVE CLASS LEVEL FUNCTIONALITY // DOES NOT KNOW STATIC
  object Person {
    // "static"/"class" level functionality
    val N_EYES = 2
    def canFly: Boolean = false

    // Factory method
    def apply(mother: Person, father: Person): Person = new Person("Bobby")
  }
  class Person(val name: String) {
    // Instance level functionality
  }
  // object & class together == Companions
  println(Person.N_EYES)

// Scala object = SINGLETON INSTANCE
  val mary = new Person("Mary")
  val john = new Person("John")
  println(mary == john) // returns false

  // If instance is INSTANTIATED... this is no longer true
  val person1 = Person
  val person2 = Person
  println(person1 == person2) // returns true

  val bobby = Person(mary, john)

  // Scala Applications = Scala Object with
  // def main(args: Array[String]): Unit
}
