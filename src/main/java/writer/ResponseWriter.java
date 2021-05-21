package writer;

import http.HttpResponse;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ResponseWriter {

    private static final int BUFFER_SIZE = 256;
    private final Logger LOG = Logger.getLogger(ResponseWriter.class.getName());

    public void writeResponse(AsynchronousSocketChannel client, HttpResponse response) {
        ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);

        LOG.log(Level.INFO,"Writing response");
        client.write(buffer.wrap((String.format(response.getHeaders(), response.getBody().length()) + response.getBody()).getBytes()));
    }

}
