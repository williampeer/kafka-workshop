package io.bekk.tasks

import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.consumer.KafkaConsumer
import java.time.Duration

class Task_1
// Consume a message from the topic "hello-world"

fun main() {
    val topicName = "hello-world"
    val consumer = KafkaConsumer<String, String>(
        mapOf(
            ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG to "org.apache.kafka.common.serialization.StringDeserializer",
            ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG to "org.apache.kafka.common.serialization.StringDeserializer",
            ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG to "localhost:9092",  // "<replace-me>"
            ConsumerConfig.GROUP_ID_CONFIG to "my-consumer-group",
            ConsumerConfig.AUTO_OFFSET_RESET_CONFIG to "earliest",
        )
    )

    consumer.subscribe(listOf(topicName))

    val consumerRecords = consumer.poll(Duration.ofMillis(500L))
    consumerRecords.forEach { consumerRecord ->
        println("Record: topic: ${consumerRecord.topic()}, offset:${consumerRecord.offset()}")
        println("Record value: ${consumerRecord.value()}")
    }
}