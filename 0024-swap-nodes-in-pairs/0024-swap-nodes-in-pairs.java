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
class Solution {
    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        ListNode curr = head;
        while(curr!=null && curr.next!=null){
            ListNode nextPair = curr.next.next;
            ListNode second = curr.next;
            second.next = curr;
            curr.next = nextPair;
            prev.next = second;

            prev=curr;
            curr=nextPair;
        }
        return dummy.next;
    }
}