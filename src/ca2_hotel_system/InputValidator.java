package ca2_hotel_system;

// here it is my class InputValidator, it works like the BOUNCER at the door of my system
// its purpose: check every name BEFORE it gets in, so only real names go into my staff list
// why do i need it? because names come from 2 places that i dont control:
//   1) the Applicants_Form.txt file, which can have empty lines or names with numbers (UC1)
//   2) the user typing a new name in "Add employee" (UC4, Step 7)
// i keep the checking in ONE place, so both of them use the same rules (no repeated code!)
// this protects my data and makes sure the program doesnt crash (FR2 + NFR5 in my SDF)
public class InputValidator {
    
//boolean because there is only True or False
// like a bouncer: you are in or out
// name.trim = its out if after removing the spaces it is empty 
//charArray = splits the name into single characters
// Bella becomes = 'B', 'e','l','l','a'
// for (char letter: ...) = check each character 
//Character.isLetter = is asking if this character really a letter??
//'\'' - means aphosthophe cause ' its special in Java
//the checking with the special characters specially apostrophe and - its
// really important for irish names for ex 
// if = if it’s not a letter &&(AND) not a space &&(AND) not ' AND not -” → out!
//return true
    
    public static boolean isValidName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return false;
        }

        for (char letter : name.trim().toCharArray()) {
            if (!Character.isLetter(letter) && letter != ' ' && letter != '\'' && letter != '-') {
                return false;
            }
        }

        return true;
    }
}

