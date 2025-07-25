package com.dsalgo.AlgoPatterns.StackAndQueues;

import java.util.*;

/**
 * 📊 MONOTONIC STACK PATTERN - COMPREHENSIVE READING GUIDE
 * 
 * ============================================================================
 * 📚 WHAT IS MONOTONIC STACK?
 * ============================================================================
 * 
 * A Monotonic Stack is a specialized stack data structure where elements are
 * maintained in a specific order (monotonic increasing or decreasing). When
 * adding new elements, we remove elements from the stack that violate the
 * monotonic property. This technique is extremely powerful for solving problems
 * involving "nearest greater/smaller element" queries and maintaining optimal
 * substructures efficiently.
 * 
 * 🔑 CORE PRINCIPLES:
 * 1. MONOTONIC PROPERTY: Stack maintains increasing or decreasing order
 * 2. VIOLATION REMOVAL: Remove elements that break the monotonic property
 * 3. OPTIMAL TRACKING: Keep track of optimal candidates for future queries
 * 4. EFFICIENT QUERIES: Answer nearest element queries in O(1) amortized time
 * 
 * 📊 MONOTONIC METAPHOR:
 * Think of monotonic stack as a "mountain range analyzer":
 * - Increasing stack: Tracks potential "peaks" that could be visible
 * - Decreasing stack: Tracks potential "valleys" that could be significant
 * - When new element arrives, remove obstructed elements
 * - Only keep elements that might be useful for future queries
 * 
 * ============================================================================
 * 🎯 WHEN TO USE MONOTONIC STACK
 * ============================================================================
 * 
 * ✅ PERFECT FOR:
 * - Finding nearest greater/smaller elements (left or right)
 * - Calculating areas in histograms and similar problems
 * - Stock span and temperature analysis problems
 * - Removing elements to maintain lexicographic order
 * - Problems requiring optimal substructure maintenance
 * - Range maximum/minimum queries with specific constraints
 * 
 * 🔍 LOOK FOR THESE PHRASES:
 * - "Next greater element" or "next smaller element"
 * - "Nearest larger/smaller to the left/right"
 * - "Largest rectangle" or "maximum area"
 * - "Stock span" or "consecutive days"
 * - "Remove elements to maintain order"
 * - "Lexicographically smallest/largest"
 * 
 * 🚩 RED FLAGS (Consider Other Approaches):
 * - Need all greater/smaller elements (use sorting)
 * - Random access to middle elements (use segment tree)
 * - Need to maintain exact counts (use frequency maps)
 * - Complex range queries (use specialized data structures)
 * 
 * ============================================================================
 * 🔄 MONOTONIC STACK VARIATIONS
 * ============================================================================
 * 
 * 1️⃣ MONOTONIC INCREASING STACK
 * - Elements in stack are in increasing order (bottom to top)
 * - Used to find next smaller element to the right
 * - Used to find previous smaller element to the left
 * - Maintains potential candidates for minimum queries
 * 
 * 2️⃣ MONOTONIC DECREASING STACK
 * - Elements in stack are in decreasing order (bottom to top)
 * - Used to find next greater element to the right
 * - Used to find previous greater element to the left
 * - Maintains potential candidates for maximum queries
 * 
 * 3️⃣ INDEX-BASED MONOTONIC STACK
 * - Store indices instead of values in stack
 * - Enables calculation of distances and spans
 * - Useful for area calculations and range problems
 * - Maintains position information for complex queries
 * 
 * 4️⃣ VALUE-BASED MONOTONIC STACK
 * - Store actual values in stack
 * - Direct comparison without array access
 * - Simpler for straightforward next element problems
 * - Efficient when only values matter, not positions
 * 
 * 5️⃣ LEXICOGRAPHIC MONOTONIC STACK
 * - Maintain lexicographic order for string problems
 * - Remove characters to achieve smallest/largest result
 * - Handle frequency constraints and requirements
 * - Complex ordering based on character properties
 * 
 * 6️⃣ BIDIRECTIONAL MONOTONIC STACK
 * - Process array from both left-to-right and right-to-left
 * - Combine results for comprehensive nearest element analysis
 * - Handle complex problems requiring both directions
 * - Optimize for problems needing complete information
 * 
 * ============================================================================
 * 🧠 CORE CONCEPTS AND ALGORITHMS
 * ============================================================================
 * 
 * 🔹 MONOTONIC PROPERTY MAINTENANCE:
 * ```
 * while (!stack.isEmpty() && violatesMonotonicProperty(stack.peek(), newElement)) {
 *     processRemovedElement(stack.pop());
 * }
 * stack.push(newElement);
 * ```
 * 
 * 🔹 NEXT GREATER ELEMENT ALGORITHM:
 * ```
 * 1. Use decreasing monotonic stack
 * 2. For each element, pop smaller elements (they found their answer)
 * 3. If stack not empty, top is the next greater element
 * 4. Push current element to stack
 * ```
 * 
 * 🔹 AREA CALCULATION TECHNIQUE:
 * ```
 * 1. Use increasing monotonic stack for heights
 * 2. When popping element, it's the shortest in its range
 * 3. Width = current_index - stack.peek() - 1 (if stack not empty)
 * 4. Area = height * width
 * ```
 * 
 * 🔹 STOCK SPAN COMPUTATION:
 * ```
 * 1. Use decreasing monotonic stack with indices
 * 2. Pop elements smaller than or equal to current price
 * 3. Span = current_index - stack.peek() (if stack not empty, else current_index + 1)
 * 4. Push current index
 * ```
 * 
 * ============================================================================
 * 📋 PROBLEM-SOLVING FRAMEWORK
 * ============================================================================
 * 
 * STEP 1: IDENTIFY THE QUERY TYPE
 * - What type of nearest element are you looking for?
 * - Do you need greater, smaller, or equal elements?
 * - Are you looking to the left, right, or both directions?
 * 
 * STEP 2: CHOOSE MONOTONIC DIRECTION
 * - Increasing stack: for next/previous smaller queries
 * - Decreasing stack: for next/previous greater queries
 * - Consider what elements should be kept vs removed
 * 
 * STEP 3: DECIDE DATA TO STORE
 * - Store indices: when you need positions/distances
 * - Store values: when only element values matter
 * - Store pairs: when you need both value and position
 * 
 * STEP 4: IMPLEMENT STACK MAINTENANCE
 * - Define the condition for removing elements
 * - Process removed elements appropriately
 * - Handle edge cases (empty stack, no answer found)
 * 
 * STEP 5: OPTIMIZE FOR SPECIFIC REQUIREMENTS
 * - Bidirectional processing if needed
 * - Result array initialization and updates
 * - Handle special cases and constraints
 * 
 * ============================================================================
 * 🎨 MONOTONIC STACK TEMPLATES
 * ============================================================================
 */
public class MonotonicStackReadingGuide {
    
    /**
     * 📈 NEXT GREATER ELEMENT TEMPLATE (Decreasing Stack)
     * Find the next greater element to the right for each element
     */
    public static int[] nextGreaterElementTemplate(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        Arrays.fill(result, -1); // Default: no greater element found
        
        Stack<Integer> stack = new Stack<>(); // Store indices
        
        for (int i = 0; i < n; i++) {
            // Pop elements smaller than current (they found their answer)
            while (!stack.isEmpty() && nums[stack.peek()] < nums[i]) {
                int index = stack.pop();
                result[index] = nums[i]; // nums[i] is next greater for nums[index]
            }
            stack.push(i); // Push current index
        }
        
        return result;
    }
    
    /**
     * 📉 NEXT SMALLER ELEMENT TEMPLATE (Increasing Stack)
     * Find the next smaller element to the right for each element
     */
    public static int[] nextSmallerElementTemplate(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        Arrays.fill(result, -1); // Default: no smaller element found
        
        Stack<Integer> stack = new Stack<>(); // Store indices
        
        for (int i = 0; i < n; i++) {
            // Pop elements greater than current (they found their answer)
            while (!stack.isEmpty() && nums[stack.peek()] > nums[i]) {
                int index = stack.pop();
                result[index] = nums[i]; // nums[i] is next smaller for nums[index]
            }
            stack.push(i); // Push current index
        }
        
        return result;
    }
    
    /**
     * 📊 LARGEST RECTANGLE TEMPLATE (Increasing Stack)
     * Find the largest rectangle area in histogram
     */
    public static int largestRectangleTemplate(int[] heights) {
        int n = heights.length;
        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;
        
        for (int i = 0; i <= n; i++) {
            int currentHeight = (i == n) ? 0 : heights[i];
            
            // Pop elements greater than current height
            while (!stack.isEmpty() && heights[stack.peek()] > currentHeight) {
                int height = heights[stack.pop()];
                int width = stack.isEmpty() ? i : i - stack.peek() - 1;
                maxArea = Math.max(maxArea, height * width);
            }
            
            stack.push(i);
        }
        
        return maxArea;
    }
    
    /**
     * 📈 STOCK SPAN TEMPLATE (Decreasing Stack)
     * Calculate stock span (consecutive days with price <= current)
     */
    public static int[] stockSpanTemplate(int[] prices) {
        int n = prices.length;
        int[] spans = new int[n];
        Stack<Integer> stack = new Stack<>(); // Store indices
        
        for (int i = 0; i < n; i++) {
            // Pop indices with prices less than or equal to current
            while (!stack.isEmpty() && prices[stack.peek()] <= prices[i]) {
                stack.pop();
            }
            
            // Calculate span
            spans[i] = stack.isEmpty() ? i + 1 : i - stack.peek();
            stack.push(i);
        }
        
        return spans;
    }
    
    /**
     * 🔤 LEXICOGRAPHIC TEMPLATE (Variable Monotonic)
     * Remove characters to maintain lexicographic order
     */
    public static String lexicographicTemplate(String s) {
        int[] count = new int[26]; // Count frequency of each character
        boolean[] inStack = new boolean[26]; // Track if character is in result
        
        // Count frequencies
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }
        
        Stack<Character> stack = new Stack<>();
        
        for (char c : s.toCharArray()) {
            count[c - 'a']--; // Decrease count as we process
            
            if (inStack[c - 'a']) continue; // Skip if already in result
            
            // Remove characters that are greater than current and appear later
            while (!stack.isEmpty() && 
                   stack.peek() > c && 
                   count[stack.peek() - 'a'] > 0) {
                char removed = stack.pop();
                inStack[removed - 'a'] = false;
            }
            
            stack.push(c);
            inStack[c - 'a'] = true;
        }
        
        // Build result
        StringBuilder result = new StringBuilder();
        for (char c : stack) {
            result.append(c);
        }
        
        return result.toString();
    }
    
    /**
     * 🔄 BIDIRECTIONAL TEMPLATE
     * Process array from both directions for comprehensive analysis
     */
    public static int[][] bidirectionalTemplate(int[] nums) {
        int n = nums.length;
        int[] nextGreater = new int[n];
        int[] prevGreater = new int[n];
        Arrays.fill(nextGreater, -1);
        Arrays.fill(prevGreater, -1);
        
        Stack<Integer> stack = new Stack<>();
        
        // Process left to right for next greater
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] < nums[i]) {
                nextGreater[stack.pop()] = i;
            }
            stack.push(i);
        }
        
        stack.clear();
        
        // Process right to left for previous greater
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && nums[stack.peek()] < nums[i]) {
                prevGreater[stack.pop()] = i;
            }
            stack.push(i);
        }
        
        return new int[][]{nextGreater, prevGreater};
    }
    
    // ============================================================================
    // 🚨 COMMON PITFALLS TO AVOID
    // ============================================================================
    
    /**
     * ❌ PITFALL 1: Wrong Monotonic Direction
     * Choose correct direction based on query type
     */
    public static void monotonicDirectionExample() {
        // For next GREATER: use DECREASING stack
        // For next SMALLER: use INCREASING stack
        // For previous GREATER: process right-to-left with DECREASING stack
        // For previous SMALLER: process right-to-left with INCREASING stack
    }
    
    /**
     * ❌ PITFALL 2: Incorrect Condition for Popping
     * Be careful with equality in comparison
     */
    public static void poppingConditionExample() {
        // For strict greater: use nums[stack.peek()] < nums[i]
        // For greater or equal: use nums[stack.peek()] <= nums[i]
        // Consider problem requirements carefully
    }
    
    /**
     * ❌ PITFALL 3: Not Handling Empty Stack
     * Always check stack emptiness before accessing
     */
    public static void emptyStackExample() {
        // Always check: if (!stack.isEmpty())
        // Provide appropriate default values
        // Handle edge cases properly
    }
    
    /**
     * ❌ PITFALL 4: Index vs Value Confusion
     * Be clear about what you're storing and comparing
     */
    public static void indexValueExample() {
        // Store indices when you need positions/distances
        // Store values when only element comparison matters
        // Be consistent throughout the algorithm
    }
    
    // ============================================================================
    // 💡 PRO TIPS
    // ============================================================================
    
    /**
     * 🎯 TIP 1: Visualize the Stack
     * - Draw the stack state at each step
     * - Understand what elements remain and why
     * - Verify the monotonic property is maintained
     */
    
    /**
     * 🎯 TIP 2: Consider Edge Cases
     * - Empty array, single element
     * - All elements same, strictly increasing/decreasing
     * - No greater/smaller element exists
     */
    
    /**
     * 🎯 TIP 3: Optimize for Space
     * - Reuse arrays when possible
     * - Consider in-place modifications
     * - Use appropriate data types
     */
    
    /**
     * 🎯 TIP 4: Handle Multiple Queries
     * - Precompute results for multiple queries
     * - Use appropriate data structures for repeated access
     * - Consider trade-offs between preprocessing and query time
     */
    
    /**
     * 🎯 TIP 5: Understand Time Complexity
     * - Each element pushed and popped at most once
     * - Amortized O(1) per element, O(n) total
     * - Space complexity typically O(n) for stack and result
     */
    
    // ============================================================================
    // 📚 PRACTICE PROBLEMS BY CATEGORY
    // ============================================================================
    
    /**
     * 🟢 BASIC MONOTONIC STACK PROBLEMS:
     * - Next Greater Element I/II
     * - Daily Temperatures
     * - Next Smaller Element
     * - Previous Greater Element
     * 
     * 🟡 INTERMEDIATE MONOTONIC STACK PROBLEMS:
     * - Largest Rectangle in Histogram
     * - Maximal Rectangle
     * - Online Stock Span
     * - Remove Duplicate Letters
     * 
     * 🔴 ADVANCED MONOTONIC STACK PROBLEMS:
     * - Sliding Window Maximum
     * - Maximum Frequency Stack
     * - Shortest Unsorted Continuous Subarray
     * - Create Maximum Number
     */
    
    // ============================================================================
    // 🧭 DECISION FRAMEWORK
    // ============================================================================
    
    /**
     * CHOOSE MONOTONIC STACK WHEN:
     * ✅ Need nearest greater/smaller elements
     * ✅ Finding optimal rectangles/areas
     * ✅ Maintaining optimal candidates
     * ✅ Lexicographic ordering problems
     * ✅ Range-based optimization queries
     * 
     * AVOID MONOTONIC STACK WHEN:
     * ❌ Need all greater/smaller elements
     * ❌ Complex range queries with updates
     * ❌ Random access to elements required
     * ❌ No monotonic property exists
     * 
     * OPTIMIZE MONOTONIC STACK WITH:
     * 🚀 Choose appropriate monotonic direction
     * 🚀 Store indices vs values based on needs
     * 🚀 Handle edge cases and empty conditions
     * 🚀 Use bidirectional processing when needed
     */
    
    // ============================================================================
    // 🌍 REAL-WORLD APPLICATIONS
    // ============================================================================
    
    /**
     * 🏭 PRACTICAL USES:
     * - Financial analysis (stock span, resistance/support levels)
     * - Weather analysis (temperature trends, precipitation patterns)
     * - Architecture (visibility analysis, skyline problems)
     * - Computer graphics (visibility determination, rendering)
     * - Data analysis (trend detection, outlier identification)
     * - Game development (line of sight, terrain analysis)
     * - Manufacturing (quality control, process optimization)
     * - Network analysis (routing, bottleneck detection)
     */
    
    public static void main(String[] args) {
        System.out.println("📊 MONOTONIC STACK PATTERN - READING GUIDE");
        System.out.println("==========================================");
        
        System.out.println("\n📖 Template Examples:");
        
        // Next Greater Element
        int[] nums1 = {2, 1, 3, 4, 2};
        int[] nextGreater = nextGreaterElementTemplate(nums1);
        System.out.println("Next Greater Elements: " + Arrays.toString(nextGreater));
        
        // Next Smaller Element
        int[] nextSmaller = nextSmallerElementTemplate(nums1);
        System.out.println("Next Smaller Elements: " + Arrays.toString(nextSmaller));
        
        // Largest Rectangle
        int[] heights = {2, 1, 5, 6, 2, 3};
        int maxArea = largestRectangleTemplate(heights);
        System.out.println("Largest Rectangle Area: " + maxArea);
        
        // Stock Span
        int[] prices = {100, 80, 60, 70, 60, 75, 85};
        int[] spans = stockSpanTemplate(prices);
        System.out.println("Stock Spans: " + Arrays.toString(spans));
        
        // Lexicographic
        String s = "bcabc";
        String lexResult = lexicographicTemplate(s);
        System.out.println("Lexicographic Result: " + lexResult);
        
        // Bidirectional
        int[][] bidirectional = bidirectionalTemplate(nums1);
        System.out.println("Next Greater: " + Arrays.toString(bidirectional[0]));
        System.out.println("Previous Greater: " + Arrays.toString(bidirectional[1]));
        
        System.out.println("\n✅ Key Monotonic Stack Principles:");
        System.out.println("1. Maintain monotonic property (increasing or decreasing)");
        System.out.println("2. Remove elements that violate the property");
        System.out.println("3. Process removed elements to answer queries");
        System.out.println("4. Store indices for position-based calculations");
        System.out.println("5. Choose direction based on query type");
        System.out.println("6. Handle edge cases and empty stack conditions");
        System.out.println("7. Understand amortized O(n) time complexity");
    }
} 