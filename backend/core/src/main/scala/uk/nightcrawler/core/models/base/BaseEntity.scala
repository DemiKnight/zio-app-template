package uk.nightcrawler.core.models.base

import java.util.UUID

trait BaseEntity {
  val id: Long
  val uuid: UUID
}
