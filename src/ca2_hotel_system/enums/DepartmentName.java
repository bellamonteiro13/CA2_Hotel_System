package ca2_hotel_system.enums;

// here it is my enum DepartmentName (an enum is a special kind of class!)
// im creating it so only the 3 real departments of the hotel can exist
// the 3 departments come from my SDF: Front Office, Housekeeping and Food & Beverage

public enum DepartmentName {
    // my 3 departments are bellow 
    FRONT_OFFICE ("Front Office"),
    HOUSEKEEPING ("Housekeeping"),
    FOOD_BEVERAGE ("Food & Beverage");
    
// the rest works the same way as my ManagerType enum 
//here i keep the name of my departments
// private = only this file can use it, final = it never changes

    private final String label;

// this is my constructor, it runs once for each department 
// here is basically saying: pick up the text that arrived and save it in my label

    DepartmentName(String label) {
        this.label = label;
    }
    
// here it gives back the nice name of the department, so i can show it on the screen
    public String getLabel() {
        return label;
    }
   
// here it gives back the number of the option, so the user can pick it from a list
// ordinal() starts counting at 0, so i add 1 to make it start at 1

    public int getMenuNumber() {
        return ordinal() + 1;
    }
 

}
