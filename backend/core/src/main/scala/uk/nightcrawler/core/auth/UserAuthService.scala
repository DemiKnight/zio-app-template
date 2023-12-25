package uk.nightcrawler.core.auth

import uk.nightcrawler.core.models.AccountUser
import zio.*
import zio.http.Header.Authorization
import zio.http.Request

import javax.sql.DataSource
import java.util.UUID

trait UserAuthService {

  /** Authorise request using JWT
    * @param request
    *   Incoming request
    * @return
    *   [[UserIdentity]] Logged in user identity
    */
  def authoriseUserRequest(request: Request): UIO[Option[UserIdentity]]
}

object UserAuthService {
  val live: URLayer[DataSource, UserAuthService] =
    ZLayer.fromFunction(new UserAuthServiceImpl(_))

  def authoriseUserRequest(
      request: Request
  ): URIO[UserAuthService, Option[UserIdentity]] =
    ZIO.serviceWithZIO[UserAuthService](_.authoriseUserRequest(request))
}

class UserAuthServiceImpl(dataSource: DataSource) extends UserAuthService {
  override def authoriseUserRequest(
      request: Request
  ): UIO[Option[UserIdentity]] = {
    // TODO THIS IS A WIP
    val result = request.headers.header(Authorization).map { value =>
      UserIdentity(AccountUser(1, UUID.randomUUID(),"", "", value.renderedValue,  true))
    }

    ZIO.succeed(result)
  }
}
