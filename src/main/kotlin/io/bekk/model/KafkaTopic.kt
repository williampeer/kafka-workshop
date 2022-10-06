package io.entur.model

data class KafkaTopic(
    val name: String,
    val replicationFactor: Int,
    val partitions: Int,
    val retentionMs: Long,
    val cleanupPolicy: CleanupPolicy
) {
    enum class CleanupPolicy {
        COMPACT, DELETE
    }
}
