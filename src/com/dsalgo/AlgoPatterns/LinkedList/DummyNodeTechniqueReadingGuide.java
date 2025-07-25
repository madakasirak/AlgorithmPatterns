package com.dsalgo.AlgoPatterns.LinkedList;

import java.util.*;

/**
 * 🎭 DUMMY NODE TECHNIQUE (LINKEDLIST) - COMPREHENSIVE READING GUIDE
 * 
 * ============================================================================
 * 📚 WHAT IS DUMMY NODE TECHNIQUE FOR LINKEDLIST?
 * ============================================================================
 * 
 * The Dummy Node Technique is a powerful pattern for LinkedList problems that
 * involves creating a temporary "dummy" or "sentinel" node at the beginning
 * of operations. This dummy node acts as a placeholder that simplifies edge
 * case handling, eliminates special treatment for head operations, and makes
 * code more uniform and robust. The dummy node is typically discarded at the
 * end, returning dummy.next as the actual result.
 * 
 * 🔑 CORE PRINCIPLES:
 * 1. SIMPLIFICATION: Eliminates special cases for head node operations
 * 2. UNIFORMITY: Makes all nodes (including head) follow same treatment
 * 3. SAFETY: Prevents null pointer exceptions in edge cases
 * 4. ELEGANCE: Reduces code complexity and improves readability
 * 
 * 🎭 DUMMY NODE METAPHOR:
 * Think of dummy node as a "starting platform" or "launch pad":
 * - Provides stable reference point for operations
 * - Simplifies pointer manipulation logic
 * - Acts as safety buffer for edge cases
 * - Discarded after serving its purpose
 * 
 * ============================================================================
 * 🎯 WHEN TO USE DUMMY NODE TECHNIQUE
 * ============================================================================
 * 
 * ✅ PERFECT FOR:
 * - Building new linked lists from scratch
 * - Merging multiple linked lists
 * - Removing nodes (especially head removal)
 * - Partitioning or rearranging lists
 * - Mathematical operations on number lists
 * - Any operation that might modify the head
 * 
 * 🔍 LOOK FOR THESE PHRASES:
 * - "Create a new linked list"
 * - "Merge two or more lists"
 * - "Remove elements from list"
 * - "Partition list based on condition"
 * - "Add/subtract numbers represented as lists"
 * - "Handle empty list edge cases"
 * 
 * 🚩 RED FLAGS (Consider Other Approaches):
 * - Simple traversal without modification (use regular traversal)
 * - Only reading/searching without structural changes
 * - In-place swaps without head changes (use direct manipulation)
 * - Memory-critical applications (dummy adds overhead)
 * 
 * ============================================================================
 * 🔄 DUMMY NODE TECHNIQUE VARIATIONS
 * ============================================================================
 * 
 * 1️⃣ SINGLE DUMMY HEAD
 * - Most common pattern with one dummy at beginning
 * - Used for linear construction and simple merging
 * - Return dummy.next as final result
 * 
 * 2️⃣ MULTIPLE DUMMY HEADS
 * - Separate dummies for different categories/partitions
 * - Used for list partitioning and classification
 * - Each dummy manages its own chain
 * 
 * 3️⃣ DUMMY WITH TAIL TRACKING
 * - Dummy head + tail pointer for efficient appending
 * - Used for building lists where order matters
 * - Avoids O(n) traversal for each append
 * 
 * 4️⃣ CIRCULAR DUMMY
 * - Dummy that eventually connects back to itself
 * - Used for circular list operations
 * - Special handling for final connection
 * 
 * 5️⃣ NESTED DUMMY STRUCTURES
 * - Multiple levels of dummy nodes
 * - Used for complex tree-like or graph operations
 * - Each level manages its own concerns
 * 
 * 6️⃣ TEMPORARY DUMMY FOR ALGORITHMS
 * - Short-lived dummy for specific algorithm phases
 * - Used in sorting, reversing, or complex manipulations
 * - Created and discarded within single operation
 * 
 * ============================================================================
 * 🧠 CORE CONCEPTS AND DESIGN PATTERNS
 * ============================================================================
 * 
 * 🔹 DUMMY NODE LIFECYCLE:
 * ```
 * 1. CREATE: ListNode dummy = new ListNode(0);
 * 2. INITIALIZE: dummy.next = someStartingPoint; (optional)
 * 3. OPERATE: Use dummy as anchor for manipulations
 * 4. TRACK: Maintain current pointer from dummy
 * 5. RETURN: Return dummy.next as final result
 * ```
 * 
 * 🔹 EDGE CASE ELIMINATION:
 * ```
 * Without Dummy:               With Dummy:
 * if (head == null) {          // No special case needed
 *     head = newNode;          current.next = newNode;
 * } else {                     current = current.next;
 *     // complex logic
 * }
 * ```
 * 
 * 🔹 POINTER MANAGEMENT PATTERNS:
 * ```
 * ListNode dummy = new ListNode(0);
 * ListNode current = dummy;    // Always maintain current pointer
 * 
 * while (condition) {
 *     current.next = newNode;  // Attach new node
 *     current = current.next;  // Move current forward
 * }
 * 
 * return dummy.next;           // Skip dummy, return actual list
 * ```
 * 
 * ============================================================================
 * 📋 PROBLEM-SOLVING FRAMEWORK
 * ============================================================================
 * 
 * STEP 1: IDENTIFY DUMMY NEED
 * - Will the head node require special handling?
 * - Are we building a new list from components?
 * - Do edge cases complicate the main logic?
 * 
 * STEP 2: DESIGN DUMMY STRUCTURE
 * - Single dummy or multiple dummies needed?
 * - What should dummy's value be? (usually 0 or -1)
 * - Do we need tail tracking for efficiency?
 * 
 * STEP 3: IMPLEMENT POINTER MANAGEMENT
 * - Initialize current pointer to dummy
 * - Maintain current pointer throughout operations
 * - Ensure proper advancement of current pointer
 * 
 * STEP 4: HANDLE MAIN ALGORITHM
 * - Focus on core logic without edge case distractions
 * - Use uniform treatment for all nodes
 * - Leverage dummy's stability as anchor point
 * 
 * STEP 5: CLEANUP AND RETURN
 * - Return dummy.next (skip the dummy node)
 * - Ensure proper null termination
 * - Verify no memory leaks or dangling pointers
 * 
 * ============================================================================
 * 🎨 DUMMY NODE TECHNIQUE TEMPLATES
 * ============================================================================
 */
public class DummyNodeTechniqueReadingGuide {
    
    /**
     * 🎭 BASIC DUMMY NODE TEMPLATE
     * Standard pattern for building new lists
     */
    public static ListNode basicDummyTemplate(int[] values) {
        // Step 1: Create dummy node
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        
        // Step 2: Main algorithm logic
        for (int value : values) {
            // Process and create new node
            ListNode newNode = new ListNode(value);
            
            // Attach to result list
            current.next = newNode;
            current = current.next;
        }
        
        // Step 3: Return actual list (skip dummy)
        return dummy.next;
    }
    
    /**
     * 🔗 MERGE LISTS TEMPLATE
     * Template for merging two or more sorted lists
     */
    public static ListNode mergeTwoListsTemplate(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        
        // Merge while both lists have elements
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
        
        // Attach remaining elements
        current.next = (l1 != null) ? l1 : l2;
        
        return dummy.next;
    }
    
    /**
     * ➕ ARITHMETIC OPERATIONS TEMPLATE
     * Template for adding numbers represented as linked lists
     */
    public static ListNode addNumbersTemplate(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        int carry = 0;
        
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
     * 🗑️ REMOVAL OPERATIONS TEMPLATE
     * Template for removing nodes based on conditions
     */
    public static ListNode removeNodesTemplate(ListNode head, int target) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode current = dummy;
        
        while (current.next != null) {
            if (current.next.val == target) {
                current.next = current.next.next; // Remove node
            } else {
                current = current.next; // Move forward
            }
        }
        
        return dummy.next;
    }
    
    /**
     * 🔀 PARTITION TEMPLATE
     * Template for partitioning list based on conditions
     */
    public static ListNode partitionTemplate(ListNode head, int x) {
        // Two dummy heads for two partitions
        ListNode lessDummy = new ListNode(0);
        ListNode greaterDummy = new ListNode(0);
        
        ListNode less = lessDummy;
        ListNode greater = greaterDummy;
        
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
     * 🎯 DUPLICATE REMOVAL TEMPLATE
     * Template for removing duplicates from sorted list
     */
    public static ListNode removeDuplicatesTemplate(ListNode head) {
        if (head == null) return null;
        
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode current = dummy;
        
        while (current.next != null && current.next.next != null) {
            if (current.next.val == current.next.next.val) {
                int duplicateVal = current.next.val;
                // Remove all nodes with duplicate value
                while (current.next != null && current.next.val == duplicateVal) {
                    current.next = current.next.next;
                }
            } else {
                current = current.next;
            }
        }
        
        return dummy.next;
    }
    
    /**
     * 🔄 REVERSE SEGMENTS TEMPLATE
     * Template for reversing segments of linked list
     */
    public static ListNode reverseSegmentTemplate(ListNode head, int left, int right) {
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
    
    /**
     * 🏗️ BUILD FROM ARRAY TEMPLATE
     * Template for constructing linked list from array
     */
    public static ListNode buildFromArrayTemplate(int[] values) {
        if (values.length == 0) return null;
        
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        
        for (int value : values) {
            current.next = new ListNode(value);
            current = current.next;
        }
        
        return dummy.next;
    }
    
    /**
     * 🎨 INSERTION SORT TEMPLATE
     * Template for insertion sort on linked list
     */
    public static ListNode insertionSortTemplate(ListNode head) {
        ListNode dummy = new ListNode(Integer.MIN_VALUE);
        ListNode current = head;
        
        while (current != null) {
            ListNode next = current.next;
            
            // Find insertion position
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
    // 🔧 HELPER CLASS
    // ============================================================================
    
    // ListNode class for linked list problems
    static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
    
    // ============================================================================
    // 🚨 COMMON PITFALLS TO AVOID
    // ============================================================================
    
    /**
     * ❌ PITFALL 1: Forgetting to Return dummy.next
     * Always return dummy.next, not dummy itself
     */
    public static void returnValueExample() {
        // BAD: Returns dummy node itself
        // return dummy;
        
        // GOOD: Returns actual list
        // return dummy.next;
    }
    
    /**
     * ❌ PITFALL 2: Not Advancing Current Pointer
     * Must move current pointer after each assignment
     */
    public static void currentPointerExample() {
        // BAD: Current pointer never moves
        // current.next = newNode;
        // // Missing: current = current.next;
        
        // GOOD: Proper pointer advancement
        // current.next = newNode;
        // current = current.next;
    }
    
    /**
     * ❌ PITFALL 3: Null Termination Issues
     * Ensure final node points to null
     */
    public static void nullTerminationExample() {
        // For partition operations, ensure proper termination:
        // greater.next = null; // Terminate second partition
        // less.next = greaterDummy.next; // Connect partitions
    }
    
    /**
     * ❌ PITFALL 4: Memory Leaks in Complex Operations
     * Be careful with dangling pointers in complex manipulations
     */
    public static void memoryManagementExample() {
        // When removing nodes, ensure no dangling references
        // Store next pointer before breaking connections
        // ListNode next = current.next;
        // current.next = current.next.next;
    }
    
    // ============================================================================
    // 💡 PRO TIPS
    // ============================================================================
    
    /**
     * 🎯 TIP 1: Choose Appropriate Dummy Value
     * - Use 0 for general purposes
     * - Use Integer.MIN_VALUE or Integer.MAX_VALUE for sorting
     * - Value doesn't matter if dummy is discarded
     */
    
    /**
     * 🎯 TIP 2: Multiple Dummies for Complex Operations
     * - Use separate dummies for different categories
     * - Helps organize complex partitioning logic
     * - Each dummy manages its own chain independently
     */
    
    /**
     * 🎯 TIP 3: Tail Tracking for Efficiency
     * - Maintain both head (dummy) and tail pointers
     * - Enables O(1) appending operations
     * - Useful for building long lists efficiently
     */
    
    /**
     * 🎯 TIP 4: Dummy for Edge Case Simplification
     * - Eliminates need for special head handling
     * - Makes code more uniform and readable
     * - Reduces bug probability in edge cases
     */
    
    /**
     * 🎯 TIP 5: Temporary Dummies in Algorithms
     * - Create dummies for intermediate steps
     * - Use for algorithm phases that need anchoring
     * - Discard when no longer needed
     */
    
    // ============================================================================
    // 📚 PRACTICE PROBLEMS BY CATEGORY
    // ============================================================================
    
    /**
     * 🟢 BASIC DUMMY NODE OPERATIONS:
     * - Merge Two Sorted Lists
     * - Remove Duplicates from Sorted List
     * - Add Two Numbers
     * - Build Linked List from Array
     * 
     * 🟡 INTERMEDIATE DUMMY NODE OPERATIONS:
     * - Partition List
     * - Remove Nth Node from End
     * - Reverse Linked List II
     * - Insertion Sort List
     * 
     * 🔴 ADVANCED DUMMY NODE OPERATIONS:
     * - Merge k Sorted Lists
     * - Sort List (Merge Sort)
     * - Remove Duplicates from Sorted List II
     * - Copy List with Random Pointer
     */
    
    // ============================================================================
    // 🧭 DECISION FRAMEWORK
    // ============================================================================
    
    /**
     * CHOOSE DUMMY NODE TECHNIQUE WHEN:
     * ✅ Building new linked lists from scratch
     * ✅ Head node might be modified or removed
     * ✅ Merging multiple lists
     * ✅ Complex edge cases around empty lists
     * ✅ Want to simplify pointer manipulation logic
     * 
     * AVOID DUMMY NODE TECHNIQUE WHEN:
     * ❌ Simple traversal without structural changes
     * ❌ Memory is extremely constrained
     * ❌ In-place operations without head modification
     * ❌ Read-only operations or searches
     * 
     * OPTIMIZE DUMMY NODE TECHNIQUE WITH:
     * 🚀 Tail tracking for efficient appending
     * 🚀 Multiple dummies for complex partitioning
     * 🚀 Proper null termination for robustness
     * 🚀 Careful memory management in complex operations
     */
    
    // ============================================================================
    // 🌍 REAL-WORLD APPLICATIONS
    // ============================================================================
    
    /**
     * 🏭 PRACTICAL USES:
     * - Database record merging and sorting
     * - File processing with conditional filtering
     * - Stream processing with buffering
     * - Memory pool management
     * - Undo/redo operation chains
     * - Message queue implementations
     * - Task scheduling and prioritization
     * - Graph algorithms with temporary structures
     */
    
    public static void main(String[] args) {
        System.out.println("🎭 DUMMY NODE TECHNIQUE (LINKEDLIST) - READING GUIDE");
        System.out.println("==================================================");
        
        System.out.println("\n📖 Template Examples:");
        
        // Basic dummy usage
        int[] values = {1, 3, 5, 7, 9};
        ListNode list1 = buildFromArrayTemplate(values);
        System.out.print("Built from array [1,3,5,7,9]: ");
        printList(list1);
        
        // Merge two lists
        int[] values2 = {2, 4, 6, 8};
        ListNode list2 = buildFromArrayTemplate(values2);
        ListNode merged = mergeTwoListsTemplate(list1, list2);
        System.out.print("Merged with [2,4,6,8]: ");
        printList(merged);
        
        // Add two numbers
        ListNode num1 = buildFromArrayTemplate(new int[]{2, 4, 3}); // 342
        ListNode num2 = buildFromArrayTemplate(new int[]{5, 6, 4}); // 465
        ListNode sum = addNumbersTemplate(num1, num2);
        System.out.print("Sum of 342 + 465 = ");
        printList(sum); // Should be 807
        
        // Remove duplicates
        ListNode withDups = buildFromArrayTemplate(new int[]{1, 1, 2, 3, 3, 4});
        System.out.print("Original with duplicates: ");
        printList(withDups);
        ListNode noDups = removeDuplicatesTemplate(withDups);
        System.out.print("After removing duplicates: ");
        printList(noDups);
        
        // Partition list
        ListNode toPartition = buildFromArrayTemplate(new int[]{1, 4, 3, 2, 5, 2});
        System.out.print("Original for partition: ");
        printList(toPartition);
        ListNode partitioned = partitionTemplate(toPartition, 3);
        System.out.print("Partitioned around 3: ");
        printList(partitioned);
        
        // Insertion sort
        ListNode unsorted = buildFromArrayTemplate(new int[]{4, 2, 1, 3});
        System.out.print("Unsorted: ");
        printList(unsorted);
        ListNode sorted = insertionSortTemplate(unsorted);
        System.out.print("Insertion sorted: ");
        printList(sorted);
        
        System.out.println("\n✅ Key Dummy Node Principles:");
        System.out.println("1. Create dummy node as stable anchor point");
        System.out.println("2. Use dummy to eliminate head-specific edge cases");
        System.out.println("3. Maintain current pointer for building operations");
        System.out.println("4. Always return dummy.next, never dummy itself");
        System.out.println("5. Ensure proper null termination of result list");
        System.out.println("6. Use multiple dummies for complex partitioning");
        System.out.println("7. Leverage dummy for uniform treatment of all nodes");
    }
    
    private static void printList(ListNode head) {
        ListNode current = head;
        while (current != null) {
            System.out.print(current.val);
            if (current.next != null) System.out.print(" -> ");
            current = current.next;
        }
        System.out.println();
    }
} 