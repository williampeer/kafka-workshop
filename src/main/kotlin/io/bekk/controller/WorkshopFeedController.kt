package io.bekk.controller

import io.bekk.repository.WorkshopStatusMessageConsumerRecord
import io.bekk.repository.ConsumerRecordWithStringValue
import io.bekk.repository.FeedRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class WorkshopFeedController(
    val feedRepository: FeedRepository
) {

    @CrossOrigin(origins = ["*"])
    @GetMapping("/status-feed/")
    fun getStatusFeed(): ResponseEntity<WorkshopStatusMessageConsumerRecordList> {
        return ResponseEntity.ok(
            WorkshopStatusMessageConsumerRecordList(recordList = feedRepository.statusFeed)
        )
    }

    @CrossOrigin(origins = ["*"])
    @GetMapping("/hello-world-feed/")
    fun getHelloWorldFeed(): ResponseEntity<ConsumerRecordWithStringValueList> {
        return ResponseEntity.ok(
            ConsumerRecordWithStringValueList(recordList = feedRepository.helloWorldFeed)
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

data class WorkshopStatusMessageConsumerRecordList(
    val recordList: List<WorkshopStatusMessageConsumerRecord>
)
data class ConsumerRecordWithStringValueList(
    val recordList: List<ConsumerRecordWithStringValue>
)
