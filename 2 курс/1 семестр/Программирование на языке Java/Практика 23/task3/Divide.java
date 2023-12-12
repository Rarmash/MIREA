package task3;

class Divide extends BinaryOperation {
    public Divide(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public int evaluate(int x) {
        int divisor = right.evaluate(x);
        if (divisor != 0) {
            return left.evaluate(x) / divisor;
        } else {
            throw new ArithmeticException("Division by zero");
        }
    }
}
