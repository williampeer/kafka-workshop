package io.bekk.controller

import io.bekk.repository.BekkbookStatusMessageConsumerRecord
import io.bekk.repository.BekkbookStatusMessageData
import io.bekk.repository.ConsumerRecordWithStringValue
import io.bekk.repository.FeedRepository
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
    fun getStatusFeed(): ResponseEntity<BekkbookStatusMessageConsumerRecordList> {
        return ResponseEntity.ok(
            BekkbookStatusMessageConsumerRecordList(recordList = feedRepository.getFeed())
        )
    }

//    ---- OUT OF SCOPE -------
//    @GetMapping("/consumer-records/{topic}")
//    fun getConsumerRecordsForTopic(
//        @PathVariable(value = "topic")
//        topic: String
//    ): ResponseEntity<ConsumerRecordWithStringValueList> {
//        return ResponseEntity.ok(
//            ConsumerRecordWithStringValueList(feedRepository.getAllRecords(topic))
//        )
//    }

// Optional:
//    fun getAllNewMessagesForTopic()
//    fun streamMessageFeed()
}

//data class ConsumerRecordWithStringValueList(
//    val recordList: List<ConsumerRecordWithStringValue>
//)

data class BekkbookStatusMessageList(
    val recordList: List<BekkbookStatusMessageData>
)

data class BekkbookStatusMessageConsumerRecordList(
    val recordList: List<BekkbookStatusMessageConsumerRecord>
)
