package lectures.part2op

import java.sql

import playground.{Cinderella, PrinceCharming => Prince}
import java.util.Date
import java.sql.{Date => SqlDate}

object PackagingAndImports extends App {

  // Package members are accessible ba simple name
  val writer = new Writer("Joe", "Czar", 1981)

  // import the package
  val princess = new Cinderella
  // OR no import
  //val princess = new playground.Cinderella // fully qualified name

  // packages are in hierarchy
  // matching folder structure

  // Package object
  // Can only have 1 per package
  // Rarely used but give "Global" access throughout the package
  sayHello
  println(SPEED_OF_LIGHT)

  //imports
  val prince = new Prince
  // 1. Fully QF Names
  //val date = new java.util.Date(2020, 2, 28)
  //val sqlDate = new java.sql.Date(2020, 2, 14)
  // 2. Import under alias
  val date = new Date(2020, 2, 28)
  val sqlDate = new SqlDate(2020, 2, 14)

  println(date)
  println(sqlDate)


}

