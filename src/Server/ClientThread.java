package Server;

import java.io.*;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Queue;

import Server.communications.*;
import communications.*;

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


    public synchronized void addPacketToProcess(Packet packet) {

        tempPacketsToProcess.add(packet);
    }

    public synchronized Packet dequeuePacketToProcess() {

        return tempPacketsToProcess.poll();
    }


    /**
     *
     */
    public synchronized void run() {

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
        return false;
    }

    @Override
    public boolean handleLogin(Login login) {
        return false;
    }
}
