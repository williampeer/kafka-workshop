from kafka import KafkaProducer

bootstrap_server = 'kafka-workshop-001-kafka-workshop.aivencloud.com:13816'
producer = KafkaProducer(bootstrap_servers=bootstrap_server, security_protocol='SASL_SSL',
                         sasl_mechanism='SCRAM-SHA-256', sasl_plain_username='workshop',
                         sasl_plain_password='<change-me>')

# Produce a message to the topic "hello-world"
producer.send(topic='hello-world', value=b'hello from Python!', key=b'hello-world-python-key')
producer.flush()
