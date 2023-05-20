// Insert a new conference into the database
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner; 
import java.sql.SQLException;

public class SQLInsertConference {

    // Connect to your database.
    // Replace server name, username, and password with your credentials
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
        String inpConferenceName;
        int inpYearFounded;

        // Enter username and press Enter
       System.out.println("Enter conference name then enter. "); 
       inpConferenceName = myObj.nextLine();   
       System.out.println("Enter year founded then enter.");
       inpYearFounded = myObj.nextInt();
       
       myObj.close();

       System.out.println("conferencename: " + inpConferenceName + "  yearfounded: " 
             + inpYearFounded);
       /*String insertSql = "INSERT INTO conference (conferencename, yearfounded) " +
              " values (?, ?); " ;
       ResultSet resultSet = null;
       try (Connection connection = DriverManager.getConnection(connectionUrl);
                PreparedStatement prepsInsert = 
                      connection.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);) {
            prepsInsert.setString(1, inpConferenceName);
            prepsInsert.setInt(2, inpYearFounded);
            prepsInsert.execute();
            // Retrieve the generated key from the insert. None in this example.
            resultSet = prepsInsert.getGeneratedKeys();
            // Print the ID of the inserted row. Again, will be null because no keys auto gen
            while (resultSet.next()) {
                System.out.println("Generated: " + resultSet.getString(1));
            }
        }*/
	String callStoredProc = "{call collegeswimming.dbo.insertConference(?,?)}"; 
   
        try (Connection connection = DriverManager.getConnection(connectionUrl);
                PreparedStatement prepsInsertConference = connection.prepareStatement(callStoredProc);) {
            // 3 parameters to stored proc start with a parameter index of 1
            prepsInsertConference.setString(1, inpConferenceName);
            prepsInsertConference.setInt(2, inpYearFounded);
            
	    prepsInsertConference.execute(); 
	}
        // Handle any errors that may have occurred.
        catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
}
