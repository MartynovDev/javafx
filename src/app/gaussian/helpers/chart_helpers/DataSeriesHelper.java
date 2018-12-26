package app.gaussian.helpers.chart_helpers;

import app.gaussian.common.Environment;
import app.gaussian.helpers.data_helpers.GaussianDistributionHelper;
import javafx.scene.chart.XYChart;

public class DataSeriesHelper {

    /**
     * Create Data Series for Gaussian Distribution
     * @param ea Activation Energy
     * @param d Ion dose
     * @return Data Series
     */
    public static XYChart.Series<Number,Number> getGaussianDistribution(int ea, double d){
        XYChart.Series<Number,Number> dataSeries = new XYChart.Series<>();
        for (double x = 0.0; x<= Environment.get().getDepth(); x+= Environment.get().getDepthStep()){
            dataSeries.getData().add(new XYChart.Data<>(x, GaussianDistributionHelper.getGaussianDistribution(ea, d, x)));
        }
        dataSeries.setName("Ea="+ea+", D="+d);
        return dataSeries;
    }
}
