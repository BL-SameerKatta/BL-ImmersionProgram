/**
 * SinglyLinkedList.java
 *
 * Day 25-26 - DSA: Linked List
 * Concepts: Singly Linked List, Insert (head/tail/position),
 *           Delete (head/tail/value), Traversal, Size,
 *           Reverse, Find Middle, Cycle Detection, Merge Sorted Lists
 *
 * A Singly Linked List is a linear data structure where each node
 * points only to the NEXT node (one direction only).
 *
 * Story: A train management system models coaches as a linked list.
 *   - head  : the engine / first coach
 *   - tail  : last coach
 *   - next  : coupling between coaches
 *
 * Structure:
 *   head --> [1] --> [2] --> [3] --> [4] --> null
 *
 * Key operations implemented:
 *   - insertAtHead(data)    : add coach at front
 *   - insertAtTail(data)    : add coach at end
 *   - insertAtPosition(...) : add coach at specific position
 *   - deleteAtHead()        : remove first coach
 *   - deleteAtTail()        : remove last coach
 *   - deleteByValue(data)   : remove coach by number
 *   - reverse()             : reverse entire train direction
 *   - findMiddle()          : find middle coach (Floyd's slow-fast pointer)
 *   - hasCycle()            : detect circular route (Floyd's cycle detection)
 *   - mergeSorted(l1, l2)   : merge two sorted train schedules
 *   - display()             : print all coaches
 *   - size()                : count total coaches
 */
public class SinglyLinkedList {

    /*
     * head : pointer to the first node (first coach / engine).
     * null when the list is empty.
     * All traversals and operations start from head.
     */
    private Node head;

    /*
     * size : tracks the number of nodes in the list.
     * Updated on every insert and delete to avoid O(n) counting.
     */
    private int size;

    /**
     * Constructor — SinglyLinkedList()
     *
     * Creates an empty linked list.
     * head is null — no coaches yet.
     * size is 0.
     */
    public SinglyLinkedList() {
        this.head = null;
        this.size = 0;
    }

    /**
     * insertAtHead(int data)
     *
     * Inserts a new node at the BEGINNING of the list.
     * The new node becomes the new head.
     *
     * Steps:
     *   1. Create new node
     *   2. Set new node's next = current head
     *   3. Update head to point to new node
     *
     * Time Complexity: O(1) — no traversal needed
     *
     * @param data Coach number to insert at the front
     */
    public void insertAtHead(int data) {
        Node newNode  = new Node(data);
        newNode.next  = head;   // new node points to old head
        head          = newNode; // head now points to new node
        size++;
    }

    /**
     * insertAtTail(int data)
     *
     * Inserts a new node at the END of the list.
     * If list is empty, the new node becomes the head.
     *
     * Steps:
     *   1. Create new node
     *   2. If empty, set as head
     *   3. Else traverse to last node (where next == null)
     *   4. Set last node's next = new node
     *
     * Time Complexity: O(n) — must traverse to end
     *
     * @param data Coach number to insert at the back
     */
    public void insertAtTail(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            /*
             * Traverse to the last node.
             * The last node is identified by current.next == null.
             */
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode; // link last node to new node
        }
        size++;
    }

    /**
     * insertAtPosition(int data, int position)
     *
     * Inserts a new node at the given 1-based position.
     * Position 1 means insert at head.
     * Position > size means insert at tail.
     *
     * Steps:
     *   1. If position 1 — call insertAtHead
     *   2. Traverse to (position-1)th node
     *   3. Set new node's next = current.next
     *   4. Set current.next = new node
     *
     * Time Complexity: O(n) — traversal to position
     *
     * @param data     Coach number to insert
     * @param position 1-based position to insert at
     */
    public void insertAtPosition(int data, int position) {
        if (position <= 1) {
            insertAtHead(data);
            return;
        }
        Node newNode = new Node(data);
        Node current = head;

        /*
         * Traverse to the node just BEFORE the target position.
         * Stop at position-1 so we can insert after it.
         */
        for (int i = 1; i < position - 1 && current.next != null; i++) {
            current = current.next;
        }

        newNode.next  = current.next; // new node points to what was at position
        current.next  = newNode;      // previous node now points to new node
        size++;
    }

    /**
     * deleteAtHead()
     *
     * Removes the first node (head) from the list.
     * Updates head to point to the second node.
     *
     * Time Complexity: O(1)
     *
     * @return The data value of the deleted node, -1 if list is empty
     */
    public int deleteAtHead() {
        if (head == null) {
            System.out.println("  [ERROR] List is empty — nothing to delete.");
            return -1;
        }
        int deletedData = head.data;
        head = head.next; // move head to next node (old head is garbage collected)
        size--;
        return deletedData;
    }

    /**
     * deleteAtTail()
     *
     * Removes the last node from the list.
     * Must traverse to second-to-last node to update its next pointer.
     *
     * Time Complexity: O(n) — must reach last node
     *
     * @return The data value of the deleted node, -1 if list is empty
     */
    public int deleteAtTail() {
        if (head == null) {
            System.out.println("  [ERROR] List is empty.");
            return -1;
        }
        if (head.next == null) {
            // Only one node
            int deletedData = head.data;
            head = null;
            size--;
            return deletedData;
        }
        Node current = head;
        /*
         * Stop at second-to-last node (current.next.next == null).
         * Then set current.next = null to remove the last node.
         */
        while (current.next.next != null) {
            current = current.next;
        }
        int deletedData  = current.next.data;
        current.next     = null; // detach last node
        size--;
        return deletedData;
    }

    /**
     * deleteByValue(int data)
     *
     * Removes the first node with the given data value.
     * Traverses the list looking for a match.
     *
     * Time Complexity: O(n)
     *
     * @param data The coach number to remove
     * @return true if deleted successfully, false if not found
     */
    public boolean deleteByValue(int data) {
        if (head == null) return false;

        // Special case: head itself is the target
        if (head.data == data) {
            head = head.next;
            size--;
            return true;
        }

        Node current = head;
        /*
         * Look for the node BEFORE the target node.
         * We need the previous node to update its next pointer.
         */
        while (current.next != null) {
            if (current.next.data == data) {
                current.next = current.next.next; // bypass the target node
                size--;
                return true;
            }
            current = current.next;
        }
        System.out.println("  [NOT FOUND] Coach " + data + " not in the train.");
        return false;
    }

    /**
     * reverse()
     *
     * Reverses the entire linked list in-place.
     * After reversal, the tail becomes the new head.
     *
     * Story: Reverses the direction of the entire train.
     *
     * Algorithm (3-pointer technique):
     *   - prev    : tracks the previous node (starts null)
     *   - current : the node being processed
     *   - next    : saves current.next before we overwrite it
     *
     * Step by step:
     *   Before: head --> [1] --> [2] --> [3] --> null
     *   After:  head --> [3] --> [2] --> [1] --> null
     *
     * Time Complexity: O(n) — single pass
     * Space Complexity: O(1) — no extra data structures
     */
    public void reverse() {
        Node prev    = null;
        Node current = head;
        Node next    = null;

        while (current != null) {
            next         = current.next; // save next before we overwrite
            current.next = prev;         // reverse the pointer
            prev         = current;      // move prev forward
            current      = next;         // move current forward
        }
        head = prev; // prev is now at the old tail = new head
    }

    /**
     * findMiddle()
     *
     * Finds the middle node using Floyd's Two-Pointer technique.
     *
     * Story: Find the middle coach for safety inspection without
     *        counting total coaches first.
     *
     * Algorithm:
     *   - slow pointer moves 1 step at a time
     *   - fast pointer moves 2 steps at a time
     *   - When fast reaches end, slow is at the middle
     *
     * For even length, returns the SECOND middle node.
     *
     * Time Complexity: O(n) — single pass
     * Space Complexity: O(1)
     *
     * @return The middle Node, or null if list is empty
     */
    public Node findMiddle() {
        if (head == null) return null;

        Node slow = head;
        Node fast = head;

        /*
         * Move slow by 1, fast by 2.
         * When fast cannot move 2 steps anymore, slow is at middle.
         */
        while (fast != null && fast.next != null) {
            slow = slow.next;       // 1 step
            fast = fast.next.next;  // 2 steps
        }
        return slow;
    }

    /**
     * hasCycle()
     *
     * Detects if the linked list contains a cycle (circular route).
     * Uses Floyd's Cycle Detection Algorithm (Tortoise and Hare).
     *
     * Story: Detect if the train route is circular (coaches loop back).
     *
     * Algorithm:
     *   - slow moves 1 step at a time (tortoise)
     *   - fast moves 2 steps at a time (hare)
     *   - If there IS a cycle, fast will eventually lap slow and they meet
     *   - If there is NO cycle, fast will reach null (end of list)
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1) — no extra set/map needed
     *
     * @return true if cycle exists, false otherwise
     */
    public boolean hasCycle() {
        if (head == null || head.next == null) return false;

        Node slow = head;
        Node fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;       // 1 step
            fast = fast.next.next;  // 2 steps

            /*
             * If slow and fast meet, a cycle exists.
             * They can only meet inside the loop.
             */
            if (slow == fast) return true;
        }
        return false; // fast reached null — no cycle
    }

    /**
     * createCycleForTesting(int position)
     *
     * Creates a cycle in the list for testing hasCycle().
     * Connects the last node back to the node at the given position.
     * FOR TESTING ONLY — do not use in production.
     *
     * @param position 0-based position to connect tail back to
     */
    public void createCycleForTesting(int position) {
        if (head == null) return;

        Node cycleNode = head;
        for (int i = 0; i < position; i++) {
            if (cycleNode.next != null) cycleNode = cycleNode.next;
        }

        Node tail = head;
        while (tail.next != null) tail = tail.next;
        tail.next = cycleNode; // create the cycle
    }

    /**
     * mergeSorted(SinglyLinkedList list1, SinglyLinkedList list2)
     *
     * Merges two sorted linked lists into one sorted linked list.
     * Story: Merge two sorted train schedules into one sorted combined schedule.
     *
     * Algorithm:
     *   Compare heads of both lists.
     *   Pick the smaller one as the next node in the merged list.
     *   Advance the pointer of the list we took from.
     *   Repeat until one list is exhausted.
     *   Append the remaining nodes from the non-empty list.
     *
     * Time Complexity: O(m + n) — where m and n are list sizes
     * Space Complexity: O(1) — reuses existing nodes, no new allocation
     *
     * @param list1 First sorted linked list
     * @param list2 Second sorted linked list
     * @return A new SinglyLinkedList containing the merged sorted result
     */
    public static SinglyLinkedList mergeSorted(SinglyLinkedList list1,
                                               SinglyLinkedList list2) {
        SinglyLinkedList merged = new SinglyLinkedList();

        Node p1 = list1.head;
        Node p2 = list2.head;

        /*
         * Use a dummy node trick to simplify head assignment.
         * We build the merged list by selecting smaller values alternately.
         */
        Node dummy   = new Node(0); // placeholder — not part of real list
        Node current = dummy;

        while (p1 != null && p2 != null) {
            if (p1.data <= p2.data) {
                current.next = new Node(p1.data);
                p1 = p1.next;
            } else {
                current.next = new Node(p2.data);
                p2 = p2.next;
            }
            current = current.next;
            merged.size++;
        }

        // Append remaining nodes from whichever list is not exhausted
        while (p1 != null) {
            current.next = new Node(p1.data);
            current = current.next;
            p1 = p1.next;
            merged.size++;
        }
        while (p2 != null) {
            current.next = new Node(p2.data);
            current = current.next;
            p2 = p2.next;
            merged.size++;
        }

        merged.head = dummy.next; // skip the dummy node
        return merged;
    }

    /**
     * display()
     *
     * Prints all nodes in the list from head to tail.
     * Format: head --> [1] --> [2] --> [3] --> null
     *
     * Time Complexity: O(n) — full traversal
     */
    public void display() {
        if (head == null) {
            System.out.println("  [EMPTY LIST]");
            return;
        }
        System.out.print("  head --> ");
        Node current = head;
        while (current != null) {
            System.out.print("[" + current.data + "]");
            if (current.next != null) System.out.print(" --> ");
            current = current.next;
        }
        System.out.println(" --> null");
    }

    /**
     * size()
     *
     * Returns the number of nodes currently in the list.
     * O(1) because we maintain size as we insert/delete.
     *
     * @return Number of nodes as an int
     */
    public int size() {
        return size;
    }

    /**
     * isEmpty()
     *
     * Returns true if the list has no nodes.
     *
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * getHead()
     *
     * Returns the head node.
     * Used by mergeSorted and other external utilities.
     *
     * @return Head Node reference
     */
    public Node getHead() {
        return head;
    }
}
