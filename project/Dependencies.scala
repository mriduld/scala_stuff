import sbt._

object Dependencies {
  lazy val coreTests = Seq(
    "org.scalatest" %% "scalatest" % "3.0.0-M7" % "test",
    "org.scalamock" %% "scalamock-scalatest-support" % "3.2.2" % "test",
    "junit" % "junit" % "4.11" % "test"
  )


  val hbaseVersion = "0.98.6-hadoop2"
  val hadoopVersion = "2.2.0"

  lazy val hbase = Seq(
    "org.apache.hbase" % "hbase-client" % hbaseVersion,
    "org.apache.hbase" % "hbase-common" % hbaseVersion,
    "org.apache.hbase" % "hbase-server" % hbaseVersion excludeAll(
          ExclusionRule(organization = "com.sun.jersey"),
          ExclusionRule(organization = "org.apache.hadoop")
      ),
    "org.apache.hadoop" % "hadoop-common" % hadoopVersion
  )
}