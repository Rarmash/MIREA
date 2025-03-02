package task1;

public class ConcreteFactory implements IComplexAbstractFactory {
    @Override
    public Complex createComplex() {
        // Реализация создания комплексного числа по умолчанию
        return new Complex(0, 0);
    }

    @Override
    public Complex CreateComplex(int real, int imaginary) {
        // Реализация создания комплексного числа с заданными вещественной и мнимой частями
        return new Complex(real, imaginary);
    }
}
