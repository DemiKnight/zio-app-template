package uk.zio_app.config

import zio.*
import zio.config.*
import zio.config.magnolia.*
import zio.config.typesafe.*

import java.io.FileNotFoundException
import scala.io.Source

private val applicationConfig: Config[AppConfig] =
  deriveConfig[AppConfig]

private val environmentConfig = deriveConfig[EnvironmentConfig]

private def getMainConfig: IO[NoMainConfig.type, ConfigProvider] = {
  ZIO attempt ConfigProvider.fromHoconString(
    Source.fromResource("application.conf").mkString
  ) catchAll {
    case _: FileNotFoundException => ZIO.fail(NoMainConfig)
    case reason: Throwable        => ZIO.die(reason)
  }
}

private def handleConfig(
    configFile: Environments,
    previous: ConfigProvider
): ZIO[Any, ConfigError.type, ConfigProvider] = {
  ZIO attempt ConfigProvider.fromHoconString(
    Source.fromResource(configFile.config).mkString
  ) catchAll {
    case _: FileNotFoundException => ZIO.succeed(previous)
    case reason: Throwable        => ZIO.die(reason)
  } map previous.orElse
}

private def getEnvironment(
    configP: ConfigProvider
): IO[Config.Error, EnvironmentConfig] = {
  read(environmentConfig.from(configP))
}

private def loadConfig: ZIO[
  Any,
  ConfigError.type | NoMainConfig.type | Config.Error,
  AppConfig
] = for {
  mainConfig: ConfigProvider <- getMainConfig
  env <- getEnvironment(mainConfig)
  environmentConfig <- handleConfig(env.environment, mainConfig)
  withTest <- handleConfig(Environments.test, environmentConfig)
  configValue <- read(applicationConfig.from(withTest))
} yield configValue

val configValue: ZIO[
  Any,
  ConfigError.type | NoMainConfig.type | Config.Error,
  AppConfig
] = loadConfig.memoize.flatten

val applicationConfigLayer: ZLayer[Any, Any, AppConfig] =
  ZLayer.fromZIO(configValue)
