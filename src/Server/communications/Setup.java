package Server.communications;

import java.util.List;

public class Setup extends ServerMemo {
	
	private boolean valid;
	
	private int userID;
	private String username;
	
	private List<Integer> chats; // chat IDs to which the user belongs
	private History activeChatHistory; // chat with most recent message
	
	public Setup() {
		super(-1);
		
		valid = false;
	}
	
	public Setup(int userID, String username, List<Integer> chats, History activeChatHistory) {
		super(userID);
		
		valid = true;
		
		this.userID = userID;
		this.username = username;
		this.chats = chats;
		this.activeChatHistory = activeChatHistory;
	}
	
	public int getUserID() {
		return userID;
	}
	
	public String getUsername() {
		return username;
	}

	public List<Integer> getChats() {
		return chats;
	}

	public History getActiveChatHistory() {
		return activeChatHistory;
	}
	
	public boolean isValidSetup() {
		return valid;
	}
	
}
