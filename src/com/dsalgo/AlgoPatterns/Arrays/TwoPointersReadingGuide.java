package com.dsalgo.AlgoPatterns.Arrays;

/**
 * ==================================================================================
 *                           TWO POINTERS PATTERN - READING GUIDE
 * ==================================================================================
 * 
 * OVERVIEW:
 * The Two Pointers technique is a powerful algorithmic pattern used to solve array 
 * and string problems efficiently. It typically reduces time complexity from O(n²) 
 * to O(n) by using two pointers that traverse the data structure strategically.
 * 
 * ==================================================================================
 *                              WHEN TO USE TWO POINTERS
 * ==================================================================================
 * 
 * 🎯 PATTERN RECOGNITION:
 * ✅ Sorted array problems - leverage the sorted property
 * ✅ Finding pairs/triplets that meet certain conditions
 * ✅ Palindrome problems - check from both ends
 * ✅ Removing duplicates in-place
 * ✅ Merging sorted arrays
 * ✅ Sliding window variations
 * ✅ LinkedList problems (fast/slow pointers)
 * 
 * 🚨 RED FLAGS (when NOT to use):
 * ❌ Unsorted data where order matters
 * ❌ Need to access random elements frequently
 * ❌ Complex nested conditions that don't follow linear patterns
 * 
 * ==================================================================================
 *                           TWO POINTER VARIATIONS
 * ==================================================================================
 */
public class TwoPointersReadingGuide {

    /**
     * ================================================================================
     *                        VARIATION 1: OPPOSITE DIRECTION (CONVERGING)
     * ================================================================================
     * 
     * PATTERN:
     * int left = 0, right = array.length - 1;
     * while (left < right) {
     *     // Process elements at left and right
     *     // Move pointers based on condition
     *     if (condition) left++;
     *     else right--;
     * }
     * 
     * USE CASES:
     * - Two Sum in sorted array
     * - Valid Palindrome
     * - Container With Most Water
     * - Three Sum
     * - Trapping Rain Water
     */
    
    // EXAMPLE 1: Two Sum in Sorted Array
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
        return new int[]{-1, -1}; // Not found
    }
    
    // EXAMPLE 2: Valid Palindrome
    public static boolean isPalindrome(String s) {
        int left = 0, right = s.length() - 1;
        
        while (left < right) {
            // Skip non-alphanumeric characters
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
                left++;
            }
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
    
    // EXAMPLE 3: Container With Most Water
    public static int maxArea(int[] height) {
        int left = 0, right = height.length - 1;
        int maxWater = 0;
        
        while (left < right) {
            // Calculate current area
            int width = right - left;
            int currentHeight = Math.min(height[left], height[right]);
            maxWater = Math.max(maxWater, width * currentHeight);
            
            // Move pointer with smaller height
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return maxWater;
    }

    /**
     * ================================================================================
     *                        VARIATION 2: SAME DIRECTION (FAST & SLOW)
     * ================================================================================
     * 
     * PATTERN:
     * int slow = 0, fast = 0;
     * while (fast < array.length) {
     *     // Fast pointer explores
     *     // Slow pointer tracks valid position
     *     if (condition) {
     *         array[slow++] = array[fast];
     *     }
     *     fast++;
     * }
     * 
     * USE CASES:
     * - Remove duplicates from sorted array
     * - Move zeros to end
     * - LinkedList cycle detection
     * - Find middle of LinkedList
     */
    
    // EXAMPLE 1: Remove Duplicates from Sorted Array
    public static int removeDuplicates(int[] nums) {
        if (nums.length <= 1) return nums.length;
        
        int slow = 1; // Points to position for next unique element
        
        for (int fast = 1; fast < nums.length; fast++) {
            if (nums[fast] != nums[fast - 1]) {
                nums[slow] = nums[fast];
                slow++;
            }
        }
        return slow; // Length of array with unique elements
    }
    
    // EXAMPLE 2: Move Zeros to End
    public static void moveZeroes(int[] nums) {
        int slow = 0; // Points to position for next non-zero element
        
        // Move all non-zero elements to the front
        for (int fast = 0; fast < nums.length; fast++) {
            if (nums[fast] != 0) {
                nums[slow] = nums[fast];
                slow++;
            }
        }
        
        // Fill remaining positions with zeros
        while (slow < nums.length) {
            nums[slow] = 0;
            slow++;
        }
    }
    
    // EXAMPLE 3: LinkedList Cycle Detection (Floyd's Algorithm)
    public static boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) return false;
        
        ListNode slow = head;
        ListNode fast = head;
        
        while (fast != null && fast.next != null) {
            slow = slow.next;        // Move 1 step
            fast = fast.next.next;   // Move 2 steps
            
            if (slow == fast) {
                return true; // Cycle detected
            }
        }
        return false;
    }

    /**
     * ================================================================================
     *                        VARIATION 3: SLIDING WINDOW (VARIABLE SIZE)
     * ================================================================================
     * 
     * PATTERN:
     * int left = 0, right = 0;
     * while (right < array.length) {
     *     // Expand window by including right element
     *     // Shrink window from left while invalid
     *     while (windowInvalid) {
     *         left++;
     *     }
     *     // Update result
     *     right++;
     * }
     * 
     * USE CASES:
     * - Longest substring without repeating characters
     * - Minimum window substring
     * - Longest repeating character replacement
     * - Subarrays with sum equals K
     */
    
    // EXAMPLE 1: Longest Substring Without Repeating Characters
    public static int lengthOfLongestSubstring(String s) {
        java.util.HashMap<Character, Integer> charMap = new java.util.HashMap<>();
        int left = 0, maxLength = 0;
        
        for (int right = 0; right < s.length(); right++) {
            char currentChar = s.charAt(right);
            
            // If character is already in current window, move left pointer
            if (charMap.containsKey(currentChar)) {
                left = Math.max(left, charMap.get(currentChar) + 1);
            }
            
            charMap.put(currentChar, right);
            maxLength = Math.max(maxLength, right - left + 1);
        }
        return maxLength;
    }
    
    // EXAMPLE 2: Longest Repeating Character Replacement
    public static int characterReplacement(String s, int k) {
        java.util.HashMap<Character, Integer> charCount = new java.util.HashMap<>();
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
     * ================================================================================
     *                           ADVANCED PATTERNS & TRICKS
     * ================================================================================
     */
    
    // THREE SUM PROBLEM (Combination of sorting + two pointers)
    public static java.util.List<java.util.List<Integer>> threeSum(int[] nums) {
        java.util.List<java.util.List<Integer>> result = new java.util.ArrayList<>();
        java.util.Arrays.sort(nums); // Sort first!
        
        for (int i = 0; i < nums.length - 2; i++) {
            // Skip duplicates for first number
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            
            int left = i + 1, right = nums.length - 1;
            int target = -nums[i];
            
            while (left < right) {
                int sum = nums[left] + nums[right];
                if (sum == target) {
                    result.add(java.util.Arrays.asList(nums[i], nums[left], nums[right]));
                    
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
     * ================================================================================
     *                              COMMON PITFALLS & TIPS
     * ================================================================================
     * 
     * 🚨 COMMON MISTAKES:
     * 1. INFINITE LOOPS: Always ensure pointers move in each iteration
     * 2. BOUNDARY CONDITIONS: Check `left < right` vs `left <= right` carefully
     * 3. OFF-BY-ONE ERRORS: Be careful with array indices and loop conditions
     * 4. SORTED ARRAY ASSUMPTION: Many solutions only work on sorted data
     * 5. DUPLICATE HANDLING: Remember to skip duplicates in sum problems
     * 
     * 💡 PRO TIPS:
     * 1. Draw out small examples to visualize pointer movement
     * 2. Consider edge cases: empty arrays, single elements, all same elements
     * 3. Use meaningful variable names: left/right, slow/fast, start/end
     * 4. Think about what each pointer represents and when to move them
     * 5. For string problems, consider case sensitivity and special characters
     * 
     * ================================================================================
     *                                PRACTICE PROBLEMS
     * ================================================================================
     * 
     * 🟢 EASY:
     * - Valid Palindrome
     * - Remove Duplicates from Sorted Array
     * - Merge Sorted Array
     * - Intersection of Two Arrays II
     * - Squares of a Sorted Array
     * 
     * 🟡 MEDIUM:
     * - Two Sum II (Input array is sorted)
     * - 3Sum
     * - Container With Most Water
     * - Longest Substring Without Repeating Characters
     * - Find the Duplicate Number
     * - Sort Colors (Dutch National Flag)
     * - Subarray Product Less Than K
     * 
     * 🔴 HARD:
     * - Trapping Rain Water
     * - Minimum Window Substring
     * - Sliding Window Maximum
     * - 4Sum
     * - Longest Substring with At Most K Distinct Characters
     * 
     * ================================================================================
     *                              DECISION FRAMEWORK
     * ================================================================================
     * 
     * ASK YOURSELF:
     * 1. Is the array/string sorted? → Consider opposite direction pointers
     * 2. Need to find pairs/triplets with specific sum? → Two pointers with target
     * 3. In-place modification needed? → Fast/slow pointer approach
     * 4. Substring/subarray problem? → Sliding window variation
     * 5. Palindrome or symmetry check? → Converging pointers from ends
     * 6. LinkedList problem? → Fast/slow pointers (Floyd's algorithm)
     * 
     * TIME COMPLEXITY: Usually O(n) instead of O(n²)
     * SPACE COMPLEXITY: Usually O(1) - in-place operations
     * 
     * Remember: Two pointers is about reducing nested loops by using the 
     * structure/properties of the data to move pointers intelligently!
     */
    
    // Helper class for LinkedList examples
    static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
} 