package ca2_hotel_system.algorithms;

import ca2_hotel_system.staff.StaffMember;

// here it is my class BinarySearch, it finds a person on the shelf by their NAME (UC3)
// it works like looking for a word in a dictionary: open in the MIDDLE,
// then throw away the half where the name cant be, and repeat
// IMPORTANT: it only works if the list is SORTED first (that is why i sort before searching)
// why Binary Search? because the brief asks for a RECURSIVE search and it is fast:
// O(log n) = 41 names need at most 6 checks, a linear search could need 41
public class BinarySearch {

    // binarySearch = looks for the name between the positions low and high
    // Receives: sortedStaff = the staff sorted A to Z
    //targetName = the name the user typed
    //low = the first position of the part i am still looking at
    //high = the last position of the part i am still looking at
    // Gives back: the StaffMember with that name, or null if the name is not there
    public static StaffMember binarySearch(StaffMember[] sortedStaff, String targetName, int low, int high) {

        // base case 1: low passed high = there is nowhere left to look, so NOT FOUND
        if (low > high) {
            return null;
        }

        // open the "dictionary" in the middle
        int middle = (low + high) / 2;

        // compare the name i want with the name in the middle (ignoring upper/lower case)
        // 0 = same name, < 0 = my name comes BEFORE(left), > 0 = my name comes AFTER(right)
        int comparison = targetName.compareToIgnoreCase(sortedStaff[middle].getName());

        if (comparison == 0) {
            // base case 2: found it!
            return sortedStaff[middle];
        } else if (comparison < 0) {
            // recursive call: the name is in the LEFT half, so i throw away the right half
            return binarySearch(sortedStaff, targetName, low, middle - 1);
        } else {
            // recursive call: the name is in the RIGHT half, so i throw away the left half
            return binarySearch(sortedStaff, targetName, middle + 1, high);
        }
    }
}
