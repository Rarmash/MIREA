package task1;

public class Main {
    public static void main(String[] args) {
        // Создание фабрики
        IComplexAbstractFactory factory = new ConcreteFactory();

        // Использование фабрики для создания комплексных чисел
        Complex complex1 = factory.createComplex();
        Complex complex2 = factory.CreateComplex(3, 4);

        // Вывод комплексных чисел
        System.out.println("Complex 1: " + complex1);
        System.out.println("Complex 2: " + complex2);
    }
}
