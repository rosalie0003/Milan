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
    public String username;

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

            // login here
            Packet packet = (Packet)input.readObject();
            handleLogin((Login)packet.getMemo());


        } catch (IOException e) {

            e.printStackTrace();
        } catch (ClassNotFoundException e) {

            e.printStackTrace();
        }
    }

    public String getUsername() {

        return this.username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public void setUserID(int userID){
        this.userID = userID;
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

        while(true) {

            try {
                addPacketToProcess((Packet) input.readObject());


            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            try {

                Packet item;
                while (true) {

                    if ((item = dequeuePacketToProcess()) != null) {

                        PacketReader.readPacket(item, this);
                    }
                }
            } catch (Exception e) {

                System.out.println("ClientController.run() interrupted.");
            }
        }
    }

    public void messageToClient(Message message) throws IOException {

        output.writeObject(new Packet(message));
    }



    /*
     * Handlers
     */

    @Override
    public boolean handleMessage(Message message) {
        System.out.println("handleMessage");
        server.receivedMessage(message);

        return true;
    }

    @Override
    public boolean handleHistory(History history) {

        // should not be received by server
        return false;
    }

    @Override
    public boolean handleHistoryRequest(HistoryRequest historyRequest) {

        System.out.println("handleHistoryRequest");
        server.history(historyRequest.getChatID(), historyRequest.getAppTarget());
        return true;
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

        // should not be received by server
        return false;
    }

    @Override
    public boolean handleLogin(Login login) {

        String username = login.getUsername();
        String password = login.getPassword();

        try {
            //setUserID(server.getUserID(username));
            setUserID(22);
            setUsername(username);

            output.writeObject(new Packet(server.setup(username)));
        } catch (IOException e) {
            e.printStackTrace();
        }
//        if(db.checkCredentials(username, password)){
//
//            output.writeObject(new Setup(db.getChats(username), db.getActiveChat(username)));
//            return true;
//        } else {
//            return false;
//            }
        return false;
    }
}
