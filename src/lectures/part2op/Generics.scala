package lectures.part2op

object Generics extends App {

  class MyList[+A] { // can also be done with Traits
   // use the type A
      // If I add B(Supertype or subtype of supertype) to List of A, return list of B(Supertype)
    def add[B >: A](element: B): MyList[B] = ???
    /*
      List[A] (Cat)
      A = Cat
      +
      B = Dog (supertype for both Animal)
      =
      List[B]
     */
  }
  class MyMap[key, value]

  val listOfIntegers = new MyList[Int]
  val listOfStrings = new MyList[String]

  object MyList {
    def empty[A]: MyList[A] = ???
  }
  val emptyListOfInts = MyList.empty[Int]
  println(emptyListOfInts)

  // variance problem
  class Animal
  class Cat extends Animal
  class Dog extends Animal
  // Does List[Cat] extend List[Animal]
  // 1. Yes - Covariance
  class CovariantList[+A]
  val animal = new Cat
  val animalList: CovariantList[Animal] = new CovariantList[Cat]
  // animalList.add(new Dog) ??? Does this work, HARD QUESTION

  // 2. No - Invariance
  class InvariantList[A]
  var invariantList: InvariantList[Animal] = new InvariantList[Animal] // Left and right hand sides must match

  // 3. Hell, no! Contravariant classes
  class Trainer[-A]
  val trainer: Trainer[Cat] = new Trainer[Animal]

  // bounded types
  class Cage[A <: Animal](animal: A) // Cage only accepts SubTypes of class Animal
  val cage = new Cage(new Dog)

  class Car
  // Generic type needs proper bounded type
  //val newCage = new Cage(new Car) // Will not compile because Car is not a subtype of animal
}
