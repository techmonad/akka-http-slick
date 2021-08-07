package com.techmonad.server

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import com.techmonad.api.EmployeeApi
import com.techmonad.db.connection.H2DBConnection
import com.techmonad.repository.{Employee, EmployeeRepository}
import com.techmonad.service.EmployeeService
import org.slf4j.LoggerFactory

import scala.concurrent.ExecutionContext

object AkkaHttpServer {

  val logger = LoggerFactory.getLogger(this.getClass)

  def main(args: Array[String]): Unit = {

    implicit val actorSystem = ActorSystem("HttpServer")
    implicit val materializer = ActorMaterializer
    implicit val ec = actorSystem.dispatcher

    val employeeRepository = new EmployeeRepository with H2DBConnection
    val employeeService = new EmployeeService {
      override val empRepository: EmployeeRepository = employeeRepository
    }

    val httpRoute: EmployeeApi = new HttpRoutes(employeeService)

    employeeRepository.ddl.onComplete { _ =>
      logger.info("creating employee.......")
      employeeRepository.create(Employee("jaz", "jaz@bar.com", "ABC solution", "Senior Consultant"))

      logger.info("Starting http server on 9000")
      Http().bindAndHandle(httpRoute.routes, "0.0.0.0", 9000)
    }

  }
}


class HttpRoutes(val employeeService: EmployeeService)(implicit val ec: ExecutionContext) extends EmployeeApi
