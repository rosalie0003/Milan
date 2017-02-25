package Server;

import Server.communications.*;
import Server.database.Database;
import Server.database.MessengerDatabase;

import java.io.*;
import java.net.Socket;

/**
 * Created by mnt_x on 22/02/2017.
 */
public class ClientThread extends Thread implements PacketHandler {

    private Server server;

    private Socket clientSocket = null;
    private ObjectInputStream input;
    private ObjectOutputStream output;
    private int userID;

    /**
     * Constructor setup input output stream and assign socket to field variable
     * @param clientSocket
     */
    public ClientThread(Server server, Socket clientSocket) {

        this.server = server;

        try {

            this.clientSocket = clientSocket;
            input = new ObjectInputStream(clientSocket.getInputStream());
            output = new ObjectOutputStream(clientSocket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     */
    public synchronized void run() {
        System.out.println();
        while(true) {

            for (int i = 0; i <10; i++) {
                System.out.print(i);
            }
            // each thread needs a queue

            // create database connection ---- consider connection pool when I have more time

            try {

                // pass input and output and db connection to connectionHandler to parse incoming objects
                CommunicationHandler ch = new CommunicationHandler(input.readObject(), output);
                synchronized (ch) {
                    ch.parseCommunication();
                    // if message is received notify other threads to query database for new message to send to client
                    if (ch.isMessageReceived()) {

                        // set false after doing stuff
                        ch.setMessageReceived(false);
                    }

                }


                output.close();
                input.close();
            } catch (IOException e) {

                e.printStackTrace();
            } catch (ClassNotFoundException e) {

                e.printStackTrace();
            }
        }
    }
}
