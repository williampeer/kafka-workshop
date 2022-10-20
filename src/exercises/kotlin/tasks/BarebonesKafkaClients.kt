package tasks

import io.confluent.kafka.serializers.AbstractKafkaSchemaSerDeConfig
import io.confluent.kafka.serializers.KafkaAvroDeserializerConfig
import org.apache.kafka.clients.CommonClientConfigs
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerConfig
import java.util.*

object BarebonesKafkaClients {

    private const val BOOTSTRAP_SERVER_URL = "10.52.1.71:9092"
    private const val SCHEMA_REGISTRY_URL = "http://10.52.1.71:8081"
    fun getBareBonesProducer(): KafkaProducer<String, String> {
        val configMap = mapOf(
            ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG to BOOTSTRAP_SERVER_URL,
            ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG to "org.apache.kafka.common.serialization.StringSerializer",
            ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG to "org.apache.kafka.common.serialization.StringSerializer"
        )
        return KafkaProducer<String, String>(configMap)
    }

    fun getBareBonesConsumer(
        offsetConfig: String = "latest",
        groupId: String = "my-consumer-${UUID.randomUUID()}",
        config: Map<String, String> = emptyMap()
    ) =
        KafkaConsumer<String, String>(
            config +
                    mapOf(
                        ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG to "org.apache.kafka.common.serialization.StringDeserializer",
                        ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG to "org.apache.kafka.common.serialization.StringDeserializer",
                        ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG to BOOTSTRAP_SERVER_URL,
                        ConsumerConfig.GROUP_ID_CONFIG to groupId,
                        ConsumerConfig.AUTO_OFFSET_RESET_CONFIG to offsetConfig,
                    )
        )

    fun <V> getAvroProducer(): KafkaProducer<String, V> =
        KafkaProducer<String, V>(
            mapOf(
                CommonClientConfigs.BOOTSTRAP_SERVERS_CONFIG to BOOTSTRAP_SERVER_URL,
                AbstractKafkaSchemaSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG to SCHEMA_REGISTRY_URL,
                ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG to "org.apache.kafka.common.serialization.StringSerializer",
                ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG to "io.confluent.kafka.serializers.KafkaAvroSerializer"
            )
        )

    fun <V> getAvroConsumer(groupId: String): KafkaConsumer<String, V> =
        KafkaConsumer<String, V>(
            mapOf(
                CommonClientConfigs.BOOTSTRAP_SERVERS_CONFIG to BOOTSTRAP_SERVER_URL,
                AbstractKafkaSchemaSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG to SCHEMA_REGISTRY_URL,
                ConsumerConfig.GROUP_ID_CONFIG to groupId,
                KafkaAvroDeserializerConfig.SPECIFIC_AVRO_READER_CONFIG to "true",
                ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG to "org.apache.kafka.common.serialization.StringDeserializer",
                ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG to "io.confluent.kafka.serializers.KafkaAvroDeserializer"
            )
        )

}

