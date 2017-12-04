package sample

import domala.jdbc.LocalTransactionConfig
import org.seasar.doma.jdbc.dialect._
import org.seasar.doma.jdbc.tx._

object AppConfig
    extends LocalTransactionConfig(
      new LocalTransactionDataSource("jdbc:h2:mem:tutorial;DB_CLOSE_DELAY=-1",
                                     "sa",
                                     null),
      new H2Dialect()
    ) {
  Class.forName("org.h2.Driver")
}
