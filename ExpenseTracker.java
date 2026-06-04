import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;

public class ExpenseTracker {
    static Scanner sc = new Scanner(System.in);
    static String FILE_NAME = "expenses.txt";

    static class Transaction {
        String type;
        double amount;
        String category;

        Transaction(String type, double amount, String category) {
            this.type = type;
            this.amount = amount;
            this.category = category;
        }
    }

    static ArrayList<Transaction> transactions = new ArrayList<>();

    public static void main(String[] args) {
        loadFromFile();

        while (true) {
            System.out.println("\n====== EXPENSE TRACKER ======");
            System.out.println("1. Add Income");
            System.out.println("2. Add Expense");
            System.out.println("3. View All Transactions");
            System.out.println("4. Monthly Summary");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();

            if (choice == 1) {
                addIncome();
            } else if (choice == 2) {
                addExpense();
            } else if (choice == 3) {
                viewTransactions();
            } else if (choice == 4) {
                monthlySummary();
            } else if (choice == 5) {
                saveToFile();
                System.out.println("Data saved. Goodbye!");
                break;
            } else {
                System.out.println("Invalid choice. Try again.");
            }
        }
    }

    static void addIncome() {
        System.out.print("Enter Amount: ");
        double amount = sc.nextDouble();
        transactions.add(new Transaction("INCOME", amount, "Income"));
        System.out.println("Income of Rs." + amount + " added successfully!");
    }

    static void addExpense() {
        System.out.print("Enter Amount: ");
        double amount = sc.nextDouble();

        System.out.println("Select Category:");
        System.out.println("1. Food");
        System.out.println("2. Travel");
        System.out.println("3. Shopping");
        System.out.println("4. Bills");
        System.out.println("5. Other");
        System.out.print("Enter choice: ");

        int catChoice = sc.nextInt();
        String category;

        if (catChoice == 1) category = "Food";
        else if (catChoice == 2) category = "Travel";
        else if (catChoice == 3) category = "Shopping";
        else if (catChoice == 4) category = "Bills";
        else category = "Other";

        transactions.add(new Transaction("EXPENSE", amount, category));
        System.out.println(category + " expense of Rs." + amount + " added successfully!");
    }

    static void viewTransactions() {
        if (transactions.isEmpty()) {
            System.out.println("No transactions yet.");
            return;
        }
        System.out.println("\n====== ALL TRANSACTIONS ======");
        System.out.printf("%-12s %-12s %-10s%n", "TYPE", "CATEGORY", "AMOUNT");
        System.out.println("--------------------------------------");
        for (Transaction t : transactions) {
            System.out.printf("%-12s %-12s Rs.%-10.2f%n", t.type, t.category, t.amount);
        }
        System.out.println("--------------------------------------");
    }

    static void monthlySummary() {
        if (transactions.isEmpty()) {
            System.out.println("No transactions yet.");
            return;
        }

        double totalIncome = 0;
        double totalExpense = 0;

        for (Transaction t : transactions) {
            if (t.type.equals("INCOME")) {
                totalIncome += t.amount;
            } else {
                totalExpense += t.amount;
            }
        }

        double balance = totalIncome - totalExpense;

        System.out.println("\n====== MONTHLY SUMMARY ======");
        System.out.printf("Total Income  : Rs.%.2f%n", totalIncome);
        System.out.printf("Total Expense : Rs.%.2f%n", totalExpense);
        System.out.println("--------------------------------------");
        System.out.printf("Balance       : Rs.%.2f%n", balance);

        if (balance < 0) {
            System.out.println("WARNING: You have exceeded your income!");
        } else {
            System.out.println("You are within budget. Good job!");
        }
    }

    static void saveToFile() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME));
            for (Transaction t : transactions) {
                bw.write(t.type + "," + t.amount + "," + t.category);
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            System.out.println("Error saving data.");
        }
    }

    static void loadFromFile() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(FILE_NAME));
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    transactions.add(new Transaction(parts[0], Double.parseDouble(parts[1]), parts[2]));
                }
            }
            br.close();
            System.out.println("Previous data loaded successfully!");
        } catch (IOException e) {
            System.out.println("No previous data found. Starting fresh!");
        }
    }
}
