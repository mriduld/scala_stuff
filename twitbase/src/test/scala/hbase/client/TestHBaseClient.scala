package hbase.client

import hbase.client.HBaseClient.{close, getTable}
import org.scalatest.{BeforeAndAfter, FunSpec}
import scala.util.control.Exception._

class TestHBaseClient extends FunSpec with BeforeAndAfter {
  after {
     close()
  }

  describe ("HbaseConnection") {
    it("should connect and close Hbase") {
        val table = getTable("users")
        nonFatalCatch.andFinally
                    {
                      println("Closing Table")
                      table.close()
                    }
                     {
                       println("Got table... "+ table)
                     }
    }
  }
}
