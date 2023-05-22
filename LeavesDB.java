package Leaves;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class LeavesDB {
    //get data from database
    public static void getleavesdata(JTable tblData){
        try {
            //get database connection
            Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/main","root","1234");
            Statement st = con.createStatement();
            //quary
            String sql = "SELECT * FROM main.leaves;";
            ResultSet rs = st.executeQuery(sql);
            ResultSetMetaData rsmd = rs.getMetaData();
            DefaultTableModel model = (DefaultTableModel) tblData.getModel();
            //set table
            int cols=rsmd.getColumnCount();
            String[] colName = new String[cols];
            for(int i=0;i<cols;i++){
                
                colName[i]=rsmd.getColumnName(i+1);
                model.setColumnIdentifiers(colName);
            }
            String leaveid,userid,name,date,horf,requests,approval,reason;
            while(rs.next()){
                leaveid = rs.getString(1);
                userid = rs.getString(2);
                name = rs.getString(3);
                date = rs.getString(4);
                horf = rs.getString(5);
                requests = rs.getString(6);
                approval = rs.getString(7);
                reason = rs.getString(8);            
                String[] row = {leaveid,userid,name,date,horf,requests,approval,reason};
                model.addRow(row); 
            }
            //close the connection
            st.close();
            con.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex.getMessage());
        }       
    }
    //get specific data that belongs to a user
    public static void getmaindata(String id, JTable table){
        try {
            //get database connection
            Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/main","root","1234");
            Statement st = con.createStatement();
            String sql = "SELECT * FROM main.leaves where Userid ='"+id+"';";
            ResultSet rs = st.executeQuery(sql);
            ResultSetMetaData rsmd = rs.getMetaData();
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            //set the table
            String[] colName = {"leaveid","Userid","Name","Date","H/F","Requests","Approval"};
            model.setColumnIdentifiers(colName);
            String leaveid,userid,name,date,horf,requests,approval;
            while(rs.next()){
                leaveid = rs.getString(1);
                userid = rs.getString(2);
                name = rs.getString(3);
                date = rs.getString(4);
                horf = rs.getString(5);
                requests = rs.getString(6);
                approval = rs.getString(7);
                //reason = rs.getString(8);      
                String[] row = {leaveid,userid,name,date,horf,requests,approval};
                model.addRow(row);
            }
            //close the connection
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
            //create connection
            Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/main","root","1234");
            Statement st = con.createStatement();
            String sql = "INSERT INTO main.leaves VALUES (0,'"+data[0]+"','"+data[1]+"','"+data[2]+"','"+data[3]+"','"+data[4]+"','"+data[5]+"','"+data[6]+"')";
        int rs = st.executeUpdate(sql);
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
    //get data that belongs to a user
    public static void getleavesmain(String id, JTable table){
        
        try {
            //get database connection
            Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/main","root","1234");
            Statement st = con.createStatement();
            String sql = "SELECT * FROM main.leaves WHERE Userid = '"+id+"';";
            ResultSet rs = st.executeQuery(sql);
            ResultSetMetaData rsmd = rs.getMetaData();
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            //set table
            int cols=rsmd.getColumnCount();
            String[] colName = new String[cols];
            for(int i=0;i<cols;i++){
                colName[i]=rsmd.getColumnName(i+1);
                model.setColumnIdentifiers(colName);
            }
            String userid,name,date,horf,requests,approval,reason,leaveid;
            while(rs.next()){
                leaveid = rs.getString(1);
                userid = rs.getString(2);
                name = rs.getString(3);
                date = rs.getString(4);
                horf = rs.getString(5);
                requests = rs.getString(6);
                approval = rs.getString(7);
                reason = rs.getString(8);
                
                String[] row = {leaveid,userid,name,date,horf,requests,approval,reason};
                model.addRow(row);
                
            }
            //close connection
            st.close();
            con.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex.getMessage());   
        }
    }
    //get reason
    public static String getreason(String id){
        String reason = null;
        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/main","root","1234");
            Statement st = con.createStatement();
            String sql = "SELECT Reason from main.leaves WHERE LeaveID='"+id+"'";
            ResultSet rs = st.executeQuery(sql);
            ResultSetMetaData rsmd = rs.getMetaData();
            while(rs.next()){
                reason = rs.getString(1);
            }
            st.close();
            con.close();
        }catch(SQLException ex){     
            JOptionPane.showMessageDialog(null,ex.getMessage());
        }
        return reason;
    }
    //get full data set for manager table
    public static void getleavesformanager(JTable table){
        try {
            //get database connection
            Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/main","root","1234");
            Statement st = con.createStatement();
            String approvalq = "Pending";
            String sql = "SELECT * FROM main.leaves WHERE Approval = '"+approvalq+"';";
            ResultSet rs = st.executeQuery(sql);
            ResultSetMetaData rsmd = rs.getMetaData();
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            
            String[] colName = {"Leaveid","Userid","Name","Date","H/F","Requests"};
            model.setColumnIdentifiers(colName);
            //set table
            String userid,name,date,horf,requests,approval,reason,leaveid;
            while(rs.next()){
                leaveid = rs.getString(1);
                userid = rs.getString(2);
                name = rs.getString(3);
                date = rs.getString(4);
                horf = rs.getString(5);
                requests = rs.getString(6);
                approval = rs.getString(7);
                reason = rs.getString(8);
                
                String[] row = {leaveid,userid,name,date,horf,requests};
                model.addRow(row);
                
            }
            st.close();
            con.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,ex.getMessage());
        }
    }
    //update approval- add aproval data to the table
    public static boolean updateapproval(JTable table, String[] data){
        boolean successfull = false;
        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/main","root","1234");
            Statement st = con.createStatement();
            String sql = "UPDATE main.leaves SET Approval = '"+data[1]+"',Reason = '"+data[2]+"' WHERE LeaveID = '"+data[0]+"'  ";
        int rs = st.executeUpdate(sql);
        
        if(rs>0){
            successfull = true;
        }else{
            successfull = false;
        }
        st.close();
        con.close();
        }catch(SQLException e){  
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        return successfull;
    }
    //delete data
    public static boolean deleteleaves(String id){
        boolean successfull = false;
        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/main","root","1234");
            Statement st = con.createStatement();
            String sql = "DELETE FROM main.leaves WHERE LeaveID='"+id+"'";
            int rs = st.executeUpdate(sql);
        
        if(rs>0){
            successfull = true;
        }else{
            successfull = false;
        }
        st.close();
        con.close();
        }catch(SQLException e){  
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        return successfull;
    }
    //update data
    public static boolean upadteLeaves(String[] data){
        boolean successfull = false;
        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/main","root","1234");
            Statement st = con.createStatement();
            String sql = "UPDATE main.leaves SET Date = '"+data[1]+"',Time = '"+data[2]+"',Request = '"+data[3]+"'WHERE LeaveID = '"+data[0]+"'  ";
            int rs = st.executeUpdate(sql);
            if(rs > 0){
                successfull = true;
            }
        
        st.close();
        con.close();
        }catch(SQLException e){  
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        return successfull;
    }

}
