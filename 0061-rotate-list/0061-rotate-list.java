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
    public ListNode rotateRight(ListNode head, int k) {


        if (head == null || head.next == null) {
            return head;
        }
        int length=1;
        ListNode curr = head;
        while(curr.next!=null){
            curr=curr.next;
            length++;
        }
        k=k%length;
        if(k==0){
            return head;
        }
        curr.next=head;
        int step = length-k;
        curr=head;
        for(int i=1;i<step;i++){
            curr=curr.next;
        }
        ListNode newhead=curr.next;
        curr.next=null;
        return newhead;
    }
}