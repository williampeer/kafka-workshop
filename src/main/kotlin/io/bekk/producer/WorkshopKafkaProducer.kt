package io.bekk.producer

import org.apache.avro.generic.GenericRecord
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.common.header.Header
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.support.SendResult

class WorkshopKafkaProducer<V : GenericRecord>(private val kafkaTemplate: KafkaTemplate<String, V>) {
    fun send(
        topic: String,
        key: String,
        payload: V,
        onSuccessCallback: ((result: SendResult<String, V>) -> Unit)? = null,
        failureCallback: ((e: Throwable) -> Unit)? = null,
        customHeaders: List<Header>? = null
    ) {
        ProducerRecord(topic, key, payload).apply {
            customHeaders?.forEach {
                headers().add(it)
            }
        }.also { record ->
            kafkaTemplate.send(record).addCallback({ success ->
                success?.let { onSuccessCallback?.invoke(success) }
            }, { failure ->
                failureCallback?.invoke(failure)
            })
        }
    }
}