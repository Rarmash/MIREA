public class CalculatorManager {
    private double value = 0.0;

    private double lastValue = 0.0;

    private Operation lastOperation;

    CalculatorManager() {}

    public void add(double val) {
        value += val;
    }

    public void subtract(double val) {
        value -= val;
    }

    public void multiply(double val) {
        value *= val;
    }

    public void divide(double val) {
        value /= val;
    }

    public void clear() {
        value = 0;
        lastValue = 0;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public double getLastValue() {
        return lastValue;
    }

    public void setLastValue(double lastValue) {
        this.lastValue = lastValue;
    }

    public Operation getLastOperation() {
        return lastOperation;
    }

    public void setLastOperation(Operation lastOperation) {
        this.lastOperation = lastOperation;
    }

    public boolean isInteger() {
        return Math.ceil(value) == Math.floor(value);
    }

    public String getStringValue() {
        if (value == 0)
            return "0";

        return String.valueOf(value);
    }

    @Override
    public String toString() {
        return getStringValue();
    }
}