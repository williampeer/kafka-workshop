package io.bekk.tasks

import io.bekk.config.KafkaConfig
import io.bekk.publisher.BekkbookStatusMessage
import io.bekk.repository.KafkaClientRepository
import org.apache.kafka.clients.producer.ProducerRecord
import java.util.*

class Task_4_2

// Produce an Avro-serialized message to the topic "bekkbook-status-message"

fun main() {
    val kafkaConfig: KafkaConfig = KafkaConfig().also {
        it.bootstrapServer = "localhost:9092"
        it.securityProtocol = "PLAINTEXT"
        it.schemaRegistryUrl = "http://localhost:8081/"
    }
    val kafkaClientRepository = KafkaClientRepository(kafkaConfig)
    val producer = kafkaClientRepository.getProducer<BekkbookStatusMessage>()

    run {
        producer.send(
            ProducerRecord(
                "bekkbook-status-message",
                UUID.randomUUID().toString(),
                BekkbookStatusMessage("\"It doesn't seem like anything to me.\"")
            )
        )
    }  // Closeable
}