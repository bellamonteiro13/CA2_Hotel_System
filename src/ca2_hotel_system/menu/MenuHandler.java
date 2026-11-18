package ca2_hotel_system.menu;

import ca2_hotel_system.enums.MenuOption;
import java.util.Scanner;

// here it is my class MenuHandler, it works like the MENU BOARD of my bar
// its purpose: show the menu options and read the user's choice safely
// why do i need it? so my main only DIRECTS the work and doesnt get huge
// each class has ONE job: this one only talks to the user about the menu
public class MenuHandler {

    // Prints every menu option by looping through the MenuOption enum.
    // If we add a new option to the enum, it appears here automatically.
    public static void displayMenu() {
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
    public static MenuOption readMenuOption(Scanner keyboard) {
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
