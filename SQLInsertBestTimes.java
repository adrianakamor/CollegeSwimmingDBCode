// insert a new best times into the database
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
//import java.sql.ResultSet; // need for select queries to get result set
//import java.sql.Statement; // if writing a sql statement directly
import java.util.Scanner; 


public class SQLInsertBestTimes {

    // Connect to your database.
    // Replace server name, username, and password with your credentials
    public static void main(String[] args) {
        String connectionUrl = 
        "jdbc:sqlserver://localhost;"
                + "database=university;"
                + "user=dbuser;" 
                + "password=scsd431134dscs;"
                + "encrypt=true;"
                + "trustServerCertificate=true;"
                + "loginTimeout=30;";
        Scanner myObj = new Scanner(System.in);
	int inpSwimmerID, inpYear; 
        String inpEventName, inpMeetName, inpMonth;
        float inpEventTime;

        // Enter username and press Enter
       System.out.println("Enter swimmerID then enter. "); 
       inpSwimmerID = myObj.nextInt();   
       myObj.nextLine(); 
       System.out.println("Enter event name then enter.");
       inpEventName = myObj.nextLine();
       System.out.println("Enter event time in seconds then enter. "); 
       inpEventTime = myObj.nextFloat();
       myObj.nextLine(); 
       System.out.println("Enter meet name then enter.");
       inpMeetName = myObj.nextLine();
       System.out.println("Enter month then enter.");
       inpMonth = myObj.nextLine();
       System.out.println("Enter year then enter.");
       inpYear = myObj.nextInt();
       
       myObj.close();

       //System.out.println("dept_name: " + inpDeptName + "  building: " 
             //+ inpBuilding + "   budget: " + inpBudget);
        
        // 3 ? because 3 parameters to stored proc needed. Index for paramters start at 1.
        String callStoredProc = "{call collegeswimming.dbo.insertBestTimes(?,?,?,?,?,?)}"; 
   
        try (Connection connection = DriverManager.getConnection(connectionUrl);
                PreparedStatement prepsInsertBestTimes = connection.prepareStatement(callStoredProc);) {
            // 3 parameters to stored proc start with a parameter index of 1
            prepsInsertBestTimes.setInt(1, inpSwimmerID); 
            prepsInsertBestTimes.setString(2, inpEventName);
            prepsInsertBestTimes.setFloat(3, inpEventTime);
	    prepsInsertBestTimes.setString(4, inpMeetName); 
            prepsInsertBestTimes.setString(5, inpMonth);
            prepsInsertBestTimes.setInt(6, inpYear);

            prepsInsertBestTimes.execute();
        }
        // Handle any errors that may have occurred.
        catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
}
