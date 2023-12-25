package uk.zio_app.models

import uk.zio_app.core.models.base.UserRestrictedEntity
import java.util.UUID

case class UserSettings(id: Long, uuid: UUID, userId: Long, supportInvited: Boolean)
    extends UserRestrictedEntity
