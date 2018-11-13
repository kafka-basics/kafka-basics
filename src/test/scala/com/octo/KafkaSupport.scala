package com.octo

import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerConfig, ProducerRecord}
import scala.collection.JavaConverters._
import Implicits._

trait KafkaSupport {
  self: ArticleSpec =>
  import KafkaSupport._
  val producer: KafkaProducer[String, String] = new KafkaProducer[String, String](kafkaPropsProducer.asJava)
  def sendValue(value: String) = producer.sendScala(generateRecord(value))
  
  // TODO consume()

}

object KafkaSupport {
  val bootstrapServers: String = "kafka:9092"
  val topic = "LEtoPic"
  val groupId = "KafkaExampleConsumer"
  
  val kafkaPropsRead: Map[String, AnyRef] = Map[String, AnyRef](
    ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG -> bootstrapServers,
    ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG -> "org.apache.kafka.common.serialization.StringDeserializer",
    ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG -> "org.apache.kafka.common.serialization.StringDeserializer",
    ConsumerConfig.AUTO_OFFSET_RESET_CONFIG -> "earliest",
    ConsumerConfig.GROUP_ID_CONFIG -> groupId
  )

  val kafkaPropsProducer: Map[String, AnyRef] = Map(
    ProducerConfig.BOOTSTRAP_SERVERS_CONFIG -> bootstrapServers,
    ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG -> "org.apache.kafka.common.serialization.StringSerializer",
    ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG -> "org.apache.kafka.common.serialization.StringSerializer"
  )

  def generateRecord(value: String) = new ProducerRecord[String, String](topic, value)
}
