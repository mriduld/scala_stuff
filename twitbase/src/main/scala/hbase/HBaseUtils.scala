package hbase

import java.security.MessageDigest

import hbase.client.HBaseClient.{getTable, using}
import org.apache.hadoop.hbase.client.{Result, ResultScanner, Scan, Table}
import org.apache.hadoop.hbase.util.Bytes

import scala.collection.JavaConverters._

object HBaseUtils {
  def md5(input: String): Array[Byte] = {
    val e: MessageDigest = MessageDigest.getInstance("MD5")
    e.update(Bytes.toBytes(input))
    e.digest()
  }

  def scan[T](scan: Scan, table: Array[Byte], fn: Result => T): List[T] =
    using[List[T], Table](getTable(table)){t =>
       using[List[T], ResultScanner](t.getScanner(scan)) {scanner =>
         scanner.asScala.map(fn).toList
       }
    }
}
