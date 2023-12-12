package task4;

class ExpressionParser {
    private int pos;
    private String input;

    public ExpressionParser(String input) {
        this.input = input;
        this.pos = 0;
    }

    public Expression parse() {
        return parseExpression(0);
    }

    private Expression parseExpression(int priority) {
        Expression left = parseTerm();
        while (pos < input.length()) {
            char operator = input.charAt(pos);
            int opPriority = getPriority(operator);

            if (opPriority <= priority) {
                break;
            }

            pos++;

            Expression right = parseExpression(opPriority);
            left = createBinaryOperation(operator, left, right);
        }
        return left;
    }

    private Expression parseTerm() {
        char currentChar = input.charAt(pos);

        if (Character.isDigit(currentChar) || currentChar == '-') {
            return parseConst();
        } else if (Character.isLetter(currentChar)) {
            return parseVariable();
        } else if (currentChar == '(') {
            pos++;
            Expression expression = parseExpression(0);
            pos++; // consume ')'
            return expression;
        }

        throw new IllegalArgumentException("Unexpected character: " + currentChar);
    }

    private Expression parseConst() {
        int start = pos;
        while (pos < input.length() && (Character.isDigit(input.charAt(pos)) || input.charAt(pos) == '-')) {
            pos++;
        }
        String constStr = input.substring(start, pos);

        try {
            return new Const(Integer.parseInt(constStr));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid constant: " + constStr);
        }
    }

    private Expression parseVariable() {
        char variable = input.charAt(pos);
        pos++;
        return new Variable(Character.toString(variable));
    }

    private Expression createBinaryOperation(char operator, Expression left, Expression right) {
        switch (operator) {
            case '+':
                return new Add(left, right);
            case '-':
                return new Subtract(left, right);
            case '*':
                return new Multiply(left, right);
            case '/':
                return new Divide(left, right);
            default:
                throw new IllegalArgumentException("Unknown operator: " + operator);
        }
    }

    private int getPriority(char operator) {
        switch (operator) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            default:
                return 0;
        }
    }
}