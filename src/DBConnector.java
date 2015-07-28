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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

*/
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
            ResultSet rs = stmt.executeQuery("SELECT E_id FROM innodb.employee");
            int rownum = 1;
            while(rs.next())
            {
                rownum++;
            }
            System.out.println(rownum);
            String sqlStatement = "INSERT INTO innodb.employee VALUES (" + rownum + ", '" + fname + "', '" + lname + "', '" + bdate + "', '" + address + "', '" + phone + "', '" + marital + "', " + withholding + ", " + wage + ", '" + ssn + "')";
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
            rs = stmt.executeQuery("SELECT E_id, E_fname, E_lname FROM innodb.employee ORDER BY E_id");
            return rs;
        }
        catch (Exception e)
        {
            System.out.println("error");
        }
        return rs;
    }
    public static String getFname(int eid)
    {
                String fname="";
        try
        {
            conn = DBConnect.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT E_fname FROM innodb.employee WHERE E_id = " + eid);
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
    public static String getLname(int eid)//
    {
        String lname = "";
        try
        {
            conn = DBConnect.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT E_lname FROM innodb.employee WHERE E_id = " + eid);
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
    public static String getBdate(int eid)//
    {
        String bdate = "";
        try
        {
            conn = DBConnect.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT E_birthdate FROM innodb.employee WHERE E_id = " + eid);
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
    public static String getAddress(int eid)//
    {
        String address = "";
        try
        {
            conn = DBConnect.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT E_address FROM innodb.employee WHERE E_id = " + eid);
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

    public static String getPhone(int eid)//
    {
        String phone = "";
        try
        {
            conn = DBConnect.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT E_phone FROM innodb.employee WHERE E_id = " + eid);
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
    public static String getMarital(int eid)//
    {

        String marital = "";
        try
        {
            conn = DBConnect.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT E_marital_status FROM innodb.employee WHERE E_id = " + eid);
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
    public static int getWithholding(int eid)//
    {
        int withhold = 0;
        try
        {
            conn = DBConnect.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT E_withholding FROM innodb.employee WHERE E_id = " + eid);
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
    public static String getSSN(int eid)//
    {
        String ssn = "";
        try
        {
            conn = DBConnect.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT E_ssn FROM innodb.employee WHERE E_id = " + eid);
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

    public static double getWage(int eid)//
    {
        double wage = 0.0;//
        try
        {
            conn = DBConnect.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT E_wage_rate FROM innodb.employee WHERE E_id = " + eid);
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
            stmt.executeUpdate("UPDATE innodb.employee SET E_wage_rate = " + wage + " WHERE E_id = " + eid);
            stmt.close();
            conn.close();
        }
        catch (Exception e)
        {
            System.out.println("error");
        }
    }

    public static int getPayrollID(int eid)
    {
        int pid = 0;
        try
        {
            conn = DBConnect.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT P_id FROM innodb.payroll WHERE E_id = " + eid);
            while(rs.next())
            {
                pid = rs.getInt("pid");
            }
            stmt.close();
            conn.close();
        }
        catch (Exception e)
        {
            System.out.println("error");
        }
        return pid;
    }

    public static void setPayrollID(int eid, int pid)
    {
        try
        {
            conn = DBConnect.getConnection();
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("UPDATE innodb.payroll SET P_id = " + pid + " WHERE E_id = " + eid);
            stmt.close();
            conn.close();
        }
        catch (Exception e)
        {
            System.out.println("error");
        }
    }

    public static double getGross(int pid)
    {
        double gross = 0.0;
        try
        {
            conn = DBConnect.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT P_gross FROM innodb.payroll WHERE P_id = " + pid);
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

    public static void setGross(int pid, double gross)
    {
        try
        {
            conn = DBConnect.getConnection();
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("UPDATE innodb.payroll SET P_gross = " + gross + " WHERE P_id = " + pid);
            stmt.close();
            conn.close();
        }
        catch (Exception e)
        {
            System.out.println("error");
        }
    }

    public static double getFIT(int pid)
    {
        double FIT = 0.0;
        try
        {
            conn = DBConnect.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT P_FIT FROM innodb.payroll WHERE P_id = " + pid);
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

    public static void setFIT(int pid, double FIT)
    {
        try
        {
            conn = DBConnect.getConnection();
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("UPDATE innodb.payroll SET P_FIT = " + FIT + " WHERE P_id = " + pid);
            stmt.close();
            conn.close();
        }
        catch (Exception e)
        {
            System.out.println("error");
        }
    }

    public static double getSIT(int pid)
    {
        double SIT = 0.0;
        try
        {
            conn = DBConnect.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT P_SIT FROM innodb.payroll WHERE P_id = " + pid);
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

    public static void setSIT(int pid, double SIT)
    {
        try
        {
            conn = DBConnect.getConnection();
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("UPDATE innodb.payroll SET P_SIT = " + SIT + " WHERE P_id = " + pid);
            stmt.close();
            conn.close();
        }
        catch (Exception e)
        {
            System.out.println("error");
        }
    }

    public static double getSocialTax(int pid)
    {
        double SST = 0.0;
        try
        {
            conn = DBConnect.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT P_social_security FROM innodb.payroll WHERE P_id = " + pid);
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

    public static void setSocialTax(int pid, double SST)
    {
        try
        {
            conn = DBConnect.getConnection();
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("UPDATE innodb.payroll SET P_social_security = " + SST + " WHERE P_id = " + pid);
            stmt.close();
            conn.close();
        }
        catch (Exception e)
        {
            System.out.println("error");
        }
    }

    public static double getMedicare(int pid)
    {
        double medicare = 0.0;
        try
        {
            conn = DBConnect.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT P_medicare FROM innodb.payroll WHERE P_id = " + pid);
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

    public static void setMedicare(int pid, double medicare)
    {
        try
        {
            conn = DBConnect.getConnection();
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("UPDATE innodb.payroll SET P_medicare = " + medicare + " WHERE P_id = " + pid);
            stmt.close();
            conn.close();
        }
        catch (Exception e)
        {
            System.out.println("error");
        }
    }

    public static double getNet(int pid)
    {
        double net = 0.0;
        try
        {
            conn = DBConnect.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT P_net FROM innodb.payroll WHERE P_id = " + pid);
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

    public static void setNet(int pid, double net)
    {
        try
        {
            conn = DBConnect.getConnection();
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("UPDATE innodb.payroll SET P_net = " + net + " WHERE P_id = " + pid);
            stmt.close();
            conn.close();
        }
        catch (Exception e)
        {
            System.out.println("error");
        }
    }

    public static String getStartDate(int pid)
    {
        String startDate = "";
        try
        {
            conn = DBConnect.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT P_start_date FROM innodb.payroll WHERE P_id = " + pid);
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

    public static void setStartDate(int pid, String startDate)
    {
        try
        {
            conn = DBConnect.getConnection();
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("UPDATE innodb.payroll SET P_start_date = '" + startDate + "' WHERE P_id = " + pid);
            stmt.close();
            conn.close();
        }
        catch (Exception e)
        {
            System.out.println("error");
        }
    }

    public static String getEndDate(int pid)
    {
        String endDate = "";
        try
        {
            conn = DBConnect.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT P_end_date FROM innodb.payroll WHERE P_id = " + pid);
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

    public static void setEndDate(int pid, String endDate)
    {
        try
        {
            conn = DBConnect.getConnection();
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("UPDATE innodb.payroll SET P_end_date = '" + endDate + "' WHERE P_id = " + pid);
            stmt.close();
            conn.close();
        }
        catch (Exception e)
        {
            System.out.println("error");
        }
    }

    public static void addPayroll(int eid, double gross, double fit, double sit, double social, double medicare, double net, String startDate, String endDate)
    {
        try
        {
            conn = DBConnect.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT P_id FROM innodb.payroll");
            int rownum = 1;
            while(rs.next())
            {
                rownum++;
            }
            System.out.println(rownum);
            String sqlStatement = "INSERT INTO innodb.payroll VALUES (" + rownum + ", " + eid + ", " + gross + ", " + fit + ", " + sit + ", " + social + ", " + medicare + ", " + net + ", '" + startDate + "', '" + endDate + "')";
            stmt.executeUpdate(sqlStatement);
            stmt.close();
            conn.close();
        }
        catch (Exception e)
        {
            System.out.println("Insert method error");
        }
    }

    public static ResultSet listPayroll(int eid)
    {
        ResultSet rs = null;
        try
        {
            conn = DBConnect.getConnection();
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT P_id, P_start_date, P_end_date FROM innodb.employee ORDER BY P_id");
            return rs;
        }
        catch (Exception e)
        {
            System.out.println("error");
        }
        return rs;
    }

}