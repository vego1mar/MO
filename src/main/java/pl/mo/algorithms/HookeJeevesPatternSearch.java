package pl.mo.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import pl.mo.functions.Himmelblau;
import pl.mo.functions.ObjectiveFunction;
import pl.mo.functions.Polynomial;
import pl.mo.general.Vectors;
import pl.mo.strings.HookeJeevesPatternSearchBundle;

public strictfp class HookeJeevesPatternSearch extends LocalMinimumSearchAlgorithm {

    private static final double INCREMENT_VECTOR_VALUE = 0.5; // α > 1
    private static final double STEP_REDUCTION_FACTOR = 2.0;
    private static final double TERMINATION_PARAMETER = 0.001;
    private final HookeJeevesPatternSearchBundle bundle = new HookeJeevesPatternSearchBundle();
    private AlgorithmVariables variables = new AlgorithmVariables();
    private AlgorithmStep nextStep = AlgorithmStep.TERMINATION;

    public HookeJeevesPatternSearch() {
        objectiveFunction = new Himmelblau();
    }

    @Override
    public void setObjectiveFunction(ObjectiveFunction objectiveFunction) {
        if (objectiveFunction instanceof Polynomial) {
            return;
        }

        super.setObjectiveFunction(objectiveFunction);
    }

    public List<Double> getLocalMinimumArgument(List<Double> startPoint) {
        if (startPoint == null || startPoint.isEmpty()) {
            startPoint = new ArrayList<>(Arrays.asList(0.0, 0.0));
        }

        return getLocalMinimumArgument(startPoint, getIncrementVector(startPoint.size()));
    }

    public long getIterationsNo() {
        return variables.iterationsNo;
    }

    private List<Double> getIncrementVector(int size) {
        List<Double> incrementVector = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            incrementVector.add(INCREMENT_VECTOR_VALUE);
        }

        return incrementVector;
    }

    private List<Double> getLocalMinimumArgument(@NotNull List<Double> startPoint, @NotNull List<Double> incrementVector) {
        if (startPoint.size() != incrementVector.size()) {
            throw new IllegalArgumentException(bundle.getWarningArgumentVectorsDoesNotMatch());
        }

        if (startPoint.size() != 2) {
            throw new IllegalArgumentException(bundle.getWarningVectorIsNot2Dimensional());
        }

        variables.startPoint = startPoint;
        variables.incrementVector = incrementVector;
        performStep(AlgorithmStep.PREPARATIONS);

        while (nextStep != AlgorithmStep.TERMINATION) {
            performStep(nextStep);
        }

        return variables.currentPoint;
    }

    private void performStep(@NotNull AlgorithmStep step) {
        switch (step) {
            case PREPARATIONS:
                performStepPreparations();
                break;
            case EXPLORATORY_MOVE_1:
                performStepExploratoryMove1();
                break;
            case CONVERGENCE_CHECK:
                performStepConvergenceCheck();
                break;
            case PATTERN_MOVE:
                performStepPatternMove();
                break;
            case EXPLORATORY_MOVE_2:
                performStepExploratoryMove2();
                break;
            case DIRECT_SEARCH_CHECK:
                performStepDirectSearchCheck();
                break;
            case TERMINATION:
        }
    }

    private void performStepPreparations() {
        variables.iterationsNo = 0;
        variables.currentPoint = new ArrayList<>(variables.startPoint);
        variables.nextPoint = new ArrayList<>();
        variables.previousPoint = new ArrayList<>();
        nextStep = AlgorithmStep.EXPLORATORY_MOVE_1;
    }

    private void performStepExploratoryMove1() {
        List<Double> result = performExploratoryMove(variables.currentPoint);

        if (variables.isExploratoryMoveSuccessful) {
            variables.nextPoint = result;
            nextStep = AlgorithmStep.PATTERN_MOVE;
            return;
        }

        nextStep = AlgorithmStep.CONVERGENCE_CHECK;
    }

    private void performStepConvergenceCheck() {
        if (Vectors.getNorm(variables.incrementVector, 2) < TERMINATION_PARAMETER) {
            nextStep = AlgorithmStep.TERMINATION;
            return;
        }

        variables.incrementVector = Vectors.multiplyByScalar(variables.incrementVector, 1.0 / STEP_REDUCTION_FACTOR);
        nextStep = AlgorithmStep.EXPLORATORY_MOVE_1;
    }

    private void performStepPatternMove() {
        variables.iterationsNo++;
        variables.previousPoint = variables.currentPoint;
        variables.currentPoint = variables.nextPoint;
        variables.nextPoint = performPatternMove(variables.currentPoint);
        nextStep = AlgorithmStep.EXPLORATORY_MOVE_2;
    }

    private void performStepExploratoryMove2() {
        variables.nextPoint = performExploratoryMove(variables.nextPoint);
        nextStep = AlgorithmStep.DIRECT_SEARCH_CHECK;
    }

    private void performStepDirectSearchCheck() {
        Number lhs = objectiveFunction.getValue(variables.nextPoint.get(0), variables.nextPoint.get(1));
        Number rhs = objectiveFunction.getValue(variables.currentPoint.get(0), variables.currentPoint.get(1));

        if (lhs.doubleValue() < rhs.doubleValue()) {
            nextStep = AlgorithmStep.PATTERN_MOVE;
            return;
        }

        nextStep = AlgorithmStep.CONVERGENCE_CHECK;
    }

    private List<Double> performExploratoryMove(@NotNull List<Double> basePoint) {
        ExploratoryMoveMinimum minimum = new ExploratoryMoveMinimum();
        minimum.argument = basePoint;

        for (int i = 0; i < basePoint.size(); i++) {
            minimum = getMinimumForDimension(minimum.argument, i);
        }

        variables.isExploratoryMoveSuccessful = !Vectors.isAs(minimum.argument, basePoint);
        return minimum.argument;
    }

    private List<Double> performPatternMove(List<Double> basePoint) {
        return Vectors.add(Vectors.add(basePoint, basePoint), Vectors.negate(variables.previousPoint));
    }

    private ExploratoryMoveMinimum getMinimumForDimension(List<Double> basePoint, int dimension) {
        ExploratoryMoveMinimum minimum = new ExploratoryMoveMinimum();
        List<Double> values = new ArrayList<>();
        List<List<Double>> arguments = new ArrayList<>();

        List<Double> minus = new ArrayList<>(basePoint);
        minus.set(dimension, minus.get(dimension) - variables.incrementVector.get(dimension));
        values.add(objectiveFunction.getValue(minus.get(0), minus.get(1)).doubleValue());
        arguments.add(minus);
        values.add(objectiveFunction.getValue(basePoint.get(0), basePoint.get(1)).doubleValue());
        arguments.add(basePoint);
        List<Double> plus = new ArrayList<>(basePoint);
        plus.set(dimension, plus.get(dimension) + variables.incrementVector.get(dimension));
        values.add(objectiveFunction.getValue(plus.get(0), plus.get(1)).doubleValue());
        arguments.add(plus);

        int minIndex = values.indexOf(Collections.min(values));
        minimum.value = values.get(minIndex);
        minimum.argument = arguments.get(minIndex);
        return minimum;
    }

    private enum AlgorithmStep {
        TERMINATION,
        PREPARATIONS,
        EXPLORATORY_MOVE_1,
        CONVERGENCE_CHECK,
        PATTERN_MOVE,
        EXPLORATORY_MOVE_2,
        DIRECT_SEARCH_CHECK
    }

    private class AlgorithmVariables {

        private List<Double> startPoint;
        private List<Double> incrementVector;
        private long iterationsNo;
        private List<Double> currentPoint;
        private boolean isExploratoryMoveSuccessful;
        private List<Double> nextPoint;
        private List<Double> previousPoint;
    }

    private class ExploratoryMoveMinimum {

        double value;
        List<Double> argument;
    }

}
