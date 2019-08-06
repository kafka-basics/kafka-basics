package com.octo

import org.apache.kafka.clients.admin._
import java.util.Properties
import scala.collection.JavaConverters._
import scala.collection.JavaConversions._

object KafkaUtilities {


  def createTopic() {
    val props = new Properties()
    props.setProperty(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092")
    val adminClient = AdminClient.create(props)
    val numPartitions = 3
    val replicationFactor = 1.toShort
    val newTopic = new NewTopic("new-topic-test", numPartitions, replicationFactor)
    val topicStatus = adminClient.createTopics(List(newTopic).asJavaCollection).values()
    println("created topic"+topicStatus.keySet())
    val topics = adminClient.listTopics
    val topicNames = topics.names.get
    for (topic <- topicNames) {
      println(topic)
    }
  }

  def main(args: Array[String]): Unit = {
    //println("Hello, world!")
    createTopic()
  }
}
