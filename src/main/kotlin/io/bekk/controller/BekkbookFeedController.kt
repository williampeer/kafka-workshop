package io.bekk.controller

import io.bekk.publisher.BekkbookStatusMessage
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class BekkbookFeedController {

    @GetMapping("/status-feed/")
    fun getStatusFeed(): ResponseEntity<List<BekkbookStatusMessage>> {
        // TODO: implement me
        return ResponseEntity.ok(emptyList())
    }

//    ---- OUT OF SCOPE -------
//    fun getAllNewMessagesForTopic()
//    fun streamMessageFeed()

    // Optional:
    @GetMapping("/messages/{topic}")
    fun getAllMessagesForTopic(
        @PathVariable(value = "topic")
        topic: String
    ): ResponseEntity<List<String>> {
        // TODO: implement me
        return ResponseEntity.ok(emptyList())
    }
}