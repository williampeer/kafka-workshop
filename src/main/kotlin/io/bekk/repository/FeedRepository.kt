package io.bekk.repository

import io.bekk.publisher.BekkbookStatusMessage
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.support.KafkaHeaders
import org.springframework.messaging.handler.annotation.Header
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Repository

@Repository
class FeedRepository {

    var statusFeed = listOf<WorkshopStatusMessageConsumerRecord>()
    var helloWorldFeed = listOf<ConsumerRecordWithStringValue>()

    @KafkaListener(topics = [feedTopic], containerFactory = "listenerFactory", groupId = "#{T(java.util.UUID).randomUUID().toString()}")
    fun receiveStatusFeedRecord(
        @Header(KafkaHeaders.RECEIVED_KEY) key: String,
        @Header(KafkaHeaders.RECEIVED_PARTITION) partition: Int,
        @Header(KafkaHeaders.OFFSET) offset: Long,
        @Header(KafkaHeaders.RECEIVED_TIMESTAMP) timestamp: Long,
        @Header(KafkaHeaders.GROUP_ID) groupId: String,
        @Payload record: BekkbookStatusMessage
    ) {
        statusFeed = statusFeed.takeLast(50).plus(
            WorkshopStatusMessageConsumerRecord(
                feedTopic,
                partition,
                offset,
                timestamp,
                key,
                BekkbookStatusMessageData(record.message)
            )
        )

    }

    @KafkaListener(topics = ["hello-world"], containerFactory = "stringListenerFactory", groupId = "#{T(java.util.UUID).randomUUID().toString()}")
    fun receiveHelloWorldRecord(
        @Header(KafkaHeaders.RECEIVED_KEY) key: String,
        @Header(KafkaHeaders.RECEIVED_PARTITION) partition: Int,
        @Header(KafkaHeaders.OFFSET) offset: Long,
        @Header(KafkaHeaders.RECEIVED_TIMESTAMP) timestamp: Long,
        @Header(KafkaHeaders.GROUP_ID) groupId: String,
        @Payload record: String
    ) {
        helloWorldFeed = helloWorldFeed.takeLast(50).plus(
            ConsumerRecordWithStringValue(
                "hello-world",
                partition,
                offset,
                timestamp,
                key,
                record
            )
        )

    }

    companion object {
        const val feedTopic = "workshop-status-message"
    }
}

data class BekkbookStatusMessageData(
    val message: String
)

data class WorkshopStatusMessageConsumerRecord(
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
