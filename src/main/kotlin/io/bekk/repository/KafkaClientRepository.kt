package io.bekk.repository

import io.bekk.config.KafkaConfig
import io.confluent.kafka.serializers.AbstractKafkaSchemaSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG
import io.confluent.kafka.serializers.KafkaAvroDeserializerConfig
import org.apache.kafka.clients.CommonClientConfigs
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerConfig
import org.springframework.stereotype.Repository

@Repository
class KafkaClientRepository(
    final val kafkaConfig: KafkaConfig
) {
    val defaultClientConfig = mapOf(
        CommonClientConfigs.BOOTSTRAP_SERVERS_CONFIG to kafkaConfig.bootstrapServer,
        CommonClientConfigs.SECURITY_PROTOCOL_CONFIG to kafkaConfig.securityProtocol,
        SCHEMA_REGISTRY_URL_CONFIG to kafkaConfig.schemaRegistryUrl,
    )

    fun <V> getConsumer(
        groupId: String,
        earliest: Boolean = true,
        autoCommit: Boolean = false,
        specificAvro: Boolean = true
    ): KafkaConsumer<String, V> {
        return KafkaConsumer<String, V>(
            consumerProperties(
                defaultProps = defaultClientConfig + defaultConsumerConfig(),
                groupId = groupId,
                earliest = earliest,
                autoCommit = autoCommit,
                specificAvro = specificAvro
            )
        )
    }

    fun <V> getProducer(): KafkaProducer<String, V> {
        return KafkaProducer<String, V>(defaultClientConfig + defaultProducerConfig())
    }


    companion object {
        fun defaultConsumerConfig() = mapOf(
            ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG to "org.apache.kafka.common.serialization.StringDeserializer",
            ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG to "io.confluent.kafka.serializers.KafkaAvroDeserializer"
        )

        fun defaultProducerConfig() = mapOf(
            ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG to "org.apache.kafka.common.serialization.StringSerializer",
            ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG to "io.confluent.kafka.serializers.KafkaAvroSerializer"
        )

        fun consumerProperties(
            defaultProps: Map<String, Any>,
            groupId: String,
            earliest: Boolean = true,
            autoCommit: Boolean = false,
            specificAvro: Boolean = true
        ): Map<String, Any> =
            defaultProps + mapOf(
                ConsumerConfig.AUTO_OFFSET_RESET_CONFIG to if (earliest) "earliest" else "latest",
                ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG to autoCommit.toString(),
                ConsumerConfig.GROUP_ID_CONFIG to groupId,
                KafkaAvroDeserializerConfig.SPECIFIC_AVRO_READER_CONFIG to if (specificAvro) "true" else "false"
            )
    }
}