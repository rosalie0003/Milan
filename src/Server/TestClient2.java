package Server;

import Server.communications.HistoryRequest;
import Server.communications.Login;
import Server.communications.Packet;
import Server.communications.Setup;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by laurie on 2/25/17.
 */
public class TestClient2 {

    public static void main(String[] args) throws IOException {

        long start = System.currentTimeMillis();

        Socket s = new Socket("localhost", 4444);

        Login l = new Login("Bob", "pass");
        HistoryRequest h = new HistoryRequest(23, 1, 2);

        ObjectOutputStream toServer = new ObjectOutputStream(s.getOutputStream());
        toServer.writeObject(new Packet(l));
        toServer.writeObject((new Packet(h)));


        try (ObjectInputStream fromServer = new ObjectInputStream(s.getInputStream())) {

            Packet p = (Packet)fromServer.readObject();
            Setup setup = (Setup)p.getMemo();
            System.out.println(setup.getUsername());

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }
}
