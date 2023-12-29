// This class defines a Queue

// You can use these methods

// void enqueue(E data)
// E dequeue()
// E front()
// int size()


public class Queue<E> {
    public Node head;
    public Node tail;

    // constructor method to create a list of object with head, tail, and size.
    public Queue() {
        head = null;
        tail = null;
    }

    // method add node to end of list
    public void addLastNode(E data) {
        if (tail == null)
            head = tail = new Node(data); // empty list
        else {
            tail.next = new Node(data); // link new node as last node
            tail = tail.next; // make tail pointer points to last node
        }
    }

    // Method for enqueue (same as addlast for linked list)
    public void enqueue(E data) {
        Node newNode = new Node(data);
        if (tail == null) // case for if our Queue is empty
            head = tail = newNode;
        else {
            tail.next = newNode;
            tail = tail.next;
        }
    }

    // Method for dequeue
    public E dequeue() throws RuntimeException {
        int size = size();
        Node temp;

        if (size == 0) {
            //System.out.println("DQEUEUE() ERROR: QUEUE IS EMPTY");
            throw new RuntimeException("DEQUEUE() ERROR: QUEUE IS EMPTY");
        }
        else if (size == 1) // if our queue has only one element
        {
            temp = head;
            head = tail = null;
            size = 0;
            return temp.data;
        } else {
            temp = head;
            head = head.next; // Make the head point to the 2nd node
            return temp.data;
        }

    }

    // Method for size, counts and gives us the number of elements (nodes) in our Queue
    public int size() {
        Node temp;
        int size = 0; // Create int variable to store the number of nodes
        temp = head; // Set current node to the head
        while (temp != null) // While the Current node doesn't equal null move forward node by node counting
        {
            size++;
            temp = temp.next;
        }
        return size; // Return the number of elements (leave method useful for when we want to
        // remove/add at an index >= Count)
    }

    // Method for front, looks at the 1st element of the Queue and returns that element
    public E front() {
        E retVal;
        if (isEmpty() == false)
            retVal = head.data;
        else
            retVal = null;
        return retVal; // return the element that is at the head

    }

    // Method for isEmpty(), checks to see if the list is empty
    public boolean isEmpty() {
        if (size() == 0)
            return true;
        else
            return false;
    }

    // method to print out the Queue
    public String toString() {
        String str = "[";
        Node temp;
        temp = head;
        while (temp != null) {
            str = str + temp.data;
            if(temp.next != null)
                str += ", ";
            temp = temp.next;
        }
        return str + "]";

    }

    // class to create nodes as objects
    private class Node {
        private E data; // data field
        private Node next; // link field

        public Node(E item) // constructor method
        {
            data = item;
            next = null;
        }
    }
}
