package Server;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by mnt_x on 22/02/2017.
 */
public class TestClient {

    public static void main(String[] args) throws IOException {

        long start = System.currentTimeMillis();

        Socket s = new Socket("localhost", 4444);
        ObjectOutputStream toServer = new ObjectOutputStream(s.getOutputStream());

        for (int id=0; id < HowMany.times; id++) {
            toServer.writeObject(new Person("John", id));
        }

        long end = System.currentTimeMillis();
        System.out.println("Total time " + (end-start)/1000 + "s");

    }
}
