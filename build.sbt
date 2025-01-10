import org.scalajs.linker.interface.ModuleSplitStyle
import sbt.Keys.testFrameworks

val scala3Version = "3.6.2"

val learnPackage = "com.learn"

lazy val core = (crossProject(JSPlatform, JVMPlatform) in file("common"))
  .settings(
    name         := "common",
    scalaVersion := scala3Version,
    organization := learnPackage
  )
  .jvmSettings(
    // add here if necessary
  )
  .jsSettings(
    // Add JS-specific settings here
  )

lazy val root = project.in(file("."))
  .settings(
    name := "ScalaJsFullStack",
    version := "0.1.0-SNAPSHOT",

    scalaVersion := scala3Version,
    organization := learnPackage,

    libraryDependencies += "org.scalameta" %% "munit" % "1.0.0" % Test
  )
  .aggregate(server, client)

val http4sVersion = "0.23.24"
val circeVersion = "0.14.0"

lazy val catsEffectVersion          = "3.3.14"
lazy val doobieVersion              = "1.0.0-RC1"
lazy val logbackVersion             = "1.4.0"
lazy val slf4jVersion               = "2.0.0"

lazy val server = project
  .in(file("./server"))
  .settings(
    name := "server",
    Compile / run / fork := true,

    scalaVersion := scala3Version,
    organization := learnPackage,

    libraryDependencies ++= Seq(
      "org.http4s" %% "http4s-ember-client" % http4sVersion,
      "org.http4s" %% "http4s-ember-server" % http4sVersion,
      "org.http4s" %% "http4s-dsl" % http4sVersion,
      "org.slf4j" % "slf4j-simple" % "2.0.9",
      "org.http4s" %% "http4s-circe" % http4sVersion,
      "io.circe" %% "circe-generic" % circeVersion,

//      "org.typelevel"         %% "cats-effect"         % catsEffectVersion,
//      "org.http4s"            %% "http4s-dsl"          % http4sVersion,
//      "org.http4s"            %% "http4s-ember-server" % http4sVersion,
//      "org.http4s"            %% "http4s-circe"        % http4sVersion,
//      "io.circe"              %% "circe-generic"       % circeVersion,
//      "io.circe"              %% "circe-fs2"           % circeVersion,
//      "org.tpolecat"          %% "doobie-core"         % doobieVersion,
//      "org.tpolecat"          %% "doobie-hikari"       % doobieVersion,
//      "org.tpolecat"          %% "doobie-postgres"     % doobieVersion,
//      "org.slf4j"              % "slf4j-simple"        % slf4jVersion,

      "org.scalameta" %% "munit" % "1.0.0" % Test
    )
  )
  .dependsOn(core.jvm)

lazy val tyrianVersion = "0.6.1"
lazy val fs2DomVersion = "0.1.0"

lazy val client = project.in(file("./client"))
  .enablePlugins(ScalaJSPlugin)
  .settings(
    scalaVersion := scala3Version,
    Compile / run / fork := true,
    // Tell Scala.js that this is an application with a main method
    scalaJSUseMainModuleInitializer := true,

    /* Configure Scala.js to emit modules in the optimal way to
     * connect to Vite's incremental reload.
     * - emit ECMAScript modules
     * - emit as many small modules as possible for classes in the "client" package
     * - emit as few (large) modules as possible for all other classes
     *   (in particular, for the standard library)
     */
    scalaJSLinkerConfig ~= {
      _.withModuleKind(ModuleKind.ESModule)
        .withModuleSplitStyle(
          ModuleSplitStyle.SmallModulesFor(List("client"))
        )
    },

    /* Depend on the scalajs-dom library.
     * It provides static types for the browser DOM APIs.
     */
    libraryDependencies ++= Seq(
      "org.scala-js" %%% "scalajs-dom" % "2.8.0",
      "io.indigoengine" %%% "tyrian-io"     % tyrianVersion,
      "com.armanbilge"  %%% "fs2-dom"       % fs2DomVersion,
      "io.circe" %%% "circe-core" % circeVersion,
      "io.circe" %%% "circe-parser" % circeVersion,
      "io.circe" %%% "circe-generic" % circeVersion,
      "org.scalameta" %% "munit" % "1.0.0" % Test
    ),
    scalaJSLinkerConfig ~= { _.withModuleKind(ModuleKind.CommonJSModule) },
    semanticdbEnabled := true,
    autoAPIMappings   := true
  )
  .dependsOn(core.js)