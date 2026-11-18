package ca2_hotel_system.staff;

import ca2_hotel_system.algorithms.BinarySearch;
import ca2_hotel_system.algorithms.BinaryTree;
import ca2_hotel_system.algorithms.QuickSort;
import ca2_hotel_system.managers.Manager;
import ca2_hotel_system.utils.InputValidator;
import ca2_hotel_system.utils.StaffFileReader;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import ca2_hotel_system.enums.ManagerType;
import ca2_hotel_system.enums.DepartmentName;
import ca2_hotel_system.departments.Department;
import ca2_hotel_system.departments.FoodAndBeverage;
import ca2_hotel_system.departments.FrontOffice;
import ca2_hotel_system.departments.Housekeeping;
import ca2_hotel_system.managers.DepartmentManager;
import ca2_hotel_system.managers.GeneralManager;
import ca2_hotel_system.managers.Supervisor;
import ca2_hotel_system.employees.Employee;


// here it is my class StaffManager, it works like the BAR MANAGER who looks after the shelf
// its purpose: keep the staff list and do all the staff features (load, sort, search...)
// UC1 load the file, UC2 sort, UC3 search (and UC4 add employee in Step 7)
// my main only calls these methods, the real work happens here
public class StaffManager {

    // here it is my shelf behind the bar: the list that keeps all the staff (managers AND employees)
    // each person has a name + a designation + a department (Step 6: random data)
    // static = there is only ONE shelf for the whole program, every method can use it
    private static ArrayList<StaffMember> staffList = new ArrayList<>();

    // here it is my second (small) shelf: ONLY the people added through option 4 (UC4)
    // so i can show "all newly added records", like the brief asks
    // it is NOT cleared when the file is loaded again, it keeps everybody added in this session
    private static ArrayList<StaffMember> newlyAddedStaff = new ArrayList<>();

            // here it is my method loadStaffFile, it works like receiving the delivery at the bar
            // it asks the file name, opens it, lets only valid names in and counts them (UC1)
            // Receives: keyboard = the Scanner to read what the user types
            
    public static void loadStaffFile(Scanner keyboard) {
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

        // FIX: put back the people added through option 4, so they are NOT lost (UC5 needs them in the tree)
        // how i read it: "add everybody from the newly added list back onto the main shelf"
        // i do it BEFORE the file, so the dice knows if one of them is already the General Manager
        staffList.addAll(newlyAddedStaff);

        // the bouncer checks each line from the delivery, one by one
        for (String line : fileLines) {
            if (InputValidator.isValidName(line)) {
                // valid name: it gets a random manager type + department, then goes on the shelf
                staffList.add(RandomStaffGenerator.createRandomStaff(line.trim(), staffList));
            } else {
                // invalid line: skip it and warn the user (UC1)
                System.out.println("Warning: skipping invalid line -> \"" + line + "\"");
            }
        }

        // show how many staff got in (UC1)
        // how i read it: "everybody on the shelf MINUS the ones i put back = the ones from the file"
        System.out.println((staffList.size() - newlyAddedStaff.size()) + " staff loaded successfully!");
        if (!newlyAddedStaff.isEmpty()) {
            System.out.println("(" + newlyAddedStaff.size() + " newly added staff kept on the list)");
        }
    }

    // here it is my method sortStaffList, it organises the shelf from A to Z (UC2)
    // it uses my recursive QuickSort and then shows only the first 20 names
    public static void sortStaffList() {
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
        
        StaffMember[] staffToSort = staffList.toArray(new StaffMember[0]);
        //create a tray of managers called staffToSort
        //staffList.toArray = copy everyone from the shelf onto the tray
        //new Manager[0] = java works out the size by itself,it is only a reference
        // it turns the shelf into a tray 
        // QuickSort works with arrays, so i turn my list (shelf) into an array (tray)
        
        StaffMember[] sortedStaff = QuickSort.quickSort(staffToSort);
       
        // put the sorted staff back on the shelf, so the list STAYS sorted
        staffList.clear();
        for (StaffMember person : sortedStaff) {
            staffList.add(person);
        }
    }

    // here it is my method searchEmployee, it finds a person by name (UC3)
    // and shows their manager type + department
    // Receives: keyboard = the Scanner to read what the user types
    public static void searchEmployee(Scanner keyboard) {
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
        //asks again if the name is empty (UC3)

        // Binary Search ONLY works on a sorted list, so i sort first (just in case)
        sortStaff();

        // start the recursive search on the WHOLE shelf: from position 0 to the last one
        StaffMember[] sortedStaff = staffList.toArray(new StaffMember[0]);
        //same thing from sort - it turns the shelf into a tray
        // create a tray of managers called sortedStaff
        //staffList.toArray = copy everyone from the shelf onto the tray
        //new Manager[0] = java works out the size by itself,it is only a reference

        // BinarySearch works with arrays, so i turn my list (shelf) into an array (tray)

        StaffMember foundPerson = BinarySearch.binarySearch(sortedStaff, nameToFind, 0, sortedStaff.length - 1);
        //BinarySearch.binarySearch(...) = hey binary search look for someone for mee
        //sortedStaff = the tray to search 
        //nameToFind = who the person u are looking for 
        // 0 = start looking at the slot(position) 
        //sortedStaff.length -1 = the slot where u are going to stop (last one)
        
        
        // null = the search didnt find anybody
        if (foundPerson == null) {
            System.out.println("Employee not found: \"" + nameToFind + "\"");
        } else {
            System.out.println("\nEmployee found!");
            System.out.println("Name: " + foundPerson.getName());
            // how i read it: "IF the person is a manager, call it Manager Type, ELSE call it Designation"
            // (getDesignation() gives the manager type for managers and the job title for employees)
            if (foundPerson instanceof Manager) {
                System.out.println("Manager Type: " + foundPerson.getDesignation());
            } else {
                System.out.println("Designation: " + foundPerson.getDesignation());
            }
            System.out.println("Department: " + foundPerson.getDepartment().getName().getLabel());
        }
    }
    
    // here it is my method addEmployee, it adds a new person to the shelf (UC4)
    // the MANAGER chooses the name, the type and the department (not the dice!)
    // Receives: keyboard = the Scanner to read what the user types
    public static void addEmployee(Scanner keyboard) {

        // ask for the name, and ask again if the bouncer says no (UC4)
        // how i read it: "create an empty box called newName, it will hold text"
        String newName;
        do {
            System.out.print("Please enter the new employee's name: ");
            // how i read it: "read what the user typed, cut the spaces, keep it in newName"
            newName = keyboard.nextLine().trim();

            // how i read it: "IF the bouncer says this name is NOT valid, show the error"
            if (!InputValidator.isValidName(newName)) {
                System.out.println("Invalid name! Use only letters, spaces, ' or -. Please try again.");
            }
        // how i read it: "go back to the start WHILE the name is NOT valid"
        // the if and the while ask the SAME question, so they both have the !
        } while (!InputValidator.isValidName(newName));

        // show the manager types from my enum (like the main menu)
        System.out.println("Choose the manager type:");
        //  "for EACH type in my ManagerType enum, print its number and its label"
        // ex: 1. General Manager / 2. Department Manager / 3. Supervisor
        for (ManagerType type : ManagerType.values()) {
            System.out.println(type.getMenuNumber() + ". " + type.getLabel());
        }
        System.out.print("Your choice: ");

        // read a valid number (1 to 3) using my helper
        //  "ask my helper for a number from 1 up to how many types there are (3),
        //                 and keep the answer in typeNumber"
        // FIX: the hotel can only have ONE General Manager (same rule as the dice)
        // so i keep asking until the type is allowed
        ManagerType chosenType;
        while (true) {
            int typeNumber = readNumberInRange(keyboard, ManagerType.values().length);

            // turn the number into the enum: slot 0 = General Manager, 1 = Department Manager, 2 = Supervisor
            // "from the list of types, take the one in slot (typeNumber - 1)"
            // the user types 1, 2 or 3 but the slots start at 0, so i do - 1 (typed 1 -> slot 0)
            chosenType = ManagerType.values()[typeNumber - 1];

            // how i read it: "IF they chose General Manager AND the shelf already has one, say no and ask again"
            if (chosenType == ManagerType.GENERAL_MANAGER && RandomStaffGenerator.hasGeneralManager(staffList)) {
                System.out.print("The hotel already has a General Manager! Please choose another type: ");
            } else {
                // the type is allowed: leave the loop
                break;
            }
        }

        // show the departments from my enum (like the manager types)
        System.out.println("Choose the department:");
        
        // how i read it: "for EACH department in my DepartmentName enum, print its number and its label"
        // ex: 1. Front Office / 2. Housekeeping / 3. Food & Beverage
        // Departmentname = the enum (the full list of departments
        // department = the loop variable(one department at a time)
        // departmentNumber = the number the user typed 
        
        for (DepartmentName department : DepartmentName.values()) {
            System.out.println(department.getMenuNumber() + ". " + department.getLabel());
        }
        System.out.print("Your choice: ");

        // how i read it: "ask my helper for a number from 1 up to how many departments there are (3)"
        int departmentNumber = readNumberInRange(keyboard, DepartmentName.values().length);

        // how i read it: "from the list of departments, take the one in slot (departmentNumber - 1)"
        DepartmentName chosenDepartment = DepartmentName.values()[departmentNumber - 1];

        // PART D: build the real person and put them on the shelf
        // until now i only have LABELS (like an order slip), now i build the real thing with new
        // (the same thing the dice does in RandomStaffGenerator)

        // turn the department LABEL (enum) into a real Department object
        // how i read it: "look at the chosen department and build the matching room with new"
        Department newDepartment;
        switch (chosenDepartment) {
            case FRONT_OFFICE:
                newDepartment = new FrontOffice();
                break;
            case HOUSEKEEPING:
                newDepartment = new Housekeeping();
                break;
            default:
                // the only one left is FOOD_BEVERAGE
                newDepartment = new FoodAndBeverage();
                break;
        }

        // turn the chosen type into a real person (object)
        // how i read it: "look at the chosen type and build the matching manager with new,
        //                 giving them the name and the department"
        Manager newPerson;
        switch (chosenType) {
            case GENERAL_MANAGER:
                newPerson = new GeneralManager(newName, newDepartment);
                break;
            case DEPARTMENT_MANAGER:
                newPerson = new DepartmentManager(newName, newDepartment);
                break;
            default:
                // the only one left is SUPERVISOR
                newPerson = new Supervisor(newName, newDepartment);
                break;
        }

        // how i read it: "put the new person on the shelf, then sort the shelf A to Z again"
        // i sort again so the Binary Search can still find everybody (it needs a sorted list!)
        staffList.add(newPerson);
        sortStaff();

        // success message (UC4)
        // \" = a quote INSIDE the text, so the names appear between "quotes"
        System.out.println("\"" + newName + "\" has been added as \"" + chosenType.getLabel()
                + "\" to \"" + chosenDepartment.getLabel() + "\" successfully!");

        // PART E: remember this person on my second shelf, then show ALL the new people (UC4)
        // how i read it: "add the new person to the newly added list too"
        newlyAddedStaff.add(newPerson);

        // how i read it: "for EACH person on the newly added list, print their number and details"
        // (the details come from my toString in Manager: name | type | department)
        System.out.println("\nNewly added staff (" + newlyAddedStaff.size() + "):");
        for (int i = 0; i < newlyAddedStaff.size(); i++) {
            System.out.println((i + 1) + ". " + newlyAddedStaff.get(i));
        }
    }

    // here it is my method displayHierarchy, it builds the hotel tree and shows it (UC5)
    // order: General Manager -> Department Managers -> Supervisors -> Employees
    // it includes the people added through option 4, because they are on the shelf too
    public static void displayHierarchy() {
        // the shelf is empty: the user needs to load the file first
        if (staffList.isEmpty()) {
            System.out.println("Please load the staff file first (option 1).");
            return;
        }

        // a NEW empty tree, big enough for everybody on the shelf
        BinaryTree hierarchy = new BinaryTree(staffList.size());

        // insert by RANK, so the most senior people go to the top levels
        // how i read it: "go through the shelf 4 times: first the GM, then the Department Managers,
        //                 then the Supervisors, then the Employees, and insert each one in level order"
        for (StaffMember person : staffList) {
            if (person instanceof GeneralManager) {
                hierarchy.insert(person);
            }
        }
        for (StaffMember person : staffList) {
            if (person instanceof DepartmentManager) {
                hierarchy.insert(person);
            }
        }
        for (StaffMember person : staffList) {
            if (person instanceof Supervisor) {
                hierarchy.insert(person);
            }
        }
        for (StaffMember person : staffList) {
            if (person instanceof Employee) {
                hierarchy.insert(person);
            }
        }

        // show the tree level by level (BFS), then the height and the number of nodes
        System.out.println("\n=== Hotel Staff Hierarchy (level order) ===");
        hierarchy.displayLevelOrder();
        System.out.println("\nTree height (number of levels): " + hierarchy.getHeight());
        System.out.println("Total number of staff (nodes): " + hierarchy.countNodes());
    }

    // here it is my helper readNumberInRange, it reads a number between 1 and max
    // and asks again if the user types letters or a number outside the list (NFR5)
    // Receives: keyboard = the Scanner, max = the biggest number allowed
    // Gives back: a valid number between 1 and max
    // i use it TWICE: for the manager type and for the department (write once, use everywhere!)
    private static int readNumberInRange(Scanner keyboard, int max) {
        // how i read it: "keep going forever... until a return gets me out"
        while (true) {
            String userInput = keyboard.nextLine().trim();
            try {
                // how i read it: "TRY to turn the text into a number ("2" -> 2)"
                int number = Integer.parseInt(userInput);
                // how i read it: "IF the number is at least 1 AND at most max, it is valid: give it back"
                // i use max (not 3) so this helper works for ANY list size
                if (number >= 1 && number <= max) {
                    return number;
                }
                // it was a number, but outside the list (ex: 9)
                System.out.print("Invalid option! Please type a number from 1 to " + max + ": ");
            } catch (NumberFormatException error) {
                // how i read it: "if the text was NOT a number (ex: banana), CATCH the error and dont crash"
                System.out.print("That is not a number! Please type a number from 1 to " + max + ": ");
            }
        }
    }
}
