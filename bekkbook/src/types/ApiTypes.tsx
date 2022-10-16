
export type BekkbookStatusMessage = {
    offset?: string;
    partition?: string;
    timestamp?: string;
    message: string;
}

export type BekkbookStatusMessageList = {
    statusFeed: BekkbookStatusMessage[]
}
