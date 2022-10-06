package io.entur.model

data class TopicPermission(val topicName: String, val allowConsume: Boolean, val allowProduce: Boolean)
data class UserTopicPermission(val username: String, val topicName: String, val allowConsume: Boolean, val allowProduce: Boolean)
