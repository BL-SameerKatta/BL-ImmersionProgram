/**
 * DoublyLinkedList.java
 *
 * Day 25-26 - DSA: Linked List
 * Concepts: Doubly Linked List, Bidirectional traversal,
 *           Insert/Delete with prev+next pointer updates,
 *           Forward and backward display
 *
 * A Doubly Linked List has nodes with both next and prev pointers.
 * This gives us:
 *   - O(1) insertion/deletion at both ends (head and tail)
 *     because we maintain both head AND tail pointers
 *   - Forward AND backward traversal
 *   - Easier deletion when the node reference is known
 *
 * Structure:
 *   head <--> [1] <--> [2] <--> [3] <--> tail
 *   null <-- [1.prev]     [3.next] --> null
 *
 * Key operations:
 *   - insertAtHead(data)   : O(1) — update head and its prev
 *   - insertAtTail(data)   : O(1) — update tail and its next
 *   - deleteAtHead()       : O(1)
 *   - deleteAtTail()       : O(1)
 *   - deleteByValue(data)  : O(n) — search, then O(1) deletion
 *   - displayForward()     : head to tail
 *   - displayBackward()    : tail to head
 */
public class DoublyLinkedList {

    /*
     * head : first node in the list.
     * tail : last node in the list.
     * Maintaining both enables O(1) operations at either end.
     */
    private DoublyNode head;
    private DoublyNode tail;
    private int        size;

    /**
     * Constructor — DoublyLinkedList()
     *
     * Creates an empty doubly linked list.
     * Both head and tail are null, size is 0.
     */
    public DoublyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    /**
     * insertAtHead(int data)
     *
     * Inserts a new node at the BEGINNING of the list.
     * Updates the old head's prev pointer to point back to new node.
     *
     * Steps:
     *   1. Create new node
     *   2. new node's next = current head
     *   3. If head exists, set head.prev = new node
     *   4. Update head to new node
     *   5. If list was empty, tail = new node too
     *
     * Time Complexity: O(1)
     *
     * @param data Value to insert at head
     */
    public void insertAtHead(int data) {
        DoublyNode newNode = new DoublyNode(data);

        if (head == null) {
            head = newNode;
            tail = newNode; // first node is both head and tail
        } else {
            newNode.next = head; // new node points forward to old head
            head.prev    = newNode; // old head points backward to new node
            head         = newNode; // update head
        }
        size++;
    }

    /**
     * insertAtTail(int data)
     *
     * Inserts a new node at the END of the list.
     * O(1) because we maintain a tail pointer.
     *
     * Steps:
     *   1. Create new node
     *   2. Set new node's prev = current tail
     *   3. Set current tail's next = new node
     *   4. Update tail to new node
     *
     * Time Complexity: O(1) — direct tail access
     *
     * @param data Value to insert at tail
     */
    public void insertAtTail(int data) {
        DoublyNode newNode = new DoublyNode(data);

        if (tail == null) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.prev = tail;    // new node points backward to old tail
            tail.next    = newNode; // old tail points forward to new node
            tail         = newNode; // update tail
        }
        size++;
    }

    /**
     * deleteAtHead()
     *
     * Removes the first node (head) from the list.
     * Updates new head's prev pointer to null.
     *
     * Time Complexity: O(1)
     *
     * @return Data value of deleted node, -1 if empty
     */
    public int deleteAtHead() {
        if (head == null) {
            System.out.println("  [ERROR] List is empty.");
            return -1;
        }
        int deletedData = head.data;

        if (head == tail) {
            // Only one node — list becomes empty
            head = null;
            tail = null;
        } else {
            head      = head.next; // move head forward
            head.prev = null;      // new head has no previous node
        }
        size--;
        return deletedData;
    }

    /**
     * deleteAtTail()
     *
     * Removes the last node (tail) from the list.
     * O(1) because we maintain a tail pointer.
     *
     * Steps:
     *   1. Save tail data
     *   2. Move tail backward (tail = tail.prev)
     *   3. Set new tail's next = null
     *
     * Time Complexity: O(1) — direct tail access
     *
     * @return Data value of deleted node, -1 if empty
     */
    public int deleteAtTail() {
        if (tail == null) {
            System.out.println("  [ERROR] List is empty.");
            return -1;
        }
        int deletedData = tail.data;

        if (head == tail) {
            head = null;
            tail = null;
        } else {
            tail      = tail.prev; // move tail backward
            tail.next = null;      // new tail has no next node
        }
        size--;
        return deletedData;
    }

    /**
     * deleteByValue(int data)
     *
     * Removes the first node with the matching data value.
     * Because of prev pointers, deletion is clean once the node is found —
     * no need to track the previous node separately.
     *
     * Time Complexity: O(n) search + O(1) deletion
     *
     * @param data Value to find and delete
     * @return true if deleted, false if not found
     */
    public boolean deleteByValue(int data) {
        DoublyNode current = head;

        while (current != null) {
            if (current.data == data) {
                if (current == head) return deleteAtHead() != -1;
                if (current == tail) return deleteAtTail() != -1;

                /*
                 * We have a node in the middle.
                 * Update its neighbors to bypass it.
                 * prev node's next skips over current.
                 * next node's prev skips over current.
                 */
                current.prev.next = current.next;
                current.next.prev = current.prev;
                size--;
                return true;
            }
            current = current.next;
        }
        System.out.println("  [NOT FOUND] Value " + data + " not in list.");
        return false;
    }

    /**
     * displayForward()
     *
     * Traverses and prints the list from head to tail.
     * Format: null <--> [1] <--> [2] <--> [3] <--> null
     *
     * Time Complexity: O(n)
     */
    public void displayForward() {
        System.out.print("  null <--> ");
        DoublyNode current = head;
        while (current != null) {
            System.out.print("[" + current.data + "]");
            if (current.next != null) System.out.print(" <--> ");
            current = current.next;
        }
        System.out.println(" <--> null");
    }

    /**
     * displayBackward()
     *
     * Traverses and prints the list from tail to head.
     * Only possible because of the prev pointer in each node.
     * This operation is IMPOSSIBLE with a singly linked list.
     *
     * Time Complexity: O(n)
     */
    public void displayBackward() {
        System.out.print("  null <--> ");
        DoublyNode current = tail;
        while (current != null) {
            System.out.print("[" + current.data + "]");
            if (current.prev != null) System.out.print(" <--> ");
            current = current.prev;
        }
        System.out.println(" <--> null");
    }

    /**
     * size() — Returns the number of nodes in the list.
     * @return Size as an int
     */
    public int size() { return size; }

    /**
     * isEmpty() — Returns true if the list has no nodes.
     * @return true if empty
     */
    public boolean isEmpty() { return head == null; }
}
