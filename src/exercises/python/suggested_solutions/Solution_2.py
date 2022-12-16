from uuid import uuid1

from kafka import KafkaConsumer

bootstrap_server = 'kafka-workshop-001-kafka-workshop.aivencloud.com:13816'
consumer = KafkaConsumer(bootstrap_servers=bootstrap_server, security_protocol='SASL_SSL',
                         sasl_mechanism='SCRAM-SHA-256', sasl_plain_username='workshop',
                         sasl_plain_password='AVNS_TKo_VY74K_CVc2wTHR0',
                         group_id=str(uuid1()), auto_offset_reset='earliest')

# Task_2
consumer.subscribe(["hello-world"])
while 1:
    records_from_broker = consumer.poll(timeout_ms=3000)
    for message in records_from_broker.values():
        print(message)
