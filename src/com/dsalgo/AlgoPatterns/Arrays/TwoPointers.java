package com.dsalgo.AlgoPatterns.Arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * Two Pointers is a pattern that uses two pointers to traverse an array or string.
 * 
 * It is used to solve problems where we need to find a pair of elements in an array or string.
 * It is a very efficient way to solve problems that require us to find a pair of elements 
 * in an array or string.
 * 
 * Look for problems where you need to iterate through the array with two pointers, 
 * typically starting from different ends or positions within the array. 
 * 
 * Consider tasks that involve comparing or manipulating elements from two different parts 
 * of the array simultaneously.
 * Look for problem descriptions mentioning a sorted array or the need to compare elements
 * from both ends of the array.
 * 
 * Example:
 * Input: nums = [1, 2, 3, 4, 5], target = 9
 * Output: [3, 4] (indices where nums[3] + nums[4] = 4 + 5 = 9)
 * Explanation: The sum of elements at indices 3 and 4 equals the target.
 */
public class TwoPointers {

    public static void main(String[] args) {
        // Test Two Pointers problems
        System.out.println("=== TWO POINTERS PROBLEMS TEST ===");
        
        // Test Two Sum
        int[] nums = {2, 7, 11, 15};
        int[] result = twoSumSorted(nums, 9);
        System.out.println("Two Sum Result: " + Arrays.toString(result));
        
        // Test Valid Palindrome
        System.out.println("Is Palindrome: " + isPalindrome("A man, a plan, a canal: Panama"));
        
        // Test Container With Most Water
        int[] heights = {1,8,6,2,5,4,8,3,7};
        System.out.println("Max Area: " + maxArea(heights));
        
        // Test Remove Duplicates
        int[] duplicates = {1,1,2,2,3,3,4};
        int newLength = removeDuplicates(duplicates);
        System.out.println("Array after removing duplicates: " + Arrays.toString(Arrays.copyOf(duplicates, newLength)));
    }

    // ==================================================================================
    //                            TWO POINTERS PRACTICE PROBLEMS
    // ==================================================================================
    
    // ==================================================================================
    //                                  EASY PROBLEMS
    // ==================================================================================
    
    /**
     * PROBLEM: Valid Palindrome
     * DIFFICULTY: Easy
     * PATTERN: Two Pointers (Opposite Direction)
     * 
     * DESCRIPTION:
     * A phrase is a palindrome if, after converting all uppercase letters into 
     * lowercase letters and removing all non-alphanumeric characters, it reads 
     * the same forward and backward.
     * 
     * APPROACH:
     * - Use two pointers: left starting from beginning, right from end
     * - Skip non-alphanumeric characters
     * - Compare characters in case-insensitive manner
     * - Move pointers toward center
     * 
     * TIME COMPLEXITY: O(n)
     * SPACE COMPLEXITY: O(1)
     */
    public static boolean isPalindrome(String s) {
        int left = 0, right = s.length() - 1;
        
        while (left < right) {
            // Skip non-alphanumeric characters from left
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
                left++;
            }
            // Skip non-alphanumeric characters from right
            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
                right--;
            }
            
            // Compare characters (case-insensitive)
            if (Character.toLowerCase(s.charAt(left)) != 
                Character.toLowerCase(s.charAt(right))) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
    
    /**
     * PROBLEM: Remove Duplicates from Sorted Array
     * DIFFICULTY: Easy
     * PATTERN: Two Pointers (Same Direction - Fast/Slow)
     * 
     * DESCRIPTION:
     * Given an integer array nums sorted in non-decreasing order, remove the 
     * duplicates in-place such that each unique element appears only once.
     * 
     * APPROACH:
     * - Use slow pointer to track position for next unique element
     * - Use fast pointer to explore the array
     * - When fast finds a new unique element, place it at slow position
     * 
     * TIME COMPLEXITY: O(n)
     * SPACE COMPLEXITY: O(1)
     */
    public static int removeDuplicates(int[] nums) {
        if (nums.length <= 1) return nums.length;
        
        int slow = 1; // Points to position for next unique element
        
        for (int fast = 1; fast < nums.length; fast++) {
            // If current element is different from previous, it's unique
            if (nums[fast] != nums[fast - 1]) {
                nums[slow] = nums[fast];
                slow++;
            }
        }
        return slow; // Length of array with unique elements
    }
    
    /**
     * PROBLEM: Merge Sorted Array
     * DIFFICULTY: Easy
     * PATTERN: Two Pointers (Opposite Direction)
     * 
     * DESCRIPTION:
     * You are given two integer arrays nums1 and nums2, sorted in non-decreasing 
     * order, and two integers m and n, representing the number of elements in 
     * nums1 and nums2 respectively. Merge nums2 into nums1 as one sorted array.
     * 
     * APPROACH:
     * - Start from the end of both arrays (to avoid overwriting)
     * - Compare elements and place the larger one at the end of nums1
     * - Move pointers backward
     * 
     * TIME COMPLEXITY: O(m + n)
     * SPACE COMPLEXITY: O(1)
     */
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1;      // Last element in nums1
        int j = n - 1;      // Last element in nums2
        int k = m + n - 1;  // Last position in merged array
        
        // Merge from the end
        while (i >= 0 && j >= 0) {
            if (nums1[i] > nums2[j]) {
                nums1[k] = nums1[i];
                i--;
            } else {
                nums1[k] = nums2[j];
                j--;
            }
            k--;
        }
        
        // Copy remaining elements from nums2
        while (j >= 0) {
            nums1[k] = nums2[j];
            j--;
            k--;
        }
    }
    
    /**
     * PROBLEM: Squares of a Sorted Array
     * DIFFICULTY: Easy
     * PATTERN: Two Pointers (Opposite Direction)
     * 
     * DESCRIPTION:
     * Given an integer array nums sorted in non-decreasing order, return an 
     * array of the squares of each number sorted in non-decreasing order.
     * 
     * APPROACH:
     * - Since array can have negative numbers, squares might not be sorted
     * - Use two pointers from both ends
     * - Compare absolute values and place larger square at end of result
     * 
     * TIME COMPLEXITY: O(n)
     * SPACE COMPLEXITY: O(n) for result array
     */
    public static int[] sortedSquares(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        int left = 0, right = n - 1;
        int index = n - 1; // Fill result from end
        
        while (left <= right) {
            int leftSquare = nums[left] * nums[left];
            int rightSquare = nums[right] * nums[right];
            
            if (leftSquare > rightSquare) {
                result[index] = leftSquare;
                left++;
            } else {
                result[index] = rightSquare;
                right--;
            }
            index--;
        }
        return result;
    }
    
    // ==================================================================================
    //                                MEDIUM PROBLEMS
    // ==================================================================================
    
    /**
     * PROBLEM: Two Sum II - Input Array Is Sorted
     * DIFFICULTY: Medium
     * PATTERN: Two Pointers (Opposite Direction)
     * 
     * DESCRIPTION:
     * Given a 1-indexed array of integers numbers that is already sorted in 
     * non-decreasing order, find two numbers such that they add up to a specific 
     * target number.
     * 
     * APPROACH:
     * - Use two pointers: left at start, right at end
     * - If sum equals target, return indices
     * - If sum is less than target, move left pointer right
     * - If sum is greater than target, move right pointer left
     * 
     * TIME COMPLEXITY: O(n)
     * SPACE COMPLEXITY: O(1)
     */
    public static int[] twoSumSorted(int[] numbers, int target) {
        int left = 0, right = numbers.length - 1;
        
        while (left < right) {
            int sum = numbers[left] + numbers[right];
            if (sum == target) {
                return new int[]{left + 1, right + 1}; // 1-indexed
            } else if (sum < target) {
                left++;  // Need larger sum
            } else {
                right--; // Need smaller sum
            }
        }
        return new int[]{-1, -1}; // Not found (should not happen per problem statement)
    }
    
    /**
     * PROBLEM: 3Sum
     * DIFFICULTY: Medium
     * PATTERN: Two Pointers + Sorting
     * 
     * DESCRIPTION:
     * Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] 
     * such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.
     * 
     * APPROACH:
     * - Sort the array first
     * - For each element, use two pointers to find pairs that sum to -element
     * - Skip duplicates to avoid duplicate triplets
     * 
     * TIME COMPLEXITY: O(n²)
     * SPACE COMPLEXITY: O(1) excluding output
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums); // Sort first!
        
        for (int i = 0; i < nums.length - 2; i++) {
            // Skip duplicates for first number
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            
            int left = i + 1, right = nums.length - 1;
            int target = -nums[i];
            
            while (left < right) {
                int sum = nums[left] + nums[right];
                if (sum == target) {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    
                    // Skip duplicates
                    while (left < right && nums[left] == nums[left + 1]) left++;
                    while (left < right && nums[right] == nums[right - 1]) right--;
                    
                    left++;
                    right--;
                } else if (sum < target) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return result;
    }
    
    /**
     * PROBLEM: Container With Most Water
     * DIFFICULTY: Medium
     * PATTERN: Two Pointers (Opposite Direction)
     * 
     * DESCRIPTION:
     * You are given an integer array height of length n. There are n vertical 
     * lines drawn such that the two endpoints of the ith line are (i, 0) and 
     * (i, height[i]). Find two lines that together with the x-axis form a 
     * container that can hold the most water.
     * 
     * APPROACH:
     * - Use two pointers from both ends
     * - Calculate area = min(height[left], height[right]) * (right - left)
     * - Move the pointer with smaller height (potential for larger area)
     * 
     * TIME COMPLEXITY: O(n)
     * SPACE COMPLEXITY: O(1)
     */
    public static int maxArea(int[] height) {
        int left = 0, right = height.length - 1;
        int maxWater = 0;
        
        while (left < right) {
            // Calculate current area
            int width = right - left;
            int currentHeight = Math.min(height[left], height[right]);
            maxWater = Math.max(maxWater, width * currentHeight);
            
            // Move pointer with smaller height (potential for improvement)
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return maxWater;
    }
    
    /**
     * PROBLEM: Sort Colors (Dutch National Flag)
     * DIFFICULTY: Medium
     * PATTERN: Three Pointers
     * 
     * DESCRIPTION:
     * Given an array nums with n objects colored red, white, or blue, sort them 
     * in-place so that objects of the same color are adjacent, with the colors 
     * in the order red, white, and blue (represented by integers 0, 1, and 2).
     * 
     * APPROACH:
     * - Use three pointers: low (for 0s), mid (current), high (for 2s)
     * - Partition array into three sections
     * - Move appropriate elements to their correct sections
     * 
     * TIME COMPLEXITY: O(n)
     * SPACE COMPLEXITY: O(1)
     */
    public static void sortColors(int[] nums) {
        int low = 0, mid = 0, high = nums.length - 1;
        
        while (mid <= high) {
            if (nums[mid] == 0) {
                // Swap with low section
                swap(nums, low, mid);
                low++;
                mid++;
            } else if (nums[mid] == 1) {
                // Already in correct position
                mid++;
            } else { // nums[mid] == 2
                // Swap with high section
                swap(nums, mid, high);
                high--;
                // Don't increment mid (need to check swapped element)
            }
        }
    }
    
    /**
     * PROBLEM: Subarray Product Less Than K
     * DIFFICULTY: Medium
     * PATTERN: Sliding Window (Two Pointers)
     * 
     * DESCRIPTION:
     * Given an array of integers nums and an integer k, return the number of 
     * contiguous subarrays where the product of all the elements in the 
     * subarray is strictly less than k.
     * 
     * APPROACH:
     * - Use sliding window with left and right pointers
     * - Expand window by moving right pointer
     * - Shrink window when product >= k by moving left pointer
     * - Count valid subarrays ending at each position
     * 
     * TIME COMPLEXITY: O(n)
     * SPACE COMPLEXITY: O(1)
     */
    public static int numSubarrayProductLessThanK(int[] nums, int k) {
        if (k <= 1) return 0; // No positive products can be less than 1
        
        int left = 0, count = 0;
        int product = 1;
        
        for (int right = 0; right < nums.length; right++) {
            product *= nums[right];
            
            // Shrink window while product >= k
            while (product >= k) {
                product /= nums[left];
                left++;
            }
            
            // All subarrays ending at 'right' with start >= 'left' are valid
            count += right - left + 1;
        }
        return count;
    }
    
    // ==================================================================================
    //                                 HARD PROBLEMS
    // ==================================================================================
    
    /**
     * PROBLEM: Trapping Rain Water
     * DIFFICULTY: Hard
     * PATTERN: Two Pointers (Opposite Direction)
     * 
     * DESCRIPTION:
     * Given n non-negative integers representing an elevation map where the 
     * width of each bar is 1, compute how much water it can trap after raining.
     * 
     * APPROACH:
     * - Use two pointers and track max heights from both sides
     * - Water level at any point is determined by minimum of max heights
     * - Move pointer with smaller max height
     * 
     * TIME COMPLEXITY: O(n)
     * SPACE COMPLEXITY: O(1)
     */
    public static int trap(int[] height) {
        if (height.length == 0) return 0;
        
        int left = 0, right = height.length - 1;
        int leftMax = 0, rightMax = 0;
        int water = 0;
        
        while (left < right) {
            if (height[left] < height[right]) {
                if (height[left] >= leftMax) {
                    leftMax = height[left];
                } else {
                    water += leftMax - height[left];
                }
                left++;
            } else {
                if (height[right] >= rightMax) {
                    rightMax = height[right];
                } else {
                    water += rightMax - height[right];
                }
                right--;
            }
        }
        return water;
    }
    
    /**
     * PROBLEM: Minimum Window Substring
     * DIFFICULTY: Hard
     * PATTERN: Sliding Window (Two Pointers)
     * 
     * DESCRIPTION:
     * Given two strings s and t of lengths m and n respectively, return the 
     * minimum window substring of s such that every character in t (including 
     * duplicates) is included in the window.
     * 
     * APPROACH:
     * - Use sliding window with character frequency maps
     * - Expand window until all characters of t are included
     * - Contract window while maintaining validity
     * - Track minimum valid window
     * 
     * TIME COMPLEXITY: O(|s| + |t|)
     * SPACE COMPLEXITY: O(|s| + |t|)
     */
    public static String minWindow(String s, String t) {
        if (s.length() == 0 || t.length() == 0) return "";
        
        // Character frequencies in t
        Map<Character, Integer> dictT = new HashMap<>();
        for (char c : t.toCharArray()) {
            dictT.put(c, dictT.getOrDefault(c, 0) + 1);
        }
        
        int required = dictT.size(); // Number of unique chars in t
        int left = 0, right = 0;
        int formed = 0; // Number of unique chars in current window with desired frequency
        
        Map<Character, Integer> windowCounts = new HashMap<>();
        
        // Result: [window length, left, right]
        int[] ans = {-1, 0, 0};
        
        while (right < s.length()) {
            // Add character from right to window
            char character = s.charAt(right);
            windowCounts.put(character, windowCounts.getOrDefault(character, 0) + 1);
            
            // Check if frequency matches requirement
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
                
                // Remove character from left
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
     * PROBLEM: 4Sum
     * DIFFICULTY: Hard
     * PATTERN: Two Pointers + Nested Loops
     * 
     * DESCRIPTION:
     * Given an array nums of n integers, return an array of all the unique 
     * quadruplets [nums[a], nums[b], nums[c], nums[d]] such that:
     * a, b, c, and d are distinct and nums[a] + nums[b] + nums[c] + nums[d] == target.
     * 
     * APPROACH:
     * - Extension of 3Sum with additional outer loop
     * - Sort array and use two nested loops + two pointers
     * - Skip duplicates at all levels
     * 
     * TIME COMPLEXITY: O(n³)
     * SPACE COMPLEXITY: O(1) excluding output
     */
    public static List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums.length < 4) return result;
        
        Arrays.sort(nums);
        
        for (int i = 0; i < nums.length - 3; i++) {
            // Skip duplicates for first number
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            
            for (int j = i + 1; j < nums.length - 2; j++) {
                // Skip duplicates for second number
                if (j > i + 1 && nums[j] == nums[j - 1]) continue;
                
                int left = j + 1, right = nums.length - 1;
                long targetSum = (long) target - nums[i] - nums[j];
                
                while (left < right) {
                    int sum = nums[left] + nums[right];
                    if (sum == targetSum) {
                        result.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        
                        // Skip duplicates
                        while (left < right && nums[left] == nums[left + 1]) left++;
                        while (left < right && nums[right] == nums[right - 1]) right--;
                        
                        left++;
                        right--;
                    } else if (sum < targetSum) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }
        return result;
    }
    
    // ==================================================================================
    //                               HELPER METHODS
    // ==================================================================================
    
    /**
     * Helper method to swap two elements in an array
     */
    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
