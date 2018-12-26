package app.gaussian.common;

import java.awt.*;
import java.io.IOException;
import java.util.Properties;

public class Environment {

    private static Environment _instance = null;
    private Properties environmentProperties;
    private String fxmlPath;
    private double windowWight;
    private double windowHeight;
    private String chartTitle;
    private String chartXLabel;
    private String chartYLabel;
    private double chartWight;
    private double chartHeight;

    private double ea100;
    private double ea50;
    private double ea30;

    private double depth;
    private double depthStep;

    private double asMolarMass;
    private double siMolarMass;


    private Environment(){
        String propFileName = "app.properties";
        environmentProperties = new Properties();
        try {
            environmentProperties.load(Environment.class.getClass().getResourceAsStream("/" + propFileName));
        } catch (IOException | NullPointerException ignore) {
            throw new RuntimeException("Failed to init environment by file " + propFileName + ". Failed to load file", ignore);
        }

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        fxmlPath = getProperty("fxml.path");
        windowWight = (Integer.valueOf(getProperty("window.size.percent")) * screenSize.getWidth()) / 100;
        windowHeight = (Integer.valueOf(getProperty("window.size.percent")) * screenSize.getHeight()) / 100;
        chartTitle = getProperty("chart.title");
        chartXLabel = getProperty("chart.x.label");
        chartYLabel = getProperty("chart.y.label");
        chartWight = windowWight;
        chartHeight = windowHeight - (5 * windowHeight / 100);

        ea100 = Double.valueOf(getProperty("ea.100"));
        ea50 = Double.valueOf(getProperty("ea.50"));
        ea30 = Double.valueOf(getProperty("ea.30"));
        depth = Double.valueOf(getProperty("depth"));
        depthStep = Double.valueOf(getProperty("depth.step"));
        asMolarMass = Double.valueOf(getProperty("as.molarMass"));
        siMolarMass = Double.valueOf(getProperty("si.molarMass"));
    }

    public static Environment get() {
        if (_instance == null) {
            _instance = new Environment();
        }

        return _instance;
    }

    public String getProperty(String propertyName) {
        return environmentProperties.getProperty(propertyName, System.getProperty(propertyName));
    }

    public String getFxmlPath() {
        return fxmlPath;
    }

    public double getWindowWight() {
        return windowWight;
    }

    public double getWindowHeight() {
        return windowHeight;
    }

    public String getChartTitle() {
        return chartTitle;
    }

    public String getChartXLabel() {
        return chartXLabel;
    }

    public String getChartYLabel() {
        return chartYLabel;
    }

    public double getChartWight() {
        return chartWight;
    }

    public double getChartHeight() {
        return chartHeight;
    }

    public double getEa100() {
        return ea100;
    }

    public double getEa50() {
        return ea50;
    }

    public double getEa30() {
        return ea30;
    }

    public double getDepth() {
        return depth;
    }

    public double getDepthStep() {
        return depthStep;
    }

    public double getAsMolarMass() {
        return asMolarMass;
    }

    public double getSiMolarMass() {
        return siMolarMass;
    }
}
