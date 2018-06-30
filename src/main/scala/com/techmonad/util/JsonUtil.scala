package com.techmonad.util

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import com.techmonad.repository.Employee
import spray.json.DefaultJsonProtocol

object JsonUtil extends DefaultJsonProtocol with SprayJsonSupport {

  implicit val employeeFormat = jsonFormat5(Employee)

}
