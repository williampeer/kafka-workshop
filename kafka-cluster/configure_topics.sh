
kafka-topics --bootstrap-server localhost:9092 --create --topic hello-world \
    --partitions 15 --replication-factor 1 \
    --config cleanup.policy=compact \
    --config min.cleanable.dirty.ratio=0.001 \
    --config segment.ms=5000
