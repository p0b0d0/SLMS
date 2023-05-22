package user;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import UI.*;

import UI.Lgin;

public class userLoginController {
    // static methord to pass the values to model class and also check the input values
    public static void checkuser(String id, String password){
        //check the values
        if(id.length() != 5 || password.equals(null)){
            JOptionPane.showMessageDialog(null,"Incorrect User Name or Password");
            Lgin newlogin = new Lgin();
            newlogin.setVisible(true);
        }else{
            //get the password 
            ArrayList<user> userlist= new ArrayList<>();
            user mainuser;
            userlist = userDBmodel.validateUser(id);
            mainuser = userlist.get(0);
        
            //check the password
            if(id.equals(mainuser.getUserId())){
                if(mainuser.getPassword().equals(password)){
                    //open the relevent JFrame
                    if(mainuser.getPosition().equals("Manager")){
                        Manager userManager = new Manager();
                        userManager.setusername(userManager,mainuser.getName());
                        
                    }else{
                        User mainUser = new User();
                        mainUser.setusername(mainUser,mainuser.getName());
                        
                    }
                //if wrong display error
                }else{
                    JOptionPane.showMessageDialog(null,"Incorrect Password");
                    Lgin newlogin = new Lgin();
                    newlogin.setVisible(true);
                }
            }
        }
    }
    //get the user id
    public static String setid(user mainuser){
        return mainuser.getUserId();
    }
}
