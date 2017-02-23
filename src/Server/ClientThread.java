package Server;

import Server.Communications.*;
import Server.Database.Database;
import Server.Database.MessengerDatabase;

import java.io.*;
import java.net.Socket;

/**
 * Created by mnt_x on 22/02/2017.
 */
public class ClientThread implements Runnable {

    private Socket clientSocket = null;
    private ObjectInputStream input;
    private ObjectOutputStream output;

    /**
     *
     * @param clientSocket
     */
    public ClientThread(Socket clientSocket) {

        try {

            this.clientSocket = clientSocket;
            input =  new ObjectInputStream(clientSocket.getInputStream());
            output = new ObjectOutputStream(clientSocket.getOutputStream());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     */
    public void run() {

        Database db = new MessengerDatabase();

        try {

            CommunicationHandler ch = new CommunicationHandler(input.readObject(), output, db);
            ch.parseCommunication();

            output.close();
            input.close();
        } catch (IOException e) {

            e.printStackTrace();
        } catch (ClassNotFoundException e) {

            e.printStackTrace();
        }

        db.close();
    }
}
