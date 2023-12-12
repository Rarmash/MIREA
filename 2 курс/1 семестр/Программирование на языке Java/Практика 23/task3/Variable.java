package task3;

class Variable implements Expression {
    private final String name;

    public Variable(String name) {
        this.name = name;
    }

    @Override
    public int evaluate(int x) {
        // В данном случае игнорируем имя переменной и возвращаем переданное значение x
        return x;
    }
}
