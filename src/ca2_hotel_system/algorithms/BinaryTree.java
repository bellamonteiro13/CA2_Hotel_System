package ca2_hotel_system.algorithms;

import ca2_hotel_system.staff.StaffMember;

// here it is my class BinaryTree, the HIERARCHY of my hotel (UC5)
// the root (top) is the General Manager, then the Department Managers,
// then the Supervisors, then the frontline Employees
// i insert in LEVEL ORDER (fill each level from left to right before going down),
// so the most senior people are always at the top
public class BinaryTree {

    // the root = the top of the tree (the first node)
    TreeNode root;

    // how many staff the tree can hold (used to make the queue big enough)
    int capacity;

    // the constructor creates an EMPTY tree
    // Receives: capacity = the number of staff that will go into the tree
    public BinaryTree(int capacity) {
        this.root = null;
        this.capacity = capacity;
    }

    // insert = put a new person in the FIRST free spot, in level order
    // it uses my Queue: it visits the nodes level by level, and the first node
    // with a free left or right child gets the new person
    public void insert(StaffMember staff) {
        TreeNode newNode = new TreeNode(staff);

        // empty tree: the new person becomes the root (the top)
        if (root == null) {
            root = newNode;
            return;
        }

        StaffQueue queue = new StaffQueue(capacity);
        queue.enqueue(root);

        // how i read it: "while there are still people in the line, serve the next one
        //                 and check if they have a free child spot"
        while (!queue.isEmpty()) {
            TreeNode current = queue.dequeue();

            if (current.left == null) {
                current.left = newNode;   // free spot on the LEFT: put the new person here
                return;
            } else {
                queue.enqueue(current.left);
            }

            if (current.right == null) {
                current.right = newNode;  // free spot on the RIGHT: put the new person here
                return;
            } else {
                queue.enqueue(current.right);
            }
        }
    }

    // displayLevelOrder = BFS (Breadth First Search), like my teacher's bfs()
    // it prints the tree LEVEL by LEVEL, from the top to the bottom, left to right
    public void displayLevelOrder() {
        if (root == null) {
            System.out.println("The tree is empty.");
            return;
        }

        StaffQueue queue = new StaffQueue(capacity);
        queue.enqueue(root);
        int level = 1;

        while (!queue.isEmpty()) {
            // how many people are on THIS level right now (they are all waiting in the line)
            int peopleOnThisLevel = queue.size();
            System.out.println("\nLevel " + level + ":");

            // serve everybody on this level, and put their children at the end of the line
            for (int i = 0; i < peopleOnThisLevel; i++) {
                TreeNode current = queue.dequeue();
                System.out.println("  " + current.staff);

                if (current.left != null) {
                    queue.enqueue(current.left);
                }
                if (current.right != null) {
                    queue.enqueue(current.right);
                }
            }
            level++;
        }
    }

    // getHeight = how many LEVELS the tree has (RECURSIVE)
    public int getHeight() {
        return height(root);
    }

    // height = the height of the tree that starts at this node
    // base case: an empty spot (null) has height 0
    // recursive call: 1 (this node) + the height of the TALLER side (left or right)
    private int height(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return 1 + Math.max(height(node.left), height(node.right));
    }

    // countNodes = how many people are in the tree (RECURSIVE)
    public int countNodes() {
        return count(root);
    }

    // count = how many nodes are in the tree that starts at this node
    // base case: an empty spot (null) has 0 nodes
    // recursive call: 1 (this node) + all the nodes on the left + all the nodes on the right
    private int count(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return 1 + count(node.left) + count(node.right);
    }
}
