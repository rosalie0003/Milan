package Server.Communications;


import java.util.List;

public class Setup implements java.io.Serializable {

    private boolean valid;
    private List<Integer> chats; // chat IDs to which the user belongs
    private List<String> activeChat; // chat with most recent message

    public Setup() {
        valid = false;
    }

    public Setup(List<Integer> chats, List<String> activeChat) {

        valid = true;
        this.chats = chats;
        this.activeChat = activeChat;
    }

    public List<Integer> getChats() {
        return chats;
    }

    public List<String> getActiveChat() {
        return this.activeChat;
    }

    public boolean isValidSetup() {
        return valid;
    }

}