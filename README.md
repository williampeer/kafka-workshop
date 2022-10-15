
# Kafka-workshop

Please find the introductory tasks under `./src/main/kotlin/io.bekk/tasks/`

We recommend that you solve these tasks in the order that is given.

However, for the challenges (which may be found under `../challenges/`) you may go about solving them in any order that you'd like - or perhaps you'd even like to integrate kafka with your own application?

## Workshop-admin

### Frontend

If you wish to display events about groups' progress throughout the workshop, such as consumed and produced messages, you may run the frontend (WIP) implemented under bekkbook/. This is essentially a simple React-webapp that fetches messages from a backend-server (TODO) which in turn listens to messages from the workshop kafka-cluster.

### Backend

A minimalistic Spring boot app, defined in `/src/main/.../Application.kt`, with Kafka clients dependencies setup in the gradle build-file.

### Kafka-cluster

If you wish to run a cluster of your own, you may do so by running `/kafka-cluster/docker.sh` (assuming that docker is intalled), or in a more bare bones version by using the property-files defined in the alternate-kafka-config-folder.

