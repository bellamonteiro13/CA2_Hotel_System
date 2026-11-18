package ca2_hotel_system.staff;

import ca2_hotel_system.departments.Department;
import ca2_hotel_system.departments.FoodAndBeverage;
import ca2_hotel_system.departments.FrontOffice;
import ca2_hotel_system.departments.Housekeeping;
import ca2_hotel_system.managers.DepartmentManager;
import ca2_hotel_system.managers.GeneralManager;
import ca2_hotel_system.managers.Manager;
import ca2_hotel_system.managers.Supervisor;
import java.util.ArrayList;
import java.util.Random;

// here it is my class RandomStaffGenerator, it works like the DICE of my hotel 🎲
// its purpose: give each name from the file a random manager type + department (Step 6)
// StaffManager calls it when the file is loaded
public class RandomStaffGenerator {

    // the "dice" of my program: it picks random numbers (Step 6)
    private static Random random = new Random();

    // here it is my method createRandomManager (Step 6: random data generation)
    // the file only has NAMES, so the program rolls a "dice" to give each person
    // a manager type and a department (this is the decision i made for UC1)
    // the data is always APPROPRIATE: it only uses the 3 real departments and the 3 real
    // manager types from my enums, and the hotel can only have ONE General Manager
    // Receives: name = a valid name from the file
    //           currentStaff = the shelf so far (to check if there is already a General Manager)
    // Gives back: a GeneralManager, DepartmentManager or Supervisor with a random department
    public static Manager createRandomManager(String name, ArrayList<Manager> currentStaff) {
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
        if (!hasGeneralManager(currentStaff) && random.nextInt(3) == 0) {
            return new GeneralManager(name, department);
        } else if (random.nextInt(2) == 0) {
            return new DepartmentManager(name, department);
        } else {
            return new Supervisor(name, department);
        }
    }

    // checks the shelf: is there already a General Manager?
    // Receives: currentStaff = the shelf to check
    // Gives back: true if yes, false if no
    private static boolean hasGeneralManager(ArrayList<Manager> currentStaff) {
        for (Manager manager : currentStaff) {
            if (manager instanceof GeneralManager) {
                return true;
            }
        }
        return false;
    }
}
