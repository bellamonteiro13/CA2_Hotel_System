package ca2_hotel_system.employees;
import ca2_hotel_system.departments.FoodAndBeverage;



public class Bartender extends Employee {
    // here it is my class Bartender, a real employee of the hotel
    // a Bartender is an Employee = that is why it extends Employee
    
      public Bartender(String name) {
        super(name, "Bartender", new FoodAndBeverage());
        //String name = receives only the person's name 
        //bartender it is the designation 
        //new FoodAndBeverage() = buils a real food & beverage department 
        //that is the reason why the import is FoodAndBeverage 
        //not the department 
    }

}
