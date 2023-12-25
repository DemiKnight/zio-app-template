package uk.nightcrawler.core.models.base

import uk.nightcrawler.core.models.AccountUser

abstract class UserRestrictedEntity extends BaseEntity {
  val userId: Long
}
