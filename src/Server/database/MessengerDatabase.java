package Server.database;

import Server.database.Database;
import Server.Server;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mnt_x on 22/02/2017.
 */
public class MessengerDatabase implements Database {

    @Override
    public void addUser(String username, String password) {

    }

    @Override
    public boolean checkCredentials(String username, String password) {
        return false;
    }

    @Override
    public int getUserID(String username) {
        return 0;
    }

    @Override
    public List<Integer> getChats(String username) {

        return null;
    }

    @Override
    public List<String> getActiveChat(String username) {

//        Leaving this code here to convert resultset to list as it makes more sense to convert in the getter
//        ResultSet rs = new ResultSet();
//
//        List<String> result = new ArrayList<>();
//        while (rs.next()) {
//            fullnames.add());
//        }
//        return result;
        return null;
    }

    @Override
    public void addUserToChat(int userID) {

    }

    @Override
    public void removeUserFromChat(int userID) {

    }

    @Override
    public int getActiveChatID(String username) {
        return 0;
    }

    @Override
    public List<String> getHistory(int chatID, int appTarget) {

//        Leaving this code here to convert resultset to list as it makes more sense to convert in the getter
//        ResultSet rs = new ResultSet();
//
//        List<String> result = new ArrayList<>();
//        while (rs.next()) {
//            fullnames.add());
//        }
//        return result;
        return null;
    }

    @Override
    public List<String> getChatUsernames(int chatID) {

//        Leaving this code here to convert resultset to list as it makes more sense to convert in the getter
//        ResultSet rs = new ResultSet();
//
//        List<String> result = new ArrayList<>();
//        while (rs.next()) {
//            fullnames.add());
//        }
//        return result;
        return null;
    }

    @Override
    public int [] getChatUserIDs(int chatID) {

//        Leaving this code here to convert resultset to list as it makes more sense to convert in the getter
//        ResultSet rs = new ResultSet();
//
//        List<String> result = new ArrayList<>();
//        while (rs.next()) {
//            fullnames.add());
//        }
//        return result;
        return null;
    }


    @Override
    public void setMessage(int chatID, int appTarget, String message, String meta) {

    }

    @Override
    public String getMessage(int chatID, String appTarget, String message, String meta) {
        return null;
    }

    @Override
    public void close() {

    }

    @Override
    public Connection getConnection() {
        return null;
    }

    @Override
    public boolean userActive(int ID) {
        return false;
    }

    @Override
    public void setUserActive(String username) {

    }
}
