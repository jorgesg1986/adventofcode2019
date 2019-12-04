package com.jorgesg1986.adventofcode

import scala.io.Source

object Puzzle2 {

  def main(args: Array[String]): Unit = {

    val initArr: Array[Int] = Source.fromFile("data/puzzle2/input.txt").getLines().next().split(',').map(_.toInt)

    var program = initArr.clone()

    val result = 19690720

    var (verb, noun) = (-1, 0)

    while(program(0) != result && verb <= 99) {
      verb = verb + 1
      noun = 0
      while(program(0) != result && noun <= 99) {
        program = initArr.clone()

        program(1) = verb
        program(2) = noun
        process(program)
        noun = noun + 1
      }
    }

    println(s"$verb ${noun - 1}")

    @scala.annotation.tailrec
    def process(arr: Array[Int]): Unit = {
      arr(0) match {
        case 1 =>
          program(arr(3)) = program(arr(1)) + program(arr(2))
          process(arr.drop(4))
        case 2 =>
          program(arr(3)) = program(arr(1)) * program(arr(2))
          process(arr.drop(4))
        case 99 => ()
        case _ => System.exit(-1)
      }


    }

  }


}
