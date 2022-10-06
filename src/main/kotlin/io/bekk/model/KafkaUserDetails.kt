package io.entur.model

data class KafkaUserDetails(
    val kafkaUser: KafkaUser,
    val topicPermissions: List<TopicPermission> = emptyList()
)
