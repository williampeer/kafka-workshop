
# Paste-bin / cheat sheet - not meant to be run

kafka-topics --create --topic hello-world --bootstrap-server localhost:9092
kafka-topics --delete --topic hello-world --bootstrap-server localhost:9092

kafka-topics --describe --topic hello-world --bootstrap-server localhost:9092
# kafka-topics --alter --topic hello-world --bootstrap-server localhost:9092 --partitions 15

kafka-consumer-groups --bootstrap-server localhost:9092 --list

kafka-topics --bootstrap-server localhost:9092 --create --topic hello-world \
    --partitions 15 --replication-factor 1 \
    --config cleanup.policy=compact \
    --config min.cleanable.dirty.ratio=0.001 \
    --config segment.ms=5000

kafka-topics --bootstrap-server localhost:9092 --describe --topic hello-world

#keytool -importcert -alias ssl_workshop -keystore $JAVA_HOME/lib/security/cacerts -file ~/Downloads/workshop_cert.pem
