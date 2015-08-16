package hbase

import hbase.HBaseUtils.scan
import hbase.client.HBaseClient.{getTable, using}
import model.User
import org.apache.hadoop.hbase.client._
import org.apache.hadoop.hbase.util.Bytes

trait UserDao {
  def addUser(id: String, name: String, email: String, password: String): Unit
  def getUser(id: String): Option[User]
  def deleteUser(id: String): Unit
  def getUsers: List[User]
}

object UserDao extends UserDao{
   val TABLE = Bytes.toBytes("users")
   val INFO_FAM = Bytes.toBytes("info")
   val ID_COL = Bytes.toBytes("id")
   val NAME_COL = Bytes.toBytes("name")
   val EMAIL_COL = Bytes.toBytes("email")
   val PASSWORD_COL = Bytes.toBytes("password")
   val TWEETS_COL = Bytes.toBytes("tweets_count")

  def userTable[T] = using[T, Table](getTable(TABLE))_

  def addUser(id: String, name: String, email: String, password: String): Unit = {
     userTable{table =>
        val put = new Put(Bytes.toBytes(id))
        put.addColumn(INFO_FAM, ID_COL, Bytes.toBytes(id))
        put.addColumn(INFO_FAM, NAME_COL, Bytes.toBytes(name))
        put.addColumn(INFO_FAM, EMAIL_COL, Bytes.toBytes(email))
        put.addColumn(INFO_FAM, PASSWORD_COL, Bytes.toBytes(password))
        table.put(put)
     }
  }

  def deleteUser(id: String): Unit = {
     userTable { table =>
       table.delete(new Delete(Bytes.toBytes(id)))
     }
  }

  def getUser(id: String): Option[User] = {
    userTable[Option[User]] { table =>
       val result = table.get(new Get(Bytes.toBytes(id)))
       if(result.isEmpty) None else Some(mkUser(result))
    }
  }

  def getUsers: List[User] = {
     scan[User](new Scan(), TABLE, mkUser)
  }

  private def mkUser(r: Result ): User = {
     val id = Bytes.toString(r.getValue(INFO_FAM, ID_COL))
     val name = Bytes.toString(r.getValue(INFO_FAM, NAME_COL))
     val email = Bytes.toString(r.getValue(INFO_FAM, EMAIL_COL))
     val password = Bytes.toString(r.getValue(INFO_FAM, PASSWORD_COL))
     val tweetCount = if( r.getValue(INFO_FAM, TWEETS_COL) == null) 0L else Bytes.toLong(r.getValue(INFO_FAM, TWEETS_COL))
     User(id, name, email, password, tweetCount)
  }
}