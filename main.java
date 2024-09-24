class User {
    private String name;
    private Journal journal;

    private static int userCount = 0;
    private static User[] users = new User[10];

    public User(String userName) {
        this.name = userName;
        this.journal = new Journal();
        users[userCount] = this;
        userCount++;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Static member function
    public static void addJournalEntry(int userId, String date, String content) {
        if (userId >= 0 && userId < userCount) {
            users[userId].journal.addEntry(date, content);
        } else {
            System.out.println("Invalid user ID!");
        }
    }

    // Static member function 
    public static void viewJournalEntries(int userId) {
        if (userId >= 0 && userId < userCount) {
            System.out.println("Journal Entries for " + users[userId].getName() + ":");
            users[userId].journal.displayAllEntries();
        } else {
            System.out.println("Invalid user ID!");
        }
    }

    // Static member function 
    public static void displayUserCount() {
        System.out.println("Total number of users: " + userCount);
    }
}

class Journal {
    private String[] entries;
    private int entryCount;

    private static int totalJournalEntries = 0;

    public Journal() {
        entries = new String[5];
        entryCount = 0;
    }

    // Getter for entryCount
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

    // Static member function 
    public static void displayTotalJournalEntries() {
        System.out.println("Total journal entries across all users: " + totalJournalEntries);
    }
}

public class Main {
    public static void main(String[] args) {
        new User("Harshith");
        new User("Aarav");

        User.addJournalEntry(0, "2024-08-01", "Started learning Java.");
        User.addJournalEntry(0, "2024-08-02", "Practiced OOP concepts.");
        
        User.addJournalEntry(1, "2024-08-03", "Started learning Python.");
        User.addJournalEntry(1, "2024-08-04", "Explored data structures.");

        User.viewJournalEntries(0);
        System.out.println();
        User.viewJournalEntries(1);

        User.displayUserCount();
        Journal.displayTotalJournalEntries();
    }
}
