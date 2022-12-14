package tasks.suggested_solutions

import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerRecord
import tasks.BarebonesKafkaClients
import tasks.Constants
import java.time.Duration

// Task_9

// Create a set of three consumers listening to a topic. With all three consumers
// running, produce two series of messages. Ensure that each messages belonging to each
// series are consumed in order.

fun main() {
    class ConsumerThread : Thread() {
        val myGroup = "task-9-group"
        val consumers = listOf(
            BarebonesKafkaClients.getBareBonesConsumer(groupId = "$myGroup-1", offsetConfig = "latest"),
            BarebonesKafkaClients.getBareBonesConsumer(groupId = "$myGroup-2", offsetConfig = "latest"),
            BarebonesKafkaClients.getBareBonesConsumer(groupId = "$myGroup-3", offsetConfig = "latest")
        ).onEach { it.subscribe(listOf(Constants.PARTITIONED_TOPIC)) }

        override fun run() {
            while (true) {
                consumers.forEachIndexed { consumerNumber, consumer ->
                    println("Polling for $consumerNumber")
                    consumer.poll(Duration.ofMillis(1000L)).forEach { record ->
                        println("$consumerNumber received ${record.key()}:${record.value()}")
                    }
                    consumer.commitSync()
                }
            }
        }
    }


    ConsumerThread().start()
    Thread.sleep(10000)
    println("Producing...")
    BarebonesKafkaClients.getBareBonesProducer().use { producer ->
        val values = listOf("First", "Second", "Third")
        values.forEach { producer.produceMessage("first-set", it) }
        values.forEach { producer.produceMessage("second-set", it) }
    }
}

fun KafkaProducer<String, String>.produceMessage(key: String, value: String) {
    send(
        ProducerRecord(
            Constants.PARTITIONED_TOPIC,
            key,
            value
        )
    )
}


