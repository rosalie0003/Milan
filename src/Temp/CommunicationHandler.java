package Server.Communications;

import Server.Database.Database;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.Connection;

/**
 * Class handles incoming communication from client
 * @author Laurie Dugdale
 */
public class CommunicationHandler {

    private Object communication;
    private ObjectOutputStream output;
    private Database db;
    private boolean messageReceived;

    public CommunicationHandler(Object communication, ObjectOutputStream output){

        this.communication = communication;
        this.output = output;
        this.db = db;
    }

    public boolean isMessageReceived(){

        return messageReceived;
    }

    public void setMessageReceived( boolean messageReceived ){

        this.messageReceived = messageReceived;
    }

    public void parseCommunication(){
        try {

            if (communication.getClass() == Login.class){

                loginHandler((Login)communication);
            } else if (communication.getClass() == HistoryRequest.class){


            } else if (communication.getClass() == Packet.class) {

                PacketReceiver pr = new PacketReceiver((Packet)communication);
            } else {

                throw new IllegalStateException("Could not read packet");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void loginHandler(Login login) throws IOException {

        String username = login.getUsername();
        String password = login.getPassword();
        output.writeObject(new Setup(db.getChats(username), db.getActiveChat(username)));

//        if(db.checkCredentials(username, password)){
//
//            output.writeObject(new Setup(db.getChats(username), db.getActiveChat(username)));
//        }
    }

    // Send a message to a client
    public void sendMessage() {

    }
}
