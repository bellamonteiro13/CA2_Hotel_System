package ca2_hotel_system.staff;

import ca2_hotel_system.departments.Department;
import ca2_hotel_system.departments.FoodAndBeverage;
import ca2_hotel_system.departments.FrontOffice;
import ca2_hotel_system.departments.Housekeeping;
import ca2_hotel_system.managers.DepartmentManager;
import ca2_hotel_system.managers.GeneralManager;
import ca2_hotel_system.managers.Supervisor;
import ca2_hotel_system.employees.Bartender;
import ca2_hotel_system.employees.Receptionist;
import ca2_hotel_system.employees.RoomAttendant;
import java.util.ArrayList;
import java.util.Random;

// here it is my class RandomStaffGenerator, it works like the DICE of my hotel 🎲
// its purpose: give each name from the file a random role + department (Step 6)
// a role can be a MANAGER (General Manager, Department Manager, Supervisor)
// or a frontline EMPLOYEE (Receptionist, Room Attendant, Bartender), so my tree has all 4 levels (UC5)
// StaffManager calls it when the file is loaded
public class RandomStaffGenerator {

    // the "dice" of my program: it picks random numbers (Step 6)
    private static Random random = new Random();

    // here it is my method createRandomStaff (Step 6: random data generation)
    // the file only has NAMES, so the program rolls a "dice" to give each person
    // a role and a department (this is the decision i made for UC1)
    // the data is always APPROPRIATE: it only uses my real departments and roles,
    // the hotel can only have ONE General Manager, and there are more employees than managers
    // Receives: name = a valid name from the file
    //           currentStaff = the shelf so far (to check if there is already a General Manager)
    // Gives back: a manager or an employee (both are StaffMembers!)
    public static StaffMember createRandomStaff(String name, ArrayList<StaffMember> currentStaff) {

        // only ONE General Manager in the hotel: 1 in 3 chance, but only if there isnt one yet
        // how i read it: "IF there is NO General Manager yet AND the dice lands on 0, make a GM"
        if (!hasGeneralManager(currentStaff) && random.nextInt(3) == 0) {
            return new GeneralManager(name, randomDepartment());
        }

        // a dice with 10 sides (0 to 9) decides the role, so i can control the chances:
        // 0-1 = Department Manager (20%), 2-4 = Supervisor (30%), 5-9 = frontline Employee (50%)
        // a real hotel has more employees than managers, so the data makes sense
        int roll = random.nextInt(10);
        if (roll <= 1) {
            return new DepartmentManager(name, randomDepartment());
        } else if (roll <= 4) {
            return new Supervisor(name, randomDepartment());
        } else {
            // frontline employee: the job already decides the department
            // (a Receptionist always works in Front Office, a Bartender in Food & Beverage...)
            switch (random.nextInt(3)) {
                case 0:
                    return new Receptionist(name);
                case 1:
                    return new RoomAttendant(name);
                default:
                    return new Bartender(name);
            }
        }
    }

    // here it is my helper randomDepartment, it rolls a dice with 3 sides and builds that department
    // Gives back: a FrontOffice, Housekeeping or FoodAndBeverage
    private static Department randomDepartment() {
        // random.nextInt(3) = a random number 0, 1 or 2 (like a dice with 3 sides)
        switch (random.nextInt(3)) {
            case 0:
                return new FrontOffice();
            case 1:
                return new Housekeeping();
            default:
                return new FoodAndBeverage();
        }
    }

    // checks the shelf: is there already a General Manager?
    // Receives: currentStaff = the shelf to check
    // Gives back: true if yes, false if no
    // public = StaffManager also uses it, to stop a 2nd General Manager in option 4 (Add employee)
    public static boolean hasGeneralManager(ArrayList<StaffMember> currentStaff) {
        for (StaffMember person : currentStaff) {
            if (person instanceof GeneralManager) {
                return true;
            }
        }
        return false;
    }
}
