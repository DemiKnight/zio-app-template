package uk.nightcrawler.models

import uk.nightcrawler.core.models.base.UserRestrictedEntity

import java.util.UUID

case class UserSettings(id: Long, uuid: UUID, userId: Long, supportInvited: Boolean)
    extends UserRestrictedEntity
