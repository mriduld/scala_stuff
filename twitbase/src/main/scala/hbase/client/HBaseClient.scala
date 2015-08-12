package hbase.client

import org.apache.hadoop.hbase.HBaseConfiguration
import org.apache.hadoop.hbase.HConstants.ZOOKEEPER_QUORUM
import org.apache.hadoop.hbase.client._

object HBaseClient {
  lazy val connection: HConnection = {
      println("Creating HBase connection")
      val config = HBaseConfiguration.create()
      config.set(ZOOKEEPER_QUORUM, "hadoop-VirtualBox")
      config.setInt(ZOOKEEPER_QUORUM, 2181)
      HConnectionManager.createConnection(config)
  }

  def close(): Unit = {
     println("Closing HBase Connection Manager")
     connection.close()
  }

  def getTable(table: String) = connection.getTable(table)
}
