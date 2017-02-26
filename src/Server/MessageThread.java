package Server;

import Server.communications.Message;

import java.io.IOException;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author Laurie Dugdale
 */
public class MessageThread extends Thread {

    private Server server;
    private Queue<Message> messageQueue;

    /**
     * Constructor
     *
     * @param server
     */
    public MessageThread(Server server){

        this.server = server;
        messageQueue = new LinkedBlockingQueue<>();
    }

    /*
     * Getters and setters
     */
    public Queue<Message> getMessageQueue() {

        return messageQueue;
    }

    public synchronized Message dequeueMessageQueue(){

        return this.messageQueue.remove();
    }

    public synchronized void enqueueMessageQueue(Message m){

        messageQueue.offer(m);
    }



    @Override
    /**
     *
     */
    public void run() {

            try {

                while (true) {

                    // if message queue is not empty
                    if (!getMessageQueue().isEmpty()) {

                        // dequeue message and assign
                        Message m = dequeueMessageQueue();
                        // for each user id in message
                        for (int id : m.getUserIDs()) {

                            // Check user is on server before sending to client
                            if (server.inThread(id)) {

                                // get the thread the message is intended for and send it to the client
                                server.getThread(id).messageToClient(m);
                            }
                        }
                    }
                }
            } catch (IOException e) {

                e.printStackTrace();
            }
    }
}
