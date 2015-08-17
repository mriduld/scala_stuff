package hbase

import hbase.client.HBaseClient
import org.scalatest.{BeforeAndAfterAll, Suites}

class HBaseTestSuites
  extends Suites(new UserDaoTest,
                 new TwitDaoTest)
  with BeforeAndAfterAll{

    override def afterAll(): Unit = {
       HBaseClient.close()
    }
}
