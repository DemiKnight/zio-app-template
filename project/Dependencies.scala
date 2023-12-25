import sbt.*

object Dependencies {

  lazy val allDeps: Seq[sbt.ModuleID] = coreDeps ++ coreService ++ coreTestDeps

  val zioVersion = "2.0.20"
  val zioQuillVersion = "4.8.0"
  val zioHTTPVersion = "3.0.0-RC4"
  val zioConfigVersion = "4.0.0-RC16"

  lazy val coreDeps: Seq[ModuleID] = Seq(
    "dev.zio" %% "zio" % zioVersion,
    "dev.zio" %% "zio-streams" % zioVersion,
    "dev.zio" %% "zio-http" % zioHTTPVersion,

    // Database
    "io.getquill" %% "quill-jdbc-zio" % zioQuillVersion,
    "org.postgresql" % "postgresql" % "42.7.1",
    "com.zaxxer" % "HikariCP" % "5.1.0",
  )

  lazy val coreService: Seq[ModuleID] = Seq(
    "dev.zio" %% "zio-config" % zioConfigVersion,
    "dev.zio" %% "zio-config-typesafe" % zioConfigVersion,
    "dev.zio" %% "zio-config-magnolia" % zioConfigVersion,

    // HTTP
    "dev.zio" %% "zio-http" % zioHTTPVersion
  )

  lazy val coreTestDeps: Seq[sbt.ModuleID] = coreDeps ++ Seq(
    "org.scalatest" %% "scalatest" % "3.2.17",
    "dev.zio" %% "zio-test" % zioVersion,
    "dev.zio" %% "zio-test-sbt" % zioVersion,
    "dev.zio" %% "zio-test-magnolia" % zioVersion
  )
}
