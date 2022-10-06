package challenges

class Challenge_1 {

    // Create your own Avro-schema and associated topic, add a consumer to listen to the topic, and then
    //  produce a message using the schema to serialise it, and verify that the message was sent by checking that
    //  your listener did indeed consume the message.
    //  However, if multiple consumers are subscribed to a topic, will this still work?
    //      Answer: Yes, as long as the consumers are in unique consumer groups. Consumers in the same consumer group
    //      will distribute partitions among them, and consume the next messages put on their partitions.
    //      Can you think of a scenario where this might be useful?
}