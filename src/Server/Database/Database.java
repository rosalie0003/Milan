package Server.Database;

import Server.Server;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;

/**
 *
 */
public interface Database {

    void addUser(String username, String password);

    boolean checkCredentials(String username, String password);

    List<Integer> getChats(String username);

    List<String> getActiveChat(String username);

    List<String> getHistory(int chatID, String appTarget);

    void setMessage(int chatID, String appTarget, String message, String meta);

    String getMessage(int chatID, String appTarget, String message, String meta);

    // Close connection
    void close();

    // Getter for the database connection
    Connection getConnection();

    // potential method could be used for checking if a user is online.
    boolean userActive(String username);
}
