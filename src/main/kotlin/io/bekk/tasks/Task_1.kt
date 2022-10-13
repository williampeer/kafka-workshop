package io.bekk.tasks

import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.consumer.KafkaConsumer
import java.time.Duration

class Task_1 {

    // Consume a message from the topic "hello-world"
    fun consumeMessageFromKafkaTopic() {
        val topicName = "hello-world"
        val consumer = KafkaConsumer<String, String>(
            mapOf(
                ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG to "false",
                ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG to "org.apache.kafka.common.serialization.StringDeserializer",
                ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG to "org.apache.kafka.common.serialization.StringDeserializer",
                ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG to "localhost:9092",  // "<replace-me>"
            )
        )

        consumer.subscribe(listOf(topicName))

        val consumerRecords = consumer.poll(Duration.ofSeconds(2))
        consumerRecords.forEach { consumerRecord ->
            println("Record: topic: ${consumerRecord.topic()}, offset:${consumerRecord.offset()}")
            println("Record value: ${consumerRecord.value()}")
        }
    }
}