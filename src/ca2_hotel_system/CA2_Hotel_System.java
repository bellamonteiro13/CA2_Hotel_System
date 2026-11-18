package ca2_hotel_system;

import ca2_hotel_system.enums.MenuOption;
import ca2_hotel_system.menu.MenuHandler;
import ca2_hotel_system.staff.StaffManager;
import java.util.Scanner;

// CA2 Hotel Staff Management System
// This is the starting point of the program. It shows the main menu,
// reads the user's choice and calls the right feature, again and again,
// until the user chooses EXIT.
// my main works like the RECEPTIONIST of the hotel: it doesnt do the work itself,
// it only sends each option to the right "department" (class)
//   menu  -> MenuHandler   (shows the menu + reads the choice)
//   staff -> StaffManager  (load, sort, search the staff)
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
            MenuHandler.displayMenu();
            selectedOption = MenuHandler.readMenuOption(keyboard);

            // switch on the enum: one "case" for each menu option
            switch (selectedOption) {
                case LOAD_STAFF_FILE:
                    StaffManager.loadStaffFile(keyboard);
                    break;
                case SORT:
                    StaffManager.sortStaffList();
                    break;
                case SEARCH:
                    StaffManager.searchEmployee(keyboard);
                    break;
                case ADD_EMPLOYEE:
                    StaffManager.addEmployee(keyboard);
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
}
