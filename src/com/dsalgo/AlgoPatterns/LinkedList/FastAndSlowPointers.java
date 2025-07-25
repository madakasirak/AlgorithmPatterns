package com.dsalgo.AlgoPatterns.LinkedList;

import java.util.*;

/**
 * 🐢🐰 FAST AND SLOW POINTERS (LINKEDLIST) - PRACTICE PROBLEMS
 * 
 * This class contains comprehensive implementations of fast and slow pointer algorithms
 * for LinkedList problems. The pattern uses two pointers moving at different speeds
 * (tortoise and hare) to solve cycle detection, middle finding, intersection detection,
 * and various linked list manipulation problems with optimal time and space complexity.
 * 
 * Pattern Focus: Floyd's algorithm variations, dual-speed traversal, O(1) space solutions
 * Time Complexity: Generally O(n) with single pass through linked list
 * Space Complexity: O(1) - constant space regardless of input size
 */
public class FastAndSlowPointers {
    
    // ============================================================================
    // 🟢 EASY PROBLEMS - Basic Fast and Slow Pointer Concepts
    // ============================================================================
    
    /**
     * 🟢 EASY: Middle of the Linked List
     * 
     * Problem: Find middle node of singly linked list
     * LeetCode: https://leetcode.com/problems/middle-of-the-linked-list/
     * 
     * Approach: Fast and slow pointers
     * - Slow pointer moves 1 step, fast pointer moves 2 steps
     * - When fast reaches end, slow is at middle
     * - For even length, returns second middle
     * 
     * Time: O(n), Space: O(1)
     */
    public static ListNode middleNode(ListNode head) {
        if (head == null) {
            return null;
        }
        
        ListNode slow = head;
        ListNode fast = head;
        
        // Move fast 2 steps, slow 1 step each iteration
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        
        return slow; // Points to middle (second middle for even length)
    }
    
    /**
     * 🟢 EASY: Linked List Cycle
     * 
     * Problem: Detect if linked list has a cycle
     * LeetCode: https://leetcode.com/problems/linked-list-cycle/
     * 
     * Approach: Floyd's Cycle Detection (Tortoise and Hare)
     * - If cycle exists, fast and slow pointers will eventually meet
     * - Fast pointer gains 1 position per iteration in cycle
     * - Meeting guarantees cycle existence
     * 
     * Time: O(n), Space: O(1)
     */
    public static boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        
        ListNode slow = head;
        ListNode fast = head;
        
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            
            if (slow == fast) {
                return true; // Cycle detected
            }
        }
        
        return false; // No cycle
    }
    
    /**
     * 🟢 EASY: Remove Nth Node From End of List
     * 
     * Problem: Remove nth node from end in one pass
     * LeetCode: https://leetcode.com/problems/remove-nth-node-from-end-of-list/
     * 
     * Approach: Two pointers with gap maintenance
     * - Move fast pointer n+1 steps ahead
     * - Move both pointers until fast reaches end
     * - Slow pointer will be at node before target
     * 
     * Time: O(n), Space: O(1)
     */
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        // Use dummy node to handle edge case of removing head
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        
        ListNode slow = dummy;
        ListNode fast = dummy;
        
        // Move fast pointer n+1 steps ahead
        for (int i = 0; i <= n; i++) {
            fast = fast.next;
        }
        
        // Move both pointers until fast reaches end
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }
        
        // Remove the nth node from end
        slow.next = slow.next.next;
        
        return dummy.next;
    }
    
    /**
     * 🟢 EASY: Intersection of Two Linked Lists
     * 
     * Problem: Find intersection node of two linked lists
     * LeetCode: https://leetcode.com/problems/intersection-of-two-linked-lists/
     * 
     * Approach: Two pointers with path switching
     * - When pointer reaches end, redirect to other list's head
     * - Both pointers travel same total distance
     * - They meet at intersection or both become null
     * 
     * Time: O(m + n), Space: O(1)
     */
    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        
        ListNode pA = headA;
        ListNode pB = headB;
        
        // When reaching end, switch to other list
        while (pA != pB) {
            pA = (pA == null) ? headB : pA.next;
            pB = (pB == null) ? headA : pB.next;
        }
        
        return pA; // Intersection node or null
    }
    
    // ============================================================================
    // 🟡 MEDIUM PROBLEMS - Intermediate Fast and Slow Pointer Techniques
    // ============================================================================
    
    /**
     * 🟡 MEDIUM: Linked List Cycle II
     * 
     * Problem: Find the node where cycle begins
     * LeetCode: https://leetcode.com/problems/linked-list-cycle-ii/
     * 
     * Approach: Two-phase Floyd's algorithm
     * - Phase 1: Detect cycle using fast/slow pointers
     * - Phase 2: Find cycle start by resetting one pointer to head
     * - Mathematical proof: meeting point properties
     * 
     * Time: O(n), Space: O(1)
     */
    public static ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        
        ListNode slow = head;
        ListNode fast = head;
        
        // Phase 1: Detect cycle
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            
            if (slow == fast) {
                break; // Cycle detected
            }
        }
        
        // No cycle found
        if (fast == null || fast.next == null) {
            return null;
        }
        
        // Phase 2: Find cycle start
        slow = head; // Reset slow to head
        while (slow != fast) {
            slow = slow.next;   // Both move at same speed
            fast = fast.next;
        }
        
        return slow; // Cycle start node
    }
    
    /**
     * 🟡 MEDIUM: Odd Even Linked List
     * 
     * Problem: Group odd and even positioned nodes together
     * LeetCode: https://leetcode.com/problems/odd-even-linked-list/
     * 
     * Approach: Two pointers for odd/even separation
     * - Maintain separate chains for odd and even positioned nodes
     * - Connect odd chain to even chain at the end
     * - Preserve relative order within each group
     * 
     * Time: O(n), Space: O(1)
     */
    public static ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        
        ListNode odd = head;        // Points to odd positioned nodes
        ListNode even = head.next;  // Points to even positioned nodes
        ListNode evenHead = even;   // Keep reference to even head
        
        while (even != null && even.next != null) {
            odd.next = even.next;   // Connect odd nodes
            odd = odd.next;
            
            even.next = odd.next;   // Connect even nodes
            even = even.next;
        }
        
        odd.next = evenHead;        // Connect odd tail to even head
        
        return head;
    }
    
    /**
     * 🟡 MEDIUM: Palindrome Linked List
     * 
     * Problem: Check if linked list is palindrome
     * 
     * Approach: Find middle, reverse second half, compare
     * - Use fast/slow to find middle
     * - Reverse second half of list
     * - Compare first and reversed second half
     * - Optionally restore original structure
     * 
     * Time: O(n), Space: O(1)
     */
    public static boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        
        // Step 1: Find middle of list
        ListNode slow = head;
        ListNode fast = head;
        
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        
        // Step 2: Reverse second half
        ListNode secondHalf = reverseList(slow.next);
        slow.next = null; // Cut the list
        
        // Step 3: Compare both halves
        ListNode p1 = head;
        ListNode p2 = secondHalf;
        boolean isPalindrome = true;
        
        while (p2 != null) { // p2 is shorter or equal length
            if (p1.val != p2.val) {
                isPalindrome = false;
                break;
            }
            p1 = p1.next;
            p2 = p2.next;
        }
        
        // Step 4: Restore original list (optional)
        slow.next = reverseList(secondHalf);
        
        return isPalindrome;
    }
    
    /**
     * 🟡 MEDIUM: Reorder List
     * 
     * Problem: Reorder list as L0→Ln→L1→Ln-1→L2→Ln-2→...
     * 
     * Approach: Find middle, reverse second half, merge alternately
     * - Split list at middle using fast/slow pointers
     * - Reverse second half
     * - Merge two halves alternately
     * 
     * Time: O(n), Space: O(1)
     */
    public static void reorderList(ListNode head) {
        if (head == null || head.next == null) {
            return;
        }
        
        // Step 1: Find middle
        ListNode slow = head;
        ListNode fast = head;
        
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        
        // Step 2: Reverse second half
        ListNode secondHalf = reverseList(slow.next);
        slow.next = null; // Cut the list
        
        // Step 3: Merge alternately
        ListNode first = head;
        ListNode second = secondHalf;
        
        while (second != null) {
            ListNode temp1 = first.next;
            ListNode temp2 = second.next;
            
            first.next = second;
            second.next = temp1;
            
            first = temp1;
            second = temp2;
        }
    }
    
    // ============================================================================
    // 🔴 HARD PROBLEMS - Advanced Fast and Slow Pointer Applications
    // ============================================================================
    
    /**
     * 🔴 HARD: Sort List
     * 
     * Problem: Sort linked list using merge sort
     * 
     * Approach: Divide and conquer with fast/slow pointers
     * - Use fast/slow to find middle for splitting
     * - Recursively sort both halves
     * - Merge sorted halves
     * 
     * Time: O(n log n), Space: O(log n) for recursion
     */
    public static ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        
        // Find middle and split list
        ListNode middle = getMiddleAndSplit(head);
        
        // Recursively sort both halves
        ListNode left = sortList(head);
        ListNode right = sortList(middle);
        
        // Merge sorted halves
        return mergeTwoSortedLists(left, right);
    }
    
    private static ListNode getMiddleAndSplit(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        ListNode prev = null;
        
        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        
        // Split the list
        prev.next = null;
        return slow;
    }
    
    /**
     * 🔴 HARD: Find Duplicate Number
     * 
     * Problem: Find duplicate in array using linked list cycle detection
     * 
     * Approach: Treat array as linked list (index as next pointer)
     * - Array values are indices pointing to next position
     * - Duplicate creates cycle in this virtual linked list
     * - Use Floyd's algorithm to find cycle start
     * 
     * Time: O(n), Space: O(1)
     */
    public static int findDuplicate(int[] nums) {
        // Treat array as linked list: nums[i] points to nums[nums[i]]
        int slow = nums[0];
        int fast = nums[0];
        
        // Phase 1: Detect cycle
        do {
            slow = nums[slow];           // Move 1 step
            fast = nums[nums[fast]];     // Move 2 steps
        } while (slow != fast);
        
        // Phase 2: Find cycle start (duplicate number)
        slow = nums[0];
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        
        return slow;
    }
    
    /**
     * 🔴 HARD: Copy List with Random Pointer
     * 
     * Problem: Deep copy linked list with random pointers
     * 
     * Approach: Three-pass algorithm without extra space
     * - Pass 1: Create new nodes and interweave with original
     * - Pass 2: Set random pointers for new nodes
     * - Pass 3: Separate original and copied lists
     * 
     * Time: O(n), Space: O(1) excluding output
     */
    public static Node copyRandomList(Node head) {
        if (head == null) return null;
        
        // Pass 1: Create new nodes and interweave
        Node curr = head;
        while (curr != null) {
            Node newNode = new Node(curr.val);
            newNode.next = curr.next;
            curr.next = newNode;
            curr = newNode.next;
        }
        
        // Pass 2: Set random pointers
        curr = head;
        while (curr != null) {
            if (curr.random != null) {
                curr.next.random = curr.random.next;
            }
            curr = curr.next.next;
        }
        
        // Pass 3: Separate lists
        Node dummy = new Node(0);
        Node copyCurr = dummy;
        curr = head;
        
        while (curr != null) {
            copyCurr.next = curr.next;
            curr.next = curr.next.next;
            copyCurr = copyCurr.next;
            curr = curr.next;
        }
        
        return dummy.next;
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
    
    // Node class with random pointer
    static class Node {
        int val;
        Node next;
        Node random;
        
        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
    
    /**
     * Helper: Reverse linked list
     */
    private static ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode current = head;
        
        while (current != null) {
            ListNode next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        
        return prev;
    }
    
    /**
     * Helper: Merge two sorted linked lists
     */
    private static ListNode mergeTwoSortedLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                current.next = l1;
                l1 = l1.next;
            } else {
                current.next = l2;
                l2 = l2.next;
            }
            current = current.next;
        }
        
        current.next = (l1 != null) ? l1 : l2;
        return dummy.next;
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
     * Helper: Create cycle in linked list for testing
     */
    public static void createCycle(ListNode head, int pos) {
        if (head == null || pos < 0) return;
        
        ListNode tail = head;
        ListNode cycleNode = null;
        int index = 0;
        
        // Find tail and cycle position
        while (tail.next != null) {
            if (index == pos) {
                cycleNode = tail;
            }
            tail = tail.next;
            index++;
        }
        
        // Create cycle
        if (cycleNode != null) {
            tail.next = cycleNode;
        }
    }
    
    // ============================================================================
    // 🧪 TESTING AND DEMONSTRATION
    // ============================================================================
    
    public static void main(String[] args) {
        System.out.println("🐢🐰 FAST AND SLOW POINTERS (LINKEDLIST) - PRACTICE PROBLEMS");
        System.out.println("============================================================");
        
        // Test Easy Problems
        System.out.println("\n🟢 EASY FAST AND SLOW POINTER PROBLEMS:");
        
        // Middle of Linked List
        ListNode list1 = createList(new int[]{1, 2, 3, 4, 5});
        ListNode middle = middleNode(list1);
        System.out.println("Middle of [1,2,3,4,5]: " + middle.val);
        
        ListNode list2 = createList(new int[]{1, 2, 3, 4, 5, 6});
        ListNode middle2 = middleNode(list2);
        System.out.println("Middle of [1,2,3,4,5,6]: " + middle2.val);
        
        // Cycle Detection
        ListNode cycleList = createList(new int[]{3, 2, 0, -4});
        System.out.println("Has cycle (no cycle): " + hasCycle(cycleList));
        createCycle(cycleList, 1); // Create cycle
        System.out.println("Has cycle (with cycle): " + hasCycle(cycleList));
        
        // Remove Nth from End
        ListNode removeList = createList(new int[]{1, 2, 3, 4, 5});
        System.out.print("Original: ");
        printList(removeList);
        ListNode modified = removeNthFromEnd(removeList, 2);
        System.out.print("After removing 2nd from end: ");
        printList(modified);
        
        // Intersection of Two Lists
        ListNode headA = createList(new int[]{4, 1, 8, 4, 5});
        ListNode headB = createList(new int[]{5, 6, 1});
        // Create intersection manually for testing
        headB.next.next.next = headA.next.next; // Both point to node with value 8
        ListNode intersection = getIntersectionNode(headA, headB);
        System.out.println("Intersection value: " + (intersection != null ? intersection.val : "null"));
        
        // Test Medium Problems
        System.out.println("\n🟡 MEDIUM FAST AND SLOW POINTER PROBLEMS:");
        
        // Cycle Detection II
        ListNode cycle2List = createList(new int[]{3, 2, 0, -4});
        createCycle(cycle2List, 1);
        ListNode cycleStart = detectCycle(cycle2List);
        System.out.println("Cycle starts at value: " + (cycleStart != null ? cycleStart.val : "null"));
        
        // Odd Even List
        ListNode oddEvenList = createList(new int[]{1, 2, 3, 4, 5});
        System.out.print("Original: ");
        printList(oddEvenList);
        ListNode reordered = oddEvenList(oddEvenList);
        System.out.print("Odd-Even reordered: ");
        printList(reordered);
        
        // Palindrome Check
        ListNode palindromeList = createList(new int[]{1, 2, 2, 1});
        System.out.println("Is [1,2,2,1] palindrome: " + isPalindrome(palindromeList));
        
        ListNode notPalindromeList = createList(new int[]{1, 2, 3, 4});
        System.out.println("Is [1,2,3,4] palindrome: " + isPalindrome(notPalindromeList));
        
        // Reorder List
        ListNode reorderList = createList(new int[]{1, 2, 3, 4});
        System.out.print("Original: ");
        printList(reorderList);
        reorderList(reorderList);
        System.out.print("After reordering: ");
        printList(reorderList);
        
        // Test Hard Problems
        System.out.println("\n🔴 HARD FAST AND SLOW POINTER PROBLEMS:");
        
        // Sort List
        ListNode unsortedList = createList(new int[]{4, 2, 1, 3});
        System.out.print("Unsorted: ");
        printList(unsortedList);
        ListNode sorted = sortList(unsortedList);
        System.out.print("Sorted: ");
        printList(sorted);
        
        // Find Duplicate Number
        int[] duplicateArray = {1, 3, 4, 2, 2};
        int duplicate = findDuplicate(duplicateArray);
        System.out.println("Duplicate in [1,3,4,2,2]: " + duplicate);
        
        // Performance Analysis
        System.out.println("\n⚡ COMPLEXITY ANALYSIS:");
        System.out.println("Middle Finding: O(n) time, O(1) space - single pass");
        System.out.println("Cycle Detection: O(n) time, O(1) space - Floyd's algorithm");
        System.out.println("Remove Nth from End: O(n) time, O(1) space - gap maintenance");
        System.out.println("Intersection Detection: O(m+n) time, O(1) space - path switching");
        System.out.println("Palindrome Check: O(n) time, O(1) space - reverse and compare");
        
        System.out.println("\n✅ Key Fast and Slow Pointer Principles:");
        System.out.println("1. Dual-speed movement creates relative positioning");
        System.out.println("2. Convergence in cycles due to speed difference");
        System.out.println("3. Single-pass solutions with constant space");
        System.out.println("4. Mathematical relationships enable elegant algorithms");
        System.out.println("5. Proper null checking essential for robustness");
        System.out.println("6. Dummy nodes simplify edge case handling");
        System.out.println("7. Gap maintenance crucial for position-based operations");
    }
} 