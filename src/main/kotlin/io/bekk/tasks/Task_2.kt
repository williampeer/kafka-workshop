package io.bekk.tasks

import io.bekk.repository.getBareBonesProducer
import org.apache.kafka.clients.producer.ProducerRecord
import java.util.*

class Task_2

// Produce a message to the topic "hello-world"
fun main() {
    val producer = getBareBonesProducer()
    run {
        producer.send(
            ProducerRecord(
                "hello-world",
                "log-compaction-key-${UUID.randomUUID()}",
                "Hey hey hey!"
            )
        )
    }  // Closeable
}
