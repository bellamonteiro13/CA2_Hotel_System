package ca2_hotel_system.staff;

import ca2_hotel_system.departments.Department;

// here it is my class StaffMember, the "grandparent" of EVERYBODY who works in my hotel
// abstract = nobody is a plain "staff member", only a real one (a Supervisor, a Bartender...)
// why do i need it? my shelf must hold managers AND frontline employees together
// "a Manager IS A StaffMember" and "an Employee IS A StaffMember"
// so Manager and Employee both extend StaffMember, and the shelf is a list of StaffMember
//
//                      StaffMember
//                    /             \
//              Manager               Employee
//        /        |        \       /     |     \
//   General  Department  Super-  Recep- Room   Bar-
//   Manager   Manager    visor   tionist Attendant tender
public abstract class StaffMember {

    // the 2 boxes that EVERY staff member has: a name and a department
    // protected = my child classes can use them too
    protected String name;
    protected Department department;

    // the constructor fills the 2 boxes
    public StaffMember(String name, Department department) {
        this.name = name;
        this.department = department;
    }

    // here are my getters, each one opens one box and gives back what is inside
    public String getName() {
        return name;
    }

    public Department getDepartment() {
        return department;
    }

    // abstract method = every child MUST write its own version
    // a Manager gives back its manager type ("Supervisor")
    // an Employee gives back its job title ("Bartender")
    public abstract String getDesignation();

    // here it is my toString, it decides how a staff member looks when i print it
    // @Override = every class in Java already has a toString (from the class Object)
    // but the original one prints something ugly like "Supervisor@1b6d3586"
    // so i OVERRIDE it (i replace it with my own version) = METHOD OVERRIDING
    // it works for managers AND employees, because getDesignation() is different in each one
    // ex: Aoife Murphy | Supervisor | Front Office
    //     Liam Byrne | Bartender | Food & Beverage
    @Override
    public String toString() {
        return name + " | " + getDesignation() + " | " + department.getName().getLabel();
    }
}
