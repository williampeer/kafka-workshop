
export type BekkbookMessageRecord = {
    topicName: string;
    partition?: string;
    offset?: string;
    timestamp?: string;
    key: string;
    message: string;
}

export type BekkbookMessageRecordList = {
    recordList: BekkbookMessageRecord[]
}
