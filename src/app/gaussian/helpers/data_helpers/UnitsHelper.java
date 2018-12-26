package app.gaussian.helpers.data_helpers;

public class UnitsHelper {
    public static double convertMkmToCm(double mkm){
        return mkm * Math.pow(10, -4);
    }

    public static double convertNmToCm(double nm){
        return nm * Math.pow(10, -7);
    }

    public static double convertNmToMkm(double nm){
        return nm * Math.pow(10, -3);
    }
}
