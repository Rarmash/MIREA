package task4;

class Const implements Expression {
    private final int value;

    public Const(int value) {
        this.value = value;
    }

    @Override
    public int evaluate(int x) {
        if (value == Integer.MIN_VALUE && x == -1) {
            throw new ArithmeticException("Overflow");
        }
        return value;
    }
}