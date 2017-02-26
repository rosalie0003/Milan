package Server;

import Server.communications.Message;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by laurie on 2/25/17.
 */
public class MessageThread extends Thread {

    private Server server;
    private Queue<Message> messageQueue;

    public MessageThread(Server server){

        this.server = server;
        messageQueue = new LinkedList<>();
    }

    public Queue<Message> getMessageQueue() {

        return messageQueue;
    }

    public Message dequeueMessageQueue(){

        return this.messageQueue.remove();
    }

    public void enqueueMessageQueue(Message m){
        System.out.println("enqueueMessageQueue");

        messageQueue.add(m);
    }

    @Override
    public void run() {
        while (true) {

            System.out.println(messageQueue.toString());
            try {

                // if message queue is not empty
                if (!getMessageQueue().isEmpty()) {

                    // dequeue message and assign
                    Message m = dequeueMessageQueue();

                    // for each user id in message
                    for (int id : m.getUserIDs()) {

                        // Check user is on server before sending to client
//                        if (server.isUserConnected(id)) {

                            // get the thread the message is intended for and send it to the client
                            server.getThread(id).messageToClient(m);
//                        }
                    }
                }
            } catch (IOException e) {

                e.printStackTrace();
            }
        }
    }
}
