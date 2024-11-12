abstract class UserType {
    protected String name;

    public UserType(String name) {
        this.name = name;
    }

    public abstract void showUserTypeDetails();

    public String getName() {
        return name;
    }
}

class User extends UserType {
    private static int userCount = 0;
    private static User[] users = new User[10];
    private JournalManager journalManager;

    public User() {
        super("Unknown User");
        this.journalManager = new JournalManager(this);
        users[userCount] = this;
        userCount++;
        System.out.println("Default constructor called for User.");
    }

    public User(String userName) {
        super(userName);
        this.journalManager = new JournalManager(this);
        users[userCount] = this;
        userCount++;
        System.out.println("Parameterized constructor called for User.");
    }

    public JournalManager getJournalManager() {
        return journalManager;
    }

    public static void displayUserCount() {
        System.out.println("Total number of users: " + userCount);
    }

    @Override
    public void showUserTypeDetails() {
        System.out.println("Standard User: " + name);
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("User object is being deleted: " + this.name);
        super.finalize();
    }
}

class SpecialUser extends User {
    private String specialFeature;

    public SpecialUser(String name, String feature) {
        super(name);
        this.specialFeature = feature;
        System.out.println("SpecialUser with feature: " + feature);
    }

    @Override
    public void showUserTypeDetails() {
        System.out.println("Special User with feature: " + specialFeature);
    }

    public void showSpecialFeature() {
        System.out.println("Special Feature: " + specialFeature);
    }
}

class PremiumUser extends SpecialUser {
    private double discountRate;

    public PremiumUser(String name, String feature, double discount) {
        super(name, feature);
        this.discountRate = discount;
        System.out.println("PremiumUser with discount rate: " + discount + "%");
    }

    @Override
    public void showUserTypeDetails() {
        System.out.println("Premium User with discount rate: " + discountRate + "%");
    }

    public void showDiscountRate() {
        System.out.println("Discount Rate: " + discountRate + "%");
    }
}

// JournalManager class to manage journal entries
class JournalManager {
    private Journal journal;
    private User user;

    public JournalManager(User user) {
        this.user = user;
        this.journal = new Journal();
    }

    public void addJournalEntry(String date, String content) {
        journal.addEntry(date, content);
    }

    public void viewJournalEntries() {
        System.out.println("Journal Entries for " + user.getName() + ":");
        journal.displayAllEntries();
    }

    public static void displayTotalJournalEntries() {
        Journal.displayTotalJournalEntries();
    }
}

class Journal {
    private String[] entries;
    private int entryCount;
    private static int totalJournalEntries = 0;

    public Journal() {
        this.entries = new String[5];
        this.entryCount = 0;
        System.out.println("Default constructor called for Journal.");
    }

    public Journal(int size) {
        this.entries = new String[size];
        this.entryCount = 0;
        System.out.println("Custom-sized Journal created with capacity: " + size);
    }

    public int getEntryCount() {
        return entryCount;
    }

    public void addEntry(String date, String content) {
        if (entryCount < entries.length) {
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
        User defaultUser = new User();
        defaultUser.getJournalManager().addJournalEntry("2024-09-01", "Created using default constructor.");

        User paramUser = new User("Aarav");
        paramUser.getJournalManager().addJournalEntry("2024-09-02", "Created using parameterized constructor.");

        SpecialUser specialUser = new SpecialUser("Riya", "Priority Support");
        specialUser.getJournalManager().addJournalEntry("2024-09-03", "Special user with priority support.");
        specialUser.showSpecialFeature();

        PremiumUser premiumUser = new PremiumUser("Sam", "Priority Support", 15.0);
        premiumUser.getJournalManager().addJournalEntry("2024-09-04", "Premium user with a discount.");
        premiumUser.showDiscountRate();

        Journal customJournal = new Journal(10);
        customJournal.addEntry("2024-09-05", "Custom-sized journal entry.");

        // Displaying details of each user type
        defaultUser.showUserTypeDetails();
        paramUser.showUserTypeDetails();
        specialUser.showUserTypeDetails();
        premiumUser.showUserTypeDetails();

        // Viewing journal entries
        defaultUser.getJournalManager().viewJournalEntries();
        System.out.println();
        paramUser.getJournalManager().viewJournalEntries();
        System.out.println();
        specialUser.getJournalManager().viewJournalEntries();
        System.out.println();
        premiumUser.getJournalManager().viewJournalEntries();

        User.displayUserCount();
        JournalManager.displayTotalJournalEntries();
    }
}
