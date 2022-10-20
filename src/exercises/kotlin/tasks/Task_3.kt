package tasks

import tasks.BarebonesKafkaClients.getBareBonesConsumer
import tasks.BarebonesKafkaClients.getBareBonesProducer
import org.apache.kafka.clients.producer.ProducerRecord
import java.time.Duration

//class Task_3

const val latest = ".. and greatest!"

// Produce multiple messages to the hello-world-topic using the same key.
//  What happens when you try to consume these messages?
//  Note: If you see all produced messages under the same key, log compaction will not have run yet. This might be due
//  to the scheduled job not having been run, or due to the topic configs.
//  Google and read about "Kafka log compaction" if you'd like to know more.
fun main() {
//    produceMessages()
    readQueueFromStart()
}

fun produceMessages() {
    getBareBonesProducer().use { producer ->
        listOf("Konichiwa!", "And another one.", "And another one!", "And another one..", latest).forEach { msg ->
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
    getBareBonesConsumer(offsetConfig = "earliest").use { consumer ->
        consumer.subscribe(listOf(Constants.TOPIC_NAME))
        val consumerRecords = consumer.poll(Duration.ofMillis(500L))
        consumerRecords.forEach { consumerRecord ->
            println("Record: topic: ${consumerRecord.topic()}, offset:${consumerRecord.offset()}")
            println("Record value: ${consumerRecord.value()}")
        }

        // iff log compaction has been run:
        assert(consumerRecords.toList().size == 1)
        assert(consumerRecords.last().value() == latest)
    }
}
