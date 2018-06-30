package com.techmonad.repository

import com.techmonad.db.connection.DBConnection

import scala.concurrent.Future

trait EmployeeRepository extends EmployeeTable {
  this: DBConnection =>

  import profile.api._


  def create(employee: Employee): Future[Int] =
    db.run {
      empTableQueryInc += employee
    }


  def update(employee: Employee): Future[Int] =
    db.run {
      empTableQuery.filter(_.id === employee.id).update(employee)
    }

  def delete(id: Int): Future[Int] =
    db.run {
      empTableQuery.filter(_.id === id).delete
    }

  def getAll(): Future[List[Employee]] =
    db.run {
      empTableQuery.to[List].result
    }

  def getById(empId: Int): Future[Option[Employee]] =
    db.run {
      empTableQuery.filter(_.id === empId).result.headOption
    }

  def ddl =
    db.run(empTableQuery.schema.create)

}

private[repository] trait EmployeeTable {
  self: DBConnection =>

  import profile.api._

  lazy protected val empTableQuery = TableQuery[EmployeeTable]

  lazy protected val empTableQueryInc = empTableQuery returning empTableQuery.map(_.id)

  private[EmployeeTable] class EmployeeTable(tag: Tag) extends Table[Employee](tag, "employee") {
    val id: Rep[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    val name: Rep[String] = column[String]("name", O.SqlType("VARCHAR(200)"))
    val email: Rep[String] = column[String]("email", O.SqlType("VARCHAR(200)"))
    val companyName: Rep[String] = column[String]("company_name")
    val position: Rep[String] = column[String]("position")

    def emailUnique = index("email_unique_key", email, unique = true)

    def * = (name, email, companyName, position, id.?) <> (Employee.tupled, Employee.unapply)
  }

}


case class Employee(name: String, email: String, companyName: String, position: String, id: Option[Int] = None)
