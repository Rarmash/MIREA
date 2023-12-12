package task4;

public class main {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java Main <expression>");
            return;
        }

        String expressionStr = args[0];

        ExpressionParser parser = new ExpressionParser(expressionStr);
        Expression expression = parser.parse();

        System.out.println(" x\tf");
        for (int x = 0; x <= 10; x++) {
            try {
                int result = expression.evaluate(x);
                System.out.println(" " + x + "\t" + result);
            } catch (ArithmeticException e) {
                if (e.getMessage().equals("Overflow")) {
                    System.out.println(" " + x + "\toverflow");
                } else {
                    System.out.println(" " + x + "\t" + e.getMessage());
                }
            }
        }
    }
}