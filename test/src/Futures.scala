import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Random, Success}

object Futures {
  def run(): Unit = {
    val rnd = new Random()
    val futures =
      (1 to 5) foreach { i =>
        val future = Future {
          Thread.sleep(rnd nextInt 100)
          if (i == 3) throw new Exception("Wow!")
          else println(s"Future #$i finished"); 22
        }
        future andThen {
          case Success(x) => println(s"andThen: x = $x")
          case Failure(e) => println(s"andThen: x = $e")
        }
        future onSuccess { case _ => println("Success") }
        future onFailure { case e => println(s"Failure: $e") }
      }
  }
}