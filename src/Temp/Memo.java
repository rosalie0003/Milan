package Server.Communications;

public abstract class Memo {

    // Time stamp?
    // User ID?

    private int chatID;
    private int appTarget;
    // App Target:
    //		0 reserved for chat app
    //		Say, -1, reserved for program communications?
    //			Or even all negative numbers?

    public Memo(int chatID, int appTarget) {
        this.chatID = chatID;
        this.appTarget = appTarget;

        // Time stamp?
        // USer ID?
    }

    public int getChatID() {
        return chatID;
    }

    public int getAppTarget() {
        return appTarget;
    }
}