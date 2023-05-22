package Leaves;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import user.user;

public class LeavesController {
    //main users details
    static user main;
    //static methord to get the mainuser
    public static void getmainuser(user mainuser){
        main = mainuser;
    }
    //static methord to set the main user
    public static user setmainuser(){
        return main;
    }
    //get the data to a table from database
    public static void getdataformaintable(JTable table){
        String userid = main.getUserId();   
    }
    //insert data to the database
    public static void insertdata(Leaves main){
        String[] values = {main.getUserID(),main.getUsername(),main.getDate(),main.getForhday(),main.getRequest(),"Pending","Processing"};
        if(LeavesDB.setdata(values) == true){
            JOptionPane.showMessageDialog(null,"Leave aded successfully");
        }else{
            JOptionPane.showMessageDialog(null,"Error");
        }
    }
    //view data
    public static void getleavesmain(JTable table){
        
        LeavesDB.getmaindata(main.getUserId(), table);
    }
    //getters
    public static String getreason(String id){
        String reason = LeavesDB.getreason(id);
        return reason;
    }
    public static void getleavesdataformanager(JTable table){
        LeavesDB.getleavesformanager(table);
    
    }
    //ser approval
    public static void aprovalpass(JTable table, String id, String approval, String reason){
        String[] data = {id,approval,reason};
        if(LeavesDB.updateapproval(table, data) == true){
            JOptionPane.showMessageDialog(null,"Successfully updated");
        }else{
            JOptionPane.showMessageDialog(null,"Error");
        }
    
    }
    //add data to a table
    public static void getall(JTable table){
        LeavesDB.getleavesdata(table);
    }
    //delete data
    public static void deleteleaves(String id){
        if(LeavesDB.deleteleaves(id) == true){
            JOptionPane.showMessageDialog(null,"Successfully Deleted");
        }else{
            JOptionPane.showMessageDialog(null,"Error");
        }
    }
    //update data
    public static void updateleaves(String[] data){
        if(LeavesDB.upadteLeaves(data) == true){
            JOptionPane.showMessageDialog(null,"Successfully Updated");
        }else{
            JOptionPane.showMessageDialog(null,"Error");
        }
    }
}
