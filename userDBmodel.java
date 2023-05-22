package user;

import Leaves.LeavesController;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class userDBmodel {
    //validate the user login details
    public static ArrayList<user>  validateUser(String userName){
        ArrayList<user> mainuser= new ArrayList<>();
        try {
            //get database connection
            Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/main","root","1234");
            Statement st = con.createStatement();
            //sql query to get the user details 
            String sql = "Select * from main.employees where username = '"+userName+"'";
            ResultSet rs = st.executeQuery(sql);
            if(rs.next()){
                String id = rs.getString(1);
                String name = rs.getString(2);
                String email = rs.getString(3);
                String department = rs.getString(4);
                String position = rs.getString(5);
                String userpassword = rs.getString(6);
                //check the user and create the new object and assighn values
                if(position.equals("Manager" )){
                    user m = new Manager_(id,name,email,department,position,userpassword);
                    mainuser.add(m);
                    con.close();
                }else{
                    user m = new Staff(id,name,email,department,position,userpassword);
                    mainuser.add(m);
                    LeavesController.getmainuser(m);
                    con.close();
                }
            }else{
            }
            //close the connection
            st.close();
            con.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex.getMessage());
            
        }    
        return mainuser;
    }
    //get the values from database and set it to the table
    public static void getemployeedata(JTable tblData){
        try {
            //get database connection
            Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/main","root","1234");
            Statement st = con.createStatement();
            //get all data
            String sql = "SELECT * FROM main.employees";
            ResultSet rs = st.executeQuery(sql);
            ResultSetMetaData rsmd = rs.getMetaData();
            DefaultTableModel model = (DefaultTableModel) tblData.getModel();
            //get the number of column in the database
            int cols=rsmd.getColumnCount();
            //get the column names frome the database
            String[] colName = new String[cols];
            
            for(int i=0;i<cols;i++){
                colName[i]=rsmd.getColumnName(i+1);
                model.setColumnIdentifiers(colName);
            }
            //get the data from database
            String userid,name,email,department,position,password;
            while(rs.next()){
                userid = rs.getString(1);
                name = rs.getString(2);
                email = rs.getString(3);
                department = rs.getString(4);
                position = rs.getString(5);
                password = rs.getString(6);
                String[] row = {userid,name,email,department,position};
                model.addRow(row);    
            }
            //close the database connection
            st.close();
            con.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex.getMessage());
        }
        
    }
    //insert data to the database
    public static boolean setdata(String[] data){
        
        boolean successfull = false;
        try{
            //create a connection
            Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/main","root","1234");
            Statement st = con.createStatement();
            //Quary to update data
            String sql = "INSERT INTO main.employees VALUES ('"+data[0]+"','"+data[1]+"','"+data[2]+"','"+data[3]+"','"+data[4]+"','"+data[5]+"')";
            int rs = st.executeUpdate(sql);
            //check that the update was done correctly
            if(rs>0){
                successfull = true;
            }else{
                successfull = false;
            }
            //close the connection
            st.close();
            con.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        
        }
        //return the boolean expression
        return successfull;
    }
    //static methord to update database
    public static boolean updateuser(String[] data){
        boolean successfull = false;
        try{
            //create a connection
            Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/main","root","1234");
            Statement st = con.createStatement();
            String sql = "UPDATE main.employees SET name = '"+data[1]+"',email = '"+data[2]+"',department = '"+data[3]+"',position = '"+data[4]+"',password = '"+data[5]+"' WHERE username = '"+data[0]+"'  ";
        int rs = st.executeUpdate(sql);
        //check if the data added correctly or not
        if(rs>0){
            successfull = true;
        }else{
            successfull = false;
        }
        //stop the connection
        st.close();
        con.close();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,ex.getMessage());
        }
        return successfull;
    }
    //set the main user
    public static boolean deleteuser(String id){
        boolean successfull = false;
        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/main","root","1234");
            Statement st = con.createStatement();
            String sql = "DELETE FROM main.employees WHERE username='"+id+"'";
            int rs = st.executeUpdate(sql);
            if(rs>0){
                successfull = true;
            }
            //close connection
            st.close();
            con.close();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,ex.getMessage());
        }
        return successfull;
    }
    public static String getpassword(String id){
        String password=null;
        try {
            //get database connection
            Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/main","root","1234");
            Statement st = con.createStatement();
            //quary 
            String sql = "SELECT password FROM main.employees WHERE username = '"+id+"'";
            ResultSet rs = st.executeQuery(sql);
            ResultSetMetaData rsmd = rs.getMetaData();
            //get the password
            while(rs.next()){
                password = rs.getString(1);
            }
            //close connection
            st.close();
            con.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex.getMessage());
            
        }
        //return password
        return password;
    }
    //static methord for update the password
    public static void setpassword(String id, String  pas){
        try{
            //create the connection
            Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/main","root","1234");
            Statement st = con.createStatement();
            // sql quary
            String sql = "UPDATE main.employees SET password = '"+pas+"' WHERE username = '"+id+"'";
            int rs = st.executeUpdate(sql);
        
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex.getMessage());
        }
    }
}
