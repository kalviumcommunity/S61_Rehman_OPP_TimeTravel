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

   
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        // System.out.println("User object is being destroyed");
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

    
    protected void finalize() throws Throwable {
        super.finalize();
        // System.out.println("Journal object is being destroyed");
    }
}

public class Main {
    public static void main(String[] args) {
        
      
        User[] users = new User[2]; 
        users[0] = new User("Harshith"); 
        users[1] = new User("Aarav"); 

        users[0].addJournalEntry("2024-08-01", "Started learning Java.");
        users[0].addJournalEntry("2024-08-02", "Practiced OOP concepts.");
        
        users[1].addJournalEntry("2024-08-03", "Started learning Python.");
        users[1].addJournalEntry("2024-08-04", "Explored data structures.");

        for (User user : users) {
            user.viewJournalEntries();
            System.out.println();
        }
    }
}
