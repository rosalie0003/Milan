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

        long start = System.currentTimeMillis();

        Socket s = new Socket("localhost", 4444);
        Login l = new Login("username", "pass");
        ObjectOutputStream toServer = new ObjectOutputStream(s.getOutputStream());
        toServer.writeObject(new Packet(l));

        long end;
        try (ObjectInputStream fromServer = new ObjectInputStream(s.getInputStream())) {

            Setup setup = (Setup)fromServer.readObject();


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }
}
