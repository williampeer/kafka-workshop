package io.bekk.repository

import io.bekk.publisher.BekkbookStatusMessage
import org.springframework.stereotype.Repository
import java.time.Duration

@Repository
class FeedRepository(
    final val kafkaClientRepository: KafkaClientRepository
) {
    final val consumer = kafkaClientRepository.getConsumer<BekkbookStatusMessage>(
        groupId = "server-consumer",
        earliest = true,
        autoCommit = false,
        specificAvro = true
    )

    init {
        consumer.seekToBeginning(consumer.assignment())
    }

    fun getFeed(): List<BekkbookStatusMessage> {
        val statusFeed = consumer.poll(Duration.ofSeconds(1))
        return statusFeed.map { it.value() }.toList()
    }
}