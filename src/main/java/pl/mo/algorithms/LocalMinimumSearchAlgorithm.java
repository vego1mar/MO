package pl.mo.algorithms;

public abstract class LocalMinimumSearchAlgorithm {

    protected ObjectiveFunction objectiveFunction;

    protected LocalMinimumSearchAlgorithm() {
        objectiveFunction = new Polynomial();
    }

    public ObjectiveFunction getObjectiveFunction() {
        return objectiveFunction;
    }

    public void setObjectiveFunction(ObjectiveFunction objectiveFunction) {
        this.objectiveFunction = objectiveFunction;
    }

}
