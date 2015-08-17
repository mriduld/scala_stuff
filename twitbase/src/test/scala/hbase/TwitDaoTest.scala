package hbase

import org.scalamock.scalatest.MockFactory
import org.scalatest.{DoNotDiscover, FunSuite}

@DoNotDiscover
class TwitDaoTest extends FunSuite with MockFactory {
  test("Actual Put to Hbase") {
    //TwitDao.tweet("TheRealMT", "Hello TwitBase !!")
  }

  test("Scan Twits for user") {
    TwitDao.tweets("TheRealMT").foreach(println)
  }
}
