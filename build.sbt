import Dependencies._

version in ThisBuild := "0.1"

scalaVersion in ThisBuild := "2.11.7"

lazy val root = Project(id = "scala-stuff", base = file("."))
                .aggregate(core, twitbase)
                .settings(
                  publishArtifact := false,
                  publish := {},
                  publishLocal := {}
                )

lazy val core = project.in(file("core"))
                .settings(
                  libraryDependencies ++= coreTests
                )

lazy val twitbase = project.in(file("twitbase"))
                    .settings(
                        libraryDependencies ++= coreTests ++ hbase
                     )
