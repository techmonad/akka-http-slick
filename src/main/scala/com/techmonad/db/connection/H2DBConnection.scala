package com.techmonad.db.connection

import org.slf4j.LoggerFactory
import slick.jdbc.H2Profile

trait H2DBConnection extends DBConnection {

  val logger = LoggerFactory.getLogger(this.getClass)

  val profile = H2Profile

  import profile.api._

  val db: Database = {
    logger.info("Connecting to H2.....")
    Database.forURL(url = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1", driver = "org.h2.Driver")

  }
}
