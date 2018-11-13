package com.octo

import java.util.concurrent.TimeUnit

import com.dimafeng.testcontainers.ForAllTestContainer
import org.scalatest.{BeforeAndAfterAll, FlatSpec, Matchers}

import scala.collection.JavaConverters._
import scala.concurrent.duration._
import scala.concurrent.{Await, ExecutionContext, Future}
import scala.util.Random

trait ArticleSpec extends FlatSpec with Matchers with BeforeAndAfterAll with ForAllTestContainer {

  override val container = 
    
  override def beforeAll(): Unit = {
    super.beforeAll()

  }

  override def afterAll(): Unit = {
    super.afterAll()
  }
  

}
