package ca2_hotel_system.departments;
import ca2_hotel_system.enums.DepartmentName;

public class FrontOffice extends Department{
    // here it is my class FrontOffice, a real department of the hotel
    // extends Department = it is a child of Department
    //so it inherits the name box and getName()
    
    public FrontOffice(){
        super(DepartmentName.FRONT_OFFICE);
        // the constructor = FrontOffice; same as the class name 
        //() empty: they dont need to receive anything 
        // it already knows it is FRONT_OFFICE 
        //super(...) = works as a child asking for a parent to do something for her 
        //here it is basically saying: parent, please save FRONT_OFFICE in my box
       
    }

}
