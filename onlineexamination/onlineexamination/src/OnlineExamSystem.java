import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OnlineExamSystem {
    static List<User> users = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);
    static int userId;

    public static void main(String[] args) {
        users.add(new User("sushma", "23"));
        boolean loggedIn = false;

        while (true) {
            if (!loggedIn) {
                System.out.println("Welcome to the Online Exam System!");
                System.out.println("1. Login");
                System.out.println("2. Exit");

                int choice = getUserChoice();

                switch (choice) {
                    case 1:
                        loggedIn = login();
                        break;
                    case 2:
                        System.out.println("Exiting...");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } else {
                System.out.println("1. Update Profile and Password");
                System.out.println("2. Start Exam");
                System.out.println("3. Logout");

                int choice = getUserChoice();

                switch (choice) {
                    case 1:
                        updateProfile();
                        break;
                    case 2:
                        startExam();
                        break;
                    case 3:
                        logout();
                        loggedIn = false;
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        }
    }

    private static int getUserChoice() {
        System.out.print("Enter your choice: ");
        return scanner.nextInt();
    }

    private static boolean login() {
        System.out.println("Enter your login credentials:");
        System.out.print("Username: ");
        String username = scanner.next();
        System.out.print("Password: ");
        String password = scanner.next();

        for (User user : users) {
            if (user.getUsername().equalsIgnoreCase(username) && user.getPassword().equals(password)) {
                userId = user.getId();
                System.out.println("Login successful. Welcome, " + username + "!");
                return true;
            }
        }

        System.out.println("DEBUG: User list - " + users); // Add this line for debugging

        System.out.println("Invalid username or password. Please try again.");
        return false;
    }

    private static void updateProfile() {
        System.out.println("Update your profile:");
        System.out.print("Enter new password: ");
        String newPassword = scanner.next();

        for (User user : users) {
            if (user.getId() == userId) {
                user.setPassword(newPassword);
                System.out.println("Password updated successfully!");
                return;
            }
        }

        System.out.println("Error updating password. Please try again.");
    }

    private static void startExam() {
        System.out.println("Welcome to the Exam! Good luck!");
        List<Question> questions = generateQuestions();
        int score = 0;

        for (Question question : questions) {
            System.out.println(question.getText());
            for (int i = 0; i < question.getAnswers().size(); i++) {
                System.out.println((i + 1) + ". " + question.getAnswers().get(i).getText());
            }

            System.out.print("Your answer (enter the number): ");
            int userChoice = getUserChoice() - 1;

            if (userChoice >= 0 && userChoice < question.getAnswers().size()) {
                if (question.getAnswers().get(userChoice).isCorrect()) {
                    score++;
                    System.out.println("Correct!\n");
                } else {
                    System.out.println("Incorrect. The correct answer is: " +
                            question.getCorrectAnswer().getText() + "\n");
                }
            } else {
                System.out.println("Invalid choice. Skipping to the next question.\n");
            }
        }

        System.out.println("Your exam is complete!");
        System.out.println("Your score: " + score);
    }

    private static List<Question> generateQuestions() {
        List<Question> questions = new ArrayList<>();

        // Question 1
        List<Answer> q1Answers = new ArrayList<>();
        q1Answers.add(new Answer("Java", false));
        q1Answers.add(new Answer("Python", false));
        q1Answers.add(new Answer("JavaScript", true));
        q1Answers.add(new Answer("C++", false));
        Question q1 = new Question("What programming language is often used for web development?", q1Answers);
        questions.add(q1);

        // Question 2
        List<Answer> q2Answers = new ArrayList<>();
        q2Answers.add(new Answer("Tokyo", false));
        q2Answers.add(new Answer("Beijing", false));
        q2Answers.add(new Answer("Seoul", false));
        q2Answers.add(new Answer("New Delhi", true));
        Question q2 = new Question("Which city is the capital of India?", q2Answers);
        questions.add(q2);

        // Question 3
        List<Answer> q3Answers = new ArrayList<>();
        q3Answers.add(new Answer("Mercury", false));
        q3Answers.add(new Answer("Venus", false));
        q3Answers.add(new Answer("Earth", true));
        q3Answers.add(new Answer("Mars", false));
        Question q3 = new Question("Which planet is known as the 'Blue Planet'?", q3Answers);
        questions.add(q3);

                // Question 4
                List<Answer> q4Answers = new ArrayList<>();
                q4Answers.add(new Answer("Mona Lisa", true));
                q4Answers.add(new Answer("The Starry Night", false));
                q4Answers.add(new Answer("The Persistence of Memory", false));
                q4Answers.add(new Answer("Guernica", false));
                Question q4 = new Question("Who painted the Mona Lisa?", q4Answers);
                questions.add(q4);
        
                // Question 5
                List<Answer> q5Answers = new ArrayList<>();
                q5Answers.add(new Answer("1492", true));
                q5Answers.add(new Answer("1776", false));
                q5Answers.add(new Answer("1215", false));
                q5Answers.add(new Answer("1066", false));
                Question q5 = new Question("In which year did Christopher Columbus reach the Americas?", q5Answers);
                questions.add(q5);
        
                return questions;
            }
        
            private static void logout() {
                userId = 0; // Reset user ID on logout
                System.out.println("Logout successful.");
            }
        
            static class User {
                private static int idCounter = 1;
        
                private int id;
                private String username;
                private String password;
        
                public User(String username, String password) {
                    this.id = idCounter++;
                    this.username = username;
                    this.password = password;
                }
        
                public int getId() {
                    return id;
                }
        
                public String getUsername() {
                    return username;
                }
        
                public String getPassword() {
                    return password;
                }
        
                public void setPassword(String password) {
                    this.password = password;
                }
        
                @Override
                public String toString() {
                    return "User{" +
                            "id=" + id +
                            ", username='" + username + '\'' +
                            ", password='" + password + '\'' +
                            '}';
                }
            }
        
            static class Question {
                private String text;
                private List<Answer> answers;
        
                public Question(String text, List<Answer> answers) {
                    this.text = text;
                    this.answers = answers;
                }
        
                public String getText() {
                    return text;
                }
        
                public List<Answer> getAnswers() {
                    return answers;
                }
        
                public Answer getCorrectAnswer() {
                    for (Answer answer : answers) {
                        if (answer.isCorrect()) {
                            return answer;
                        }
                    }
                    return null;
                }
            }
        
            static class Answer {
                private String text;
                private boolean isCorrect;
        
                public Answer(String text, boolean isCorrect) {
                    this.text = text;
                    this.isCorrect = isCorrect;
                }
        
                public String getText() {
                    return text;
                }
        
                public boolean isCorrect() {
                    return isCorrect;
                }
            }
        }
        
