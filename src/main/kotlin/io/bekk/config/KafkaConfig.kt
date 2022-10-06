package io.bekk.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("app.kafka")
class KafkaConfig {
    lateinit var bootstrapServer: String
    lateinit var securityProtocol: String
    lateinit var schemaRegistryUrl: String
}