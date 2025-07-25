package com.dsalgo.AlgoPatterns.LinkedList;

import java.util.*;

/**
 * 🌀 RECURSION TECHNIQUE (LINKEDLIST) - COMPREHENSIVE READING GUIDE
 * 
 * ============================================================================
 * 📚 WHAT IS RECURSION TECHNIQUE FOR LINKEDLIST?
 * ============================================================================
 * 
 * The Recursion Technique for LinkedList problems leverages the naturally
 * recursive structure of linked lists to solve complex problems through
 * elegant decomposition. Each recursive call works on a smaller subproblem
 * (the rest of the list), and the solution is built by combining results
 * from recursive calls. This approach often leads to cleaner, more intuitive
 * solutions for problems involving structural manipulation and traversal.
 * 
 * 🔑 CORE PRINCIPLES:
 * 1. DECOMPOSITION: Break problem into smaller subproblems
 * 2. BASE CASE: Define termination conditions clearly
 * 3. RECURSIVE CASE: Process current node and recurse on rest
 * 4. COMBINATION: Build solution from recursive results
 * 
 * 🌀 RECURSION METAPHOR:
 * Think of recursion as "solving the puzzle piece by piece":
 * - Handle current piece (node)
 * - Trust recursion to solve the rest
 * - Combine your piece with the solved rest
 * - Base case stops the recursion
 * 
 * ============================================================================
 * 🎯 WHEN TO USE RECURSION TECHNIQUE
 * ============================================================================
 * 
 * ✅ PERFECT FOR:
 * - Problems with natural recursive structure
 * - Tree-like thinking on linear lists
 * - Backtracking with state restoration
 * - Problems requiring "future knowledge"
 * - Elegant mathematical formulations
 * - Structural modifications that propagate backwards
 * 
 * 🔍 LOOK FOR THESE PHRASES:
 * - "Process pairs/groups of nodes"
 * - "Reverse or swap operations"
 * - "Check palindrome properties"
 * - "Remove all occurrences"
 * - "Flatten nested structures"
 * - "Transform based on future state"
 * 
 * 🚩 RED FLAGS (Consider Other Approaches):
 * - Simple linear traversal (use iteration)
 * - Memory is extremely constrained (recursion uses stack)
 * - Very long lists (stack overflow risk)
 * - Single-pass problems without backtracking needs
 * 
 * ============================================================================
 * 🔄 RECURSION TECHNIQUE VARIATIONS
 * ============================================================================
 * 
 * 1️⃣ TAIL RECURSION
 * - Recursive call is the last operation
 * - Can be optimized to iteration
 * - Memory efficient in optimized environments
 * 
 * 2️⃣ HEAD RECURSION
 * - Process current node after recursive call
 * - Enables "reverse" processing order
 * - Useful for building solutions backwards
 * 
 * 3️⃣ BINARY RECURSION
 * - Two recursive calls (like binary tree)
 * - Used for divide-and-conquer approaches
 * - Natural for problems with splits
 * 
 * 4️⃣ MUTUAL RECURSION
 * - Functions call each other recursively
 * - Used for state machine-like problems
 * - Alternating processing patterns
 * 
 * 5️⃣ MEMOIZED RECURSION
 * - Cache results to avoid recomputation
 * - Transform exponential to polynomial
 * - Bridge between recursion and DP
 * 
 * 6️⃣ BACKTRACKING RECURSION
 * - Explore options and backtrack on failure
 * - Restore state after recursive calls
 * - Used for constraint satisfaction
 * 
 * ============================================================================
 * 🧠 CORE CONCEPTS AND DESIGN PATTERNS
 * ============================================================================
 * 
 * 🔹 RECURSIVE THINKING PROCESS:
 * ```
 * 1. IDENTIFY: What's the smallest version of this problem?
 * 2. BASE CASE: How do I handle the trivial case?
 * 3. RECURSIVE CASE: How do I reduce the problem size?
 * 4. COMBINATION: How do I build solution from subresults?
 * 5. TRUST: Assume recursion solves the subproblem correctly
 * ```
 * 
 * 🔹 LINKED LIST RECURSION PATTERN:
 * ```
 * function recursiveFunction(node) {
 *     // Base case: null or leaf condition
 *     if (node == null || baseCondition) {
 *         return baseResult;
 *     }
 *     
 *     // Recursive case: process rest of list
 *     Result subResult = recursiveFunction(node.next);
 *     
 *     // Combine: use subResult to build final result
 *     return combineResults(node, subResult);
 * }
 * ```
 * 
 * 🔹 FORWARD VS BACKWARD PROCESSING:
 * ```
 * Forward (Tail Recursion):       Backward (Head Recursion):
 * process(node)                    subResult = recurse(node.next)
 * return recurse(node.next)        return process(node, subResult)
 * ```
 * 
 * ============================================================================
 * 📋 PROBLEM-SOLVING FRAMEWORK
 * ============================================================================
 * 
 * STEP 1: IDENTIFY RECURSIVE STRUCTURE
 * - Can the problem be broken into smaller subproblems?
 * - Does the solution depend on results from "rest of list"?
 * - Is there a natural divide point in the data structure?
 * 
 * STEP 2: DEFINE BASE CASES
 * - What are the termination conditions?
 * - Handle null nodes, single nodes, empty lists
 * - Ensure base cases cover all edge scenarios
 * 
 * STEP 3: DESIGN RECURSIVE CASE
 * - How to reduce problem size in each call?
 * - What information to pass to recursive calls?
 * - How to use recursive results?
 * 
 * STEP 4: IMPLEMENT COMBINATION LOGIC
 * - How to merge results from recursive calls?
 * - What processing needed on current node?
 * - Maintain invariants throughout recursion
 * 
 * STEP 5: OPTIMIZE AND VERIFY
 * - Check for stack overflow with large inputs
 * - Consider memoization for overlapping subproblems
 * - Verify correctness with edge cases
 * 
 * ============================================================================
 * 🎨 RECURSION TECHNIQUE TEMPLATES
 * ============================================================================
 */
public class RecursionTechniqueReadingGuide {
    
    /**
     * 🌀 BASIC RECURSION TEMPLATE
     * Standard pattern for recursive list processing
     */
    public static ListNode basicRecursionTemplate(ListNode head) {
        // Base case: end of list
        if (head == null) {
            return null;
        }
        
        // Recursive case: process rest of list
        ListNode subResult = basicRecursionTemplate(head.next);
        
        // Combine: build result using current node and subresult
        // This is where the main logic goes
        return combineLogic(head, subResult);
    }
    
    /**
     * 🔄 REVERSE PROCESSING TEMPLATE
     * Process nodes in reverse order using recursion stack
     */
    public static void reverseProcessingTemplate(ListNode head) {
        // Base case: end of list
        if (head == null) {
            return;
        }
        
        // Recursive case: first process the rest
        reverseProcessingTemplate(head.next);
        
        // Process current node after recursion
        processNode(head);
    }
    
    /**
     * 🎯 CONDITIONAL REMOVAL TEMPLATE
     * Remove nodes based on conditions using recursion
     */
    public static ListNode conditionalRemovalTemplate(ListNode head, int condition) {
        // Base case: end of list
        if (head == null) {
            return null;
        }
        
        // Recursive case: process rest of list first
        head.next = conditionalRemovalTemplate(head.next, condition);
        
        // Decision: keep or remove current node
        if (shouldRemove(head, condition)) {
            return head.next; // Remove current node
        } else {
            return head; // Keep current node
        }
    }
    
    /**
     * 🔀 PAIR SWAPPING TEMPLATE
     * Swap nodes in pairs using recursion
     */
    public static ListNode pairSwappingTemplate(ListNode head) {
        // Base case: less than 2 nodes
        if (head == null || head.next == null) {
            return head;
        }
        
        // Store nodes to swap
        ListNode first = head;
        ListNode second = head.next;
        
        // Recursive case: swap rest of list
        first.next = pairSwappingTemplate(second.next);
        
        // Perform current swap
        second.next = first;
        
        return second; // New head of swapped pair
    }
    
    /**
     * 🎭 PALINDROME CHECK TEMPLATE
     * Check if list is palindrome using recursion
     */
    public static boolean palindromeCheckTemplate(ListNode head) {
        return palindromeHelper(head, new ListNode[]{head}).isPalindrome;
    }
    
    private static PalindromeResult palindromeHelper(ListNode head, ListNode[] left) {
        // Base case: end of list
        if (head == null) {
            return new PalindromeResult(true, null);
        }
        
        // Recursive case: check rest of list
        PalindromeResult subResult = palindromeHelper(head.next, left);
        
        // Check current pair
        boolean currentMatch = (left[0].val == head.val);
        left[0] = left[0].next; // Move left pointer
        
        return new PalindromeResult(
            subResult.isPalindrome && currentMatch,
            subResult.tail
        );
    }
    
    /**
     * 🏗️ MULTILEVEL FLATTENING TEMPLATE
     * Flatten nested structures using recursion
     */
    public static Node flattenTemplate(Node head) {
        if (head == null) return null;
        
        return flattenHelper(head).head;
    }
    
    private static FlattenResult flattenHelper(Node head) {
        Node curr = head;
        Node tail = head;
        
        while (curr != null) {
            Node next = curr.next;
            
            if (curr.child != null) {
                // Recursively flatten child
                FlattenResult childResult = flattenHelper(curr.child);
                
                // Connect child list
                curr.next = childResult.head;
                childResult.head.prev = curr;
                
                // Connect to remaining list
                if (next != null) {
                    childResult.tail.next = next;
                    next.prev = childResult.tail;
                }
                
                // Clear child pointer
                curr.child = null;
                tail = childResult.tail;
            }
            
            if (curr.next != null) {
                tail = curr.next;
            }
            curr = next;
        }
        
        return new FlattenResult(head, tail);
    }
    
    /**
     * 🔍 DUPLICATE REMOVAL TEMPLATE
     * Remove all duplicates using recursion
     */
    public static ListNode duplicateRemovalTemplate(ListNode head) {
        // Base case: end of list or single node
        if (head == null || head.next == null) {
            return head;
        }
        
        // Check if current node has duplicates
        if (head.val == head.next.val) {
            int duplicateVal = head.val;
            
            // Skip all duplicates
            while (head != null && head.val == duplicateVal) {
                head = head.next;
            }
            
            // Recursively process remaining list
            return duplicateRemovalTemplate(head);
        } else {
            // No duplicates, keep current and recurse
            head.next = duplicateRemovalTemplate(head.next);
            return head;
        }
    }
    
    /**
     * 📊 LENGTH CALCULATION TEMPLATE
     * Calculate list length using recursion
     */
    public static int lengthTemplate(ListNode head) {
        // Base case: end of list
        if (head == null) {
            return 0;
        }
        
        // Recursive case: 1 + length of rest
        return 1 + lengthTemplate(head.next);
    }
    
    /**
     * 🔄 REVERSE LIST TEMPLATE
     * Reverse linked list using recursion
     */
    public static ListNode reverseTemplate(ListNode head) {
        // Base case: empty or single node
        if (head == null || head.next == null) {
            return head;
        }
        
        // Recursive case: reverse rest of list
        ListNode reversedHead = reverseTemplate(head.next);
        
        // Reverse current connection
        head.next.next = head;
        head.next = null;
        
        return reversedHead;
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
    
    // Node class for multilevel lists
    static class Node {
        public int val;
        public Node prev;
        public Node next;
        public Node child;
        public Node(int val) { this.val = val; }
    }
    
    // Result classes for complex recursive operations
    static class PalindromeResult {
        boolean isPalindrome;
        ListNode tail;
        PalindromeResult(boolean isPalindrome, ListNode tail) {
            this.isPalindrome = isPalindrome;
            this.tail = tail;
        }
    }
    
    static class FlattenResult {
        Node head;
        Node tail;
        FlattenResult(Node head, Node tail) {
            this.head = head;
            this.tail = tail;
        }
    }
    
    // Helper methods for templates
    private static ListNode combineLogic(ListNode current, ListNode subResult) {
        // Example combination logic
        current.next = subResult;
        return current;
    }
    
    private static void processNode(ListNode node) {
        // Example node processing
        System.out.print(node.val + " ");
    }
    
    private static boolean shouldRemove(ListNode node, int condition) {
        // Example removal condition
        return node.val == condition;
    }
    
    // ============================================================================
    // 🚨 COMMON PITFALLS TO AVOID
    // ============================================================================
    
    /**
     * ❌ PITFALL 1: Missing Base Cases
     * Always handle null and edge cases properly
     */
    public static void baseCaseExample() {
        // BAD: Missing null check
        // return 1 + length(head.next);
        
        // GOOD: Proper base case
        // if (head == null) return 0;
        // return 1 + length(head.next);
    }
    
    /**
     * ❌ PITFALL 2: Stack Overflow
     * Be aware of recursion depth limits
     */
    public static void stackOverflowExample() {
        // For very long lists, consider:
        // 1. Iterative alternatives
        // 2. Tail recursion optimization
        // 3. Trampolining techniques
    }
    
    /**
     * ❌ PITFALL 3: Incorrect State Management
     * Be careful with mutable state in recursion
     */
    public static void stateMutationExample() {
        // Use immutable parameters or
        // Pass state through return values
        // Avoid global mutable state
    }
    
    /**
     * ❌ PITFALL 4: Not Trusting Recursion
     * Don't try to trace through recursive calls
     */
    public static void trustRecursionExample() {
        // TRUST: Assume recursive call works correctly
        // FOCUS: Only on current level logic
        // VERIFY: With base cases and small examples
    }
    
    // ============================================================================
    // 💡 PRO TIPS
    // ============================================================================
    
    /**
     * 🎯 TIP 1: Identify Natural Recursion
     * - Look for problems that become simpler on smaller inputs
     * - Check if solution depends on "rest of list" results
     * - Natural fit for tree-like thinking on linear structures
     */
    
    /**
     * 🎯 TIP 2: Design Base Cases Carefully
     * - Handle null, single node, and empty list cases
     * - Ensure base cases are mutually exclusive and exhaustive
     * - Test base cases independently
     */
    
    /**
     * 🎯 TIP 3: Use Auxiliary Data Structures
     * - Helper classes for complex return values
     * - Arrays for mutable reference passing
     * - Custom result objects for multiple return values
     */
    
    /**
     * 🎯 TIP 4: Optimize for Tail Recursion
     * - Move processing before recursive call when possible
     * - Use accumulator parameters
     * - Consider iterative conversion for performance
     */
    
    /**
     * 🎯 TIP 5: Combine with Other Patterns
     * - Recursion + Memoization for overlapping subproblems
     * - Recursion + Backtracking for constraint satisfaction
     * - Recursion + Dummy nodes for simpler edge cases
     */
    
    // ============================================================================
    // 📚 PRACTICE PROBLEMS BY CATEGORY
    // ============================================================================
    
    /**
     * 🟢 BASIC RECURSIVE OPERATIONS:
     * - Reverse Linked List
     * - Palindrome Linked List
     * - Merge Two Sorted Lists
     * - Calculate Length
     * 
     * 🟡 INTERMEDIATE RECURSIVE OPERATIONS:
     * - Swap Nodes in Pairs
     * - Remove Duplicates from Sorted List II
     * - Add Two Numbers
     * - Remove Nth Node from End
     * 
     * 🔴 ADVANCED RECURSIVE OPERATIONS:
     * - Flatten Multilevel Doubly Linked List
     * - Sort List (Merge Sort)
     * - Copy List with Random Pointer
     * - LRU Cache with Recursive Operations
     */
    
    // ============================================================================
    // 🧭 DECISION FRAMEWORK
    // ============================================================================
    
    /**
     * CHOOSE RECURSION TECHNIQUE WHEN:
     * ✅ Problem has natural recursive structure
     * ✅ Solution depends on results from subproblems
     * ✅ Elegant mathematical formulation possible
     * ✅ Need to process in reverse order
     * ✅ Backtracking or state restoration required
     * 
     * AVOID RECURSION TECHNIQUE WHEN:
     * ❌ Simple linear traversal sufficient
     * ❌ Memory is extremely constrained
     * ❌ Very long lists (stack overflow risk)
     * ❌ Iterative solution is clearer
     * 
     * OPTIMIZE RECURSION TECHNIQUE WITH:
     * 🚀 Tail recursion for better memory usage
     * 🚀 Memoization for overlapping subproblems
     * 🚀 Helper functions with accumulator parameters
     * 🚀 Base case optimization for performance
     */
    
    // ============================================================================
    // 🌍 REAL-WORLD APPLICATIONS
    // ============================================================================
    
    /**
     * 🏭 PRACTICAL USES:
     * - Expression evaluation in compilers
     * - Undo/redo operation chains
     * - Parse tree construction and manipulation
     * - Recursive descent parsing
     * - Functional programming data transformations
     * - Tree serialization and deserialization
     * - Recursive data structure validation
     * - Mathematical formula evaluation
     */
    
    public static void main(String[] args) {
        System.out.println("🌀 RECURSION TECHNIQUE (LINKEDLIST) - READING GUIDE");
        System.out.println("==================================================");
        
        System.out.println("\n📖 Template Examples:");
        
        // Create test linked list: 1 -> 2 -> 3 -> 4 -> 5
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        
        // Length calculation
        int length = lengthTemplate(head);
        System.out.println("Length of list [1,2,3,4,5]: " + length);
        
        // Reverse processing
        System.out.print("Reverse processing order: ");
        reverseProcessingTemplate(head);
        System.out.println();
        
        // Pair swapping
        ListNode swapped = pairSwappingTemplate(createList(new int[]{1, 2, 3, 4, 5, 6}));
        System.out.print("After pair swapping [1,2,3,4,5,6]: ");
        printList(swapped);
        
        // Palindrome check
        ListNode palindrome = createList(new int[]{1, 2, 3, 2, 1});
        boolean isPalindrome = palindromeCheckTemplate(palindrome);
        System.out.println("Is [1,2,3,2,1] palindrome: " + isPalindrome);
        
        // Duplicate removal
        ListNode withDups = createList(new int[]{1, 2, 2, 3, 3, 3, 4});
        ListNode noDups = duplicateRemovalTemplate(withDups);
        System.out.print("Remove all duplicates from [1,2,2,3,3,3,4]: ");
        printList(noDups);
        
        // Reverse list
        ListNode toReverse = createList(new int[]{1, 2, 3, 4, 5});
        ListNode reversed = reverseTemplate(toReverse);
        System.out.print("Reversed [1,2,3,4,5]: ");
        printList(reversed);
        
        System.out.println("\n✅ Key Recursion Principles:");
        System.out.println("1. Define clear base cases for termination");
        System.out.println("2. Trust recursion to solve subproblems correctly");
        System.out.println("3. Focus only on current level logic");
        System.out.println("4. Use helper functions for complex state management");
        System.out.println("5. Consider stack depth for very long lists");
        System.out.println("6. Combine results from recursive calls appropriately");
        System.out.println("7. Leverage natural recursive structure of linked lists");
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