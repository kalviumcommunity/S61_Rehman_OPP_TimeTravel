class User {
    private String name;
    private Journal journal;

    public User(String userName) {
        name = userName;
        journal = new Journal();
    }

    public void addJournalEntry(String date, String content) {
        journal.addEntry(date, content);
    }

    public void viewJournalEntries() {
        System.out.println("Journal Entries for " + name + ":");
        journal.displayAllEntries();
    }
}

class Journal {
    private String[] entries;
    private int entryCount;

    public Journal() {
        entries = new String[5];
        entryCount = 0;
    }

    public void addEntry(String date, String content) {
        if (entryCount < 5) {
            entries[entryCount] = date + ": " + content;
            entryCount++;
        } else {
            System.out.println("Journal is full!");
        }
    }

    public void displayAllEntries() {
        for (int i = 0; i < entryCount; i++) {
            System.out.println(entries[i]);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        User user = new User("Harshith");

        user.addJournalEntry("2024-08-01", "Started learning Java.");
        user.addJournalEntry("2024-08-02", "Practiced OOP concepts.");

        user.viewJournalEntries();
    }
}
