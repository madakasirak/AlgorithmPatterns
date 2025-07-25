package com.dsalgo.AlgoPatterns.StackAndQueues;

import java.util.*;

/**
 * 📊 MONOTONIC STACK PATTERN - PRACTICE PROBLEMS
 * 
 * This class contains comprehensive implementations of monotonic stack algorithms
 * for finding nearest greater/smaller elements, calculating areas in histograms,
 * analyzing trends and spans, and maintaining lexicographic ordering. Monotonic
 * stacks maintain elements in a specific order (increasing or decreasing) and
 * efficiently answer nearest element queries through strategic element removal.
 * 
 * Pattern Focus: Nearest element queries, optimal substructure maintenance, trend analysis
 * Time Complexity: O(n) amortized - each element pushed and popped at most once
 * Space Complexity: O(n) for stack and result arrays
 */
public class MonotonicStack {
    
    // ============================================================================
    // 🟢 EASY PROBLEMS - Basic Monotonic Stack Concepts
    // ============================================================================
    
    /**
     * 🟢 EASY: Next Greater Element I
     * 
     * Problem: Find next greater element for elements in nums1 from nums2
     * LeetCode: https://leetcode.com/problems/next-greater-element-i/
     * 
     * Approach: Monotonic decreasing stack + HashMap
     * - Use decreasing stack to find next greater elements in nums2
     * - Store results in HashMap for quick lookup
     * - Build result array using HashMap
     * 
     * Time: O(n + m), Space: O(n) where n = nums2.length, m = nums1.length
     */
    public static int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Map<Integer, Integer> nextGreaterMap = new HashMap<>();
        Stack<Integer> stack = new Stack<>();
        
        // Process nums2 to find next greater elements
        for (int num : nums2) {
            // Pop elements smaller than current (they found their answer)
            while (!stack.isEmpty() && stack.peek() < num) {
                nextGreaterMap.put(stack.pop(), num);
            }
            stack.push(num);
        }
        
        // Build result for nums1
        int[] result = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            result[i] = nextGreaterMap.getOrDefault(nums1[i], -1);
        }
        
        return result;
    }
    
    /**
     * 🟢 EASY: Daily Temperatures
     * 
     * Problem: Find how many days until warmer temperature
     * LeetCode: https://leetcode.com/problems/daily-temperatures/
     * 
     * Approach: Monotonic decreasing stack with indices
     * - Stack stores indices of temperatures in decreasing order
     * - When finding warmer day, pop and calculate distance
     * - Result[i] = distance to next warmer day
     * 
     * Time: O(n), Space: O(n)
     */
    public static int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] result = new int[n];
        Stack<Integer> stack = new Stack<>(); // Store indices
        
        for (int i = 0; i < n; i++) {
            // Pop indices with temperatures less than current
            while (!stack.isEmpty() && temperatures[stack.peek()] < temperatures[i]) {
                int prevIndex = stack.pop();
                result[prevIndex] = i - prevIndex; // Distance to warmer day
            }
            stack.push(i);
        }
        
        return result;
    }
    
    /**
     * 🟢 EASY: Next Greater Element II (Bonus)
     * 
     * Problem: Find next greater element in circular array
     * 
     * Approach: Monotonic decreasing stack with circular processing
     * - Process array twice to handle circular nature
     * - Use modulo arithmetic for circular indexing
     * - Maintain decreasing stack of indices
     * 
     * Time: O(n), Space: O(n)
     */
    public static int[] nextGreaterElementsCircular(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        Arrays.fill(result, -1);
        Stack<Integer> stack = new Stack<>();
        
        // Process array twice for circular behavior
        for (int i = 0; i < 2 * n; i++) {
            int current = nums[i % n];
            
            // Pop elements smaller than current
            while (!stack.isEmpty() && nums[stack.peek()] < current) {
                int index = stack.pop();
                result[index] = current;
            }
            
            // Only push indices in first iteration
            if (i < n) {
                stack.push(i);
            }
        }
        
        return result;
    }
    
    // ============================================================================
    // 🟡 MEDIUM PROBLEMS - Intermediate Monotonic Stack Applications
    // ============================================================================
    
    /**
     * 🟡 MEDIUM: Largest Rectangle in Histogram
     * 
     * Problem: Find largest rectangular area in histogram
     * LeetCode: https://leetcode.com/problems/largest-rectangle-in-histogram/
     * 
     * Approach: Monotonic increasing stack for area calculation
     * - Stack maintains increasing heights with indices
     * - When popping, calculate area with popped height as minimum
     * - Width = current_index - stack.peek() - 1
     * 
     * Time: O(n), Space: O(n)
     */
    public static int largestRectangleArea(int[] heights) {
        int n = heights.length;
        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;
        
        for (int i = 0; i <= n; i++) {
            int currentHeight = (i == n) ? 0 : heights[i];
            
            // Pop elements with height greater than current
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
     * 🟡 MEDIUM: Online Stock Span
     * 
     * Problem: Calculate stock span for consecutive days with price <= current
     * LeetCode: https://leetcode.com/problems/online-stock-span/
     * 
     * Approach: Monotonic decreasing stack with price-span pairs
     * - Stack stores [price, span] pairs in decreasing price order
     * - When price is greater, pop and accumulate spans
     * - Current span = 1 + sum of popped spans
     * 
     * Time: O(1) amortized per call, Space: O(n)
     */
    public static class StockSpanner {
        private Stack<int[]> stack; // [price, span]
        
        public StockSpanner() {
            stack = new Stack<>();
        }
        
        public int next(int price) {
            int span = 1; // At least current day
            
            // Pop prices less than or equal to current and accumulate spans
            while (!stack.isEmpty() && stack.peek()[0] <= price) {
                span += stack.pop()[1];
            }
            
            stack.push(new int[]{price, span});
            return span;
        }
    }
    
    /**
     * 🟡 MEDIUM: Remove Duplicate Letters
     * 
     * Problem: Remove duplicate letters to get lexicographically smallest result
     * LeetCode: https://leetcode.com/problems/remove-duplicate-letters/
     * 
     * Approach: Monotonic increasing stack with frequency tracking
     * - Stack maintains lexicographically increasing characters
     * - Remove characters that are greater and appear later
     * - Track which characters are already in result
     * 
     * Time: O(n), Space: O(1) - limited by alphabet size
     */
    public static String removeDuplicateLetters(String s) {
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
     * 🟡 MEDIUM: Maximal Rectangle (Bonus)
     * 
     * Problem: Find largest rectangle in binary matrix
     * 
     * Approach: Convert to multiple histogram problems
     * - For each row, calculate heights as consecutive 1s
     * - Apply largest rectangle in histogram for each row
     * - Track maximum area across all rows
     * 
     * Time: O(m * n), Space: O(n)
     */
    public static int maximalRectangle(char[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) return 0;
        
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[] heights = new int[cols];
        int maxArea = 0;
        
        for (int i = 0; i < rows; i++) {
            // Update heights for current row
            for (int j = 0; j < cols; j++) {
                heights[j] = matrix[i][j] == '1' ? heights[j] + 1 : 0;
            }
            
            // Calculate max rectangle for current histogram
            maxArea = Math.max(maxArea, largestRectangleArea(heights));
        }
        
        return maxArea;
    }
    
    // ============================================================================
    // 🔴 HARD PROBLEMS - Advanced Monotonic Stack Applications
    // ============================================================================
    
    /**
     * 🔴 HARD: Sliding Window Maximum
     * 
     * Problem: Find maximum in each sliding window of size k
     * 
     * Approach: Monotonic decreasing deque
     * - Deque maintains indices in decreasing order of values
     * - Remove indices outside current window
     * - Front of deque is always maximum in current window
     * 
     * Time: O(n), Space: O(k)
     */
    public static int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int[] result = new int[n - k + 1];
        Deque<Integer> deque = new ArrayDeque<>(); // Store indices
        
        for (int i = 0; i < n; i++) {
            // Remove indices outside current window
            while (!deque.isEmpty() && deque.peekFirst() < i - k + 1) {
                deque.pollFirst();
            }
            
            // Remove indices with smaller values than current
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
                deque.pollLast();
            }
            
            deque.offerLast(i);
            
            // Record result when window is complete
            if (i >= k - 1) {
                result[i - k + 1] = nums[deque.peekFirst()];
            }
        }
        
        return result;
    }
    
    /**
     * 🔴 HARD: Shortest Unsorted Continuous Subarray
     * 
     * Problem: Find shortest subarray that needs to be sorted
     * 
     * Approach: Monotonic stacks for boundary detection
     * - Use increasing stack to find left boundary
     * - Use decreasing stack to find right boundary
     * - Combine results to find shortest unsorted subarray
     * 
     * Time: O(n), Space: O(n)
     */
    public static int findUnsortedSubarray(int[] nums) {
        int n = nums.length;
        Stack<Integer> stack = new Stack<>();
        int left = n, right = 0;
        
        // Find left boundary using increasing stack
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] > nums[i]) {
                left = Math.min(left, stack.pop());
            }
            stack.push(i);
        }
        
        stack.clear();
        
        // Find right boundary using decreasing stack
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && nums[stack.peek()] < nums[i]) {
                right = Math.max(right, stack.pop());
            }
            stack.push(i);
        }
        
        return right > left ? right - left + 1 : 0;
    }
    
    /**
     * 🔴 HARD: Create Maximum Number
     * 
     * Problem: Create maximum number from two arrays by picking k digits
     * 
     * Approach: Monotonic stack for maximum subsequence
     * - For each array, find maximum subsequence of required length
     * - Merge two subsequences to get maximum result
     * - Use monotonic decreasing stack with length constraint
     * 
     * Time: O(k * (m + n + k)), Space: O(k)
     */
    public static int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int m = nums1.length, n = nums2.length;
        int[] result = new int[k];
        
        // Try all possible distributions
        for (int i = Math.max(0, k - n); i <= Math.min(k, m); i++) {
            int[] subseq1 = maxSubsequence(nums1, i);
            int[] subseq2 = maxSubsequence(nums2, k - i);
            int[] merged = merge(subseq1, subseq2);
            
            if (isGreater(merged, 0, result, 0)) {
                result = merged;
            }
        }
        
        return result;
    }
    
    private static int[] maxSubsequence(int[] nums, int k) {
        int n = nums.length;
        int[] result = new int[k];
        int top = -1; // Stack top pointer
        int toRemove = n - k; // Number of elements to remove
        
        for (int num : nums) {
            // Remove smaller elements if we can afford to remove them
            while (top >= 0 && result[top] < num && toRemove > 0) {
                top--;
                toRemove--;
            }
            
            // Add current number if there's space
            if (top + 1 < k) {
                result[++top] = num;
            } else {
                toRemove--;
            }
        }
        
        return result;
    }
    
    private static int[] merge(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        int[] result = new int[m + n];
        int i = 0, j = 0, k = 0;
        
        while (i < m || j < n) {
            if (isGreater(nums1, i, nums2, j)) {
                result[k++] = nums1[i++];
            } else {
                result[k++] = nums2[j++];
            }
        }
        
        return result;
    }
    
    private static boolean isGreater(int[] nums1, int i, int[] nums2, int j) {
        int m = nums1.length, n = nums2.length;
        
        while (i < m && j < n && nums1[i] == nums2[j]) {
            i++;
            j++;
        }
        
        return j == n || (i < m && nums1[i] > nums2[j]);
    }
    
    // ============================================================================
    // 🧪 TESTING AND DEMONSTRATION
    // ============================================================================
    
    public static void main(String[] args) {
        System.out.println("📊 MONOTONIC STACK PATTERN - PRACTICE PROBLEMS");
        System.out.println("===============================================");
        
        // Test Easy Problems
        System.out.println("\n🟢 EASY MONOTONIC STACK PROBLEMS:");
        
        // Next Greater Element I
        int[] nums1 = {4, 1, 2};
        int[] nums2 = {1, 3, 4, 2};
        int[] nextGreater = nextGreaterElement(nums1, nums2);
        System.out.println("Next Greater Element I: " + Arrays.toString(nextGreater)); // [-1, 3, -1]
        
        // Daily Temperatures
        int[] temperatures = {73, 74, 75, 71, 69, 72, 76, 73};
        int[] tempResult = dailyTemperatures(temperatures);
        System.out.println("Daily Temperatures: " + Arrays.toString(tempResult)); // [1, 1, 4, 2, 1, 1, 0, 0]
        
        // Next Greater Element II (Circular)
        int[] circular = {1, 2, 1};
        int[] circularResult = nextGreaterElementsCircular(circular);
        System.out.println("Next Greater Element II: " + Arrays.toString(circularResult)); // [2, -1, 2]
        
        // Test Medium Problems
        System.out.println("\n🟡 MEDIUM MONOTONIC STACK PROBLEMS:");
        
        // Largest Rectangle in Histogram
        int[] heights = {2, 1, 5, 6, 2, 3};
        int maxArea = largestRectangleArea(heights);
        System.out.println("Largest Rectangle Area: " + maxArea); // 10
        
        // Online Stock Span
        StockSpanner spanner = new StockSpanner();
        System.out.print("Stock Spans: [");
        int[] stockPrices = {100, 80, 60, 70, 60, 75, 85};
        for (int i = 0; i < stockPrices.length; i++) {
            System.out.print(spanner.next(stockPrices[i]));
            if (i < stockPrices.length - 1) System.out.print(", ");
        }
        System.out.println("]"); // [1, 1, 1, 2, 1, 4, 6]
        
        // Remove Duplicate Letters
        String s = "bcabc";
        String removedDuplicates = removeDuplicateLetters(s);
        System.out.println("Remove Duplicate Letters: " + removedDuplicates); // "abc"
        
        // Maximal Rectangle
        char[][] matrix = {
            {'1', '0', '1', '0', '0'},
            {'1', '0', '1', '1', '1'},
            {'1', '1', '1', '1', '1'},
            {'1', '0', '0', '1', '0'}
        };
        int maxRectangle = maximalRectangle(matrix);
        System.out.println("Maximal Rectangle: " + maxRectangle); // 6
        
        // Test Hard Problems
        System.out.println("\n🔴 HARD MONOTONIC STACK PROBLEMS:");
        
        // Sliding Window Maximum
        int[] slideNums = {1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;
        int[] slideMax = maxSlidingWindow(slideNums, k);
        System.out.println("Sliding Window Maximum: " + Arrays.toString(slideMax)); // [3, 3, 5, 5, 6, 7]
        
        // Shortest Unsorted Continuous Subarray
        int[] unsorted = {2, 6, 4, 8, 10, 9, 15};
        int shortestLength = findUnsortedSubarray(unsorted);
        System.out.println("Shortest Unsorted Subarray Length: " + shortestLength); // 5
        
        // Create Maximum Number
        int[] maxNums1 = {3, 4, 6, 5};
        int[] maxNums2 = {9, 1, 2, 5, 8, 3};
        int maxK = 5;
        int[] maxNumberResult = maxNumber(maxNums1, maxNums2, maxK);
        System.out.println("Create Maximum Number: " + Arrays.toString(maxNumberResult)); // [9, 8, 6, 5, 3]
        
        System.out.println("\n✅ Key Monotonic Stack Principles:");
        System.out.println("1. Maintain monotonic property (increasing or decreasing)");
        System.out.println("2. Remove elements that violate the monotonic property");
        System.out.println("3. Process removed elements to answer nearest element queries");
        System.out.println("4. Store indices for position-based calculations and distances");
        System.out.println("5. Choose correct direction based on query type (greater/smaller)");
        System.out.println("6. Handle edge cases: empty stack, no answer found, boundary conditions");
        System.out.println("7. Understand amortized O(n) complexity - each element pushed/popped once");
        System.out.println("8. Use auxiliary data structures (HashMap, frequency arrays) when needed");
    }
} 