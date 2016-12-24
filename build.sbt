name := "tikiticket-core"
version := "0.01"
scalaVersion := "2.11.7"

libraryDependencies ++= {
  Seq(
    "org.jsoup" % "jsoup" % "1.10.1",
    "org.apache.httpcomponents" % "httpclient" % "4.5.2" % "test",
    "org.scalactic" %% "scalactic" % "3.0.1" % "test",
    "org.scalatest" %% "scalatest" % "3.0.1" % "test"
  )
}
