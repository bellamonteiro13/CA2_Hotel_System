package ca2_hotel_system.managers;
import ca2_hotel_system.departments.Department;
import ca2_hotel_system.enums.ManagerType;

public class GeneralManager extends Manager {
    // here it is my class GeneralManager, a real type of manager in the hotel
    // extends Manager = it is a child of Manager
    // so it inherits the 3 boxes and the 3 getters

    public GeneralManager(String name, Department department) {
        super(name, department, ManagerType.GENERAL_MANAGER);
        // GeneralManager = constructor w the same name as the class
        // it receives the name and the department of the person
        // () = the parameters here it receives 02 things: type+name, type+name
        // (it changes from person to person)
        // super = again the child is asking for the parent to do something for her
        // () = after the super its just the 02 things received,
        // we're just passing along to the parent
        // ManagerType.GENERAL_MANAGER = the child already knows its a general manager
    }

}
