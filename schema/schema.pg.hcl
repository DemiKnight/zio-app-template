schema "public" {

}

table "account_user" {
  schema = schema.public
  column "id" {
    type  = serial
    null = false
  }
  column "uuid" {
    type = uuid
    null = false
    default = sql("gen_random_uuid()")
  }
  column "username" {
    type = character_varying(255)
    null = false
  }
  column "password_hash" {
    type = text
    null = false
  }
  column "email_address" {
    type = text
    null = false
  }
  column "verified" {
    type = boolean
    null = false
    default = false
  }

  primary_key {
    columns = [column.id]
  }
}

table "user_settings" {
  schema = schema.public

  column "id" {
    type = serial
    null = false
  }
  column "uuid" {
    type = uuid
    null = false
  }
  column "user_id" {
    type = integer
    null = false

  }
  foreign_key "user_id" {
    columns = [column.user_id]
    ref_columns = [table.account_user.column.id]
    on_update   = NO_ACTION
    on_delete   = NO_ACTION
  }
}
