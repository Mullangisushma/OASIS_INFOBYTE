import java.util.ArrayList;
import java.util.Scanner;

class Transaction {
    String type;
    double amount;

    public Transaction(String type, double amount) {
        this.type = type;
        this.amount = amount;
    }
}

class Account {
    String userId;
    String userPin;
    double balance;
    ArrayList<Transaction> transactions;

    public Account(String userId, String userPin) {
        this.userId = userId;
        this.userPin = userPin;
        this.balance = 0.0;
        this.transactions = new ArrayList<>();
    }

    public void deposit(double amount) {
        balance += amount;
        transactions.add(new Transaction("DEPOSIT", amount));
        System.out.println("Deposited $" + amount + " successfully.");
    }

    public void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            transactions.add(new Transaction("WITHDRAWAL", amount));
            System.out.println("Withdrawn $" + amount + " successfully.");
        } else {
            System.out.println("Insufficient funds.");
        }
    }

    public void transfer(Account recipient, double amount) {
        if (balance >= amount) {
            balance -= amount;
            recipient.deposit(amount);
            transactions.add(new Transaction("TRANSFER", amount));
            System.out.println("Transferred $" + amount + " to " + recipient.userId + " successfully.");
        } else {
            System.out.println("Insufficient funds for transfer.");
        }
    }

    public void printTransactionHistory() {
        System.out.println("Transaction History for User " + userId + ":");
        for (Transaction transaction : transactions) {
            System.out.println(transaction.type + ": $" + transaction.amount);
        }
    }
}

class ATM {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Create sample accounts
        Account account1 = new Account("user1", "1234");
        Account account2 = new Account("user2", "5678");

        Account currentAccount = null;

        // ATM login
        System.out.println("Welcome to the ATM!");
        System.out.print("Enter User ID: ");
        String userId = scanner.nextLine();
        System.out.print("Enter User PIN: ");
        String userPin = scanner.nextLine();

        // Check login credentials
        if ((account1.userId.equals(userId) && account1.userPin.equals(userPin)) ||
            (account2.userId.equals(userId) && account2.userPin.equals(userPin))) {
            System.out.println("Login successful!");
            currentAccount = (account1.userId.equals(userId)) ? account1 : account2;
        } else {
            System.out.println("Invalid credentials. Exiting...");
            System.exit(0);
        }

        // ATM operations
        while (true) {
            System.out.println("\nATM Menu:");
            System.out.println("1. Transaction History");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Transfer");
            System.out.println("5. Quit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    currentAccount.printTransactionHistory();
                    break;
                case 2:
                    System.out.print("Enter withdrawal amount: $");
                    double withdrawAmount = scanner.nextDouble();
                    currentAccount.withdraw(withdrawAmount);
                    break;
                case 3:
                    System.out.print("Enter deposit amount: $");
                    double depositAmount = scanner.nextDouble();
                    currentAccount.deposit(depositAmount);
                    break;
                case 4:
                    System.out.print("Enter recipient's User ID: ");
                    String recipientId = scanner.next();
                    Account recipient = (account1.userId.equals(recipientId)) ? account1 : account2;
                    System.out.print("Enter transfer amount: $");
                    double transferAmount = scanner.nextDouble();
                    currentAccount.transfer(recipient, transferAmount);
                    break;
                case 5:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }
}

