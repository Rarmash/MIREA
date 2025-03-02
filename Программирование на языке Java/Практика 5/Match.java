import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Color;
public class Match extends JFrame {
    private final JButton milan;
    private final JButton madrid;
    private final JLabel result;
    private final JLabel lastScore;
    private final JLabel winner;
    private int milanScore = 0;
    private int madridScore = 0;

    public Match(){
        setTitle("Football match");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 1));
        getContentPane().setBackground(new Color(255, 179, 0));


        milan = new JButton("AC Milan");
        madrid = new JButton("Real Madrid");
        result = new JLabel("Result: 0 X 0");
        lastScore = new JLabel("Last Scorer: N/A");
        winner = new JLabel("Winner: DRAW");
        milan.addActionListener(a-> {
            milanScore++;
            updateScore();
            lastScore.setText("Last Scorer: AC Milan");
            updateWinner();
        });
        madrid.addActionListener(b-> {
            madridScore++;
            updateScore();
            lastScore.setText("Last Scorer: AC Milan");
            updateWinner();
        });
        add(milan);
        add(madrid);
        add(result);
        add(lastScore);
        add(winner);

        setVisible(true);
    }
    private void updateScore() {
        result.setText("Result: " + milanScore + " X " + madridScore);
    }
    private void updateWinner() {
        if (milanScore > madridScore) {
            winner.setText("Winner: AC Milan");
        } else if (milanScore < madridScore) {
            winner.setText("Winner: Real Madrid");
        } else {
            winner.setText("Winner: DRAW");
        }
    }
    public static void main(String[] args) {
        new Match();
    }
}