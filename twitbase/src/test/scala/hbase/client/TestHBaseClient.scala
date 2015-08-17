package hbase.client

import org.scalatest.{BeforeAndAfter, FunSuite}

class TestHBaseClient extends FunSuite with BeforeAndAfter {
  after {
     println("Running after")
  }

  test("Hbase Connection") {

  }
}
