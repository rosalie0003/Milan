package Server.Communications;

import Server.Database.Database;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.Connection;

/**
 * Created by mnt_x on 23/02/2017.
 */
public class CommunicationHandler {

    Object communication;
    ObjectOutputStream output;
    Database db;

    public CommunicationHandler(Object communication, ObjectOutputStream output, Database db){

        this.communication = communication;
        this.output = output;
        this.db = db;
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

    public void send() {

    }
}
