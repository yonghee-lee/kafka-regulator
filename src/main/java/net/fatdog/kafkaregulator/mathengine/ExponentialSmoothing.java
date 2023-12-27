package net.fatdog.kafkaregulator.mathengine;

public class ExponentialSmoothing {

    private static final int MAX_DATA_POINTS = 10;
    private static final double ALPHA = 0.3;
    private Double[] dataPoints = new Double[MAX_DATA_POINTS];
    private int currentIndex = 0;

    public void addDataPoint(double data) {
        dataPoints[currentIndex % MAX_DATA_POINTS] = data;
        currentIndex++;
    }

    public double calculateExponentialSmoothing() {
        double exponentialAverage = 0.0;
        double normalizationFactor = 0.0;
        double alphaPower = 1.0;

        for (int i = 1; i <= MAX_DATA_POINTS; i++) {
            int index = Math.floorMod(currentIndex - i, MAX_DATA_POINTS);
            if (dataPoints[index] != null) {
                exponentialAverage += alphaPower * dataPoints[index];
                normalizationFactor += alphaPower;
                alphaPower *= (1 - ALPHA);
            }
        }

        return normalizationFactor == 0.0 ? 0.0 : exponentialAverage / normalizationFactor;
    }

    public static void main(String[] args) {
        ExponentialSmoothing smoothing = new ExponentialSmoothing();

        // 예시 데이터 추가
        for (int i = 0; i < 10; i++) {
            smoothing.addDataPoint(Math.random() * 100); // 임의의 데이터 추가
        }

        System.out.println("Exponential Smoothing Average: " + smoothing.calculateExponentialSmoothing());
    }
}
