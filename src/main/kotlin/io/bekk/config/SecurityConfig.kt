package io.bekk.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

@Configuration
class SecurityConfig {

    @Bean("corsConfigurationSource")
    fun myCorsConfigurationSource(@Value("\${app.cors}") corsLocations: String): CorsConfigurationSource =
        UrlBasedCorsConfigurationSource()
            .also {
                it.registerCorsConfiguration(
                    "/**",
                    CorsConfiguration()
                        .apply {
                            allowedOrigins = corsLocations.split(";")
                            allowCredentials = true
                            allowedMethods = listOf(
                                HttpMethod.GET.name,
                                HttpMethod.PUT.name,
                                HttpMethod.DELETE.name,
                                HttpMethod.PATCH.name,
                                HttpMethod.POST.name
                            )
                            addAllowedHeader(HttpHeaders.AUTHORIZATION)
                            addAllowedHeader(HttpHeaders.CONTENT_TYPE)
                        }
                )
            }
}
