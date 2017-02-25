package Server;

import java.io.*;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Queue;

import Server.communications.*;

/**
 * Created by mnt_x on 22/02/2017.
 */
public class ClientThread extends Thread implements PacketHandler {

    private Server server;

    private Socket clientSocket = null;
    private ObjectInputStream input;
    private ObjectOutputStream output;
    Queue<Packet> tempPacketsToProcess = new LinkedList<Packet>();
    private int userID;
    private String username;

    /**
     * Constructor setup input output stream and assign socket to field variable.
     *
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

    public String getUsername() {

        return this.username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public int getUserID() {

        return this.userID;
    }


    /**
     *
     * @param packet
     */
    public synchronized void addPacketToProcess(Packet packet) {

        tempPacketsToProcess.add(packet);
    }

    /**
     *
     * @return
     */
    public synchronized Packet dequeuePacketToProcess() {

        return tempPacketsToProcess.poll();
    }


    /**
     *
     */
    public synchronized void run() {

        // Connection to server is initialised in run()
        // Then we enter continuous loop here and wait for i/o from server or program
        //
        // Do we also have Queues of incoming and outgoing messages to pack/unpack?
        try {
            addPacketToProcess((Packet)input.readObject());


        }catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        /////////////////
        //////////
        ////////// This is the important bit.
        ////////// The PacketReader.readPacket(item, this); call, where 'this' is
        ////////// a controller which implements PacketHandler is the thing which
        ////////// allows the current class to process any incoming packet.
        ////////// Note that a packet can be passed to PacketReader either as a
        ////////// Packet (throge = new Message(new int[] {ugh readPacket) or as an Object (through ReadRawPacket).
        //////////
        /////////////////

        try {

            Packet item;
            while(true) {

                if((item = dequeuePacketToProcess()) != null) {

                    PacketReader.readPacket(item, this);
                }
            }
        }
        catch (Exception e) {

            System.out.println("ClientController.run() interrupted.");
        }
    }



    /*
     * Handlers
     */

    @Override
    public boolean handleMessage(Message message) {
        return false;
    }

    @Override
    public boolean handleHistory(History history) {
        return false;
    }

    @Override
    public boolean handleHistoryRequest(HistoryRequest historyRequest) {
        return false;
    }

    @Override
    public boolean handleServerMessage(ServerMessage serverMessage) {
        return false;
    }

    @Override
    public boolean handleSignUp(SignUp signUp) {
        return false;
    }

    @Override
    public boolean handleLogout(Logout logout) {
        return false;
    }

    @Override
    public boolean handleCreateGroup(CreateGroup createGroup) {
        return false;
    }

    @Override
    public boolean handleFriendRequest(FriendRequest friendRequest) {
        return false;
    }

    @Override
    public boolean handleAddFriend(AddFriend addFriend) {
        return false;
    }

    @Override
    public boolean handleSetup(Setup setup) {
        System.out.println("setup");

        // should not be received by server
        return false;
    }

    @Override
    public boolean handleLogin(Login login) {
        setUsername(login.getUsername());
        System.out.println(getUsername());
        return false;
    }
}
