import sbt._

object Dependencies {
  lazy val coreTests = Seq(
    "org.scalatest" %% "scalatest" % "3.0.0-M7" % "test",
    "org.scalamock" %% "scalamock-scalatest-support" % "3.2.2" % "test",
    "junit" % "junit" % "4.11" % "test"
  )


  val hbaseVersion = "0.98.6-hadoop2"
  val hadoopVersion = "2.4.0"

  lazy val hbase = Seq(
    "org.apache.hbase" % "hbase-client" % hbaseVersion,
    "org.apache.hbase" % "hbase-common" % hbaseVersion,
    "org.apache.hbase" % "hbase-server" % hbaseVersion excludeAll(
          ExclusionRule(organization = "com.sun.jersey"),
          ExclusionRule(organization = "org.apache.hadoop")
      ),
    "org.apache.hadoop" % "hadoop-common" % hadoopVersion
  )

  lazy val guava = Seq(
    "com.google.guava" % "guava" % "19.0-rc1"
  )

  lazy val crunch = Seq(

  )

  lazy val avroDeps = Seq(
    "org.apache.avro" % "avro-compiler" % "1.7.7" % "compile"
  )
}