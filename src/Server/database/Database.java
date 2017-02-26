package Server.database;

import Server.Server;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;

/**
 *
 */
public interface Database {

    // add user to the database
    void addUser(String username, String password);

    // check user credentials
    boolean checkCredentials(String username, String password);

    // get the user ID
    int getUserID(String username);

    // get a list of chats the user belongs too
    List<Integer> getChats(String username);

    // get the users last active chat
    List<String> getActiveChat(String username);

    void addUserToChat(int userID);

    void removeUserFromChat(int userID);


    // get the ID of the users last active chat
    int getActiveChatID(String username);

    // get chat history
    List<String> getHistory(int chatID, int appTarget);

    // get usernames belonging to a specific chat
    List<String> getChatUsernames(int chatID);

    // get user IDs belonging to a specific chat
    int []  getChatUserIDs(int chatID);

    // add a message to the database
    void setMessage(int chatID, int appTarget, String message, String meta);

    // get a single message from the database
    // TODO parameters are wrong discuss the needed parameters to get a specific message
    String getMessage(int chatID, String appTarget, String message, String meta);

    // Close connection
    void close();

    // Getter for the database connection
    Connection getConnection();

    // potential method could be used for checking if a user is online.
    boolean userActive(int ID);

    // set a marker that the user is active
    void setUserActive(String username);
}
