package io.bekk.repository

import io.bekk.publisher.BekkbookStatusMessage
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.support.KafkaHeaders
import org.springframework.messaging.handler.annotation.Header
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Repository
import java.time.Duration

@Repository
class FeedRepository {

    var feed = listOf<BekkbookStatusMessageConsumerRecord>()

    @KafkaListener(topics = [feedTopic], containerFactory = "listenerFactory", groupId = groupId)
    fun receiveTestRecord(
        @Header(KafkaHeaders.RECEIVED_KEY) key: String,
        @Header(KafkaHeaders.RECEIVED_PARTITION) partition: Int,
        @Header(KafkaHeaders.OFFSET) offset: Long,
        @Header(KafkaHeaders.RECEIVED_TIMESTAMP) timestamp: Long,
        @Header(KafkaHeaders.GROUP_ID) groupId: String,
        @Payload record: BekkbookStatusMessage
    ) {
        feed = feed.takeLast(50).plus(BekkbookStatusMessageConsumerRecord(
            feedTopic,
            partition,
            offset,
            timestamp,
            key,
            BekkbookStatusMessageData(record.message)
        ))

    }

    companion object {
        const val feedTopic = "bekkbook-status-message"
        const val groupId = "server-consumer"
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
