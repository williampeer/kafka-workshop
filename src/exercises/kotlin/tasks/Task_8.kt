package tasks

import java.time.Duration

// Task_8

// Consume the hello-world topic, starting from the first message. As you consume messages, commit offsets.
// To test, start the consumers again using the same group. Observe that the second run does not consume previously
// consumed messages.
fun main() {
    BarebonesKafkaClients.getBareBonesConsumer(groupId = "name-task-9", offsetConfig = "earliest").use { consumer ->
        consumer.subscribe(listOf(Constants.TOPIC_NAME))
        // TODO: Implement me
    }
}