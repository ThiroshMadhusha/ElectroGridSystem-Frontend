package com;

import java.sql.*;

public class Bill {
	
//	Create Bills Database Connection
	private Connection connect() 
	 { 
		 Connection con = null; 
			 try { 
				 Class.forName("com.mysql.jdbc.Driver"); 
				 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/bill", "root", "2057060"); 
			 } 
			 	catch (Exception e) { 
			 	e.printStackTrace(); 
			 } 
			 	return con; 
	 } 
	
//	Create Bills Read CRUD Operation
	public String readBills() {
		
		String output = ""; 
		try { 
			 Connection con = connect(); 
			 if (con == null) { 
				 return "Error while connecting to the database for reading."; 
			 } 
			 // Prepare the HTML table to be displayed
			 output = "<table border='1' width='200%'><tr><th>Bill Account No</th> <th>Customer Name</th><th>Monthly Unit</th>"+ "<th>Monthly Amount</th> <th>Update</th><th>Remove</th></tr>"; 
			 String query = "select * from bills"; 
				 Statement stmt = con.createStatement(); 
				 ResultSet rs = stmt.executeQuery(query); 
			 // iterate through the rows in the result set
			 while (rs.next()) { 
				 String billID = Integer.toString(rs.getInt("billID")); 
				 String billAccNo = rs.getString("billAccNo"); 
				 String cusName = rs.getString("cusName"); 
				 String billUnit = rs.getString("billUnit"); 
				 String billAmount = rs.getString("billAmount");
				 
			 // Add into the HTML table
			output += "<tr><td><input id='hidItemIDUpdate' name='hidItemIDUpdate' type='hidden' value='" + billID
				 + "'>" + billAccNo + "</td>"; 
			output += "<td>" + cusName + "</td>"; 
			output += "<td>" + billUnit + "</td>"; 
			output += "<td>" + billAmount + "</td>"; 
			 // buttons
			output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"+ "<td><input name='btnRemove' type='submit' value='Remove' class='btnRemove btn btn-danger' data-billid='"
				 + billID + "'>" + "</td></tr>"; 
			 } 
			 con.close(); 
			 // Complete the HTML table
			 output += "</table>"; 
		} 
		catch (Exception e) { 
			 output = "Error while reading the bills."; 
			 	System.err.println(e.getMessage()); 
		} 
			 return output; 
	} 
	
//	Create Insert Bill CRUD Operation
	public String insertBill(String accno, String name, String unit, String amount) { 
		String output = ""; 
		try { 
			 Connection con = connect(); 
			 if (con == null) 
			 { 
				 return "Error while connecting to the database for inserting."; 
			 } 
			 // create a prepared statement
				 String query = " insert into bills (`billID`,`billAccNo`,`cusName`,`billUnit`,`billAmount`)"
				 + " values (?, ?, ?, ?, ?)"; 
				 PreparedStatement preparedStmt = con.prepareStatement(query); 
			 // binding values
				 preparedStmt.setInt(1, 0); 
				 preparedStmt.setString(2, accno); 
				 preparedStmt.setString(3, name); 
				 preparedStmt.setString(4, unit); 
				 preparedStmt.setString(5, amount); 
			 // execute the statement
				 preparedStmt.execute(); 
				 con.close(); 
				 String newItems = readBills(); 
				 output = "{\"status\":\"success\", \"data\": \"" + 
				 newItems + "\"}"; 
		} 
		catch (Exception e) { 
			 output = "{\"status\":\"error\", \"data\": \"Error while inserting the bill.\"}"; 
			 System.err.println(e.getMessage()); 
		} 
		return output; 
	}
	
//	Update Bill CRUD Operation
	public String updateBill(String ID, String accno, String name, String unit, String amount) { 
		String output = ""; 
		try{ 
			Connection con = connect(); 
			if (con == null) { 
				return "Error while connecting to the database for updating."; 
			} 
			 // create a prepared statement
			 String query = "UPDATE bills SET billAccNo=?,cusName=?,billUnit=?,billAmount=? WHERE billID=?"; 
			 PreparedStatement preparedStmt = con.prepareStatement(query); 
			 // binding values
			 preparedStmt.setString(1, accno); 
			 preparedStmt.setString(2, name); 
			 preparedStmt.setString(3, unit); 
			 preparedStmt.setString(4, amount); 
			 preparedStmt.setInt(5, Integer.parseInt(ID)); 
	 
	 
			 // execute the statement
			 preparedStmt.execute(); 
			 con.close(); 
			 String newItems = readBills(); 
			 output = "{\"status\":\"success\", \"data\": \"" + 
			 newItems + "\"}"; 
		} 
		catch (Exception e) { 
			 output = "{\"status\":\"error\", \"data\": \"Error while updating the bill.\"}"; 
			 System.err.println(e.getMessage()); 
		} 
		return output; 
	} 
	
//	Delete Bill CRUD Operation
	
	
	public String deleteBill(String billID) 
	 { 
	 String output = ""; 
	 try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 { 
	 return "Error while connecting to the database for deleting."; 
	 } 
	 // create a prepared statement
	 String query = "delete from bills where billID=?"; 
	 PreparedStatement preparedStmt = con.prepareStatement(query); 
	 // binding values
	 preparedStmt.setInt(1, Integer.parseInt(billID)); 
	 // execute the statement
	 preparedStmt.execute(); 
	 con.close(); 
	 String newItems = readBills(); 
	 output = "{\"status\":\"success\", \"data\": \"" + newItems + "\"}"; 
	 } 
	 catch (Exception e) 
	 { 
	 output = "{\"status\":\"error\", \"data\": \"Error while deleting the item.\"}"; 
	 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 }
}
