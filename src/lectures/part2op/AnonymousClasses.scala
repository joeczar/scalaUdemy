package lectures.part2op

object AnonymousClasses extends App{

  abstract class Animal{
    def eat: Unit
  }

  // Anonymous Class
  val funnyAnimal: Animal = new Animal {
    override def eat: Unit = println("ahahahahaha")
  }
  /* What the Compiler does...
    class AnonymousClasses$$anon$1 extends Animal {
      override def eat: Unit = println("ahahahahaha")
    }
    val funnyAnimal = new AnonymousClasses$$anon$1
   */
  println(funnyAnimal.getClass)

  class Person(name: String) {
    def sayHi: Unit = println(s"S'up Gangsta, they call me $name.")
  }
  val jim = new Person("Jim") {
    override def sayHi: Unit = println(s"Um, hello. I'm Jim")
  }

}
