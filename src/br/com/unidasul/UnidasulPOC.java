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
                "jdbc:sap://cb12347d-3a21-4c2e-9c42-ed1db64758f1.hana.prod-br10.hanacloud.ondemand.com:443?encrypt=true&validateCertificate=true", "UNIDASUL_HDI_DB_1_9EYTD2GM2A0W2DFV8QNC1DH8S_RT", "Gy3P5nYcnswcB8UGjBT0yaKYaml2novMs9lPb1r_oBRQDx-NeAhWhWAkmHKg3Lwxvisiu9.ovMjDFdshc.RrG5tz2bkZScUmqWsK6VT5xKRZU-uOzKtpwbC31OhwbtGX");
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