package tasks

import org.apache.kafka.clients.producer.ProducerRecord
import tasks.BarebonesKafkaClients.getBareBonesProducer
import java.util.*

// Task_1

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
