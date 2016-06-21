package importingXML;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class ConnectionDB {

	private static String url = "jdbc:mysql://localhost:3306/NexThink?"; 	// this is a local host url
	private static String userName = "root";
	private static String password = "521buaa";

	
	public void startDB() {
		List<Record> recordList = new ArrayList<Record>();
	
		XML_Handler change = new XML_Handler(); // call XML_Handler class's XML_Parser() method 
		recordList = change.XML_Parser();  // get the returned parsed data in recordList
	
		connectionDriver(); 	// connect to DB
		passinUpdate(recordList); // Write data to DataBase

	}

	
	// Enables java to connect to mysql database
	public void connectionDriver() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Connected to mySQL");
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}
	}

	
	
	// Save Name and Last IP Address into Table Device
	// Save Serial Number and Resolution (Horizontal * Vertical) into Table Monitor
	
	public void passinUpdate(List<Record> recordList) {

		try {
			Connection connect2 = DriverManager.getConnection(url, userName, password);
			connect2.setAutoCommit(false);
			System.out.println("Established Conenction.....");

			String insertDevice = "INSERT INTO Device" + "(Name, Last_Ip_Address) VALUES" + "(?,?)";
			String insertMonitor = "INSERT INTO Monitor" + "(Serial_Number, Resolution) VALUES" + "(?,?)";

			PreparedStatement statement2 = connect2.prepareStatement(insertDevice);
			PreparedStatement statement3 = connect2.prepareStatement(insertMonitor);

			for (Record record : recordList) {
				statement2.setString(1, record.getName());
				statement2.setString(2, record.getLastIPAddress());
				statement2.addBatch();
				statement3.setString(1, record.getSerialNumber());
				statement3.setString(2, record.getResolution());
				statement3.addBatch();
			}
			
			statement2.executeBatch();
			statement3.executeBatch();
			connect2.commit();
			connect2.close();
			
		    System.out.println("All the data has been saved successfully in MySQL");

		} catch (SQLException sqlException) {
			if (sqlException.getErrorCode() == 1007) {
				// Database already exists error
				System.out.println(sqlException.getMessage());
			} else {
				// Some other problems, e.g. Server down, no permission, etc
				sqlException.printStackTrace();
			}
		}
	}

}
