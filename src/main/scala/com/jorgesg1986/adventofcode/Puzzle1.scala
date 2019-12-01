package com.jorgesg1986.adventofcode

import java.nio.file.spi.FileSystemProvider
import java.nio.file.{Files, Path}

import scala.io.Source

object Puzzle1 {

  def main(args: Array[String]): Unit = {
    val iterLines = Source.fromFile("data/puzzle1/input.txt").getLines()

    val fuelRequired = iterLines.foldLeft(0){ (acc, mass) =>
      val fuelMass = (mass.toInt / 3 - 2)
      acc + calculateFuelMass(fuelMass, fuelMass)
    }
    println(fuelRequired)
//    println(calculateFuelMass(fuelRequired, fuelRequired))
  }

  @scala.annotation.tailrec
  def calculateFuelMass(fuel: Int, fuelMass: Int): Int = {

    val mass = (fuel / 3 - 2)
    val total = fuelMass + mass
    println(s"Fuel: $fuel Mass: $mass Total: $total")
    if (mass > 0)
      calculateFuelMass(mass, fuelMass + mass)
    else
      fuelMass
  }
}
