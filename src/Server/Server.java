package Server;

import Server.database.*;
import communications.Packet;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * @author Laurie Dugdale
 */
public class Server implements Runnable {

    private int port;
    private ServerGUI sg;
    private ServerSocket serverSocket = null;
    private boolean isStopped = false;
    private Thread runningThread= null;
    private Map<String, ClientThread> threads;
    Database db;

    public Server(int port) {

        this.port = port;
    }

    public Server(int port, ServerGUI sg) {

        this.port = port;

        this.sg = sg;

        db = new MessengerDatabase();

        threads = new HashMap<>();
    }


    /**
     * Server pool creating new Client thread
     */
    public void run(){

        synchronized(this){
            this.runningThread = Thread.currentThread();
        }
        openServerSocket();

        while(! isStopped()){

            try {

                Socket clientSocket = this.serverSocket.accept();

                threads.put("123", new ClientThread(this, clientSocket));
                threads.get("123").run();
            } catch (IOException e) {

                if(isStopped()) {

                    System.out.println("Server Stopped.") ;
                    break;
                }

                throw new RuntimeException( "Error accepting client connection", e);
            }
            // Create new clientThread on connection
        }
        System.out.println("Server Stopped.") ;
    }


    private synchronized boolean isStopped() {

        return this.isStopped;
    }

    public synchronized void stop(){

        this.isStopped = true;
        try {

            this.serverSocket.close();
        } catch (IOException e) {

            throw new RuntimeException("Error closing server", e);
        }
    }

    /**
     * terminate threads in the map here
     */
    public void terminateThreads(){

    }

    private void openServerSocket() {

        try {
            this.serverSocket = new ServerSocket(this.port);
        } catch (IOException e) {
            throw new RuntimeException("Cannot open port", e);
        }
    }

    public static void main(String[] args) {
        Server test = new Server(4444);
        test.run();
    }
}
