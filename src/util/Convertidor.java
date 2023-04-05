package util;

public class Convertidor {
 
    public static double inchToPix(double inch){
        return inch * 72;
    }
    
    public static int cmToPix(double cm){
        Double pix = inchToPix(cm / 2.54);
        return pix.intValue();
    }
    
}