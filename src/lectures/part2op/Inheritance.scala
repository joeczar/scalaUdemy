package lectures.part2op

object Inheritance extends App {

  class Animal {
    def eat = println("nomnomnom")
    //private def be = println("I am, what I am...")//Only usable within Animal class
    //protected def move = print("One wiggle after the other")// only usable within animal & subclasses
    // def means public and can be accessed anywhere
    val creatureType: String = "Wild"
  }

  // Single class inheritance - can only extend ONE class at a time
  class Cat extends Animal { // Cat = subclass of Animal, Animal = Superclass of Cat
    def crunch = {
      eat // can call protected eat here
      println("Crunch, Crunch")
    }
  }

  val cat = new Cat
  cat.crunch

  //constructors
  class Person(name: String, age: Int){
    def this(name: String) = this(name, 0)
  }
  class Adult(name:String, age: Int, IdCard: String) extends Person(name)
  // Override
  class Dog(override val creatureType: String) extends Animal {
    override def eat: Unit ={
      super.eat
      println("CrunchCrunch")
    }
    //override val creatureType: String = "Domestic"
  }
  val dog = new Dog("K9")
  dog.eat
  println(dog.creatureType)

  // Type substitution // Broad: Polymorphism
  val unknownAnimal: Animal = new Dog("K9")
  unknownAnimal.eat //

  // KNow the difference: OverRIDING & overLOADING

  // super

  /*
   Preventing overrides
   1. use final on member ( final def eat: String = "Nomnomnom" )
   2. use final on entire class final class Animal { doesn't allow for subclasses }
   3. use sealed, this allows for extension in THIS FILE, but doesn't allow for subclasses in other files
   */



}

