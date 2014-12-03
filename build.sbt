name := """poireauxvinaigrette"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.1"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache,
  javaWs,
  "postgresql" % "postgresql" % "9.2-1002.jdbc4"
  ,"org.webjars" % "bootstrap" % "3.0.2"
  ,"com.twilio.sdk" % "twilio-java-sdk" % "3.4.5"
  ,"org.mindrot" % "jbcrypt" % "0.3m"
  ,"ws.securesocial" % "securesocial_2.11" % "3.0-M1"
)
