package pl.mo.algorithms;

public strictfp class GoldenSectionSearch extends LocalMinimumSearchAlgorithm {

    public static final double GOLDEN_RATIO = (Math.sqrt(5.0) - 1.0) / 2.0;

    /**
     * @throws IllegalArgumentException when 'left' >= 'right'
     */
    @Override
    public Double getLocalMinimumArgument(double left, double right, double epsilon) {
        if (left >= right) {
            throw new IllegalArgumentException("Argument 'left' is greater than or equal to argument 'right'.");
        }

        final double EPSILON = Math.abs(epsilon);
        Double currentLeft = right - GOLDEN_RATIO * (right - left);
        Double currentRight = left + GOLDEN_RATIO * (right - left);

        while ((right - left) > EPSILON) {
            if (scoreFunction.getValue(currentLeft) < scoreFunction.getValue(currentRight)) {
                right = currentRight;
                currentRight = currentLeft;
                currentLeft = right - GOLDEN_RATIO * (right - left);
            } else {
                left = currentLeft;
                currentLeft = currentRight;
                currentRight = left + GOLDEN_RATIO * (right - left);
            }
        }

        return (left + right) / 2.0;
    }

}
