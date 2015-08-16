package hbase.client

import java.io.Closeable

import org.apache.hadoop.hbase.TableName
import org.apache.hadoop.hbase.client._
import org.apache.hadoop.hbase.util.Bytes

object HBaseClient {
  lazy val connection = ConnectionFactory.createConnection()

  def close(): Unit = {
     println("Closing HBase Connection Manager")
     connection.close()
  }

  def getTable(table: Array[Byte]): Table = connection.getTable(TableName.valueOf(table))

  def withTable[T](tableName: String)(f: Table => T): T = {
      var table: Option[Table] = None
      try {
          table = Some(getTable(Bytes.toBytes(tableName)))
          f(table.get)
      } finally {
          if(table.isDefined){
            println(s"Closing table $tableName")
            table.get.close()
          }
      }
  }


  def using[A,B <: Closeable](resource: B)(fn: B => A): A = try {
    fn(resource)
  } finally {
     if(resource != null){
       println("Closing resource => "+ resource)
       resource.close()
     }
  }
}
