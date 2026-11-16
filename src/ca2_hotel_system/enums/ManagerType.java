package ca2_hotel_system.enums;
// here it is my enum ManagerType (an enum is a special kind of class!)
// im creating it so the type of manager is shown, and only these 3 types can exist
// the 3 types come from my SDF: General Manager, Department Manager and Supervisor
public enum ManagerType { 

    // here are my 3 manager types, each one with the nice name that shows on the screen
    GENERAL_MANAGER ("General Manager"),
    DEPARTMENT_MANAGER ("Department Manager"),
    SUPERVISOR ("Supervisor");

// here is where i keep the nice name of each manager type
// private = only this file can use it, final = it never changes

    private final String label;

// this is my constructor, it runs once for each manager type above
// here is basically saying: pick up the text that arrived and save it in my label

    ManagerType(String label) {
        this.label = label;
    }
    
// here it gives back the nice name of the manager type, so i can show it on the screen
    public String getLabel() {
        return label;
    }
   
// here it gives back the number of the option, so the user can pick it from a list
// ordinal() starts counting at 0, so i add 1 to make it start at 1

    public int getMenuNumber() {
        return ordinal() + 1;
    }
 



}
 


