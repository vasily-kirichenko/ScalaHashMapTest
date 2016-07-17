import scala.collection.mutable
import scala.util.Random

object Utils {
  def time[A](name: String) (f: => Unit) = {
    val s = System.nanoTime
    f
    println(s"$name: ${((System.nanoTime - s) / 1e6).toLong} elapsed.")
  }
}

object Benchmark {
  def run(count: Int = 500000) = {
    val source = Seq.fill(count) {
      Random.nextInt()
    }.toArray
    val m = mutable.HashMap.empty[Int, Int]

    Utils.time("Insertion") {
      for (x <- source) {
        m.put(x, x)
      }
    }

    Utils.time("Lookups") {
      var acc = 0
      for (x <- source)
        acc += m(x) % 10
      acc
    }
  }
}

object Main {
  def main(args: Array[String]): Unit = {
    Benchmark.run()
  }
}

