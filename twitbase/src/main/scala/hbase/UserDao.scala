package hbase

import model.User
import org.apache.hadoop.hbase.client._
import org.apache.hadoop.hbase.util.Bytes

trait UserDao {
  def addUser(id: String, name: String, email: String, password: String): Unit
  def getUser(id: String): Option[User]
  def deleteUser(id: String): Unit
  def getUsers: List[User]
}

object UserDao extends AbstractDao[User] with UserDao {
   val tableName = Bytes.toBytes("users")
   val INFO_FAM = Bytes.toBytes("info")
   val ID_COL = Bytes.toBytes("id")
   val NAME_COL = Bytes.toBytes("name")
   val EMAIL_COL = Bytes.toBytes("email")
   val PASSWORD_COL = Bytes.toBytes("password")
   val TWEETS_COL = Bytes.toBytes("tweets_count")

   def addUser(id: String, name: String, email: String, password: String): Unit =
     put {
       new Put(Bytes.toBytes(id))
         .add(INFO_FAM, ID_COL, Bytes.toBytes(id))
         .add(INFO_FAM, NAME_COL, Bytes.toBytes(name))
         .add(INFO_FAM, PASSWORD_COL, Bytes.toBytes(password))
     }

  def deleteUser(id: String): Unit = delete(Bytes.toBytes(id))

  def getUser(id: String): Option[User] = get(Bytes.toBytes(id), mkUser)

  def getUsers: List[User] = scanRange(fn = mkUser)

  def mkUser(r: Result ): User = {
     import hbase.client.HBaseClient._
     val id = r.getValue(INFO_FAM, ID_COL)
     val name = r.getValue(INFO_FAM, NAME_COL)
     val email = r.getValue(INFO_FAM, EMAIL_COL)
     val password = r.getValue(INFO_FAM, PASSWORD_COL)
     val tweetCount = r.getValue(INFO_FAM, TWEETS_COL)
     User(id, name, email, password, tweetCount)
  }
}