package Server;

import java.io.*;
import java.net.Socket;

/**
 * Created by mnt_x on 22/02/2017.
 */
public class Packets implements Runnable {

    protected Socket clientSocket = null;

    public Packets(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public void run() {
        try {
//            InputStream input  = clientSocket.getInputStream();
//            OutputStream output = clientSocket.getOutputStream();

            ObjectInputStream output =  new ObjectInputStream(clientSocket.getInputStream());

            ObjectOutputStream input = new ObjectOutputStream(clientSocket.getOutputStream());

            output.close();
            input.close();


        } catch (IOException e) {
            //report exception somewhere.
            e.printStackTrace();
        }
    }
}
