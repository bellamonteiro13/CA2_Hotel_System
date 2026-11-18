package ca2_hotel_system;

import ca2_hotel_system.algorithms.BinarySearch;
import ca2_hotel_system.algorithms.QuickSort;
import ca2_hotel_system.departments.Department;
import ca2_hotel_system.departments.FoodAndBeverage;
import ca2_hotel_system.departments.FrontOffice;
import ca2_hotel_system.departments.Housekeeping;
import ca2_hotel_system.managers.DepartmentManager;
import ca2_hotel_system.managers.GeneralManager;
import ca2_hotel_system.managers.Manager;
import ca2_hotel_system.managers.Supervisor;
import ca2_hotel_system.enums.MenuOption;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.util.Random;

// CA2 Hotel Staff Management System
// This is the starting point of the program. It shows the main menu,
// reads the user's choice and calls the right feature, again and again,
// until the user chooses EXIT.
public class CA2_Hotel_System {

    // here it is my shelf behind the bar: the list that keeps all the staff (Manager objects)
    // each manager has a name + a manager type + a department (Step 6: random data)
    // static = there is only ONE shelf for the whole program, every method can use it
    private static ArrayList<Manager> staffList = new ArrayList<>();

    // the "dice" of my program: it picks random numbers (Step 6)
    private static Random random = new Random();
    
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
                    searchEmployee(keyboard);
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
        staffList.clear();

        // the bouncer checks each line from the delivery, one by one
        for (String line : fileLines) {
            if (InputValidator.isValidName(line)) {
                // valid name: it gets a random manager type + department, then goes on the shelf
                staffList.add(createRandomManager(line.trim()));
            } else {
                // invalid line: skip it and warn the user (UC1)
                System.out.println("Warning: skipping invalid line -> \"" + line + "\"");
            }
        }

        // show how many staff got in (UC1)
        System.out.println(staffList.size() + " staff loaded successfully!");
    }

    // here it is my method sortStaffList, it organises the shelf from A to Z (UC2)
    // it uses my recursive QuickSort and then shows only the first 20 names
    private static void sortStaffList() {
        // the shelf is empty: the user needs to load the file first (UC2)
        if (staffList.isEmpty()) {
            System.out.println("Please load the staff file first (option 1).");
            return;
        }

        // organise the shelf A to Z (the helper method below does the work)
        sortStaff();

        // show the first 20, or all of them if there are less than 20 (UC2)
        int amountToShow = Math.min(20, staffList.size());
        System.out.println("\nStaff sorted A-Z (showing " + amountToShow + " of " + staffList.size() + "):");
        for (int i = 0; i < amountToShow; i++) {
            System.out.println((i + 1) + ". " + staffList.get(i));
        }
    }

    // here it is my helper sortStaff, it puts the shelf in A to Z order WITHOUT printing
    // both the Sort option (UC2) and the Search option (UC3) use it, so i dont repeat code
    private static void sortStaff() {
        // QuickSort works with arrays, so i turn my list (shelf) into an array (tray)
        Manager[] staffToSort = staffList.toArray(new Manager[0]);
        Manager[] sortedStaff = QuickSort.quickSort(staffToSort);

        // put the sorted staff back on the shelf, so the list STAYS sorted
        staffList.clear();
        for (Manager manager : sortedStaff) {
            staffList.add(manager);
        }
    }

    // here it is my method searchEmployee, it finds a person by name (UC3)
    // and shows their manager type + department
    // Receives: keyboard = the Scanner to read what the user types
    private static void searchEmployee(Scanner keyboard) {
        // the shelf is empty: the user needs to load the file first (UC3)
        if (staffList.isEmpty()) {
            System.out.println("Please load the staff file first (option 1).");
            return;
        }

        // ask for the name, and ask again if it is empty (UC3)
        String nameToFind;
        do {
            System.out.print("Please enter the name to search: ");
            nameToFind = keyboard.nextLine().trim();
            if (nameToFind.isEmpty()) {
                System.out.println("The name can't be empty! Please try again.");
            }
        } while (nameToFind.isEmpty());

        // Binary Search ONLY works on a sorted list, so i sort first (just in case)
        sortStaff();

        // start the recursive search on the WHOLE shelf: from position 0 to the last one
        Manager[] sortedStaff = staffList.toArray(new Manager[0]);
        Manager foundPerson = BinarySearch.binarySearch(sortedStaff, nameToFind, 0, sortedStaff.length - 1);

        // null = the search didnt find anybody
        if (foundPerson == null) {
            System.out.println("Employee not found: \"" + nameToFind + "\"");
        } else {
            System.out.println("\nEmployee found!");
            System.out.println("Name: " + foundPerson.getName());
            System.out.println("Manager Type: " + foundPerson.getManagerType().getLabel());
            System.out.println("Department: " + foundPerson.getDepartment().getName().getLabel());
        }
    }

    // here it is my method createRandomManager (Step 6: random data generation)
    // the file only has NAMES, so the program rolls a "dice" to give each person
    // a manager type and a department (this is the decision i made for UC1)
    // the data is always APPROPRIATE: it only uses the 3 real departments and the 3 real
    // manager types from my enums, and the hotel can only have ONE General Manager
    // Receives: name = a valid name from the file
    // Gives back: a GeneralManager, DepartmentManager or Supervisor with a random department
    private static Manager createRandomManager(String name) {
        // random.nextInt(3) = a random number 0, 1 or 2 (like a dice with 3 sides)
        Department department;
        switch (random.nextInt(3)) {
            case 0:
                department = new FrontOffice();
                break;
            case 1:
                department = new Housekeeping();
                break;
            default:
                department = new FoodAndBeverage();
                break;
        }

        // only ONE General Manager in the hotel: if there is already one,
        // the dice only picks between Department Manager and Supervisor
        if (!hasGeneralManager() && random.nextInt(3) == 0) {
            return new GeneralManager(name, department);
        } else if (random.nextInt(2) == 0) {
            return new DepartmentManager(name, department);
        } else {
            return new Supervisor(name, department);
        }
    }

    // checks the shelf: is there already a General Manager?
    // Gives back: true if yes, false if no
    private static boolean hasGeneralManager() {
        for (Manager manager : staffList) {
            if (manager instanceof GeneralManager) {
                return true;
            }
        }
        return false;
    }
}
