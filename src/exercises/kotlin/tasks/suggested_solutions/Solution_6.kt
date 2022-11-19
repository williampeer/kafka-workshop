package tasks.suggested_solutions

import io.bekk.publisher.BekkbookStatusMessage
import tasks.BarebonesKafkaClients
import tasks.Constants
import java.time.Duration

// Task_6

// Create a listener that consumes messages from the topic "bekkbook-status-message"
// Use/inspect deserialised Avro-schema object-model
// Optional: Read more about Avro-schema DeSer
fun main() {
    BarebonesKafkaClients.getAvroConsumer<BekkbookStatusMessage>("my-group-id")
        .use { consumer ->
            consumer.subscribe(listOf(Constants.AVRO_TOPIC_NAME))
            consumer.seekToBeginning(consumer.assignment())
            val records = consumer.poll(Duration.ofMillis(500L))
            println("Records:")
            records.forEach {
                println("Record value: ${it.value()}")
            }
            consumer.commitAsync()
        }
}
