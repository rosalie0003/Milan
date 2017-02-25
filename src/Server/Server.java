package Server;

import Server.communications.*;
import Server.database.*;

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
    private Queue<Message> messageQueue;
    private Database db;

    public Server(int port) {

        this.port = port;

        db = new MessengerDatabase();

        threads = new HashMap<>();

        Queue<Message> messageQueue = new LinkedList<>();
    }

    public Server(int port, ServerGUI sg) {

        this.port = port;

        this.sg = sg;

        db = new MessengerDatabase();

        threads = new HashMap<>();

        Queue<Message> messageQueue = new LinkedList<>();
    }


    /**
     * Server pool creating new Client thread
     */
    public void run(){

        synchronized(this){
            this.runningThread = Thread.currentThread();
        }
        openServerSocket();

        // Create Thread to handle all messages.
        Thread MessagesThread = new Thread( new MessageThread(this));

        while(! isStopped()){

            try {

                // Accept socket connection
                Socket clientSocket = this.serverSocket.accept();

                // Create the client thread
                ClientThread temp = new ClientThread(this, clientSocket);

                // Add the client thread to the map
                threads.put(temp.getUsername(),temp);
                threads.get(temp.getUsername()).start();

                System.out.println(threads.toString());
            } catch (IOException e) {

                if(isStopped()) {

                    System.out.println("Server Stopped.") ;
                    break;
                }

                throw new RuntimeException( "Error accepting client connection", e);
            }
        }
        System.out.println("Server Stopped.") ;
    }


    private synchronized boolean isStopped() {

        return this.isStopped;
    }

    /**
     * Stop server - used by GUI
     */
    public synchronized void stop(){

        this.isStopped = true;
        try {

            terminateThreads();
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

    /*
     * Client server operations
     */
    public int getUserID(String username){
        return db.getUserID(username);
    }

    public Setup setup(String username){

        return new Setup(db.getUserID(username), username, db.getChats(username), history(db.getActiveChatID(username), 1));
    }

    public History history(int chatID, int appTarget){


        return new History(db.getChatUserIDs(chatID), chatID, appTarget, db.getChatUsernames(chatID), db.getHistory(chatID, appTarget));
    }

    public void recievedMessage(Message m){

        db.setMessage(m.getChatID(), m.getAppTarget(), m.getMessage(), m.getMeta());
        messageQueue.add(m);
    }














}

