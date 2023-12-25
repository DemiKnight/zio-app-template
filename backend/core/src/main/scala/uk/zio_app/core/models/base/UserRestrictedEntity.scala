package uk.zio_app.core.models.base

import uk.zio_app.core.models.AccountUser

abstract class UserRestrictedEntity extends BaseEntity {
  val userId: Long
}
