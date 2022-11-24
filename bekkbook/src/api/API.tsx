import {BekkbookMessageRecordList, ConsumerRecordWithStringValueList} from "../types/ApiTypes";

export const API_URL = "https://cloud-native-kafka-workshop.herokuapp.com"

async function doGetRequestForUrl<T>(
    url: string,
    expectedResponse = 200
): Promise<T> {
    return fetch(url, {
        headers: { 'Access-Control-Allow-Origin': '*' }
    }).then((response) => {
        if (response.status === expectedResponse) {
            return response.json();
        } else {
            throw new Error(
                response.statusText ? response.statusText : "Unexpected response"
            );
        }
    });
}

async function doGetRequest<T>(
    path: string,
    expectedResponse = 200
): Promise<T> {
    const url = `${API_URL}${path}`;
    return doGetRequestForUrl(url, expectedResponse);
}

export const getStatusFeed = async(): Promise<BekkbookMessageRecordList> => {
    return doGetRequest("/status-feed/")
}

export const getHelloWorldFeed = async(): Promise<ConsumerRecordWithStringValueList> => {
    return doGetRequest("/hello-world-feed/")
}
