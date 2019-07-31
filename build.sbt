name := "kafka-basics2"

version := "0.1"

scalaVersion := "2.12.7"
val kafkaVersion = "2.2.0-cp2"

resolvers += "confluent" at "https://packages.confluent.io/maven/"

libraryDependencies ++= Seq("org.scalatest" %% "scalatest" % "3.0.5" % Test,
  "org.apache.kafka" % "kafka-clients" % kafkaVersion,
  "com.dimafeng" %% "testcontainers-scala" % "0.22.0" % Test,
  "ch.qos.logback" % "logback-classic" % "1.2.3",
  "org.slf4j" % "slf4j-api" % "1.7.25")



