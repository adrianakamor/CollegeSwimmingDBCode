// Inserting new swimmer into   
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner; 
import java.sql.SQLException;

public class SQLInsertSwimmer {

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
        String inpFirstName, inpLastName, inpGender, inpSpeciality, inpMajor, inpHometown;
        int inpTeamID, inpYear;

        // User Input
       System.out.println("Enter first name then enter. "); 
       inpFirstName = myObj.nextLine();   
       System.out.println("Enter last name then enter.");
       inpLastName = myObj.nextLine();
       System.out.println("Enter gender then enter. "); 
       inpGender = myObj.nextLine();
       System.out.println("Enter teamID then enter. "); 
       inpTeamID = myObj.nextInt();   
       System.out.println("Enter graduation year then enter.");
       inpYear = myObj.nextInt();
       myObj.nextLine(); 
       System.out.println("Enter major then enter. "); 
       inpMajor = myObj.nextLine();
       System.out.println("Enter speciality then enter. "); 
       inpSpeciality = myObj.nextLine();   
       System.out.println("Enter hometown then enter.");
       inpHometown = myObj.nextLine();
       
       myObj.close();

       System.out.println("firstname: " + inpFirstName + "  lastname: " 
             + inpLastName + "   gender: " + inpGender
	     + " teamID: " + inpTeamID + " year: " + inpYear
	     + " major : " + inpMajor + " speciality: " + inpSpeciality
	     + " hometown: " + inpHometown);
	    String callStoredProc = "{call collegeswimming.dbo.insertSwimmer(?,?,?,?,?,?,?,?)}"; 
   
        try (Connection connection = DriverManager.getConnection(connectionUrl);
                PreparedStatement prepsInsertSwimmer = connection.prepareStatement(callStoredProc);) {
            // 3 parameters to stored proc start with a parameter index of 1
            prepsInsertSwimmer.setString(1, inpFirstName);
            prepsInsertSwimmer.setString(2, inpLastName);
            prepsInsertSwimmer.setString(3, inpGender);
	        prepsInsertSwimmer.setInt(4, inpTeamID);
            prepsInsertSwimmer.setInt(5, inpYear);
            prepsInsertSwimmer.setString(6,inpMajor);
            prepsInsertSwimmer.setString(7, inpSpeciality);
            prepsInsertSwimmer.setString(8,inpHometown);
            
	        prepsInsertSwimmer.execute(); 
	    }
        // Handle any errors that may have occurred.
        catch (SQLException e) {
            e.printStackTrace();
        }
    }   
}
