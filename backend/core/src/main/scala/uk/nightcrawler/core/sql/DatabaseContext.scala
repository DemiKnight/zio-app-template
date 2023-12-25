package uk.nightcrawler.core.sql

import io.getquill.{PostgresZioJdbcContext, SnakeCase}
import zio.{ULayer, ZLayer}


object DatabaseContext extends PostgresZioJdbcContext(SnakeCase)

val databaseContextLayer: ULayer[DatabaseContext.type] = ZLayer.succeed(DatabaseContext)
