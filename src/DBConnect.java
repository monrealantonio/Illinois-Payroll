import java.sql.*;

public class DBConnect 
{	
	static Connection conn = null;
	public static Connection getConnection()
	{
		try 
		{
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?user=root&password=root");
			System.out.println("success");
		}
		catch (Exception e) 
		{
			System.out.println("workplease");
		}
		return conn;
	}
	
	public static void closeConnection()
	{
		try
		{
			conn.close();
		}
		catch (Exception e)
		{
			System.out.println("NO CONNECTION");		
		}
	}
}