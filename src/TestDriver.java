import java.sql.*;

public class TestDriver 
{

	public static void main(String[] args) 
	{
		System.out.println(DBConnector.getWage(2));
		DBConnector.setWage(2, 25.0);
		System.out.println(DBConnector.getWage(2));
		DBConnector.addEmployee("Antonio", "Monreal", "1952-01-18", "123 Fake St.", "(773)123-4567", "single", 2, 100.0, "123-45-6789");
		System.out.println(DBConnector.getWage(4));
		try {
			
		ResultSet rs = DBConnector.listEmployee();
		while(rs.next())
		{
			int stuff = rs.getInt("E_id");
			System.out.println(stuff);
		}
		}
		catch (Exception e)
		{
			System.out.println("omfg");
		}
	}
	
}