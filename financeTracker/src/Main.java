import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        FinanceTracker tracker = new FinanceTracker();
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Personal Finance Tracker ===");
        tracker.loadData();

        while (true) {
            System.out.println("\n1. Add Transaction");
            System.out.println("2. View All Transactions");
            System.out.println("3. View Summary");
            System.out.println("4. Delete Transaction");
            System.out.println("5. Save & Exit");
            System.out.print("Choose option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    addTransaction(tracker, scanner);
                    break;
                case 2:
                    tracker.viewAllTransactions();
                    break;
                case 3:
                    tracker.displaySummary();
                    break;
                case 4:
                    deleteTransaction(tracker, scanner);
                    break;
                case 5:
                    tracker.saveData();
                    System.out.println("Data saved. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option!");
            }
        }
    }

    private static void addTransaction(FinanceTracker tracker, Scanner scanner) {
        System.out.print("Type (income/expense): ");
        String type = scanner.nextLine().toLowerCase();

        System.out.print("Amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Category (Food/Transport/Entertainment/Salary/Other): ");
        String category = scanner.nextLine();

        System.out.print("Description: ");
        String description = scanner.nextLine();

        Transaction t = new Transaction(type, amount, category, description);
        tracker.addTransaction(t);
        System.out.println("Transaction added successfully!");
    }

    private static void deleteTransaction(FinanceTracker tracker, Scanner scanner) {
        System.out.print("Enter transaction ID to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        if (tracker.deleteTransaction(id)) {
            System.out.println("Transaction deleted!");
        } else {
            System.out.println("Transaction not found!");
        }
    }
}