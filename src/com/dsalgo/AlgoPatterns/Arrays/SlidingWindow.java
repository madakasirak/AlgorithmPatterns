package com.dsalgo.AlgoPatterns.Arrays;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*
 * Sliding Window is a pattern used to perform operations on a specific window 
 * size of an array or string. It involves creating a window of elements and 
 * sliding it across the data structure to find optimal solutions.
 * 
 * Identify problems involving contiguous subarrays or substrings where you need 
 * to maintain a window of elements and slide it across the array.
 * 
 * Look for tasks where you need to track a subset of elements within the array 
 * that satisfy specific conditions.
 * 
 * Look for problems involving contiguous subarrays, hints that a "window" needs 
 * to slide through the array or mention of properties like "maximum sum" or 
 * "minimum length."
 * 
 * Example:
 * Input: nums = [2,1,2,3,3,4,4,5,6], k = 4
 * Output: 2.50000
 * Explanation: Maximum average subarray of length 4 is [3,3,4,4] with average 2.5
 */
public class SlidingWindow {

    public static void main(String[] args) {
        // Test Sliding Window problems
        System.out.println("=== SLIDING WINDOW PROBLEMS TEST ===");
        
        // Test Maximum Sum Subarray
        int[] nums1 = {2,1,2,3,3,4,4,5,6};
        System.out.println("Max sum of size 4: " + maxSumSubarray(nums1, 4));
        
        // Test Minimum Size Subarray Sum
        int[] nums2 = {2,3,1,2,4,3};
        System.out.println("Min subarray length for sum 7: " + minSubArrayLen(7, nums2));
        
        // Test Longest Substring Without Repeating
        String s = "abcabcbb";
        System.out.println("Longest substring without repeating: " + lengthOfLongestSubstring(s));
        
        // Test Maximum Points from Cards
        int[] cardPoints = {1,2,3,4,5,6,1};
        System.out.println("Max points from 3 cards: " + maxScore(cardPoints, 3));
        
        // Test Longest Mountain
        int[] mountain = {2,1,4,7,3,2,5};
        System.out.println("Longest mountain: " + longestMountain(mountain));
    }

    // ==================================================================================
    //                          SLIDING WINDOW PRACTICE PROBLEMS
    // ==================================================================================
    
    // ==================================================================================
    //                                  EASY PROBLEMS
    // ==================================================================================
    
    /**
     * PROBLEM: Maximum Sum Subarray of Size K
     * DIFFICULTY: Easy
     * PATTERN: Fixed Size Window
     * 
     * DESCRIPTION:
     * Given an array of integers and a number k, find the maximum sum of any 
     * contiguous subarray of size k.
     * 
     * APPROACH:
     * 1. Calculate sum of first window of size k
     * 2. Slide window by removing leftmost and adding rightmost element
     * 3. Track maximum sum seen so far
     * 
     * TIME COMPLEXITY: O(n)
     * SPACE COMPLEXITY: O(1)
     */
    public static int maxSumSubarray(int[] nums, int k) {
        if (nums.length < k) return -1;
        
        // Calculate sum of first window
        int windowSum = 0;
        for (int i = 0; i < k; i++) {
            windowSum += nums[i];
        }
        
        int maxSum = windowSum;
        
        // Slide the window
        for (int i = k; i < nums.length; i++) {
            windowSum = windowSum - nums[i - k] + nums[i];
            maxSum = Math.max(maxSum, windowSum);
        }
        
        return maxSum;
    }
    
    /**
     * PROBLEM: Average of Subarrays of Size K
     * DIFFICULTY: Easy
     * PATTERN: Fixed Size Window
     * 
     * DESCRIPTION:
     * Given an array, find the average of all contiguous subarrays of size k.
     * 
     * APPROACH:
     * 1. Use sliding window to calculate sums
     * 2. Convert each sum to average
     * 3. Store results in array
     * 
     * TIME COMPLEXITY: O(n)
     * SPACE COMPLEXITY: O(n-k+1) for result
     */
    public static double[] findAverages(int[] nums, int k) {
        if (nums.length < k) return new double[0];
        
        double[] result = new double[nums.length - k + 1];
        double windowSum = 0;
        
        // Calculate sum of first window
        for (int i = 0; i < k; i++) {
            windowSum += nums[i];
        }
        result[0] = windowSum / k;
        
        // Slide window and calculate averages
        for (int i = k; i < nums.length; i++) {
            windowSum = windowSum - nums[i - k] + nums[i];
            result[i - k + 1] = windowSum / k;
        }
        
        return result;
    }
    
    /**
     * PROBLEM: Longest Continuous Increasing Subsequence
     * DIFFICULTY: Easy
     * PATTERN: Variable Size Window
     * 
     * DESCRIPTION:
     * Given an unsorted array of integers nums, return the length of the 
     * longest continuous increasing subsequence.
     * 
     * APPROACH:
     * 1. Use window to track current increasing sequence
     * 2. Expand window while elements are increasing
     * 3. Reset window when sequence breaks
     * 
     * TIME COMPLEXITY: O(n)
     * SPACE COMPLEXITY: O(1)
     */
    public static int findLengthOfLCIS(int[] nums) {
        if (nums.length == 0) return 0;
        
        int maxLength = 1;
        int currentLength = 1;
        
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                currentLength++;
                maxLength = Math.max(maxLength, currentLength);
            } else {
                currentLength = 1;
            }
        }
        
        return maxLength;
    }
    
    /**
     * PROBLEM: Contains Duplicate II
     * DIFFICULTY: Easy
     * PATTERN: Fixed Size Window with Set
     * 
     * DESCRIPTION:
     * Given an integer array nums and an integer k, return true if there are 
     * two distinct indices i and j in the array such that nums[i] == nums[j] 
     * and abs(i - j) <= k.
     * 
     * APPROACH:
     * 1. Maintain a set of at most k+1 elements
     * 2. If duplicate found in set, return true
     * 3. Slide window by removing leftmost element when size exceeds k
     * 
     * TIME COMPLEXITY: O(n)
     * SPACE COMPLEXITY: O(min(n, k))
     */
    public static boolean containsNearbyDuplicate(int[] nums, int k) {
        Set<Integer> window = new HashSet<>();
        
        for (int i = 0; i < nums.length; i++) {
            if (window.contains(nums[i])) {
                return true;
            }
            
            window.add(nums[i]);
            
            // Keep window size at most k
            if (window.size() > k) {
                window.remove(nums[i - k]);
            }
        }
        
        return false;
    }
    
    // ==================================================================================
    //                                MEDIUM PROBLEMS
    // ==================================================================================
    
    /**
     * PROBLEM: Minimum Size Subarray Sum
     * DIFFICULTY: Medium
     * PATTERN: Dynamic Resizing Window
     * 
     * DESCRIPTION:
     * Given an array of positive integers nums and a positive integer target, 
     * return the minimal length of a contiguous subarray of which the sum is 
     * greater than or equal to target.
     * 
     * APPROACH:
     * 1. Use two pointers to maintain window
     * 2. Expand window by moving right pointer
     * 3. Contract window from left while sum >= target
     * 4. Track minimum window size
     * 
     * TIME COMPLEXITY: O(n)
     * SPACE COMPLEXITY: O(1)
     */
    public static int minSubArrayLen(int target, int[] nums) {
        int left = 0, sum = 0, minLength = Integer.MAX_VALUE;
        
        for (int right = 0; right < nums.length; right++) {
            sum += nums[right];
            
            // Contract window while sum >= target
            while (sum >= target) {
                minLength = Math.min(minLength, right - left + 1);
                sum -= nums[left];
                left++;
            }
        }
        
        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }
    
    /**
     * PROBLEM: Longest Substring Without Repeating Characters
     * DIFFICULTY: Medium
     * PATTERN: Variable Size Window with Set
     * 
     * DESCRIPTION:
     * Given a string s, find the length of the longest substring without 
     * repeating characters.
     * 
     * APPROACH:
     * 1. Use set to track characters in current window
     * 2. Expand window by adding right character
     * 3. Contract window from left while duplicate exists
     * 4. Track maximum window size
     * 
     * TIME COMPLEXITY: O(n)
     * SPACE COMPLEXITY: O(min(m, n)) where m is charset size
     */
    public static int lengthOfLongestSubstring(String s) {
        Set<Character> window = new HashSet<>();
        int left = 0, maxLength = 0;
        
        for (int right = 0; right < s.length(); right++) {
            // Contract window while duplicate exists
            while (window.contains(s.charAt(right))) {
                window.remove(s.charAt(left));
                left++;
            }
            
            // Expand window
            window.add(s.charAt(right));
            maxLength = Math.max(maxLength, right - left + 1);
        }
        
        return maxLength;
    }
    
    /**
     * PROBLEM: Maximum Points You Can Obtain from Cards
     * DIFFICULTY: Medium
     * PATTERN: Complement Window (Fixed Size)
     * 
     * DESCRIPTION:
     * There are several cards arranged in a row, and each card has an associated 
     * number of points. You can take cards from the beginning or from the end of 
     * the row. Return the maximum score you can obtain.
     * 
     * APPROACH:
     * 1. Calculate total sum of all cards
     * 2. Find minimum sum of subarray of size (n-k)
     * 3. Maximum points = total sum - minimum subarray sum
     * 
     * TIME COMPLEXITY: O(n)
     * SPACE COMPLEXITY: O(1)
     */
    public static int maxScore(int[] cardPoints, int k) {
        int n = cardPoints.length;
        int totalSum = 0;
        
        // Calculate total sum
        for (int point : cardPoints) {
            totalSum += point;
        }
        
        // If we take all cards
        if (k == n) return totalSum;
        
        // Find minimum sum of subarray of length (n - k)
        int windowSize = n - k;
        int windowSum = 0;
        
        // Calculate first window sum
        for (int i = 0; i < windowSize; i++) {
            windowSum += cardPoints[i];
        }
        
        int minWindowSum = windowSum;
        
        // Slide window to find minimum
        for (int i = windowSize; i < n; i++) {
            windowSum = windowSum - cardPoints[i - windowSize] + cardPoints[i];
            minWindowSum = Math.min(minWindowSum, windowSum);
        }
        
        return totalSum - minWindowSum;
    }
    
    /**
     * PROBLEM: Longest Mountain in Array
     * DIFFICULTY: Medium
     * PATTERN: Peak Detection with Expansion
     * 
     * DESCRIPTION:
     * Given an integer array arr, return the length of the longest mountain.
     * A mountain is defined as having at least 3 elements and strictly 
     * increasing then strictly decreasing.
     * 
     * APPROACH:
     * 1. Find peaks (elements greater than both neighbors)
     * 2. For each peak, expand left and right to find mountain boundaries
     * 3. Track maximum mountain length
     * 
     * TIME COMPLEXITY: O(n)
     * SPACE COMPLEXITY: O(1)
     */
    public static int longestMountain(int[] arr) {
        int n = arr.length;
        if (n < 3) return 0;
        
        int maxLength = 0;
        int i = 1;
        
        while (i < n - 1) {
            // Check if current position is a peak
            if (arr[i - 1] < arr[i] && arr[i] > arr[i + 1]) {
                // Found a peak, expand in both directions
                int left = i - 1;
                int right = i + 1;
                
                // Expand left (uphill)
                while (left > 0 && arr[left - 1] < arr[left]) {
                    left--;
                }
                
                // Expand right (downhill)
                while (right < n - 1 && arr[right] > arr[right + 1]) {
                    right++;
                }
                
                maxLength = Math.max(maxLength, right - left + 1);
                i = right; // Skip to end of current mountain
            } else {
                i++;
            }
        }
        
        return maxLength;
    }
    
    /**
     * PROBLEM: Find All Anagrams in String
     * DIFFICULTY: Medium
     * PATTERN: Fixed Size Window with Frequency Map
     * 
     * DESCRIPTION:
     * Given two strings s and p, return an array of all the start indices of 
     * p's anagrams in s.
     * 
     * APPROACH:
     * 1. Create frequency map of pattern string p
     * 2. Use fixed-size sliding window of length p.length()
     * 3. Compare window frequency with pattern frequency
     * 4. Record indices where frequencies match
     * 
     * TIME COMPLEXITY: O(n)
     * SPACE COMPLEXITY: O(1) - limited by alphabet size
     */
    public static List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        if (s.length() < p.length()) return result;
        
        Map<Character, Integer> pCount = new HashMap<>();
        Map<Character, Integer> windowCount = new HashMap<>();
        
        // Count characters in p
        for (char c : p.toCharArray()) {
            pCount.put(c, pCount.getOrDefault(c, 0) + 1);
        }
        
        int windowSize = p.length();
        
        // Initialize first window
        for (int i = 0; i < windowSize; i++) {
            char c = s.charAt(i);
            windowCount.put(c, windowCount.getOrDefault(c, 0) + 1);
        }
        
        if (windowCount.equals(pCount)) {
            result.add(0);
        }
        
        // Slide window
        for (int i = windowSize; i < s.length(); i++) {
            // Remove leftmost character
            char leftChar = s.charAt(i - windowSize);
            windowCount.put(leftChar, windowCount.get(leftChar) - 1);
            if (windowCount.get(leftChar) == 0) {
                windowCount.remove(leftChar);
            }
            
            // Add rightmost character
            char rightChar = s.charAt(i);
            windowCount.put(rightChar, windowCount.getOrDefault(rightChar, 0) + 1);
            
            // Check if current window is anagram
            if (windowCount.equals(pCount)) {
                result.add(i - windowSize + 1);
            }
        }
        
        return result;
    }
    
    /**
     * PROBLEM: Longest Repeating Character Replacement
     * DIFFICULTY: Medium
     * PATTERN: Variable Window with Character Frequency
     * 
     * DESCRIPTION:
     * You are given a string s and an integer k. You can choose any character 
     * of the string and change it to any other uppercase English character. 
     * You can perform this operation at most k times. Return the length of 
     * the longest substring containing the same letter.
     * 
     * APPROACH:
     * 1. Use frequency map to track character counts in window
     * 2. Track most frequent character in current window
     * 3. If window_size - max_frequency > k, shrink window
     * 4. Track maximum valid window size
     * 
     * TIME COMPLEXITY: O(n)
     * SPACE COMPLEXITY: O(1) - limited by alphabet size
     */
    public static int characterReplacement(String s, int k) {
        Map<Character, Integer> charCount = new HashMap<>();
        int left = 0, maxFreq = 0, maxLength = 0;
        
        for (int right = 0; right < s.length(); right++) {
            char rightChar = s.charAt(right);
            charCount.put(rightChar, charCount.getOrDefault(rightChar, 0) + 1);
            maxFreq = Math.max(maxFreq, charCount.get(rightChar));
            
            // If current window needs more than k replacements, shrink it
            int windowSize = right - left + 1;
            if (windowSize - maxFreq > k) {
                char leftChar = s.charAt(left);
                charCount.put(leftChar, charCount.get(leftChar) - 1);
                left++;
            }
            
            maxLength = Math.max(maxLength, right - left + 1);
        }
        
        return maxLength;
    }
    
    /**
     * PROBLEM: Max Consecutive Ones III
     * DIFFICULTY: Medium
     * PATTERN: Variable Window with Zero Count
     * 
     * DESCRIPTION:
     * Given a binary array nums and an integer k, return the maximum number 
     * of consecutive 1s in the array if you can flip at most k 0s.
     * 
     * APPROACH:
     * 1. Use sliding window to track zeros in current window
     * 2. Expand window by moving right pointer
     * 3. Contract window when zero count exceeds k
     * 4. Track maximum window size
     * 
     * TIME COMPLEXITY: O(n)
     * SPACE COMPLEXITY: O(1)
     */
    public static int longestOnes(int[] nums, int k) {
        int left = 0, zeroCount = 0, maxLength = 0;
        
        for (int right = 0; right < nums.length; right++) {
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
    
    // ==================================================================================
    //                                 HARD PROBLEMS
    // ==================================================================================
    
    /**
     * PROBLEM: Minimum Window Substring
     * DIFFICULTY: Hard
     * PATTERN: Variable Window with Multiple Conditions
     * 
     * DESCRIPTION:
     * Given two strings s and t of lengths m and n respectively, return the 
     * minimum window substring of s such that every character in t is included 
     * in the window.
     * 
     * APPROACH:
     * 1. Use frequency map to track required characters
     * 2. Expand window until all characters of t are included
     * 3. Contract window while maintaining validity
     * 4. Track minimum valid window
     * 
     * TIME COMPLEXITY: O(|s| + |t|)
     * SPACE COMPLEXITY: O(|s| + |t|)
     */
    public static String minWindow(String s, String t) {
        if (s.length() == 0 || t.length() == 0) return "";
        
        Map<Character, Integer> dictT = new HashMap<>();
        for (char c : t.toCharArray()) {
            dictT.put(c, dictT.getOrDefault(c, 0) + 1);
        }
        
        int required = dictT.size();
        int left = 0, right = 0;
        int formed = 0;
        
        Map<Character, Integer> windowCounts = new HashMap<>();
        
        // Result: [window length, left, right]
        int[] ans = {-1, 0, 0};
        
        while (right < s.length()) {
            char character = s.charAt(right);
            windowCounts.put(character, windowCounts.getOrDefault(character, 0) + 1);
            
            if (dictT.containsKey(character) && 
                windowCounts.get(character).intValue() == dictT.get(character).intValue()) {
                formed++;
            }
            
            // Contract window while it's valid
            while (left <= right && formed == required) {
                character = s.charAt(left);
                
                // Update result if this window is smaller
                if (ans[0] == -1 || right - left + 1 < ans[0]) {
                    ans[0] = right - left + 1;
                    ans[1] = left;
                    ans[2] = right;
                }
                
                windowCounts.put(character, windowCounts.get(character) - 1);
                if (dictT.containsKey(character) && 
                    windowCounts.get(character).intValue() < dictT.get(character).intValue()) {
                    formed--;
                }
                left++;
            }
            right++;
        }
        
        return ans[0] == -1 ? "" : s.substring(ans[1], ans[2] + 1);
    }
    
    /**
     * PROBLEM: Maximum Product Subarray
     * DIFFICULTY: Hard
     * PATTERN: Dynamic Window with State Tracking
     * 
     * DESCRIPTION:
     * Given an integer array nums, find a contiguous non-empty subarray within 
     * the array that has the largest product, and return the product.
     * 
     * APPROACH:
     * 1. Track both maximum and minimum products ending at current position
     * 2. Handle negative numbers by swapping max and min
     * 3. Reset window at each position or continue from previous
     * 4. Track global maximum
     * 
     * TIME COMPLEXITY: O(n)
     * SPACE COMPLEXITY: O(1)
     */
    public static int maxProduct(int[] nums) {
        int maxProduct = nums[0];
        int currentMax = nums[0];
        int currentMin = nums[0];
        
        for (int i = 1; i < nums.length; i++) {
            // If current number is negative, swap max and min
            if (nums[i] < 0) {
                int temp = currentMax;
                currentMax = currentMin;
                currentMin = temp;
            }
            
            // Update current max and min
            currentMax = Math.max(nums[i], currentMax * nums[i]);
            currentMin = Math.min(nums[i], currentMin * nums[i]);
            
            maxProduct = Math.max(maxProduct, currentMax);
        }
        
        return maxProduct;
    }
    
    /**
     * PROBLEM: Subarrays with K Different Integers
     * DIFFICULTY: Hard
     * PATTERN: Double Sliding Window
     * 
     * DESCRIPTION:
     * Given an integer array nums and an integer k, return the number of good 
     * subarrays of nums. A good array is an array where the number of different 
     * integers in that array is exactly equal to k.
     * 
     * APPROACH:
     * 1. Use helper function to count subarrays with at most k distinct integers
     * 2. Answer = atmostK(k) - atmostK(k-1)
     * 3. This gives exactly k distinct integers
     * 
     * TIME COMPLEXITY: O(n)
     * SPACE COMPLEXITY: O(k)
     */
    public static int subarraysWithKDistinct(int[] nums, int k) {
        return atMostK(nums, k) - atMostK(nums, k - 1);
    }
    
    private static int atMostK(int[] nums, int k) {
        Map<Integer, Integer> count = new HashMap<>();
        int left = 0, result = 0;
        
        for (int right = 0; right < nums.length; right++) {
            count.put(nums[right], count.getOrDefault(nums[right], 0) + 1);
            
            while (count.size() > k) {
                count.put(nums[left], count.get(nums[left]) - 1);
                if (count.get(nums[left]) == 0) {
                    count.remove(nums[left]);
                }
                left++;
            }
            
            result += right - left + 1;
        }
        
        return result;
    }
    
    // ==================================================================================
    //                               HELPER METHODS
    // ==================================================================================
    
    /**
     * Helper method to print array for debugging
     */
    private static void printArray(int[] arr) {
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) System.out.print(", ");
        }
        System.out.println("]");
    }
    
    /**
     * Helper method to print double array for debugging
     */
    private static void printDoubleArray(double[] arr) {
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("%.2f", arr[i]);
            if (i < arr.length - 1) System.out.print(", ");
        }
        System.out.println("]");
    }
} 