// Inserting a new team into the database 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner; 
import java.sql.SQLException;

public class SQLInsertTeam {

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
        String inpTeamName, inpCity, inpState, inpConferenceName;

        // Enter username and press Enter
       System.out.println("Enter team name then enter. "); 
       inpTeamName = myObj.nextLine();   
       System.out.println("Enter city then enter.");
       inpCity = myObj.nextLine();
       System.out.println("Enter state then enter. "); 
       inpState = myObj.nextLine();
       System.out.println("Enter conference name then enter. "); 
       inpConferenceName = myObj.nextLine();   
       
       myObj.close();

       System.out.println("teamname: " + inpTeamName + "  city: " 
             + inpCity + "   state: " + inpState
	     + " conferencename: " + inpConferenceName);

	String callStoredProc = "{call collegeswimming.dbo.insertTeam(?,?,?,?)}"; 
	ResultSet resultSet = null;
   
        try (Connection connection = DriverManager.getConnection(connectionUrl);
                PreparedStatement prepsInsertTeam = connection.prepareStatement(callStoredProc,Statement.RETURN_GENERATED_KEYS);) {
            // 3 parameters to stored proc start with a parameter index of 1
            prepsInsertTeam.setString(1, inpTeamName);
            prepsInsertTeam.setString(2, inpCity);
            prepsInsertTeam.setString(3, inpState);
	    prepsInsertTeam.setString(4, inpConferenceName); 
            
	    prepsInsertTeam.execute(); 
	}
        // Handle any errors that may have occurred.
        catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
}
