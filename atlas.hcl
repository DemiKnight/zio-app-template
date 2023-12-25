env "local" {
  src = "file://schema/schema.pg.hcl"

  url = "postgres://root:postgresql@localhost:5432/nightcrawler?search_path=public&sslmode=disable"

  dev = "docker://postgres/16" // remove to get around test-db not having schema
  // todo figure out a better solution or way to hook into `--baseline` parameter

  migration {
    dir = "file://schema/migrations"
    format = "atlas"
  }
}