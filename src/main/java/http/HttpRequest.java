package http;

import java.util.Map;

public class HttpRequest {

    private String type;
    private String mapping;
    private Map<String,String> headers;
    private String body;

    public HttpRequest(String type, String mapping, Map<String, String> headers, String body) {
        this.type = type;
        this.mapping = mapping;
        this.headers = headers;
        this.body = body;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type=type;
    }

    public String getMapping() {
        return mapping;
    }

    public void setMapping(String mapping) {
        this.mapping = mapping;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
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
        return "Type: " + type + ",\n" +
               "Mapping: " + mapping + ",\n" +
               "Headers: " + headers.toString() + ",\n" +
               "Body: " + body + "\r\n";
    }
}
