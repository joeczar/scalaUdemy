package lectures.part3functionalProgramming

object TuplesAndMaps extends App {

  // tuples - finite ordered "lists"
  val aTuple = (2, "Gangsta!") // type Tuple2[Int, String] == (Int, String)
  println(aTuple._2)
  println(aTuple.copy(_2 = "Yo gon die."))
  println(aTuple.swap)

  // Maps = keys -> values
  val aMap: Map[String, Int] = Map()
  val phonebook = Map(("Jim", 555), "Daniel"-> 987, "JIM"->321 ).withDefaultValue("╭∩╮（︶︿︶）╭∩╮")
  // a -> b == (a, b)
  println(phonebook)

  // map ops
  println(phonebook.contains("John")) // returns boolean
  println(phonebook("Jim")) // returns value
  println(phonebook("Mary")) // throws an error if not treated with .withDefaultValue(-1)

  val newPairing = "Mary" -> 493
  val newPhonebook = phonebook + newPairing
  println(newPhonebook)

  // functionals on maps
  // map, flatMap, filter
  println("#######################################")
  println(newPhonebook.map(pair => pair._1.toLowerCase -> pair._2))

  // filterKeys
  println(newPhonebook.view.filterKeys(x => x.startsWith("J")).toMap)
  // mapvalues
  println(newPhonebook.view.mapValues(number => "666-" + number).toMap)
  println(newPhonebook.toList)
  println(List(("Daniel", 523)).toMap)
  val names = List("Bob", "James", "Michael", "Jozef", "Ronja", "Angela", "Mary", "Daniel", "Jim", "Berthould",
    "Bartholemeu", "Missy", "Agnes")
  println(names.groupBy(name => name.charAt(0)))

  // 1. What would happen with two original entries eg. "Jim" -> 321 and "JIM" -> 444 if we run .toLowercase on the
  // tupple... One of them gets dropped because the keys overlap

  def add(network: Map[String, Set[String]], person: String): Map[String, Set[String]] = {
    network + (person -> Set())
  }
  def friend(network: Map[String, Set[String]], a: String, b: String): Map[String, Set[String]] = {
    val friendsA = network(a)
    val friendsB = network(b)

    network + (a -> (friendsA + b)) + (b -> (friendsB + a))
  }
  def unfriend(network: Map[String, Set[String]], a: String, b: String): Map[String, Set[String]] = {
    val friendsA = network(a)
    val friendsB = network(b)

    network + (a -> (friendsA - b)) + (b-> (friendsB - a))
  }
  def delete(network: Map[String, Set[String]], person: String): Map[String, Set[String]] = {
    @scala.annotation.tailrec
    def removeAux(friends: Set[String], networkAcc: Map[String, Set[String]]): Map[String, Set[String]] = {
      if (friends.isEmpty) networkAcc
      else removeAux(friends.tail, unfriend(networkAcc, person, friends.head))
    }
    val unfriended = removeAux(network(person), network)
    unfriended - person
  }

  val empty: Map[String, Set[String]] = Map()
  val network = add(add(empty, "Bob"),"Mary")
  println(network)
  println(friend(network, "Bob", "Mary"))
  println(unfriend(friend(network, "Bob", "Mary"), "Bob", "Mary"))
  println(delete(friend(network, "Bob", "Mary"), "Bob"))

  // Jim, Bob, Mary

  val people =  add(add(add(empty, "Bob"),"Mary"), "Jim")
  val jimBob = friend(people, "Bob", "Jim")
  val testNet = friend(jimBob, "Bob", "Mary")
  println(testNet)

  def nFriends(network: Map[String, Set[String]], person: String): Int = {
    if(!network.contains(person)) 0
    else network(person).size
  }
  println(nFriends(testNet, "Bob"))

  def mostFriends(network: Map[String, Set[String]]): String =
    network.maxBy(pair => pair._2.size)._1

  println(mostFriends(testNet))

  def nPeopleWithNoFriends(network: Map[String, Set[String]]): Int =
    network.count(_._2.isEmpty)

  println(nPeopleWithNoFriends(testNet))

  def socialConnection(network: Map[String, Set[String]], a: String, b: String): Boolean = {
    @scala.annotation.tailrec
    def bfs(target: String, consideredPeople: Set[String], discoveredPeople: Set[String]): Boolean = {
      if (discoveredPeople.isEmpty) false
      else {
        val person = discoveredPeople.head
        if (person == target) true
        else if(consideredPeople.contains(person)) bfs(target, consideredPeople, discoveredPeople.tail)
        else bfs(target, consideredPeople + person, discoveredPeople ++ network(person))
      }
    }
    bfs(b, Set(), network(a) + a)
  }
  println(socialConnection(testNet, "Mary", "Jim"))
  println(socialConnection(network, "Mary", "Bob"))
}
