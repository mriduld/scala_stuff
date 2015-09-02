import Dependencies._

val commonSettings = Seq(
  version := "0.1",
  scalaVersion := "2.11.7",
  scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")
)

lazy val core = project.in(file("core"))
  .settings(commonSettings :_*)
  .settings(libraryDependencies ++= coreTests ++ guava)

lazy val twitbase = project.in(file("twitbase"))
  .settings(commonSettings :_*)
  .settings(libraryDependencies ++= coreTests ++ hbase)


lazy val mapreduce = project.in(file("mapreduce"))
  .settings(commonSettings :_*)
  .settings(libraryDependencies ++= coreTests ++ crunch )
  .settings( sbtavro.SbtAvro.avroSettings : _*)
  .settings( (stringType in avroConfig) := "String")
  .settings( (version in avroConfig) := "1.7.7")


lazy val main = project.in(file("."))
  .aggregate(core, twitbase, mapreduce)
