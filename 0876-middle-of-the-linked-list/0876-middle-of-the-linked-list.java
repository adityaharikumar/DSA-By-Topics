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
    public ListNode middleNode(ListNode head) {
      ListNode curr = head;
      ListNode prev = head;
      while(curr!=null && curr.next!=null){
        prev=prev.next;
        curr=curr.next.next;
        
      }  
      return prev;
    }
}