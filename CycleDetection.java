

    // Complete the hasCycle function below.

    /*
     * For your reference:
     *
     * SinglyLinkedListNode {
     *     int data;
     *     SinglyLinkedListNode next;
     * }
     *
     */
    static boolean hasCycle(SinglyLinkedListNode head) {
    if (head == null || head.next == null) {
        return false;
    }
    
    SinglyLinkedListNode slow = head;
    SinglyLinkedListNode fast = head;
    
    while (fast != null && fast.next != null) {
        slow = slow.next;          // Move one step
        fast = fast.next.next;     // Move two steps
        if (slow == fast) {        // Cycle detected
            return true;
        }
    }
    
    return false;
}

