
export type BekkbookMessage = {
    message: string;
}

export type BekkbookMessageRecord = {
    topicName: string;
    partition?: string;
    offset?: string;
    timestamp?: string;
    key: string;
    value: BekkbookMessage;
}

export type BekkbookMessageRecordList = {
    recordList: BekkbookMessageRecord[]
}


export type ConsumerRecord = {
    topicName: string;
    partition?: string;
    offset?: string;
    timestamp?: string;
    key: string;
    value: string;
}

export type ConsumerRecords = ConsumerRecord[]
