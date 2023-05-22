package user;

import javax.swing.JOptionPane;
import javax.swing.JTable;

public class usercontroller {
    //view all data using a table
    public static void settabledata(JTable table){
        userDBmodel.getemployeedata(table);
    }
    //add new data
    public static void adddata(String uname,String name,String email,String dept, String pos, String tpass){
        String[] data = {uname,name,email,dept,pos,tpass};
        if(userDBmodel.setdata(data) == true){
            JOptionPane.showMessageDialog(null,"Successfully Added");
        }
    }
    //update existing data
    public static void updatedata(String uname,String name,String email,String dept, String pos, String tpass){
        String[] data = {uname,name,email,dept,pos,tpass};
        if(userDBmodel.updateuser(data) == true){
            JOptionPane.showMessageDialog(null,"Update successfully");
        }
    }
    //delete data
    public static void deletedata(String id){
        if(userDBmodel.deleteuser(id) == true){
            JOptionPane.showMessageDialog(null,"Successfully Deleted");
        }
    }
    //change the password
    public static void changepassword(String id, String newpassword, String oldpassword){
        if(userDBmodel.getpassword(id).equals(oldpassword) == true){
            userDBmodel.setpassword(id, newpassword);
        }else{
            
        }
    }
}
