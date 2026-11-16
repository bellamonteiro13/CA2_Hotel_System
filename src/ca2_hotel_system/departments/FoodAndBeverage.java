package ca2_hotel_system.departments;
import ca2_hotel_system.enums.DepartmentName;

public class FoodAndBeverage extends Department {
    // here it is my class FoodAndBeverage, a real department of the hotel
    // extends Department = it is a child of Department
    // so it inherits the name box and getName()

     public FoodAndBeverage() {
        super(DepartmentName.FOOD_BEVERAGE);
        // the constructor = FoodAndBeverage; same as the class name
        // () empty: it doesnt need to receive anything
        // it already knows it is FOOD_BEVERAGE
        // super(...) = works as a child asking for a parent to do something for her
        // here it is basically saying: parent, please save FOOD_BEVERAGE in my box
     }
}
