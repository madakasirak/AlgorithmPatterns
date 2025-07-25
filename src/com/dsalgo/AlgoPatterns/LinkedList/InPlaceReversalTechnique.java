package com.dsalgo.AlgoPatterns.LinkedList;

import java.util.*;

/**
 * 🔄 IN-PLACE REVERSAL TECHNIQUE (LINKEDLIST) - PRACTICE PROBLEMS
 * 
 * This class contains comprehensive implementations of in-place reversal techniques
 * for LinkedList problems. The pattern involves manipulating pointer relationships
 * directly without using additional data structures, achieving efficient O(1) space
 * complexity. These algorithms are ideal for memory-constrained environments and
 * large datasets where space efficiency is crucial.
 * 
 * Pattern Focus: Pointer manipulation, iterative approach, O(1) space complexity
 * Time Complexity: Generally O(n) with single pass through linked lists
 * Space Complexity: O(1) additional space regardless of input size
 */
public class InPlaceReversalTechnique {
    
    // ============================================================================
    // 🟢 EASY PROBLEMS - Basic In-Place Reversal Concepts
    // ============================================================================
    
    /**
     * 🟢 EASY: Reverse Linked List
     * 
     * Problem: Reverse a singly linked list iteratively
     * LeetCode: https://leetcode.com/problems/reverse-linked-list/
     * 
     * Approach: Three-pointer technique
     * - Track previous, current, and next nodes
     * - Iteratively reverse connections
     * - Return new head (original tail)
     * 
     * Time: O(n), Space: O(1)
     */
    public static ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        
        while (curr != null) {
            ListNode next = curr.next;  // Store next node
            curr.next = prev;           // Reverse the link
            prev = curr;                // Move prev forward
            curr = next;                // Move curr forward
        }
        
        return prev; // New head of reversed list
    }
    
    /**
     * 🟢 EASY: Remove Nth Node From End
     * 
     * Problem: Remove nth node from end of list
     * 
     * Approach: Two-pointer technique with in-place removal
     * - Use fast/slow pointers to find position
     * - Remove node by adjusting pointers
     * - Handle edge case where head is removed
     * 
     * Time: O(n), Space: O(1)
     */
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode fast = dummy;
        ListNode slow = dummy;
        
        // Move fast pointer n+1 steps ahead
        for (int i = 0; i <= n; i++) {
            fast = fast.next;
        }
        
        // Move both pointers until fast reaches end
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        
        // Remove the nth node from end
        slow.next = slow.next.next;
        
        return dummy.next;
    }
    
    // ============================================================================
    // 🟡 MEDIUM PROBLEMS - Intermediate In-Place Reversal Techniques
    // ============================================================================
    
    /**
     * 🟡 MEDIUM: Swap Nodes in Pairs
     * 
     * Problem: Swap every two adjacent nodes in the linked list
     * LeetCode: https://leetcode.com/problems/swap-nodes-in-pairs/
     * 
     * Approach: Iterative pair swapping with dummy node
     * - Use dummy to simplify head handling
     * - Track previous node to connect swapped pairs
     * - Swap pairs iteratively
     * 
     * Time: O(n), Space: O(1)
     */
    public static ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        
        while (prev.next != null && prev.next.next != null) {
            // Identify nodes to swap
            ListNode first = prev.next;
            ListNode second = prev.next.next;
            
            // Perform swap
            prev.next = second;
            first.next = second.next;
            second.next = first;
            
            // Move to next pair
            prev = first;
        }
        
        return dummy.next;
    }
    
    /**
     * 🟡 MEDIUM: Rotate List
     * 
     * Problem: Rotate list to the right by k places
     * LeetCode: https://leetcode.com/problems/rotate-list/
     * 
     * Approach: Find rotation point and reconnect
     * - Calculate effective rotation (k % length)
     * - Find new tail and head positions
     * - Break and reconnect the list
     * 
     * Time: O(n), Space: O(1)
     */
    public static ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null || k == 0) {
            return head;
        }
        
        // Step 1: Find length and connect tail to head
        ListNode tail = head;
        int length = 1;
        while (tail.next != null) {
            tail = tail.next;
            length++;
        }
        tail.next = head; // Make circular
        
        // Step 2: Find new tail (length - k % length - 1 steps from head)
        k = k % length;
        int stepsToNewTail = length - k;
        ListNode newTail = head;
        for (int i = 1; i < stepsToNewTail; i++) {
            newTail = newTail.next;
        }
        
        // Step 3: Break circle and return new head
        ListNode newHead = newTail.next;
        newTail.next = null;
        
        return newHead;
    }
    
    /**
     * 🟡 MEDIUM: Reorder List
     * 
     * Problem: Reorder list to L0→Ln→L1→Ln-1→L2→Ln-2→...
     * LeetCode: https://leetcode.com/problems/reorder-list/
     * 
     * Approach: Split, reverse, merge
     * - Find middle using slow/fast pointers
     * - Reverse second half
     * - Merge two halves alternately
     * 
     * Time: O(n), Space: O(1)
     */
    public static void reorderList(ListNode head) {
        if (head == null || head.next == null) {
            return;
        }
        
        // Step 1: Find middle using slow/fast pointers
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        
        // Step 2: Reverse second half
        ListNode secondHalf = reverseList(slow.next);
        slow.next = null; // Cut the connection
        
        // Step 3: Merge two halves alternately
        ListNode first = head;
        ListNode second = secondHalf;
        
        while (second != null) {
            ListNode nextFirst = first.next;
            ListNode nextSecond = second.next;
            
            first.next = second;
            second.next = nextFirst;
            
            first = nextFirst;
            second = nextSecond;
        }
    }
    
    /**
     * 🟡 MEDIUM: Split Linked List in Parts
     * 
     * Problem: Split linked list into k consecutive parts
     * LeetCode: https://leetcode.com/problems/split-linked-list-in-parts/
     * 
     * Approach: Calculate part sizes and split
     * - Determine base size and remainder
     * - Distribute nodes among parts
     * - Break connections between parts
     * 
     * Time: O(n + k), Space: O(k) for result array
     */
    public static ListNode[] splitListToParts(ListNode head, int k) {
        ListNode[] result = new ListNode[k];
        
        // Calculate length
        int length = 0;
        ListNode curr = head;
        while (curr != null) {
            length++;
            curr = curr.next;
        }
        
        // Calculate part sizes
        int partSize = length / k;
        int remainder = length % k;
        
        curr = head;
        for (int i = 0; i < k && curr != null; i++) {
            result[i] = curr;
            
            // Current part size (first 'remainder' parts get extra node)
            int currentSize = partSize + (remainder > 0 ? 1 : 0);
            remainder--;
            
            // Move to end of current part
            for (int j = 1; j < currentSize; j++) {
                curr = curr.next;
            }
            
            // Cut the connection
            ListNode next = curr.next;
            curr.next = null;
            curr = next;
        }
        
        return result;
    }
    
    // ============================================================================
    // 🔴 HARD PROBLEMS - Advanced In-Place Reversal Applications
    // ============================================================================
    
    /**
     * 🔴 HARD: Reverse Nodes in k-Group
     * 
     * Problem: Reverse nodes in groups of k
     * LeetCode: https://leetcode.com/problems/reverse-nodes-in-k-group/
     * 
     * Approach: Iterative group reversal
     * - Check if k nodes available
     * - Reverse current group
     * - Connect with previous and next groups
     * 
     * Time: O(n), Space: O(1)
     */
    public static ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k == 1) {
            return head;
        }
        
        // Check if we have at least k nodes
        ListNode curr = head;
        for (int i = 0; i < k; i++) {
            if (curr == null) {
                return head; // Less than k nodes, return as is
            }
            curr = curr.next;
        }
        
        // Reverse current group
        ListNode prev = null;
        curr = head;
        for (int i = 0; i < k; i++) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        
        // Recursively reverse rest of the groups
        head.next = reverseKGroup(curr, k);
        
        return prev; // New head of current group
    }
    
    /**
     * 🔴 HARD: Reverse Linked List II
     * 
     * Problem: Reverse nodes from position left to right
     * 
     * Approach: Segment reversal with connection management
     * - Navigate to segment start
     * - Reverse specified segment
     * - Connect reversed segment properly
     * 
     * Time: O(n), Space: O(1)
     */
    public static ListNode reverseBetween(ListNode head, int left, int right) {
        if (head == null || left == right) {
            return head;
        }
        
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prevLeft = dummy;
        
        // Navigate to position before left
        for (int i = 0; i < left - 1; i++) {
            prevLeft = prevLeft.next;
        }
        
        // Reverse segment from left to right
        ListNode prev = null;
        ListNode curr = prevLeft.next;
        
        for (int i = 0; i < right - left + 1; i++) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        
        // Connect reversed segment
        prevLeft.next.next = curr; // Connect tail of reversed to remaining
        prevLeft.next = prev;      // Connect head of reversed to previous
        
        return dummy.next;
    }
    
    /**
     * 🔴 HARD: Odd Even Linked List
     * 
     * Problem: Group odd and even positioned nodes together
     * 
     * Approach: In-place partitioning with pointer manipulation
     * - Separate odd and even positioned nodes
     * - Maintain two chains
     * - Connect odd chain to even chain
     * 
     * Time: O(n), Space: O(1)
     */
    public static ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        
        ListNode odd = head;
        ListNode even = head.next;
        ListNode evenHead = even;
        
        while (even != null && even.next != null) {
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }
        
        odd.next = evenHead; // Connect odd chain to even chain
        
        return head;
    }
    
    // ============================================================================
    // 🔧 HELPER METHODS AND CLASSES
    // ============================================================================
    
    // Standard ListNode class
    static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
    
    /**
     * Helper: Create linked list from array
     */
    public static ListNode createList(int[] vals) {
        if (vals.length == 0) return null;
        
        ListNode head = new ListNode(vals[0]);
        ListNode current = head;
        
        for (int i = 1; i < vals.length; i++) {
            current.next = new ListNode(vals[i]);
            current = current.next;
        }
        
        return head;
    }
    
    /**
     * Helper: Print linked list
     */
    public static void printList(ListNode head) {
        ListNode current = head;
        while (current != null) {
            System.out.print(current.val);
            if (current.next != null) {
                System.out.print(" -> ");
            }
            current = current.next;
        }
        System.out.println();
    }
    
    /**
     * Helper: Print array of linked lists
     */
    public static void printListArray(ListNode[] lists) {
        for (int i = 0; i < lists.length; i++) {
            System.out.print("Part " + (i + 1) + ": ");
            if (lists[i] != null) {
                printList(lists[i]);
            } else {
                System.out.println("null");
            }
        }
    }
    
    /**
     * Helper: Convert linked list to array for verification
     */
    public static int[] listToArray(ListNode head) {
        List<Integer> result = new ArrayList<>();
        ListNode current = head;
        
        while (current != null) {
            result.add(current.val);
            current = current.next;
        }
        
        return result.stream().mapToInt(i -> i).toArray();
    }
    
    /**
     * Helper: Calculate linked list length
     */
    public static int getLength(ListNode head) {
        int length = 0;
        ListNode current = head;
        
        while (current != null) {
            length++;
            current = current.next;
        }
        
        return length;
    }
    
    /**
     * Helper: Get middle node of linked list
     */
    public static ListNode getMiddle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        
        return slow;
    }
    
    // ============================================================================
    // 🧪 TESTING AND DEMONSTRATION
    // ============================================================================
    
    public static void main(String[] args) {
        System.out.println("🔄 IN-PLACE REVERSAL TECHNIQUE (LINKEDLIST) - PRACTICE PROBLEMS");
        System.out.println("================================================================");
        
        // Test Easy Problems
        System.out.println("\n🟢 EASY IN-PLACE REVERSAL PROBLEMS:");
        
        // Reverse List
        ListNode list1 = createList(new int[]{1, 2, 3, 4, 5});
        System.out.print("Original: ");
        printList(list1);
        ListNode reversed = reverseList(list1);
        System.out.print("Reversed: ");
        printList(reversed);
        
        // Remove Nth From End
        ListNode list2 = createList(new int[]{1, 2, 3, 4, 5});
        System.out.print("Original: ");
        printList(list2);
        ListNode removed = removeNthFromEnd(list2, 2);
        System.out.print("Remove 2nd from end: ");
        printList(removed);
        
        // Test Medium Problems
        System.out.println("\n🟡 MEDIUM IN-PLACE REVERSAL PROBLEMS:");
        
        // Swap Pairs
        ListNode list3 = createList(new int[]{1, 2, 3, 4, 5, 6});
        System.out.print("Original: ");
        printList(list3);
        ListNode swapped = swapPairs(list3);
        System.out.print("Swapped pairs: ");
        printList(swapped);
        
        // Rotate List
        ListNode list4 = createList(new int[]{1, 2, 3, 4, 5});
        System.out.print("Original: ");
        printList(list4);
        ListNode rotated = rotateRight(list4, 2);
        System.out.print("Rotated right by 2: ");
        printList(rotated);
        
        // Reorder List
        ListNode list5 = createList(new int[]{1, 2, 3, 4, 5});
        System.out.print("Original: ");
        printList(list5);
        reorderList(list5);
        System.out.print("Reordered: ");
        printList(list5);
        
        // Split List
        ListNode list6 = createList(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
        System.out.print("Original: ");
        printList(list6);
        ListNode[] parts = splitListToParts(list6, 3);
        System.out.println("Split into 3 parts:");
        printListArray(parts);
        
        // Test Hard Problems
        System.out.println("\n🔴 HARD IN-PLACE REVERSAL PROBLEMS:");
        
        // Reverse K Group
        ListNode list7 = createList(new int[]{1, 2, 3, 4, 5, 6, 7, 8});
        System.out.print("Original: ");
        printList(list7);
        ListNode kReversed = reverseKGroup(list7, 3);
        System.out.print("Reverse in groups of 3: ");
        printList(kReversed);
        
        // Reverse Between
        ListNode list8 = createList(new int[]{1, 2, 3, 4, 5});
        System.out.print("Original: ");
        printList(list8);
        ListNode betweenReversed = reverseBetween(list8, 2, 4);
        System.out.print("Reverse between positions 2-4: ");
        printList(betweenReversed);
        
        // Odd Even List
        ListNode list9 = createList(new int[]{1, 2, 3, 4, 5, 6});
        System.out.print("Original: ");
        printList(list9);
        ListNode oddEven = oddEvenList(list9);
        System.out.print("Odd-even grouped: ");
        printList(oddEven);
        
        // Performance Analysis
        System.out.println("\n⚡ COMPLEXITY ANALYSIS:");
        System.out.println("Reverse List: O(n) time, O(1) space - single pass");
        System.out.println("Swap Pairs: O(n) time, O(1) space - iterative swapping");
        System.out.println("Rotate List: O(n) time, O(1) space - circular manipulation");
        System.out.println("Reorder List: O(n) time, O(1) space - split, reverse, merge");
        System.out.println("Split List: O(n+k) time, O(k) space - for result array");
        System.out.println("Reverse K Group: O(n) time, O(1) space - group-wise reversal");
        
        System.out.println("\n✅ Key In-Place Reversal Principles:");
        System.out.println("1. Use three-pointer technique (prev, curr, next)");
        System.out.println("2. Always store next node before breaking connections");
        System.out.println("3. Maintain O(1) space complexity throughout");
        System.out.println("4. Handle edge cases (empty, single node) first");
        System.out.println("5. Use dummy nodes for complex head manipulations");
        System.out.println("6. Verify proper null termination after operations");
        System.out.println("7. Draw diagrams to visualize pointer movements");
        System.out.println("8. Test with various input sizes and edge cases");
    }
} 