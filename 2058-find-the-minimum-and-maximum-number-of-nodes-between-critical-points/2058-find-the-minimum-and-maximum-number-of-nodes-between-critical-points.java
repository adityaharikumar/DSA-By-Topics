class Solution {
    public int[] nodesBetweenCriticalPoints(ListNode head) {

        if (head == null || head.next == null || head.next.next == null) {
            return new int[]{-1, -1};
        }

        ListNode prev = head;
        ListNode curr = head.next;

        int index = 1;

        int first = -1;
        int last = -1;

        int minDistance = Integer.MAX_VALUE;

        while (curr.next != null) {

            ListNode next = curr.next;

            boolean isMax = curr.val > prev.val && curr.val > next.val;
            boolean isMin = curr.val < prev.val && curr.val < next.val;

            if (isMax || isMin) {

                // First critical point
                if (first == -1) {
                    first = index;
                } else {
                    // Distance from previous critical point
                    minDistance = Math.min(minDistance, index - last);
                }

                // Update latest critical point
                last = index;
            }

            prev = curr;
            curr = curr.next;
            index++;
        }

        // Fewer than 2 critical points
        if (first == last) {
            return new int[]{-1, -1};
        }

        return new int[]{
            minDistance,
            last - first
        };
    }
}