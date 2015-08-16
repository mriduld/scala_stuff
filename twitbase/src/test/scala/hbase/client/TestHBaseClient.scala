package hbase.client

import hbase.UserDao
import org.apache.hadoop.hbase.client.{Get, Put}
import org.apache.hadoop.hbase.util.Bytes
import org.apache.hadoop.hbase.util.Bytes.toBytes
import org.scalatest.{BeforeAndAfter, FunSuite}

class TestHBaseClient extends FunSuite with BeforeAndAfter {
  after {
     println("Running after")
  }

  test("Hbase Connection") {

  }

  test("Test Put"){
    UserDao.userTable{table =>
      val put = new Put(toBytes("TheRealMT"))
      put.addColumn(toBytes("info"), toBytes("name"), toBytes("Mark Twain"))
      put.addColumn(toBytes("info"), toBytes("email"), toBytes("samuel@clemens.org"))
      table.put(put)
    }
  }

  test("Test Get"){
    UserDao.userTable{table =>
      val get = new Get(toBytes("TheRealMT"))
      val results = table.get(get)
      val name = Bytes.toString(results.getValue(toBytes("info"), toBytes("name")))
      val email = Bytes.toString(results.getValue(toBytes("info"), toBytes("email")))
      println(s"Name => $name")
      println(s"Email => $email")
    }
  }
}
