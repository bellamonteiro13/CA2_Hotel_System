package ca2_hotel_system.algorithms;

// here it is my hand-made Queue, like the one in my teacher's BinaryTreeTeaching.java
// a Queue is FIFO = First In, First Out, like the line of customers at the bar:
// the first one who arrives is the first one to be served
// my teacher's queue had only 20 slots, so mine receives the size it needs (my hotel has 40+ staff!)
public class StaffQueue {

    // the array (tray) that holds the nodes waiting in the line
    TreeNode[] arr;

    // front = the slot of the next one to be served, rear = the slot of the last one who arrived
    int front = 0, rear = -1;

    // the constructor creates a tray big enough for everybody
    // Receives: capacity = how many nodes can go into the line
    StaffQueue(int capacity) {
        arr = new TreeNode[capacity];
    }

    // enqueue = a new customer joins the END of the line
    void enqueue(TreeNode node) {
        rear++;
        arr[rear] = node;
    }

    // dequeue = the customer at the FRONT of the line is served (and leaves the line)
    TreeNode dequeue() {
        return arr[front++];
    }

    // isEmpty = is the line empty? (front passed rear = nobody left)
    boolean isEmpty() {
        return front > rear;
    }

    // size = how many are waiting in the line right now
    // i use it to print the tree one LEVEL at a time
    int size() {
        return rear - front + 1;
    }
}
