package app.gaussian.helpers.data_helpers;

import app.gaussian.common.Environment;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class GaussianDistributionHelper {

    private static final double R_EA_100 = UnitsHelper.convertNmToMkm(Environment.get().getEa100());
    private static final double R_EA_50 = UnitsHelper.convertNmToMkm(Environment.get().getEa50());
    private static final double R_EA_30 = UnitsHelper.convertNmToMkm(Environment.get().getEa30());

    /**
     * Molar mass for implanted ion As
     */
    private static final double MOLAR_MASS_1 = Environment.get().getAsMolarMass();

    /**
     * Molar mass for bombed atom Si
     */
    private static final double MOLAR_MASS_2 = Environment.get().getSiMolarMass();

    public static double getGaussianDistribution(int ea, double d, double x){
        double rp = getRpByEa(ea);
        double deltaRp = getDeltaRpByRp(rp);
        return (d / (Math.sqrt(2*Math.PI) * deltaRp)) * Math.exp(-Math.pow(x-rp, 2) / (2 * Math.pow(deltaRp, 2))) / getNMax(ea, d);
    }

    public static double getNMax(int ea, double d){
        double rp = getRpByEa(ea);
        double deltaRp = getDeltaRpByRp(rp);
        return (d / (Math.sqrt(2*Math.PI) * deltaRp));
    }

    private static double getDeltaRpByRp(double rp){
        return Math.sqrt(2.0 / 3.0 * ((MOLAR_MASS_1 * MOLAR_MASS_2) / (MOLAR_MASS_1 + MOLAR_MASS_2))) * rp;
    }

    /**
     * Get Rp by known activation energy
     * @param ea Activation Energy
     * @return Rp
     */
    private static double getRpByEa(int ea){
        switch (ea){
            case 100:
                return R_EA_100;
            case 50:
                return R_EA_50;
            case 30:
                return R_EA_30;
                default:
                    throw new NotImplementedException();
        }
    }

}
