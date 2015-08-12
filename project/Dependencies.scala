import sbt._

object Dependencies {
  lazy val coreTests = Seq(
    "org.scalatest" %% "scalatest" % "3.0.0-M7" % "test",
    "junit" % "junit" % "4.11" % "test",
    "com.novocode" % "junit-interface" % "0.9" % "test->default",
    "org.mockito" % "mockito-core" % "1.9.5"
  )

  lazy val hbase = Seq(
    "org.apache.hbase" % "hbase-client" % "0.98.6-hadoop2",
    "org.apache.hbase" % "hbase-common" % "0.98.6-hadoop2",
    "org.apache.hbase" % "hbase-server" % "0.98.6-hadoop2" excludeAll(
          ExclusionRule(organization = "com.sun.jersey"),
          ExclusionRule(organization = "org.apache.hadoop")
      ),
    "org.apache.hadoop" % "hadoop-common" % "2.2.0"
  )
}