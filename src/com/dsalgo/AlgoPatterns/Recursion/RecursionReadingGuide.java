package com.dsalgo.AlgoPatterns.Recursion;

import java.util.*;

/**
 * 🔄 RECURSION PATTERN - COMPREHENSIVE READING GUIDE
 * 
 * ============================================================================
 * 📚 WHAT IS RECURSION?
 * ============================================================================
 * 
 * Recursion is a programming technique where a function calls itself to solve
 * a problem by breaking it down into smaller, similar subproblems. Each recursive
 * call works on a smaller instance of the original problem until a base case
 * is reached that can be solved directly.
 * 
 * 🔑 KEY COMPONENTS OF RECURSION:
 * 1. Base Case(s): The simplest instance(s) that can be solved directly
 * 2. Recursive Case: The function calls itself with a smaller/simpler input
 * 3. Progress: Each recursive call must move closer to the base case
 * 
 * ============================================================================
 * 🎯 WHEN TO USE RECURSION
 * ============================================================================
 * 
 * ✅ PERFECT FOR:
 * - Problems with natural recursive structure (trees, graphs)
 * - Mathematical sequences and calculations
 * - Divide and conquer algorithms
 * - Backtracking and exploration problems
 * - Problems that can be broken into similar subproblems
 * 
 * 🔍 LOOK FOR THESE PHRASES:
 * - "Can be broken down into smaller instances"
 * - "Calculate factorial", "Fibonacci sequence"
 * - "Explore all possibilities"
 * - "Tree traversal", "graph traversal"
 * - "Generate all combinations/permutations"
 * 
 * 🚩 RED FLAGS (Consider Iteration Instead):
 * - Simple linear calculations
 * - Large input sizes (stack overflow risk)
 * - Performance-critical code without memoization
 * - Problems with significant overlapping subproblems
 * 
 * ============================================================================
 * 🔄 RECURSION VARIATIONS
 * ============================================================================
 * 
 * 1️⃣ BASIC RECURSION (Direct Recursion)
 * - Function calls itself directly
 * - Single recursive call per function execution
 * - Examples: Factorial, Power calculation
 * 
 * 2️⃣ MULTIPLE RECURSION
 * - Function makes multiple recursive calls
 * - Creates tree-like call structure
 * - Examples: Fibonacci, Tree traversal
 * 
 * 3️⃣ TAIL RECURSION
 * - Recursive call is the last operation
 * - Can be optimized to iteration by compilers
 * - Examples: Tail-recursive factorial
 * 
 * 4️⃣ MUTUAL RECURSION (Indirect Recursion)
 * - Two or more functions call each other
 * - Examples: Even/Odd checking, parsing
 * 
 * 5️⃣ MEMOIZED RECURSION (Dynamic Programming)
 * - Store results to avoid recalculation
 * - Combines recursion with caching
 * - Examples: Optimized Fibonacci, LCS
 * 
 * 6️⃣ BACKTRACKING RECURSION
 * - Explore all possibilities systematically
 * - Undo choices when they don't lead to solution
 * - Examples: N-Queens, Sudoku solver
 * 
 * ============================================================================
 * 🧠 CORE CONCEPTS
 * ============================================================================
 * 
 * 🔹 RECURSION ANATOMY:
 * ```
 * function solve(problem) {
 *     // Base case: smallest solvable instance
 *     if (isBaseCase(problem)) {
 *         return baseSolution(problem);
 *     }
 *     
 *     // Recursive case: break down and combine
 *     smaller = breakDown(problem);
 *     subResult = solve(smaller);
 *     return combine(subResult, problem);
 * }
 * ```
 * 
 * 🔹 STACK FRAMES:
 * - Each recursive call creates a new stack frame
 * - Contains local variables and return address
 * - Stack unwinds as functions return
 * 
 * 🔹 TIME COMPLEXITY ANALYSIS:
 * - Count total number of function calls
 * - Consider work done in each call
 * - T(n) = a * T(n/b) + f(n) (Master Theorem)
 * 
 * 🔹 SPACE COMPLEXITY:
 * - Recursion depth determines space usage
 * - Each call uses O(1) space typically
 * - Total: O(depth) for call stack
 * 
 * ============================================================================
 * 📋 PROBLEM-SOLVING FRAMEWORK
 * ============================================================================
 * 
 * STEP 1: IDENTIFY RECURSION SUITABILITY
 * - Can problem be broken into similar subproblems?
 * - Is there a clear base case?
 * - Does problem have natural recursive structure?
 * 
 * STEP 2: DEFINE BASE CASE(S)
 * - What's the simplest instance?
 * - What can be solved directly?
 * - Handle edge cases (empty input, single element)
 * 
 * STEP 3: DESIGN RECURSIVE CASE
 * - How to make problem smaller?
 * - What recursive calls are needed?
 * - How to combine subproblem results?
 * 
 * STEP 4: ENSURE PROGRESS
 * - Each call must move toward base case
 * - Avoid infinite recursion
 * - Parameters must change appropriately
 * 
 * STEP 5: OPTIMIZE IF NEEDED
 * - Add memoization for overlapping subproblems
 * - Consider tail recursion optimization
 * - Convert to iteration if stack depth is concern
 * 
 * ============================================================================
 * 🎨 RECURSION TEMPLATES
 * ============================================================================
 */
public class RecursionReadingGuide {
    
    /**
     * 🔄 BASIC RECURSION TEMPLATE
     * Single recursive call, direct problem reduction
     */
    public static int basicRecursionTemplate(int n) {
        // Base case: simplest instance
        if (n <= 1) {
            return 1; // or appropriate base value
        }
        
        // Recursive case: reduce problem size
        return n * basicRecursionTemplate(n - 1);
    }
    
    /**
     * 🌳 MULTIPLE RECURSION TEMPLATE
     * Multiple recursive calls, tree-like structure
     */
    public static int multipleRecursionTemplate(int n) {
        // Base case
        if (n <= 1) {
            return n;
        }
        
        // Multiple recursive calls
        return multipleRecursionTemplate(n - 1) + multipleRecursionTemplate(n - 2);
    }
    
    /**
     * 🎯 TAIL RECURSION TEMPLATE
     * Recursive call is the last operation
     */
    public static int tailRecursionTemplate(int n, int accumulator) {
        // Base case
        if (n == 0) {
            return accumulator;
        }
        
        // Tail recursive call
        return tailRecursionTemplate(n - 1, n * accumulator);
    }
    
    /**
     * 💾 MEMOIZED RECURSION TEMPLATE
     * Store results to avoid recalculation
     */
    private static Map<Integer, Integer> memo = new HashMap<>();
    
    public static int memoizedRecursionTemplate(int n) {
        // Check memo first
        if (memo.containsKey(n)) {
            return memo.get(n);
        }
        
        // Base case
        if (n <= 1) {
            return n;
        }
        
        // Calculate and store result
        int result = memoizedRecursionTemplate(n - 1) + memoizedRecursionTemplate(n - 2);
        memo.put(n, result);
        return result;
    }
    
    /**
     * 🔙 BACKTRACKING TEMPLATE
     * Explore all possibilities with undo mechanism
     */
    public static void backtrackingTemplate(List<Integer> current, List<List<Integer>> result, int[] choices) {
        // Base case: valid solution found
        if (isValidSolution(current)) {
            result.add(new ArrayList<>(current));
            return;
        }
        
        // Try each choice
        for (int choice : choices) {
            if (isValidChoice(current, choice)) {
                // Make choice
                current.add(choice);
                
                // Recurse
                backtrackingTemplate(current, result, choices);
                
                // Undo choice (backtrack)
                current.remove(current.size() - 1);
            }
        }
    }
    
    private static boolean isValidSolution(List<Integer> current) {
        // Define solution criteria
        return current.size() == 3; // Example
    }
    
    private static boolean isValidChoice(List<Integer> current, int choice) {
        // Define validity criteria
        return !current.contains(choice); // Example
    }
    
    /**
     * 🔄 DIVIDE AND CONQUER TEMPLATE
     * Split problem, solve subproblems, combine results
     */
    public static int divideAndConquerTemplate(int[] arr, int left, int right) {
        // Base case: single element or empty
        if (left >= right) {
            return left == right ? arr[left] : 0;
        }
        
        // Divide
        int mid = left + (right - left) / 2;
        
        // Conquer
        int leftResult = divideAndConquerTemplate(arr, left, mid);
        int rightResult = divideAndConquerTemplate(arr, mid + 1, right);
        
        // Combine
        return Math.max(leftResult, rightResult);
    }
    
    // ============================================================================
    // 🚨 COMMON PITFALLS TO AVOID
    // ============================================================================
    
    /**
     * ❌ PITFALL 1: Missing Base Case
     * Always ensure there's a way to stop recursion
     */
    public static int badRecursion1(int n) {
        // Missing base case - infinite recursion!
        // return n * badRecursion1(n - 1);
        
        // ✅ FIXED:
        if (n <= 1) return 1;
        return n * badRecursion1(n - 1);
    }
    
    /**
     * ❌ PITFALL 2: No Progress Toward Base Case
     * Each call must move closer to termination
     */
    public static int badRecursion2(int n) {
        if (n == 0) return 1;
        // Not making progress - infinite recursion!
        // return badRecursion2(n);
        
        // ✅ FIXED:
        return n * badRecursion2(n - 1);
    }
    
    /**
     * ❌ PITFALL 3: Inefficient Without Memoization
     * Exponential time for overlapping subproblems
     */
    public static int inefficientFib(int n) {
        if (n <= 1) return n;
        // Recalculates same values multiple times
        return inefficientFib(n - 1) + inefficientFib(n - 2);
        // Time: O(2^n) - Very inefficient!
    }
    
    // ============================================================================
    // 💡 PRO TIPS
    // ============================================================================
    
    /**
     * 🎯 TIP 1: Start with Base Cases
     * Always identify and implement base cases first
     */
    
    /**
     * 🎯 TIP 2: Trust the Recursion
     * Assume recursive calls work correctly for smaller inputs
     */
    
    /**
     * 🎯 TIP 3: Trace Small Examples
     * Manually trace through small inputs to verify logic
     */
    
    /**
     * 🎯 TIP 4: Consider Iterative Alternative
     * For simple cases, iteration might be more efficient
     */
    
    /**
     * 🎯 TIP 5: Use Helper Functions
     * Add parameters for accumulation or state tracking
     */
    
    // ============================================================================
    // 📚 PRACTICE PROBLEMS BY CATEGORY
    // ============================================================================
    
    /**
     * 🟢 BASIC RECURSIVE FUNCTIONS:
     * - Factorial Calculation
     * - Fibonacci Number
     * - Power Function (x^n)
     * - Greatest Common Divisor
     * - String GCD
     * 
     * 🟡 TREE AND GRAPH RECURSION:
     * - Binary Tree Traversal
     * - Maximum Depth of Tree
     * - Path Sum Problems
     * - Graph DFS
     * 
     * 🔴 ADVANCED RECURSION:
     * - N-Queens Problem
     * - Sudoku Solver
     * - Generate Parentheses
     * - Word Search
     * - Combination Sum
     */
    
    // ============================================================================
    // 🧭 DECISION FRAMEWORK
    // ============================================================================
    
    /**
     * CHOOSE RECURSION WHEN:
     * ✅ Problem has natural recursive structure
     * ✅ Subproblems are similar to original
     * ✅ Clear base case exists
     * ✅ Input size is manageable
     * 
     * AVOID RECURSION WHEN:
     * ❌ Simple iterative solution exists
     * ❌ Deep recursion (stack overflow risk)
     * ❌ No overlapping subproblems but expensive calls
     * ❌ Tail recursion without compiler optimization
     * 
     * OPTIMIZE RECURSION WITH:
     * 🚀 Memoization for overlapping subproblems
     * 🚀 Tail recursion when possible
     * 🚀 Iterative conversion for deep recursion
     * 🚀 Early termination conditions
     */
    
    // ============================================================================
    // 🌍 REAL-WORLD APPLICATIONS
    // ============================================================================
    
    /**
     * 🏭 PRACTICAL USES:
     * - File system traversal
     * - JSON/XML parsing
     * - Mathematical calculations
     * - Algorithm design (quicksort, mergesort)
     * - AI and game playing (minimax)
     * - Compiler design (parsing)
     * - Graphics (fractal generation)
     * - Database query optimization
     */
    
    public static void main(String[] args) {
        System.out.println("🔄 RECURSION PATTERN - READING GUIDE");
        System.out.println("====================================");
        
        System.out.println("\n📖 Template Examples:");
        System.out.println("Basic Recursion (Factorial 5): " + basicRecursionTemplate(5));
        System.out.println("Multiple Recursion (Fib 7): " + multipleRecursionTemplate(7));
        System.out.println("Tail Recursion (Factorial 5): " + tailRecursionTemplate(5, 1));
        System.out.println("Memoized Recursion (Fib 10): " + memoizedRecursionTemplate(10));
        
        System.out.println("\n🔄 Divide and Conquer Example:");
        int[] arr = {3, 1, 4, 1, 5, 9, 2, 6};
        System.out.println("Max element: " + divideAndConquerTemplate(arr, 0, arr.length - 1));
        
        System.out.println("\n🔙 Backtracking Example:");
        List<List<Integer>> result = new ArrayList<>();
        backtrackingTemplate(new ArrayList<>(), result, new int[]{1, 2, 3, 4, 5});
        System.out.println("Generated combinations: " + result.size());
        
        System.out.println("\n✅ Key Takeaways:");
        System.out.println("1. Always define clear base cases");
        System.out.println("2. Ensure progress toward base case");
        System.out.println("3. Consider memoization for optimization");
        System.out.println("4. Trust the recursion - assume it works for smaller inputs");
        System.out.println("5. Trace through small examples to verify logic");
        System.out.println("6. Use helper functions for additional parameters");
        System.out.println("7. Consider iterative alternatives for simple cases");
    }
} 