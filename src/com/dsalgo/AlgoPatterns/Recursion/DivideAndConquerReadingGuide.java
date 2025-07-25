package com.dsalgo.AlgoPatterns.Recursion;

import java.util.*;

/**
 * ⚡ DIVIDE & CONQUER PATTERN - COMPREHENSIVE READING GUIDE
 * 
 * ============================================================================
 * 📚 WHAT IS DIVIDE & CONQUER?
 * ============================================================================
 * 
 * Divide & Conquer is a fundamental algorithmic paradigm that breaks down
 * a problem into smaller subproblems of the same type, solves each subproblem
 * recursively, and then combines their solutions to solve the original problem.
 * 
 * 🔑 THREE CORE PHASES:
 * 1. DIVIDE: Break the problem into smaller subproblems
 * 2. CONQUER: Solve the subproblems recursively (base case for small problems)
 * 3. COMBINE: Merge solutions of subproblems to get solution to original problem
 * 
 * ============================================================================
 * 🎯 WHEN TO USE DIVIDE & CONQUER
 * ============================================================================
 * 
 * ✅ PERFECT FOR:
 * - Problems that can be broken into similar smaller subproblems
 * - Sorting and searching algorithms
 * - Mathematical computations (power, multiplication)
 * - Array and string processing problems
 * - Tree and graph algorithms
 * - Optimization problems with optimal substructure
 * 
 * 🔍 LOOK FOR THESE PHRASES:
 * - "Find maximum/minimum in array"
 * - "Sort an array efficiently"
 * - "Search in sorted data"
 * - "Can be solved by combining solutions to smaller instances"
 * - "Optimal substructure property"
 * 
 * 🚩 RED FLAGS (Consider Other Approaches):
 * - Problems without natural divide points
 * - Subproblems that overlap significantly (use Dynamic Programming)
 * - Simple iterative solutions exist
 * - Combine step is too expensive
 * 
 * ============================================================================
 * 🔄 DIVIDE & CONQUER VARIATIONS
 * ============================================================================
 * 
 * 1️⃣ BINARY DIVIDE & CONQUER
 * - Split problem into two equal/nearly equal parts
 * - Most common approach
 * - Examples: Binary Search, Merge Sort, Maximum Subarray
 * 
 * 2️⃣ MULTI-WAY DIVIDE & CONQUER
 * - Split problem into more than two parts
 * - Examples: Quick Sort (3-way partition), Fast Fourier Transform
 * 
 * 3️⃣ DECREASE & CONQUER
 * - Reduce problem size by a constant amount
 * - Examples: Binary Search (halving), Euclidean GCD
 * 
 * 4️⃣ TRANSFORM & CONQUER
 * - Transform problem into easier format, then solve
 * - Examples: Fast Matrix Multiplication, Convex Hull
 * 
 * ============================================================================
 * 🧠 CORE CONCEPTS
 * ============================================================================
 * 
 * 🔹 DIVIDE & CONQUER TEMPLATE:
 * ```
 * function divideAndConquer(problem) {
 *     // Base case: problem is small enough to solve directly
 *     if (problem.size <= threshold) {
 *         return solveDirect(problem);
 *     }
 *     
 *     // Divide: break into subproblems
 *     subproblems = divide(problem);
 *     
 *     // Conquer: solve each subproblem recursively
 *     solutions = [];
 *     for (subproblem : subproblems) {
 *         solutions.add(divideAndConquer(subproblem));
 *     }
 *     
 *     // Combine: merge solutions
 *     return combine(solutions);
 * }
 * ```
 * 
 * 🔹 TIME COMPLEXITY ANALYSIS (Master Theorem):
 * For recurrence: T(n) = a * T(n/b) + f(n)
 * - a: number of subproblems
 * - n/b: size of each subproblem
 * - f(n): cost of divide and combine operations
 * 
 * 🔹 MASTER THEOREM CASES:
 * - Case 1: f(n) = O(n^c) where c < log_b(a) → T(n) = Θ(n^log_b(a))
 * - Case 2: f(n) = Θ(n^c * log^k(n)) where c = log_b(a) → T(n) = Θ(n^c * log^(k+1)(n))
 * - Case 3: f(n) = Ω(n^c) where c > log_b(a) → T(n) = Θ(f(n))
 * 
 * ============================================================================
 * 📋 PROBLEM-SOLVING FRAMEWORK
 * ============================================================================
 * 
 * STEP 1: IDENTIFY DIVIDE & CONQUER SUITABILITY
 * - Can problem be broken into similar smaller subproblems?
 * - Is there a natural way to divide the problem?
 * - Can solutions be combined efficiently?
 * 
 * STEP 2: DESIGN THE DIVIDE STRATEGY
 * - How to split the problem? (binary, multi-way, etc.)
 * - What's the base case for direct solving?
 * - Ensure subproblems are independent
 * 
 * STEP 3: IMPLEMENT CONQUER PHASE
 * - Recursive calls to solve subproblems
 * - Handle base cases efficiently
 * - Ensure progress toward base case
 * 
 * STEP 4: DESIGN COMBINE STRATEGY
 * - How to merge subproblem solutions?
 * - Ensure combine operation is efficient
 * - Maintain correctness of overall solution
 * 
 * STEP 5: ANALYZE COMPLEXITY
 * - Apply Master Theorem for time complexity
 * - Consider space complexity (recursion depth)
 * - Compare with alternative approaches
 * 
 * ============================================================================
 * 🎨 DIVIDE & CONQUER TEMPLATES
 * ============================================================================
 */
public class DivideAndConquerReadingGuide {
    
    /**
     * ⚡ BINARY DIVIDE & CONQUER TEMPLATE
     * Split problem into two equal parts
     */
    public static int binaryDivideConquerTemplate(int[] arr, int left, int right) {
        // Base case: single element or empty
        if (left >= right) {
            return left == right ? arr[left] : Integer.MIN_VALUE;
        }
        
        // Divide: split into two halves
        int mid = left + (right - left) / 2;
        
        // Conquer: solve each half recursively
        int leftResult = binaryDivideConquerTemplate(arr, left, mid);
        int rightResult = binaryDivideConquerTemplate(arr, mid + 1, right);
        
        // Combine: merge solutions (example: find maximum)
        return Math.max(leftResult, rightResult);
    }
    
    /**
     * 🔍 BINARY SEARCH TEMPLATE
     * Classic divide & conquer search algorithm
     */
    public static int binarySearchTemplate(int[] arr, int target) {
        return binarySearchHelper(arr, target, 0, arr.length - 1);
    }
    
    private static int binarySearchHelper(int[] arr, int target, int left, int right) {
        // Base case: element not found
        if (left > right) {
            return -1;
        }
        
        // Divide: find middle point
        int mid = left + (right - left) / 2;
        
        // Conquer: check if target found
        if (arr[mid] == target) {
            return mid;
        }
        
        // Combine: search appropriate half
        if (arr[mid] > target) {
            return binarySearchHelper(arr, target, left, mid - 1);
        } else {
            return binarySearchHelper(arr, target, mid + 1, right);
        }
    }
    
    /**
     * 🔄 MERGE SORT TEMPLATE
     * Divide & conquer sorting algorithm
     */
    public static void mergeSortTemplate(int[] arr) {
        if (arr.length <= 1) return;
        mergeSortHelper(arr, 0, arr.length - 1);
    }
    
    private static void mergeSortHelper(int[] arr, int left, int right) {
        // Base case: single element
        if (left >= right) {
            return;
        }
        
        // Divide: split into two halves
        int mid = left + (right - left) / 2;
        
        // Conquer: sort each half
        mergeSortHelper(arr, left, mid);
        mergeSortHelper(arr, mid + 1, right);
        
        // Combine: merge sorted halves
        merge(arr, left, mid, right);
    }
    
    private static void merge(int[] arr, int left, int mid, int right) {
        // Create temporary arrays for left and right subarrays
        int leftSize = mid - left + 1;
        int rightSize = right - mid;
        
        int[] leftArr = new int[leftSize];
        int[] rightArr = new int[rightSize];
        
        // Copy data to temporary arrays
        System.arraycopy(arr, left, leftArr, 0, leftSize);
        System.arraycopy(arr, mid + 1, rightArr, 0, rightSize);
        
        // Merge the temporary arrays back into arr[left..right]
        int i = 0, j = 0, k = left;
        
        while (i < leftSize && j < rightSize) {
            if (leftArr[i] <= rightArr[j]) {
                arr[k++] = leftArr[i++];
            } else {
                arr[k++] = rightArr[j++];
            }
        }
        
        // Copy remaining elements
        while (i < leftSize) arr[k++] = leftArr[i++];
        while (j < rightSize) arr[k++] = rightArr[j++];
    }
    
    /**
     * ⚡ QUICK SORT TEMPLATE
     * Divide & conquer with partitioning
     */
    public static void quickSortTemplate(int[] arr) {
        if (arr.length <= 1) return;
        quickSortHelper(arr, 0, arr.length - 1);
    }
    
    private static void quickSortHelper(int[] arr, int left, int right) {
        // Base case: single element or empty
        if (left >= right) {
            return;
        }
        
        // Divide: partition around pivot
        int pivotIndex = partition(arr, left, right);
        
        // Conquer: sort each partition
        quickSortHelper(arr, left, pivotIndex - 1);
        quickSortHelper(arr, pivotIndex + 1, right);
        
        // Combine: no explicit combine needed (in-place sorting)
    }
    
    private static int partition(int[] arr, int left, int right) {
        int pivot = arr[right]; // Choose rightmost as pivot
        int i = left - 1; // Index of smaller element
        
        for (int j = left; j < right; j++) {
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        
        swap(arr, i + 1, right);
        return i + 1;
    }
    
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    
    /**
     * 💰 MAXIMUM SUBARRAY TEMPLATE (Kadane's with D&C)
     * Find maximum sum contiguous subarray
     */
    public static int maxSubarrayTemplate(int[] arr) {
        return maxSubarrayHelper(arr, 0, arr.length - 1);
    }
    
    private static int maxSubarrayHelper(int[] arr, int left, int right) {
        // Base case: single element
        if (left == right) {
            return arr[left];
        }
        
        // Divide: find middle point
        int mid = left + (right - left) / 2;
        
        // Conquer: find max subarray in each half
        int leftMax = maxSubarrayHelper(arr, left, mid);
        int rightMax = maxSubarrayHelper(arr, mid + 1, right);
        
        // Combine: find max crossing subarray
        int crossMax = maxCrossingSubarray(arr, left, mid, right);
        
        // Return maximum of all three
        return Math.max(Math.max(leftMax, rightMax), crossMax);
    }
    
    private static int maxCrossingSubarray(int[] arr, int left, int mid, int right) {
        // Find max sum on left side of mid
        int leftSum = Integer.MIN_VALUE;
        int sum = 0;
        for (int i = mid; i >= left; i--) {
            sum += arr[i];
            leftSum = Math.max(leftSum, sum);
        }
        
        // Find max sum on right side of mid
        int rightSum = Integer.MIN_VALUE;
        sum = 0;
        for (int i = mid + 1; i <= right; i++) {
            sum += arr[i];
            rightSum = Math.max(rightSum, sum);
        }
        
        // Return sum of both sides
        return leftSum + rightSum;
    }
    
    /**
     * 🔢 POWER CALCULATION TEMPLATE
     * Fast exponentiation using divide & conquer
     */
    public static double powerTemplate(double base, int exponent) {
        // Handle negative exponents
        if (exponent < 0) {
            return 1.0 / powerHelper(base, -(long)exponent);
        }
        return powerHelper(base, exponent);
    }
    
    private static double powerHelper(double base, long exp) {
        // Base case
        if (exp == 0) return 1.0;
        if (exp == 1) return base;
        
        // Divide: split exponent in half
        double halfPower = powerHelper(base, exp / 2);
        
        // Combine: square the result
        if (exp % 2 == 0) {
            return halfPower * halfPower; // Even exponent
        } else {
            return base * halfPower * halfPower; // Odd exponent
        }
    }
    
    // ============================================================================
    // 🚨 COMMON PITFALLS TO AVOID
    // ============================================================================
    
    /**
     * ❌ PITFALL 1: Inefficient Divide Strategy
     * Always aim for balanced divisions
     */
    public static void badDivideExample() {
        // BAD: Unbalanced division (reduces to O(n²))
        // quickSort with worst-case pivot selection
        
        // GOOD: Balanced division (achieves O(n log n))
        // mergeSort or quickSort with good pivot selection
    }
    
    /**
     * ❌ PITFALL 2: Expensive Combine Operation
     * Ensure combine step doesn't dominate complexity
     */
    public static void expensiveCombineExample() {
        // BAD: O(n²) combine step negates D&C benefits
        // GOOD: O(n) or O(log n) combine operations
    }
    
    /**
     * ❌ PITFALL 3: Overlapping Subproblems
     * Use Dynamic Programming instead for overlapping subproblems
     */
    public static int inefficientFibonacci(int n) {
        // BAD: Overlapping subproblems make D&C inefficient
        if (n <= 1) return n;
        return inefficientFibonacci(n - 1) + inefficientFibonacci(n - 2);
        // This should use DP, not pure D&C
    }
    
    // ============================================================================
    // 💡 PRO TIPS
    // ============================================================================
    
    /**
     * 🎯 TIP 1: Choose Good Divide Points
     * Aim for balanced divisions to achieve optimal complexity
     */
    
    /**
     * 🎯 TIP 2: Optimize Base Cases
     * Use iterative solutions for small subproblems
     */
    
    /**
     * 🎯 TIP 3: Minimize Combine Overhead
     * Design efficient merge operations
     */
    
    /**
     * 🎯 TIP 4: Consider Iterative Alternatives
     * Sometimes iterative solutions are simpler and more efficient
     */
    
    /**
     * 🎯 TIP 5: Use Master Theorem
     * Analyze complexity systematically
     */
    
    // ============================================================================
    // 📚 PRACTICE PROBLEMS BY CATEGORY
    // ============================================================================
    
    /**
     * 🟢 BASIC DIVIDE & CONQUER:
     * - Binary Search
     * - Find Maximum/Minimum in Array
     * - Power Calculation
     * - Merge Sort
     * - Quick Sort
     * 
     * 🟡 INTERMEDIATE DIVIDE & CONQUER:
     * - Maximum Subarray Sum
     * - Closest Pair of Points
     * - Inversion Count
     * - Median of Two Sorted Arrays
     * 
     * 🔴 ADVANCED DIVIDE & CONQUER:
     * - Fast Matrix Multiplication (Strassen's)
     * - Fast Fourier Transform
     * - Convex Hull
     * - Skyline Problem
     */
    
    // ============================================================================
    // 🧭 DECISION FRAMEWORK
    // ============================================================================
    
    /**
     * CHOOSE DIVIDE & CONQUER WHEN:
     * ✅ Problem can be naturally divided into similar subproblems
     * ✅ Subproblems are independent
     * ✅ Combine operation is efficient
     * ✅ Balanced division is possible
     * 
     * AVOID DIVIDE & CONQUER WHEN:
     * ❌ Significant overlapping subproblems exist (use DP)
     * ❌ Combine step is too expensive
     * ❌ Simple iterative solution exists
     * ❌ Unbalanced divisions lead to poor performance
     * 
     * OPTIMIZE DIVIDE & CONQUER WITH:
     * 🚀 Balanced partitioning strategies
     * 🚀 Efficient combine operations
     * 🚀 Iterative solutions for small subproblems
     * 🚀 In-place algorithms to reduce space
     */
    
    // ============================================================================
    // 🌍 REAL-WORLD APPLICATIONS
    // ============================================================================
    
    /**
     * 🏭 PRACTICAL USES:
     * - Database query optimization (merge joins)
     * - Computer graphics (convex hull, closest pair)
     * - Signal processing (Fast Fourier Transform)
     * - Computational geometry (Voronoi diagrams)
     * - Parallel computing (divide work among processors)
     * - File systems (B-trees, binary search)
     * - Computer vision (image processing algorithms)
     * - Machine learning (decision trees, ensemble methods)
     */
    
    public static void main(String[] args) {
        System.out.println("⚡ DIVIDE & CONQUER PATTERN - READING GUIDE");
        System.out.println("===========================================");
        
        System.out.println("\n📖 Template Examples:");
        
        // Binary Search Example
        int[] sortedArr = {1, 3, 5, 7, 9, 11, 13, 15};
        System.out.println("Binary Search for 7: " + binarySearchTemplate(sortedArr, 7));
        
        // Merge Sort Example
        int[] unsortedArr = {64, 34, 25, 12, 22, 11, 90};
        System.out.println("Original array: " + Arrays.toString(unsortedArr));
        mergeSortTemplate(unsortedArr);
        System.out.println("Merge sorted: " + Arrays.toString(unsortedArr));
        
        // Quick Sort Example
        int[] quickSortArr = {64, 34, 25, 12, 22, 11, 90};
        System.out.println("Original array: " + Arrays.toString(quickSortArr));
        quickSortTemplate(quickSortArr);
        System.out.println("Quick sorted: " + Arrays.toString(quickSortArr));
        
        // Maximum Subarray Example
        int[] maxSubarrayArr = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println("Maximum subarray sum: " + maxSubarrayTemplate(maxSubarrayArr));
        
        // Power Calculation Example
        System.out.println("2^10 = " + powerTemplate(2.0, 10));
        
        // Binary Divide & Conquer Example
        int[] findMaxArr = {3, 1, 4, 1, 5, 9, 2, 6};
        System.out.println("Maximum element: " + binaryDivideConquerTemplate(findMaxArr, 0, findMaxArr.length - 1));
        
        System.out.println("\n✅ Key Divide & Conquer Principles:");
        System.out.println("1. Divide problem into similar smaller subproblems");
        System.out.println("2. Solve subproblems recursively");
        System.out.println("3. Combine solutions efficiently");
        System.out.println("4. Ensure balanced divisions for optimal performance");
        System.out.println("5. Use Master Theorem for complexity analysis");
        System.out.println("6. Consider base case optimizations");
        System.out.println("7. Minimize overhead in divide and combine operations");
    }
} 