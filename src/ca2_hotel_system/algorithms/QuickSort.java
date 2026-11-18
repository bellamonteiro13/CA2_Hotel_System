package ca2_hotel_system.algorithms;

import ca2_hotel_system.staff.StaffMember;

// here it is my class QuickSort, it organises the staff names in alphabetical order
// it is based on my teacher's QuickSort.java, but i changed it from numbers (int) to names (String)
// why Quick Sort? because the brief asks for a RECURSIVE sort and this is the one we learned in class
// average O(n log n), worst case O(n²) (when the list is already sorted)
public class QuickSort {

    // quickSort = sorts the staff A to Z by their NAME (ignoring upper/lower case)
    // Receives: arr = the staff to sort
    // Gives back: a NEW array with the staff in alphabetical order
    //it sorts people now 
    public static StaffMember[] quickSort(StaffMember[] arr) {

        // base case: 0 or 1 name is already sorted, so stop here (the STOP sign of the recursion)
        if (arr.length <= 1) {
            return arr;
        }

        // the pivot = the LAST name, my "reference bottle"
        StaffMember pivot = arr[arr.length - 1];

        // counters (numbers, so they stay int)
        int leftCount = 0;
        int rightCount = 0;

        // count sizes: how many names come BEFORE the pivot and how many come AFTER
        // i need to count first because an array is a tray with a FIXED number of slots
        for (int i = 0; i < arr.length - 1; i++) {
            // getName() = i compare only the NAMES (the type and department come along with the person)
            // like a guest luggage
            // compareToIgnoreCase < 0 means this name comes before the pivot in the alphabet
            if (arr[i].getName().compareToIgnoreCase(pivot.getName()) < 0) {
                leftCount++;
            } else {
                rightCount++;
            }
        }

        // create the two piles (trays) with the right size
        StaffMember[] left = new StaffMember[leftCount];
        StaffMember[] right = new StaffMember[rightCount];

        // l and r = the next empty slot on each tray
        int l = 0, r = 0;

        // fill the piles: before the pivot goes left, the rest goes right
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i].getName().compareToIgnoreCase(pivot.getName()) < 0) {
                left[l++] = arr[i];
            } else {
                right[r++] = arr[i];
            }
        }

        // recursive calls: the method calls ITSELF to sort each pile (same recipe, smaller pile)
        left = quickSort(left);
        right = quickSort(right);

        // combine: left pile + pivot + right pile in one big tray
        StaffMember[] result = new StaffMember[arr.length];
        int index = 0;

        for (int i = 0; i < left.length; i++) {
            result[index++] = left[i];
        }

        result[index++] = pivot;

        for (int i = 0; i < right.length; i++) {
            result[index++] = right[i];
        }

        return result;
    }
}
