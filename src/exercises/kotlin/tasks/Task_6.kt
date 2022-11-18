package tasks

import io.bekk.publisher.BekkbookStatusMessage
import java.time.Duration

// Task_6

// Create a listener that consumes messages from the topic "bekkbook-status-message"
// Use/inspect deserialised Avro-schema object-model
// Optional: Read more about Avro-schema DeSer
fun main() {
    BarebonesKafkaClients.getAvroConsumer<BekkbookStatusMessage>("my-group-id")
        .use { consumer ->
            // TODO: Implement me
        }
}
