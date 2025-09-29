
import static java.lang.System.exit;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Transaction {

    private String type;
    private double amount;
    private double balanceAfter;
    private String note;

    public Transaction(String type, double amount, double balanceAfter, String note) {
        this.type = type;
        this.amount = amount;
        this.balanceAfter = balanceAfter;
        this.note = note;
    }

    @Override
    public String toString() {
        return type + " of ₹" + amount + " | Balance: ₹" + balanceAfter
                + (note.isEmpty() ? "" : " | Note: " + note);
    }
}

class Account {

    private static int accountCounter = 1000;
    private int accountNumber;
    private String accountHolder;
    private String username;
    private String password;
    private double balance;
    private List<Transaction> transactions;

    public Account(String accountHolder, String username, String password) {
        this.accountHolder = accountHolder;
        this.username = username;
        this.password = password;
        this.accountNumber = ++accountCounter;
        this.balance = 0.0;
        this.transactions = new ArrayList<>();
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public double getBalance() {
        return balance;
    }

    public String getUsername() {
        return username;
    }

    public boolean checkPassword(String pass) {
        return password.equals(pass);
    }

    public void deposit(double amount, String note) {
        if (amount > 0) {
            balance += amount;
            transactions.add(new Transaction("Deposit", amount, balance, note));
            System.out.println(" Deposited ₹" + amount + ". New Balance: ₹" + balance);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public boolean withdraw(double amount, String note) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            transactions.add(new Transaction("Withdraw", amount, balance, note));
            System.out.println("Withdrew ₹" + amount + ". New Balance: ₹" + balance);
            return true;
        } else {
            System.out.println(" Insufficient funds or invalid amount.");
            return false;
        }
    }

    public void showTransactions() {
        System.out.println("\n Transaction History for " + accountHolder + " (A/C: " + accountNumber + "):");
        if (transactions.isEmpty()) {
            System.out.println("No transactions yet.");
        } else {
            for (Transaction t : transactions) {
                System.out.println(t);
            }
        }
    }

    public void updateName(String newName) {
        this.accountHolder = newName;
        System.out.println("Name updated successfully!");
    }

    public void updateUsername(String newUsername) {
        this.username = newUsername;
        System.out.println("Username updated successfully!");
    }

    public void updatePassword(String newPassword) {
        this.password = newPassword;
        System.out.println(" Password updated successfully!");
    }
}

public class BankSimulation {

    private static List<Account> accounts = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);
    private static Account loggedInAccount = null;

    public static void main(String[] args) {
        int choice;

        do {
            System.out.println("\n====== BANK MENU ======");
            if (loggedInAccount == null) {
                System.out.println("1. Register New Account");
                System.out.println("2. Login");
                System.out.println("3. Exit");
                System.out.print("Enter choice: ");
                choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {
                    case 1 ->
                        registerAccount();
                    case 2 ->
                        login();
                    case 3 ->{
                        System.out.println(" Thank you for banking with us!");
                        exit(0);}
                    default ->
                        System.out.println("Invalid choice. Try again.");
                }
            } else {
                System.out.println("Welcome, " + loggedInAccount.getAccountHolder());
                System.out.println("1. Deposit Money");
                System.out.println("2. Withdraw Money");
                System.out.println("3. Show Balance");
                System.out.println("4. Show Transaction History");
                System.out.println("5. Transfer Funds");
                System.out.println("6. Update Profile");
                System.out.println("7. Logout");
                System.out.print("Enter choice: ");
                choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {
                    case 1 ->
                        depositMoney();
                    case 2 ->
                        withdrawMoney();
                    case 3 ->
                        showBalance();
                    case 4 ->
                        loggedInAccount.showTransactions();
                    case 5 ->
                        transferFunds();
                    case 6 ->
                        updateProfile();
                    case 7 -> {
                        loggedInAccount = null;
                        System.out.println("Logged out successfully.");
                    }
                    default ->
                        System.out.println("Invalid choice. Try again.");
                }
            }
        } while (true);
    }

    private static void registerAccount() {
        System.out.print("Enter account holder name: ");
        String name = sc.nextLine();
        System.out.print("Choose username: ");
        String username = sc.nextLine();
        System.out.print("Choose password: ");
        String password = sc.nextLine();

        // check if username already exists
        for (Account acc : accounts) {
            if (acc.getUsername().equals(username)) {
                System.out.println("Username already taken. Try another.");
                return;
            }
        }

        Account acc = new Account(name, username, password);
        accounts.add(acc);
        System.out.println("✅ Account created successfully! Account No: " + acc.getAccountNumber());
    }

    private static void login() {
        System.out.print("Enter username: ");
        String username = sc.nextLine();
        System.out.print("Enter password: ");
        String password = sc.nextLine();

        for (Account acc : accounts) {
            if (acc.getUsername().equals(username) && acc.checkPassword(password)) {
                loggedInAccount = acc;
                System.out.println(" Login successful! Welcome " + acc.getAccountHolder());
                return;
            }
        }
        System.out.println(" Invalid username or password.");
    }

    private static void depositMoney() {
        System.out.print("Enter amount to deposit: ");
        double amt = sc.nextDouble();
        loggedInAccount.deposit(amt, "Cash Deposit");
    }

    private static void withdrawMoney() {
        System.out.print("Enter amount to withdraw: ");
        double amt = sc.nextDouble();
        loggedInAccount.withdraw(amt, "Cash Withdrawal");
    }

    private static void showBalance() {
        System.out.println(" Balance: " + loggedInAccount.getBalance());
    }

    private static void transferFunds() {
        System.out.print("Enter recipient account number: ");
        int recipientAccNo = sc.nextInt();
        System.out.print("Enter amount to transfer: ");
        double amt = sc.nextDouble();
        sc.nextLine();

        Account recipient = findAccountByNumber(recipientAccNo);
        if (recipient == null) {
            System.out.println(" Recipient account not found.");
            return;
        }

        if (loggedInAccount.withdraw(amt, "Transfer to A/C " + recipientAccNo)) {
            recipient.deposit(amt, "Transfer from A/C " + loggedInAccount.getAccountNumber());
            System.out.println(" Successfully transferred " + amt + " to " + recipient.getAccountHolder());
        }
    }

    private static void updateProfile() {
        System.out.println("\n--- Update Profile ---");
        System.out.println("1. Change Name");
        System.out.println("2. Change Username");
        System.out.println("3. Change Password");
        System.out.print("Enter choice: ");
        int choice = sc.nextInt();
        sc.nextLine();

        switch (choice) {
            case 1 -> {
                System.out.print("Enter new name: ");
                String newName = sc.nextLine();
                loggedInAccount.updateName(newName);
            }
            case 2 -> {
                System.out.print("Enter new username: ");
                String newUser = sc.nextLine();
                for (Account acc : accounts) {
                    if (acc.getUsername().equals(newUser)) {
                        System.out.println(" Username already taken.");
                        return;
                    }
                }
                loggedInAccount.updateUsername(newUser);
            }
            case 3 -> {
                System.out.print("Enter new password: ");
                String newPass = sc.nextLine();
                loggedInAccount.updatePassword(newPass);
            }
            default ->
                System.out.println("Invalid choice.");
        }
    }

    private static Account findAccountByNumber(int accNo) {
        for (Account acc : accounts) {
            if (acc.getAccountNumber() == accNo) {
                return acc;
            }
        }
        return null;
    }
    
}
