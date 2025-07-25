package com.dsalgo.AlgoPatterns.Arrays;

/**
 * ==================================================================================
 *                        SLIDING WINDOW PATTERN - READING GUIDE
 * ==================================================================================
 * 
 * OVERVIEW:
 * The Sliding Window pattern is used to perform operations on a specific window size
 * of an array or string. It involves creating a window of elements and sliding it
 * across the data structure to find optimal solutions. This pattern reduces time
 * complexity from O(n²) or O(n³) to O(n) for many problems.
 * 
 * ==================================================================================
 *                            WHEN TO USE SLIDING WINDOW
 * ==================================================================================
 * 
 * 🎯 PATTERN RECOGNITION:
 * ✅ Problems involving contiguous subarrays or substrings
 * ✅ Need to maintain a window of elements
 * ✅ Finding maximum/minimum/optimal value in subarrays
 * ✅ String pattern matching problems
 * ✅ Problems mentioning "window", "subarray", "substring"
 * ✅ Conditions like "maximum sum", "minimum length", "contains all"
 * ✅ Need to track subset of elements satisfying conditions
 * ✅ Running calculations on fixed or variable window sizes
 * 
 * 🚨 RED FLAGS (when NOT to use):
 * ❌ Need to access non-contiguous elements
 * ❌ Global optimization across entire array
 * ❌ Problems requiring backtracking or recursion
 * ❌ Need to maintain sorted order of elements
 * 
 * ==================================================================================
 *                            SLIDING WINDOW VARIATIONS
 * ==================================================================================
 */
public class SlidingWindowReadingGuide {

    /**
     * ================================================================================
     *                         VARIATION 1: FIXED SIZE WINDOW
     * ================================================================================
     * 
     * PATTERN:
     * 1. Create window of fixed size k
     * 2. Calculate result for first window
     * 3. Slide window: remove left element, add right element
     * 4. Update result as window slides
     * 
     * USE CASES:
     * - Maximum sum of k consecutive elements
     * - Average of subarrays of size k
     * - Maximum/minimum in sliding window
     * - Anagram detection in fixed windows
     */
    
    // EXAMPLE: Maximum Sum of Subarray of Size K
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
     * ================================================================================
     *                        VARIATION 2: VARIABLE SIZE WINDOW
     * ================================================================================
     * 
     * PATTERN:
     * 1. Use two pointers (left and right) to define window
     * 2. Expand window by moving right pointer
     * 3. Contract window by moving left pointer when condition violated
     * 4. Track optimal result throughout the process
     * 
     * USE CASES:
     * - Longest substring without repeating characters
     * - Minimum window substring
     * - Longest subarray with sum ≤ target
     * - Subarray with exactly k distinct elements
     */
    
    // EXAMPLE: Longest Substring Without Repeating Characters
    public static int lengthOfLongestSubstring(String s) {
        java.util.Set<Character> window = new java.util.HashSet<>();
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
     * ================================================================================
     *                        VARIATION 3: DYNAMIC RESIZING WINDOW
     * ================================================================================
     * 
     * PATTERN:
     * 1. Window size changes based on conditions
     * 2. May need to track multiple conditions simultaneously
     * 3. Often involves frequency counting or sum tracking
     * 4. Both expansion and contraction based on dynamic criteria
     * 
     * USE CASES:
     * - Minimum size subarray with sum ≥ target
     * - Longest subarray with at most k distinct characters
     * - Maximum points from cards (choosing from ends)
     * - Subarray product less than k
     */
    
    // EXAMPLE: Minimum Size Subarray Sum
    public static int minSubArrayLen(int target, int[] nums) {
        int left = 0, sum = 0, minLength = Integer.MAX_VALUE;
        
        for (int right = 0; right < nums.length; right++) {
            sum += nums[right];
            
            // Contract window while condition is satisfied
            while (sum >= target) {
                minLength = Math.min(minLength, right - left + 1);
                sum -= nums[left];
                left++;
            }
        }
        
        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }

    /**
     * ================================================================================
     *                         VARIATION 4: STRING PATTERN MATCHING
     * ================================================================================
     * 
     * PATTERN:
     * 1. Use frequency maps to track character counts
     * 2. Expand window until pattern requirements met
     * 3. Contract window while maintaining validity
     * 4. Track all valid windows or find optimal one
     * 
     * USE CASES:
     * - Find all anagrams in string
     * - Minimum window containing all characters
     * - Permutation in string
     * - Character replacement problems
     */
    
    // EXAMPLE: Find All Anagrams in String
    public static java.util.List<Integer> findAnagrams(String s, String p) {
        java.util.List<Integer> result = new java.util.ArrayList<>();
        if (s.length() < p.length()) return result;
        
        java.util.Map<Character, Integer> pCount = new java.util.HashMap<>();
        java.util.Map<Character, Integer> windowCount = new java.util.HashMap<>();
        
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
     * ================================================================================
     *                      VARIATION 5: MULTIPLE WINDOWS / ENDPOINTS
     * ================================================================================
     * 
     * PATTERN:
     * 1. Consider elements from both ends of array
     * 2. Decide optimal combination of left and right selections
     * 3. May involve prefix/suffix sums
     * 4. Often optimization problems with constraints
     * 
     * USE CASES:
     * - Maximum points from cards (select from ends)
     * - Optimal strategy games
     * - Range sum queries with selection constraints
     * - Binary search on answer with sliding window validation
     */
    
    // EXAMPLE: Maximum Points from Cards
    public static int maxScore(int[] cardPoints, int k) {
        int n = cardPoints.length;
        int totalSum = 0;
        
        // Calculate total sum
        for (int point : cardPoints) {
            totalSum += point;
        }
        
        // If we need to take all cards
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
     * ================================================================================
     *                           ADVANCED SLIDING WINDOW PATTERNS
     * ================================================================================
     */
    
    // LONGEST MOUNTAIN IN ARRAY - Complex state tracking
    public static int longestMountain(int[] arr) {
        int n = arr.length;
        if (n < 3) return 0;
        
        int maxLength = 0;
        int i = 1;
        
        while (i < n - 1) {
            // Check if current position can be peak
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
    
    // MAXIMUM PRODUCT SUBARRAY - Dynamic window with state
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
     * ================================================================================
     *                             COMMON PITFALLS & TIPS
     * ================================================================================
     * 
     * 🚨 COMMON MISTAKES:
     * 1. BOUNDARY ERRORS: Off-by-one errors in window boundaries
     * 2. INITIALIZATION: Incorrect initial window setup
     * 3. STATE MANAGEMENT: Not properly updating window state
     * 4. EDGE CASES: Empty arrays, single elements, window size larger than array
     * 5. CONDITION LOGIC: Incorrect expansion/contraction conditions
     * 6. FREQUENCY TRACKING: Not handling character/element counts correctly
     * 
     * 💡 PRO TIPS:
     * 1. Always validate window size against array length
     * 2. Use clear variable names: left, right, windowStart, windowEnd
     * 3. Draw out examples to visualize window movement
     * 4. Consider edge cases: empty input, single element, k=0, k=n
     * 5. For string problems, consider ASCII vs Unicode character sets
     * 6. Use appropriate data structures: Map for frequency, Set for uniqueness
     * 7. Sometimes converting to different problem helps (complement approach)
     * 
     * ================================================================================
     *                               PRACTICE PROBLEMS
     * ================================================================================
     * 
     * 🟢 EASY:
     * - Maximum Sum Subarray of Size K
     * - Average of Subarrays of Size K
     * - Maximum Difference Between Increasing Elements
     * - Contains Duplicate II
     * - Longest Continuous Increasing Subsequence
     * 
     * 🟡 MEDIUM:
     * - Minimum Size Subarray Sum
     * - Longest Substring Without Repeating Characters
     * - Maximum Points You Can Obtain from Cards
     * - Longest Mountain in Array
     * - Find All Anagrams in String
     * - Permutation in String
     * - Longest Repeating Character Replacement
     * - Max Consecutive Ones III
     * - Fruit Into Baskets
     * - Subarrays with K Different Integers
     * 
     * 🔴 HARD:
     * - Minimum Window Substring
     * - Sliding Window Maximum
     * - Longest Substring with At Most K Distinct Characters
     * - Maximum Product Subarray
     * - Constrained Subsequence Sum
     * 
     * ================================================================================
     *                             DECISION FRAMEWORK
     * ================================================================================
     * 
     * ASK YOURSELF:
     * 1. Do I need contiguous elements? → Consider sliding window
     * 2. Is there a fixed window size? → Fixed size sliding window
     * 3. Need to find optimal subarray/substring? → Variable size window
     * 4. Involves character/element frequency? → Use frequency maps
     * 5. Multiple conditions to track? → Dynamic resizing window
     * 6. Selection from both ends? → Complement or dual-end approach
     * 7. Need all valid windows vs optimal one? → Affects implementation strategy
     * 
     * COMPLEXITY ANALYSIS:
     * - Time: Usually O(n) where n is array/string length
     * - Space: O(1) for simple cases, O(k) for frequency tracking
     * 
     * WINDOW EXPANSION/CONTRACTION RULES:
     * - Expand: Add new element, update state
     * - Contract: Remove old element, update state
     * - Always check validity after state changes
     * 
     * Remember: Sliding window transforms nested loop problems into linear time
     * solutions by maintaining running state and avoiding redundant calculations!
     */
} 