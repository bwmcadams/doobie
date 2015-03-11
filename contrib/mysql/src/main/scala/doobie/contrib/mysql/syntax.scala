package doobie.contrib.mysql

import scalaz.{ Monad, Catchable }

import doobie.imports._
/**
 * Module of useful syntax helpers for MySQL + Doobie
 */

object syntax {
  implicit class DoobieInsertHelpers(insert: ConnectionIO[Int]) {
    /**
     * Chained Insert with an auto increment ID returned 
     */
    def insertWithAutoId[MysqlId : Composite]: ConnectionIO[MysqlId] = {
      val result = for {
        _ <- insert
        id <- sql"SELECT last_insert_id()".query[MysqlId].unique
      } yield id
      result
    }
  }


  // chainable last insert id op
  def lastInsertId[A : Composite]: ConnectionIO[A] = 
    HC.prepareStatement("SELECT last_insert_id()")(HPS.executeQuery(HRS.getUnique[A]))
}
// vim: set ts=2 sw=2 sts=2 et:
