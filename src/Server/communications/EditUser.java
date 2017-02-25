package Server.communications;

public class EditUser extends ServerMemo {

	private String password;
	
	private boolean[] edited;
	private String[] fields;
	
	public EditUser(int userID, String password, boolean[] edited, String[] fields) {
		super(userID);
		
		this.password = password;
		
		this.edited = edited;
		this.fields = fields;
	}

	public String getPassword() {
		return password;
	}

	public boolean[] getEdited() {
		return edited;
	}

	public String[] getFields() {
		return fields;
	}

}
