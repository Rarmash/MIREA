package Work_7;

public class MathTest {
    public static void main(String[] args) {
        MathCalculable mc = new MathFunc();

        int powResult = mc.pow(2, 3);
        System.out.println("Степень: " + powResult);

        double complNumResult = mc.complNum(3, 4);
        System.out.println("Модуль комплексного числа: " + complNumResult);

        double circleLength = ((MathFunc)mc).cirleLenth(5);
        System.out.println("Длина окружности: " + circleLength);

        System.out.println("Чило ПИ: " + MathCalculable.PI);
    }
}