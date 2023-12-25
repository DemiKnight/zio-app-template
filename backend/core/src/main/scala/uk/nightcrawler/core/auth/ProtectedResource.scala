package uk.nightcrawler.core.auth

import zio.*
import zio.http.*
import zio.http.Middleware.customAuthProvidingZIO

// This modifies the request, depending on whether the request can be authorised by `UserAuthService`
// If it fails, then it will return 401 
private[auth] val userRequestAuthoriser
    : HandlerAspect[UserAuthService, UserIdentity] =
  customAuthProvidingZIO(req => UserAuthService.authoriseUserRequest(req))

/** Defines a HTTP endpoint, which requires an authenticated user. If authorised, will provide [[UserIdentity]]
 * @param endpointFn
 * @tparam R Environment requirement for the request
 * @tparam E Any Errors from the http endpoint
 * @return Handled request, can be constructed into route(s)
 */
inline def protectedResource[R, E](
    inline endpointFn: ZIO[R & UserIdentity, E, Response]
): Handler[R & UserAuthService, Response, Request, Response] =
  Handler.fromFunctionZIO[(UserIdentity, Request)] {
    case (auth: UserIdentity, _) =>
      endpointFn.provideSomeLayer(ZLayer.succeed(auth))
  } @@ userRequestAuthoriser
