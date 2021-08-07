name := "akka-http-slick-spray-json"

version := "0.1"

scalaVersion := "2.13.6"

val AkkaVersion = "2.6.8"
val AkkaHttpVersion = "10.2.6"
val SlickVersion = "3.3.3"


libraryDependencies ++= Seq(
  "mysql" % "mysql-connector-java" % "8.0.26",
  "com.typesafe.akka" %% "akka-actor-typed" % AkkaVersion,
  "com.typesafe.akka" %% "akka-stream" % AkkaVersion,
  "com.typesafe.akka" %% "akka-http" % AkkaHttpVersion,
  "com.typesafe.slick" %% "slick" % SlickVersion,
  "com.typesafe.slick" %% "slick-hikaricp" % SlickVersion,
  "org.json4s" %% "json4s-native" % "4.0.3",
  "ch.qos.logback" % "logback-classic" % "1.2.5" ,
  "com.h2database" % "h2" % "1.4.200",
  "org.scalatest" %% "scalatest" % "3.2.9" % Test,
  "com.typesafe.akka" %% "akka-http-testkit" % AkkaHttpVersion % Test
)


