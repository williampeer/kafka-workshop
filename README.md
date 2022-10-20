
# Kafka-workshop

## For the participant

Please find the introductory tasks under `./src/main/kotlin/tasks/` and by checking out branch task-<task-number>

Once you have completed the task at hand, you may proceed to the next task by checkout out the next branch, and will
also find a suggested solution to the previous task in the succeeding branch.

For the challenges (which may be found under `../challenges/`) you may go about solving any one you'd like, 
without any specific given or suggested order - or perhaps you'd even like to integrate kafka with one of your own 
applications or projects?


## For the workshop-admin

### Frontend

If you wish to display events about groups' progress throughout the workshop, such as consumed and produced messages, 
you may run the frontend implemented under bekkbook/. This is essentially a simple React-webapp that fetches 
messages from a lightweight spring-boot backend, which in turn listens to messages from the workshop kafka-cluster.

### Backend

A minimalistic Spring boot app, defined in `/src/main/.../Application.kt`, 
with Kafka clients dependencies setup in the gradle build-file.

### Kafka-cluster

If you wish to run a cluster of your own, you may do so by running `/kafka-cluster/docker.sh` 
(assuming that docker is intalled), or in a more bare-bones version by using the property-files defined in the 
alternate-kafka-config-folder.

