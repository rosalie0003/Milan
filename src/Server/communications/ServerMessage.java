package Server.communications;

public abstract class ServerMessage extends ServerMemo {

	private String message;

	public ServerMessage(int userID, String message) {
		super(userID);
		
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	
}
