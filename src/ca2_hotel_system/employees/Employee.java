package ca2_hotel_system.employees;
import ca2_hotel_system.departments.Department;
// here im calling the Department class that lives inside the departments package
import ca2_hotel_system.staff.StaffMember;

// here it is my abstract class Employee, the parent of my 3 hotel employees
// abstract = nobody is a plain "employee", only a real one like a Bartender
// "a Bartender IS AN Employee" -> that is why the children will extend Employee
// extends StaffMember = an Employee IS A StaffMember (it inherits the name + department boxes)
public abstract class Employee extends StaffMember {

    // here is my box for the person's job title
    // the name and the department boxes now live in my parent StaffMember
    // an employee HAS A department (it is a box), it is not a child of Department
    // protected = my child classes can use them too
    protected String designation;

    // this is my constructor, it receives the 3 things and saves each one in its box
    // super = i ask my parent StaffMember to fill the name and department boxes
    // this.designation = my box called designation, designation = the value that just arrived
    public Employee(String name, String designation, Department department) {
        super(name, department);
        this.designation = designation;
    }

    // here is my getter, it opens the box and gives back what is inside
    // @Override = this is my version of the getDesignation that StaffMember asks for
    @Override
    public String getDesignation() {
        return designation;
    }
}
