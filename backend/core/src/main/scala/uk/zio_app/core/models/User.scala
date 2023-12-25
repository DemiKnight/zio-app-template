package uk.zio_app.core.models

import io.getquill.*
import uk.zio_app.core.models.base.BaseEntity
import uk.zio_app.core.sql.DatabaseContext

import java.util.UUID

case class AccountUser(
    id: Long,
    uuid: UUID,
    username: String,
    passwordHash: String,
    emailAddress: String,
    verified: Boolean
) extends BaseEntity

extension (inline targetQuery: Query[AccountUser]) {
  inline def getByUsername(username: String): Query[AccountUser] =
    targetQuery.filter(_.username == DatabaseContext.lift(username))
  inline def getByEmail(email: String): Query[AccountUser] =
    targetQuery.filter(_.emailAddress == DatabaseContext.lift(email))
}

inline def getUser(targetString: String) = quote {
  query[AccountUser]
    .getByEmail(targetString)
    .getByUsername(targetString)
}


