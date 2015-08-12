package hbase.client

import hbase.client.HBaseClient._
import org.scalatest.{BeforeAndAfter, FunSpec}

class TestHBaseClient extends FunSpec with BeforeAndAfter {
  after {
     close()
  }

  describe ("HbaseConnection") {
    it("should connect and close Hbase") {
        withTable("users"){table =>
           println(s"Got table $table")
        }
    }
  }
}
