// Finding the event rank of a swimmer within their team 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Scanner; 
import java.sql.SQLException;

public class SQLFindEventRank {

    // Connect to your database
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
        String inpEventName;

        // User Input
        System.out.println("Enter swimmerID then enter. "); 
        inpSwimmerID = myObj.nextInt();   
        myObj.nextLine(); 
        System.out.println("Enter event name then enter.");
        inpEventName= myObj.nextLine();
       
        myObj.close();

        System.out.println("SwimmerID: " + inpSwimmerID + "  EventName: " 
             + inpEventName);

        String callStoredProc = "{call collegeswimming.dbo.findEventRank(?,?)}"; 
        
   
       try (Connection connection = DriverManager.getConnection(connectionUrl);
                PreparedStatement prepsRank = connection.prepareStatement(callStoredProc);) {
            prepsRank.setInt(1, inpSwimmerID);
            prepsRank.setString(2, inpEventName);
	      
            ResultSet resultSet = prepsRank.executeQuery();

            while(resultSet.next())
                    System.out.println("Rank: " + resultSet.getInt(1)); 
           
        }
        // Handle any errors that may have occurred.
        catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
}



