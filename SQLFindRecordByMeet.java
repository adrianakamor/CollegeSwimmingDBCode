// Class SQLFindRecordByMeet locates the records broken given an input meet name
// If no records were broken at a given meet, an appropriate statement is returned

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Scanner; 
import java.sql.SQLException;

public class SQLFindRecordByMeet {

    // Connect to the database
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
        String inpMeetName;
        String inpMeetMonth;
        String inpMeetYear;

        // Enter Meet Info
        System.out.println("Enter desired meet name:");
        inpMeetName = myObj.next();
        myObj.nextLine();
        System.out.println("Enter desired meet month:");
        inpMeetMonth = myObj.next();
        System.out.println("Enter desired meet year:");
        inpMeetYear = myObj.next();
        myObj.close();

        System.out.println("Meet name: " + inpMeetName + " || Meet Month: " + inpMeetMonth + " || Meet Year: " + inpMeetYear);

        String callStoredProc = "{call collegeswimming.dbo.meetRecords(?,?,?)}";

        try (Connection connection = DriverManager.getConnection(connectionUrl);
            PreparedStatement prepsMeetRecords = connection.prepareStatement(callStoredProc,Statement.RETURN_GENERATED_KEYS);) {
                prepsMeetRecords.setString(1, inpMeetName);
                prepsMeetRecords.setString(2, inpMeetMonth);
                prepsMeetRecords.setString(3, inpMeetYear);

                ResultSet resultSet = prepsMeetRecords.executeQuery();
                ResultSetMetaData rsmd = resultSet.getMetaData(); 
                int cols = rsmd.getColumnCount(); 
                for (int i = 1; i <= cols; i++) 
                        System.out.print(rsmd.getColumnName(i) + "\t");
                     
     
                while (resultSet.next()) {
                    System.out.println(resultSet.getString(1) + "\t" 
                            + resultSet.getString(2) +"\t" +resultSet.getInt(3)
                            + "\t" + resultSet.getString(4) + "\t" + resultSet.getInt(5)
                            + "\t" + resultSet.getString(6) + "\t" + resultSet.getString(7)
                            + "\t" + resultSet.getString(8) + "\t" + resultSet.getFloat(9));
                     }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }  
}