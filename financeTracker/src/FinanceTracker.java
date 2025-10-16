import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FinanceTracker {
    private ArrayList<Transaction> transactions;
    private static final String FILE_NAME = "transactions.txt";

    public FinanceTracker() {
        transactions = new ArrayList<>();
    }

    public void addTransaction(Transaction t) {
        transactions.add(t);
    }

    public boolean deleteTransaction(int id) {
        return transactions.removeIf(t -> t.getId() == id);
    }

    public void viewAllTransactions() {
        if (transactions.isEmpty()) {
            System.out.println("No transactions found.");
            return;
        }

        System.out.println("\n=== All Transactions ===");
        for (Transaction t : transactions) {
            System.out.println(t);
        }
    }

    public void displaySummary() {
        double totalIncome = 0;
        double totalExpense = 0;
        Map<String, Double> categoryExpenses = new HashMap<>();

        for (Transaction t : transactions) {
            if (t.getType().equals("income")) {
                totalIncome += t.getAmount();
            } else {
                totalExpense += t.getAmount();
                categoryExpenses.put(t.getCategory(),
                        categoryExpenses.getOrDefault(t.getCategory(), 0.0) + t.getAmount());
            }
        }

        System.out.println("\n=== Financial Summary ===");
        System.out.printf("Total Income: $%.2f\n", totalIncome);
        System.out.printf("Total Expenses: $%.2f\n", totalExpense);
        System.out.printf("Balance: $%.2f\n", totalIncome - totalExpense);

        System.out.println("\n=== Expenses by Category ===");
        for (Map.Entry<String, Double> entry : categoryExpenses.entrySet()) {
            System.out.printf("%s: $%.2f\n", entry.getKey(), entry.getValue());
        }
    }

    public void saveData() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Transaction t : transactions) {
                writer.write(t.toFileString());
                writer.newLine();
            }
            System.out.println("Data saved successfully!");
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    public void loadData() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            System.out.println("No previous data found. Starting fresh!");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 6) {
                    Transaction t = new Transaction(
                            Integer.parseInt(parts[0]),
                            parts[1],
                            Double.parseDouble(parts[2]),
                            parts[3],
                            parts[4],
                            parts[5]
                    );
                    transactions.add(t);
                }
            }
            System.out.println("Data loaded successfully! Found " + transactions.size() + " transactions.");
        } catch (IOException e) {
            System.out.println("Error loading data: " + e.getMessage());
        }
    }
}