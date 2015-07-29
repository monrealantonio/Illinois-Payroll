public class CalApp
{

   public static void main(String args[])
   {
      Calculations cal = new Calculations();
      cal.setHours(40.00);
      cal.setWages(25.00);
      cal.setWithHold(0.0375); // 3.75 %
      

      System.out.print("Hours : " + cal.getHours()+ "\n" +
                             "Wage  : "+ cal.getWages()+ "\n" + "Gross: "+ cal.getGross()+ "\n" + 
                             "Federal: "+ cal.getFederalTX()+ "\n" + "State: " + cal.getStateTX()+ "\n" + 
                             "Social: "+ cal.getSocial_secu()+ "\n" + "Medicare: " + cal.getMediCare()+ "\n" +
                             "Net: " + cal.NetIn());
    }
}