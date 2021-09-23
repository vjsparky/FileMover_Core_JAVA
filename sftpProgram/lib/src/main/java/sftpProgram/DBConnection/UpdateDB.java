package sftpProgram.DBConnection;

import sftpProgram.entities.SftpEntities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateDB {

	public static void updateDBStatus(String name,String status,String date) {
	
	try {
		Class.forName(SftpEntities.dbdriver);
		//Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/vijay","root","Sparky@6@21998");
		Connection connect = DriverManager.getConnection(SftpEntities.dburl,SftpEntities.username,SftpEntities.password);
		insermethod(connect,name,status,date);
		connect.close();  
		}catch (Exception e)
		{System.out.println(e);}
	}
	
	public static void insermethod(Connection con,String name,String status,String date) throws SQLException{
		{
//			Statement stmt = connect.createStatement();
//			ResultSet rs = stmt.executeQuery("select * from vijay.product");
//			while(rs.next()){
//				System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3));  
//				}
			PreparedStatement stmt=con.prepareStatement("insert into vijay.sftpaudit values(?,?,?)");  
			stmt.setString(1, name);//1 specifies the first parameter in the query  
			stmt.setString(2, status);
			stmt.setString(3, date);
			stmt.executeUpdate();  
		}
	}
	
}
