package net.fatdog.kafkaregulator.mathengine;

public class QueueCalculation {

    private double deltaOfBytesInSystem;
    private double inputBytes;
    private double outputBytes;
    private double batchSize;

    
    public void create(double delta, double batchSize, double inputBytes, double outputBytes) {
    
        this.deltaOfBytesInSystem = delta;
        this.batchSize = batchSize;
        this.inputBytes = inputBytes;
        this.outputBytes = outputBytes;
    }

    public double findDirectionOfBytesInSystemDelta() {

        double direction = (outputBytes - inputBytes)/Math.abs(outputBytes-inputBytes);

        return direction;
    }
    
    public double findBytesInSystem() {

        double bytesInSystem = 0.0;

        bytesInSystem = ((batchSize+1)/2.0)*(inputBytes/(outputBytes-inputBytes));

        return bytesInSystem;
    }

    public double findBatchSizeDelta() {

        double batchSizeDelta = 0.0;

        batchSizeDelta = 2*deltaOfBytesInSystem*((outputBytes-inputBytes)/inputBytes);
        
        return batchSizeDelta;

    }

}
