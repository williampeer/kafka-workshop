package tasks

import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerRecord
import java.time.Duration

// Task_9

// Create a set of three consumers listening to a topic. With all three producers
// running, produce two series of messages. Ensure that each messages belonging to each
// series are produced in order.

fun main() {
    class ConsumerThread : Thread() {
        val myGroup = "task-8-group"
        val consumers = listOf(
            BarebonesKafkaClients.getBareBonesConsumer(groupId = myGroup, offsetConfig = "latest"),
            BarebonesKafkaClients.getBareBonesConsumer(groupId = myGroup, offsetConfig = "latest"),
            BarebonesKafkaClients.getBareBonesConsumer(groupId = myGroup, offsetConfig = "latest")
        ).onEach { it.subscribe(listOf(Constants.PARTITIONED_TOPIC)) }

        override fun run() {
            while (true) {
                consumers.forEachIndexed { consumerNumber, consumer ->
                    consumer.poll(Duration.ofMillis(500)).forEach { record ->
                        println("$consumerNumber received ${record.key()}:${record.value()}")
                    }
                    consumer.commitSync()
                }
            }
        }
    }

    ConsumerThread().start()
    Thread.sleep(5000)
    print("Producing...")
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
