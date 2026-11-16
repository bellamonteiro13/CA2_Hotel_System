package ca2_hotel_system.managers;
import ca2_hotel_system.departments.Department;
import ca2_hotel_system.enums.ManagerType;

public abstract class Manager {
    //it is abstract because nobody is a "generic" manager
    // we have specific types of manager: General Manager, Department Manager 
    // and Supervisor 
    
    protected String name;
    protected Department department;
    protected ManagerType managerType;
    // protected = so the childen class can use it too
    // String (text) = the name of the employee
    // Department (my class) = is the type and the name is department
    // ManagerType = my enum 

    // the constructor fills the box
    public Manager(String name, Department department, ManagerType managerType) {
        this.name= name;
        this.department = department;
        this.managerType = managerType;
        // each line reads like 
        //ex: my box called name = the value that just arrived called name”
    }

    // the getter opens the box
    // here are my getters
    //each one opens one box and gives back what is inside
       public String getName() {
        return name;
    }

    public Department getDepartment() {
        return department;
    }

    public ManagerType getManagerType() {
        return managerType;
    }
}

