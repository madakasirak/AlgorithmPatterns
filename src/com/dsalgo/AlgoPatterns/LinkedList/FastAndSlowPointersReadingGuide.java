package com.dsalgo.AlgoPatterns.LinkedList;

import java.util.*;

/**
 * 🐢🐰 FAST AND SLOW POINTERS (LINKEDLIST) - COMPREHENSIVE READING GUIDE
 * 
 * ============================================================================
 * 📚 WHAT IS FAST AND SLOW POINTERS FOR LINKEDLIST?
 * ============================================================================
 * 
 * Fast and Slow Pointers (also known as Floyd's Tortoise and Hare algorithm)
 * is a powerful technique for solving LinkedList problems using two pointers
 * that move at different speeds. The slow pointer moves one step at a time,
 * while the fast pointer moves two steps at a time. This speed difference
 * creates elegant solutions for cycle detection, finding midpoints, and
 * various linked list manipulations.
 * 
 * 🔑 CORE PRINCIPLES:
 * 1. DUAL SPEED: Two pointers moving at different rates (1x and 2x)
 * 2. CONVERGENCE: Fast and slow pointers will meet if there's a cycle
 * 3. POSITIONING: Relative positions reveal structural properties
 * 4. EFFICIENCY: Single pass solutions with O(1) space complexity
 * 
 * 🏃‍♂️💨 SPEED RELATIONSHIP:
 * - Slow Pointer: moves 1 node per iteration (tortoise)
 * - Fast Pointer: moves 2 nodes per iteration (hare)
 * - Distance Gap: increases by 1 each iteration (no cycle) or decreases (cycle)
 * 
 * ============================================================================
 * 🎯 WHEN TO USE FAST AND SLOW POINTERS
 * ============================================================================
 * 
 * ✅ PERFECT FOR:
 * - Cycle detection in linked lists
 * - Finding the middle element of linked list
 * - Detecting intersection of two linked lists
 * - Removing nth node from end efficiently
 * - Palindrome checking in linked lists
 * - Reordering linked list elements
 * 
 * 🔍 LOOK FOR THESE PHRASES:
 * - "Find the middle of linked list"
 * - "Detect if linked list has cycle"
 * - "Remove nth node from end"
 * - "Find intersection of two linked lists"
 * - "Check if linked list is palindrome"
 * - "Reorder linked list"
 * 
 * 🚩 RED FLAGS (Consider Other Approaches):
 * - Need to access random positions (use array/vector)
 * - Frequent insertions at beginning (use deque)
 * - Need bidirectional traversal (use doubly linked list)
 * - Simple single pass problems (use single pointer)
 * 
 * ============================================================================
 * 🔄 FAST AND SLOW POINTERS VARIATIONS
 * ============================================================================
 * 
 * 1️⃣ CYCLE DETECTION (Floyd's Algorithm)
 * - Detect if linked list contains a cycle
 * - Find the start of the cycle
 * - Calculate cycle length
 * 
 * 2️⃣ MIDDLE FINDING
 * - Find middle node of linked list
 * - Handle even/odd length lists appropriately
 * - Second middle for even length lists
 * 
 * 3️⃣ INTERSECTION DETECTION
 * - Find where two linked lists intersect
 * - Handle different length lists
 * - Optimize with length calculation
 * 
 * 4️⃣ NTH FROM END
 * - Remove nth node from end in one pass
 * - Handle edge cases (remove head, single node)
 * - Maintain gap between pointers
 * 
 * 5️⃣ PALINDROME CHECKING
 * - Find middle, reverse second half, compare
 * - Restore original structure
 * - Handle odd/even length differences
 * 
 * 6️⃣ LIST REORDERING
 * - Separate odd/even positioned nodes
 * - Rearrange based on specific patterns
 * - Merge reordered sublists
 * 
 * ============================================================================
 * 🧠 CORE CONCEPTS AND MATHEMATICS
 * ============================================================================
 * 
 * 🔹 CYCLE DETECTION MATHEMATICS:
 * ```
 * In a linked list with cycle:
 * - If slow pointer moves 's' steps, fast pointer moves '2s' steps
 * - When they meet: fast has traveled exactly one more cycle than slow
 * - Meeting point: (distance from start) = (cycle_length - start_of_cycle)
 * - To find cycle start: reset one pointer to head, move both at same speed
 * ```
 * 
 * 🔹 MIDDLE FINDING LOGIC:
 * ```
 * For list of length n:
 * - When fast reaches end, slow is at position n/2
 * - Odd length: slow points to exact middle
 * - Even length: slow points to second middle
 * - First middle: use fast.next != null condition
 * ```
 * 
 * 🔹 NTH FROM END TECHNIQUE:
 * ```
 * To remove nth from end:
 * 1. Move fast pointer n steps ahead
 * 2. Move both pointers until fast reaches end
 * 3. Slow pointer is now at (length - n)th position
 * 4. Remove slow.next node
 * ```
 * 
 * ============================================================================
 * 📋 PROBLEM-SOLVING FRAMEWORK
 * ============================================================================
 * 
 * STEP 1: IDENTIFY POINTER REQUIREMENTS
 * - Do we need to find relative positions?
 * - Is cycle detection required?
 * - What's the target relationship between pointers?
 * 
 * STEP 2: DETERMINE SPEED RATIO
 * - Standard: fast moves 2x, slow moves 1x
 * - Custom: adjust based on specific requirements
 * - Gap maintenance: for nth from end problems
 * 
 * STEP 3: DEFINE TERMINATION CONDITIONS
 * - Fast pointer reaches null (no cycle)
 * - Fast and slow pointers meet (cycle detected)
 * - Specific position reached (middle, nth from end)
 * 
 * STEP 4: HANDLE EDGE CASES
 * - Empty list or single node
 * - Lists shorter than expected
 * - Boundary conditions for removal/insertion
 * 
 * STEP 5: IMPLEMENT POINTER MOVEMENTS
 * - Careful null checking for fast pointer
 * - Proper advancement logic
 * - State tracking for complex operations
 * 
 * ============================================================================
 * 🎨 FAST AND SLOW POINTERS TEMPLATES
 * ============================================================================
 */
public class FastAndSlowPointersReadingGuide {
    
    /**
     * 🔄 CYCLE DETECTION TEMPLATE
     * Classic Floyd's Tortoise and Hare algorithm
     */
    public static boolean cycleDetectionTemplate(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        
        ListNode slow = head;
        ListNode fast = head;
        
        // Phase 1: Detect cycle
        while (fast != null && fast.next != null) {
            slow = slow.next;           // Move 1 step
            fast = fast.next.next;      // Move 2 steps
            
            if (slow == fast) {
                return true;            // Cycle detected
            }
        }
        
        return false;                   // No cycle
    }
    
    /**
     * 🎯 FIND CYCLE START TEMPLATE
     * After detecting cycle, find where cycle begins
     */
    public static ListNode findCycleStartTemplate(ListNode head) {
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
                break;                  // Cycle detected
            }
        }
        
        // No cycle found
        if (fast == null || fast.next == null) {
            return null;
        }
        
        // Phase 2: Find cycle start
        slow = head;                    // Reset slow to head
        while (slow != fast) {
            slow = slow.next;           // Both move at same speed
            fast = fast.next;
        }
        
        return slow;                    // Cycle start node
    }
    
    /**
     * 🎯 MIDDLE FINDING TEMPLATE
     * Find middle node of linked list
     */
    public static ListNode findMiddleTemplate(ListNode head) {
        if (head == null) {
            return null;
        }
        
        ListNode slow = head;
        ListNode fast = head;
        
        // Standard middle finding
        while (fast != null && fast.next != null) {
            slow = slow.next;           // Move 1 step
            fast = fast.next.next;      // Move 2 steps
        }
        
        return slow;                    // Points to middle (or second middle for even length)
    }
    
    /**
     * 🎯 FIRST MIDDLE TEMPLATE (for even length lists)
     * Returns first middle for even length lists
     */
    public static ListNode findFirstMiddleTemplate(ListNode head) {
        if (head == null) {
            return null;
        }
        
        ListNode slow = head;
        ListNode fast = head.next;      // Start fast one step ahead
        
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        
        return slow;                    // Points to first middle for even length
    }
    
    /**
     * 🗑️ REMOVE NTH FROM END TEMPLATE
     * Remove nth node from end in single pass
     */
    public static ListNode removeNthFromEndTemplate(ListNode head, int n) {
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
        
        // Remove nth node from end
        slow.next = slow.next.next;
        
        return dummy.next;
    }
    
    /**
     * 🔗 INTERSECTION DETECTION TEMPLATE
     * Find intersection of two linked lists
     */
    public static ListNode findIntersectionTemplate(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        
        ListNode pA = headA;
        ListNode pB = headB;
        
        // When pA reaches end, redirect to headB
        // When pB reaches end, redirect to headA
        // They will meet at intersection (or both reach null)
        while (pA != pB) {
            pA = (pA == null) ? headB : pA.next;
            pB = (pB == null) ? headA : pB.next;
        }
        
        return pA;                      // Intersection node (or null)
    }
    
    /**
     * 🔄 PALINDROME CHECKING TEMPLATE
     * Check if linked list forms palindrome
     */
    public static boolean palindromeCheckTemplate(ListNode head) {
        if (head == null || head.next == null) {
            return true;
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
        slow.next = null;               // Cut the list
        
        // Step 3: Compare both halves
        ListNode p1 = head;
        ListNode p2 = secondHalf;
        boolean isPalindrome = true;
        
        while (p2 != null) {            // p2 is shorter or equal
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
     * 🔀 ODD-EVEN REORDERING TEMPLATE
     * Group odd and even positioned nodes together
     */
    public static ListNode oddEvenReorderTemplate(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        
        ListNode odd = head;            // Points to odd positioned nodes
        ListNode even = head.next;      // Points to even positioned nodes
        ListNode evenHead = even;       // Keep reference to even head
        
        while (even != null && even.next != null) {
            odd.next = even.next;       // Connect odd nodes
            odd = odd.next;
            
            even.next = odd.next;       // Connect even nodes
            even = even.next;
        }
        
        odd.next = evenHead;            // Connect odd tail to even head
        
        return head;
    }
    
    /**
     * 📏 CALCULATE LIST LENGTH TEMPLATE
     * Efficiently calculate linked list length
     */
    public static int calculateLengthTemplate(ListNode head) {
        int length = 0;
        ListNode current = head;
        
        while (current != null) {
            length++;
            current = current.next;
        }
        
        return length;
    }
    
    /**
     * 🔄 REVERSE LINKED LIST TEMPLATE
     * Helper method for palindrome and other algorithms
     */
    public static ListNode reverseList(ListNode head) {
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
     * ❌ PITFALL 1: Not Checking Fast Pointer Nulls
     * Always check both fast and fast.next before advancing
     */
    public static void nullCheckExample() {
        // BAD: Can cause NullPointerException
        // while (fast != null) {
        //     fast = fast.next.next;  // If fast.next is null, this crashes
        // }
        
        // GOOD: Proper null checking
        // while (fast != null && fast.next != null) {
        //     fast = fast.next.next;
        // }
    }
    
    /**
     * ❌ PITFALL 2: Off-by-One Errors in Gap Maintenance
     * Careful counting when maintaining distance between pointers
     */
    public static void gapMaintenanceExample() {
        // For removing nth from end:
        // Move fast pointer exactly n+1 steps ahead (not n steps)
        // This ensures slow points to node before target
    }
    
    /**
     * ❌ PITFALL 3: Not Using Dummy Node
     * Use dummy node for operations that might modify head
     */
    public static void dummyNodeExample() {
        // BAD: Direct manipulation might lose head reference
        // head.next = head.next.next;
        
        // GOOD: Use dummy node for safer manipulation
        // ListNode dummy = new ListNode(0);
        // dummy.next = head;
    }
    
    /**
     * ❌ PITFALL 4: Incorrect Cycle Start Detection
     * After detecting cycle, must reset one pointer to head
     */
    public static void cycleStartExample() {
        // After cycle detection (slow == fast):
        // Reset slow to head, then move both at same speed
        // Don't reset fast to head!
    }
    
    // ============================================================================
    // 💡 PRO TIPS
    // ============================================================================
    
    /**
     * 🎯 TIP 1: Choose Right Middle Definition
     * - Standard middle: for odd length, exact middle; for even, second middle
     * - First middle: for even length, use fast = head.next initially
     */
    
    /**
     * 🎯 TIP 2: Dummy Node Strategy
     * - Use dummy node when head might be modified
     * - Simplifies edge case handling
     * - Makes code more robust
     */
    
    /**
     * 🎯 TIP 3: Visualization
     * - Draw out pointer movements for complex cases
     * - Trace through examples step by step
     * - Understand the mathematical relationship
     */
    
    /**
     * 🎯 TIP 4: Edge Case Testing
     * - Empty list: head == null
     * - Single node: head.next == null
     * - Two nodes: special case for many algorithms
     */
    
    /**
     * 🎯 TIP 5: Optimize for Specific Cases
     * - Intersection: use length calculation for efficiency
     * - Cycle detection: can optimize with additional checks
     * - Palindrome: consider space vs time trade-offs
     */
    
    // ============================================================================
    // 📚 PRACTICE PROBLEMS BY CATEGORY
    // ============================================================================
    
    /**
     * 🟢 BASIC FAST AND SLOW POINTERS:
     * - Middle of Linked List
     * - Linked List Cycle
     * - Remove Nth Node from End
     * - Intersection of Two Linked Lists
     * 
     * 🟡 INTERMEDIATE FAST AND SLOW POINTERS:
     * - Linked List Cycle II
     * - Palindrome Linked List
     * - Odd Even Linked List
     * - Reorder List
     * 
     * 🔴 ADVANCED FAST AND SLOW POINTERS:
     * - Copy List with Random Pointer
     * - Merge k Sorted Lists
     * - Sort List (merge sort)
     * - LRU Cache Implementation
     */
    
    // ============================================================================
    // 🧭 DECISION FRAMEWORK
    // ============================================================================
    
    /**
     * CHOOSE FAST AND SLOW POINTERS WHEN:
     * ✅ Need to find relative positions in linked list
     * ✅ Cycle detection or analysis required
     * ✅ Want O(1) space complexity solution
     * ✅ Single pass traversal preferred
     * 
     * AVOID FAST AND SLOW POINTERS WHEN:
     * ❌ Need random access to elements
     * ❌ Frequent insertions/deletions at arbitrary positions
     * ❌ Bidirectional traversal required
     * ❌ Simple problems solvable with single pointer
     * 
     * OPTIMIZE FAST AND SLOW POINTERS WITH:
     * 🚀 Dummy nodes for head modification cases
     * 🚀 Proper null checking for robustness
     * 🚀 Mathematical understanding of pointer relationships
     * 🚀 Edge case handling for small lists
     */
    
    // ============================================================================
    // 🌍 REAL-WORLD APPLICATIONS
    // ============================================================================
    
    /**
     * 🏭 PRACTICAL USES:
     * - Memory management: cycle detection in object references
     * - Network protocols: loop detection in routing
     * - Music players: playlist cycle detection
     * - File systems: symbolic link loop detection
     * - Browser history: navigation cycle prevention
     * - Game development: entity relationship cycles
     * - Database systems: foreign key cycle detection
     * - Compiler design: dependency cycle detection
     */
    
    public static void main(String[] args) {
        System.out.println("🐢🐰 FAST AND SLOW POINTERS (LINKEDLIST) - READING GUIDE");
        System.out.println("=========================================================");
        
        System.out.println("\n📖 Template Examples:");
        
        // Create test linked list: 1 -> 2 -> 3 -> 4 -> 5
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        
        // Middle finding
        ListNode middle = findMiddleTemplate(head);
        System.out.println("Middle of list (1->2->3->4->5): " + middle.val);
        
        ListNode firstMiddle = findFirstMiddleTemplate(head);
        System.out.println("First middle of list: " + firstMiddle.val);
        
        // Cycle detection
        System.out.println("Has cycle: " + cycleDetectionTemplate(head));
        
        // Create cycle: 5 -> 3 (points back to node 3)
        head.next.next.next.next.next = head.next.next;
        System.out.println("Has cycle after creating one: " + cycleDetectionTemplate(head));
        
        // Find cycle start
        ListNode cycleStart = findCycleStartTemplate(head);
        System.out.println("Cycle starts at node: " + (cycleStart != null ? cycleStart.val : "null"));
        
        // Break cycle for other tests
        head.next.next.next.next.next = null;
        
        // Remove nth from end
        System.out.println("Original list: 1->2->3->4->5");
        ListNode modified = removeNthFromEndTemplate(head, 2);
        System.out.print("After removing 2nd from end: ");
        printList(modified);
        
        // Palindrome check
        ListNode palindrome = new ListNode(1);
        palindrome.next = new ListNode(2);
        palindrome.next.next = new ListNode(2);
        palindrome.next.next.next = new ListNode(1);
        System.out.println("Is 1->2->2->1 palindrome: " + palindromeCheckTemplate(palindrome));
        
        // Odd-even reordering
        ListNode oddEven = new ListNode(1);
        oddEven.next = new ListNode(2);
        oddEven.next.next = new ListNode(3);
        oddEven.next.next.next = new ListNode(4);
        oddEven.next.next.next.next = new ListNode(5);
        
        System.out.print("Original: ");
        printList(oddEven);
        ListNode reordered = oddEvenReorderTemplate(oddEven);
        System.out.print("Odd-Even reordered: ");
        printList(reordered);
        
        System.out.println("\n✅ Key Fast and Slow Pointers Principles:");
        System.out.println("1. Fast pointer moves 2x speed, slow pointer moves 1x speed");
        System.out.println("2. Convergence indicates cycles, relative position reveals structure");
        System.out.println("3. Always check fast != null && fast.next != null");
        System.out.println("4. Use dummy nodes for operations that might modify head");
        System.out.println("5. Reset pointers appropriately for cycle start detection");
        System.out.println("6. Gap maintenance crucial for nth from end problems");
        System.out.println("7. Mathematical relationships enable elegant single-pass solutions");
    }
    
    private static void printList(ListNode head) {
        ListNode current = head;
        while (current != null) {
            System.out.print(current.val);
            if (current.next != null) System.out.print("->");
            current = current.next;
        }
        System.out.println();
    }
} 