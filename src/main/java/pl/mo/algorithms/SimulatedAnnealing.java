package pl.mo.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import pl.mo.functions.Himmelblau;
import pl.mo.general.Numbers;
import pl.mo.strings.SimulatedAnnealingBundle;

public class SimulatedAnnealing extends LocalMinimumSearchAlgorithm {

    private static final double MINIMUM_TEMPERATURE = 0.00001;
    private double temperatureCoolingFactor = 0.77;
    private int annealingThreshold = 1000;
    private AlgorithmVariables vars;
    private SimulatedAnnealingBundle bundle;

    public SimulatedAnnealing() {
        objectiveFunction = new Himmelblau();
        bundle = new SimulatedAnnealingBundle();
    }

    public long getIterationsNo() {
        if (vars != null) {
            return vars.iterationsNo;
        }

        return 0;
    }

    public List<Double> getLocalMinimumArgument(List<Double> startPoint, IntervalConstraints bounds) {
        if (startPoint == null || startPoint.isEmpty()) {
            startPoint = new ArrayList<>(Arrays.asList(0.0, 0.0));
        }

        if (bounds == null) {
            bounds = new IntervalConstraints();
        }

        return getAnnealingResult(startPoint, bounds);
    }

    private List<Double> getAnnealingResult(@NotNull List<Double> startPoint, IntervalConstraints bounds) {
        if (startPoint.size() != 2) {
            throw new IllegalArgumentException(bundle.getErrorPointIsNot2D());
        }

        vars = new AlgorithmVariables(startPoint, bounds);

        while (vars.temperature > MINIMUM_TEMPERATURE) {
            performAnnealingCycle();
            performCooling();
            vars.iterationsNo++;
        }

        return vars.bestSolution;
    }

    private void performAnnealingCycle() {
        for (int i = 0; i < annealingThreshold; i++) {
            vars.randomSolution = getRandomNeighboringSolution();
            double lhs = getObjectiveFunction().getValue(vars.randomSolution.get(0), vars.randomSolution.get(1)).doubleValue();
            double rhs = getObjectiveFunction().getValue(vars.bestSolution.get(0), vars.bestSolution.get(1)).doubleValue();

            if (lhs < rhs) {
                vars.bestSolution = new ArrayList<>(vars.randomSolution);
            }

            rhs = getObjectiveFunction().getValue(vars.currentSolution.get(0), vars.currentSolution.get(1)).doubleValue();
            vars.costSubtraction = lhs - rhs;

            if (vars.costSubtraction < 0.0) {
                vars.currentSolution = new ArrayList<>(vars.randomSolution);
                continue;
            }

            if (vars.random.nextDouble() < getAcceptanceProbability()) {
                vars.currentSolution = new ArrayList<>(vars.randomSolution);
            }
        }
    }

    private void performCooling() {
        vars.temperature *= temperatureCoolingFactor;
    }

    private List<Double> getRandomNeighboringSolution() {
        List<Double> point = new ArrayList<>(vars.currentSolution);
        List<Double> xBound = vars.bounds.getX();
        List<Double> yBound = vars.bounds.getY();
        double gaussian1 = Numbers.getRandomGaussian(xBound.get(0), xBound.get(1));
        double gaussian2 = Numbers.getRandomGaussian(yBound.get(0), yBound.get(1));

        if (Numbers.isInRange(point.get(0) + gaussian1, xBound.get(0), xBound.get(1))) {
            point.set(0, point.get(0) + gaussian1);
        }

        if (Numbers.isInRange(point.get(1) + gaussian2, xBound.get(0), xBound.get(1))) {
            point.set(1, point.get(1) + gaussian2);
        }

        return point;
    }

    @Contract(pure = true)
    private double getAcceptanceProbability() {
        return Math.exp(-vars.costSubtraction / vars.temperature);
    }

    public double getTemperatureCoolingFactor() {
        return temperatureCoolingFactor;
    }

    public void setTemperatureCoolingFactor(double temperatureCoolingFactor) {
        if (temperatureCoolingFactor > 0.0 && temperatureCoolingFactor < 1.0) {
            this.temperatureCoolingFactor = temperatureCoolingFactor;
            return;
        }

        throw new IllegalArgumentException(bundle.getErrorTemperatureCoolingFactor());
    }

    public int getAnnealingThreshold() {
        return annealingThreshold;
    }

    public void setAnnealingThreshold(int annealingThreshold) {
        if (annealingThreshold > 0) {
            this.annealingThreshold = annealingThreshold;
            return;
        }

        throw new IllegalArgumentException(bundle.getErrorAnnealingThreshold());
    }

    private final class AlgorithmVariables {

        double costSubtraction;
        double temperature;
        long iterationsNo;
        private Random random;
        private List<Double> currentSolution;
        private List<Double> bestSolution;
        private List<Double> randomSolution;
        private IntervalConstraints bounds;

        private AlgorithmVariables(List<Double> startPoint, IntervalConstraints bounds) {
            random = new Random(System.nanoTime());
            currentSolution = new ArrayList<>(startPoint);
            bestSolution = new ArrayList<>(startPoint);
            temperature = 1.0;
            randomSolution = new ArrayList<>(startPoint);
            costSubtraction = 0.0;
            this.bounds = bounds;
            iterationsNo = 0;
        }
    }

    public final class IntervalConstraints {

        private double xLeft;
        private double xRight;
        private double yLeft;
        private double yRight;

        public IntervalConstraints() {
            xLeft = -1.0;
            xRight = 1.0;
            yLeft = -1.0;
            yRight = 1.0;
        }

        public void setX(double a, double b) {
            if (a < b) {
                xLeft = a;
                xRight = b;
                return;
            }

            throw new IllegalArgumentException(bundle.getErrorIntervalInvalid());
        }

        public void setY(double a, double b) {
            if (a < b) {
                yLeft = a;
                yRight = b;
                return;
            }

            throw new IllegalArgumentException(bundle.getErrorIntervalInvalid());
        }

        @NotNull
        @Contract(pure = true)
        public List<Double> getX() {
            return List.of(xLeft, xRight);
        }

        @NotNull
        @Contract(pure = true)
        public List<Double> getY() {
            return List.of(yLeft, yRight);
        }

    }

}
