package pl.mo.window;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import org.apache.log4j.Logger;
import pl.mo.general.PrimitivesParser;
import pl.mo.general.ReflectionHelper;
import pl.mo.gs.GridSearch;

public class MainWindowController {

    private static final Logger log = Logger.getLogger(MainWindowController.class);

    @FXML private TextField gsLeftArgument;
    @FXML private TextField gsRightArgument;
    @FXML private TextField gsAccuracy;
    @FXML private TextField gsLocalMinimum;
    @FXML private TextField gsNumberOfCalls;
    @FXML private LineChart<Double, Double> gsFunctionChart;
    @FXML private BarChart<Double, Double> gsDependencyChart;

    @FXML
    private void countUserDefinedLocalMinimum() {
        Double leftArgument = PrimitivesParser.getDouble(gsLeftArgument.getText());
        Double rightArgument = PrimitivesParser.getDouble(gsRightArgument.getText());
        Double accuracy = PrimitivesParser.getDouble(gsAccuracy.getText());

        if (leftArgument == null || rightArgument == null || accuracy == null) {
            new Alert(AlertType.ERROR, "Improper value." + System.lineSeparator() + PrimitivesParser.getErrorMessage()).showAndWait();
            return;
        }

        GridSearch gridSearch = new GridSearch();
        Double localMinimum = gridSearch.getLocalMinimumArgument(leftArgument, rightArgument, accuracy);

        if (localMinimum == null) {
            gsLocalMinimum.setText("N/A");
            new Alert(AlertType.ERROR, "The value has not been found.").showAndWait();
            return;
        }

        gsLocalMinimum.setText(localMinimum.toString());
        gsNumberOfCalls.setText(String.valueOf(gridSearch.getNumberOfCalls()));
        log.info(ReflectionHelper.getCurrentMethodName() + "(" + leftArgument + ", " + rightArgument + ", " + accuracy + ") = " + localMinimum);
        populateFunctionChart(leftArgument, rightArgument);
    }

    @FXML
    private void populateFunctionChart(Double left, Double right) {
        GridSearch gridSearch = new GridSearch();
        Series series = new Series();
        final int POINTS_PER_INTERVAL = 11;
        final Double step = (right - left) / POINTS_PER_INTERVAL;

        for (Double i = left; i <= right; i += step) {
            series.getData().add(new Data<>(i, gridSearch.getPolynomialValue(i)));
        }

        gsFunctionChart.getData().clear();
        gsFunctionChart.getData().addAll(series);
        log.info(ReflectionHelper.getCurrentMethodName() + "(" + left + ", " + right + ")");
    }

    private void setFunctionChartDefaultSettings() {
        gsFunctionChart.getXAxis().setAutoRanging(true);
        gsFunctionChart.getYAxis().setAutoRanging(true);
        gsFunctionChart.setLegendVisible(false);
        gsFunctionChart.setTitle("The provided function in a specified interval");
    }

    private void setDependencyChartDefaultSettings() {
        gsDependencyChart.setLegendVisible(true);
        gsDependencyChart.setTitle("The function calls dependency on the accuracy");
    }

    public void setUiControlsDefaultSettings() {
        setFunctionChartDefaultSettings();
        setDependencyChartDefaultSettings();
        populateDependencyChart();
    }

    @FXML
    private void populateDependencyChart() {
        gsDependencyChart.getData().clear();

        for (double n = 0.1; n >= 1E-4; n /= 10) {
            Series series = new Series();
            series.setName(String.valueOf(n));
            GridSearch gridSearch = new GridSearch();
            gridSearch.getLocalMinimumArgument(1.0, 10.0, n);
            series.getData().add(new Data<>("", gridSearch.getNumberOfCalls()));
            gsDependencyChart.getData().add(series);
        }

        log.info(ReflectionHelper.getCurrentMethodName() + "()");
    }

}
