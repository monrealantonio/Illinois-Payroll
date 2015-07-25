import java.sql.*;

public class TestDriver 
{

	public static void main(String[] args) 
	{
		System.out.println(Employee.getWage(2));
		Employee.setWage(2, 25.0);
		System.out.println(Employee.getWage(2));
		Employee.addEmployee("Antonio", "Monreal", "1952-01-18", "123 Fake St.", "(773)123-4567", "single", 2, 100.0, "123-45-6789");
		System.out.println(Employee.getWage(4));
		try {
			
		ResultSet rs = Employee.listEmployee();
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