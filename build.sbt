name := "akka-http-slick-spray-json"

version := "0.1"

scalaVersion := "2.12.6"


libraryDependencies ++= Seq(
  "mysql" % "mysql-connector-java" % "5.1.36",
  "com.typesafe.akka" %% "akka-http" % "10.1.3",
  "com.typesafe.akka" %% "akka-stream" % "2.5.13",
  "com.typesafe.akka" %% "akka-http-spray-json" % "10.1.3",
  "com.typesafe.slick" %% "slick" % "3.2.3",
  "com.typesafe.slick" %% "slick-hikaricp" % "3.2.3",
  "ch.qos.logback" % "logback-classic" % "1.1.3",
  "com.h2database" % "h2" % "1.4.187",
  "org.scalatest" %% "scalatest" % "3.0.5" % Test,
  "com.typesafe.akka" %% "akka-http-testkit" % "10.1.3" % Test
)


