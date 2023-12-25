package uk.nightcrawler.core.sql

import io.getquill.*
import uk.nightcrawler.core.auth.UserIdentity
import uk.nightcrawler.core.models.base.UserRestrictedEntity
import zio.*

import java.sql.SQLException
import java.util.UUID
import javax.sql.DataSource

import uk.nightcrawler.core.sql.DatabaseContext.*

extension [A <: UserRestrictedEntity](inline query: Query[A]) {

  inline def optional(using
      user: UserIdentity
  ): ZIO[DataSource, Option[Nothing] | SQLException, A] = {
    DatabaseContext
      .run(
        quote {
          query
            .filter(entity => entity.userId == lift(user.user.id))
            .take(1)
        }
      )
      .flatMap {
        case List(head) => ZIO.succeed(head)
        case _          => ZIO.fail(None)
      }
  }

  inline def getResults(using user: UserIdentity): ZIO[DataSource, Option[Nothing] | SQLException, Seq[A]] = {
    DatabaseContext
      .run(
        quote {
          query
            .filter(entity => entity.userId == lift(user.user.id))
        }
      )
  }

  inline def find(id: Long)(using
      user: UserIdentity
  ): ZIO[DataSource, Option[Nothing] | SQLException, A] = {
    DatabaseContext
      .run(quote {
        query
          .filter(entity =>
            entity.userId == lift(user.user.id) && entity.id == lift(id)
          )
          .take(1)
      })
      .flatMap {
        case List(head) => ZIO.succeed(head)
        case _          => ZIO.fail(None)
      }
  }

  inline def find(inline uuid: UUID)(using user: UserIdentity): Option[A] = ???
}
//}
