package hbase

import hbase.HBaseUtils.nextClosest
import hbase.client.HBaseClient._
import org.apache.hadoop.hbase.HConstants
import org.apache.hadoop.hbase.client._

import scala.collection.JavaConverters._

abstract class AbstractDao[T] {
   def tableName: Array[Byte]

   private def table[T] = using[T, HTableInterface](getTable(tableName)) _

   protected def put(put: Put): Unit = table { _.put(put) }

   protected def delete(rowKey: Array[Byte]) = table { _.delete(new Delete(rowKey)) }

   protected def get(rowKey: Array[Byte], fn: Result => T): Option[T] = table[Option[T]]{t =>
      val result = t.get(new Get(rowKey))
      if(result.isEmpty) None else Some(fn(result))
     }

   protected def scan(scan: Scan, fn: Result => T): List[T] = table[List[T]] {t =>
          using[List[T], ResultScanner](t.getScanner(scan)) { scanner =>
            scanner.asScala.map(fn).toList
          }
      }


   protected def scanRange(startKey: Array[Byte] = HConstants.EMPTY_START_ROW,
                           stopKey: Array[Byte] = HConstants.EMPTY_END_ROW,
                           fn: Result => T): List[T] = scan(new Scan(startKey, stopKey), fn)

   protected def getAll(startKey: Array[Byte], fn: Result => T): List[T] = scan(new Scan(startKey, nextClosest(startKey)), fn)
}
