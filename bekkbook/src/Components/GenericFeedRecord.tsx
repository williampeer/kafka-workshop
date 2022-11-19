import React from "react";
import {BekkbookMessageRecord, ConsumerRecordWithStringValue} from "../types/ApiTypes";


type GenericFeedRecordProps = {
    record: ConsumerRecordWithStringValue
}

const GenericFeedRecord = ( { record }: GenericFeedRecordProps ) => (
    <div style={{ padding: "2rem 1rem", borderBottom: "solid", borderColor: "beige" }}>
        Topic: {record.topicName}<br/>
        partition:{record.partition}{', '}offset:{record.partition}
        <br/>
        Key: {record.key}
        <br/>
        Timestamp: {record.timestamp}
        <br/>
        <br/>
        {record.value}
    </div>
)

export default GenericFeedRecord;