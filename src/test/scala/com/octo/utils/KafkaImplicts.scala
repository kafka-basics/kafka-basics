package com.octo.utils

import org.apache.kafka.clients.producer.{Callback, KafkaProducer, ProducerRecord, RecordMetadata}

import scala.concurrent.{ExecutionContext, Future, Promise}
import scala.util.{Failure, Success}


trait KafkaImplicts {

  implicit class PimpedKafkaProducer[K, V](kafkaProducer: KafkaProducer[K, V]) {
    def sendScala(record: ProducerRecord[K, V])(implicit ec: ExecutionContext): Future[RecordMetadata] = {
      kafkaProducer.send(record)
      val promise = Promise[RecordMetadata]()
      val callback: Callback = new Callback {
        override def onCompletion(metadata: RecordMetadata, exception: Exception): Unit = {
          val result =
            if (exception == null) Success(metadata)
            else Failure(exception)
          promise.complete(result)
        }
      }
      try {
        kafkaProducer.send(record, callback)
      } catch {
        case e: Throwable =>
          promise.failure(e)
      }
      promise.future
    }

  }

}

object KafkaImplicts extends KafkaImplicts
