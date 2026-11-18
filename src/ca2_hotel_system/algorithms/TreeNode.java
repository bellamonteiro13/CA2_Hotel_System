package ca2_hotel_system.algorithms;

import ca2_hotel_system.staff.StaffMember;

// here it is my class TreeNode, ONE box (node) of my hierarchy tree
// it is like my teacher's Node class, but instead of a String it keeps a whole staff member
// each node has: the person (name, designation, department) + a LEFT child + a RIGHT child
// binary tree = every node has at most 2 children (left and right)
public class TreeNode {

    // the person inside this node
    StaffMember staff;

    // the 2 children (null = there is nobody there yet)
    TreeNode left, right;

    // the constructor puts the person in the node, with no children yet
    TreeNode(StaffMember staff) {
        this.staff = staff;
        left = right = null;
    }
}
