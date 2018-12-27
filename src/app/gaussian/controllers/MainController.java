package app.gaussian.controllers;

import app.gaussian.common.Environment;
import app.gaussian.exception_handlers.TextFieldHandler;
import app.gaussian.helpers.chart_helpers.DataSeriesHelper;
import com.sun.xml.internal.ws.server.ServerRtException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;

import java.math.BigDecimal;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.Format;
import java.util.IllegalFormatConversionException;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    private LineChart<Number, Number> lineChart;

    @FXML
    private NumberAxis xAxis;

    @FXML
    private NumberAxis yAxis;

    @FXML
    private TextField implantedDose;

    @FXML
    private Label implantedDoseLabel;

    @FXML
    private ChoiceBox activationEnergy;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initLineChart();
        initAxis();
    }

    private void initLineChart(){
        lineChart.setPrefSize(Environment.get().getChartWight(), Environment.get().getChartHeight());
        lineChart.setTitle(Environment.get().getChartTitle());
    }

    private void initAxis(){
        yAxis.setLabel(Environment.get().getChartYLabel());
        yAxis.setTickLabelFormatter(new StringConverter<Number>() {
            @Override
            public String toString(Number object) {
                return String.valueOf(object.doubleValue());
            }

            @Override
            public Number fromString(String string) {
                return 0;
            }
        });
        xAxis.setLabel(Environment.get().getChartXLabel());
    }

    public void modeling(ActionEvent event){
        double implantedDoseValue;
        try {
            implantedDoseValue = Double.valueOf(implantedDose.getText());
            XYChart.Series<Number,Number> dataSeries = DataSeriesHelper.getGaussianDistribution(Integer.valueOf(activationEnergy.getValue().toString()), implantedDoseValue);

            boolean flag = false;
            if (lineChart.getData().size() != 0){
                for (int i=0; i<lineChart.getData().size(); i++){
                    if (lineChart.getData().get(i).getName().equals(dataSeries.getName())){
                        TextFieldHandler.repeatingDataHandler(dataSeries.getName());
                        flag = true;
                        break;
                    }
                }
            }
            if (!flag){
                lineChart.getData().add(dataSeries);
            }
        } catch (NumberFormatException e){
            TextFieldHandler.numberFormatExceptionHandler(implantedDoseLabel.getText());
        }
    }

    public void clear(ActionEvent event){
        lineChart.getData().clear();
    }
}
