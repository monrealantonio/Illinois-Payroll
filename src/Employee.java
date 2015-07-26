public class Employee {
    private int idNum, withholdingAmt;
    private String firstName, lastName, maritalStatus, date, ssn;
    private double wageRate;

    public Employee(int idNum, int withholdingAmt, String firstName, String lastName,
                    String maritalStatus, String date, String ssn, double wageRate) {
        this.idNum = idNum;
        this.withholdingAmt = withholdingAmt;
        this.firstName = firstName;
        this.lastName = lastName;
        this.maritalStatus = maritalStatus;
        this.date = date;
        this.ssn = ssn;
        this.wageRate = wageRate;
    }

    public int getIdNum() {
        return idNum;
    }

    public void setIdNum(int idNum) {
        this.idNum = idNum;
    }

    public int getWithholdingAmt() {
        return withholdingAmt;
    }

    public void setWithholdingAmt(int withholdingAmt) {
        this.withholdingAmt = withholdingAmt;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public double getWageRate() {
        return wageRate;
    }

    public void setWageRate(double wageRate) {
        this.wageRate = wageRate;
    }
}
