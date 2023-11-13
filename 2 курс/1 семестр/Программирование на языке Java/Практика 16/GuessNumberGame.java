import javax.swing.*;
import java.awt.*;

public class GuessNumberGame extends JFrame {
    private final int secretNumber;
    private int attemptsLeft = 3;
    private final JTextField textField;
    private final JLabel resultLabel;

    public GuessNumberGame() {
        setTitle("Угадай число");
        setSize(400, 200);
        setLayout(new BorderLayout());

        textField = new JTextField();
        add(textField, BorderLayout.CENTER);

        JButton guessButton = new JButton("Угадать");
        guessButton.addActionListener(e -> checkGuess());
        add(guessButton, BorderLayout.SOUTH);

        resultLabel = new JLabel("");
        add(resultLabel, BorderLayout.EAST);

        secretNumber = (int) (Math.random() * 21);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void checkGuess() {
        try {
            int userGuess = Integer.parseInt(textField.getText());

            if (userGuess == secretNumber) {
                resultLabel.setText("Поздравляем! Вы угадали число.");
                textField.setEnabled(false);
            } else {
                attemptsLeft--;
                if (attemptsLeft > 0) {
                    resultLabel.setText("Неправильно. Осталось попыток: " + attemptsLeft);
                } else {
                    resultLabel.setText("Вы исчерпали все попытки. Загаданное число: " + secretNumber);
                    textField.setEnabled(false);
                }
            }
        } catch (NumberFormatException ex) {
            resultLabel.setText("Введите число от 0 до 20");
        }
    }

    public static void main(String[] args) {
        new GuessNumberGame();
    }
}