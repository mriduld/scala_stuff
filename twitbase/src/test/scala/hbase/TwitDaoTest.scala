package hbase

import org.scalamock.scalatest.MockFactory
import org.scalatest.FunSuite


class TwitDaoTest extends FunSuite with MockFactory {
  test("Actual Put to Hbase") {
    //TwitDao.tweet("TheRealMT", "This is my second!!")
  }

  test("Scan Twits for user") {
    TwitDao.tweets("TheRealMT").foreach(println)
  }
}
