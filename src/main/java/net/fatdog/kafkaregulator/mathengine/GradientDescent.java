package net.fatdog.kafkaregulator.mathengine;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GradientDescent {

    // K에 대한 편미분
    private static double dfByK(double K, double lambda, double mu) {
        return -2 / (K * (K + 1)) - 2 * (-K * lambda + mu) / (K * lambda * Math.pow(K + 1, 2)) - 2 * (-K * lambda + mu) / (Math.pow(K, 2) * lambda * (K + 1));
    }

    // λ에 대한 편미분
    private static double dfByLambda(double K, double lambda, double mu) {
        return -2 / (lambda * (K + 1)) - 2 * (-K * lambda + mu) / (K * Math.pow(lambda, 2) * (K + 1));
    }

    // μ에 대한 편미분
    private static double dfByMu(double K, double lambda) {
        return 2 / (K * lambda * (K + 1));
    }

    // 그래디언트 디센트
    public static double[] gradientDescent(double initialK, double initialLambda, double initialMu, double learningRate, int iterations) {
        double K = initialK;
        double lambda = initialLambda;
        double mu = initialMu;

        for(int i = 0; i < iterations; i++) {
            K -= learningRate * dfByK(K, lambda, mu);
            lambda -= learningRate * dfByLambda(K, lambda, mu);
            mu -= learningRate * dfByMu(K, lambda);
        }

        return new double[]{K, lambda, mu};
    }

    public double[] getOptimalValues(double initialK, double initialLambda, double initialMu, double learningRate, int iteration) {

        initialK = 0.0; // 시작 K 값
        initialLambda = 1.0; // 시작 λ 값
        initialMu = 1.0; // 시작 μ 값
        learningRate = 0.01; // 학습률
        int iterations = 1000; // 반복 횟수
        
        double[] optimalValues = gradientDescent(initialK, initialLambda, initialMu, learningRate, iterations);

        log.info("Optimal values: K = %f, λ = %f, μ = %f\n", optimalValues[0], optimalValues[1], optimalValues[2]);

        return optimalValues;
    }
    
}
