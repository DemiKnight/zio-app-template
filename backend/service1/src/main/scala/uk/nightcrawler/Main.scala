package uk.nightcrawler

import io.getquill.*
import uk.nightcrawler.config.{AppConfig, applicationConfigLayer}
import uk.nightcrawler.core.auth.UserAuthService
import uk.nightcrawler.core.models.AccountUser
import uk.nightcrawler.http.{httpRoutes, httpServer}
import zio.*
import zio.http.*

import javax.sql.DataSource
import java.io.IOException

object Main extends ZIOAppDefault {

  private val app: ZIO[AppConfig & Server & UserAuthService, IOException, Unit] = for {
    _ <- Console.printLine("Hello WOrld!")
    _ <- Server.install(httpRoutes)
    config: ZEnvironment[AppConfig] <- ZIO.environment[AppConfig]
    _ <- Console.printLine(s"size = ${config.get.db.url}")
    _ <- ZIO.never
  } yield ()

  override val run: ZIO[Any & ZIOAppArgs & Scope, Any, Any] =
    app.provide(applicationConfigLayer, httpServer, databaseDatasource, UserAuthService.live)
}
