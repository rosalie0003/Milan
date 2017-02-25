package Server.communications;

public class FriendRequest extends ServerMemo {
	
	private String username;

	public FriendRequest(int userID, String username) {
		super(userID);
		
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

}
