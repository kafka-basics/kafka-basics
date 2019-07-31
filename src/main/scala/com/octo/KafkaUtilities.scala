package com.octo

import org.apache.kafka.clients.admin._

import scala.collection.JavaConverters.{mapAsJavaMapConverter, seqAsJavaListConverter}

object KafkaUtilities {


  def createTopic() {
    val adminKafka = AdminClient.create(Map(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG -> "localhost:9092")
      .asJava.asInstanceOf[java.util.Map[String, AnyRef]])
    val confNewTopic = new NewTopic("NewTopic", 2, 2)
    val listconfNewTopic = List(confNewTopic).asJava
    val t = adminKafka.createTopics(listconfNewTopic, new CreateTopicsOptions())

    val listTopics = adminKafka.listTopics()
    val listTopicNames = listTopics.names().get()
    print(listTopicNames)


    return listTopicNames
  }
}
