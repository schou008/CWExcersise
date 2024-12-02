import java.util.HashMap;
import java.util.Scanner;

class Record {
    private String firstName;
    private String lastName;
    private int age;

    // Constructor for creating a new record
    public Record(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    // Return a string representation of the record in a readable format
    public String toString() {
        return lastName + ", " + firstName + " " + age;
    }
}

public class RecordManager {
    private static HashMap<Integer, Record> database = new HashMap<>();
    private static int nextId = 1; // The unique ID for each record

    // Run the RecordManager with a given Scanner input
    public static void run(Scanner scanner) {
        String command;

        while (scanner.hasNextLine()) {
            command = scanner.nextLine().trim().toLowerCase();

            switch (command) {
                case "a": // Add a new record
                    addRecord(scanner);
                    break;
                case "r": // Remove a record by ID
                    removeRecord(scanner);
                    break;
                case "l": // List all records
                    listRecords();
                    break;
                case "q": // Quit the application
                    System.out.println("Goodbye!");
                    return;
                case "help": // Show help
                    printHelp();
                    break;
                default: // Invalid command
                    System.out.println("Invalid command. Type 'help' for options.");
            }
        }
    }

    // Method to add a record
    private static void addRecord(Scanner scanner) {
        // Read the next line as a single record input, example: "John Doe 47"
        String input = scanner.nextLine().trim();
        String[] parts = input.split(" ");

        if (parts.length != 3) {
            System.out.println("Invalid format. Please enter as \"firstName lastName age\".");
            return;
        }

        try {
            String firstName = parts[0];
            String lastName = parts[1];
            int age = Integer.parseInt(parts[2]);

            // Add the new record with the unique ID
            database.put(nextId, new Record(firstName, lastName, age));
            System.out.println("Record added with ID " + nextId + ".");
            nextId++; // Increment the ID for the next record
        } catch (NumberFormatException e) {
            System.out.println("Invalid age. Please enter a valid number.");
        }
    }

    // Method to remove a record by ID
    private static void removeRecord(Scanner scanner) {
        // Read the next line as the record ID to remove
        try {
            int id = Integer.parseInt(scanner.nextLine());

            // Check if the record with the given ID exists
            if (database.containsKey(id)) {
                database.remove(id); // Remove the record
                System.out.println("Record removed.");
            } else {
                System.out.println("No record found with ID " + id + ".");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID. Please enter a valid number.");
        }
    }

    // Method to list all records in the database
    private static void listRecords() {
        if (database.isEmpty()) {
            System.out.println("Database is empty.");
        } else {
            // Print each record in the database
            database.forEach((id, record) -> System.out.println(record + " (id " + id + ")"));
        }
    }

    // Method to print help instructions
    private static void printHelp() {
        System.out.println("\nValid commands are:");
        System.out.println("  a   Add record");
        System.out.println("  r   Remove record");
        System.out.println("  l   List records");
        System.out.println("  q   Quit");
        System.out.println("  help   Show help menu");
    }
}
