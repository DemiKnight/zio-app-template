package uk.nightcrawler

import uk.nightcrawler.config.AppConfig
import zio.* 
import zio.http.*

val httpRoutes: HttpApp[UserAuthService] = (healthCheck).toHttpApp

val httpServer: ZLayer[AppConfig, Nothing, Server] = ZLayer.service[AppConfig].flatMap { config =>
  Server.defaultWithPort(config.get.httpPort)
}.orDie
