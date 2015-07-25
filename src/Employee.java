import java.sql.*;

public class Employee 
{
	static Connection conn;
	
	public static void addEmployee(String fname, String lname, String bdate,
			String address, String phone, String marital, int withholding, double wage, String ssn)
	{
		try
		{
			conn = DBConnect.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT E_id FROM ippdb.employee");
			int rownum = 1;
			while(rs.next())
			{
				rownum++;
			}
			System.out.println(rownum);
			String sqlStatement ="INSERT INTO ippdb.employee " + "VALUES (" + rownum + ", 'Antonio', 'Monreal', '1990-05-07', '123 blah st', '(773)123-4567', 'single', 2, 100.0, '123-54-6798')";
			stmt.executeUpdate(sqlStatement);
			stmt.close();
			conn.close();
		}
		catch (Exception e)
		{
			System.out.println("Insert method error");
		}
	}
	
	public static ResultSet listEmployee()
	{
		ResultSet rs = null;
		try
		{
			conn = DBConnect.getConnection();
			Statement stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT E_id, E_fname, E_lname FROM ippdb.employee ORDER BY E_id");
			conn.close();
			return rs;
		}
		catch (Exception e)
		{
			System.out.println("error");
		}
		return rs;
	}
	
	public static double getWage(int eid)
	{
		double wage = 0.0;
		try
		{
			conn = DBConnect.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT E_wage_rate FROM ippdb.employee WHERE E_id = " + eid);
			while(rs.next())
			{
				wage = rs.getDouble("E_wage_rate");
			}
			stmt.close();
			conn.close();
		}
		catch (Exception e)
		{
			System.out.println("error");
		}
		return wage;
	}
	
	public static void setWage(int eid, double wage)
	{
		try
		{
			conn = DBConnect.getConnection();
			Statement stmt = conn.createStatement();
			stmt.executeUpdate("UPDATE ippdb.employee SET E_wage_rate = " + wage + " WHERE E_id = " + eid);
			stmt.close();
			conn.close();
		}
		catch (Exception e)
		{
			System.out.println("error");
		}
	}
}