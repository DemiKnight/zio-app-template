package uk.zio_app

import uk.zio_app.config.AppConfig
import uk.zio_app.core.auth.UserAuthService
import uk.zio_app.http.healthCheck
import zio.*
import zio.http.*

val httpRoutes: HttpApp[UserAuthService] = (healthCheck).toHttpApp

val httpServer: ZLayer[AppConfig, Nothing, Server] = ZLayer.service[AppConfig].flatMap { config =>
  Server.defaultWithPort(config.get.httpPort)
}.orDie
