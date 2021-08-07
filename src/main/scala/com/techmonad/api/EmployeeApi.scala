package com.techmonad.api

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import com.techmonad.service.EmployeeService

import scala.concurrent.ExecutionContext


trait EmployeeApi {


  implicit val ec: ExecutionContext
  val employeeService: EmployeeService

  val routes: Route =
    pathPrefix("api" / "employee") {
      (path("create") & post) {
        entity(as[String]) { employee =>
          complete {
            employeeService.create(employee)
          }
        }
      } ~ (path("list") & get) {
        complete {
          employeeService.getAll()
        }
      }

    }

}

