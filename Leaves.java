package Leaves;

public class Leaves {
    private String leaveID;
    private String userID;
    private String username;
    private String date;
    private String forhday;
    private String Request;
    private String approved;
    private String reason;
    //constructor
    public Leaves(String userID, String username, String date, String forhday, String request,String approved, String reason){
		
	
	this.userID = userID;
	this.username = username;
	this.date = date;
	this.forhday = forhday;
	Request = request;
	this.approved = approved;
	this.reason = reason;
    }
    //getters
    public String getLeaveID() {
        return leaveID;
    }
	
    public String getUserID() {
	return userID;
    }
	
    public String getUsername() {
	return username;
    }
	
    public String getDate() {
	return date;
    }
	
    public String getForhday() {
        return forhday;
    }
	
    public String getRequest() {
        return Request;
    }
	
    public String getApproved() {
        return approved;
    }
	
    public String getReason() {
	return reason;
    }
}   
