package pl.mo.algorithms;

import pl.mo.functions.ObjectiveFunction;
import pl.mo.functions.Polynomial;

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
