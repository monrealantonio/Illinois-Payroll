/**
 * Created by Stiv on 7/28/2015.
 */
public class Payroll
{
    private int pid, eid;
    private String startDate, endDate;
    private double gross, fit, sit, social, medicare, net, hoursWorked;

    public Payroll(int pid, int eid, String startDate, String endDate, double gross, double fit, double sit, double social, double medicare, double net) {
        this.pid = pid;
        this.eid = eid;
        this.startDate = startDate;
        this.endDate = endDate;
        this.gross = gross;
        this.fit = fit;
        this.sit = sit;
        this.social = social;
        this.medicare = medicare;
        this.net = net;
    }

    public Payroll(int eid, String startDate, String endDate, double gross, double fit, double sit, double social, double medicare, double net) {
        this.eid = eid;
        this.startDate = startDate;
        this.endDate = endDate;
        this.gross = gross;
        this.fit = fit;
        this.sit = sit;
        this.social = social;
        this.medicare = medicare;
        this.net = net;
    }

    public Payroll(int pid, int eid, String startDate, String endDate, double net) {
        this.pid = pid;
        this.eid = eid;
        this.startDate = startDate;
        this.endDate = endDate;
        this.net = net;
    }

    public int getEid() {
        return eid;
    }

    public int getPid() {
        return pid;
    }

    public String getStartDate() {
        return DBConnector.getStartDate(this.pid);
    }

    public String getEndDate() {
        return DBConnector.getEndDate(this.pid);
    }

    public double getGross() {
        return DBConnector.getGross(this.pid);
    }

    public double getFit() {
        return DBConnector.getFIT(this.pid);
    }

    public double getSit() {
        return DBConnector.getSIT(this.pid);
    }

    public double getSocial() {
        return DBConnector.getSocialTax(this.pid);
    }

    public double getMedicare() {
        return DBConnector.getMedicare(this.pid);
    }

    public double getNet() {
        return DBConnector.getNet(this.pid);
    }

    public void newPayroll() {
        DBConnector.addPayroll(this.eid, this.gross, this.fit, this.sit, this.social, this.medicare, this.net, this.startDate, this.endDate, this.hoursWorked);
    }
}
