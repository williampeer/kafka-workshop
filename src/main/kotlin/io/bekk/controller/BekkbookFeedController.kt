package io.bekk.controller

import io.bekk.repository.FeedRepository
import io.bekk.repository.BekkbookMessageConsumerRecord
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class BekkbookFeedController(
    val feedRepository: FeedRepository
) {

    @CrossOrigin(origins = ["*"])
    @GetMapping("/status-feed/")
    fun getStatusFeed(): ResponseEntity<BekkbookMessageConsumerRecordList> {
        return ResponseEntity.ok(
            BekkbookMessageConsumerRecordList(recordList = feedRepository.getFeed())
        )
    }

    @GetMapping("/consumer-records/{topic}")
    fun <V> getConsumerRecordsForTopic(
        @PathVariable(value = "topic")
        topic: String
    ): ResponseEntity<List<ConsumerRecord<String, V>>> {
        return ResponseEntity.ok(feedRepository.getAllRecords(topic))
    }

//    ---- OUT OF SCOPE -------
// Optional:
//    fun getAllNewMessagesForTopic()
//    fun streamMessageFeed()
}

data class BekkbookMessageConsumerRecordList(
    val recordList: List<BekkbookMessageConsumerRecord>
)
