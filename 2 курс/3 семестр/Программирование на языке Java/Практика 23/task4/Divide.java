package task4;

class Divide extends BinaryOperation {
    public Divide(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public int evaluate(int x) {
        int leftValue = left.evaluate(x);
        int rightValue = right.evaluate(x);

        if (rightValue == 0) {
            throw new ArithmeticException("Division by zero");
        }

        if (leftValue == Integer.MIN_VALUE && rightValue == -1) {
            throw new ArithmeticException("Overflow");
        }

        return leftValue / rightValue;
    }
}
