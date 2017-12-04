import Dependencies._

lazy val metaMacroSettings: Seq[Def.Setting[_]] = Seq(
  addCompilerPlugin("org.scalameta" % "paradise" % "3.0.0-M10" cross CrossVersion.full),
  scalacOptions += "-Xplugin-require:macroparadise",
  scalacOptions in (Compile, console) ~= (_ filterNot (_ contains "paradise")) // macroparadise plugin doesn't work in repl yet.
)

lazy val root = (project in file(".")).settings(
  inThisBuild(List(
    scalaVersion := "2.12.4",
    version      := "0.1.0-SNAPSHOT"
  )),
  name := "scala-doma-sample3",
  libraryDependencies += scalaTest % Test,
  libraryDependencies ++= Seq(
    "com.github.domala" %% "domala" % "0.1.0-beta.6",
    "com.h2database" % "h2" % "1.4.193"
  ),
  // for Doma annotation processor
  metaMacroSettings,
)
