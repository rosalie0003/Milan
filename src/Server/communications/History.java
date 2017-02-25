package Server.communications;

import java.util.List;

public class History extends AppMemo {
	
	private List<String> usernames;
	
	private String history;
	
	public History(int userIDs[], int chatID, int appTarget, List<String> usernames, String history) {
		super(userIDs, chatID, appTarget);
		
		this.usernames = usernames;
		this.history = history;
	}

	public String getHistory() {
		return history;
	}

	public List<String> getUsernames() {
		return usernames;
	}
	
}
