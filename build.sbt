import sbt.Keys.autoCompilerPlugins

//val dottyVersion = "3.4.0-RC1-bin-SNAPSHOT"
val dottyVersion = "3.3.3"
val dottyCpsAsyncVersion = "0.9.21"

ThisBuild / version := "0.9.21"
ThisBuild / versionScheme := Some("semver-spec")
ThisBuild / organization := "dotty-cps-async"
ThisBuild / resolvers += Resolver.mavenLocal

Global / concurrentRestrictions += Tags.limit(ScalaJSTags.Link, 1)
Global / concurrentRestrictions += Tags.limit(ScalaJSTags.Link, 1)

lazy val commonSettings = Seq(
  scalaVersion := dottyVersion,
  libraryDependencies += "com.github.rssh" %%% "dotty-cps-async" % dottyCpsAsyncVersion,
  libraryDependencies += "org.scalameta" %%% "munit" % "1.0.0-M11" % Test,
  testFrameworks += new TestFramework("munit.Framework"),
  scalacOptions ++= Seq("-Wvalue-discard", "-Wnonunit-statement"),
  autoCompilerPlugins := true,
  addCompilerPlugin("com.github.rssh" %% "dotty-cps-async-compiler-plugin" % dottyCpsAsyncVersion)
)


lazy val core = crossProject(JSPlatform, JVMPlatform)
  .in(file("core"))
  .settings(
    commonSettings,
    name := "cps-effects"
  ).jsSettings(
    scalaJSLinkerConfig ~= {
      _.withModuleKind(ModuleKind.CommonJSModule)
    },
    scalaJSUseMainModuleInitializer := true,
  ).jvmSettings(
    scalacOptions ++= Seq("-unchecked", "-explain")
  )

lazy val catsMT = crossProject(JSPlatform, JVMPlatform)
  .in(file("catsMT"))
  .dependsOn(core)
  .settings(
    commonSettings,
    name := "cps-effects-catsmt",
    libraryDependencies += "com.github.rssh" %%% "cps-async-connect-cats-effect" % "0.9.21",
    libraryDependencies += "org.typelevel" %%% "cats-effect" % "3.5.4" % Test,
  )

lazy val eff = crossProject(JSPlatform, JVMPlatform)
  .in(file("eff"))
  .dependsOn(core)
  .settings(
    commonSettings,
    name := "cps-effects-eff",
    libraryDependencies += "org.atnos" %% "eff" % "7.0.4",
  )

lazy val turbolift = crossProject(JSPlatform, JVMPlatform)
  .in(file("turbolift"))
  .dependsOn(core)
  .settings(
    commonSettings,
    name := "cps-effects-turbolift",
    libraryDependencies += "io.github.marcinzh" %% "turbolift-core" % "0.94.0"
  )

lazy val kyo = crossProject(JSPlatform, JVMPlatform)
  .in(file("kyo"))
  .dependsOn(core)
  .settings(
    commonSettings,
    name := "cps-effects-kyo",
    libraryDependencies += "io.getkyo" %% "kyo-core" % "0.10.2"
  )

