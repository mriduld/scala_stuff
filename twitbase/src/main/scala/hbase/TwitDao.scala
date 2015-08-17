package hbase

import java.lang.System.currentTimeMillis

import hbase.HBaseUtils.md5
import model.Twit
import org.apache.hadoop.hbase.client.{Result, Put}
import org.apache.hadoop.hbase.util.Bytes
import org.apache.hadoop.hbase.util.Bytes.{add, toBytes}

trait TwitDao {
  def tweet(user: String, tweet: String): Unit
  def tweets(user: String): List[Twit]
}

object TwitDao extends AbstractDao[Twit] with TwitDao {
  val tableName = Bytes.toBytes("twits")

  val TWIT_FAM = toBytes("twits")
  val USER_COL = toBytes("user")
  val DT_COL = toBytes("dt")
  val TWIT_COL = toBytes("twit")

   def tweet(user: String, tweet: String): Unit = put {
     new Put(rowKey(user))
       .add(TWIT_FAM, USER_COL, toBytes(user))
       .add(TWIT_FAM, DT_COL, toBytes(currentTimeMillis()))
       .add(TWIT_FAM, TWIT_COL, toBytes(tweet))
   }

  def tweets(user: String): List[Twit] = getAll(md5(user), mkTwit)


  private def rowKey(user: String): Array[Byte] = add(md5(user), toBytes(-1 * currentTimeMillis()))

  def mkTwit(r: Result ): Twit = {
    import hbase.client.HBaseClient._
    val user = r.getValue(TWIT_FAM, USER_COL)
    val date = r.getValue(TWIT_FAM, DT_COL)
    val twit = r.getValue(TWIT_FAM, TWIT_COL)
    Twit(user, twit, date)
  }
}
