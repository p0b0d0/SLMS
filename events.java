package Events;

public class events {
    private String date;
    private String name;
    private String participation;
    //constructor
    public events(String date, String name, String participation){
	this.date = date;
	this.name = name;
	this.participation = participation;
    }
    //getters
    public String getDate() {
	return date;
    }
		
    public String getName() {
	return name;
    }
		
    public String getParticipation() {
	return participation;
    }
		
}
