package Server;

import Server.communications.Message;

import java.io.IOException;

/**
 * Created by laurie on 2/25/17.
 */
public class MessageThread implements Runnable {

    private Server server;

    public MessageThread(Server server){


        this.server = server;

    }


    @Override
    public void run() {

        public void sendMessages(){
            try {

                if(!server.getMessageQueue().isEmpty()) {
                    Message m = getMessageQueue().remove();

                    for(int id : m.getUserIDs()){

                        if(db.userActive(id)) {

                            threads.get(id).messageFromClient(m);
                        }

                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
