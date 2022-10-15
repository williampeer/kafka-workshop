package io.bekk.tasks

import io.bekk.repository.getBareBonesConsumer
import java.time.Duration

class Task_1
// Consume a message from the topic "hello-world"

fun main() {
    val topicName = "hello-world"
    val consumer = getBareBonesConsumer(offsetConfig = "earliest")
    consumer.subscribe(listOf(topicName))

    val consumerRecords = consumer.poll(Duration.ofMillis(500L))
    consumerRecords.forEach { consumerRecord ->
        println("Record: topic: ${consumerRecord.topic()}, offset:${consumerRecord.offset()}")
        println("Record value: ${consumerRecord.value()}")
    }
}