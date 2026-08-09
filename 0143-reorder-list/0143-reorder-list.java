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
    public void reorderList(ListNode head) {
        if(head==null || head.next==null){
            return ;
        }
        ListNode start = head;
        while(start.next!=null){
            ListNode prev = null;
            ListNode end = start;
            while(end.next!=null){
                prev=end;
                end=end.next;
            }
            prev.next=null;
            end.next=start.next;
            start.next=end;
            start=end.next;
            if(start==null){
                break;
            }
        }
    }
}