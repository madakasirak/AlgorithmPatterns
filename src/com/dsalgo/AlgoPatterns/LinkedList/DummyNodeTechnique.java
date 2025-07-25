package com.dsalgo.AlgoPatterns.LinkedList;

import java.util.*;

/**
 * 🎭 DUMMY NODE TECHNIQUE (LINKEDLIST) - PRACTICE PROBLEMS
 * 
 * This class contains comprehensive implementations of dummy node techniques for
 * LinkedList problems. The pattern uses a temporary "dummy" or "sentinel" node
 * to simplify edge cases, eliminate special head handling, and make pointer
 * manipulation more uniform and robust. The dummy node acts as a stable anchor
 * point and is typically discarded at the end.
 * 
 * Pattern Focus: Edge case simplification, uniform pointer handling, head modification safety
 * Time Complexity: Generally O(n) with single pass through linked lists
 * Space Complexity: O(1) additional space for dummy nodes
 */
public class DummyNodeTechnique {
    
    // ============================================================================
    // 🟢 EASY PROBLEMS - Basic Dummy Node Concepts
    // ============================================================================
    
    /**
     * 🟢 EASY: Add Two Numbers
     * 
     * Problem: Add two numbers represented as linked lists (digits in reverse order)
     * LeetCode: https://leetcode.com/problems/add-two-numbers/
     * 
     * Approach: Dummy node for result construction
     * - Use dummy to build result list without head special cases
     * - Handle carry propagation through digits
     * - Continue until all digits and carry processed
     * 
     * Time: O(max(m,n)), Space: O(max(m,n)) for result
     */
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        int carry = 0;
        
        // Process both lists and any remaining carry
        while (l1 != null || l2 != null || carry != 0) {
            int sum = carry;
            
            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }
            
            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }
            
            carry = sum / 10;
            current.next = new ListNode(sum % 10);
            current = current.next;
        }
        
        return dummy.next;
    }
    
    /**
     * 🟢 EASY: Merge Two Sorted Lists
     * 
     * Problem: Merge two sorted linked lists into one sorted list
     * LeetCode: https://leetcode.com/problems/merge-two-sorted-lists/
     * 
     * Approach: Dummy node for simplified merging
     * - Compare values and attach smaller node to result
     * - No special case for determining result head
     * - Attach remaining elements at the end
     * 
     * Time: O(m + n), Space: O(1)
     */
    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        
        // Merge while both lists have elements
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                current.next = list1;
                list1 = list1.next;
            } else {
                current.next = list2;
                list2 = list2.next;
            }
            current = current.next;
        }
        
        // Attach remaining elements
        current.next = (list1 != null) ? list1 : list2;
        
        return dummy.next;
    }
    
    /**
     * 🟢 EASY: Remove Duplicates from Sorted List
     * 
     * Problem: Remove all duplicate values from sorted linked list
     * LeetCode: https://leetcode.com/problems/remove-duplicates-from-sorted-list/
     * 
     * Approach: In-place removal with dummy for head safety
     * - Use dummy to handle case where head itself is duplicate
     * - Skip nodes with same value as current node
     * - Maintain single instance of each value
     * 
     * Time: O(n), Space: O(1)
     */
    public static ListNode deleteDuplicates(ListNode head) {
        if (head == null) return null;
        
        ListNode current = head;
        
        while (current != null && current.next != null) {
            if (current.val == current.next.val) {
                current.next = current.next.next; // Skip duplicate
            } else {
                current = current.next; // Move to next unique value
            }
        }
        
        return head;
    }
    
    /**
     * 🟢 EASY: Remove Elements
     * 
     * Problem: Remove all nodes with specific value from linked list
     * 
     * Approach: Dummy node for head modification safety
     * - Dummy prevents special handling when head needs removal
     * - Skip nodes with target value
     * - Maintain connections for remaining nodes
     * 
     * Time: O(n), Space: O(1)
     */
    public static ListNode removeElements(ListNode head, int val) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode current = dummy;
        
        while (current.next != null) {
            if (current.next.val == val) {
                current.next = current.next.next; // Remove node
            } else {
                current = current.next; // Move forward
            }
        }
        
        return dummy.next;
    }
    
    // ============================================================================
    // 🟡 MEDIUM PROBLEMS - Intermediate Dummy Node Techniques
    // ============================================================================
    
    /**
     * 🟡 MEDIUM: Partition List
     * 
     * Problem: Partition list so that nodes < x come before nodes >= x
     * LeetCode: https://leetcode.com/problems/partition-list/
     * 
     * Approach: Two dummy heads for two partitions
     * - Separate dummy for "less than" and "greater equal" partitions
     * - Distribute nodes to appropriate partition
     * - Connect partitions at the end
     * 
     * Time: O(n), Space: O(1)
     */
    public static ListNode partition(ListNode head, int x) {
        // Two dummy heads for two partitions
        ListNode lessDummy = new ListNode(0);
        ListNode greaterDummy = new ListNode(0);
        
        ListNode less = lessDummy;
        ListNode greater = greaterDummy;
        
        // Distribute nodes to appropriate partition
        while (head != null) {
            if (head.val < x) {
                less.next = head;
                less = less.next;
            } else {
                greater.next = head;
                greater = greater.next;
            }
            head = head.next;
        }
        
        // Connect partitions
        greater.next = null; // Terminate second partition
        less.next = greaterDummy.next; // Connect partitions
        
        return lessDummy.next;
    }
    
    /**
     * 🟡 MEDIUM: Remove Duplicates from Sorted List II
     * 
     * Problem: Remove all nodes that have duplicate values
     * 
     * Approach: Dummy node with lookahead for complete removal
     * - Use dummy to handle head removal
     * - Remove all instances of duplicate values
     * - Keep only nodes that appear exactly once
     * 
     * Time: O(n), Space: O(1)
     */
    public static ListNode deleteDuplicatesII(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        
        while (prev.next != null && prev.next.next != null) {
            if (prev.next.val == prev.next.next.val) {
                int duplicateVal = prev.next.val;
                // Remove all nodes with duplicate value
                while (prev.next != null && prev.next.val == duplicateVal) {
                    prev.next = prev.next.next;
                }
            } else {
                prev = prev.next;
            }
        }
        
        return dummy.next;
    }
    
    /**
     * 🟡 MEDIUM: Add Two Numbers II
     * 
     * Problem: Add two numbers represented as linked lists (digits in normal order)
     * 
     * Approach: Reverse lists, add, then reverse result
     * - Reverse both input lists
     * - Use standard addition with dummy
     * - Reverse result to get correct order
     * 
     * Time: O(max(m,n)), Space: O(max(m,n))
     */
    public static ListNode addTwoNumbersII(ListNode l1, ListNode l2) {
        // Reverse both lists
        ListNode rev1 = reverseList(l1);
        ListNode rev2 = reverseList(l2);
        
        // Add reversed lists
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        int carry = 0;
        
        while (rev1 != null || rev2 != null || carry != 0) {
            int sum = carry;
            
            if (rev1 != null) {
                sum += rev1.val;
                rev1 = rev1.next;
            }
            
            if (rev2 != null) {
                sum += rev2.val;
                rev2 = rev2.next;
            }
            
            carry = sum / 10;
            current.next = new ListNode(sum % 10);
            current = current.next;
        }
        
        // Reverse result to get correct order
        return reverseList(dummy.next);
    }
    
    /**
     * 🟡 MEDIUM: Reverse Linked List II
     * 
     * Problem: Reverse portion of linked list from position left to right
     * 
     * Approach: Dummy node for position tracking and reversal
     * - Use dummy to handle edge cases with head reversal
     * - Navigate to start position
     * - Reverse segment using iterative approach
     * 
     * Time: O(n), Space: O(1)
     */
    public static ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        
        // Move to position before left
        for (int i = 0; i < left - 1; i++) {
            prev = prev.next;
        }
        
        // Reverse segment from left to right
        ListNode current = prev.next;
        for (int i = 0; i < right - left; i++) {
            ListNode next = current.next;
            current.next = next.next;
            next.next = prev.next;
            prev.next = next;
        }
        
        return dummy.next;
    }
    
    // ============================================================================
    // 🔴 HARD PROBLEMS - Advanced Dummy Node Applications
    // ============================================================================
    
    /**
     * 🔴 HARD: Merge k Sorted Lists
     * 
     * Problem: Merge k sorted linked lists into one sorted list
     * 
     * Approach: Divide and conquer with dummy nodes
     * - Recursively merge pairs of lists
     * - Use dummy node for each merge operation
     * - Optimize with priority queue for large k
     * 
     * Time: O(n log k), Space: O(log k) for recursion
     */
    public static ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        
        while (lists.length > 1) {
            List<ListNode> mergedLists = new ArrayList<>();
            
            // Merge pairs of lists
            for (int i = 0; i < lists.length; i += 2) {
                ListNode l1 = lists[i];
                ListNode l2 = (i + 1 < lists.length) ? lists[i + 1] : null;
                mergedLists.add(mergeTwoLists(l1, l2));
            }
            
            lists = mergedLists.toArray(new ListNode[0]);
        }
        
        return lists[0];
    }
    
    /**
     * 🔴 HARD: Sort List
     * 
     * Problem: Sort linked list using merge sort algorithm
     * 
     * Approach: Divide and conquer with dummy nodes
     * - Split list into halves recursively
     * - Use dummy nodes for merging sorted halves
     * - Achieve O(n log n) time with O(1) space
     * 
     * Time: O(n log n), Space: O(log n) for recursion
     */
    public static ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;
        
        // Split list into two halves
        ListNode mid = getMidAndSplit(head);
        
        // Recursively sort both halves
        ListNode left = sortList(head);
        ListNode right = sortList(mid);
        
        // Merge sorted halves
        return mergeTwoLists(left, right);
    }
    
    private static ListNode getMidAndSplit(ListNode head) {
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
     * 🔴 HARD: Insertion Sort List
     * 
     * Problem: Sort linked list using insertion sort algorithm
     * 
     * Approach: Dummy node for sorted portion management
     * - Maintain sorted portion starting with dummy
     * - For each node, find correct insertion position
     * - Insert node in sorted position
     * 
     * Time: O(n²), Space: O(1)
     */
    public static ListNode insertionSortList(ListNode head) {
        ListNode dummy = new ListNode(Integer.MIN_VALUE);
        ListNode current = head;
        
        while (current != null) {
            ListNode next = current.next;
            
            // Find insertion position in sorted portion
            ListNode prev = dummy;
            while (prev.next != null && prev.next.val < current.val) {
                prev = prev.next;
            }
            
            // Insert current node
            current.next = prev.next;
            prev.next = current;
            
            current = next;
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
     * Helper: Create linked list from array
     */
    public static ListNode createList(int[] vals) {
        if (vals.length == 0) return null;
        
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        
        for (int val : vals) {
            current.next = new ListNode(val);
            current = current.next;
        }
        
        return dummy.next;
    }
    
    /**
     * Helper: Convert linked list to array for easy comparison
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
    
    // ============================================================================
    // 🧪 TESTING AND DEMONSTRATION
    // ============================================================================
    
    public static void main(String[] args) {
        System.out.println("🎭 DUMMY NODE TECHNIQUE (LINKEDLIST) - PRACTICE PROBLEMS");
        System.out.println("========================================================");
        
        // Test Easy Problems
        System.out.println("\n🟢 EASY DUMMY NODE PROBLEMS:");
        
        // Add Two Numbers
        ListNode num1 = createList(new int[]{2, 4, 3}); // 342
        ListNode num2 = createList(new int[]{5, 6, 4}); // 465
        ListNode sum = addTwoNumbers(num1, num2);
        System.out.print("Add 342 + 465 = ");
        printList(sum); // Should be 807 (7->0->8)
        
        // Merge Two Sorted Lists
        ListNode list1 = createList(new int[]{1, 2, 4});
        ListNode list2 = createList(new int[]{1, 3, 4});
        ListNode merged = mergeTwoLists(list1, list2);
        System.out.print("Merge [1,2,4] and [1,3,4]: ");
        printList(merged);
        
        // Remove Duplicates
        ListNode withDups = createList(new int[]{1, 1, 2, 3, 3});
        ListNode noDups = deleteDuplicates(withDups);
        System.out.print("Remove duplicates from [1,1,2,3,3]: ");
        printList(noDups);
        
        // Remove Elements
        ListNode toRemove = createList(new int[]{1, 2, 6, 3, 4, 5, 6});
        ListNode removed = removeElements(toRemove, 6);
        System.out.print("Remove 6 from [1,2,6,3,4,5,6]: ");
        printList(removed);
        
        // Test Medium Problems
        System.out.println("\n🟡 MEDIUM DUMMY NODE PROBLEMS:");
        
        // Partition List
        ListNode toPartition = createList(new int[]{1, 4, 3, 2, 5, 2});
        ListNode partitioned = partition(toPartition, 3);
        System.out.print("Partition [1,4,3,2,5,2] around 3: ");
        printList(partitioned);
        
        // Remove Duplicates II
        ListNode withDupsII = createList(new int[]{1, 2, 3, 3, 4, 4, 5});
        ListNode noDupsII = deleteDuplicatesII(withDupsII);
        System.out.print("Remove all duplicates from [1,2,3,3,4,4,5]: ");
        printList(noDupsII);
        
        // Add Two Numbers II
        ListNode num3 = createList(new int[]{7, 2, 4, 3}); // 7243
        ListNode num4 = createList(new int[]{5, 6, 4});    // 564
        ListNode sum2 = addTwoNumbersII(num3, num4);
        System.out.print("Add 7243 + 564 = ");
        printList(sum2); // Should be 7807
        
        // Reverse Between
        ListNode toReverse = createList(new int[]{1, 2, 3, 4, 5});
        ListNode reversed = reverseBetween(toReverse, 2, 4);
        System.out.print("Reverse [1,2,3,4,5] from position 2 to 4: ");
        printList(reversed);
        
        // Test Hard Problems
        System.out.println("\n🔴 HARD DUMMY NODE PROBLEMS:");
        
        // Merge k Sorted Lists
        ListNode[] kLists = {
            createList(new int[]{1, 4, 5}),
            createList(new int[]{1, 3, 4}),
            createList(new int[]{2, 6})
        };
        ListNode mergedK = mergeKLists(kLists);
        System.out.print("Merge k sorted lists: ");
        printList(mergedK);
        
        // Sort List
        ListNode unsorted = createList(new int[]{4, 2, 1, 3});
        ListNode sorted = sortList(unsorted);
        System.out.print("Sort [4,2,1,3] using merge sort: ");
        printList(sorted);
        
        // Insertion Sort List
        ListNode unsorted2 = createList(new int[]{4, 2, 1, 3});
        ListNode sorted2 = insertionSortList(unsorted2);
        System.out.print("Sort [4,2,1,3] using insertion sort: ");
        printList(sorted2);
        
        // Performance Analysis
        System.out.println("\n⚡ COMPLEXITY ANALYSIS:");
        System.out.println("Add Two Numbers: O(max(m,n)) time, O(max(m,n)) space");
        System.out.println("Merge Two Lists: O(m+n) time, O(1) space");
        System.out.println("Partition List: O(n) time, O(1) space");
        System.out.println("Merge k Lists: O(n log k) time, O(log k) space");
        System.out.println("Sort List: O(n log n) time, O(log n) space");
        
        System.out.println("\n✅ Key Dummy Node Principles:");
        System.out.println("1. Dummy nodes eliminate head-specific edge cases");
        System.out.println("2. Always return dummy.next, never dummy itself");
        System.out.println("3. Use multiple dummies for complex partitioning");
        System.out.println("4. Maintain current pointer for building operations");
        System.out.println("5. Ensure proper null termination of result lists");
        System.out.println("6. Dummy provides stable anchor for pointer manipulations");
        System.out.println("7. Simplifies code logic and reduces bug probability");
    }
} 