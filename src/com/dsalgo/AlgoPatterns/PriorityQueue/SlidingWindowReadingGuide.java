package com.dsalgo.AlgoPatterns.PriorityQueue;

import java.util.*;

/**
 * 🎯 SLIDING WINDOW/MINIMUM WINDOW PATTERN - COMPREHENSIVE READING GUIDE
 * 
 * ============================================================================
 * 📚 WHAT IS SLIDING WINDOW/MINIMUM WINDOW PATTERN?
 * ============================================================================
 * 
 * Sliding Window/Minimum Window Pattern focuses on efficiently processing
 * contiguous subarrays of fixed or variable size to find optimal solutions
 * (maximum, minimum, sum, or specific conditions). This pattern is essential
 * for array and string processing where we need to examine all possible
 * windows while maintaining optimal time complexity through smart data
 * structure usage and window management techniques.
 * 
 * The pattern leverages monotonic deque structures for max/min queries,
 * two-pointer techniques for variable window sizes, hash map tracking for
 * character/element frequencies, and specialized optimization strategies
 * for maintaining window properties with optimal performance characteristics.
 * 
 * 🔑 CORE PRINCIPLES:
 * 1. WINDOW MAINTENANCE: Efficiently slide window boundaries while preserving properties
 * 2. MONOTONIC OPTIMIZATION: Use deque structures for optimal max/min queries
 * 3. TWO-POINTER TECHNIQUE: Expand and contract windows based on conditions
 * 4. FREQUENCY TRACKING: Monitor element counts for substring/subarray problems
 * 
 * 🎯 SLIDING WINDOW METAPHOR:
 * Think of Sliding Window as "moving spotlight analysis":
 * - Spotlight beam: the current window examining a portion of data
 * - Smooth movement: efficiently sliding without recomputing everything
 * - Focused analysis: maintaining specific properties within the window
 * - Optimal coverage: finding best windows without examining all possibilities
 * 
 * ============================================================================
 * 🎯 WHEN TO USE SLIDING WINDOW/MINIMUM WINDOW PATTERN
 * ============================================================================
 * 
 * ✅ PERFECT FOR:
 * - Finding maximum/minimum in all contiguous subarrays of size K
 * - Variable-size window problems with specific conditions
 * - Substring problems with character frequency constraints
 * - Longest/shortest subarray problems with given properties
 * - Range query optimization for streaming data
 * - Moving average and statistical calculations
 * - Pattern matching in strings with flexibility requirements
 * - Resource allocation problems with window constraints
 * 
 * 🔍 LOOK FOR THESE PHRASES:
 * - "Sliding window maximum/minimum"
 * - "Contiguous subarray of size K"
 * - "Minimum window substring"
 * - "Longest subarray with condition"
 * - "All subarrays of size K"
 * - "Maximum sum of subarray"
 * - "Window contains all characters"
 * 
 * 🚩 RED FLAGS (Consider Other Approaches):
 * - Non-contiguous subarray problems (use different techniques)
 * - Need all possible subsequences (use backtracking/DP)
 * - Global optimization without locality (use different algorithms)
 * - Single-element queries (use direct indexing)
 * 
 * ============================================================================
 * 🔄 SLIDING WINDOW PATTERN VARIATIONS
 * ============================================================================
 * 
 * 1️⃣ FIXED-SIZE WINDOW WITH MONOTONIC DEQUE
 * - Deque-based max/min tracking
 * - Constant window size sliding
 * - Optimal O(n) time complexity
 * - Best for range max/min queries
 * 
 * 2️⃣ VARIABLE-SIZE WINDOW WITH TWO POINTERS
 * - Expand and contract window dynamically
 * - Condition-based window adjustment
 * - Flexible window size optimization
 * - Ideal for longest/shortest subarray problems
 * 
 * 3️⃣ CHARACTER FREQUENCY TRACKING
 * - Hash map for character/element counting
 * - Substring pattern matching
 * - Minimum window covering problems
 * - String processing optimization
 * 
 * 4️⃣ SUM-BASED WINDOW OPTIMIZATION
 * - Running sum maintenance
 * - Prefix sum optimization
 * - Maximum sum subarray variants
 * - Statistical calculations in windows
 * 
 * 5️⃣ CONDITION-BASED WINDOW MANAGEMENT
 * - Complex constraint satisfaction
 * - Multi-criteria window validation
 * - Advanced filtering and selection
 * - Real-time data processing
 * 
 * 6️⃣ MONOTONIC QUEUE OPTIMIZATION
 * - Specialized queue structures
 * - Efficient element tracking
 * - Range query optimization
 * - Advanced window property maintenance
 * 
 * ============================================================================
 * 🧠 CORE ALGORITHMS AND STRATEGIES
 * ============================================================================
 * 
 * 🔹 MONOTONIC DEQUE ALGORITHM:
 * ```
 * Fixed-size window maximum:
 *   1. Use deque to store indices in decreasing order of values
 *   2. For each element:
 *      - Remove indices outside current window
 *      - Remove smaller elements from back (maintain decreasing order)
 *      - Add current index to back
 *      - Front element is maximum of current window
 * 
 * Time: O(n), Space: O(k)
 * ```
 * 
 * 🔹 TWO-POINTER WINDOW ALGORITHM:
 * ```
 * Variable window with condition:
 *   1. Initialize left=0, right=0
 *   2. Expand right while condition not satisfied
 *   3. Contract left while condition is satisfied
 *   4. Update optimal result during expansion/contraction
 *   5. Continue until right reaches end
 * 
 * Time: O(n), Space: O(1) or O(k) for tracking
 * ```
 * 
 * 🔹 MINIMUM WINDOW ALGORITHM:
 * ```
 * Minimum window covering all characters:
 *   1. Use frequency map for target characters
 *   2. Expand window until all characters covered
 *   3. Contract window while maintaining coverage
 *   4. Update minimum window during contraction
 *   5. Continue until no valid windows remain
 * 
 * Time: O(n + m), Space: O(k) for character set
 * ```
 * 
 * 🔹 SLIDING WINDOW SUM ALGORITHM:
 * ```
 * Maximum sum of subarray size K:
 *   1. Calculate sum of first K elements
 *   2. Slide window: subtract left element, add right element
 *   3. Update maximum sum after each slide
 *   4. Continue until end of array
 * 
 * Time: O(n), Space: O(1)
 * ```
 * 
 * ============================================================================
 * 📋 ALGORITHM SELECTION FRAMEWORK
 * ============================================================================
 * 
 * STEP 1: IDENTIFY WINDOW TYPE
 * - Fixed size vs variable size?
 * - Maximum/minimum vs sum vs condition-based?
 * - Single value vs multiple criteria?
 * 
 * STEP 2: CHOOSE OPTIMIZATION STRATEGY
 * - Monotonic deque: for max/min in fixed windows
 * - Two pointers: for variable size with conditions
 * - Hash map tracking: for frequency-based problems
 * 
 * STEP 3: IMPLEMENT CORE ALGORITHM
 * - Handle window expansion and contraction logic
 * - Maintain necessary data structures efficiently
 * - Update results at appropriate times
 * 
 * STEP 4: OPTIMIZE FOR CONSTRAINTS
 * - Memory optimization: choose minimal tracking structures
 * - Time optimization: avoid redundant computations
 * - Edge case handling: empty arrays, single elements
 * 
 * STEP 5: VALIDATE AND TEST
 * - Test with various window sizes and array lengths
 * - Verify edge cases and boundary conditions
 * - Benchmark performance for large inputs
 * 
 * ============================================================================
 * 🎨 SLIDING WINDOW ALGORITHM TEMPLATES
 * ============================================================================
 */

public class SlidingWindowReadingGuide {
    
    /**
     * 🏆 MONOTONIC DEQUE TEMPLATES
     */
    public static class MonotonicDequeTemplates {
        
        /**
         * Sliding Window Maximum using Monotonic Deque
         * Strategy: Maintain decreasing order of indices in deque
         */
        public static int[] maxSlidingWindow(int[] nums, int k) {
            if (nums.length == 0 || k == 0) return new int[0];
            
            Deque<Integer> deque = new ArrayDeque<>(); // Store indices
            int[] result = new int[nums.length - k + 1];
            
            for (int i = 0; i < nums.length; i++) {
                // Remove indices outside current window
                while (!deque.isEmpty() && deque.peekFirst() < i - k + 1) {
                    deque.pollFirst();
                }
                
                // Remove smaller elements from back (maintain decreasing order)
                while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
                    deque.pollLast();
                }
                
                deque.offerLast(i);
                
                // Add maximum to result once window is full
                if (i >= k - 1) {
                    result[i - k + 1] = nums[deque.peekFirst()];
                }
            }
            
            return result;
        }
        
        /**
         * Sliding Window Minimum using Monotonic Deque
         * Strategy: Maintain increasing order of indices in deque
         */
        public static int[] minSlidingWindow(int[] nums, int k) {
            if (nums.length == 0 || k == 0) return new int[0];
            
            Deque<Integer> deque = new ArrayDeque<>(); // Store indices
            int[] result = new int[nums.length - k + 1];
            
            for (int i = 0; i < nums.length; i++) {
                // Remove indices outside current window
                while (!deque.isEmpty() && deque.peekFirst() < i - k + 1) {
                    deque.pollFirst();
                }
                
                // Remove larger elements from back (maintain increasing order)
                while (!deque.isEmpty() && nums[deque.peekLast()] > nums[i]) {
                    deque.pollLast();
                }
                
                deque.offerLast(i);
                
                // Add minimum to result once window is full
                if (i >= k - 1) {
                    result[i - k + 1] = nums[deque.peekFirst()];
                }
            }
            
            return result;
        }
        
        /**
         * Constrained Subarray Sum with Deque
         * Strategy: Use deque to maintain optimal sliding window bounds
         */
        public static int constrainedSubsetSum(int[] nums, int k) {
            Deque<Integer> deque = new ArrayDeque<>();
            int[] dp = new int[nums.length];
            
            for (int i = 0; i < nums.length; i++) {
                // Remove indices outside window of size k
                while (!deque.isEmpty() && deque.peekFirst() < i - k) {
                    deque.pollFirst();
                }
                
                // Current maximum sum ending at i
                dp[i] = nums[i] + (deque.isEmpty() ? 0 : Math.max(0, dp[deque.peekFirst()]));
                
                // Maintain decreasing order in deque
                while (!deque.isEmpty() && dp[deque.peekLast()] <= dp[i]) {
                    deque.pollLast();
                }
                
                deque.offerLast(i);
            }
            
            return Arrays.stream(dp).max().orElse(0);
        }
    }
    
    /**
     * ⚡ TWO-POINTER WINDOW TEMPLATES
     */
    public static class TwoPointerTemplates {
        
        /**
         * Longest Subarray with At Most K Distinct Elements
         * Strategy: Expand right, contract left when condition violated
         */
        public static int longestSubarrayWithKDistinct(int[] nums, int k) {
            Map<Integer, Integer> count = new HashMap<>();
            int left = 0, maxLength = 0;
            
            for (int right = 0; right < nums.length; right++) {
                // Expand window
                count.put(nums[right], count.getOrDefault(nums[right], 0) + 1);
                
                // Contract window if too many distinct elements
                while (count.size() > k) {
                    count.put(nums[left], count.get(nums[left]) - 1);
                    if (count.get(nums[left]) == 0) {
                        count.remove(nums[left]);
                    }
                    left++;
                }
                
                maxLength = Math.max(maxLength, right - left + 1);
            }
            
            return maxLength;
        }
        
        /**
         * Longest Subarray with Condition
         * Strategy: Two-pointer with condition checking
         */
        public static int longestSubarrayWithCondition(int[] nums, int limit) {
            Deque<Integer> maxDeque = new ArrayDeque<>();
            Deque<Integer> minDeque = new ArrayDeque<>();
            int left = 0, maxLength = 0;
            
            for (int right = 0; right < nums.length; right++) {
                // Maintain max deque
                while (!maxDeque.isEmpty() && nums[maxDeque.peekLast()] <= nums[right]) {
                    maxDeque.pollLast();
                }
                maxDeque.offerLast(right);
                
                // Maintain min deque
                while (!minDeque.isEmpty() && nums[minDeque.peekLast()] >= nums[right]) {
                    minDeque.pollLast();
                }
                minDeque.offerLast(right);
                
                // Contract window if condition violated
                while (nums[maxDeque.peekFirst()] - nums[minDeque.peekFirst()] > limit) {
                    if (maxDeque.peekFirst() == left) maxDeque.pollFirst();
                    if (minDeque.peekFirst() == left) minDeque.pollFirst();
                    left++;
                }
                
                maxLength = Math.max(maxLength, right - left + 1);
            }
            
            return maxLength;
        }
        
        /**
         * Maximum Consecutive Ones with K Flips
         * Strategy: Track zeros count in sliding window
         */
        public static int longestOnes(int[] nums, int k) {
            int left = 0, zeroCount = 0, maxLength = 0;
            
            for (int right = 0; right < nums.length; right++) {
                // Expand window
                if (nums[right] == 0) {
                    zeroCount++;
                }
                
                // Contract window if too many zeros
                while (zeroCount > k) {
                    if (nums[left] == 0) {
                        zeroCount--;
                    }
                    left++;
                }
                
                maxLength = Math.max(maxLength, right - left + 1);
            }
            
            return maxLength;
        }
    }
    
    /**
     * 📊 CHARACTER FREQUENCY TEMPLATES
     */
    public static class CharacterFrequencyTemplates {
        
        /**
         * Minimum Window Substring
         * Strategy: Expand until all characters covered, then contract
         */
        public static String minWindow(String s, String t) {
            if (s.length() == 0 || t.length() == 0) return "";
            
            // Frequency map for target string
            Map<Character, Integer> dictT = new HashMap<>();
            for (char c : t.toCharArray()) {
                dictT.put(c, dictT.getOrDefault(c, 0) + 1);
            }
            
            int required = dictT.size();
            int formed = 0;
            Map<Character, Integer> windowCounts = new HashMap<>();
            
            int left = 0, right = 0;
            int minLen = Integer.MAX_VALUE;
            int minLeft = 0;
            
            while (right < s.length()) {
                // Expand window
                char c = s.charAt(right);
                windowCounts.put(c, windowCounts.getOrDefault(c, 0) + 1);
                
                if (dictT.containsKey(c) && windowCounts.get(c).intValue() == dictT.get(c).intValue()) {
                    formed++;
                }
                
                // Contract window when all characters are covered
                while (left <= right && formed == required) {
                    if (right - left + 1 < minLen) {
                        minLen = right - left + 1;
                        minLeft = left;
                    }
                    
                    char leftChar = s.charAt(left);
                    windowCounts.put(leftChar, windowCounts.get(leftChar) - 1);
                    if (dictT.containsKey(leftChar) && windowCounts.get(leftChar) < dictT.get(leftChar)) {
                        formed--;
                    }
                    left++;
                }
                
                right++;
            }
            
            return minLen == Integer.MAX_VALUE ? "" : s.substring(minLeft, minLeft + minLen);
        }
        
        /**
         * Longest Substring Without Repeating Characters
         * Strategy: Track character positions and slide window
         */
        public static int lengthOfLongestSubstring(String s) {
            Map<Character, Integer> charIndex = new HashMap<>();
            int left = 0, maxLength = 0;
            
            for (int right = 0; right < s.length(); right++) {
                char c = s.charAt(right);
                
                if (charIndex.containsKey(c) && charIndex.get(c) >= left) {
                    left = charIndex.get(c) + 1;
                }
                
                charIndex.put(c, right);
                maxLength = Math.max(maxLength, right - left + 1);
            }
            
            return maxLength;
        }
    }
    
    /**
     * 💰 SUM-BASED WINDOW TEMPLATES
     */
    public static class SumBasedTemplates {
        
        /**
         * Maximum Sum of Subarray of Size K
         * Strategy: Sliding window with sum maintenance
         */
        public static int maxSumSubarrayOfSizeK(int[] nums, int k) {
            if (nums.length < k) return 0;
            
            // Calculate sum of first window
            int windowSum = 0;
            for (int i = 0; i < k; i++) {
                windowSum += nums[i];
            }
            
            int maxSum = windowSum;
            
            // Slide window and update sum
            for (int i = k; i < nums.length; i++) {
                windowSum = windowSum - nums[i - k] + nums[i];
                maxSum = Math.max(maxSum, windowSum);
            }
            
            return maxSum;
        }
        
        /**
         * Subarray Sum Equals K (Count)
         * Strategy: Prefix sum with hash map
         */
        public static int subarraySum(int[] nums, int k) {
            Map<Integer, Integer> prefixSumCount = new HashMap<>();
            prefixSumCount.put(0, 1);
            
            int count = 0, prefixSum = 0;
            
            for (int num : nums) {
                prefixSum += num;
                count += prefixSumCount.getOrDefault(prefixSum - k, 0);
                prefixSumCount.put(prefixSum, prefixSumCount.getOrDefault(prefixSum, 0) + 1);
            }
            
            return count;
        }
    }
    
    // ============================================================================
    // 🚨 COMMON PITFALLS TO AVOID
    // ============================================================================
    
    /**
     * ❌ PITFALL 1: Incorrect Deque Maintenance
     * Ensure proper order maintenance in monotonic deque
     */
    public static void dequeMaintenanceExample() {
        Deque<Integer> deque = new ArrayDeque<>();
        int[] nums = {1, 3, 2, 4};
        
        // Correct: Remove smaller elements for max deque
        for (int i = 0; i < nums.length; i++) {
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
                deque.pollLast();
            }
            deque.offerLast(i);
        }
        
        // Incorrect: Wrong comparison or removal logic
    }
    
    /**
     * ❌ PITFALL 2: Window Boundary Errors
     * Careful with window size and boundary calculations
     */
    public static void windowBoundaryExample() {
        int[] nums = {1, 2, 3, 4, 5};
        int k = 3;
        
        // Correct: Proper window boundary checking
        for (int i = 0; i < nums.length; i++) {
            if (i >= k - 1) {
                // Process window [i-k+1, i]
            }
        }
        
        // Incorrect: Off-by-one errors in window boundaries
    }
    
    // ============================================================================
    // 💡 OPTIMIZATION STRATEGIES
    // ============================================================================
    
    /**
     * 🎯 STRATEGY 1: Choose Right Data Structure
     * - Deque: for max/min queries in fixed windows
     * - Hash map: for frequency tracking and character problems
     * - Two pointers: for variable size windows with conditions
     */
    
    /**
     * 🎯 STRATEGY 2: Window Management Optimization
     * - Monotonic structures: maintain sorted order for efficient queries
     * - Lazy removal: remove elements only when necessary
     * - Batch operations: minimize individual element processing
     */
    
    // ============================================================================
    // 🧭 DECISION FRAMEWORK
    // ============================================================================
    
    /**
     * CHOOSE ALGORITHM BASED ON:
     * 
     * WINDOW TYPE:
     * ✅ Fixed size → Monotonic deque or simple sliding
     * ✅ Variable size → Two pointers with condition checking
     * ✅ Character-based → Hash map frequency tracking
     * ✅ Sum-based → Running sum with prefix calculations
     * 
     * QUERY TYPE:
     * ✅ Max/Min → Monotonic deque for O(1) queries
     * ✅ Sum → Simple arithmetic with sliding
     * ✅ Count/Frequency → Hash map tracking
     * ✅ Condition → Two-pointer expansion/contraction
     */
    
    public static void main(String[] args) {
        System.out.println("🎯 SLIDING WINDOW/MINIMUM WINDOW PATTERN - READING GUIDE");
        System.out.println("========================================================");
        
        // Test examples
        int[] testArray = {1, 3, -1, -3, 5, 3, 6, 7};
        
        // Monotonic deque examples
        int[] maxWindow = MonotonicDequeTemplates.maxSlidingWindow(testArray, 3);
        System.out.println("Sliding window maximum (k=3): " + Arrays.toString(maxWindow)); // [3, 3, 5, 5, 6, 7]
        
        int[] minWindow = MonotonicDequeTemplates.minSlidingWindow(testArray, 3);
        System.out.println("Sliding window minimum (k=3): " + Arrays.toString(minWindow)); // [-1, -3, -3, -3, 3, 3]
        
        // Two-pointer examples
        int[] nums = {1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0};
        int maxOnes = TwoPointerTemplates.longestOnes(nums, 2);
        System.out.println("Max consecutive ones with 2 flips: " + maxOnes); // 6
        
        // Character frequency examples
        String minWindowResult = CharacterFrequencyTemplates.minWindow("ADOBECODEBANC", "ABC");
        System.out.println("Minimum window substring: " + minWindowResult); // "BANC"
        
        int longestSubstring = CharacterFrequencyTemplates.lengthOfLongestSubstring("abcabcbb");
        System.out.println("Longest substring without repeating: " + longestSubstring); // 3
        
        // Sum-based examples
        int[] sumArray = {2, 1, 3, 4, 1};
        int maxSum = SumBasedTemplates.maxSumSubarrayOfSizeK(sumArray, 2);
        System.out.println("Maximum sum of subarray size 2: " + maxSum); // 7
        
        System.out.println("\n✅ Key Sliding Window Principles:");
        System.out.println("1. Use monotonic deque for efficient max/min queries in fixed windows");
        System.out.println("2. Apply two-pointer technique for variable-size windows with conditions");
        System.out.println("3. Track character/element frequencies with hash maps for substring problems");
        System.out.println("4. Maintain running sums for sum-based window optimizations");
        System.out.println("5. Handle window expansion and contraction logic carefully");
        System.out.println("6. Choose appropriate data structures based on query requirements");
        System.out.println("7. Optimize for both time and space complexity constraints");
    }
} 