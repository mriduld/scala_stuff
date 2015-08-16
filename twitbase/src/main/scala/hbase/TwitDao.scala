package hbase

import hbase.HBaseUtils.md5
import hbase.client.HBaseClient._
import model.Twit
import org.apache.hadoop.hbase.client.{Result, Scan, Put, Table}
import org.apache.hadoop.hbase.util.Bytes
import org.apache.hadoop.hbase.util.Bytes.toBytes

trait TwitDao {
  def tweet(user: String, tweet: String): Unit
  def tweets(user: String): List[Twit]
}

object TwitDao extends TwitDao {
  val TABLE = toBytes("twits")
  val TWIT_FAM = toBytes("twits")
  val USER_COL = toBytes("user")
  val DT_COL = toBytes("dt")
  val TWIT_COL = toBytes("twit")

  private def table[T] = using[T, Table](getTable(TABLE))_

   def tweet(user: String, tweet: String): Unit = {
      table { t =>
          val put = new Put(rowKey(user))
          put.addColumn(TWIT_FAM, USER_COL, toBytes(user))
          put.addColumn(TWIT_FAM, DT_COL, toBytes(System.currentTimeMillis()))
          put.addColumn(TWIT_FAM, TWIT_COL, toBytes(tweet))
          t.put(put)
      }
   }


  def tweets(user: String): List[Twit] = {
     val scan = new Scan()
     scan.setRowPrefixFilter(md5(user))
     HBaseUtils.scan[Twit](scan, TABLE, mkTwit)
  }

  private def rowKey(user: String): Array[Byte] = {
       val userHash = md5(user)
       val timeStamp = toBytes(-1 * System.currentTimeMillis())
       Bytes.add(userHash, timeStamp)
  }

  private def mkTwit(r: Result ): Twit = {
    val user = Bytes.toString(r.getValue(TWIT_FAM, USER_COL))
    val date = Bytes.toLong(r.getValue(TWIT_FAM, DT_COL))
    val twit = Bytes.toString(r.getValue(TWIT_FAM, TWIT_COL))
    Twit(user, twit, date)
  }
}
