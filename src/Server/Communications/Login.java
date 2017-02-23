package Server.Communications;

/**
 * Created by mnt_x on 23/02/2017.
 */
public class Login implements java.io.Serializable{

    private String username;
    private String password;

    public Login(String username, String password) {
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
