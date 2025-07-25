package com.dsalgo.AlgoPatterns.PriorityQueue;

import java.util.*;

/**
 * 🎯 SLIDING WINDOW/MINIMUM WINDOW PATTERN - PRACTICE PROBLEMS
 * 
 * This class contains comprehensive implementations of sliding window algorithms
 * for efficiently processing contiguous subarrays and substrings. These algorithms
 * demonstrate monotonic deque optimization for max/min queries, two-pointer techniques
 * for variable window sizes, hash map tracking for character frequencies, and
 * specialized optimization strategies for maintaining window properties across
 * various computational scenarios and constraint requirements.
 * 
 * Pattern Focus: Window management, monotonic optimization, frequency tracking
 * Time Complexity: O(n) for most sliding window operations
 * Space Complexity: O(k) for window-size dependent structures, O(1) for simple sliding
 */

public class SlidingWindow {
    
    // ============================================================================
    // 🔴 HARD PROBLEMS - Advanced Sliding Window Algorithms
    // ============================================================================
    
    /**
     * 🔴 HARD: Sliding Window Maximum
     * 
     * Problem: Find maximum in all contiguous subarrays of size k
     * LeetCode: https://leetcode.com/problems/sliding-window-maximum/
     * 
     * Approach: Monotonic Deque Optimization
     * - Use deque to store indices in decreasing order of values
     * - Front of deque always contains index of maximum element
     * - Remove indices outside current window from front
     * - Remove smaller elements from back to maintain decreasing order
     * - Achieves O(n) time complexity with each element added/removed at most once
     * 
     * Time: O(n), Space: O(k)
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
     * Alternative: Priority Queue approach (less efficient)
     * - Use max-heap with (value, index) pairs
     * - Remove outdated elements from top when needed
     * - Less efficient due to O(log k) operations
     * 
     * Time: O(n log k), Space: O(k)
     */
    public static int[] maxSlidingWindowHeap(int[] nums, int k) {
        if (nums.length == 0 || k == 0) return new int[0];
        
        // Max-heap storing [value, index]
        java.util.PriorityQueue<int[]> maxHeap = new java.util.PriorityQueue<>(
            (a, b) -> b[0] - a[0] // Compare by value in descending order
        );
        
        int[] result = new int[nums.length - k + 1];
        
        for (int i = 0; i < nums.length; i++) {
            maxHeap.offer(new int[]{nums[i], i});
            
            // Remove outdated elements from heap top
            while (!maxHeap.isEmpty() && maxHeap.peek()[1] < i - k + 1) {
                maxHeap.poll();
            }
            
            if (i >= k - 1) {
                result[i - k + 1] = maxHeap.peek()[0];
            }
        }
        
        return result;
    }
    
    /**
     * 🔴 HARD: Minimum Window Substring
     * 
     * Problem: Find minimum window in s that contains all characters of t
     * LeetCode: https://leetcode.com/problems/minimum-window-substring/
     * 
     * Approach: Two-Pointer with Character Frequency Tracking
     * - Use frequency map for target string characters
     * - Expand window until all characters are covered
     * - Contract window while maintaining complete coverage
     * - Track minimum window during contraction phase
     * - Efficient single-pass algorithm with optimal time complexity
     * 
     * Time: O(|s| + |t|), Space: O(|s| + |t|)
     */
    public static String minWindow(String s, String t) {
        if (s.length() == 0 || t.length() == 0) return "";
        
        // Frequency map for target string
        Map<Character, Integer> dictT = new HashMap<>();
        for (char c : t.toCharArray()) {
            dictT.put(c, dictT.getOrDefault(c, 0) + 1);
        }
        
        int required = dictT.size(); // Number of unique characters in t
        int formed = 0; // Number of unique characters in current window with desired frequency
        
        Map<Character, Integer> windowCounts = new HashMap<>();
        
        int left = 0, right = 0;
        int minLen = Integer.MAX_VALUE;
        int minLeft = 0;
        
        while (right < s.length()) {
            // Expand window by including character at right
            char c = s.charAt(right);
            windowCounts.put(c, windowCounts.getOrDefault(c, 0) + 1);
            
            // Check if frequency of current character matches desired count
            if (dictT.containsKey(c) && windowCounts.get(c).intValue() == dictT.get(c).intValue()) {
                formed++;
            }
            
            // Contract window while all characters are covered
            while (left <= right && formed == required) {
                // Update minimum window if current is smaller
                if (right - left + 1 < minLen) {
                    minLen = right - left + 1;
                    minLeft = left;
                }
                
                // Remove character at left from window
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
     * 🔴 HARD: Longest Contiguous Subarray With Absolute Diff Less Than or Equal to Limit
     * 
     * Problem: Find longest subarray where absolute difference between max and min is ≤ limit
     * LeetCode: https://leetcode.com/problems/longest-contiguous-subarray-with-absolute-diff-less-than-or-equal-to-limit/
     * 
     * Approach: Dual Monotonic Deques
     * - Use one deque for maximum elements (decreasing order)
     * - Use another deque for minimum elements (increasing order)
     * - Expand window while difference ≤ limit
     * - Contract window when difference > limit
     * - Maintain both deques during window operations
     * 
     * Time: O(n), Space: O(n)
     */
    public static int longestSubarray(int[] nums, int limit) {
        Deque<Integer> maxDeque = new ArrayDeque<>(); // Store indices in decreasing order of values
        Deque<Integer> minDeque = new ArrayDeque<>(); // Store indices in increasing order of values
        
        int left = 0, maxLength = 0;
        
        for (int right = 0; right < nums.length; right++) {
            // Maintain max deque (decreasing order)
            while (!maxDeque.isEmpty() && nums[maxDeque.peekLast()] <= nums[right]) {
                maxDeque.pollLast();
            }
            maxDeque.offerLast(right);
            
            // Maintain min deque (increasing order)
            while (!minDeque.isEmpty() && nums[minDeque.peekLast()] >= nums[right]) {
                minDeque.pollLast();
            }
            minDeque.offerLast(right);
            
            // Contract window if difference exceeds limit
            while (nums[maxDeque.peekFirst()] - nums[minDeque.peekFirst()] > limit) {
                if (maxDeque.peekFirst() == left) {
                    maxDeque.pollFirst();
                }
                if (minDeque.peekFirst() == left) {
                    minDeque.pollFirst();
                }
                left++;
            }
            
            maxLength = Math.max(maxLength, right - left + 1);
        }
        
        return maxLength;
    }
    
    // ============================================================================
    // 🟡 MEDIUM PROBLEMS - Core Sliding Window Techniques
    // ============================================================================
    
    /**
     * 🟡 MEDIUM: Maximum Sum of Subarray of Size K
     * 
     * Problem: Find maximum sum of contiguous subarray of size k
     * 
     * Approach: Simple Sliding Window with Sum Maintenance
     * - Calculate sum of first k elements
     * - Slide window by removing left element and adding right element
     * - Track maximum sum during sliding process
     * - Classic sliding window technique for sum-based problems
     * 
     * Time: O(n), Space: O(1)
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
            windowSum = windowSum - nums[i - k] + nums[i]; // Remove left, add right
            maxSum = Math.max(maxSum, windowSum);
        }
        
        return maxSum;
    }
    
    /**
     * 🟡 MEDIUM: Max Consecutive Ones III
     * 
     * Problem: Find longest subarray of 1s after flipping at most k zeros
     * LeetCode: https://leetcode.com/problems/max-consecutive-ones-iii/
     * 
     * Approach: Two-Pointer with Zero Count Tracking
     * - Use two pointers to maintain a sliding window
     * - Expand window by moving right pointer
     * - Count zeros in current window
     * - Contract window when zero count exceeds k
     * - Track maximum window size during expansion
     * 
     * Time: O(n), Space: O(1)
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
    
    /**
     * Bonus: Longest Substring Without Repeating Characters
     * - Classic sliding window with character tracking
     * - Use hash map to store character positions
     * - Slide window when duplicate character found
     * 
     * Time: O(n), Space: O(min(m, n)) where m is character set size
     */
    public static int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> charIndex = new HashMap<>();
        int left = 0, maxLength = 0;
        
        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            
            // If character seen before and is in current window
            if (charIndex.containsKey(c) && charIndex.get(c) >= left) {
                left = charIndex.get(c) + 1;
            }
            
            charIndex.put(c, right);
            maxLength = Math.max(maxLength, right - left + 1);
        }
        
        return maxLength;
    }
    
    /**
     * Bonus: Longest Subarray with At Most K Distinct Elements
     * - Variable window with distinct element counting
     * - Use hash map to track element frequencies
     * - Contract window when distinct count exceeds k
     * 
     * Time: O(n), Space: O(k)
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
    
    // ============================================================================
    // 🟢 EASY PROBLEMS - Basic Sliding Window Operations
    // ============================================================================
    
    /**
     * 🟢 EASY: Average of Subarrays of Size K
     * 
     * Problem: Find average of all contiguous subarrays of size k
     * 
     * Approach: Simple Sliding Window with Average Calculation
     * - Maintain running sum of current window
     * - Calculate average for each window position
     * - Slide window by updating sum efficiently
     * 
     * Time: O(n), Space: O(1)
     */
    public static double[] findAverages(int[] nums, int k) {
        if (nums.length < k) return new double[0];
        
        double[] result = new double[nums.length - k + 1];
        
        // Calculate sum of first window
        int windowSum = 0;
        for (int i = 0; i < k; i++) {
            windowSum += nums[i];
        }
        result[0] = (double) windowSum / k;
        
        // Slide window and calculate averages
        for (int i = k; i < nums.length; i++) {
            windowSum = windowSum - nums[i - k] + nums[i];
            result[i - k + 1] = (double) windowSum / k;
        }
        
        return result;
    }
    
    /**
     * 🟢 EASY: Contains Duplicate within K Distance
     * 
     * Problem: Check if array contains duplicate within k distance
     * 
     * Approach: Sliding Window with Set Tracking
     * - Maintain set of elements in current window
     * - Check for duplicates when adding new elements
     * - Remove elements outside window boundary
     * 
     * Time: O(n), Space: O(k)
     */
    public static boolean containsNearbyDuplicate(int[] nums, int k) {
        Set<Integer> window = new HashSet<>();
        
        for (int i = 0; i < nums.length; i++) {
            // Remove element outside window
            if (i > k) {
                window.remove(nums[i - k - 1]);
            }
            
            // Check for duplicate in current window
            if (window.contains(nums[i])) {
                return true;
            }
            
            window.add(nums[i]);
        }
        
        return false;
    }
    
    // ============================================================================
    // 🧪 TESTING AND DEMONSTRATION
    // ============================================================================
    
    public static void main(String[] args) {
        System.out.println("🎯 SLIDING WINDOW/MINIMUM WINDOW PATTERN - PRACTICE PROBLEMS");
        System.out.println("===========================================================");
        
        // Test Hard Problems
        System.out.println("\n🔴 HARD PROBLEMS:");
        
        // Test Sliding Window Maximum
        int[] maxWindowArray = {1, 3, -1, -3, 5, 3, 6, 7};
        int[] maxResults = maxSlidingWindow(maxWindowArray, 3);
        System.out.println("Sliding window maximum (k=3): " + Arrays.toString(maxResults)); // [3, 3, 5, 5, 6, 7]
        
        int[] maxResultsHeap = maxSlidingWindowHeap(maxWindowArray, 3);
        System.out.println("Sliding window maximum (heap): " + Arrays.toString(maxResultsHeap)); // [3, 3, 5, 5, 6, 7]
        
        // Test Minimum Window Substring
        String minWindowResult = minWindow("ADOBECODEBANC", "ABC");
        System.out.println("Minimum window substring: " + minWindowResult); // "BANC"
        
        String minWindowResult2 = minWindow("a", "a");
        System.out.println("Minimum window substring (edge case): " + minWindowResult2); // "a"
        
        // Test Longest Subarray with Limit
        int[] limitArray = {8, 2, 4, 7};
        int longestWithLimit = longestSubarray(limitArray, 4);
        System.out.println("Longest subarray with limit 4: " + longestWithLimit); // 2
        
        int[] limitArray2 = {10, 1, 2, 4, 7, 2};
        int longestWithLimit2 = longestSubarray(limitArray2, 5);
        System.out.println("Longest subarray with limit 5: " + longestWithLimit2); // 4
        
        // Test Medium Problems
        System.out.println("\n🟡 MEDIUM PROBLEMS:");
        
        // Test Maximum Sum of Subarray
        int[] sumArray = {2, 1, 3, 4, 1};
        int maxSum = maxSumSubarrayOfSizeK(sumArray, 2);
        System.out.println("Maximum sum of subarray size 2: " + maxSum); // 7
        
        // Test Max Consecutive Ones III
        int[] onesArray = {1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0};
        int maxOnes = longestOnes(onesArray, 2);
        System.out.println("Max consecutive ones with 2 flips: " + maxOnes); // 6
        
        int[] onesArray2 = {0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1};
        int maxOnes2 = longestOnes(onesArray2, 3);
        System.out.println("Max consecutive ones with 3 flips: " + maxOnes2); // 10
        
        // Test Longest Substring Without Repeating
        int longestSubstring = lengthOfLongestSubstring("abcabcbb");
        System.out.println("Longest substring without repeating: " + longestSubstring); // 3
        
        // Test Longest Subarray with K Distinct
        int[] distinctArray = {1, 2, 1, 2, 3};
        int longestDistinct = longestSubarrayWithKDistinct(distinctArray, 2);
        System.out.println("Longest subarray with 2 distinct: " + longestDistinct); // 4
        
        // Test Easy Problems
        System.out.println("\n🟢 EASY PROBLEMS:");
        
        // Test Average of Subarrays
        int[] avgArray = {1, 3, 2, 6, -1, 4, 1, 8, 2};
        double[] averages = findAverages(avgArray, 5);
        System.out.println("Averages of subarrays size 5: " + Arrays.toString(averages)); // [2.2, 2.8, 2.4, 3.6, 2.8]
        
        // Test Contains Nearby Duplicate
        int[] dupArray = {1, 2, 3, 1};
        boolean hasDuplicate = containsNearbyDuplicate(dupArray, 3);
        System.out.println("Contains duplicate within distance 3: " + hasDuplicate); // true
        
        int[] dupArray2 = {1, 0, 1, 1};
        boolean hasDuplicate2 = containsNearbyDuplicate(dupArray2, 1);
        System.out.println("Contains duplicate within distance 1: " + hasDuplicate2); // true
        
        System.out.println("\n✅ Key Sliding Window Principles:");
        System.out.println("1. Use monotonic deque for efficient max/min queries in fixed windows");
        System.out.println("2. Apply two-pointer technique for variable-size windows with conditions");
        System.out.println("3. Track character/element frequencies with hash maps for substring problems");
        System.out.println("4. Maintain running sums for sum-based window optimizations");
        System.out.println("5. Handle window expansion and contraction logic carefully");
        System.out.println("6. Choose appropriate data structures based on query requirements");
        System.out.println("7. Optimize for both time and space complexity constraints");
        System.out.println("8. Use dual deques for problems requiring both max and min tracking");
    }
} 