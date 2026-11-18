package ca2_hotel_system.staff;

import ca2_hotel_system.algorithms.BinarySearch;
import ca2_hotel_system.algorithms.QuickSort;
import ca2_hotel_system.managers.Manager;
import ca2_hotel_system.utils.InputValidator;
import ca2_hotel_system.utils.StaffFileReader;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

// here it is my class StaffManager, it works like the BAR MANAGER who looks after the shelf
// its purpose: keep the staff list and do all the staff features (load, sort, search...)
// UC1 load the file, UC2 sort, UC3 search (and UC4 add employee in Step 7)
// my main only calls these methods, the real work happens here
public class StaffManager {

    // here it is my shelf behind the bar: the list that keeps all the staff (Manager objects)
    // each manager has a name + a manager type + a department (Step 6: random data)
    // static = there is only ONE shelf for the whole program, every method can use it
    private static ArrayList<Manager> staffList = new ArrayList<>();

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

        // the bouncer checks each line from the delivery, one by one
        for (String line : fileLines) {
            if (InputValidator.isValidName(line)) {
                // valid name: it gets a random manager type + department, then goes on the shelf
                staffList.add(RandomStaffGenerator.createRandomManager(line.trim(), staffList));
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
        
        Manager[] staffToSort = staffList.toArray(new Manager[0]);
        //create a tray of managers called staffToSort
        //staffList.toArray = copy everyone from the shelf onto the tray
        //new Manager[0] = java works out the size by itself,it is only a reference
        // it turns the shelf into a tray 
        // QuickSort works with arrays, so i turn my list (shelf) into an array (tray)
        
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
        Manager[] sortedStaff = staffList.toArray(new Manager[0]);
        //same thing from sort - it turns the shelf into a tray
        // create a tray of managers called sortedStaff
        //staffList.toArray = copy everyone from the shelf onto the tray
        //new Manager[0] = java works out the size by itself,it is only a reference

        // BinarySearch works with arrays, so i turn my list (shelf) into an array (tray)

        Manager foundPerson = BinarySearch.binarySearch(sortedStaff, nameToFind, 0, sortedStaff.length - 1);
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
            System.out.println("Manager Type: " + foundPerson.getManagerType().getLabel());
            System.out.println("Department: " + foundPerson.getDepartment().getName().getLabel());
        }
    }
}
