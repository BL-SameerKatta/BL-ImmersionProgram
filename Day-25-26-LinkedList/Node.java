/**
 * Node.java
 *
 * Day 25-26 - DSA: Linked List
 * Concepts: Node structure, Data + Pointer model, Building block of Linked List
 *
 * Node is the fundamental building block of a singly linked list.
 * Each Node holds:
 *   - data  : the value stored at this position (coach number/name)
 *   - next  : reference (pointer) to the next Node in the chain
 *
 * Story context:
 *   In the train management system, each Node represents one coach.
 *   The 'next' pointer connects coaches to form the full train.
 *
 * What this demonstrates:
 *   - Self-referential class — Node contains a reference to another Node
 *   - The 'next' field is null for the last node (end of list / last coach)
 *   - Data can be any type — here we use int for coach number
 *
 * Memory model:
 *   Node1 [data=1 | next] --> Node2 [data=2 | next] --> Node3 [data=3 | next=null]
 */
public class Node {

    /*
     * data : the value stored in this node.
     * Represents the coach number in our train simulation.
     * Package-private (no modifier) — accessible within the same package
     * and by the LinkedList class for direct manipulation.
     */
    int data;

    /*
     * next : reference to the next Node in the list.
     * null when this is the last node (tail of the list).
     * This is what makes it a "linked" list — nodes are chained via this pointer.
     */
    Node next;

    /**
     * Constructor — Node(int data)
     *
     * Creates a new node with the given data value.
     * 'next' is automatically null — new nodes are not connected yet.
     * The calling code (LinkedList methods) is responsible for
     * linking this node into the chain.
     *
     * @param data The integer value to store in this node
     */
    public Node(int data) {
        this.data = data;
        this.next = null; // not connected to any next node yet
    }
}
