import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
    private static int idCounter = 1;
    private int id;
    private String type; // "income" or "expense"
    private double amount;
    private String category;
    private String description;
    private LocalDateTime dateTime;

    public Transaction(String type, double amount, String category, String description) {
        this.id = idCounter++;
        this.type = type;
        this.amount = amount;
        this.category = category;
        this.description = description;
        this.dateTime = LocalDateTime.now();
    }

    // Constructor for loading from file
    public Transaction(int id, String type, double amount, String category,
                       String description, String dateTimeStr) {
        this.id = id;
        this.type = type;
        this.amount = amount;
        this.category = category;
        this.description = description;
        this.dateTime = LocalDateTime.parse(dateTimeStr);
        if (id >= idCounter) {
            idCounter = id + 1;
        }
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String toFileString() {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        return id + "," + type + "," + amount + "," + category + "," +
                description + "," + dateTime.format(formatter);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return String.format("ID: %d | %s | $%.2f | %s | %s | %s",
                id, type.toUpperCase(), amount, category,
                description, dateTime.format(formatter));
    }
}