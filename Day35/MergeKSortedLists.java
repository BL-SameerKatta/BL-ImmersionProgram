import java.util.PriorityQueue;

/**
 * Merges K sorted linked lists using a Min-Heap.
 */
public class MergeKSortedLists {
    public static ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>((a, b) -> Integer.compare(a.val, b.val));
        
        for (ListNode node : lists) {
            if (node != null) {
                minHeap.offer(node);
            }
        }
        
        ListNode dummyHead = new ListNode(0);
        ListNode current = dummyHead;
        
        while (!minHeap.isEmpty()) {
            ListNode smallestNode = minHeap.poll();
            current.next = smallestNode;
            current = current.next;
            
            if (smallestNode.next != null) {
                minHeap.offer(smallestNode.next);
            }
        }
        
        return dummyHead.next;
    }
}
