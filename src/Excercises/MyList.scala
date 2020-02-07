package Excercises

abstract class MyList[+A] {
  /*
 1. Head - returns the first element of the list
 2. tail = the remainder of the list
 3. isEmpty = boolean is the list empty
 4. add(int) = new list with this element added
 5. toString = a string representation of this list
 */

  def head: A
  def tail: MyList[A]
  def isEmpty: Boolean
  def add[B >: A](element: B): MyList[B]
  def printElements: String
  // Polymorphic call
  override def toString: String = "[" + printElements + "]"
}

object Empty extends MyList[Nothing] {
  def head: Nothing = throw new NoSuchElementException
  def tail: MyList[Nothing] = throw new NoSuchElementException
  def isEmpty: Boolean = true
  def add[B >: Nothing](element: B): MyList[B] = new Cons(element, Empty)
  def printElements = ""
}

class Cons[+A](h: A, t: MyList[A]) extends MyList[A] {
  def head: A = h
  def tail: MyList[A] = t
  def isEmpty: Boolean = false
  def add[B >: A](element: B): MyList[B] = new Cons(element, this)
  def printElements: String =
    if (t.isEmpty) "" + h
    else h + " " + t.printElements
}
object ListTest extends App {
  val intList = new Cons(1, new Cons(2, new Cons(3, Empty)))
  val stringList = new Cons("Hello", new Cons("Scala", Empty))
  val mixList = new Cons(42, new Cons("Is the answer", new Cons(2, new Cons("Life, the Universe and everything.", Empty))))
  println(intList.toString)
  println(stringList.toString)
  println(mixList.toString)
}
