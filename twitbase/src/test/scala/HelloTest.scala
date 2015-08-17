import org.scalatest.{FunSpec, Matchers}

class HelloTest extends FunSpec with Matchers{
  describe ("Addition") {
     it("should add 2 numbers") {
         val x = 10
         val y = 3
         (x+y) should be (13)
     }
  }

  describe("Subtraction")  {
    it("should subtract 2 numbers") {
      val x = 10
      val y = 3
      (x-y) should be (7)
    }
  }
}
