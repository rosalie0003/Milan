package Server.communications;

public class Login extends ServerMemo {
	
	private String username;
	private String password;
	
	public Login(String username, String password) {
		super(-1); // Client doesn't know its own userID at login
		
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}
	
}
