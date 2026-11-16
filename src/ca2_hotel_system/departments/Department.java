package ca2_hotel_system.departments;

import ca2_hotel_system.enums.DepartmentName;
//here im calling the DepartmentName that lives inside the enum package

public abstract class Department {
// abstract it is pointing that this class is an idea/base
//that means that JAVA it is not gonna let anyone create anything directly 
//only the scpecif children
// we need the class department because every 3 departments come w the same things 
//so instead of writing 03 times, we write it once in the parent class
// and the children inherit it!!! 

    protected DepartmentName name;
    // protected = so the childen class can use it too
    // DepartmentName = is the type 
    // name = the box name; we are going to fill in later 

    // the constructor fills the box
    public Department(DepartmentName name) {
        this.name = name;
    }

    // the getter opens the box
    // it is like asking which department are u? 
    public DepartmentName getName() {
        return name;
    }

}
