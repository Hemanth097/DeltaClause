import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NumberGuessingGameGUI extends JFrame {
    private JTextField guessField;
    private JLabel messageLabel;
    private JButton guessButton;
    private JLabel attemptsLabel;
    private int attempts;
    private int numberToGuess;
    private List<Integer> previousGuesses;

    public NumberGuessingGameGUI() {
        Random random = new Random();
        numberToGuess = random.nextInt(100) + 1;
        previousGuesses = new ArrayList<>();

        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        JLabel welcomeLabel = new JLabel("Welcome to the number guessing game!");
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        add(welcomeLabel, constraints);

        JLabel promptLabel = new JLabel("Enter your guess:");
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.anchor = GridBagConstraints.LINE_START;
        add(promptLabel, constraints);

        guessField = new JTextField(5);
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.anchor = GridBagConstraints.LINE_END;
        add(guessField, constraints);

        guessButton = new JButton("Guess");
        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        constraints.anchor = GridBagConstraints.CENTER;
        add(guessButton, constraints);

        messageLabel = new JLabel("");
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        add(messageLabel, constraints);

        attemptsLabel = new JLabel("Attempts: 0");
        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        add(attemptsLabel, constraints);

        guessButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String guessText = guessField.getText();
                int guess;
                try {
                    guess = Integer.parseInt(guessText);
                } catch (NumberFormatException ex) {
                    messageLabel.setText("Invalid input. Please enter a number.");
                    return;
                }

                previousGuesses.add(guess);
                attempts++;
                attemptsLabel.setText("Attempts: " + attempts);

                if (guess < numberToGuess) {
                    if (numberToGuess - guess <= 5) {
                        messageLabel.setText("Still low, but close! Try again.");
                    } else {
                        messageLabel.setText("Too low! Try again.");
                    }
                } else if (guess > numberToGuess) {
                    if (guess - numberToGuess <= 5) {
                        messageLabel.setText("Still high, but close! Try again.");
                    } else {
                        messageLabel.setText("Too high! Try again.");
                    }
                } else {
                    messageLabel.setText("Congratulations! You guessed the correct number in " + attempts + " attempts.");
                    guessField.setEnabled(false);
                    guessButton.setEnabled(false);
                }

                guessField.setText("");
                guessField.requestFocus();
            }
        });

        guessField.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        guessButton.doClick();
                    }
                });

                setTitle("Number Guessing Game");
                setDefaultCloseOperation(EXIT_ON_CLOSE);
                setSize(400, 200);
                setLocationRelativeTo(null);
                setResizable(false);
                setVisible(true);
        }

        public static void main(String[] args) {
            new NumberGuessingGameGUI();
        }
    }
       