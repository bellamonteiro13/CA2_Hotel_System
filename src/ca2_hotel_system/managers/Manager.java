package ca2_hotel_system.managers;
import ca2_hotel_system.departments.Department;
import ca2_hotel_system.enums.ManagerType;
import ca2_hotel_system.staff.StaffMember;

// extends StaffMember = a Manager IS A StaffMember (it inherits the name + department boxes)
public abstract class Manager extends StaffMember {
    //it is abstract because nobody is a "generic" manager
    // we have specific types of manager: General Manager, Department Manager 
    // and Supervisor 
    
    protected ManagerType managerType;
    // protected = so the childen class can use it too
    // ManagerType = my enum 
    // the name and the department boxes now live in my parent StaffMember

    // the constructor fills the box
    public Manager(String name, Department department, ManagerType managerType) {
        // super = i ask my parent StaffMember to fill the name and department boxes
        super(name, department);
        this.managerType = managerType;
        // each line reads like 
        //ex: my box called managerType = the value that just arrived called managerType
    }

    // the getter opens the box
    public ManagerType getManagerType() {
        return managerType;
    }

    // my version of getDesignation: for a manager, the designation is the manager type label
    // ex: "Supervisor"
    @Override
    public String getDesignation() {
        return managerType.getLabel();
    }
}
