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
    public ListNode partition(ListNode head, int x) {
        ListNode lessDummy = new ListNode(0);
        ListNode greaterDummy = new ListNode(0);
        ListNode less = lessDummy;
        ListNode great = greaterDummy;
        ListNode curr = head;
        while(curr!=null){
            if(curr.val<x){
                less.next = curr;
                less = less.next;
            }else{
                great.next = curr;
                great = great.next;
            }
            curr= curr.next;
        }
        great.next = null;
        less.next=greaterDummy.next;
        return lessDummy.next;
    }
}