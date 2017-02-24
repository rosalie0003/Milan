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
     * Constructor setup input output stream and assign socket to field variable
     * @param clientSocket
     */
    public ClientThread(Socket clientSocket) {

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
        // each thread needs a queue

        // create database connection ---- consider connection pool when I have more time
        Database db = new MessengerDatabase();

        try {

            // pass intput and output and db connection to connectionHandler to parse incoming objects
            CommunicationHandler ch = new CommunicationHandler(input.readObject(), output, db);
            synchronized (ch){
                ch.parseCommunication();
                // if message is received notify other threads to query database for new message to send to client
                if(ch.isMessageReceived()){

                    // set false after doing stuff
                    ch.setMessageReceived(false);
                }

            }

            System.out.println("message received");

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
