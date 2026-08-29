class Solution {
    public void reorderList(ListNode head) {

        if (head == null || head.next == null) {
            return;
        }

        ListNode start = head;

        while (start != null && start.next != null) {

            ListNode end = start;
            ListNode prev = null;

            // Find the last node
            while (end.next != null) {
                prev = end;
                end = end.next;
            }

            // If only one node remains
            if (prev == null) {
                break;
            }

            // Remove last node
            prev.next = null;

            // Insert last node after start
            end.next = start.next;
            start.next = end;

            // Move start to next original node
            start = end.next;
        }
    }
}