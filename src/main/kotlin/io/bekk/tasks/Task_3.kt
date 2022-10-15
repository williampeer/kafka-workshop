package io.bekk.tasks

import io.bekk.repository.getBareBonesConsumer
import io.bekk.repository.getBareBonesProducer
import org.apache.kafka.clients.producer.ProducerRecord
import java.time.Duration

class Task_3

val latest = ".. and greatest!"

// Produce multiple messages to the hello-world-topic using the same key. What happens when you try to consume these messages?
//  Google and read about "Kafka log compaction" if you'd like to know more.
fun main() {
    val producer = getBareBonesProducer()

    val msgs = listOf("Konichiwa!", "And another one.", "And another one!", "And another one..", latest)
    run {
        msgs.forEach { msg ->
            producer.send(
                ProducerRecord(
                    "hello-world",
                    "such-compaction-much-log",
                    msg
                )
            )
        }
    }
}

fun readQueueFromStart() {
    val consumer = getBareBonesConsumer(offsetConfig = "earliest")
    consumer.subscribe(listOf(topicName))

    val consumerRecords = consumer.poll(Duration.ofMillis(500L))
    consumerRecords.forEach { consumerRecord ->
        println("Record: topic: ${consumerRecord.topic()}, offset:${consumerRecord.offset()}")
        println("Record value: ${consumerRecord.value()}")
    }

    // iff log compaction has been run:
    assert(consumerRecords.toList().size == 1)
    assert(consumerRecords.last().value() == latest)

}
