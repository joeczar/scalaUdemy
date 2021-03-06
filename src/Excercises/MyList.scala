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

  def map[B](transformer: A => B): MyList[B]
  def flatMap[B](transformer: A => MyList[B]): MyList[B]
  def filter(predicate: A => Boolean): MyList[A]
  // Concatenation
  def ++[B >: A](list: MyList[B]): MyList[B]
  def fold[B](start: B)(operator: (B, A) => B): B

  // HOFs
  def forEach(f: A => Unit): Unit
  def sort(compare: (A, A) => Int): MyList[A]
  def zipWith[B, C](list: MyList[B], zip:(A,B) => C): MyList[C]
}

case object Empty extends MyList[Nothing] {
  def head: Nothing = throw new NoSuchElementException
  def tail: MyList[Nothing] = throw new NoSuchElementException
  def isEmpty: Boolean = true
  def add[B >: Nothing](element: B): MyList[B] = new Cons(element, Empty)
  def printElements = ""

  def map[B](transformer: Nothing => B): MyList[B] = Empty
  def flatMap[B](transformer: Nothing =>MyList[B]): MyList[B] = Empty
  def filter(predicate: Nothing => Boolean): MyList[Nothing] = Empty
  def ++[B >: Nothing](list: MyList[B]): MyList[B] = list

  // HOFs
  def forEach(f: Nothing => Unit): Unit = ()
  def sort(compare: (Nothing, Nothing) => Int) = Empty
  def zipWith[B, C](list: MyList[B], zip:(Nothing,B) => C): MyList[C] =
    if(!list.isEmpty) throw new RuntimeException("Lists do not have the same length")
    else Empty
  def fold[B](start: B)(operator: (B, Nothing) => B): B = start
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
  def filter(predicate: A => Boolean): MyList[A] =
    if(predicate(h)) new Cons(h, t.filter(predicate))
    else t.filter(predicate)
  /*
    [1, 2, 3].filter(n % 2 == 0) =
       [2, 3].filter(n % 2 == 0) =
       = new Cons(2, [3].filter(n % 2 == 0))
       = new Cons(2, Empty.filter(n % 2 == 0))
       = new Cons(2, Empty)
   */

  def map[B](transformer: A => B): MyList[B] =
    new Cons(transformer(h), t.map(transformer))
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
  def flatMap[B](transformer: A => MyList[B]): MyList[B] = {
    transformer(h) ++ t.flatMap(transformer)
  }
  /*
    [1,2].flatMap(n => n+1)
    = [1,2] ++ [2].flatMap(n => n+1)
    = [1,2] ++ [2,3] ++ Empty.flatMap(n => n+1)
    = [1,2] ** [2,3] ++ Empty
    = [1,2,2,3]
   */
// HOFs
  def forEach(f: A => Unit): Unit = {
    f(h)
    t.forEach(f)
  }
  def sort(compare: (A, A) => Int): MyList[A] = {
    def insert(x: A, sortedList: MyList[A]): MyList[A] =
      if (sortedList.isEmpty) new Cons(x, Empty)
      else if (compare(x, sortedList.head) <= 0) new Cons(x, sortedList)
      else new Cons(sortedList.head, insert(x, sortedList.tail))
    val sortedTail = t.sort(compare)
    insert(h, sortedTail)
  }
  def zipWith[B, C](list: MyList[B], zip:(A,B) => C): MyList[C] =
    if (list.isEmpty) throw new RuntimeException("Lists do not have the same length")
    else new Cons(zip(h, list.head), t.zipWith(list.tail, zip))
  /*
    [1,2,3].fold(0)(+) =
    = [2,3].fold(0+1)(+)
    = [3].fold(1+2)(+)
    = [].fold(3+3)(+)
    = 6
   */
  def fold[B](start: B)(operator: (B, A) => B): B =
    t.fold(operator(start, h))(operator)

}
/*
1. Generic Trait MyPredicate[-T] with method test(T) => Boolean
2. Generic trait MyTransformer[-A, B]
3. MyList
    - map(transformer) => MyList
    - filter(predicate) => MyList
    - flatMap(transformer from [A] to MyList[B]) => MyList[B]
 */

object ListTest extends App {
  val intList = new Cons(1, new Cons(2, new Cons(3, Empty)))
  val clonedIntList = new Cons(1, new Cons(2, new Cons(3, Empty)))
  val anothaIntList = new Cons(4, new Cons(5, Empty))
  val stringList = new Cons("Hello", new Cons("Scala", Empty))
  val mixList = new Cons(42, new Cons("Is the answer", new Cons(2, new Cons("Life, the Universe and everything.", Empty))))
  println(intList.toString)
  println(stringList.toString)
  println(mixList.toString)

  /*  The Long syntax

  println(intList.map(new Function1[Int, Int] {
    override def apply(element: Int): Int = element * 2
  }).toString)

  println(intList.filter(new Function1[Int, Boolean] {
    override def apply(element: Int): Boolean = element % 2 == 0
  }).toString)

  println(intList ++ anothaIntList)

  println(intList.flatMap(new Function1[Int, MyList[Int]] {
    override def apply(element: Int): MyList[Int] = new Cons(element, new Cons(element +1, Empty))
  }).toString)
   */
  println(intList.map(_ * 2).toString)

  println(intList.filter(_ % 2 == 0).toString)

  println(intList ++ anothaIntList)

  println(intList.flatMap((element: Int) => new Cons(element, new Cons(element + 1, Empty))).toString)

  // Because of case classes & Objects
  println(intList == clonedIntList)
  intList.forEach(print)
  // Sort
  println(intList.sort((x,y)=> y-x))
  // zipWith
  val zipped = anothaIntList.zipWith[String, String](stringList, _ + "|" + _)
  println(zipped)
  // fold
  println(stringList.fold("")(_+_))

  // for comprehension
  val forComprehension = for {
    n <- intList
    s <- stringList
  } yield s"$n-$s"
  println(forComprehension)
}