public class Utils {

  
    public static double scaleCIA(double cia1, double cia2, double cia3, int credits) {
        double scaledCIA = 0;

        if (credits == 4) {
            
            double totalCIA = cia1 + cia2 + cia3;
            scaledCIA = (totalCIA / 90) * 30;  
        } else if (credits == 3) {
            
            scaledCIA = 45;  
        } else if (credits == 2) {
            
            scaledCIA = 20;  
        }

        return scaledCIA;
    }

    
    public static double scaleLab(double midSemLab, double endSemLab, int credits) {
        double scaledLab = 0;

        if (credits == 4) {
           
            double totalLab = midSemLab + endSemLab;
            scaledLab = (totalLab / 100) * 35;  
        } else if (credits == 2) {
            
            scaledLab = 0; 
        }

        return scaledLab;
    }

   
    public static double calculateEndSemMarks(double cia1, double cia2, double cia3, double midSemLab, double endSemLab, int attendance, int credits, double targetScore) {
        
        double scaledCIA = scaleCIA(cia1, cia2, cia3, credits);
        double scaledLab = scaleLab(midSemLab, endSemLab, credits);

        
        double totalCondensed = scaledCIA + scaledLab + attendance;

        
        double requiredEndSem = targetScore - totalCondensed;

        
       

        return requiredEndSem;
    }
}
