
import http.handler.HttpHandler;
import http.parser.HttpRequestParser;
import http.HttpResponse;
import reader.RequestReader;
import writer.ResponseWriter;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WebServer {

    private final HttpRequestParser parser;
    private final HttpHandler httpHandler;
    private final RequestReader requestReader;
    private final ResponseWriter responseWriter;

    private final Logger LOG;

    private final String host;
    private final int port;

    public WebServer() {
        parser=new HttpRequestParser();
        httpHandler=new HttpHandler();
        requestReader=new RequestReader();
        responseWriter=new ResponseWriter();

        LOG = Logger.getLogger(WebServer.class.getName());

        host="localhost";
        port=333;
    }

    public void start() throws IOException, ExecutionException, InterruptedException, IllegalAccessException, InvocationTargetException, URISyntaxException, InstantiationException {
        AsynchronousServerSocketChannel server = AsynchronousServerSocketChannel.open();
        server.bind(new InetSocketAddress("localhost",333));
        LOG.log(Level.INFO,String.format("Server started at host %s, port %d",host,port));
        AsynchronousSocketChannel client = server.accept().get();
        LOG.log(Level.INFO,"Client connected");

        String request = requestReader.readRequest(client);
        HttpResponse response = httpHandler.handle(parser.parseRequest(request));
        responseWriter.writeResponse(client,response);
        LOG.log(Level.INFO,"Connection with client closed");
    }

}
