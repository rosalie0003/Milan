package Server.communications;

public class HistoryRequest extends ServerMemo {
	
	private int chatID;
	private int appTarget;
	
	public HistoryRequest(int userID, int chatID, int appTarget) {
		super(userID);
		
		this.chatID = chatID;
		this.appTarget = appTarget;
	}

	public int getChatID() {
		return chatID;
	}

	public int getAppTarget() {
		return appTarget;
	}
	
}
