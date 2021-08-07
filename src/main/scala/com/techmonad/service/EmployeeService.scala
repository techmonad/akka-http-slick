package com.techmonad.service

import com.techmonad.repository.{Employee, EmployeeRepository}
import com.techmonad.util.JsonHelper._

import scala.concurrent.{ExecutionContext, Future}

trait EmployeeService {

  val empRepository: EmployeeRepository

  def create(emp: String)(implicit ec: ExecutionContext): Future[String] = {
    val employee = parse(emp).extract[Employee]
    empRepository
      .create(employee)
      .map { id => s"Employee created successfully[id: $id]" }
  }

  def getAll()(implicit ec: ExecutionContext) =
    empRepository
      .getAll()
      .map(empList => write(empList))

}
