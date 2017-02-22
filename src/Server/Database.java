package Server;

import java.sql.ResultSet;

/**
 *
 */
public interface Database {

    void addUser(String username, String password);

    boolean checkCredientials(String username, String password);

    String getChats(String username);

    String getActiveChat(String username);

    ResultSet getHistory(int chatID, String appTarget);

    void setMessage(int chatID, String appTarget, String message, String meta);

    String getMessage(int chatID, String appTarget, String message, String meta);

    // potential method could be used for checking if a user is online.
    //boolean userActive(String username);
}
