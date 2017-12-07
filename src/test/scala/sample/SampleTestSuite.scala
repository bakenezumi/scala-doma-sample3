package sample

import domala.Required
import domala.jdbc.{Config, Result}
import org.scalatest.FunSuite

class SampleTestSuite extends FunSuite with org.scalatest.BeforeAndAfterAll {

  private implicit lazy val config: Config = AppConfig
  private lazy val dao: EmpDao = EmpDao.impl

  override def beforeAll(): Unit = {
    Required {
      dao.create()
    }
  }

  test("insert & select") {
    Required {
      config.getTransactionManager.setRollbackOnly()
      val Result(_, entity) =
        dao.insert(Emp(ID(-1), "foo", 10, -1))
      val selected = dao.selectById(entity.id)
      assert(selected contains Emp(ID(1), "foo", 10, 1))
    }
  }
}
