package ca2_hotel_system.employees;
import ca2_hotel_system.departments.Housekeeping;

// here it is my class RoomAttendant, a real employee of the hotel
// they clean and prepare the guests' rooms
// a Room Attendant is an Employee = that is why it extends Employee
public class RoomAttendant extends Employee {

    public RoomAttendant(String name) {
        super(name, "Room Attendant", new Housekeeping());
        // String name = receives only the person's name
        // "Room Attendant" = the designation (with a space, because it is just text for the screen)
        // new Housekeeping() = builds a real housekeeping department
        // that is the reason why the import is Housekeeping and not Department
    }
}
