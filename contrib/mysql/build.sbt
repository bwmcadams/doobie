name := "doobie-contrib-mysql"

description := "MySQL support for doobie."

libraryDependencies ++= Seq(
  "mysql" % "mysql-connector-java" % "5.1.34",
  "org.specs2"     %% "specs2"       % "2.4"               % "test"
)


initialCommands := """
  import scalaz._,Scalaz._
  import scalaz.concurrent.Task
  import doobie.imports._
  //import doobie.contrib.mysql.mysqltypes._
  val xa: Transactor[Task] = DriverManagerTransactor[Task]("com.mysql.jdbc.Driver", "jdbc:mysql", "root", "")
  import xa.yolo._
  """

/// PUBLISH SETTINGS

bintrayPublishSettings
