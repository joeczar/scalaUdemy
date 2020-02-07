package lectures.part2op

object AbstractDataTypes extends App {
  //abstract
  abstract class Animal {
    val creatureType: String = "wild"
    def eat: Unit
  }

  class Dog extends Animal {
    override val creatureType: String = "K9"
    override def eat: Unit = println("Crunch Crunch")
  }

  // Traits
  trait Carnivore {
    def eat(animal: Animal): Unit
    val preferredMeal: String = "Fresh Meat"
  }
  trait coldBlooded


  class Crocodile extends Animal with Carnivore with coldBlooded {
    override val creatureType: String = "Croc"
    def eat: Unit = println("Snap! Nomnomnom")
    def eat(animal: Animal): Unit = println(s"I'm a $creatureType and I am eating a ${animal.creatureType}")
  }

  val dog = new Dog
  val croc = new Crocodile

  croc.eat(dog)

  // Traits vs. abstract classes
  // 1. Traits do not have constructor parameters
  // 2. can only mix in 1 class, but inherit multiple traits
  // 3. straits are behavior

}
