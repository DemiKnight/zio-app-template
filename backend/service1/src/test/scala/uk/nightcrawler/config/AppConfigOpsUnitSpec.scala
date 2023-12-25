package uk.nightcrawler.config

import zio.*
import zio.test.*

object AppConfigOpsUnitSpec extends ZIOSpecDefault {

  def spec = suite("AppConfigOps") {
    suite("configValue") {
      test("Will load environment") {
        for {
          config: AppConfig <- configValue
        } yield assertTrue(config.environment == Environments.dev)
      }
    }
  }
}
