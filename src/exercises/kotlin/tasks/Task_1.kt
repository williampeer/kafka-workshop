package tasks

import tasks.BarebonesKafkaClients.getBareBonesProducer

// Task_1

// Produce a message to the topic "hello-world"
fun main() {
    getBareBonesProducer().use { producer ->
        // TODO: Implement me
    }
}
