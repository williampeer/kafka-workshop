
# Paste-bin / cheat sheet - not meant to be run

kafka-topics --create --topic hello-world --bootstrap-server localhost:9092
kafka-topics --delete --topic hello-world --bootstrap-server localhost:9092

kafka-topics --describe --topic hello-world --bootstrap-server localhost:9092
# kafka-topics --alter --topic hello-world --bootstrap-server localhost:9092 --partitions 15

kafka-consumer-groups --bootstrap-server localhost:9092 --list
