package ca2_hotel_system.employees;
import ca2_hotel_system.departments.Department;
// here im calling the Department class that lives inside the departments package

// here it is my abstract class Employee, the parent of my 3 hotel employees
// abstract = nobody is a plain "employee", only a real one like a Bartender
// "a Bartender IS AN Employee" -> that is why the children will extend Employee
public abstract class Employee {

    // here are my 3 boxes: the person's name, their job title and the department they work in
    // an employee HAS A department (it is a box), it is not a child of Department
    // protected = my child classes can use them too
    protected String name;
    protected String designation;
    protected Department department;

    // this is my constructor, it receives the 3 things and saves each one in its box
    // this.name = my box called name, name = the value that just arrived
    public Employee(String name, String designation, Department department) {
        this.name = name;
        this.designation = designation;
        this.department = department;
    }

    // here are my getters, each one opens one box and gives back what is inside
    public String getName() {
        return name;
    }

    public String getDesignation() {
        return designation;
    }

    public Department getDepartment() {
        return department;
    }
}
