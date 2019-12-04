package com.jorgesg1986.adventofcode

import scala.io.Source

object Puzzle3 {

  type Position = (Int, Int)

  def main(args: Array[String]): Unit = {

    val source = Source.fromFile("data/puzzle3/input.txt").getLines()
    val wireOne: Array[String] = source.next().split(',')
//    val wireOne: Array[String] = "R8,U5,L5,D3".split(',')
    val wireTwo: Array[String] = source.next().split(',')
//    val wireTwo: Array[String] = "U7,R6,D4,L4".split(',')

    val wireOnePositions = getPositions(wireOne)
    val wireTwoPositions = getPositions(wireTwo)

    val samePositions: Set[Position] = wireOnePositions.intersect(wireTwoPositions)

    val min = getMinimumDistance(samePositions.-((0,0)))

    println(min)
  }

  def getMinimumDistance(points: Set[Position]): Int = {
    points.foldLeft(Integer.MAX_VALUE){ (minimum, pos) =>
      val distance = Math.abs(pos._1) + Math.abs(pos._2)
      if(distance < minimum) distance
      else minimum
    }
  }

  def getPositions(movements: Array[String], positions: List[Position] = List.empty[Position]): Set[Position] = {
    movements.foldLeft((Set.empty[Position], (0, 0))){ (positions, movement) =>
      val move = movement.substring(1).toInt
      movement.charAt(0) match {
        case 'U' =>
          val (x, y) = positions._2
          val nextMoves = ((y + 1) to (y + move)).map((x, _))
          (positions._1 ++ nextMoves, nextMoves.last) // Review TO or UNTIL
        case 'D' =>
          val (x, y) = positions._2
          val nextMoves = ((y - 1) to (y - move)).by(-1).map((x, _))
          (positions._1 ++ nextMoves, nextMoves.last)
        case 'L' =>
          val (x, y) = positions._2
          val nextMoves = ((x - 1) to (x - move)).by(-1).map((_, y))
          (positions._1 ++ nextMoves, nextMoves.last)
        case 'R' =>
          val (x, y) = positions._2
          val nextMoves = ((x + 1) to (x + move)).map((_, y))
          (positions._1 ++ nextMoves, nextMoves.last)
      }
    }._1
  }


}
