class Solution {
    public Node flatten(Node head) {

        if (head == null) {
            return head;
        }

        Node curr = head;

        while (curr != null) {

            if (curr.child != null) {

                Node next = curr.next;

                curr.next = curr.child;
                curr.child.prev = curr;

                Node tailchild = flatten(curr.child);
                while(tailchild.next!=null){
                    tailchild=tailchild.next;
                }

                curr.child = null;

                if (next != null) {
                    tailchild.next = next;
                    next.prev = tailchild;
                }
            }

            curr = curr.next;
        }

        return head;
    }
}