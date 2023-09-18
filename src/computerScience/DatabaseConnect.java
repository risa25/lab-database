package computerScience;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;

public class DatabaseConnect {
	
    public Connection conn;
    
    public Connection connectDatabase() {//function to connect to the mysql database

		try{
			
            conn=DriverManager.getConnection("jdbc:mysql://localhost:8889/ComputerScience","root","root"); 
            //creating the connection and the address is the one written in the getConnection function
        }catch(Exception ex) {  
            JOptionPane.showMessageDialog(null, ex);
        }
        return conn;//returning the connection to the database
    }

}
