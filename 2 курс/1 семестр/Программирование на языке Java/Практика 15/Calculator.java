import javax.swing.*;
import java.awt.event.ActionListener;

public class Calculator extends JFrame {
    private JPanel jPanel1;
    private JTextField output;
    private JButton a7Button;
    private JButton a8Button;
    private JButton a9Button;
    private JButton divisionButton;
    private JButton a4Button;
    private JButton a5Button;
    private JButton a6Button;
    private JButton multiplyButton;
    private JButton a1Button;
    private JButton a2Button;
    private JButton a3Button;
    private JButton subtractionButton;
    private JButton a0Button;
    private JButton dotButton;
    private JButton calculateButton;
    private JButton addButton;
    private final CalculatorManager calculatorManager;
    private boolean readSecondValue;

    private boolean error;

    public Calculator() {
        setContentPane(jPanel1);
        pack();
        setTitle("Calculator");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);

        calculatorManager = new CalculatorManager();
        output.setText(calculatorManager.getStringValue());

        // Creating listeners
        a7Button.addActionListener(e -> {
            addDigitToValue('7');
        });
        a8Button.addActionListener(e -> {
            addDigitToValue('8');
        });
        a9Button.addActionListener(e -> {
            addDigitToValue('9');
        });
        a4Button.addActionListener(e -> {
            addDigitToValue('4');
        });
        a5Button.addActionListener(e -> {
            addDigitToValue('5');
        });
        a6Button.addActionListener(e -> {
            addDigitToValue('6');
        });
        a1Button.addActionListener(e -> {
            addDigitToValue('1');
        });
        a2Button.addActionListener(e -> {
            addDigitToValue('2');
        });
        a3Button.addActionListener(e -> {
            addDigitToValue('3');
        });
        a0Button.addActionListener(e -> {
            addDigitToValue('0');
        });
        dotButton.addActionListener(e -> {
            addDigitToValue('.');
        });
        calculateButton.addActionListener(e -> {
            calculate();
        });
        addButton.addActionListener(e -> {
            performOperation(Operation.ADD);
        });
        subtractionButton.addActionListener(e -> {
            performOperation(Operation.SUBTRACT);
        });
        multiplyButton.addActionListener(e -> {
            performOperation(Operation.MULTIPLY);
        });
        divisionButton.addActionListener(e -> {
            performOperation(Operation.DIVIDE);
        });
    }

    private void addDigitToValue(char c) {
        if (readSecondValue) {
            output.setText(null);
        }

        if (error) {
            output.setText(null);
            error = false;
        }

//        if (c == '.' && calculatorManager.isInteger() && !output.getText().contains(".")) {
//            output.setText(output.getText() + '.');
//            return;
//        }

//        if (calculatorManager.isInteger() && calculatorManager.getValue() != 0) {
//            output.setText(output.getText() + c);
//        } else {
//            if (calculatorManager.getValue() == 0)
//                output.setText(String.valueOf(c));
//            else
//                output.setText(output.getText() + c);
//        }
        if (c == '.' && !output.getText().contains(".")) {
            output.setText(output.getText() + '.');
            return;
        }

        if (c != '.') {
            if (calculatorManager.getValue() == 0)
                output.setText(String.valueOf(c));
            else
                output.setText(output.getText() + c);
        }

        String stringValue = output.getText();
        if (stringValue.endsWith("."))
            stringValue = String.valueOf(calculatorManager.getValue());

        if (readSecondValue)
            calculatorManager.setLastValue(Double.parseDouble(stringValue));
        else
            calculatorManager.setValue(Double.parseDouble(stringValue));
    }

    private void performOperation(Operation operation) {
        calculatorManager.setLastOperation(operation);
        readSecondValue = true;
    }

    public void calculate() {
        if (calculatorManager.getLastOperation() == null)
            return;

        if (readSecondValue) {
            readSecondValue = false;
        }

        if (calculatorManager.getLastOperation() == Operation.DIVIDE && calculatorManager.getLastValue() == 0) {
            calculatorManager.clear();
            output.setText("Нельзя делить на 0");
            error = true;
            return;
        }

        switch (calculatorManager.getLastOperation()) {
            case ADD -> {
                calculatorManager.add(calculatorManager.getLastValue());
            }
            case SUBTRACT -> {
                calculatorManager.subtract(calculatorManager.getLastValue());
            }
            case MULTIPLY -> {
                calculatorManager.multiply(calculatorManager.getLastValue());
            }
            case DIVIDE -> {
                calculatorManager.divide(calculatorManager.getLastValue());
            }
        }

        output.setText(String.valueOf(calculatorManager.getValue()));
    }
}