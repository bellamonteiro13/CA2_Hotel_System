package ca2_hotel_system;

import ca2_hotel_system.algorithms.QuickSort;
import ca2_hotel_system.enums.MenuOption;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileNotFoundException;

// CA2 Hotel Staff Management System
// This is the starting point of the program. It shows the main menu,
// reads the user's choice and calls the right feature, again and again,
// until the user chooses EXIT.
public class CA2_Hotel_System {

    private static ArrayList<String> staffNames = new ArrayList<>();
    // here it is my shelf behind the bar: the list that keeps all the valid staff names
    // static = there is only ONE shelf for the whole program, every method can use it
    
    // Main method: the program starts running here
    public static void main(String[] args) {
        // Scanner reads what the user types on the keyboard
        Scanner keyboard = new Scanner(System.in);

        System.out.println("=========================================");
        System.out.println("   Welcome to the Hotel Staff System!");
        System.out.println("=========================================");

        // here i am declaring my variable menuOption with the name selectedOption
        MenuOption selectedOption;

        // do-while: show the menu at least once, then repeat until EXIT
        do {
            displayMenu(); // 
            selectedOption = readMenuOption(keyboard);

            // switch on the enum: one "case" for each menu option
            switch (selectedOption) {
                case LOAD_STAFF_FILE:
                    loadStaffFile(keyboard);
                    break;
                case SORT:
                    sortStaffList();
                    break;
                case SEARCH:
                    System.out.println("\nSEARCH selected (coming soon!)");
                    break;
                case ADD_EMPLOYEE:
                    System.out.println("\nADD EMPLOYEE selected (coming soon!)");
                    break;
                case DISPLAY_HIERARCHY:
                    System.out.println("\nDISPLAY HIERARCHY selected (coming soon!)");
                    break;
                case EXIT:
                    System.out.println("\nGoodbye! Thank you for using the Hotel Staff System.");
                    break;
            }
        } while (selectedOption != MenuOption.EXIT);
        //while the choice is not exit keep going back to the loop
        //=! menas is not EQUAL to..
        
        keyboard.close();
        //here im adding the keyboard close cause it is a good pratice
        //especially when u are using the arquives such as Applicants_Form.txt
    }

    // Prints every menu option by looping through the MenuOption enum.
    // If we add a new option to the enum, it appears here automatically.
    private static void displayMenu() {
        System.out.println("\nPlease select an option:");
        for (MenuOption option : MenuOption.values()) {
            System.out.println(option.getMenuNumber() + ". " + option.getLabel());
        }
        System.out.print("Your choice: ");
    }

    // Reads the user's menu choice and keeps asking until it is valid.
    // The program never crashes on bad input (NFR5): letters, empty
    // input or numbers outside the menu just show an error message.
    // Receives: keyboard = the Scanner used to read user input
    // Gives back: a valid MenuOption chosen by the user
    private static MenuOption readMenuOption(Scanner keyboard) {
        while (true) {
            // Read the whole line, then remove spaces at the start/end
            String userInput = keyboard.nextLine().trim();

            try {
                // Try to turn the text into a number ("2" -> 2)
                int menuNumber = Integer.parseInt(userInput);

                // Find the enum option that matches this number
                MenuOption chosenOption = MenuOption.fromMenuNumber(menuNumber);

                if (chosenOption != null) {
                    return chosenOption; // valid choice: leave the loop
                }

                // It was a number, but not on the menu (e.g. 9 or -1)
                System.out.print("Invalid option! Please type a number from 1 to "
                        + MenuOption.values().length + ": ");

            } catch (NumberFormatException error) {
                // It was not a number at all (e.g. "banana" or empty)
                System.out.print("That is not a number! Please type a number from 1 to "
                        + MenuOption.values().length + ": ");
            }
        }
    }
            // here it is my method loadStaffFile, it works like receiving the delivery at the bar
            // it asks the file name, opens it, lets only valid names in and counts them (UC1)
            // Receives: keyboard = the Scanner to read what the user types
            
    private static void loadStaffFile(Scanner keyboard) {
        // the box where i keep the lines from the file (still not checked by the bouncer)
        //void = gives nothing back 
        // only puts the names on the shelf
        // it reveices the keyboard so he can read what the user types 
        
    
        ArrayList<String> fileLines;
        // an empty box w a label on it 
        // we declare outside the loop so it still exists after the loops ends 

        // keep asking until the file is found (UC1: re-ask)
        while (true) {
            System.out.print("Please enter the file name: ");
            String fileName = keyboard.nextLine().trim();

            try { 
                // try to open the delivery
                fileLines = StaffFileReader.readLines(fileName);
                break; // it worked! leave the loop
            } catch (FileNotFoundException error) {
                // the file doesnt exist: dont crash, just ask again
                System.out.println("File not found! Please try again.");
            }
        }

        // empty the shelf first, so loading the file twice doesnt repeat the names
        staffNames.clear();

        // the bouncer checks each line from the delivery, one by one
        for (String line : fileLines) {
            if (InputValidator.isValidName(line)) {
                // valid name: it goes on the shelf (without extra spaces)
                staffNames.add(line.trim());
            } else {
                // invalid line: skip it and warn the user (UC1)
                System.out.println("Warning: skipping invalid line -> \"" + line + "\"");
            }
        }

        // show how many staff got in (UC1)
        System.out.println(staffNames.size() + " staff loaded successfully!");
    }

    // here it is my method sortStaffList, it organises the shelf from A to Z (UC2)
    // it uses my recursive QuickSort and then shows only the first 20 names
    private static void sortStaffList() {
        // the shelf is empty: the user needs to load the file first (UC2)
        if (staffNames.isEmpty()) {
            System.out.println("Please load the staff file first (option 1).");
            return;
        }

        // QuickSort works with arrays, so i turn my list (shelf) into an array (tray)
        String[] namesToSort = staffNames.toArray(new String[0]);
        String[] sortedNames = QuickSort.quickSort(namesToSort);

        // put the sorted names back on the shelf, so the list STAYS sorted (needed for the search in Step 5)
        staffNames.clear();
        for (String name : sortedNames) {
            staffNames.add(name);
        }

        // show the first 20, or all of them if there are less than 20 (UC2)
        int amountToShow = Math.min(20, staffNames.size());
        System.out.println("\nStaff sorted A-Z (showing " + amountToShow + " of " + staffNames.size() + "):");
        for (int i = 0; i < amountToShow; i++) {
            System.out.println((i + 1) + ". " + staffNames.get(i));
        }
    }
}
