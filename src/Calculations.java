
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
   
    // constructor is NOT required.     
       /*
   public Calculations(double hrs, double wgs, double fedtx, double sttx, double w_hld, double ss, double medi, double net )
   {
   
      hours = hrs;
      wages = wgs;
      federaltx = fedtx;
      statetx = sttx;
      withhold = w_hld;
      social_secu = ss; 
      medicare = medi;
      netin = net; 
      
   
   }*/

    // getters
	public double getHours()
	{
		return hours;
	}

   public double getWages()
	{
		return wages;
	}
   
   public double getGross()
   {
      
      double gross;

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
    
    public double getFederalTX()
     {
      
      double gross;

      final double Limit = 40.00;  
      
      if ( hours <= Limit )
      {
         gross = wages * hours;
      }      
      else 
      {
         
         gross = ((1.5*wages)*(hours-Limit))+( wages * Limit );
        
      }
      
      double federaltx = (gross * withhold);   
      //double federaltx = (gross - (withhold * 153.80));
      return federaltx;
      
     }
     
     public double getStateTX()
     {
      
      double gross;

      final double Limit = 40.00;  
      
      if ( hours <= Limit )
      {
         gross = wages * hours;
      }      
      else 
      {
         
         gross = ((1.5*wages)*(hours-Limit))+( wages * Limit );
        
      }

      double statetx = (gross - (withhold * 1.44));
      return statetx;
      
     }
     
     public double getWithHold()
     {
      
       return withhold;
     }
     
     public double getSocial_secu()
     {
     
      double gross;

      final double Limit = 40.00;  
      
      if ( hours <= Limit )
      {
         gross = wages * hours;
      }      
      else 
      {
         
         gross = ((1.5*wages)*(hours-Limit))+( wages * Limit );
        
      }

      
      double social_secu = gross * 0.062;
      return social_secu; 
      
     }
    
     
     public double getMediCare()
     {
     
      double gross;

      final double Limit = 40.00;  
      
      if ( hours <= Limit )
      {
         gross = wages * hours;
      }      
      else 
      {
         
         gross = ((1.5*wages)*(hours-Limit))+( wages * Limit );
        
      }

     
      double medicare = (gross * 0.0145);
      return medicare;
      
     }
     
     public double NetIn()
     {
     
      double gross;

      final double Limit = 40.00;  
      
      if ( hours <= Limit )
      {
         gross = wages * hours;
      }      
      else 
      {
         
         gross = ((1.5*wages)*(hours-Limit))+( wages * Limit );
        
      }
      
         double federaltx = (gross - (withhold * 153.80));
         double statetx = (gross - (withhold * 1.44));
         double social_secu = gross * 0.062;
         double medicare = (gross * 0.0145);

      double netin = (gross - (federaltx + statetx + social_secu + medicare));
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
          
     

}