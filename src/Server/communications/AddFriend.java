package Server.communications;

import Server.communications.*;

public class AddFriend extends ServerMemo {
	
	private String username;

	public AddFriend(int userID, String username) {
		super(userID);
		
		this.username = username;
	}
	
	public String getUsername() {
		return username;
	}

}
