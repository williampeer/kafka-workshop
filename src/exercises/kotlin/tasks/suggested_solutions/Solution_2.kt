package tasks

import tasks.BarebonesKafkaClients.getBareBonesConsumer
import java.time.Duration

// Task_2
// Consume a message from the topic "hello-world"

fun main() {
        getBareBonesConsumer(offsetConfig = "earliest", groupId = "anotherveryrandomgroup").use { consumer ->
            consumer.subscribe(listOf(Constants.TOPIC_NAME))
            consumer.poll(Duration.ofMillis(500L))
                .forEach { consumerRecord ->
                    println("Record: topic: ${consumerRecord.topic()}, offset:${consumerRecord.offset()}")
                    println("Record value: ${consumerRecord.value()}")
                }
            consumer.commitSync()
        }
}
