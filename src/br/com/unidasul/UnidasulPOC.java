package br.com.unidasul;

import java.sql.*;
import com.sap.db.jdbc.Driver;

public class UnidasulPOC {

	public static void main(String[] args) {

		System.out.println("Java version: " + com.sap.db.jdbc.Driver.getJavaVersion());
		System.out.println("SAP driver details: " + com.sap.db.jdbc.Driver.getVersionInfo() + "\n");
		Connection connection = null;
		
		try {  
            connection = DriverManager.getConnection(  
                //Specify the connection parameters
                "jdbc:sap://XXXXXXXX?encrypt=true&validateCertificate=true", "XXXXXXXXx", "XXXXX");
        }
        catch (SQLException e) {
            System.err.println("Connection Failed:");
            System.err.println(e);
            return;
        }
        if (connection != null) {
            try {
                System.out.println("Connection to HANA successful!");
                Statement stmt = connection.createStatement();
                ResultSet resultSet = stmt.executeQuery("SELECT TOP 50 MANDT, MATNR, ERNAM, BEGRU FROM UNIDASUL_HDI_DB_1.CV_MARA;");
                while (resultSet.next()) {
                    String mandt = resultSet.getString(1);
                    String material = resultSet.getString(2);
                    String userName = resultSet.getString(3);
                    System.out.println(mandt + " - " + material + " " + userName);
                }
            }
            catch (SQLException e) {
                System.err.println("Query failed!");
            }
        }
    }
}
