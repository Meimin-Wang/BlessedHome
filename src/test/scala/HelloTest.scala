import java.io.InputStream

import breeze.linalg.{DenseMatrix, DenseVector}

import scala.collection.mutable.ArrayBuffer

object HelloTest extends App {
  val zeroVector = DenseVector.zeros[Double](5)
  val onesVector = DenseVector.ones[Double](10)
  println(zeroVector)
  println(onesVector)

  val m = DenseMatrix.zeros[Double](3, 5)
  println(m)
}