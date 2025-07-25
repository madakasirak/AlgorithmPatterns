package com.dsalgo.AlgoPatterns.LinkedList;

import java.util.*;

/**
 * 🔄 IN-PLACE REVERSAL TECHNIQUE (LINKEDLIST) - COMPREHENSIVE READING GUIDE
 * 
 * ============================================================================
 * 📚 WHAT IS IN-PLACE REVERSAL TECHNIQUE FOR LINKEDLIST?
 * ============================================================================
 * 
 * The In-Place Reversal Technique is a powerful pattern for LinkedList problems
 * that involves manipulating pointer relationships directly without using
 * additional data structures. This technique achieves efficient O(1) space
 * complexity by reversing connections iteratively, making it ideal for memory-
 * constrained environments and large datasets. The key insight is that linked
 * list reversal can be achieved by systematically changing the direction of
 * next pointers while carefully tracking previous, current, and next nodes.
 * 
 * 🔑 CORE PRINCIPLES:
 * 1. POINTER MANIPULATION: Direct modification of next pointers
 * 2. ITERATIVE APPROACH: Step-by-step traversal with state tracking
 * 3. SPACE EFFICIENCY: O(1) extra space regardless of input size
 * 4. STATE MANAGEMENT: Careful tracking of previous, current, and next nodes
 * 
 * 🔄 IN-PLACE METAPHOR:
 * Think of in-place reversal as "flipping a chain of dominoes":
 * - Each domino (node) points to the next one
 * - To reverse, flip each domino to point backwards
 * - Keep track of where you are and where you're going
 * - No extra dominoes needed, just rearrange existing ones
 * 
 * ============================================================================
 * 🎯 WHEN TO USE IN-PLACE REVERSAL TECHNIQUE
 * ============================================================================
 * 
 * ✅ PERFECT FOR:
 * - Reversing entire linked lists or segments
 * - Memory-constrained environments
 * - Large datasets where space matters
 * - Swapping or reordering operations
 * - Rotating or cycling operations
 * - Any operation requiring pointer direction changes
 * 
 * 🔍 LOOK FOR THESE PHRASES:
 * - "Reverse the linked list"
 * - "In-place operation"
 * - "Without using extra space"
 * - "Swap nodes/pairs"
 * - "Rotate or reorder"
 * - "Modify pointer relationships"
 * 
 * 🚩 RED FLAGS (Consider Other Approaches):
 * - Need to preserve original structure (use copying)
 * - Complex tree-like operations (use recursion)
 * - Multiple simultaneous traversals needed
 * - Random access patterns required
 * 
 * ============================================================================
 * 🔄 IN-PLACE REVERSAL VARIATIONS
 * ============================================================================
 * 
 * 1️⃣ COMPLETE REVERSAL
 * - Reverse entire linked list
 * - Standard three-pointer technique
 * - Most fundamental pattern
 * 
 * 2️⃣ SEGMENT REVERSAL
 * - Reverse specific portions of list
 * - Requires position tracking
 * - Connect reversed segment to surrounding nodes
 * 
 * 3️⃣ GROUP REVERSAL
 * - Reverse nodes in groups (pairs, k-groups)
 * - Iterative group processing
 * - Handle incomplete final groups
 * 
 * 4️⃣ ROTATION OPERATIONS
 * - Move tail segment to front
 * - Find rotation point and reconnect
 * - Handle circular-like operations
 * 
 * 5️⃣ REORDERING PATTERNS
 * - Complex rearrangements (L0->Ln->L1->Ln-1...)
 * - Combine multiple reversal techniques
 * - Multi-step pointer manipulations
 * 
 * 6️⃣ SPLITTING OPERATIONS
 * - Divide list into multiple parts
 * - Maintain balanced distribution
 * - Handle remainders appropriately
 * 
 * ============================================================================
 * 🧠 CORE CONCEPTS AND DESIGN PATTERNS
 * ============================================================================
 * 
 * 🔹 THREE-POINTER TECHNIQUE:
 * ```
 * prev = null, curr = head, next = null
 * 
 * while (curr != null) {
 *     next = curr.next        // Store next node
 *     curr.next = prev        // Reverse the link
 *     prev = curr             // Move prev forward
 *     curr = next             // Move curr forward
 * }
 * 
 * return prev                 // New head
 * ```
 * 
 * 🔹 SEGMENT REVERSAL PATTERN:
 * ```
 * 1. Navigate to segment start
 * 2. Reverse segment using three-pointer technique
 * 3. Connect reversed segment to surrounding nodes
 * 4. Update head if necessary
 * ```
 * 
 * 🔹 STATE TRACKING ESSENTIALS:
 * ```
 * - Previous node (for linking backwards)
 * - Current node (being processed)
 * - Next node (to continue traversal)
 * - Segment boundaries (start/end positions)
 * ```
 * 
 * ============================================================================
 * 📋 PROBLEM-SOLVING FRAMEWORK
 * ============================================================================
 * 
 * STEP 1: IDENTIFY REVERSAL SCOPE
 * - Complete list or specific segments?
 * - Fixed positions or dynamic boundaries?
 * - Single operation or multiple reversals?
 * 
 * STEP 2: PLAN POINTER MANAGEMENT
 * - What nodes need tracking (prev, curr, next)?
 * - How to connect reversed segments?
 * - How to handle edge cases (empty, single node)?
 * 
 * STEP 3: IMPLEMENT CORE REVERSAL
 * - Use three-pointer technique for basic reversal
 * - Maintain state throughout operation
 * - Handle boundary conditions carefully
 * 
 * STEP 4: CONNECT SEGMENTS
 * - Link reversed portions to surrounding nodes
 * - Update head pointer if necessary
 * - Ensure proper null termination
 * 
 * STEP 5: VALIDATE AND OPTIMIZE
 * - Verify correctness with edge cases
 * - Confirm O(1) space complexity
 * - Check for memory leaks or dangling pointers
 * 
 * ============================================================================
 * 🎨 IN-PLACE REVERSAL TEMPLATES
 * ============================================================================
 */
public class InPlaceReversalTechniqueReadingGuide {
    
    /**
     * 🔄 BASIC REVERSAL TEMPLATE
     * Standard pattern for reversing entire list
     */
    public static ListNode basicReversalTemplate(ListNode head) {
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
     * 🎯 SEGMENT REVERSAL TEMPLATE
     * Reverse nodes from position left to right
     */
    public static ListNode segmentReversalTemplate(ListNode head, int left, int right) {
        if (head == null || left == right) return head;
        
        // Step 1: Navigate to position before left
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prevLeft = dummy;
        
        for (int i = 0; i < left - 1; i++) {
            prevLeft = prevLeft.next;
        }
        
        // Step 2: Reverse segment from left to right
        ListNode prev = null;
        ListNode curr = prevLeft.next;
        
        for (int i = 0; i < right - left + 1; i++) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        
        // Step 3: Connect reversed segment
        prevLeft.next.next = curr; // Connect tail of reversed to remaining
        prevLeft.next = prev;      // Connect head of reversed to previous
        
        return dummy.next;
    }
    
    /**
     * 🔀 PAIR SWAPPING TEMPLATE
     * Swap every two adjacent nodes
     */
    public static ListNode pairSwappingTemplate(ListNode head) {
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
     * 🔄 K-GROUP REVERSAL TEMPLATE
     * Reverse nodes in groups of k
     */
    public static ListNode kGroupReversalTemplate(ListNode head, int k) {
        // Check if we have k nodes remaining
        ListNode curr = head;
        int count = 0;
        while (curr != null && count < k) {
            curr = curr.next;
            count++;
        }
        
        if (count == k) {
            // Reverse current k-group
            curr = kGroupReversalTemplate(curr, k); // Recursive call for rest
            
            // Reverse current group
            while (count > 0) {
                ListNode next = head.next;
                head.next = curr;
                curr = head;
                head = next;
                count--;
            }
            head = curr;
        }
        
        return head;
    }
    
    /**
     * 🌀 ROTATION TEMPLATE
     * Rotate list to the right by k places
     */
    public static ListNode rotationTemplate(ListNode head, int k) {
        if (head == null || head.next == null || k == 0) return head;
        
        // Step 1: Find length and make circular
        ListNode tail = head;
        int length = 1;
        while (tail.next != null) {
            tail = tail.next;
            length++;
        }
        tail.next = head; // Make circular
        
        // Step 2: Find new tail (length - k % length - 1 steps)
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
     * 🎭 REORDERING TEMPLATE
     * Reorder list: L0->Ln->L1->Ln-1->L2->Ln-2->...
     */
    public static ListNode reorderingTemplate(ListNode head) {
        if (head == null || head.next == null) return head;
        
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
        
        return head;
    }
    
    /**
     * ✂️ SPLITTING TEMPLATE
     * Split list into k parts
     */
    public static ListNode[] splittingTemplate(ListNode head, int k) {
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
            
            // Current part size
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
    
    /**
     * 🔍 LENGTH CALCULATION TEMPLATE
     * Calculate list length efficiently
     */
    public static int lengthTemplate(ListNode head) {
        int length = 0;
        ListNode curr = head;
        while (curr != null) {
            length++;
            curr = curr.next;
        }
        return length;
    }
    
    /**
     * 🎯 FIND NTH FROM END TEMPLATE
     * Find nth node from end using two pointers
     */
    public static ListNode findNthFromEndTemplate(ListNode head, int n) {
        ListNode fast = head;
        ListNode slow = head;
        
        // Move fast pointer n steps ahead
        for (int i = 0; i < n; i++) {
            if (fast == null) return null;
            fast = fast.next;
        }
        
        // Move both pointers until fast reaches end
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        
        return slow;
    }
    
    // ============================================================================
    // 🔧 HELPER CLASSES AND METHODS
    // ============================================================================
    
    // Standard ListNode class
    static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
    
    // Helper method for basic reversal (used in templates)
    private static ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        
        return prev;
    }
    
    // ============================================================================
    // 🚨 COMMON PITFALLS TO AVOID
    // ============================================================================
    
    /**
     * ❌ PITFALL 1: Losing Reference to Next Node
     * Always store next node before breaking connections
     */
    public static void nextNodeExample() {
        // BAD: Lost reference to rest of list
        // curr.next = prev;
        // curr = curr.next; // This is now prev, not the original next!
        
        // GOOD: Store next before modifying
        // ListNode next = curr.next;
        // curr.next = prev;
        // curr = next;
    }
    
    /**
     * ❌ PITFALL 2: Incorrect Boundary Handling
     * Handle start and end of segments carefully
     */
    public static void boundaryExample() {
        // Ensure proper connection of reversed segments
        // Connect tail of reversed segment to remaining list
        // Connect head of reversed segment to previous part
        // Update overall head if first segment is reversed
    }
    
    /**
     * ❌ PITFALL 3: Off-by-One Errors in Positioning
     * Be precise with position calculations
     */
    public static void positionExample() {
        // For 1-indexed positions, navigate (left-1) times to reach start
        // For segment reversal, count exactly (right-left+1) nodes
        // For rotation, calculate proper steps considering list length
    }
    
    /**
     * ❌ PITFALL 4: Forgetting Null Termination
     * Ensure proper list termination after operations
     */
    public static void terminationExample() {
        // After splitting or rotating, ensure last node points to null
        // In circular operations, remember to break the circle
        // Verify no dangling pointers remain
    }
    
    // ============================================================================
    // 💡 PRO TIPS
    // ============================================================================
    
    /**
     * 🎯 TIP 1: Use Dummy Nodes for Complex Operations
     * - Simplifies handling when head might change
     * - Provides stable reference point
     * - Eliminates special cases for first node
     */
    
    /**
     * 🎯 TIP 2: Draw Diagrams for Complex Reversals
     * - Visualize pointer changes before coding
     * - Track all three pointers (prev, curr, next)
     * - Verify connections at each step
     */
    
    /**
     * 🎯 TIP 3: Handle Edge Cases First
     * - Empty list, single node, two nodes
     * - Test boundary positions (first, last)
     * - Verify with minimum and maximum inputs
     */
    
    /**
     * 🎯 TIP 4: Use Two-Pointer Technique for Finding Positions
     * - Fast/slow pointers for middle finding
     * - Gap technique for nth from end
     * - Efficient length calculation when needed
     */
    
    /**
     * 🎯 TIP 5: Optimize for Common Cases
     * - Early return for trivial inputs
     * - Modular arithmetic for rotation
     * - Combine operations when possible
     */
    
    // ============================================================================
    // 📚 PRACTICE PROBLEMS BY CATEGORY
    // ============================================================================
    
    /**
     * 🟢 BASIC IN-PLACE OPERATIONS:
     * - Reverse Linked List
     * - Swap Nodes in Pairs
     * - Rotate List
     * - Remove Nth Node from End
     * 
     * 🟡 INTERMEDIATE IN-PLACE OPERATIONS:
     * - Reverse Nodes in k-Group
     * - Reorder List
     * - Split Linked List in Parts
     * - Reverse Linked List II
     * 
     * 🔴 ADVANCED IN-PLACE OPERATIONS:
     * - Reverse Alternate k-Group
     * - Complex Reordering Patterns
     * - Multi-level In-place Operations
     * - Optimized Rotation Algorithms
     */
    
    // ============================================================================
    // 🧭 DECISION FRAMEWORK
    // ============================================================================
    
    /**
     * CHOOSE IN-PLACE REVERSAL TECHNIQUE WHEN:
     * ✅ Need to reverse list or segments
     * ✅ Memory efficiency is crucial (O(1) space)
     * ✅ Large datasets where extra space is prohibitive
     * ✅ Pointer manipulation is natural for the problem
     * ✅ Want to modify existing structure directly
     * 
     * AVOID IN-PLACE REVERSAL TECHNIQUE WHEN:
     * ❌ Need to preserve original structure
     * ❌ Complex tree-like operations required
     * ❌ Multiple simultaneous traversals needed
     * ❌ Random access patterns are essential
     * 
     * OPTIMIZE IN-PLACE REVERSAL TECHNIQUE WITH:
     * 🚀 Dummy nodes for complex head handling
     * 🚀 Two-pointer techniques for position finding
     * 🚀 Modular arithmetic for rotation calculations
     * 🚀 Early termination for edge cases
     */
    
    // ============================================================================
    // 🌍 REAL-WORLD APPLICATIONS
    // ============================================================================
    
    /**
     * 🏭 PRACTICAL USES:
     * - Memory-constrained embedded systems
     * - Large-scale data processing pipelines
     * - Real-time systems with strict memory limits
     * - Undo/redo operations in text editors
     * - Playlist manipulation in media players
     * - Route optimization in navigation systems
     * - Message queue reordering in distributed systems
     * - Cache line optimization in high-performance computing
     */
    
    public static void main(String[] args) {
        System.out.println("🔄 IN-PLACE REVERSAL TECHNIQUE (LINKEDLIST) - READING GUIDE");
        System.out.println("============================================================");
        
        System.out.println("\n📖 Template Examples:");
        
        // Create test linked list: 1 -> 2 -> 3 -> 4 -> 5
        ListNode head = createList(new int[]{1, 2, 3, 4, 5});
        System.out.print("Original list: ");
        printList(head);
        
        // Basic reversal
        ListNode reversed = basicReversalTemplate(createList(new int[]{1, 2, 3, 4, 5}));
        System.out.print("Basic reversal: ");
        printList(reversed);
        
        // Segment reversal (reverse from position 2 to 4)
        ListNode segmentReversed = segmentReversalTemplate(createList(new int[]{1, 2, 3, 4, 5}), 2, 4);
        System.out.print("Segment reversal (2 to 4): ");
        printList(segmentReversed);
        
        // Pair swapping
        ListNode pairSwapped = pairSwappingTemplate(createList(new int[]{1, 2, 3, 4, 5, 6}));
        System.out.print("Pair swapping: ");
        printList(pairSwapped);
        
        // Rotation by 2
        ListNode rotated = rotationTemplate(createList(new int[]{1, 2, 3, 4, 5}), 2);
        System.out.print("Rotated by 2: ");
        printList(rotated);
        
        // Reordering
        ListNode reordered = reorderingTemplate(createList(new int[]{1, 2, 3, 4, 5}));
        System.out.print("Reordered: ");
        printList(reordered);
        
        // Splitting into 3 parts
        ListNode[] parts = splittingTemplate(createList(new int[]{1, 2, 3, 4, 5}), 3);
        System.out.println("Split into 3 parts:");
        for (int i = 0; i < parts.length; i++) {
            System.out.print("Part " + (i + 1) + ": ");
            printList(parts[i]);
        }
        
        System.out.println("\n✅ Key In-Place Reversal Principles:");
        System.out.println("1. Use three-pointer technique (prev, curr, next)");
        System.out.println("2. Always store next node before breaking connections");
        System.out.println("3. Maintain O(1) space complexity throughout");
        System.out.println("4. Handle edge cases (empty, single node) first");
        System.out.println("5. Use dummy nodes for complex head manipulations");
        System.out.println("6. Verify proper null termination after operations");
        System.out.println("7. Draw diagrams to visualize pointer movements");
    }
    
    private static ListNode createList(int[] vals) {
        if (vals.length == 0) return null;
        ListNode head = new ListNode(vals[0]);
        ListNode current = head;
        for (int i = 1; i < vals.length; i++) {
            current.next = new ListNode(vals[i]);
            current = current.next;
        }
        return head;
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