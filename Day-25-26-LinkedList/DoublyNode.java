/**
 * DoublyNode.java
 *
 * Day 25-26 - DSA: Linked List
 * Concepts: Doubly Linked List Node, Two-way pointer model,
 *           prev + next pointers, Bidirectional traversal
 *
 * DoublyNode is the building block of a doubly linked list.
 * Unlike a singly linked list node (which only has 'next'),
 * a DoublyNode has BOTH:
 *   - next : pointer to the node AFTER this one
 *   - prev : pointer to the node BEFORE this one
 *
 * This bidirectional linking enables:
 *   - Forward traversal  (via next pointers)
 *   - Backward traversal (via prev pointers)
 *   - O(1) deletion when you already have the node reference
 *   - Easier insertion before a given node
 *
 * Memory model:
 *   null <-- [1] <--> [2] <--> [3] --> null
 *   prev=null  prev=1   prev=2
 *   next=2     next=3   next=null
 *
 * Trade-off vs Singly:
 *   Doubly uses more memory (extra prev pointer per node)
 *   but enables O(1) backward navigation and easier deletion.
 */
public class DoublyNode {

    /*
     * data : the value stored in this node (coach number).
     * Same as singly linked list node.
     */
    int data;

    /*
     * next : reference to the next node (forward direction).
     * null for the tail (last) node.
     */
    DoublyNode next;

    /*
     * prev : reference to the previous node (backward direction).
     * null for the head (first) node.
     * This is what makes it a DOUBLY linked list.
     */
    DoublyNode prev;

    /**
     * Constructor — DoublyNode(int data)
     *
     * Creates a new doubly linked list node.
     * Both prev and next are null initially.
     * The calling code links this node into the chain.
     *
     * @param data The integer value to store
     */
    public DoublyNode(int data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }
}
