package com.dsalgo.AlgoPatterns.LinkedList;

import java.util.*;

/**
 * 🌀 RECURSION TECHNIQUE (LINKEDLIST) - PRACTICE PROBLEMS
 * 
 * This class contains comprehensive implementations of recursive techniques for
 * LinkedList problems. The pattern leverages the naturally recursive structure
 * of linked lists to solve complex problems through elegant decomposition.
 * Each recursive call works on a smaller subproblem, and solutions are built
 * by combining results from recursive calls.
 * 
 * Pattern Focus: Recursive decomposition, natural structure exploitation, elegant solutions
 * Time Complexity: Generally O(n) with O(n) space for recursion stack
 * Space Complexity: O(n) for recursion stack depth
 */
public class RecursionTechnique {
    
    // ============================================================================
    // 🟢 EASY PROBLEMS - Basic Recursive Concepts
    // ============================================================================
    
    /**
     * 🟢 EASY: Reverse Linked List
     * 
     * Problem: Reverse a singly linked list using recursion
     * 
     * Approach: Recursive reversal with connection manipulation
     * - Recursively reverse the rest of the list
     * - Reverse current connection after recursive call
     * - Return the new head from deepest recursion
     * 
     * Time: O(n), Space: O(n) for recursion stack
     */
    public static ListNode reverseList(ListNode head) {
        // Base case: empty or single node
        if (head == null || head.next == null) {
            return head;
        }
        
        // Recursive case: reverse rest of list
        ListNode reversedHead = reverseList(head.next);
        
        // Reverse current connection
        head.next.next = head;
        head.next = null;
        
        return reversedHead;
    }
    
    /**
     * 🟢 EASY: Merge Two Sorted Lists
     * 
     * Problem: Merge two sorted linked lists recursively
     * 
     * Approach: Recursive comparison and merging
     * - Compare current nodes and choose smaller
     * - Recursively merge remaining lists
     * - Build result by connecting chosen node to merged rest
     * 
     * Time: O(m + n), Space: O(m + n) for recursion
     */
    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // Base cases: one list is empty
        if (list1 == null) return list2;
        if (list2 == null) return list1;
        
        // Recursive case: choose smaller node and merge rest
        if (list1.val <= list2.val) {
            list1.next = mergeTwoLists(list1.next, list2);
            return list1;
        } else {
            list2.next = mergeTwoLists(list1, list2.next);
            return list2;
        }
    }
    
    /**
     * 🟢 EASY: Search in Linked List
     * 
     * Problem: Search for a value in linked list recursively
     * 
     * Approach: Recursive traversal with value comparison
     * - Check current node value
     * - Recursively search in rest of list if not found
     * 
     * Time: O(n), Space: O(n)
     */
    public static boolean search(ListNode head, int target) {
        // Base case: reached end of list
        if (head == null) {
            return false;
        }
        
        // Base case: found target
        if (head.val == target) {
            return true;
        }
        
        // Recursive case: search in rest of list
        return search(head.next, target);
    }
    
    // ============================================================================
    // 🟡 MEDIUM PROBLEMS - Intermediate Recursive Techniques
    // ============================================================================
    
    /**
     * 🟡 MEDIUM: Swap Nodes in Pairs
     * 
     * Problem: Swap every two adjacent nodes in linked list
     * LeetCode: https://leetcode.com/problems/swap-nodes-in-pairs/
     * 
     * Approach: Recursive pair swapping
     * - Handle pairs at current level
     * - Recursively swap rest of the list
     * - Connect current swapped pair to recursively swapped rest
     * 
     * Time: O(n), Space: O(n/2) for recursion stack
     */
    public static ListNode swapPairs(ListNode head) {
        // Base case: less than 2 nodes
        if (head == null || head.next == null) {
            return head;
        }
        
        // Store nodes to swap
        ListNode first = head;
        ListNode second = head.next;
        
        // Recursive case: swap rest of list first
        first.next = swapPairs(second.next);
        
        // Perform current swap
        second.next = first;
        
        return second; // Second becomes new head of this pair
    }
    
    /**
     * 🟡 MEDIUM: Palindrome Linked List
     * 
     * Problem: Check if linked list is a palindrome
     * LeetCode: https://leetcode.com/problems/palindrome-linked-list/
     * 
     * Approach: Recursive traversal with two-pointer comparison
     * - Use recursion to reach end of list
     * - Compare nodes from outside in using recursion stack
     * - Pass left pointer by reference to advance it
     * 
     * Time: O(n), Space: O(n) for recursion stack
     */
    public static boolean isPalindrome(ListNode head) {
        return isPalindromeHelper(head, new ListNode[]{head});
    }
    
    private static boolean isPalindromeHelper(ListNode right, ListNode[] left) {
        // Base case: reached end of list
        if (right == null) {
            return true;
        }
        
        // Recursive case: check rest of list first
        boolean restIsPalindrome = isPalindromeHelper(right.next, left);
        
        // Check current pair (left advances as we return from recursion)
        boolean currentMatch = (left[0].val == right.val);
        left[0] = left[0].next; // Advance left pointer
        
        return restIsPalindrome && currentMatch;
    }
    
    /**
     * 🟡 MEDIUM: Remove Duplicates from Sorted List II
     * 
     * Problem: Remove all nodes that have duplicate values
     * LeetCode: https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii/
     * 
     * Approach: Recursive duplicate detection and removal
     * - Check if current node has duplicates
     * - If duplicates found, skip all and recurse on remaining
     * - If no duplicates, keep current and recurse on next
     * 
     * Time: O(n), Space: O(n) for recursion stack
     */
    public static ListNode deleteDuplicates(ListNode head) {
        // Base case: empty list or single node
        if (head == null || head.next == null) {
            return head;
        }
        
        // Check if current node has duplicates
        if (head.val == head.next.val) {
            int duplicateVal = head.val;
            
            // Skip all nodes with duplicate value
            while (head != null && head.val == duplicateVal) {
                head = head.next;
            }
            
            // Recursively process remaining list
            return deleteDuplicates(head);
        } else {
            // No duplicates, keep current and recurse
            head.next = deleteDuplicates(head.next);
            return head;
        }
    }
    
    /**
     * 🟡 MEDIUM: Add Two Numbers
     * 
     * Problem: Add two numbers represented as linked lists
     * 
     * Approach: Recursive addition with carry propagation
     * - Add current digits and carry from previous
     * - Recursively add remaining digits
     * - Handle carry propagation through recursive calls
     * 
     * Time: O(max(m,n)), Space: O(max(m,n))
     */
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        return addTwoNumbersHelper(l1, l2, 0);
    }
    
    private static ListNode addTwoNumbersHelper(ListNode l1, ListNode l2, int carry) {
        // Base case: both lists empty and no carry
        if (l1 == null && l2 == null && carry == 0) {
            return null;
        }
        
        // Calculate sum
        int sum = carry;
        if (l1 != null) {
            sum += l1.val;
            l1 = l1.next;
        }
        if (l2 != null) {
            sum += l2.val;
            l2 = l2.next;
        }
        
        // Create current result node
        ListNode result = new ListNode(sum % 10);
        
        // Recursively add remaining digits
        result.next = addTwoNumbersHelper(l1, l2, sum / 10);
        
        return result;
    }
    
    // ============================================================================
    // 🔴 HARD PROBLEMS - Advanced Recursive Applications
    // ============================================================================
    
    /**
     * 🔴 HARD: Flatten a Multilevel Doubly Linked List
     * 
     * Problem: Flatten multilevel doubly linked list into single level
     * LeetCode: https://leetcode.com/problems/flatten-a-multilevel-doubly-linked-list/
     * 
     * Approach: Recursive depth-first flattening
     * - Process main chain and child chains recursively
     * - Connect child chains properly maintaining prev/next links
     * - Return both head and tail for proper connection
     * 
     * Time: O(n), Space: O(d) where d is maximum depth
     */
    public static Node flatten(Node head) {
        if (head == null) return null;
        flattenHelper(head);
        return head;
    }
    
    private static Node flattenHelper(Node head) {
        Node curr = head;
        Node tail = head;
        
        while (curr != null) {
            if (curr.child != null) {
                Node next = curr.next;
                
                // Recursively flatten child
                Node childTail = flattenHelper(curr.child);
                
                // Connect child to current
                curr.next = curr.child;
                curr.child.prev = curr;
                curr.child = null; // Clear child pointer
                
                // Connect child tail to next
                if (next != null) {
                    childTail.next = next;
                    next.prev = childTail;
                }
                
                tail = childTail;
            }
            
            if (curr.next != null) {
                tail = curr.next;
            }
            curr = curr.next;
        }
        
        return tail;
    }
    
    /**
     * 🔴 HARD: Sort List (Merge Sort)
     * 
     * Problem: Sort linked list using merge sort algorithm
     * 
     * Approach: Recursive divide and conquer
     * - Split list into two halves
     * - Recursively sort each half
     * - Merge sorted halves using recursive merge
     * 
     * Time: O(n log n), Space: O(log n) for recursion
     */
    public static ListNode sortList(ListNode head) {
        // Base case: empty or single node
        if (head == null || head.next == null) {
            return head;
        }
        
        // Split list into two halves
        ListNode mid = getMid(head);
        ListNode left = head;
        ListNode right = mid.next;
        mid.next = null; // Break the connection
        
        // Recursively sort both halves
        left = sortList(left);
        right = sortList(right);
        
        // Merge sorted halves
        return mergeTwoLists(left, right);
    }
    
    private static ListNode getMid(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        ListNode prev = null;
        
        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        
        return prev; // Return node before middle
    }
    
    /**
     * 🔴 HARD: Copy List with Random Pointer
     * 
     * Problem: Deep copy linked list with random pointers
     * 
     * Approach: Recursive copying with memoization
     * - Use recursion to create nodes on demand
     * - Memoize created nodes to handle cycles and sharing
     * - Recursively copy next and random pointers
     * 
     * Time: O(n), Space: O(n) for recursion and memoization
     */
    public static RandomNode copyRandomList(RandomNode head) {
        Map<RandomNode, RandomNode> memo = new HashMap<>();
        return copyRandomListHelper(head, memo);
    }
    
    private static RandomNode copyRandomListHelper(RandomNode head, Map<RandomNode, RandomNode> memo) {
        // Base case: null node
        if (head == null) {
            return null;
        }
        
        // Check if already created
        if (memo.containsKey(head)) {
            return memo.get(head);
        }
        
        // Create new node
        RandomNode copy = new RandomNode(head.val);
        memo.put(head, copy); // Memoize before recursive calls
        
        // Recursively copy next and random pointers
        copy.next = copyRandomListHelper(head.next, memo);
        copy.random = copyRandomListHelper(head.random, memo);
        
        return copy;
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
    
    // Node class for multilevel doubly linked lists
    static class Node {
        public int val;
        public Node prev;
        public Node next;
        public Node child;
        
        public Node() {}
        public Node(int val) { this.val = val; }
        public Node(int val, Node prev, Node next, Node child) {
            this.val = val;
            this.prev = prev;
            this.next = next;
            this.child = child;
        }
    }
    
    // Node class with random pointer
    static class RandomNode {
        int val;
        RandomNode next;
        RandomNode random;
        
        public RandomNode(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
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
     * Helper: Create multilevel doubly linked list for testing
     */
    public static Node createMultilevelList() {
        // Create a simple multilevel list for testing
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node7 = new Node(7);
        Node node8 = new Node(8);
        Node node11 = new Node(11);
        Node node12 = new Node(12);
        
        // Main chain: 1 <-> 2 <-> 3
        node1.next = node2;
        node2.prev = node1;
        node2.next = node3;
        node3.prev = node2;
        
        // Child chain from node 2: 7 <-> 8
        node2.child = node7;
        node7.next = node8;
        node8.prev = node7;
        
        // Child chain from node 8: 11 <-> 12
        node8.child = node11;
        node11.next = node12;
        node12.prev = node11;
        
        return node1;
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
     * Helper: Print multilevel list (flattened)
     */
    public static void printMultilevelList(Node head) {
        Node current = head;
        while (current != null) {
            System.out.print(current.val);
            if (current.next != null) {
                System.out.print(" <-> ");
            }
            current = current.next;
        }
        System.out.println();
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
    
    // ============================================================================
    // 🧪 TESTING AND DEMONSTRATION
    // ============================================================================
    
    public static void main(String[] args) {
        System.out.println("🌀 RECURSION TECHNIQUE (LINKEDLIST) - PRACTICE PROBLEMS");
        System.out.println("=======================================================");
        
        // Test Easy Problems
        System.out.println("\n🟢 EASY RECURSIVE PROBLEMS:");
        
        // Reverse List
        ListNode list1 = createList(new int[]{1, 2, 3, 4, 5});
        System.out.print("Original: ");
        printList(list1);
        ListNode reversed = reverseList(list1);
        System.out.print("Reversed: ");
        printList(reversed);
        
        // Merge Two Lists
        ListNode list2 = createList(new int[]{1, 2, 4});
        ListNode list3 = createList(new int[]{1, 3, 4});
        ListNode merged = mergeTwoLists(list2, list3);
        System.out.print("Merged [1,2,4] and [1,3,4]: ");
        printList(merged);
        
        // Search
        ListNode searchList = createList(new int[]{1, 3, 5, 7, 9});
        System.out.println("Search 5 in [1,3,5,7,9]: " + search(searchList, 5));
        System.out.println("Search 6 in [1,3,5,7,9]: " + search(searchList, 6));
        
        // Test Medium Problems
        System.out.println("\n🟡 MEDIUM RECURSIVE PROBLEMS:");
        
        // Swap Pairs
        ListNode pairList = createList(new int[]{1, 2, 3, 4, 5, 6});
        System.out.print("Original: ");
        printList(pairList);
        ListNode swapped = swapPairs(pairList);
        System.out.print("After swapping pairs: ");
        printList(swapped);
        
        // Palindrome Check
        ListNode palindrome1 = createList(new int[]{1, 2, 3, 2, 1});
        ListNode palindrome2 = createList(new int[]{1, 2, 3, 4, 5});
        System.out.println("Is [1,2,3,2,1] palindrome: " + isPalindrome(palindrome1));
        System.out.println("Is [1,2,3,4,5] palindrome: " + isPalindrome(palindrome2));
        
        // Remove Duplicates II
        ListNode duplicates = createList(new int[]{1, 2, 3, 3, 4, 4, 5});
        System.out.print("Original with duplicates: ");
        printList(duplicates);
        ListNode noDuplicates = deleteDuplicates(duplicates);
        System.out.print("After removing all duplicates: ");
        printList(noDuplicates);
        
        // Add Two Numbers
        ListNode num1 = createList(new int[]{2, 4, 3}); // 342
        ListNode num2 = createList(new int[]{5, 6, 4}); // 465
        ListNode sum = addTwoNumbers(num1, num2);
        System.out.print("Add 342 + 465 = ");
        printList(sum); // Should be 807
        
        // Test Hard Problems
        System.out.println("\n🔴 HARD RECURSIVE PROBLEMS:");
        
        // Flatten Multilevel List
        Node multilevel = createMultilevelList();
        System.out.print("Original multilevel structure created\n");
        Node flattened = flatten(multilevel);
        System.out.print("Flattened: ");
        printMultilevelList(flattened);
        
        // Sort List
        ListNode unsorted = createList(new int[]{4, 2, 1, 3});
        System.out.print("Unsorted: ");
        printList(unsorted);
        ListNode sorted = sortList(unsorted);
        System.out.print("Sorted: ");
        printList(sorted);
        
        // Performance Analysis
        System.out.println("\n⚡ COMPLEXITY ANALYSIS:");
        System.out.println("Reverse List: O(n) time, O(n) space - recursion stack");
        System.out.println("Swap Pairs: O(n) time, O(n/2) space - recursion depth");
        System.out.println("Palindrome Check: O(n) time, O(n) space - recursion stack");
        System.out.println("Remove Duplicates II: O(n) time, O(n) space - worst case recursion");
        System.out.println("Flatten Multilevel: O(n) time, O(d) space - depth of nesting");
        System.out.println("Sort List: O(n log n) time, O(log n) space - merge sort recursion");
        
        System.out.println("\n✅ Key Recursion Principles:");
        System.out.println("1. Define clear base cases for termination");
        System.out.println("2. Trust recursion to solve subproblems correctly");
        System.out.println("3. Focus only on current level logic");
        System.out.println("4. Combine results from recursive calls appropriately");
        System.out.println("5. Consider stack depth for very long lists");
        System.out.println("6. Use helper functions for complex state management");
        System.out.println("7. Leverage natural recursive structure of linked lists");
    }
} 