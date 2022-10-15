export const API_URL = "localhost:9092"

async function doGetRequestForUrl<T>(
    url: string,
    expectedResponse = 200
): Promise<T> {
    return fetch(url).then((response) => {
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

async function doPostRequestToUrl<T>(
    url: string,
    stringifiedPayload: string,
    expectedResponse = 201
): Promise<T> {
    return fetch(url, {
        method: "POST",
        body: stringifiedPayload,
    }).then(async (response) => {
        if (response.status === expectedResponse) {
            return response.json();
        } else {
            const error = await response.json();
            throw new Error(error.message);
        }
    });
}