package http;

public class HttpResponse {

    private String headers;
    private String body;

    public HttpResponse(String headers, String body) {
        this.headers = headers;
        this.body = body;
    }

    public String getHeaders() {
        return headers;
    }

    public void setHeaders(String headers) {
        this.headers = headers;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "Headers: " + headers +
               "Body: " + body;
    }

}
