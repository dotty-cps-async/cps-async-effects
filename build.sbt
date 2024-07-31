import sbt.Keys.autoCompilerPlugins

//val dottyVersion = "3.4.0-RC1-bin-SNAPSHOT"
val dottyVersion = "3.3.3"
val dottyCpsAsyncVersion = "0.9.21"

ThisBuild/version := "0.9.21"
ThisBuild/versionScheme := Some("semver-spec")
ThisBuild/organization := "com.github.rssh"
ThisBuild/resolvers += Resolver.mavenLocal

Global / concurrentRestrictions += Tags.limit(ScalaJSTags.Link, 1)
Global / concurrentRestrictions += Tags.limit(ScalaJSTags.Link, 1)

lazy val commonSettings = Seq(
   scalaVersion := dottyVersion,
   libraryDependencies += "com.github.rssh" %%% "dotty-cps-async" % dottyCpsAsyncVersion,
   libraryDependencies += "org.scalameta" %%% "munit" % "1.0.0-M11" % Test,
   testFrameworks += new TestFramework("munit.Framework"),
   scalacOptions ++= Seq( "-Wvalue-discard", "-Wnonunit-statement"),
   autoCompilerPlugins := true,
   addCompilerPlugin("com.github.rssh" %% "dotty-cps-async-compiler-plugin" % dottyCpsAsyncVersion)
)


lazy val core  = crossProject(JSPlatform, JVMPlatform)
  .in(file("core"))
  .settings(
    commonSettings,
    name := "cps-async-effects"
  ).jsSettings(
    scalaJSLinkerConfig ~= { _.withModuleKind(ModuleKind.CommonJSModule) },
    scalaJSUseMainModuleInitializer := true,
  ).jvmSettings(
    scalacOptions ++= Seq( "-unchecked", "-explain")
  )


