package ca2_hotel_system.employees;
import ca2_hotel_system.departments.FrontOffice;

public class Receptionist extends Employee {
     // here it is my class Receptionist, a real employee of the hotel
    // a Receptionist is an Employee = that is why it extends Employee
     
    public Receptionist(String name){
        super(name,"Receptionist", new FrontOffice());
    }
        //String name = receives only the person's name 
        //receptionist it is the designation 
        //new FrontOffice() = buils a real front office department 
        //that is the reason why the import is FrontOffice 
        //not the department 
}
