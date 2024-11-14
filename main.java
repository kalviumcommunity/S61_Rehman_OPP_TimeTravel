// JournalInterface to follow Dependency Inversion Principle
interface JournalInterface {
    void addEntry(String date, String content);
    void displayAllEntries();
    int getEntryCount();
    static void displayTotalJournalEntries() {}
}

// JournalManager class depends on JournalInterface rather than Journal directly
class JournalManager {
    private JournalInterface journal;
    private User user;

    public JournalManager(User user, JournalInterface journal) {
        this.user = user;
        this.journal = journal;
    }

    public void addJournalEntry(String date, String content) {
        journal.addEntry(date, content);
    }

    public void viewJournalEntries() {
        System.out.println(user.getName() + ":");
        journal.displayAllEntries();
    }

    public static void displayTotalJournalEntries() {
        Journal.displayTotalJournalEntries();
    }
}

// Journal class implements JournalInterface
class Journal implements JournalInterface {
    private String[] entries;
    private int entryCount;
    private static int totalJournalEntries = 0;

    public Journal() {
        this.entries = new String[5];
        this.entryCount = 0;
    }

    public Journal(int size) {
        this.entries = new String[size];
        this.entryCount = 0;
        System.out.println(size);
    }

    public int getEntryCount() {
        return entryCount;
    }

    @Override
    public void addEntry(String date, String content) {
        if (entryCount < entries.length) {
            entries[entryCount] = date + ": " + content;
            entryCount++;
            totalJournalEntries++;
        } else {
            System.out.println("Journal is full!");
        }
    }

    @Override
    public void displayAllEntries() {
        for (int i = 0; i < entryCount; i++) {
            System.out.println(entries[i]);
        }
    }

    public static void displayTotalJournalEntries() {
        System.out.println(totalJournalEntries);
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }
}

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
        this.journalManager = new JournalManager(this, new Journal());
        users[userCount] = this;
        userCount++;
    }

    public User(String userName) {
        super(userName);
        this.journalManager = new JournalManager(this, new Journal());
        users[userCount] = this;
        userCount++;
    }

    public JournalManager getJournalManager() {
        return journalManager;
    }

    public static void displayUserCount() {
        System.out.println(userCount);
    }

    @Override
    public void showUserTypeDetails() {
        System.out.println(name);
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println(this.name);
        super.finalize();
    }
}

class SpecialUser extends User {
    private String specialFeature;

    public SpecialUser(String name, String feature) {
        super(name);
        this.specialFeature = feature;
        System.out.println(feature);
    }

    @Override
    public void showUserTypeDetails() {
        System.out.println(specialFeature);
    }

    public void showSpecialFeature() {
        System.out.println(specialFeature);
    }
}

class PremiumUser extends SpecialUser {
    private double discountRate;

    public PremiumUser(String name, String feature, double discount) {
        super(name, feature);
        this.discountRate = discount;
        System.out.println(discount + "%");
    }

    @Override
    public void showUserTypeDetails() {
        System.out.println(discountRate + "%");
    }

    public void showDiscountRate() {
        System.out.println("Discount Rate: " + discountRate + "%");
    }
}

// New AdminUser class for Liskov Substitution Principle
class AdminUser extends User {
    private String adminLevel;

    public AdminUser(String name, String adminLevel) {
        super(name);
        this.adminLevel = adminLevel;
        System.out.println("AdminUser with level: " + adminLevel);
    }

    @Override
    public void showUserTypeDetails() {
        System.out.println("Admin User with level: " + adminLevel);
    }

    // New functionality specific to AdminUser
    public void viewAllJournalEntries(UserType[] users) {
        for (UserType user : users) {
            if (user instanceof User) {
                System.out.println("Entries for user: " + user.getName());
                ((User) user).getJournalManager().viewJournalEntries();
                System.out.println();
            }
        }
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

        // AdminUser created for Liskov Substitution Principle demonstration
        AdminUser adminUser = new AdminUser("Alex", "Super Admin");
        adminUser.getJournalManager().addJournalEntry("2024-09-06", "Admin user entry.");
        
        UserType[] users = {defaultUser, paramUser, specialUser, premiumUser, adminUser};
        adminUser.viewAllJournalEntries(users); 
        User.displayUserCount();
        JournalManager.displayTotalJournalEntries();
    }
}
