package com.octo

import java.util.Collections

import org.apache.kafka.clients.consumer.{ConsumerConfig, ConsumerRecords, KafkaConsumer}
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerConfig, ProducerRecord}
import org.scalatest.concurrent.Eventually

import scala.collection.JavaConverters._

import scala.concurrent.duration._

class HighLevelApiSpec extends ArticleSpec with Eventually {
  
  "kafka" should "read what it published" in {
    //GIVEN
    
    Thread.sleep(10000)

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

    val kafkaConsumer: KafkaConsumer[String, String] = new KafkaConsumer[String, String](kafkaPropsRead.asJava)
    kafkaConsumer.subscribe(Collections.singleton(topic))
    kafkaConsumer

    val connectProps: Map[String, AnyRef] = Map(
      ProducerConfig.BOOTSTRAP_SERVERS_CONFIG -> bootstrapServers,
      ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG -> "org.apache.kafka.common.serialization.StringSerializer",
      ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG -> "org.apache.kafka.common.serialization.StringSerializer"
    )

    val producer: KafkaProducer[String, String] = new KafkaProducer[String, String](connectProps.asJava)

    val record = new ProducerRecord[String, String](topic, "value")
    producer.send(record)
    implicit val patienceConfig = PatienceConfig(1 minute, 5 seconds) 
    eventually {
      val result: ConsumerRecords[String, String] = kafkaConsumer.poll(0)
      
      val option = result.asScala.headOption 
      option shouldBe defined
      option.get.value() shouldBe "value"
    }
    
//    
//    override def sendScoredEvent(ev: Event, scoredEv: ScoredEvent): Future[Try[Unit]] = {
//      val scoredEventId = UUID.randomUUID().toString
//      val eventHeader = KafkaEventHeader(scoredEventId,
//        DateUtil.dateTimeToTimestampUnixString(ev.dateTimeRef),
//        ev.nomenclatureEv,
//        lclfCanal,
//        lclfMedia,
//        lclfSchemaVersion,
//        lclfHeaderVersion)
//      val
//      val eventBusinessContext = KafkaScoredEventBody(date = scoredEv.date.getMillis, eventId = scoredEv.eventId.toString, identifiantPersonne = scoredEv.identifiantPersonne, score = scoredEv.score, statut = scoredEv.statut.value.toLong,
//        listeReglesLevees = scoredEv.ruleResults.map(ruleResult =>
//          regle(nomRegle = ruleResult.name, scoreRegle = ruleResult.score, output = ruleResult.output,
//            listControlesLeves = ruleResult.controlResults.map(controlResult =>
//              controle(nomControle = controlResult.name, scoreControle = controlResult.score, resultat = controlResult.result)).toList)).toList,
//        listeEvtLies = scoredEv.relatedEvents.map(relatedEvent => evtLie(eventId = relatedEvent.eventId.toString, eventType = relatedEvent.eventType, date = relatedEvent.date.getMillis)).toList,
//        metadata = scoredEv.metadata)
//      val kafkaScoredEvent = KafkaScoredEvent(eventHeader, eventBusinessContext)
//
//      val eventualRecord = KafkaTopicReferential.getScoredEventTopic(scoredEv, ev).map(new ProducerRecord(_, scoredEv.identifiantPersonne, kafkaScoredEvent))
//      eventualRecord.map(scalaSend(kafkaProducer, _)).getOrElse(Future.successful(Success({})))
//    }
//
//    private def send() {
//      
//    }
//    
//    private def scalaSend[K, V](producer: KafkaProducer[K, V], record: ProducerRecord[K, V]): Future[Try[Unit]] = {
//      val promise = Promise[RecordMetadata]()
//      val callback: Callback = new Callback {
//        override def onCompletion(metadata: RecordMetadata, exception: Exception): Unit = {
//          val result =
//            if (exception == null) Success(metadata)
//            else Failure(exception)
//          promise.complete(result)
//        }
//      }
//      try {
//        Logger.info(s"Send record to Kafka: $record")
//        producer.send(record, callback)
//      } catch {
//        case e: Throwable =>
//          Logger.error(s"Failed to send record to Kafka: $record", e)
//          promise.failure(e)
//      }
//      promise.future.map(_ => {
//        Logger.info(s"Successfully published $record to Kafka")
//        Success({})
//      }).recover {
//        case e =>
//          Logger.error(s"Publishing record to Kafka failed: $record", e)
//          Failure(e)
//      }
//    }
//  }
    //WHEN

    //THEN
    succeed
  }
}
