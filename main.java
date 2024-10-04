class User {
    private String name;
    private Journal journal;

    private static int userCount = 0;
    private static User[] users = new User[10];

    public User() {
        this.name = "Unknown User"; 
        this.journal = new Journal();
        users[userCount] = this;
        userCount++;
        System.out.println("Default constructor called for User.");
    }

    public User(String userName) {
        this.name = userName;
        this.journal = new Journal();
        users[userCount] = this;
        userCount++;
        System.out.println("Parameterized constructor called for User.");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static void addJournalEntry(int userId, String date, String content) {
        if (userId >= 0 && userId < userCount) {
            users[userId].journal.addEntry(date, content);
        } else {
            System.out.println("Invalid user ID!");
        }
    }

    public static void viewJournalEntries(int userId) {
        if (userId >= 0 && userId < userCount) {
            System.out.println("Journal Entries for " + users[userId].getName() + ":");
            users[userId].journal.displayAllEntries();
        } else {
            System.out.println("Invalid user ID!");
        }
    }

    public static void displayUserCount() {
        System.out.println("Total number of users: " + userCount);
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("User object is being deleted: " + this.name);
        super.finalize();
    }
}

class Journal {
    private String[] entries;
    private int entryCount;

    private static int totalJournalEntries = 0;

    public Journal() {
        entries = new String[5];
        entryCount = 0;
        System.out.println("Default constructor called for Journal.");
    }

    public int getEntryCount() {
        return entryCount;
    }

    public void addEntry(String date, String content) {
        if (entryCount < 5) {
            entries[entryCount] = date + ": " + content;
            entryCount++;
            totalJournalEntries++;
        } else {
            System.out.println("Journal is full!");
        }
    }

    public void displayAllEntries() {
        for (int i = 0; i < entryCount; i++) {
            System.out.println(entries[i]);
        }
    }

    public static void displayTotalJournalEntries() {
        System.out.println("Total journal entries across all users: " + totalJournalEntries);
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("Journal object is being deleted.");
        super.finalize();
    }
}

public class Main {
    public static void main(String[] args) {
        // Using default constructor for User
        User defaultUser = new User();
        User.addJournalEntry(0, "2024-09-01", "Created using default constructor.");

        // Using parameterized constructor for User
        User paramUser = new User("Aarav");
        User.addJournalEntry(1, "2024-09-02", "Created using parameterized constructor.");

        User.viewJournalEntries(0);
        System.out.println();
        User.viewJournalEntries(1);

        User.displayUserCount();
        Journal.displayTotalJournalEntries();
    }
}
