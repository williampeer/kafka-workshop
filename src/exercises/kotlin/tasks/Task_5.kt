package tasks

import io.bekk.publisher.BekkbookStatusMessage
import org.apache.kafka.clients.producer.ProducerRecord
import java.util.*

// Task_5

// Produce an Avro-serialized message to the topic "bekkbook-status-message"

fun main() {

    BarebonesKafkaClients.getAvroProducer<BekkbookStatusMessage>().use { producer ->
        // TODO: Implement me
//        val avroSerialisedValue: BekkbookStatusMessage
//
//        producer.send(
//            ProducerRecord(
//                Constants.AVRO_TOPIC_NAME,
//                UUID.randomUUID().toString(),  // what happens if you produce several messages using the same key?
//                avroSerialisedValue
//            )
//        )
    }
}