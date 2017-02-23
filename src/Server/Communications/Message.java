package Server.Communications;


public class Message extends Memo {

    private int userID;
    private String meta;
    private String message;

    public Message(int chatID, int appTarget, int userID, String meta, String message) {

        super(chatID, appTarget);
        this.userID = userID;
        this.meta = meta;
        this.message = message;
    }

    public int getUserID() {
        return userID;
    }

    public String getMeta() {
        return meta;
    }

    public String getMessage() {
        return message;
    }

}
