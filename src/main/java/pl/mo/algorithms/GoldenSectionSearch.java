package pl.mo.algorithms;

import pl.mo.strings.GoldenSectionSearchBundle;

public strictfp class GoldenSectionSearch extends LocalMinimumSearchAlgorithm {

    public static final double GOLDEN_RATIO = (Math.sqrt(5.0) - 1.0) / 2.0;
    private final GoldenSectionSearchBundle bundle = new GoldenSectionSearchBundle();

    /**
     * @throws IllegalArgumentException when 'left' >= 'right'
     */
    @Override
    public Double getLocalMinimumArgument(double left, double right, double epsilon) {
        if (left >= right) {
            throw new IllegalArgumentException(bundle.getErrorIntervalArgumentsMismatch());
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
