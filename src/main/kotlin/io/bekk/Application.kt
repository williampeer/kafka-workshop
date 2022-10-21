package io.bekk

import io.bekk.properties.KafkaProps
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(KafkaProps::class)
@ConfigurationPropertiesScan("io.bekk.config")
class Application
fun main(args: Array<String>) {
    runApplication<Application>(*args)
}
