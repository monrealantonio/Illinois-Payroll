public class PayrollCalculator
{   
   public PayrollCalculator(double hours, double wage, int employeeID) 
   {
      double grossIncome = hours * wage;
   }
   
   public static overtimeWage(double hours, double wage)
   {
      if (hours > 40)
      {
         return (hours - 40) * (wage * 1.5); 
      }
      return 0;
   }
   public static double fedWithholding(double grossIncome, int withholding)
   {
      int withholdingAmount = 153.80 * withholding;
      int taxableIncome = grossIncome - withholdingAmount;
      if (maritalStatus == "single")
      {
         if (taxableIncome <= 88)
         {
            return 0.0;
         }
         if (taxableIncome <= 443)
         {
            return ((taxableIncome - 88) * 0.10);
         }
         if (taxableIncome <= 1529)
         {
            return ((taxableIncome - 443) * 0.15) + 35.50;
         }
         if (taxableIncome <= 3579)
         {
            return ((taxableIncome - 1529) * 0.25) + 198.40;
         }
         if (taxableIncome <= 7369)
         {
            return ((taxableIncome - 3579 * 0.28) + 710.90;
         }
         if (taxableIncome <= 15915)
         {
            return ((taxableIncome - 7369) * 0.33) + 1772.10;
         }
         if (taxableIncome <= 15981)
         {
            return ((taxableIncome - 15915) * 0.35) + 4592.28;
         }
         return ((taxableIncome - 15981) * 0.396) + 4615.38;
      }
      if (maritalStatus == "married")
      {
         if (taxableIncome <= 331)
         {
            return 0.0;
         }
         if (taxableIncome <= 1040)
         {
            return ((taxableIncome - 331) * 0.10);
         }
         if (taxableIncome <= 3212)
         {
            return ((taxableIncome - 1040) * 0.15) + 35.50;
         }
         if (taxableIncome <= 6146)
         {
            return ((taxableIncome - 3212) * 0.25) + 198.40;
         }
         if (taxableIncome <= 9194)
         {
            return ((taxableIncome - 6146 * 0.28) + 710.90;
         }
         if (taxableIncome <= 16158)
         {
            return ((taxableIncome - 9194) * 0.33) + 1772.10;
         }
         if (taxableIncome <= 18210)
         {
            return ((taxableIncome - 16158) * 0.35) + 4592.28;
         }
         return ((taxableIncome - 18210) * 0.396) + 4615.38;
      }
   }
   
   public static double stateWithholding(double grossIncome, int withholding)
   {
      return (((grossIncome - (withholding * 1.44)) * 0.0375);
   }
   
   public static double getSocial(double grossIncome)
   {
      if (grossIncome < 118500)
      {
         return grossIncome * 0.062;
      }
      return 7347;
   }
   
   public static double getMedicare(double grossIncome)
   {
      if (grossIncome < 200000)
      {
         return grossIncome * 0.0145; 
      }
      return grossIncome * 0.0235;
   }
   
   public static double getNet(double grossIncome)
   {
      return grossIncome - fedWithholding(grossIncome) - stateWitholding(grossIncome) - getSocial(grossIncome) - getMedicare(grossIncome);
   }
}