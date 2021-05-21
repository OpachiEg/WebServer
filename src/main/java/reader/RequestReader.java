package reader;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RequestReader {

    private static final int BUFFER_SIZE = 256;
    private static final Logger LOG = Logger.getLogger(RequestReader.class.getName());

    public String readRequest(AsynchronousSocketChannel client) throws ExecutionException, InterruptedException {
        LOG.log(Level.INFO,"Reading request...");
        ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);

        StringBuilder sb = new StringBuilder();
        boolean reading = true;
        while (reading) {
            client.read(buffer).get();
            sb.append(new String(Arrays.copyOfRange(buffer.array(), 0, buffer.position())));
            LOG.log(Level.INFO,"Read " + buffer.position());
            buffer.clear();
            if(sb.toString().endsWith("\n}")) reading=false;
        }
        LOG.log(Level.INFO,"Request reading is done");
        LOG.log(Level.INFO,"Request: \n" + sb.toString());
        return sb.toString();
    }

}
