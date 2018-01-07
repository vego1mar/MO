package pl.mo.window;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import pl.mo.algorithms.GoldenSectionSearch;
import pl.mo.algorithms.HookeJeevesPatternSearch;
import pl.mo.algorithms.SimulatedAnnealing;
import pl.mo.functions.Paraboloid;
import pl.mo.functions.Polynomial;
import pl.mo.algorithms.SteepestDescent;
import pl.mo.general.PrimitivesHelper;
import pl.mo.general.ReflectionHelper;
import pl.mo.algorithms.GridSearch;
import pl.mo.strings.MainWindowBundle;

public class MainWindowController {

    private static final Logger log = Logger.getLogger(MainWindowController.class);
    private static final MainWindowBundle bundle = MainWindow.getBundle();

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

    @FXML private TextField gssLeftArgument;
    @FXML private TextField gssRightArgument;
    @FXML private TextField gssAccuracy;
    @FXML private TextField gssLocalMinimum;
    @FXML private TextField gssNumberOfCalls;
    @FXML private LineChart<Double, Double> gssFunctionChart;

    @FXML private TextField sdStartPointX;
    @FXML private TextField sdStartPointY;
    @FXML private CheckBox sdUseBacktracking;
    @FXML private TextField sdResult;
    @FXML private TextField sdResultAccuracy;
    @FXML private TextField sdIterations;
    @FXML private TextField sdFunctionCalls;

    @FXML private TextField hjStartPointX;
    @FXML private TextField hjStartPointY;
    @FXML private TextField hjResult;
    @FXML private TextField hjIterations;
    @FXML private TextField hjFunctionCalls;

    @FXML private TextField saStartPointX;
    @FXML private TextField saStartPointY;
    @FXML private TextField saBoundXLeft;
    @FXML private TextField saBoundXRight;
    @FXML private TextField saBoundYLeft;
    @FXML private TextField saBoundYRight;
    @FXML private TextField saAnnealingThreshold;
    @FXML private TextField saCoolingFactor;
    @FXML private TextField saResult;
    @FXML private TextField saIterations;
    @FXML private TextField saFunctionCalls;

    @FXML
    private void populateFunctionChart(Double left, Double right, LineChart<Double, Double> lineChart, Polynomial scoreFunction) {
        Series series = new Series();
        final Double step = (right - left) / 11;

        for (Double i = left; i <= right; i += step) {
            series.getData().add(new Data<>(i, scoreFunction.getValue(i)));
        }

        lineChart.getData().clear();
        lineChart.getData().addAll(series);
        log.info(ReflectionHelper.getCurrentMethodName() + "(" + left + ", " + right + ", " + lineChart.getId() + ")");
    }

    private void setFunctionChartDefaultSettings(@NotNull LineChart<Double, Double> lineChart) {
        lineChart.getXAxis().setAutoRanging(true);
        lineChart.getYAxis().setAutoRanging(true);
        lineChart.setLegendVisible(false);
        lineChart.setTitle(bundle.getLineChartsTitle());
    }

    private void setDependencyChartDefaultSettings() {
        gsDependencyChart.setLegendVisible(true);
        gsDependencyChart.setTitle(bundle.getDependencyChartTitle());
    }

    public void setUiControlsDefaultSettings() {
        setFunctionChartDefaultSettings(gsFunctionChart);
        setFunctionChartDefaultSettings(recgsFunctionChart);
        setFunctionChartDefaultSettings(gssFunctionChart);
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
            series.getData().add(new Data<>("", gridSearch.getObjectiveFunction().getNumberOfCalls()));
            gsDependencyChart.getData().add(series);
        }

        log.info(ReflectionHelper.getCurrentMethodName() + "()");
    }

    @FXML
    private void countMinimumUsingGridSearch() {
        Double leftArgument = PrimitivesHelper.getDouble(gsLeftArgument.getText());
        Double rightArgument = PrimitivesHelper.getDouble(gsRightArgument.getText());
        Double accuracy = PrimitivesHelper.getDouble(gsAccuracy.getText());

        if (leftArgument == null || rightArgument == null || accuracy == null) {
            new Alert(AlertType.ERROR, bundle.getTextImproperValue() + System.lineSeparator() + PrimitivesHelper.getErrorMessage()).showAndWait();
            return;
        }

        GridSearch gridSearch = new GridSearch();
        Double localMinimum = gridSearch.getLocalMinimumArgument(leftArgument, rightArgument, accuracy);

        if (localMinimum == null) {
            gsLocalMinimum.setText(bundle.getAbbreviationNotApplicable());
            new Alert(AlertType.ERROR, bundle.getTextValueNotFound()).showAndWait();
            return;
        }

        gsLocalMinimum.setText(localMinimum.toString());
        gsNumberOfCalls.setText(String.valueOf(gridSearch.getObjectiveFunction().getNumberOfCalls()));
        log.info(ReflectionHelper.getCurrentMethodName() + "(" + leftArgument + ", " + rightArgument + ", " + accuracy + ") = " + localMinimum);
        populateFunctionChart(leftArgument, rightArgument, gsFunctionChart, (Polynomial) gridSearch.getObjectiveFunction());
    }

    @FXML
    private void countMinimumUsingRecursiveGridSearch() {
        Double leftArgument = PrimitivesHelper.getDouble(recgsLeftArgument.getText());
        Double rightArgument = PrimitivesHelper.getDouble(recgsRightArgument.getText());
        Double accuracy = PrimitivesHelper.getDouble(recgsAccuracy.getText());
        Integer intervalDivisionsNo = PrimitivesHelper.getInteger(recgsIntervalDivisions.getText());

        if (leftArgument == null || rightArgument == null || accuracy == null || intervalDivisionsNo == null) {
            new Alert(AlertType.ERROR, bundle.getTextImproperValue() + System.lineSeparator() + PrimitivesHelper.getErrorMessage()).showAndWait();
            return;
        }

        GridSearch gridSearch = new GridSearch();
        Double localMinimum;

        try {
            localMinimum = gridSearch.getLocalMinimumArgument(leftArgument, rightArgument, accuracy, intervalDivisionsNo);
        } catch (NoSuchElementException ex) {
            log.error(ex.toString());
            new Alert(AlertType.ERROR, bundle.getErrorIntervalArgumentsMismatch()).showAndWait();
            return;
        } catch (IllegalArgumentException ex) {
            log.error(ex.toString());
            new Alert(AlertType.ERROR, bundle.getErrorNegativeIntervalDivisionsNo()).showAndWait();
            return;
        }

        recgsLocalMinimum.setText(localMinimum.toString());
        recgsNumberOfCalls.setText(String.valueOf(gridSearch.getObjectiveFunction().getNumberOfCalls()));
        log.info(ReflectionHelper.getCurrentMethodName() + "(" + leftArgument + ", " + rightArgument + ", " + accuracy + ", " + intervalDivisionsNo + ") = " + localMinimum);
        populateFunctionChart(leftArgument, rightArgument, recgsFunctionChart, (Polynomial) gridSearch.getObjectiveFunction());
    }

    @FXML
    private void countMinimumUsingGoldenSectionSearch() {
        Double leftArgument = PrimitivesHelper.getDouble(gssLeftArgument.getText());
        Double rightArgument = PrimitivesHelper.getDouble(gssRightArgument.getText());
        Double accuracy = PrimitivesHelper.getDouble(gssAccuracy.getText());

        if (leftArgument == null || rightArgument == null || accuracy == null) {
            new Alert(AlertType.ERROR, bundle.getTextImproperValue() + System.lineSeparator() + PrimitivesHelper.getErrorMessage()).showAndWait();
            return;
        }

        GoldenSectionSearch gss = new GoldenSectionSearch();
        Double localMinimum;

        try {
            localMinimum = gss.getLocalMinimumArgument(leftArgument, rightArgument, accuracy);
        } catch (IllegalArgumentException ex) {
            log.error(ex.toString());
            new Alert(AlertType.ERROR, bundle.getErrorIntervalArgumentsMismatch()).showAndWait();
            return;
        }

        gssLocalMinimum.setText(localMinimum.toString());
        gssNumberOfCalls.setText(String.valueOf(gss.getObjectiveFunction().getNumberOfCalls()));
        log.info(ReflectionHelper.getCurrentMethodName() + "(" + leftArgument + ", " + rightArgument + ", " + accuracy + ") = " + localMinimum);
        populateFunctionChart(leftArgument, rightArgument, gssFunctionChart, (Polynomial) gss.getObjectiveFunction());
    }

    @FXML
    private void countMinimumUsingSteepestDescent() {
        Double startPointX = PrimitivesHelper.getDouble(sdStartPointX.getText());
        Double startPointY = PrimitivesHelper.getDouble(sdStartPointY.getText());

        if (startPointX == null || startPointY == null) {
            new Alert(AlertType.ERROR, bundle.getTextImproperValue() + System.lineSeparator() + PrimitivesHelper.getErrorMessage()).showAndWait();
            return;
        }

        List<Double> startPoint = Arrays.asList(startPointX, startPointY);
        boolean isBacktracked = sdUseBacktracking.isSelected();

        SteepestDescent sd = new SteepestDescent();
        sd.setObjectiveFunction(new Paraboloid());
        List<Double> localMinimum = sd.getLocalMinimumArgument(startPoint, !isBacktracked);
        sdResult.setText(String.valueOf(localMinimum));
        sdResultAccuracy.setText(String.valueOf(SteepestDescent.MUTABLE_GRADIENT_CONVERGENCE));

        if (!isBacktracked) {
            sdResultAccuracy.setText(String.valueOf(SteepestDescent.CONSTANT_GRADIENT_CONVERGENCE));
        }

        sdIterations.setText(String.valueOf(sd.getIterationsNo()));
        sdFunctionCalls.setText(String.valueOf(sd.getObjectiveFunction().getNumberOfCalls()));
        log.info(ReflectionHelper.getCurrentMethodName() + "(" + startPoint + ", backtracking = " + isBacktracked + ") = " + localMinimum);
    }

    @FXML
    private void countMinimumUsingHookeJeevesPatternSearch() {
        Double startPointX = PrimitivesHelper.getDouble(hjStartPointX.getText());
        Double startPointY = PrimitivesHelper.getDouble(hjStartPointY.getText());

        if (startPointX == null || startPointY == null) {
            new Alert(AlertType.ERROR, bundle.getTextImproperValue() + System.lineSeparator() + PrimitivesHelper.getErrorMessage()).showAndWait();
            return;
        }

        List<Double> startPoint = Arrays.asList(startPointX, startPointY);
        HookeJeevesPatternSearch hj = new HookeJeevesPatternSearch();
        hj.setObjectiveFunction(new Paraboloid());
        List<Double> localMinimum;

        try {
            localMinimum = hj.getLocalMinimumArgument(startPoint);
        } catch (IllegalArgumentException | NullPointerException ex) {
            log.error(ex.toString());
            new Alert(AlertType.ERROR, ex.getMessage()).showAndWait();
            return;
        }

        hjResult.setText(String.valueOf(localMinimum));
        hjIterations.setText(String.valueOf(hj.getIterationsNo()));
        hjFunctionCalls.setText(String.valueOf(hj.getObjectiveFunction().getNumberOfCalls()));
        log.info(ReflectionHelper.getCurrentMethodName() + "(" + startPoint + ") = " + localMinimum);
    }

    @FXML
    private void countMinimumUsingSimulatedAnnealing() {
        Double startPointX = PrimitivesHelper.getDouble(saStartPointX.getText());
        Double startPointY = PrimitivesHelper.getDouble(saStartPointY.getText());
        Double xLeftBound = PrimitivesHelper.getDouble(saBoundXLeft.getText());
        Double xRightBound = PrimitivesHelper.getDouble(saBoundXRight.getText());
        Double yLeftBound = PrimitivesHelper.getDouble(saBoundYLeft.getText());
        Double yRightBound = PrimitivesHelper.getDouble(saBoundYRight.getText());
        Integer threshold = PrimitivesHelper.getInteger(saAnnealingThreshold.getText());
        Double coolingFactor = PrimitivesHelper.getDouble(saCoolingFactor.getText());
        Alert alert = new Alert(AlertType.ERROR, bundle.getTextImproperValue() + System.lineSeparator() + PrimitivesHelper.getErrorMessage());

        if (startPointX == null || startPointY == null) {
            alert.showAndWait();
            return;
        }

        if (xLeftBound == null || xRightBound == null || yLeftBound == null || yRightBound == null) {
            alert.showAndWait();
            return;
        }

        if (threshold == null || coolingFactor == null) {
            alert.showAndWait();
            return;
        }

        List<Double> startPoint = List.of(startPointX, startPointY);
        SimulatedAnnealing annealing = new SimulatedAnnealing();
        List<Double> minimum;

        try {
            annealing.setObjectiveFunction(new Paraboloid());
            annealing.setAnnealingThreshold(threshold);
            annealing.setTemperatureCoolingFactor(coolingFactor);
            SimulatedAnnealing.IntervalConstraints bounds = annealing.new IntervalConstraints();
            bounds.setX(xLeftBound, xRightBound);
            bounds.setY(yLeftBound, yRightBound);
            minimum = annealing.getLocalMinimumArgument(startPoint, bounds);
        } catch (IllegalArgumentException | NullPointerException ex) {
            log.error(ex.toString());
            new Alert(AlertType.ERROR, ex.getMessage()).showAndWait();
            return;
        }

        saResult.setText(String.valueOf(minimum));
        saIterations.setText(String.valueOf(annealing.getIterationsNo()));
        saFunctionCalls.setText(String.valueOf(annealing.getObjectiveFunction().getNumberOfCalls()));
        String boundsString = "[(" + xLeftBound + ", " + xRightBound + "), (" + yLeftBound + ", " + yRightBound + ")]";
        log.info(ReflectionHelper.getCurrentMethodName() + "(SP=" + startPoint + ", B=" + boundsString + ", L=" + threshold + ", CF=" + coolingFactor + ") = " + minimum);
    }

}
