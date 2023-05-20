// update a best time in the database
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
//import java.sql.ResultSet; // need for select queries to get result set
//import java.sql.Statement; // if writing a sql statement directly
import java.util.Scanner; 


public class SQLUpdateBestTimes {

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
                PreparedStatement prepsUpdateBestTimes = connection.prepareStatement(callStoredProc);) {
            // 3 parameters to stored proc start with a parameter index of 1
            prepsUpdateBestTimes.setInt(1, inpSwimmerID); 
            prepsUpdateBestTimes.setString(2, inpEventName);
            prepsUpdateBestTimes.setFloat(3, inpEventTime);
	    prepsUpdateBestTimes.setString(4, inpMeetName); 
            prepsUpdateBestTimes.setString(5, inpMonth);
            prepsUpdateBestTimes.setInt(6, inpYear);

            prepsUpdateBestTimes.execute();
        }
        // Handle any errors that may have occurred.
        catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
}
