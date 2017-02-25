package Server.communications;

public abstract class ServerMemo extends Memo {
	
	private int userID;

	public ServerMemo(int userID) {
		super();
		
		this.userID = userID;
	}

	public int getUserID() {
		return userID;
	}

}
