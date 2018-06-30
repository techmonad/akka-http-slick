package com.techmonad.db.connection

import slick.jdbc.MySQLProfile


trait MySQLDBConnection extends DBConnection {

  val profile = MySQLProfile

  import profile.api._

  val db: Database = MySqlDB.connectionPool

}

private[connection] object MySqlDB {

  import slick.jdbc.MySQLProfile.api._

  val connectionPool = Database.forConfig("mysql")

}
