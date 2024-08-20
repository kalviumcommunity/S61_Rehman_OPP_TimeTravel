class User {
    String name;
    Journal journal;

    public User(String userName) {
        this.name = userName;
        this.journal = new Journal();
    }

    public void addJournalEntry(String date, String content) {
        this.journal.addEntry(date, content);
    }

    public void viewJournalEntries() {
        System.out.println("Journal Entries for " + this.name + ":");
        this.journal.displayAllEntries();
    }
}

class Journal {
    String[] entries;
    int entryCount;

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
