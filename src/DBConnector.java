/*
CREATE TABLE `employee` (
  `E_id` int(11) NOT NULL,
  `E_fname` varchar(45) NOT NULL,
  `E_lname` varchar(45) NOT NULL,
  `E_birthdate` date DEFAULT NULL,
  `E_address` varchar(75) DEFAULT NULL,
  `E_phone` varchar(45) DEFAULT NULL,
  `E_marital_status` varchar(7) NOT NULL,
  `E_withholding` int(11) NOT NULL,
  `E_wage_rate` double NOT NULL,
  `E_SSN` varchar(11) DEFAULT NULL,
  PRIMARY KEY (`E_id`)
) ENGINE=ippdb DEFAULT CHARSET=utf8;

CREATE TABLE `payroll` (
  `P_id` int(11) NOT NULL,
  `E_id` int(11) NOT NULL,
  `P_gross` double NOT NULL,
  `P_FIT` double NOT NULL,
  `P_SIT` double NOT NULL,
  `P_social_security` double NOT NULL,
  `P_medicare` double NOT NULL,
  `P_net` double NOT NULL,
  `P_start_date` date NOT NULL,
  `P_end_date` date NOT NULL,
  PRIMARY KEY (`P_id`),
  KEY `payroll_to_employee_idx` (`E_id`),
  CONSTRAINT `payroll_to_employee` FOREIGN KEY (`E_id`) REFERENCES `employee` (`E_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=ippdb DEFAULT CHARSET=utf8;

*/
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;


public class DBConnector
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
            String sqlStatement = "INSERT INTO ippdb.employee VALUES (" + rownum + ", '" + fname + "', '" + lname + "', '" + bdate + "', '" + address + "', '" + phone + "', '" + marital + "', " + withholding + ", " + wage + ", '" + ssn + "')";
            stmt.executeUpdate(sqlStatement);
            stmt.close();
            conn.close();
        }
        catch (Exception e)
        {
            System.out.println("Insert method error");
        }
    }

    public static ObservableList<Employee> listEmployee()
    {
        ResultSet rs = null;
        ObservableList<Employee> employees = FXCollections.observableArrayList();
        try
        {
            conn = DBConnect.getConnection();
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT E_id, E_fname, E_lname FROM ippdb.employee ORDER BY E_id");
            while(rs.next())
            {
                Employee emp = new Employee(rs.getInt("E_id"), rs.getString("E_fname"), rs.getString("E_lname"));
                employees.add(emp);
            }
            stmt.close();
            conn.close();
        }
        catch (Exception e)
        {
            System.out.println("error");
        }

        return employees;
    }

    public static void editEmployee(int eid, String fname, String lname, String bdate, String address, String phone, String marital, int withholding, double wage, String ssn) {
        try {
            conn = DBConnect.getConnection();
            Statement e = conn.createStatement();
            String sqlStatement = "UPDATE ippdb.employee SET E_fname = '" + fname + "', E_lname = '" + lname + "', E_birthdate = '" + bdate + "', E_address = '" + address + "', E_phone = '" + phone + "', E_marital_status = '" + marital + "', E_withholding = " + withholding + ", E_wage_rate = " + wage + ", E_SSN = '" + ssn + "' WHERE E_id = " + eid;
            e.executeUpdate(sqlStatement);
            e.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("Update method error");
        }

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

    public static String getFname(int eid)
    {
        String fname="";
        try
        {
            conn = DBConnect.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT E_fname FROM ippdb.employee WHERE E_id = " + eid);
            while(rs.next())
            {
                fname = rs.getString("E_fname");
            }
            stmt.close();
            conn.close();
        }
        catch (Exception e)
        {
            System.out.println("error");
        }
        return fname;
    }
    public static String getLname(int eid)
    {
        String lname = "";
        try
        {
            conn = DBConnect.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT E_lname FROM ippdb.employee WHERE E_id = " + eid);
            while(rs.next())
            {
                lname = rs.getString("E_lname");
            }
            stmt.close();
            conn.close();
        }
        catch (Exception e)
        {
            System.out.println("error");
        }
        return lname;
    }
    public static String getBdate(int eid)
    {
        String bdate = "";
        try
        {
            conn = DBConnect.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT E_birthdate FROM ippdb.employee WHERE E_id = " + eid);
            while(rs.next())
            {
                bdate = rs.getString("E_birthdate");
            }
            stmt.close();
            conn.close();
        }
        catch (Exception e)
        {
            System.out.println("error");
        }
        return bdate;
    }
    public static String getAddress(int eid)
    {
        String address = "";
        try
        {
            conn = DBConnect.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT E_address FROM ippdb.employee WHERE E_id = " + eid);
            while(rs.next())
            {
                address = rs.getString("E_address");
            }
            stmt.close();
            conn.close();
        }
        catch (Exception e)
        {
            System.out.println("error");
        }
        return address;
    }

    public static String getPhone(int eid)
    {
        String phone = "";
        try
        {
            conn = DBConnect.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT E_phone FROM ippdb.employee WHERE E_id = " + eid);
            while(rs.next())
            {
                phone = rs.getString("E_phone");
            }
            stmt.close();
            conn.close();
        }
        catch (Exception e)
        {
            System.out.println("error");
        }
        return phone;
    }
    public static String getMarital(int eid)
    {

        String marital = "";
        try
        {
            conn = DBConnect.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT E_marital_status FROM ippdb.employee WHERE E_id = " + eid);
            while(rs.next())
            {
                marital = rs.getString("E_marital_status");
            }
            stmt.close();
            conn.close();
        }
        catch (Exception e)
        {
            System.out.println("error");
        }
        return marital;
    }
    public static int getWithholding(int eid)
    {
        int withhold = 0;
        try
        {
            conn = DBConnect.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT E_withholding FROM ippdb.employee WHERE E_id = " + eid);
            while(rs.next())
            {
                withhold = rs.getInt("E_withholding");
            }
            stmt.close();
            conn.close();
        }
        catch (Exception e)
        {
            System.out.println("error");
        }
        return withhold;
    }
    public static String getSSN(int eid)
    {
        String ssn = "";
        try
        {
            conn = DBConnect.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT E_ssn FROM ippdb.employee WHERE E_id = " + eid);
            while(rs.next())
            {
                ssn = rs.getString("E_ssn");
            }
            stmt.close();
            conn.close();
        }
        catch (Exception e)
        {
            System.out.println("error");
        }
        return ssn;
    }

    public static double getGross(int pid)
    {
        double gross = 0.0;
        try
        {
            conn = DBConnect.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT P_gross FROM ippdb.payroll WHERE P_id = " + pid);
            while(rs.next())
            {
                gross = rs.getDouble("P_gross");
            }
            stmt.close();
            conn.close();
        }
        catch (Exception e)
        {
            System.out.println("error");
        }
        return gross;
    }

    public static double getFIT(int pid)
    {
        double FIT = 0.0;
        try
        {
            conn = DBConnect.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT P_FIT FROM ippdb.payroll WHERE P_id = " + pid);
            while(rs.next())
            {
                FIT = rs.getDouble("P_FIT");
            }
            stmt.close();
            conn.close();
        }
        catch (Exception e)
        {
            System.out.println("error");
        }
        return FIT;
    }

    public static double getSIT(int pid)
    {
        double SIT = 0.0;
        try
        {
            conn = DBConnect.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT P_SIT FROM ippdb.payroll WHERE P_id = " + pid);
            while(rs.next())
            {
                SIT = rs.getDouble("P_SIT");
            }
            stmt.close();
            conn.close();
        }
        catch (Exception e)
        {
            System.out.println("error");
        }
        return SIT;
    }

    public static double getSocialTax(int pid)
    {
        double SST = 0.0;
        try
        {
            conn = DBConnect.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT P_social_security FROM ippdb.payroll WHERE P_id = " + pid);
            while(rs.next())
            {
                SST = rs.getDouble("P_social_security");
            }
            stmt.close();
            conn.close();
        }
        catch (Exception e)
        {
            System.out.println("error");
        }
        return SST;
    }

    public static double getMedicare(int pid)
    {
        double medicare = 0.0;
        try
        {
            conn = DBConnect.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT P_medicare FROM ippdb.payroll WHERE P_id = " + pid);
            while(rs.next())
            {
                medicare = rs.getDouble("P_medicare");
            }
            stmt.close();
            conn.close();
        }
        catch (Exception e)
        {
            System.out.println("error");
        }
        return medicare;
    }

    public static double getNet(int pid)
    {
        double net = 0.0;
        try
        {
            conn = DBConnect.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT P_net FROM ippdb.payroll WHERE P_id = " + pid);
            while(rs.next())
            {
                net = rs.getDouble("P_net");
            }
            stmt.close();
            conn.close();
        }
        catch (Exception e)
        {
            System.out.println("error");
        }
        return net;
    }

    public static String getStartDate(int pid)
    {
        String startDate = "";
        try
        {
            conn = DBConnect.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT P_start_date FROM ippdb.payroll WHERE P_id = " + pid);
            while(rs.next())
            {
                startDate = rs.getString("P_start_date");
            }
            stmt.close();
            conn.close();
        }
        catch (Exception e)
        {
            System.out.println("error");
        }
        return startDate;
    }

    public static String getEndDate(int pid)
    {
        String endDate = "";
        try
        {
            conn = DBConnect.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT P_end_date FROM ippdb.payroll WHERE P_id = " + pid);
            while(rs.next())
            {
                endDate = rs.getString("P_end_date");
            }
            stmt.close();
            conn.close();
        }
        catch (Exception e)
        {
            System.out.println("error");
        }
        return endDate;
    }

    public static void addPayroll(int eid, double gross, double fit, double sit, double social, double medicare, double net, String startDate, String endDate)
    {
        try
        {
            conn = DBConnect.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT P_id FROM ippdb.payroll");
            int rownum = 1;
            while(rs.next())
            {
                rownum++;
            }
            System.out.println(rownum);
            String sqlStatement = "INSERT INTO ippdb.payroll VALUES (" + rownum + ", " + eid + ", " + gross + ", " + fit + ", " + sit + ", " + social + ", " + medicare + ", " + net + ", '" + startDate + "', '" + endDate + "')";
            stmt.executeUpdate(sqlStatement);
            stmt.close();
            conn.close();
        }
        catch (Exception e)
        {
            System.out.println("Insert method error");
        }
    }

    public static ObservableList<Payroll> listPayroll(int eid)
    {
        ResultSet rs = null;
        ObservableList<Payroll> payrollRecords = FXCollections.observableArrayList();
        try
        {
            conn = DBConnect.getConnection();
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT P_id, P_start_date, P_end_date, P_net FROM ippdb.payroll WHERE E_id = " + eid);
            while(rs.next())
            {
                Payroll rec = new Payroll(rs.getInt("P_id"), rs.getString("P_start_date"), rs.getString("P_end_date"), rs.getDouble("P_net"));
                payrollRecords.add(rec);
            }
            stmt.close();
            conn.close();
        }
        catch (Exception e)
        {
            System.out.println("error");
        }

        return payrollRecords;
    }
}