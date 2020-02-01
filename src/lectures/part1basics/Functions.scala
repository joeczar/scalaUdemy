package lectures.part1basics

object Functions extends App {

  def aFunction(a: String, b: Int): String = {
    a + " " + b
  }

  println(aFunction("Hello", 666))

  def aParameterLessFunction(): Int = 42
  println(aParameterLessFunction())
  println(aParameterLessFunction) // both are valid


  def aRepeatedFunction(aString: String, n: Int): String = {
    if (n == 1) aString
    else aString + aRepeatedFunction(aString, n-1)
  }
  print(aRepeatedFunction("Giggedy, ", 66))

  // Use RECURSION INSTEAD OF LOOOOOOOOPS!!!!

  def aFunctionWithSideEffects(aString: String): Unit = println(aString)

  def aBigFunction(n: Int): Int = {
    def aSmallerFunction(a: Int, b: Int): Int = a + b

    aSmallerFunction(n, n-1) // This determines the return type
  }

  /*
    1. A greeting function for kids (name, age) => "Hi, my name is $name and I am $age years old."
    2. Factorial function 1 * 2 * 3 * 4 * ... *n
    3. A fibinacci function (recursive)
        f(1) = 1
        f(2) = 1
        f(n) = f(n-1) + f(n -2)
    4. Tests if number is Prime
     */
  def greetMe(name: String, age: Int): String = {
    s"Sup bitches, they call me $name, I have reached the ripe old age of $age."
  }
  println(greetMe(name = "Joe", age = 39))

  def factorial(x:Int): Int ={
    if (x == 0) 1
    else x * factorial(x -1)
  }
  println("Factorial", factorial(5))

  def fibonacci(x: Int): Int = {
    if (x <= 2)  1
    else fibonacci(x - 1) + fibonacci(x - 2)
  }
  println(fibonacci(10))

  def isPrime(x: Int): Boolean = {
    def isPrimeUntil(t: Int): Boolean =
      if (t <= 1) true
      else x % t != 0 && isPrime(t - 1)
    isPrimeUntil(x / 2)
    }
  println(isPrime(37))
  println(isPrime(2003))
  println(isPrime(37 * 17))

}
