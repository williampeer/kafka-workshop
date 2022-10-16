
declare namespace Model {
    namespace FeedEvent {
        export interface BekkbookStatusMessage
        {
            offset?: string;
            partition?: string;
            timestamp?: string;
            message: string;
        }
    }
}

export {};