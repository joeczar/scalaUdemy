package lectures.part4pm

import Excercises.{Cons, Empty, MyList}

object AllThePatterns extends App {

  // 1- constants
  val x: Any = "Scala"
  val constants = x match {
    case 1  => "a number"
    case "Scala" => "THE Scala"
    case true => "The truth"
    case AllThePatterns => "A singleton object"
  }

  // 2 match anything
  // 2.1 wildcard
  val matchAnything = x match {
    case _ => "Anything"
  }

  //2.2 variable
  val matchAVariable = x match {
    case something => s"I've found $x"
  }

  //3 tuples
  val aTuple = (1,2)

  val matchATuple = aTuple match {
    case (1,1) => "nope!"
    case (something, 2) => s"I've found $something"
  }

  val nestedTuple = (1, (2, 3))
  val matchNestedTuple = nestedTuple match {
    case (_, (2, v)) => s"I got $v"
  }
  // PMs can be nested
  // 4 - case classes - constructor pattern
  // PM's can be nested with CCs as well
  val aList: MyList[Int] = Cons(1, Cons(2, Empty))
  val matchAList = aList match {
    case Empty => "Nothing here."
    case Cons(head, Cons(subhead, subtail)) => s"$head, $subhead, $subtail"
  }

  // 5 list patterns
  val standardList = List(1,2,3,4,42)
  val StandardListMatching = standardList match {
    case List(1, _, _, _) => "List starts with 1 and has 3 more Ints"
    case List(1, _*) => "List starts with one and has at least one more Int"
    case 1 :: List(_) => "That matched an 'Infix' pattern"
    case List(1,2,3) :+ 42 => "Another infix pattern"
  }
  //7 Name binding
  val nameBindingMatch = aList match {
    case nonEmptyList @ Cons(_,_) => s"this is a $nonEmptyList"
    case Cons(1, rest @ Cons(2, _)) => s"name binding $rest inside nested patterns"
  }
  // Multipatterns
  val multiPattern = aList match {
    case Empty | Cons(0, _) => "compound pattern (multi-pattern)"
  }

  // ) if guards
  val secondElementSpecial = aList match {
    case Cons(_, Cons(specialElement, _)) if specialElement % 2 == 0 => "It's even!"
  }
  // All
  val numbers = List(1,2,3)
  val trickQuestion = numbers match {
    case listOfStrings: List[String] => "a list of strings" // type erasure. the JVM erases the types after List. This will match
    case ListOfNumbers: List[Int] => "a list of numbers"
  }
}
