package Server;

import java.sql.ResultSet;

/**
 * Created by mnt_x on 22/02/2017.
 */
public class MessengerDatabase implements Database {
    @Override
    public void addUser(String username, String password) {

    }

    @Override
    public boolean checkCredientials(String username, String password) {
        return false;
    }

    @Override
    public String getChats(String username) {
        return null;
    }

    @Override
    public String getActiveChat(String username) {
        return null;
    }

    @Override
    public ResultSet getHistory(int chatID, String appTarget) {
        return null;
    }

    @Override
    public void setMessage(int chatID, String appTarget, String message, String meta) {

    }

    @Override
    public String getMessage(int chatID, String appTarget, String message, String meta) {
        return null;
    }
}
