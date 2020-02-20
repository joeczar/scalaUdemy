package lectures.part3functionalProgramming

import scala.util.Random

object Options extends App {

  val myFirstOption: Option[Int]  = Some(4)
  val noOption: Option[Int] = None

  println(myFirstOption)

  // Work with unsafe APIs
  def unsafeMethod(): String = null
  //val result = Some(unsafeMethod()) // WRONG!!!
  val result = Option(unsafeMethod())
  println(result)

  def backupMethod(): String = "a valid result"
  val chainedResult = Option(unsafeMethod()).orElse(Option(backupMethod()))

  // Design SAFE APIs
  def betterUnsafeMethod(): Option[String] = None
  def betterBackupMethod(): Option[String] = Some("A valid result.")
  val betterChainedResult = betterUnsafeMethod() orElse betterBackupMethod()

  // Functions as options
  println(myFirstOption.isEmpty)
  println(myFirstOption.get) // UNSAFE!!!! Do not use .get!!!!

  // map, flatMap & filter
  println(myFirstOption.map(_*2))
  println(myFirstOption.filter(_>10))
  println(myFirstOption.flatMap(x => Option(x * 10)))

  // for comprehensions
  /*
    Exercises:
   */
  val config: Map[String, String] = Map(
    // fetched from elsewhere
    "host" -> "19.2168.1.121",
    "port" -> "80"
  )

  class Connection {
    def connect = "connected"
  }
  object Connection {
    val random = new Random(System.nanoTime())

    def apply(host: String, port: String): Option[Connection] =
      if (random.nextBoolean()) Some(new Connection)
      else None
  }

  val host = config.get("host")
  val port = config.get("port")
  /*
    if (h != null)
      if (p != null)
        return Connection(h, p)

    return null
   */
  val connection = host.flatMap(h => port.flatMap(p => Connection(h,p)))
  /*
    if (c != null)
      return c.connect
    return null
   */
  val connectionStatus = connection.map(c => c.connect)
  // if (connectionStatus == null) println(None) else println(Some(connectionStatus.get))
  println(connectionStatus)
  /*
    if (status != null)
      ptintln(status
   */
  connectionStatus.foreach(println)

  //println(host, port)
  // chained calls!
  config.get("host")
    .flatMap(host => config.get("port")
      .flatMap(port => Connection(host, port))
      .map(connection => connection.connect))
    .foreach(println)

  // for comprehensions
  val forConnectionStatus = for {
    host <- config.get("host")
    port <- config.get("port")
    connection <- Connection(host, port)
  } yield connection.connect
  forConnectionStatus.foreach(println)

}
