package ca2_hotel_system.managers;
import ca2_hotel_system.departments.Department;
import ca2_hotel_system.enums.ManagerType;

// here it is my class DepartmentManager, the manager in charge of ONE department
// (like the manager of Housekeeping or of Food & Beverage)
// in the hotel hierarchy it sits in the middle: under the General Manager, above the Supervisors
// but in the code it is just a "sister" of them, because all 3 are children of Manager
// "a Department Manager IS A Manager" -> that is why it extends Manager
public class DepartmentManager extends Manager {

    // my constructor needs 2 things from outside: who the person is (name)
    // and which department they run (department), because every department manager is different
    public DepartmentManager(String name, Department department) {
        super(name, department, ManagerType.DEPARTMENT_MANAGER);
        // super(...) sends the 3 boxes up to my parent Manager to be filled:
        // name and department I just pass along, and DEPARTMENT_MANAGER I add myself,
        // because a DepartmentManager is always a department manager
    }

}
