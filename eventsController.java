package Events;

import javax.swing.JOptionPane;
import javax.swing.JTable;

public class eventsController {
    //set all data to a table
    public static void setdatatoTable(JTable tblData){
        eventsDB.geteventsdata(tblData);
    }
    //add data
    public static void settable(String date, String name,String par){
        String[] data = {date,name,par};
  
        if(eventsDB.setdata(data) == true){
            JOptionPane.showMessageDialog(null,"Succesfully aded");
        }else{
            JOptionPane.showMessageDialog(null,"Error");
        }
    }
    //update existing data
    public static void updateevents(String no,String date,String name,String par){
        String[] data = {no,date,name,par};
        
        if(eventsDB.updateevent(data) == true){
            JOptionPane.showMessageDialog(null,"Updated Successfully");
        }else{
            JOptionPane.showMessageDialog(null,"Error");
        }
    }
    //delete data
    public static void deleteevent(String id){

        if(eventsDB.deleteevent(id) == true){
            JOptionPane.showMessageDialog(null,"Successfully deleted");
        }else{
            JOptionPane.showMessageDialog(null,"Error");
        }
    }
    //search data
    public static void searchevents(JTable tblData, String datec){
        eventsDB.getevents(tblData, datec);
    }
}
