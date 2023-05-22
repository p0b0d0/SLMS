package user;

public class user {
    protected String userId;
    protected String name;
    protected String email;
    protected String position;
    protected String password;
    //constructor
    public user(String userId, String name, String email,String position,String password) {	
	this.userId = userId;
	this.name = name;
	this.email = email;
        this.position = position;
	this.password = password;
    }
    //getters
    public String getUserId() {
	return userId;
    }
    public String getName() {
	return name;
    }
    public String getEmail() {
	return email;
    }
    
    public String getPassword() {
	return password;
    }
    public String getPosition() {
	return position;
    }
}
