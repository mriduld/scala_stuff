import sbt._

object Dependencies {
  lazy val coreTests = Seq(
    "org.scalatest" %% "scalatest" % "3.0.0-M7" % "test",
    "org.scalamock" %% "scalamock-scalatest-support" % "3.2.2" % "test",
    "junit" % "junit" % "4.11" % "test"
  )


  lazy val hbase = Seq(
    "org.apache.hbase" % "hbase-client" % "1.1.1",
    "org.apache.hbase" % "hbase-common" % "1.1.1",
    "org.apache.hbase" % "hbase-server" % "1.1.1" excludeAll(
          ExclusionRule(organization = "com.sun.jersey"),
          ExclusionRule(organization = "org.apache.hadoop")
      ),
    "org.apache.hadoop" % "hadoop-common" % "2.7.0"
  )
}