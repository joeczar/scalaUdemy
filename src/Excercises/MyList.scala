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

  def map[B](transformer: MyTransformer[A, B]): MyList[B]
  def flatMap[B](transformer: MyTransformer[A, MyList[B]]): MyList[B]
  def filter(predicate: MyPredicate[A]): MyList[A]
  // Concatenation
  def ++[B >: A](list: MyList[B]): MyList[B]
}

case object Empty extends MyList[Nothing] {
  def head: Nothing = throw new NoSuchElementException
  def tail: MyList[Nothing] = throw new NoSuchElementException
  def isEmpty: Boolean = true
  def add[B >: Nothing](element: B): MyList[B] = new Cons(element, Empty)
  def printElements = ""

  def map[B](transformer: MyTransformer[Nothing, B]): MyList[B] = Empty
  def flatMap[B](transformer: MyTransformer[Nothing, MyList[B]]): MyList[B] = Empty
  def filter(predicate: MyPredicate[Nothing]): MyList[Nothing] = Empty
  def ++[B >: Nothing](list: MyList[B]): MyList[B] = list
}

case class Cons[+A](h: A, t: MyList[A]) extends MyList[A] {
  def head: A = h
  def tail: MyList[A] = t
  def isEmpty: Boolean = false
  def add[B >: A](element: B): MyList[B] = new Cons(element, this)
  def printElements: String =
    if (t.isEmpty) "" + h
    else h + " " + t.printElements


  //def flatMap[B](transformer: MyTransformer[A, MyList[B]]): MyList[B]
  def filter(predicate: MyPredicate[A]): MyList[A] =
    if(predicate.test(h)) new Cons(h, t.filter(predicate))
    else t.filter(predicate)
  /*
    [1, 2, 3].filter(n % 2 == 0) =
       [2, 3].filter(n % 2 == 0) =
       = new Cons(2, [3].filter(n % 2 == 0))
       = new Cons(2, Empty.filter(n % 2 == 0))
       = new Cons(2, Empty)
   */

  def map[B](transformer: MyTransformer[A, B]): MyList[B] =
    new Cons(transformer.transform(h), t.map(transformer))
  /*
    [1, 2, 3].map(n * 2)
      = new Cons(2, [2,3].map(n*2))
      = new Cons(2, new Cons(4, [3].map(n*2)))
      = new Cons(2, new Cons(4, new Cons(6, Empty)))
   */
  def ++[B >: A](list: MyList[B]): MyList[B] = new Cons(h, t ++ list)
  /*
    [1, 2] ++ [3, 4, 5] =
    = new Cons(1, [2] ++ [3, 4, 5])
    = new Cons(1, new Cons(2, Empty ++ [3, 4, 5]))
    = new Cons(1, new Cons(2, new Cons(3, new Cons(4, new Cons(5, Empty))))
   */
  def flatMap[B](transformer: MyTransformer[A, MyList[B]]): MyList[B] = {
    transformer.transform(h) ++ t.flatMap(transformer)
  }
  /*
    [1,2].flatMap(n => n+1)
    = [1,2] ++ [2].flatMap(n => n+1)
    = [1,2] ++ [2,3] ++ Empty.flatMap(n => n+1)
    = [1,2] ** [2,3] ++ Empty
    = [1,2,2,3]
   */

}
/*
1. Generic Trait MyPredicate[-T] with method test(T) => Boolean
2. Generic trait MyTransformer[-A, B]
3. MyList
    - map(transformer) => MyList
    - filter(predicate) => MyList
    - flatMap(transformer from [A] to MyList[B]) => MyList[B]
 */
trait MyPredicate[-T] {
  def test(element: T): Boolean
}
trait MyTransformer[-A, B] {
  def transform(element: A): B
}

object ListTest extends App {
  val intList = new Cons(1, new Cons(2, new Cons(3, Empty)))
  val clonedIntList = new Cons(1, new Cons(2, new Cons(3, Empty)))
  val anothaIntList = new Cons(4, new Cons(5, Empty))
  val stringList = new Cons("Hello", new Cons("Scala", Empty))
  val mixList = new Cons(42, new Cons("Is the answer", new Cons(2, new Cons("Life, the Universe and everything.", Empty))))
  println(intList.toString)
  println(stringList.toString)
  println(mixList.toString)

  println(intList.map(new MyTransformer[Int, Int] {
    override def transform(element: Int): Int = element * 2
  }).toString)

  println(intList.filter(new MyPredicate[Int] {
    override def test(element: Int): Boolean = element % 2 == 0
  }).toString)

  println(intList ++ anothaIntList)

  println(intList.flatMap(new MyTransformer[Int, MyList[Int]] {
    override def transform(element: Int): MyList[Int] = new Cons(element, new Cons(element +1, Empty))
  }).toString)

  // Because of case classes & Objects
  println(intList == clonedIntList)
}