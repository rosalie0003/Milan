package Server;

import Server.communications.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by mnt_x on 22/02/2017.
 */
public class TestClient {

    public static void main(String[] args) throws IOException {

        Socket s = new Socket("localhost", 4444);

        Login l = new Login("username", "pass");
        HistoryRequest h = new HistoryRequest(22, 1, 2);

        int [] userIDs = {22};
        Message m = new Message(userIDs, 1, 2, "username", "thisismeta", "Its working!!!");

        ObjectOutputStream toServer = new ObjectOutputStream(s.getOutputStream());

        toServer.writeObject(new Packet(l));
        //toServer.writeObject(new Packet(h));
        toServer.writeObject(new Packet(m));


        try (ObjectInputStream fromServer = new ObjectInputStream(s.getInputStream())) {
            while(true) {

                Packet p = (Packet) fromServer.readObject();
                Setup setup = (Setup) p.getMemo();
                System.out.println(setup.getUsername());

                Packet pm = (Packet) fromServer.readObject();
                Message message = (Message) pm.getMemo();
                System.out.println(message.getMessage());
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
