package com.octo

import java.io.File

import com.dimafeng.testcontainers.{DockerComposeContainer, ForAllTestContainer}
import org.scalatest.{BeforeAndAfterAll, FlatSpec, Matchers}

import scala.concurrent.ExecutionContext

trait ArticleSpec extends FlatSpec with Matchers with BeforeAndAfterAll with ForAllTestContainer {
  implicit val ec: ExecutionContext = ExecutionContext.global

  override val container = DockerComposeContainer(new File("src/test/resources/docker-compose.yml"))
    
  override def beforeAll(): Unit = {
    super.beforeAll()

  }

  override def afterAll(): Unit = {
    super.afterAll()
  }
  
}
