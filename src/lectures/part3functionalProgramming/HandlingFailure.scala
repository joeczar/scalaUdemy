package lectures.part3functionalProgramming

import scala.util.{Failure, Random, Success, Try}

object HandlingFailure extends App {

  // create a success and a failure
  val aSuccess = Success(3)
  val aFailure = Failure(new RuntimeException("Eat my shorts, man!"))
  println(aSuccess)
  println(aFailure)

  // Try Objects
  def unsafeMethod(): String = throw new RuntimeException(" ╭∩╮(°͜ʖ͡°) ")
  val potentialFailure = Try(unsafeMethod())
  println(potentialFailure)

  // syntax sugar
  val anotherPotentialFailure = Try {
    // Stuff that might go wrong
  }
  // utilities
  println(potentialFailure.isSuccess)

  // orElse
  def backupMethod(): String = "That went well"
  val fallbackTry = Try(unsafeMethod()).orElse(Try(backupMethod()))
  println(fallbackTry)

  // building safer APIs
  def betterUnsafeMethod(): Try[String] = Failure(new RuntimeException(" ╭∩╮ʕ•ᴥ•ʔ╭∩╮ "))
  def betterBackupMethod(): Try[String] = Success("Ya gon dun it, Tiger!")
  val betterFallback = betterUnsafeMethod() orElse betterBackupMethod()
  println(betterFallback)

  // map, flatMap, filter
  println(aSuccess.map(_ * 2))
  println(aSuccess.flatMap(x => Success(x * 10)))
  println(aSuccess.filter(_ > 10))

  // for comprehension
  /*
  Exersise
   */
  val hostname = "RPI.local"
  val port = "8080"
  def renderHTML(page: String) = println(page)

  class Connection{
    def get(url: String): String = {
      val random = new Random(System.nanoTime())
      if (random.nextBoolean()) "<html>.......</html>"
      else throw new RuntimeException(" ┌∩┐(◕◡◉)┌∩┐ connection interrupted")
    }
    // part of course solution
    def getSafe(url: String): Try[String] = Try(get(url))
  }
  object HttpService {
    val random = new Random(System.nanoTime())

    def getConnection(host: String, port: String): Connection =
      if (random.nextBoolean()) new Connection
      else throw new RuntimeException("HAha, ┬┴┬┴┤ ͜ʖ ͡°) ╭∩╮ \n someone else took that port")
    // part of course solution
    def getSafeConnection(host: String, port: String): Try[Connection] = Try(getConnection(host, port))
  }
  // my solution
  val httpConnection = for {
    hostname <- Try(hostname)
    port <- Try(port)
    connection <- Try(HttpService.getConnection(hostname, port))
  } yield connection.get("www.google.com")

  println(httpConnection)

  // course solution
  val possibleSolution = HttpService.getSafeConnection(hostname, port)
  val possibleHTML = possibleSolution.flatMap(connection => connection.getSafe("/home"))
  possibleHTML.foreach(renderHTML)

  val chainedSolution: Unit = HttpService.getSafeConnection(hostname, port)
    .flatMap(c => c.getSafe("/home"))
    .foreach(renderHTML)

  println(chainedSolution)

  val forSolution = for {
    connection <- HttpService.getSafeConnection(hostname, port)
    html <- connection.getSafe("/home")
  } renderHTML(html)

  println(forSolution)
}
