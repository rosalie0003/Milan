package Server;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * Created by mnt_x on 22/02/2017.
 */
public class Server {

    private int port;
    private ServerGUI sg;

    public Server(int port, ServerGUI sg) {

        this.port = port;

        this.sg = sg;

    }

    public void start(){

        try {
            ServerSocket serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void stop() {

    }
}
