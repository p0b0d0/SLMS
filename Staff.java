package user;

public class Staff extends user{
    
    private String department;
    private String position;
    
    //constructor
    public Staff(String userId, String name, String email, String department, String position, String password) {
        super(userId, name, email, position, password);
        this.department = department;
    }
    //geters
    public String getDepartment() {
	return department;
    }

}
