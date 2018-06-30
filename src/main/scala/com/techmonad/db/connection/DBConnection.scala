package com.techmonad.db.connection

import slick.jdbc.JdbcProfile

trait DBConnection {

  val profile: JdbcProfile

  import profile.api._

  val db: Database

}
