package uk.zio_app.core.models.base

import java.util.UUID

trait BaseEntity {
  val id: Long
  val uuid: UUID
}
