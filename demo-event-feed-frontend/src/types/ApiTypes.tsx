
export type BekkbookMessage = {
    message: string;
}

export type BekkbookMessageRecord = {
    topicName: string;
    partition: number;
    offset: number;
    timestamp: number;
    key: string;
    value: BekkbookMessage;
}

export type ConsumerRecordWithStringValue = {
    topicName: string;
    partition: number;
    offset: number;
    timestamp: number;
    key: string;
    value: string;
}

export type BekkbookMessageRecordList = {
    recordList: BekkbookMessageRecord[]
}

export type ConsumerRecordWithStringValueList = {
    recordList: ConsumerRecordWithStringValue[]
}


export type ConsumerRecord = {
    topicName: string;
    partition: string;
    offset: string;
    timestamp: string;
    key: string;
    value: string;
}

export type ConsumerRecords = ConsumerRecord[]
