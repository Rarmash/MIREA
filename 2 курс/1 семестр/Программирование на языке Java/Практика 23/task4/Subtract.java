package task4;

class Subtract extends BinaryOperation {
    public Subtract(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public int evaluate(int x) {
        int leftValue = left.evaluate(x);
        int rightValue = right.evaluate(x);

        if ((rightValue < 0 && leftValue > Integer.MAX_VALUE + rightValue) ||
                (rightValue > 0 && leftValue < Integer.MIN_VALUE + rightValue)) {
            throw new ArithmeticException("Overflow");
        }

        return leftValue - rightValue;
    }
}
