package Task_2;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.Stack;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MyCalculator extends JFrame {
    private final JTextField jTextField;
    private final JTextField expressionTextField;
    private final JPanel jPanel = new JPanel();
    private final JButton[] jButtons;
    private final Stack<Double> operandStack = new Stack<>();
    private final StringBuilder currentExpression = new StringBuilder();

    public MyCalculator() {
        this.setTitle("Польская нотация калькулятор");
        this.setSize(600, 300);
        this.setLocationRelativeTo(null);

        jTextField = new JTextField(30);
        jTextField.setText("");
        jTextField.setEditable(true);
        this.add(jTextField, "North");

        expressionTextField = new JTextField(30);
        expressionTextField.setText("");
        expressionTextField.setEditable(false);
        this.add(expressionTextField, "South");

        jPanel.setLayout(new GridLayout(5, 7, 3, 3));
        String[] name = {
                "C", "/", "*", "Back",
                "7", "8", "9", "-", "4", "5",
                "6", "+", "1", "2", "3", "%",
                "0", ".", "=", "Space"
        };
        jButtons = new JButton[name.length];
        MyActionListener actionListener = new MyActionListener();

        for (int i = 0; i < name.length; i++) {
            jButtons[i] = new JButton(name[i]);
            jButtons[i].addActionListener(actionListener);
            jButtons[i].setBackground(Color.LIGHT_GRAY);
            if (name[i].equals("="))
                jButtons[i].setBackground(Color.RED);
            else if (isNumeric(name[i]))
                jButtons[i].setBackground(Color.WHITE);
            else if (name[i].equals("Back"))
                jButtons[i].setBackground(Color.GRAY);
            jPanel.add(jButtons[i]);
        }

        this.add(jPanel);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    class MyActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String input = e.getActionCommand();

            if (isNumeric(input)) {
                double number = Double.parseDouble(input);
                operandStack.push(number);
                currentExpression.append(input);
            }
//            } else if (input.equals("Space")) {
//                input += " ";
//            }
            else if (isOperator(input)) {
                if (operandStack.size() >= 2) {
                    double result = performOperation(input);
                    operandStack.push(result);
                    currentExpression.append(" ").append(input);
                }
            } else if (input.equals("=")) {
                if (!operandStack.isEmpty()) {
                    double result = operandStack.pop();
                    jTextField.setText(getPrettyNumber(Double.toString(result)));
                    currentExpression.append(" = ").append(getPrettyNumber(Double.toString(result)));
                }
            } else if (input.equals("C")) {
                operandStack.clear();
                jTextField.setText("");
                currentExpression.setLength(0);
            } else if (input.equals("Back")) {
                if (!operandStack.isEmpty()) {
                    operandStack.pop();
                    if (!operandStack.isEmpty()) {
                        double result = operandStack.peek();
                        jTextField.setText(getPrettyNumber(Double.toString(result)));
                        currentExpression.setLength(currentExpression.length() - 2); // Remove last operator and space
                    } else {
                        jTextField.setText("");
                        currentExpression.setLength(0);
                    }
                }
            }
            expressionTextField.setText(currentExpression.toString());
        }
    }

    private boolean isNumeric(String input) {
        return input.matches("-?\\d+(\\.\\d+)?");
    }

    private boolean isOperator(String input) {
        return "+-*/".contains(input);
    }

    private double performOperation(String operator) {
        if (operandStack.size() < 2) {
            return 0;
        }

        double operand2 = operandStack.pop();
        double operand1 = operandStack.pop();

        switch (operator) {
            case "+":
                return operand1 + operand2;
            case "-":
                return operand1 - operand2;
            case "*":
                return operand1 * operand2;
            case "/":
                if (operand2 != 0) {
                    return operand1 / operand2;
                } else {
                    return 0;
                }
            default:
                return 0;
        }
    }

    public static String getPrettyNumber(String number) {
        return BigDecimal.valueOf(Double.parseDouble(number))
                .stripTrailingZeros().toPlainString();
    }

    public static void main(String[] args) {
        new MyCalculator();
    }
}