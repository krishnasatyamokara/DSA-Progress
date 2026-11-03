/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Pair {
    int value;
    ListNode node;

    Pair(int value, ListNode node) {
        this.value = value;
        this.node = node;
    }
}

class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<Pair> pq = new PriorityQueue<>(
            (a, b) -> Integer.compare(a.value, b.value)
        );

        for (ListNode node : lists) {
            if (node != null)
                pq.offer(new Pair(node.val, node));
        }

        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;

        while (!pq.isEmpty()) {
            Pair pair = pq.poll();

            curr.next = new ListNode(pair.value);
            curr = curr.next;

            if (pair.node.next != null) {
                ListNode next = pair.node.next;
                pq.offer(new Pair(next.val, next));
            }
        }

        return dummy.next;
    }
}