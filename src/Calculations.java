
/**
  * Mohamed Miraj 
 **/
 
public class Calculations
 {
         // variables 
       private double hours;
       private double wages;
       private double federaltx;
       private double statetx; 
       private double withhold;
       private double social_secu; 
       private double medicare; 
       private double netin;
       private double gross;
       private boolean maritalStatus;
   
    // constructor IS required.
    public Calculations(double hours, double wages, double federaltx, double statetx, double withhold, double social_secu, double medicare, double netin, double gross, boolean maritalStatus) {
        this.hours = hours;
        this.wages = wages;
        this.federaltx = federaltx;
        this.statetx = statetx;
        this.withhold = withhold;
        this.social_secu = social_secu;
        this.medicare = medicare;
        this.netin = netin;
        this.gross = gross;
        this.maritalStatus=maritalStatus;
    }

     public Calculations(double hours, double wages, int withhold) {
         this.hours = hours;
         this.wages = wages;
         this.withhold= withhold;
     }

     // getters
	public double getHours()
	{
		return hours;
	}

   public double getWages()
	{
		return wages;
	}
   
   public double calcGross()
   {
      final double Limit = 40.00;  
      
      if ( hours <= Limit )
      {
         gross = wages * hours;
      }      
      else 
      {
         
         gross = ((1.5*wages)*(hours-Limit))+( wages * Limit );
        
      }

      return gross;
    }
    
    public double calcFederalTX()
    {
        double withholdingAmount = 153.80 * withhold;
        double taxableIncome = gross - withholdingAmount;
        if (!maritalStatus)
        {
            if (taxableIncome <= 88)
            {
                return 0.0;
            }
            else if (taxableIncome <= 443)
            {
                federaltx = ((taxableIncome - 88) * 0.10);
                System.out.println(federaltx);
            }
            else if (taxableIncome <= 1529)
            {
                federaltx = ((taxableIncome - 443) * 0.15) + 35.50;
            }
            else if (taxableIncome <= 3579)
            {
                federaltx = ((taxableIncome - 1529) * 0.25) + 198.40;
            }
            else if (taxableIncome <= 7369)
            {
                federaltx = ((taxableIncome - 3579 * 0.28) + 710.90);
            }
            else if (taxableIncome <= 15915)
            {
                federaltx = ((taxableIncome - 7369) * 0.33) + 1772.10;
            }
            else if (taxableIncome <= 15981)
            {
                federaltx = ((taxableIncome - 15915) * 0.35) + 4592.28;
            }
            else {
                federaltx = ((taxableIncome - 15981) * 0.396) + 4615.38;
            }
        }
        else
        {
            if (taxableIncome <= 331)
            {
                federaltx = 0.0;
            }
            else if (taxableIncome <= 1040)
            {
                federaltx = ((taxableIncome - 331) * 0.10);
            }
            else if (taxableIncome <= 3212)
            {
                federaltx = ((taxableIncome - 1040) * 0.15) + 35.50;
            }
            else if (taxableIncome <= 6146)
            {
                federaltx = ((taxableIncome - 3212) * 0.25) + 198.40;
            }
            else if (taxableIncome <= 9194)
            {
                federaltx = ((taxableIncome - 6146 * 0.28) + 710.90);
            }
            else if (taxableIncome <= 16158)
            {
                federaltx = ((taxableIncome - 9194) * 0.33) + 1772.10;
            }
            else if (taxableIncome <= 18210)
            {
                federaltx = ((taxableIncome - 16158) * 0.35) + 4592.28;
            }
            else {
                federaltx = ((taxableIncome - 18210) * 0.396) + 4615.38;
            }
        }

      return federaltx;
      
     }
     
     public double calcStateTX()
     {
      statetx = (gross - withhold*1.44)*.0375;
      return statetx;
      
     }
     
     public double getWithHold()
     {
      
       return withhold;
     }

     public boolean isMarried(int eid)
     {
         String s = DBConnector.getMarital(eid);
         if(s.equals("Single"))
             return false;
         else
             return true;
     }

     public boolean getMaritalStatus()
     {
         return maritalStatus;
     }
     
     public double calcSocial_secu()
     {
      social_secu = gross * 0.062;
      return social_secu; 
      
     }
    
     
     public double calcMediCare()
     {
         medicare = (gross * 0.0145);
      return medicare;
      
     }
     
     public double calcNetIn()
     {
      netin = (gross - (federaltx + statetx + social_secu + medicare));
      return netin;
      
     }
     
     // setters
	  public void setHours(double hrs)
	  {
		
       hours = hrs;
	  }
	
	  public void setWages(double wgs)
	  {
     
      wages = wgs;
       
	  }
     public void setGross(double hrs, double wgs)
     {
      hours = hrs;
      wages = wgs; 
     }
     public void setFederalTX(double fedtx)
     {
     
      federaltx = fedtx;
      
     }
     public void setStateTX(double sttx)
     {
    
      statetx = sttx;
      
     }
     
     public void setWithHold(double w_hld)
     {
      
      withhold = w_hld; 
     }
     
     public void setSocial_sec(double ss)
     {
      
       social_secu = ss; 
     }
     
     public void setMediCare(double medi)
     {
     
       medicare = medi;
     }
     
     public void setNetIn(double net)
     {
     
      netin = net;
     }

     public void setMaritalStatus(boolean maritalStatus)
     {
         this.maritalStatus = maritalStatus;
     }
}