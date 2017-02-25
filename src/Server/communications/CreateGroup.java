package Server.communications;

import java.util.List;

public class CreateGroup extends ServerMemo {
	
	private List<String> usernames;

	public CreateGroup(int userID, List<String> usernames) {
		super(userID);
		
		this.usernames = usernames;
	}
	
	public List<String> getUsernames() {
		return usernames;
	}

}
