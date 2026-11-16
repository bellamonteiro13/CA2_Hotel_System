package ca2_hotel_system.enums;


// MenuOption lists every option of the main menu.
// Using an enum (instead of plain numbers or Strings) means the menu
// can only ever have these fixed options. Java will not let anyone
// create a MenuOption that does not exist here.
// Each option matches a use case from the SDF document (UC1 to UC5).
public enum MenuOption {

    // Each constant has a label: the text the user sees on the screen
    LOAD_STAFF_FILE("Load staff file"),      // UC1
    SORT("Sort staff list"),                 // UC2
    SEARCH("Search employee"),               // UC3
    ADD_EMPLOYEE("Add employee"),            // UC4
    DISPLAY_HIERARCHY("Display hierarchy"),  // UC5
    EXIT("Exit");

    // The text shown in the menu for this option
    private final String label;

    // Enum constructor: it runs once for each constant above.
    // It is always private, so nobody outside this file can create new options.
    // Receives: label = the text shown to the user 
    // here is basically saying pick up the new text and put in the label 
    MenuOption(String label) {
        this.label = label;
    } 

    // Gives back the text shown to the user for this option
    public String getLabel() {
        return label;
    }

    // Gives back the number the user types to pick this option.
    // ordinal() is the position in the list, starting at 0,
    // so we add 1 to make the menu start at 1 (friendlier for people).
    public int getMenuNumber() {
        return ordinal() + 1;
    }

    // Finds the option that matches the number the user typed.
    // Receives: menuNumber = the number typed by the user
    // Gives back: the matching MenuOption, or null if no option has that number
    public static MenuOption fromMenuNumber(int menuNumber) {
        // values() gives us every option of this enum, in order
        for (MenuOption option : values()) {
            if (option.getMenuNumber() == menuNumber) {
                return option;
            }
        }
        // No option has this number (e.g. the user typed 9)
        return null;
    }
}
