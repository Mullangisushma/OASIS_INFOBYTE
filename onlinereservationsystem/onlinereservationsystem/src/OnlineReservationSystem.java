import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class OnlineReservationSystem {
    private static Map<String, String> userCredentials = new HashMap<>();
    private static Map<Integer, Reservation> reservations = new HashMap<>();
    private static int reservationCounter = 1;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Adding a sample user for demonstration
        userCredentials.put("sushma", "23");

        while (true) {
            System.out.println("1. Login");
            System.out.println("2. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    login();
                    break;
                case 2:
                    System.out.println("Exiting the system. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void login() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your username: ");
        String username = scanner.next();
        System.out.print("Enter your password: ");
        String password = scanner.next();

        if (userCredentials.containsKey(username) && userCredentials.get(username).equals(password)) {
            System.out.println("Login successful!");

            while (true) {
                System.out.println("1. Reservation");
                System.out.println("2. Cancellation");
                System.out.println("3. Logout");
                System.out.print("Enter your choice: ");
                int userChoice = scanner.nextInt();

                switch (userChoice) {
                    case 1:
                        performReservation(username, scanner);
                        break;
                    case 2:
                        performCancellation(username, scanner);
                        break;
                    case 3:
                        System.out.println("Logging out...");
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        } else {
            System.out.println("Invalid username or password. Please try again.");
        }
    }

    private static void performReservation(String username, Scanner scanner) {
        System.out.print("Enter your full name: ");
        scanner.nextLine(); // Consume the newline character
        String fullName = scanner.nextLine();

        System.out.print("Enter train number: ");
        int trainNumber = scanner.nextInt();

        System.out.print("Enter class type: ");
        String classType = scanner.next();

        System.out.print("Enter journey date (YYYY-MM-DD): ");
        String journeyDateStr = scanner.next();

        System.out.print("Enter from place: ");
        String fromPlace = scanner.next();

        System.out.print("Enter to destination: ");
        String toDestination = scanner.next();

        Reservation reservation = new Reservation(reservationCounter++, username, fullName, trainNumber, classType, journeyDateStr, fromPlace, toDestination);
        reservations.put(reservation.getReservationId(), reservation);

        System.out.println("Reservation successful! Your PNR number is: " + reservation.getReservationId());
    }

    private static void performCancellation(String username, Scanner scanner) {
        System.out.print("Enter your PNR number for cancellation: ");
        int pnrNumber = scanner.nextInt();

        if (reservations.containsKey(pnrNumber) && reservations.get(pnrNumber).getUsername().equals(username)) {
            Reservation reservation = reservations.get(pnrNumber);

            System.out.println("Reservation details:");
            System.out.println(reservation);

            System.out.print("Do you want to confirm cancellation? (yes/no): ");
            String confirm = scanner.next().toLowerCase();
            if (confirm.equals("yes")) {
                reservations.remove(pnrNumber);
                System.out.println("Cancellation confirmed!");
            } else {
                System.out.println("Cancellation not confirmed.");
            }
        } else {
            System.out.println("Invalid PNR number or reservation does not belong to you. Please try again.");
        }
    }
}

class Reservation {
    private int reservationId;
    private String username;
    private String fullName;
    private int trainNumber;
    private String classType;
    private String journeyDate;
    private String fromPlace;
    private String toDestination;

    public Reservation(int reservationId, String username, String fullName, int trainNumber, String classType, String journeyDate, String fromPlace, String toDestination) {
        this.reservationId = reservationId;
        this.username = username;
        this.fullName = fullName;
        this.trainNumber = trainNumber;
        this.classType = classType;
        this.journeyDate = journeyDate;
        this.fromPlace = fromPlace;
        this.toDestination = toDestination;
    }

    public int getReservationId() {
        return reservationId;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public String toString() {
        return "Reservation ID: " + reservationId +
                "\nUsername: " + username +
                "\nFull Name: " + fullName +
                "\nTrain Number: " + trainNumber +
                "\nClass Type: " + classType +
                "\nJourney Date: " + journeyDate +
                "\nFrom Place: " + fromPlace +
                "\nTo Destination: " + toDestination;
    }
    }
