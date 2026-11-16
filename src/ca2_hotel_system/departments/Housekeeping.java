package ca2_hotel_system.departments;
import ca2_hotel_system.enums.DepartmentName;

public class Housekeeping extends Department {
    // here it is my class Housekeeping, a real department of the hotel
    // extends Department = it is a child of Department
    // so it inherits the name box and getName()

    public Housekeeping() {
        super(DepartmentName.HOUSEKEEPING);
        // the constructor = Housekeeping; same as the class name
        // () empty: it doesnt need to receive anything
        // it already knows it is HOUSEKEEPING
        // super(...) = works as a child asking for a parent to do something for her
        // here it is basically saying: parent, please save HOUSEKEEPING in my box
    }

}
