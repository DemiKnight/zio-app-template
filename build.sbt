import Dependencies.*
import software.purpledragon.sbt.lock.DependencyLockPlugin

ThisBuild / version := "0.0.1"

ThisBuild / scalaVersion := "3.4.0-RC1"

lazy val core = (project in file("backend/core"))
  .settings(
    name := "core",
    libraryDependencies ++= coreDeps ++ coreTestDeps,
    scalacOptions ++= Settings.compilerSettings,
    testFrameworks += new TestFramework("zio.test.sbt.ZTestFramework")
  ).disablePlugins(DependencyLockPlugin)

lazy val service1Backend = (project in file("backend/service1"))
  .dependsOn(core)
  .settings(
    name := "service",
    libraryDependencies ++= coreDeps ++ coreService ++ coreTestDeps,
    scalacOptions ++= Settings.compilerSettings,
    testFrameworks += new TestFramework("zio.test.sbt.ZTestFramework")
  ).disablePlugins(DependencyLockPlugin)

lazy val root = (project in file("."))
  .aggregate(core, service1Backend)
  .settings(
    name := "App",
    libraryDependencies ++= allDeps
  )

ThisBuild / testFrameworks += new TestFramework("zio.test.sbt.ZTestFramework")