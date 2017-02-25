package Server.communications;

public class Message extends AppMemo {
	
	private String senderUsername; // The user who sent this message
	private String meta;
	private String message;
	
	public Message(int[] userIDs, int chatID, int appTarget, String senderUsername, String meta, String message) {
		super(userIDs, chatID, appTarget);
		
		this.senderUsername = senderUsername;
		this.meta = meta;
		this.message = message;
	}

	public String getSenderID() {
		return senderUsername;
	}
	
	public String getMeta() {
		return meta;
	}

	public String getMessage() {
		return message;
	}
	
}
