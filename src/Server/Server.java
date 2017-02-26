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
    private ServerSocket serverSocket;
    private boolean isStopped;
    private Map<Integer, ClientThread> clientThreads;
    private MessageThread messageThread;
    private Database db;

    /**
     * Constructor for command line
     *
     * @param port
     */
    public Server(int port) {

        this.serverSocket = null;

        this.port = port;

        this.isStopped = false;

        db = new MessengerDatabase();

        clientThreads = new HashMap<>();

        messageThread = new MessageThread(this);
    }

    /**
     * Constructor for GUI
     *
     * @param port
     * @param sg
     */
    public Server(int port, ServerGUI sg) {

        this.serverSocket = null;

        this.port = port;

        this.isStopped = false;

        db = new MessengerDatabase();

        clientThreads = new HashMap<>();

        messageThread = new MessageThread(this);

        this.sg = sg;

    }

    public void addThread(int key, ClientThread thread){

        clientThreads.put(key, thread);
    }

    public ClientThread getThread(int key){

        return clientThreads.get(key);
    }

    private synchronized boolean isStopped() {

        return this.isStopped;
    }

    /**
     * Server pool creating new Client thread
     */
    public void run(){

        synchronized(this){
            Thread runningThread = Thread.currentThread();
        }
        openServerSocket();

        // start messageThread to handle all messages.
        messageThread.start();

        while(! isStopped()){

            try {

                // Accept socket connection
                Socket clientSocket = this.serverSocket.accept();

                // Create the client thread
                ClientThread client = new ClientThread(this, clientSocket);

                // Add the client thread to the map
                addThread(client.getUserID(), client);
                getThread(client.getUserID()).start();
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

    /**
     * Stop server - used by GUI
     */
    public synchronized void stop(){

        this.isStopped = true;
        try {

            terminateThreads();
            this.messageThread.interrupt();
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
     * server to thread operations
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

    public void receivedMessage(Message m){

        db.setMessage(m.getChatID(), m.getAppTarget(), m.getMessage(), m.getMeta());
        messageThread.enqueueMessageQueue(m);
    }

    public boolean inThread(int id){

        return this.clientThreads.containsKey(id);
    }

    public boolean isUserConnected(int id){

        return db.userActive(id);
    }













}

