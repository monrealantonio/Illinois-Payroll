import java.sql.*;

public class TestDriver
{

    public static void main(String[] args)
    {
		/*System.out.println(DBConnector.getWage(2));
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
		}*/
        /*DBConnector.addPayroll(3, 2000.0, 150.0, 50.0, 25.0, 25.0, 1750.0, "2015-07-10", "2015-07-24");
        //DBConnector.setPayrollID(1, 1);
        System.out.println(DBConnector.getPayrollID(2));
        //DBConnector.setGross(1, 1000.0);
        System.out.println(DBConnector.getGross(2));
        //DBConnector.setFIT(1, 100.0);
        System.out.println(DBConnector.getFIT(2));
        //DBConnector.setSIT(1, 50.0);
        System.out.println(DBConnector.getSIT(2));
        //DBConnector.setSocialTax(1, 25.0);
        System.out.println(DBConnector.getSocialTax(2));
        //DBConnector.setMedicare(1, 25.0);
        System.out.println(DBConnector.getMedicare(2));
        //DBConnector.setNet(1, 800.0);
        System.out.println(DBConnector.getNet(2));
        //DBConnector.setStartDate(1, "2015-07-10");
        System.out.println(DBConnector.getStartDate(2));
        //DBConnector.setEndDate(1, "2015-07-24");
        System.out.println(DBConnector.getEndDate(2));*/
        DBConnector.editEmployee(3, "Antonio", "Monreal", "1952-01-18", "123 Fake St.", "(773)123-4567", "single", 2, 100.0, "123-45-6789");
        System.out.println(DBConnector.getWage(3));
    }
}