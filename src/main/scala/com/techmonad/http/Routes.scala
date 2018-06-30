package com.techmonad.http

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import com.techmonad.repository.{Employee, EmployeeRepository}
import com.techmonad.util.JsonUtil._

import scala.concurrent.ExecutionContext


trait Routes {


  implicit val ec: ExecutionContext

  val empRepository:EmployeeRepository

  val routes: Route =
    pathPrefix("api" / "employee") {
      (path("create") & post) {
        entity(as[Employee]) { employee =>
          complete {
            empRepository.create(employee).map { id => s"Employee created successfully[id: $id]" }
          }
        }
      } ~ (path("list") & get) {
        complete {
          empRepository.getAll()
        }
      }

    }

}

