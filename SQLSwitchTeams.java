// switching a swimmer's team 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner; 
import java.sql.SQLException;

public class SQLSwitchTeams {

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
        int inpSwimmerID, inpTeamID; 

       System.out.println("Enter swimmerID then enter. "); 
       inpSwimmerID = myObj.nextInt();   
       myObj.nextLine();
       System.out.println("Enter new teamID then enter.");
       inpTeamID = myObj.nextInt();
       
       myObj.close();

       System.out.println("swimmerID: " + inpSwimmerID + " teamID: " 
             + inpTeamID);

	String callStoredProc = "{call collegeswimming.dbo.swimmerTransfer(?,?)}"; 
	ResultSet resultSet = null;
   
        try (Connection connection = DriverManager.getConnection(connectionUrl);
                PreparedStatement prepsSwitchTeam = connection.prepareStatement(callStoredProc,Statement.RETURN_GENERATED_KEYS);) {
            prepsSwitchTeam.setInt(1, inpSwimmerID);
            prepsSwitchTeam.setInt(2, inpTeamID);
            
	    prepsSwitchTeam.execute(); 
	}
        // Handle any errors that may have occurred.
        catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
}
