package tasks

import tasks.BarebonesKafkaClients.getBareBonesProducer
import org.apache.kafka.clients.producer.ProducerRecord
import java.util.*

// Task_2

// Produce a message to the topic "hello-world"
fun main() {
    getBareBonesProducer().use { producer ->
        producer.send(
            ProducerRecord(
                Constants.TOPIC_NAME,
                "log-compaction-key-${UUID.randomUUID()}",
                "Hey hey hey!"
            )
        )
    }
}
