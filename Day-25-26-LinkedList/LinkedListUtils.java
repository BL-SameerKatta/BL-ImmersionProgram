/**
 * LinkedListUtils.java
 *
 * Day 25-26 - DSA: Linked List
 * Concepts: Floyd's Cycle Detection, Floyd's Middle Finding,
 *           Merge Sorted Lists, Nth from End, Palindrome Check,
 *           Static utility methods
 *
 * This utility class provides advanced linked list algorithms
 * that operate on SinglyLinkedList objects.
 *
 * All methods are static — no object needed to call them.
 * They work directly with Node references for algorithmic clarity.
 *
 * Algorithms included:
 *   - detectCycleAndFindStart() : Floyd's full cycle detection
 *   - findNthFromEnd()          : two-pointer technique
 *   - isPalindrome()            : reverse second half and compare
 *   - printMergedSorted()       : visual merge demonstration
 */
public class LinkedListUtils {

    /**
     * detectCycleAndFindStart(SinglyLinkedList list)
     *
     * Uses Floyd's Cycle Detection Algorithm to:
     *   Phase 1: Detect if a cycle exists (slow + fast pointers meet)
     *   Phase 2: Find where the cycle starts (reset one pointer to head)
     *
     * Why phase 2 works:
     *   When slow and fast meet inside the cycle, the distance from
     *   head to cycle start = distance from meeting point to cycle start.
     *   So resetting one pointer to head and moving both by 1 step
     *   brings them to the cycle start simultaneously.
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     *
     * @param list The linked list to check for cycles
     * @return The Node where the cycle starts, or null if no cycle
     */
    public static Node detectCycleAndFindStart(SinglyLinkedList list) {
        Node head = list.getHead();
        if (head == null || head.next == null) return null;

        Node slow = head;
        Node fast = head;

        // Phase 1 — detect cycle
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) break; // cycle detected — they met inside
        }

        // No cycle found
        if (fast == null || fast.next == null) return null;

        /*
         * Phase 2 — find cycle start.
         * Reset one pointer to head, keep other at meeting point.
         * Move both one step at a time — they meet at cycle start.
         */
        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow; // cycle start node
    }

    /**
     * findNthFromEnd(SinglyLinkedList list, int n)
     *
     * Finds the Nth node from the end using two-pointer technique.
     * Does NOT require knowing the list size in advance.
     *
     * Algorithm:
     *   1. Move 'fast' pointer N steps ahead
     *   2. Move both 'slow' and 'fast' one step at a time
     *   3. When 'fast' reaches null, 'slow' is at Nth from end
     *
     * Example for N=2, list=[1,2,3,4,5]:
     *   fast moves 2 steps: fast at [3]
     *   both move until fast=null:
     *     fast=[4], slow=[2]
     *     fast=[5], slow=[3]
     *     fast=null, slow=[4] -> 2nd from end is 4
     *
     * Time Complexity: O(n) — single pass after initial N steps
     * Space Complexity: O(1)
     *
     * @param list The linked list to search
     * @param n    Position from end (1 = last node)
     * @return The data at Nth from end, -1 if invalid
     */
    public static int findNthFromEnd(SinglyLinkedList list, int n) {
        Node slow = list.getHead();
        Node fast = list.getHead();

        // Move fast n steps ahead
        for (int i = 0; i < n; i++) {
            if (fast == null) {
                System.out.println("  [ERROR] N=" + n + " exceeds list size.");
                return -1;
            }
            fast = fast.next;
        }

        // Move both until fast reaches null
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow.data;
    }

    /**
     * isPalindrome(SinglyLinkedList list)
     *
     * Checks if the linked list is a palindrome.
     * A palindrome reads the same forward and backward.
     * Example: [1, 2, 3, 2, 1] is a palindrome.
     *
     * Algorithm:
     *   1. Find middle using slow/fast pointers
     *   2. Reverse the second half in-place
     *   3. Compare first half with reversed second half
     *   4. Restore second half (optional — good practice)
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     *
     * @param list The linked list to check
     * @return true if palindrome, false otherwise
     */
    public static boolean isPalindrome(SinglyLinkedList list) {
        Node head = list.getHead();
        if (head == null || head.next == null) return true;

        // Step 1: Find middle
        Node slow = head;
        Node fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // Step 2: Reverse second half
        Node prev    = null;
        Node current = slow;
        while (current != null) {
            Node next    = current.next;
            current.next = prev;
            prev         = current;
            current      = next;
        }

        // Step 3: Compare both halves
        Node left  = head;
        Node right = prev; // head of reversed second half
        while (right != null) {
            if (left.data != right.data) return false;
            left  = left.next;
            right = right.next;
        }
        return true;
    }

    /**
     * printMergeDemo(SinglyLinkedList l1, SinglyLinkedList l2)
     *
     * Prints a visual demonstration of merging two sorted lists.
     * Shows both input lists and the resulting merged list.
     *
     * @param l1 First sorted linked list
     * @param l2 Second sorted linked list
     */
    public static void printMergeDemo(SinglyLinkedList l1, SinglyLinkedList l2) {
        System.out.println("  List 1 (sorted): ");
        l1.display();
        System.out.println("  List 2 (sorted): ");
        l2.display();

        SinglyLinkedList merged = SinglyLinkedList.mergeSorted(l1, l2);
        System.out.println("  Merged (sorted): ");
        merged.display();
        System.out.println("  Merged size: " + merged.size());
    }
}
