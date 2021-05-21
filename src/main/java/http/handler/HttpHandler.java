package http.handler;

import annotations.Controller;
import annotations.Mapping;
import com.google.gson.Gson;
import dto.Dto;
import http.HttpRequest;
import http.HttpResponse;
import org.reflections.Reflections;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HttpHandler {

    private static final String headers =
            "HTTP/1.1 200 OK\n" +
                    "Server: CustomWebServer\n" +
                    "Content-Type: text/html\n" +
                    "Content-Length: %s\n" +
                    "Connection: close\n\n";
    private static final Logger LOG = Logger.getLogger(HttpHandler.class.getName());

    public HttpResponse handle(HttpRequest request) throws IOException, URISyntaxException, InvocationTargetException, IllegalAccessException, InstantiationException {
        LOG.log(Level.INFO,"Handling request");
        Reflections reflections = new Reflections();
        Gson gson = new Gson();

        LOG.log(Level.INFO,"Getting controller classes");
        Set<Class<?>> classes = reflections.getTypesAnnotatedWith(Controller.class);

        for(Class<?> c : classes) {
            LOG.log(Level.INFO,String.format("Getting methods of %s class",c.getName()));
            Method[] methods = c.getDeclaredMethods();
            for(Method m : methods) {
                Mapping mapping = m.getAnnotation(Mapping.class);
                if(mapping.mapping().equals(request.getMapping())) {
                    LOG.log(Level.INFO,"Creating response");
                    HttpResponse response = new HttpResponse(headers,(String) m.invoke(c.newInstance(),gson.fromJson(request.getBody(), Dto.class)));
                    LOG.log(Level.INFO,"Response " + response);
                    return response;
                }
            }
        }

        return null;
    }

}
