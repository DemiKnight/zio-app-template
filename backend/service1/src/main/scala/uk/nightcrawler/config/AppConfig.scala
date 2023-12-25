package uk.nightcrawler.config

enum Environments(val config: String) {
  case dev extends Environments("application-dev.conf")
  case staging extends Environments("application-staging.conf")
  case prod extends Environments("application-prod.conf")
  case test extends Environments("application-test.conf")
}

case class DataSourceConfig(
    url: String,
    password: String,
    maximumPoolSize: Int,
    username: String,
    connectionTimeout: Int
)

trait IncludeEnvironmentConfig {
  val environment: Environments
}
case class AppConfig(db: DataSourceConfig, environment: Environments, httpPort: Int)
    extends IncludeEnvironmentConfig
case class EnvironmentConfig(environment: Environments)
    extends IncludeEnvironmentConfig

case object ConfigError
case object NoMainConfig
