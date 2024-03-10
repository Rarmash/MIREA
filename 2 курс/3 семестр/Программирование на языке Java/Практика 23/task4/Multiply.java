package task4;

class Multiply extends BinaryOperation {
    public Multiply(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public int evaluate(int x) {
        int leftValue = left.evaluate(x);
        int rightValue = right.evaluate(x);

        if (leftValue > 0
                ? (rightValue > 0 && leftValue > Integer.MAX_VALUE / rightValue) || (rightValue < 0 && rightValue < Integer.MIN_VALUE / leftValue)
                : (rightValue > 0 && leftValue < Integer.MIN_VALUE / rightValue) || (rightValue < 0 && leftValue != 0 && leftValue > Integer.MAX_VALUE / rightValue)) {
            throw new ArithmeticException("Overflow");
        }

        return leftValue * rightValue;
    }
}
