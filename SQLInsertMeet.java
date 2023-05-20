// Inserting a new meet into the database 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner; 
import java.sql.SQLException;

public class SQLInsertMeet {

    // Connect to your database.
    public static void main(String[] args) {
        String connectionUrl = 
        "jdbc:sqlserver://localhost;"
                + "database=collegeswimming;"
                + "user=dbuser;" 
                + "password=scsd431134dscs;"
                + "encrypt=true;"
                + "trustServerCertificate=true;"
                + "loginTimeout=30;";
        Scanner myObj = new Scanner(System.in);
        String inpMeetName, inpMonth, inpType;
	int inpYear; 

        // Enter username and press Enter
       System.out.println("Enter meet name then enter. "); 
       inpMeetName = myObj.nextLine();   
       System.out.println("Enter month of the meet then enter.");
       inpMonth = myObj.nextLine();
       System.out.println("Enter year of the meet then enter. "); 
       inpYear = myObj.nextInt();
       myObj.nextLine(); 
       System.out.println("Enter type of meet then enter. "); 
       inpType = myObj.nextLine();   
       
       myObj.close();

       System.out.println("meetname: " + inpMeetName + " month: " 
             + inpMonth + " year: " + inpYear
	     + " type: " + inpType);

	String callStoredProc = "{call collegeswimming.dbo.insertMeet(?,?,?,?)}"; 
	ResultSet resultSet = null;
   
        try (Connection connection = DriverManager.getConnection(connectionUrl);
                PreparedStatement prepsInsertMeet = connection.prepareStatement(callStoredProc,Statement.RETURN_GENERATED_KEYS);) {
            // 3 parameters to stored proc start with a parameter index of 1
            prepsInsertMeet.setString(1, inpMeetName);
            prepsInsertMeet.setString(2, inpMonth);
            prepsInsertMeet.setInt(3, inpYear);
	    prepsInsertMeet.setString(4, inpType); 
            
	    prepsInsertMeet.execute(); 
	}
        // Handle any errors that may have occurred.
        catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
}
