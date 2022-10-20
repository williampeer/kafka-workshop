package io.bekk.repository

import io.bekk.publisher.BekkbookStatusMessage
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.springframework.stereotype.Repository
import java.time.Duration

@Repository
class FeedRepository(
    val kafkaClientRepository: KafkaClientRepository
) {
    val feedTopic = "bekkbook-status-message"
    fun<V> connectNewConsumerToCluster(): KafkaConsumer<String, V> {
        return kafkaClientRepository.getConsumer<V>(
            groupId = "server-consumer",
            earliest = true,
            autoCommit = false,
            specificAvro = true
        )
    }

    fun getFeed(): List<BekkbookStatusMessageConsumerRecord> {
        // Should you read this file as a participant: Creating a new client for each server request is really
        //  not an approach anyone should adopt. The reason we have done it this way (currently) is simply time-
        //  constraints put forth by writing this workshop. Under normal circumstances, we'd like to have clients/
        //  consumers that poll from queues continuously - i.e. that are continuously subscribed to an event stream
        //  for a given topic and set of partitions.
        return connectNewConsumerToCluster<BekkbookStatusMessage>().use { consumer ->
            consumer.subscribe(listOf(feedTopic))
            consumer.seekToBeginning(consumer.assignment())

            val statusFeed = consumer.poll(Duration.ofSeconds(1))
            return@use statusFeed.map {
                BekkbookStatusMessageConsumerRecord(
                    it.topic(),
                    it.partition(),
                    it.offset(),
                    it.timestamp(),
                    it.key(),
                    BekkbookStatusMessageData(it.value().message)
                )
            }.toList()
        }
    }
}

data class BekkbookStatusMessageData(
    val message: String
)

data class BekkbookStatusMessageConsumerRecord(
    val topicName: String,
    val partition: Int,
    val offset: Long,
    val timestamp: Long,
    val key: String,
    val value: BekkbookStatusMessageData
)

// Compare with above - we return a custom data type - this could be replaced with an arbitrary DTO, or we could use the deserialised Avro-generated object
data class ConsumerRecordWithStringValue(
    val topicName: String,
    val partition: Int,
    val offset: Long,
    val timestamp: Long,
    val key: String,
    val value: String
)
