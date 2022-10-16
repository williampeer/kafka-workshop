package io.bekk.tasks

import io.bekk.config.KafkaConfig
import io.bekk.publisher.BekkbookStatusMessage
import io.bekk.repository.KafkaClientRepository
import java.time.Duration

class Task_5

// Create a listener that consumes messages from the topic "bekkbook-status-message"
// Use/inspect deserialised Avro-schema object-model
// Optional: Read more about Avro-schema DeSer
fun main() {
    val kafkaConfig: KafkaConfig = KafkaConfig().also {
        it.bootstrapServer = "localhost:9092"
        it.securityProtocol = "PLAINTEXT"
        it.schemaRegistryUrl = "http://localhost:8081/"
    }
    val kafkaClientRepository = KafkaClientRepository(kafkaConfig)
    val consumer = kafkaClientRepository.getConsumer<BekkbookStatusMessage>(
        "my-group-id",
        specificAvro = true
    )
    consumer.subscribe(listOf("bekkbook-status-message"))
    consumer.seekToBeginning(consumer.assignment())
    val records = consumer.poll(Duration.ofMillis(500L))
    println("Records:")
    records.forEach {
        println("Record value: ${it.value()}")
    }
    // TODO: Fix this
}
