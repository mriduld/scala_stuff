package hbase.client

import java.io.Closeable

import org.apache.hadoop.hbase.{HBaseConfiguration, TableName}
import org.apache.hadoop.hbase.client._
import org.apache.hadoop.hbase.util.Bytes

object HBaseClient {
  lazy val connection = HConnectionManager.createConnection(HBaseConfiguration.create())

  def close(): Unit = {
     println("Closing HBase Connection Manager")
     connection.close()
  }

  def getTable(table: Array[Byte]) = connection.getTable(TableName.valueOf(table))

  def using[A,B <: Closeable](resource: B)(fn: B => A): A = try {
    fn(resource)
  } finally {
     if(resource != null){
       println("Closing resource => "+ resource)
       resource.close()
     }
  }

  implicit def stringFromBytes(bytes: Array[Byte]): String = Bytes.toString(bytes)
  implicit def longFromBytes(bytes: Array[Byte]): Long = if(bytes == null) 0L else Bytes.toLong(bytes)
}
