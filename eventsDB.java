package Events;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import user.userDBmodel;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.JTable;

public class eventsDB {
    //get all the data  set it to a table
    public static void geteventsdata(JTable tblData){
        try {
            //get database connection
            Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/main","root","1234");
            Statement st = con.createStatement();
            //quary
            String sql = "SELECT * FROM main.events;";
            ResultSet rs = st.executeQuery(sql);
            ResultSetMetaData rsmd = rs.getMetaData();
            DefaultTableModel model = (DefaultTableModel) tblData.getModel();
            //set tables columns
            int cols=rsmd.getColumnCount();
            String[] colName = new String[cols];
            //set data
            for(int i=0;i<cols;i++){
                
                colName[i]=rsmd.getColumnName(i+1);
                model.setColumnIdentifiers(colName);
            }
            String eid,date,name,participation;
            while(rs.next()){
                eid = rs.getString(1);
                date = rs.getString(2);
                name = rs.getString(3);
                participation = rs.getString(4);
                
                String[] row = {eid,date,name,participation};
                model.addRow(row);
                
            }
            //close connection
            st.close();
            con.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex.getMessage());
            
        }
        
    }
    //add data
    public static boolean setdata(String[] data){
        
        boolean successfull = false;
        try{
            //create a connection
            Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/main","root","1234");
            Statement st = con.createStatement();
            //quary
            String sql = "INSERT INTO main.events VALUES (0,'"+data[0]+"','"+data[1]+"','"+data[2]+"')";
            int rs = st.executeUpdate(sql);
            //check the process
            if(rs>0){
                successfull = true;
            }else{
                successfull = false;
            }
            //close connection
            st.close();
            con.close();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,ex.getMessage());
        }
        
        return successfull;
    }
    //update data
    public static boolean updateevent(String[] data){
        boolean successfull = false;
        try{
            //create a connection
            Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/main","root","1234");
            Statement st = con.createStatement();
            //quary
            String sql = "UPDATE main.events SET Date = '"+data[1]+"',Name = '"+data[2]+"',Participation = '"+data[3]+"'WHERE EventID = '"+data[0]+"'  ";
            int rs = st.executeUpdate(sql);
            //check the process
            if(rs>0){
            successfull = true;
            }else{
            successfull = false;
             }
             //close connection
            st.close();
            con.close();
        }catch(SQLException ex){  
            JOptionPane.showMessageDialog(null,ex.getMessage());
        }
        return successfull;
    }
    // delete data
    public static boolean deleteevent(String id){
        boolean successfull = false;
        try{
            //create a connection
            Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/main","root","1234");
            Statement st = con.createStatement();
            //quary
            String sql = "DELETE FROM main.events WHERE EventID='"+id+"'";
            int rs = st.executeUpdate(sql);
            //check the process
            if(rs>0){
            successfull = true;
            }else{
            successfull = false;
            }
            //close connection
            st.close();
            con.close();
        }catch(SQLException e){  
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        return successfull;
    }

    public static void getevents(JTable tblData, String datec){
        try {
            //get database connection
            Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/main","root","1234");
            Statement st = con.createStatement();
            //quary
            String sql = "SELECT * FROM main.events where Date ='"+datec+"';";
            ResultSet rs = st.executeQuery(sql);
            ResultSetMetaData rsmd = rs.getMetaData();
            DefaultTableModel model = (DefaultTableModel) tblData.getModel();
            
            //set data to the table
            String[] colName = {"Date","Name","Participation"};
            model.setColumnIdentifiers(colName);
            String eid,date,name,participation;
            while(rs.next()){
                date = rs.getString(2);
                name = rs.getString(3);
                participation = rs.getString(4);
                
                String[] row = {date,name,participation};
                model.addRow(row);
                
            }
            //close connection
            st.close();
            con.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex.getMessage());
            
        }
        
    
    }
}
