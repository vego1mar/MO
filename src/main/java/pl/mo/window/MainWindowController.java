package pl.mo.window;

import java.util.NoSuchElementException;
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

    @FXML private TextField recgsLeftArgument;
    @FXML private TextField recgsRightArgument;
    @FXML private TextField recgsAccuracy;
    @FXML private TextField recgsIntervalDivisions;
    @FXML private TextField recgsLocalMinimum;
    @FXML private TextField recgsNumberOfCalls;
    @FXML private LineChart<Double, Double> recgsFunctionChart;

    @FXML
    private void countUserDefinedGridSearchLocalMinimum() {
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
        populateFunctionChart(leftArgument, rightArgument, gsFunctionChart);
    }

    @FXML
    private void populateFunctionChart(Double left, Double right, LineChart<Double, Double> lineChart) {
        GridSearch gridSearch = new GridSearch();
        Series series = new Series();
        final int POINTS_PER_INTERVAL = 11;
        final Double step = (right - left) / POINTS_PER_INTERVAL;

        for (Double i = left; i <= right; i += step) {
            series.getData().add(new Data<>(i, gridSearch.getPolynomialValue(i)));
        }

        lineChart.getData().clear();
        lineChart.getData().addAll(series);
        log.info(ReflectionHelper.getCurrentMethodName() + "(" + left + ", " + right + ", " + lineChart.getId() + ")");
    }

    private void setFunctionChartDefaultSettings(LineChart<Double, Double> lineChart) {
        lineChart.getXAxis().setAutoRanging(true);
        lineChart.getYAxis().setAutoRanging(true);
        lineChart.setLegendVisible(false);
        lineChart.setTitle("The provided function in a specified interval");
    }

    private void setDependencyChartDefaultSettings() {
        gsDependencyChart.setLegendVisible(true);
        gsDependencyChart.setTitle("The function calls dependency on the accuracy");
    }

    public void setUiControlsDefaultSettings() {
        setFunctionChartDefaultSettings(gsFunctionChart);
        setFunctionChartDefaultSettings(recgsFunctionChart);
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

    @FXML
    private void countUserDefinedRecursiveGridSearchLocalMinimum() {
        Double leftArgument = PrimitivesParser.getDouble(recgsLeftArgument.getText());
        Double rightArgument = PrimitivesParser.getDouble(recgsRightArgument.getText());
        Double accuracy = PrimitivesParser.getDouble(recgsAccuracy.getText());
        Integer intervalDivisionsNo = PrimitivesParser.getInteger(recgsIntervalDivisions.getText());

        if (leftArgument == null || rightArgument == null || accuracy == null || intervalDivisionsNo == null) {
            new Alert(AlertType.ERROR, "Improper value." + System.lineSeparator() + PrimitivesParser.getErrorMessage()).showAndWait();
            return;
        }

        GridSearch gridSearch = new GridSearch();
        Double localMinimum = null;

        try {
            localMinimum = gridSearch.getLocalMinimumArgument(leftArgument, rightArgument, accuracy, intervalDivisionsNo);
        } catch (NoSuchElementException ex) {
            log.error(ex.toString());
            new Alert(AlertType.ERROR, "Left argument is greater than right argument.").showAndWait();
            return;
        } catch (IllegalArgumentException ex) {
            log.error(ex.toString());
            new Alert(AlertType.ERROR, "The number of interval divisions is lower than or equal to zero.").showAndWait();
            return;
        }

        recgsLocalMinimum.setText(localMinimum.toString());
        recgsNumberOfCalls.setText(String.valueOf(gridSearch.getNumberOfCalls()));
        log.info(ReflectionHelper.getCurrentMethodName() + "(" + leftArgument + ", " + rightArgument + ", " + accuracy + ", " + intervalDivisionsNo + ") = " + localMinimum);
        populateFunctionChart(leftArgument, rightArgument, recgsFunctionChart);
    }

}
