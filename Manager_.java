package user;

public class Manager_ extends user{
    
    private String department;
    
    //constructor
    public Manager_(String userId, String name, String email, String department, String position, String password) {
        super(userId, name, email,position, password);
        this.department = department;

    }
    //getters
    public String getDepartment() {
	return department;
    }

}
