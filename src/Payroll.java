/**
 * Created by Stiv on 7/28/2015.
 */
public class Payroll
{
    private int pid, eid;
    private String startDate, endDate;
    private double gross, fit, sit, social, medicare, net;

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

    public int getPid() {
        return DBConnector.getPayrollID(this.eid);
    }

    public void setPid(int pid) {
        this.pid = pid;
        DBConnector.setPayrollID(this.eid, this.pid);
    }

    public String getStartDate() {
        return DBConnector.getStartDate(this.pid);
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
        DBConnector.setStartDate(this.pid, this.startDate);
    }

    public String getEndDate() {
        return DBConnector.getEndDate(this.pid);
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
        DBConnector.setEndDate(this.pid, this.endDate);
    }

    public double getGross() {
        return DBConnector.getGross(this.pid);
    }

    public void setGross(double gross) {
        this.gross = gross;
        DBConnector.setGross(this.pid, this.gross);
    }

    public double getFit() {
        return DBConnector.getFIT(this.pid);
    }

    public void setFit(double fit) {
        this.fit = fit;
        DBConnector.setFIT(this.pid, this.fit);
    }

    public double getSit() {
        return DBConnector.getSIT(this.pid);
    }

    public void setSit(double sit) {
        this.sit = sit;
        DBConnector.setSIT(this.pid, this.sit);
    }

    public double getSocial() {
        return DBConnector.getSocialTax(this.pid);
    }

    public void setSocial(double social) {
        this.social = social;
        DBConnector.setSocialTax(this.pid, this.social);
    }

    public double getMedicare() {
        return DBConnector.getMedicare(this.pid);
    }

    public void setMedicare(double medicare) {
        this.medicare = medicare;
        DBConnector.setMedicare(this.pid, this.medicare);
    }

    public double getNet() {
        return DBConnector.getNet(this.pid);
    }

    public void setNet(double net) {
        this.net = net;
        DBConnector.setNet(this.pid, this.net);
    }
}
