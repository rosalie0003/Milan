package Server.communications;

public abstract class AppMemo extends Memo {

	// Time stamp?
	// User ID?
	
	private int[] userIDs; // Users who care about this message
	
	private int chatID;
	private int appTarget;
					// App Target:
					//		0 reserved for chat app
					//		Say, -1, reserved for program communications?
					//			Or even all negative numbers?
	
	public AppMemo(int[] userIDs, int chatID, int appTarget) {
		super();
		
		this.userIDs = userIDs;
		this.chatID = chatID;
		this.appTarget = appTarget;
		
		// Time stamp?
		// USer ID?
	}
	
	public int[] getUserIDs() {
		return userIDs;
	}
	
	public int getChatID() {
		return chatID;
	}

	public int getAppTarget() {
		return appTarget;
	}

}
