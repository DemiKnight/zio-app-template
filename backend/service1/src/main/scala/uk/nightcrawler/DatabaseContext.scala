package uk.nightcrawler

import com.zaxxer.hikari.{HikariConfig, HikariDataSource}
import io.getquill.*
import io.getquill.jdbczio.Quill
import uk.nightcrawler.config.AppConfig
import uk.nightcrawler.core.sql.DatabaseContext
import zio.*

import javax.sql.DataSource

val databaseNamingStrategy
    : ZLayer[DataSource, Nothing, Quill.Postgres[SnakeCase.type]] =
  Quill.Postgres.fromNamingStrategy(SnakeCase)

val databaseDatasource: ZLayer[AppConfig, Throwable, DataSource] =
  ZLayer.fromZIO {
    ZIO.environment[AppConfig].map { config =>
      val hikariConfig = new HikariConfig()
      hikariConfig.setPassword(config.get.db.password)
      hikariConfig.setUsername(config.get.db.username)
      hikariConfig.setJdbcUrl(config.get.db.url)
      hikariConfig.setMaximumPoolSize(config.get.db.maximumPoolSize)
      hikariConfig.setConnectionTimeout(config.get.db.connectionTimeout)
      hikariConfig.setDriverClassName("org.postgresql.Driver")
      val dataSource = HikariDataSource(hikariConfig)
      dataSource
    }
  }
