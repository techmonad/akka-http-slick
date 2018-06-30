package com.techmonad.http

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import com.techmonad.db.connection.H2DBConnection
import com.techmonad.repository.EmployeeRepository
import org.slf4j.LoggerFactory

import scala.concurrent.ExecutionContext

object HttpServer {

  val logger = LoggerFactory.getLogger(this.getClass)

  def main(args: Array[String]): Unit = {

    implicit val actorSystem = ActorSystem("HttpServer")
    implicit val materializer = ActorMaterializer()
    implicit val ec = actorSystem.dispatcher
    val employeeRepository = new EmployeeRepository with H2DBConnection

    val httpRoute: Routes = new HttpRoutes(employeeRepository)

    employeeRepository.ddl.onComplete { _ =>
      logger.info("Starting http server on 9000")
      Http().bindAndHandle(httpRoute.routes, "0.0.0.0", 9000)
    }

  }
}


class HttpRoutes(val empRepository: EmployeeRepository)(implicit val ec: ExecutionContext) extends Routes
