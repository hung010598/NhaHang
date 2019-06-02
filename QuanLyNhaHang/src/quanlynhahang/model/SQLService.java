package quanlynhahang.model;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

public class SQLService {
	public static Connection conn;
	
	public  SQLService(){
		try
		{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		String connectionUrl="jdbc:sqlserver://HUNG-UIT:1433;databaseName=NhaHangUIT;integratedSecurity=true;";
		conn= DriverManager.getConnection(connectionUrl);
	    
		}catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "kết nối cơ sở dữ liệu thất bại");
			e.printStackTrace();
		}
	}
	
	public Connection connect1(){
		return conn;
	}
	
}
