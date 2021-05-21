package http.parser;

import http.HttpRequest;

import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HttpRequestParser {

    private final Logger LOG = Logger.getLogger(HttpRequestParser.class.getName());

    public HttpRequest parseRequest(String request) {
        LOG.log(Level.INFO,"Parsing request...");
        return new HttpRequest(parseType(request),parseMapping(request),parseHeaders(request),parseBody(request));
    }

    private String parseType(String request) {
        LOG.log(Level.INFO,"Parsing request type...");
        return request.substring(0,request.indexOf("/")).replaceAll(" ","");
    }

    private String parseMapping(String request) {
        LOG.log(Level.INFO,"Parsing request mapping...");
        return request.substring(request.indexOf("/"),request.indexOf("H")).replaceAll(" ","");
    }

    private Map<String,String> parseHeaders(String request) {
        LOG.log(Level.INFO,"Parsing request headers...");

        Map<String,String> headersMap = new TreeMap<>();

        /* убираем лишнее */
        String newRequest = request.replace("HTTP/1.1","");
        String newRequest1 = newRequest.replace(newRequest.substring(0,newRequest.indexOf("H")),"");
        /* -------------- */

        String[] headers = newRequest1.substring(newRequest1.indexOf("H"),newRequest1.indexOf("{")).replaceAll(" ","").split("\n");

        for(String header : headers) {
            if(header.equals("\r")) break;
            headersMap.put(header.substring(0,header.indexOf(":")),header.substring(header.indexOf(":"),header.length()-1));
        }
        return headersMap;
    }

    private String parseBody(String request) {
        LOG.log(Level.INFO,"Parsing request body...");
        return request.substring(request.indexOf("{"),request.indexOf("}")+1);
    }


}
