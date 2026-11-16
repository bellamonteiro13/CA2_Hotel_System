package ca2_hotel_system;

import ca2_hotel_system.enums.MenuOption;
import java.util.Scanner;

// CA2 Hotel Staff Management System
// This is the starting point of the program. It shows the main menu,
// reads the user's choice and calls the right feature, again and again,
// until the user chooses EXIT.
public class CA2_Hotel_System {

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
                    System.out.println("\nLOAD STAFF FILE selected (coming soon!)");
                    break;
                case SORT:
                    System.out.println("\nSORT selected (coming soon!)");
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
}
