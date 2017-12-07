package sample

import domala._
import domala.jdbc.Result

@Dao
trait EmpDao {

  @Select("select * from emp where id = /* id */0")
  def selectById(id: ID[Emp]): Option[Emp] // scala.Optionが使える

  @Select("select * from emp")
  def selectAll: Seq[Emp] // scala.Seqが使える

  @Insert
  def insert(emp: Emp): Result[Emp]

  @Update
  def update(emp: Emp): Result[Emp]

  @Script("""
  create table emp(
      id int not null primary key,
      name varchar(20),
      age int,
      version int not null
  );

  create sequence emp_id_seq start with 1;
  """)
  def create(): Unit

}
