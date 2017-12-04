package sample

import org.scalatest.FunSuite
import SampleApp3._
import domala.Required
import domala.jdbc.Result

class SampleTestSuite extends FunSuite with org.scalatest.BeforeAndAfterAll {

  override def beforeAll(): Unit = {
    Required {
      dao.create()
    }
  }

  test("insert & select") {
    Required {
      config.getTransactionManager.setRollbackOnly()
      val Result(_, entity) =
        dao.insert(Emp(NOT_ASSIGNED_EMP_ID, "foo", 10, -1))
      val selected = dao.selectById(entity.id)
      assert(selected contains Emp(ID(1), "foo", 10, 1))
    }
  }
}
