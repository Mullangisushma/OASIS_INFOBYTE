import java.util.Random;
import javax.swing.JOptionPane;

public class GuessNumberGame {
    public static void main(String[] args) {
        // Setting up the game
        int lowerBound = 1;
        int upperBound = 100;
        int targetNumber = generateRandomNumber(lowerBound, upperBound);
        int maxAttempts = 10;
        int attempts = 0;
        int score = 100; // Initial score

        // Game loop
        while (attempts < maxAttempts) {
            // User input
            String input = JOptionPane.showInputDialog("Guess the number between "
                    + lowerBound + " and " + upperBound + "\nAttempts left: "
                    + (maxAttempts - attempts) + "\nYour score: " + score);
            
            // Check if the user wants to exit
            if (input == null) {
                break;
            }

            // Validate user input
            int userGuess;
            try {
                userGuess = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid number.");
                continue;
            }

            // Check user's guess
            if (userGuess < lowerBound || userGuess > upperBound) {
                JOptionPane.showMessageDialog(null, "Please guess a number within the specified range.");
            } else if (userGuess == targetNumber) {
                JOptionPane.showMessageDialog(null, "Congratulations! You guessed the correct number!");
                break;
            } else {
                String message = (userGuess < targetNumber) ? "Too low! Try again." : "Too high! Try again.";
                JOptionPane.showMessageDialog(null, message);
                attempts++;
                // Adjust score based on attempts
                score -= 10;
            }
        }

        // Display result
        if (attempts == maxAttempts) {
            JOptionPane.showMessageDialog(null, "Sorry, you've run out of attempts. The correct number was " + targetNumber + ".");
        }

        JOptionPane.showMessageDialog(null, "Game Over! Your final score: " + score);
    }

    // Helper method to generate a random number within a specified range
    private static int generateRandomNumber(int lowerBound, int upperBound) {
        Random random = new Random();
        return random.nextInt(upperBound - lowerBound + 1) + lowerBound;
    }
}

