package uk.nightcrawler.http
import zio.*
import zio.http.*

val healthCheck = Routes.apply(
  Method.HEAD / "monitors" / "check" -> Handler.from(ZIO.logInfo("Health Check") *> ZIO.succeed(Response.ok))
)
