// Retiring a swimmer into   
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner; 
import java.sql.SQLException;

public class SQLRetireSwimmer {

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
        int inpSwimmerID;

        // Enter username and press Enter
       System.out.println("Enter the swimmer's ID then enter. "); 
       inpSwimmerID = myObj.nextInt();   
       
       myObj.close();

       System.out.println("Swimmer ID: " + inpSwimmerID + " is now retired");
	String callStoredProc = "{call collegeswimming.dbo.retiredSwimmer(?)}"; 
   
        try (Connection connection = DriverManager.getConnection(connectionUrl);
                PreparedStatement prepsRetiredSwimmer = connection.prepareStatement(callStoredProc);) {
            // 1 parameter to stored proc start with a parameter index of 1
            prepsRetiredSwimmer.setInt(1, inpSwimmerID);
            
	    prepsRetiredSwimmer.execute(); 
	}
        // Handle any errors that may have occurred.
        catch (SQLException e) {
            e.printStackTrace();
        }
}
}
