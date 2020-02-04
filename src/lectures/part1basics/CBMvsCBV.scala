package lectures.part1basics

object CBMvsCBV extends App {

  def calledByValue(x: Long): Unit = {
    println("By value: " + x)
    println("By value: " + x)
  }
  def calledByName(x: => Long): Unit = {
    println("By name: " + x)
    println("By name: " + x)
  }
  calledByValue(System.nanoTime())
  calledByName(System.nanoTime())

  def infiniteCrash(): Int = 1 + infiniteCrash()
  def printFirst(x: Int, y: => Int): Unit = println(x)

  // printFirst(infiniteCrash(), 34)
  printFirst(34, infiniteCrash())

}
