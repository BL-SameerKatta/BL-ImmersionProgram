/**
 * TrainManagementSimulation.java
 *
 * Day 25-26 - DSA: Linked List
 * Concepts: Singly Linked List, Doubly Linked List,
 *           Insert/Delete, Reverse, Cycle Detection,
 *           Find Middle, Merge Sorted Lists, Nth from End, Palindrome
 *
 * This is the main driver class for the Day 25-26 simulation.
 *
 * Story: A train management system models coaches as a linked list.
 *   - Each coach is a Node
 *   - Adding/removing coaches = insert/delete
 *   - Reversing train direction = reverse linked list
 *   - Circular route detection = cycle detection
 *   - Middle coach inspection = find middle
 *   - Merging two train schedules = merge sorted lists
 *
 * What this simulation demonstrates:
 *   1. Singly Linked List — insert at head/tail/position, delete, traverse
 *   2. Reverse — 3-pointer technique, O(n) time O(1) space
 *   3. Find Middle — Floyd's slow/fast pointer
 *   4. Cycle Detection — Floyd's Tortoise and Hare
 *   5. Merge Sorted Lists — two-pointer merge
 *   6. Doubly Linked List — bidirectional traversal, O(1) tail ops
 *   7. Nth from End — two-pointer technique
 *   8. Palindrome Check — reverse second half and compare
 *
 * How to run:
 *   javac Node.java DoublyNode.java SinglyLinkedList.java
 *         DoublyLinkedList.java LinkedListUtils.java TrainManagementSimulation.java
 *   java TrainManagementSimulation
 */
public class TrainManagementSimulation {

    /**
     * main(String[] args)
     *
     * Entry point of the program.
     * Runs all linked list operations on the train simulation.
     *
     * @param args Command-line arguments (not used)
     */
    public static void main(String[] args) {

        System.out.println("╔══════════════════════════════════════════════╗");
        System.out.println("║     TRAIN MANAGEMENT — LINKED LIST DEMO      ║");
        System.out.println("╚══════════════════════════════════════════════╝");

        /*
         * ── SINGLY LINKED LIST — INSERT OPERATIONS ──
         * Build a train by adding coaches at head, tail, and positions.
         */
        System.out.println("\n══════════════════════════════════════════════");
        System.out.println("  SINGLY LINKED LIST: Insert Operations");
        System.out.println("══════════════════════════════════════════════");

        SinglyLinkedList train = new SinglyLinkedList();

        train.insertAtTail(1);
        train.insertAtTail(2);
        train.insertAtTail(3);
        train.insertAtTail(4);
        train.insertAtTail(5);
        System.out.print("  After insertAtTail (1 to 5)  : ");
        train.display();

        train.insertAtHead(0);
        System.out.print("  After insertAtHead(0)         : ");
        train.display();

        train.insertAtPosition(99, 3);
        System.out.print("  After insertAtPosition(99, 3) : ");
        train.display();
        System.out.println("  Size: " + train.size());

        /*
         * ── DELETE OPERATIONS ──
         */
        System.out.println("\n══════════════════════════════════════════════");
        System.out.println("  SINGLY LINKED LIST: Delete Operations");
        System.out.println("══════════════════════════════════════════════");

        System.out.println("  Deleted head: " + train.deleteAtHead());
        System.out.print("  After deleteAtHead()          : ");
        train.display();

        System.out.println("  Deleted tail: " + train.deleteAtTail());
        System.out.print("  After deleteAtTail()          : ");
        train.display();

        train.deleteByValue(99);
        System.out.print("  After deleteByValue(99)       : ");
        train.display();

        train.deleteByValue(999); // not found
        System.out.println("  Size: " + train.size());

        /*
         * ── REVERSE — Reverse the entire train ──
         */
        System.out.println("\n══════════════════════════════════════════════");
        System.out.println("  REVERSE: Reverse entire train direction");
        System.out.println("══════════════════════════════════════════════");

        System.out.print("  Before reverse : ");
        train.display();
        train.reverse();
        System.out.print("  After reverse  : ");
        train.display();
        train.reverse(); // restore for further demos
        System.out.print("  Restored       : ");
        train.display();

        /*
         * ── FIND MIDDLE — Floyd's slow/fast pointer ──
         */
        System.out.println("\n══════════════════════════════════════════════");
        System.out.println("  FIND MIDDLE: Floyd's Two-Pointer");
        System.out.println("══════════════════════════════════════════════");

        System.out.print("  Train        : ");
        train.display();
        Node middle = train.findMiddle();
        System.out.println("  Middle coach : " + (middle != null ? middle.data : "null"));

        // Try with even-sized list
        SinglyLinkedList evenTrain = new SinglyLinkedList();
        for (int i = 1; i <= 6; i++) evenTrain.insertAtTail(i);
        System.out.print("  Even train   : ");
        evenTrain.display();
        Node evenMiddle = evenTrain.findMiddle();
        System.out.println("  Middle coach : " + (evenMiddle != null ? evenMiddle.data : "null")
                + "  (second middle for even-length list)");

        /*
         * ── CYCLE DETECTION — Floyd's Tortoise and Hare ──
         */
        System.out.println("\n══════════════════════════════════════════════");
        System.out.println("  CYCLE DETECTION: Floyd's Algorithm");
        System.out.println("══════════════════════════════════════════════");

        SinglyLinkedList normalTrain = new SinglyLinkedList();
        for (int i = 1; i <= 5; i++) normalTrain.insertAtTail(i);

        System.out.println("  Normal train (no cycle): hasCycle = " + normalTrain.hasCycle());

        SinglyLinkedList cyclicTrain = new SinglyLinkedList();
        for (int i = 1; i <= 5; i++) cyclicTrain.insertAtTail(i);
        cyclicTrain.createCycleForTesting(1); // tail connects back to node at index 1

        System.out.println("  Cyclic train (tail->node[1]): hasCycle = " + cyclicTrain.hasCycle());

        /*
         * ── MERGE SORTED LISTS ──
         */
        System.out.println("\n══════════════════════════════════════════════");
        System.out.println("  MERGE SORTED: Two Train Schedules");
        System.out.println("══════════════════════════════════════════════");

        SinglyLinkedList schedule1 = new SinglyLinkedList();
        schedule1.insertAtTail(1);
        schedule1.insertAtTail(3);
        schedule1.insertAtTail(5);
        schedule1.insertAtTail(7);
        schedule1.insertAtTail(9);

        SinglyLinkedList schedule2 = new SinglyLinkedList();
        schedule2.insertAtTail(2);
        schedule2.insertAtTail(4);
        schedule2.insertAtTail(6);
        schedule2.insertAtTail(8);
        schedule2.insertAtTail(10);

        LinkedListUtils.printMergeDemo(schedule1, schedule2);

        // Non-uniform sized merge
        SinglyLinkedList s3 = new SinglyLinkedList();
        s3.insertAtTail(1); s3.insertAtTail(4); s3.insertAtTail(7);

        SinglyLinkedList s4 = new SinglyLinkedList();
        s4.insertAtTail(2); s4.insertAtTail(3); s4.insertAtTail(5);
        s4.insertAtTail(6); s4.insertAtTail(8); s4.insertAtTail(9);

        System.out.println("\n  Non-uniform sized merge:");
        LinkedListUtils.printMergeDemo(s3, s4);

        /*
         * ── NTH FROM END — Two-pointer technique ──
         */
        System.out.println("\n══════════════════════════════════════════════");
        System.out.println("  NTH FROM END: Two-Pointer Technique");
        System.out.println("══════════════════════════════════════════════");

        System.out.print("  Train: ");
        train.display();
        System.out.println("  1st from end: " + LinkedListUtils.findNthFromEnd(train, 1));
        System.out.println("  2nd from end: " + LinkedListUtils.findNthFromEnd(train, 2));
        System.out.println("  3rd from end: " + LinkedListUtils.findNthFromEnd(train, 3));

        /*
         * ── PALINDROME CHECK ──
         */
        System.out.println("\n══════════════════════════════════════════════");
        System.out.println("  PALINDROME CHECK: Reverse Second Half");
        System.out.println("══════════════════════════════════════════════");

        SinglyLinkedList pal1 = new SinglyLinkedList();
        for (int v : new int[]{1, 2, 3, 2, 1}) pal1.insertAtTail(v);
        System.out.print("  List [1,2,3,2,1]  : ");
        pal1.display();
        System.out.println("  isPalindrome      : " + LinkedListUtils.isPalindrome(pal1));

        SinglyLinkedList pal2 = new SinglyLinkedList();
        for (int v : new int[]{1, 2, 3, 4, 5}) pal2.insertAtTail(v);
        System.out.print("  List [1,2,3,4,5]  : ");
        pal2.display();
        System.out.println("  isPalindrome      : " + LinkedListUtils.isPalindrome(pal2));

        /*
         * ── DOUBLY LINKED LIST ──
         */
        System.out.println("\n══════════════════════════════════════════════");
        System.out.println("  DOUBLY LINKED LIST: Bidirectional Operations");
        System.out.println("══════════════════════════════════════════════");

        DoublyLinkedList dTrain = new DoublyLinkedList();
        dTrain.insertAtTail(1);
        dTrain.insertAtTail(2);
        dTrain.insertAtTail(3);
        dTrain.insertAtTail(4);
        dTrain.insertAtTail(5);

        System.out.print("  Forward  : ");
        dTrain.displayForward();
        System.out.print("  Backward : ");
        dTrain.displayBackward();

        dTrain.insertAtHead(0);
        System.out.print("  After insertAtHead(0) forward : ");
        dTrain.displayForward();

        System.out.println("  Deleted head: " + dTrain.deleteAtHead());
        System.out.println("  Deleted tail: " + dTrain.deleteAtTail());
        System.out.print("  After delete head+tail : ");
        dTrain.displayForward();

        dTrain.deleteByValue(3);
        System.out.print("  After deleteByValue(3) : ");
        dTrain.displayForward();
        System.out.print("  Backward (verify prev) : ");
        dTrain.displayBackward();
        System.out.println("  Size: " + dTrain.size());

        /*
         * ── SINGLY VS DOUBLY COMPARISON ──
         */
        System.out.println("\n╔══════════════════════════════════════════════════════╗");
        System.out.println("║         SINGLY vs DOUBLY LINKED LIST                 ║");
        System.out.println("╠══════════════════════════════════════════════════════╣");
        System.out.println("║ Feature            Singly          Doubly            ║");
        System.out.println("║ ---------------    -----------     -----------       ║");
        System.out.println("║ Pointers/node      1 (next)        2 (next+prev)     ║");
        System.out.println("║ insertAtHead       O(1)            O(1)              ║");
        System.out.println("║ insertAtTail       O(n)            O(1)*             ║");
        System.out.println("║ deleteAtTail       O(n)            O(1)*             ║");
        System.out.println("║ Backward traversal NOT POSSIBLE    O(n)              ║");
        System.out.println("║ Memory             Less            More              ║");
        System.out.println("║ * = with tail pointer maintained                     ║");
        System.out.println("╚══════════════════════════════════════════════════════╝");
    }
}
