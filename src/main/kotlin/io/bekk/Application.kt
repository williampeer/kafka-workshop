package io.bekk

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan("io.bekk.config")
class Application
fun main(args: Array<String>) {
    runApplication<Application>(*args)
}
