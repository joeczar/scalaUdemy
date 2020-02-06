package lectures.part1basics

object stringOps extends App {

    val str: String = "Hello, I am learning Scala."

    // From Java
    println(str.charAt(2))
    println(str.substring(7, 11))
    println(str.split(" ").toList)
    println(str.startsWith("Hello"))
    println(str.replace(" ", "-"))
    println(str.toLowerCase())
    println(str.length)

    // Scala Specific
    val numberString: String = "42"
    val number: Int = numberString.toInt
    println('a' +: numberString :+ 'z')
    println(str.reverse)
    println(str.take(2)) // takes the first two characters of the string

    // S-Interpolators
    val name = "David"
    val age = 12
    val greeting = s"Hello, my name is $name, I am $age years old"
    val anotherGreeting = s"Hello, my name is $name, I will be ${age + 1} years old"
    println(anotherGreeting)

    // F-Interpolators
    val speed = 1.2f
    val myth = f"$name can eat $speed%2.2f burgers a minute."

    // Raw-Interpolator
    println(raw"This is a \n new line.")
    val escaped = "This is a \n new line."
    println(raw"$escaped")

}