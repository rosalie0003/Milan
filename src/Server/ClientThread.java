package Server;

import java.io.*;
import java.net.Socket;

/**
 * Created by mnt_x on 22/02/2017.
 */
public class ClientThread implements Runnable {

    private Socket clientSocket = null;
    private ObjectInputStream input;
    private ObjectOutputStream output;

    /**
     *
     * @param clientSocket
     */
    public ClientThread(Socket clientSocket) {
        System.out.println("hello");
        try {

            this.clientSocket = clientSocket;
            input =  new ObjectInputStream(clientSocket.getInputStream());
            output = new ObjectOutputStream(clientSocket.getOutputStream());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     */
    public void run() {

        try {

            int count=0;
            System.out.println("here");
            for(int i = 0; i < HowMany.times; i++) {
                Person p = (Person) input.readObject();

                if (count++ % 1000 == 0) {
                    System.out.println(p);
                }
            }

            output.close();
            input.close();
        } catch (IOException e) {

            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
