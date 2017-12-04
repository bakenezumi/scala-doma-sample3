package sample

import domala._
import domala.jdbc.Config

object SampleApp3 extends App {
  implicit lazy val config: Config = AppConfig
  lazy val dao: EmpDao = EmpDao.impl
  lazy val NOT_ASSIGNED_EMP_ID = ID[Emp](-1)
  lazy val INITIAL_VERSION = -1

  Required {
    dao.create() // create table
    Seq(
      Emp(NOT_ASSIGNED_EMP_ID, "scott", 10, INITIAL_VERSION),
      Emp(NOT_ASSIGNED_EMP_ID, "allen", 20, INITIAL_VERSION)
    ).map {
      dao.insert
    }
    // idが2のEmpのageを +1
    val updated = // Optionなので型推論が働く
      dao
        .selectById(ID(2)) // 検索して
        .map { emp =>
          dao.update(emp.growOld) // 見つかった場合には年齢を＋１して更新
        }
    println(updated) // => Some(Result(1,Emp(ID(2),allen,21,2)))
    val list = dao.selectAll
    list.foreach(println) // Scalaコレクションの各種メソッドが利用可能になった
    // =>
    //   Emp(ID(1),scott,10,1)
    //   Emp(ID(2),allen,21,2)
  }

}
