package com.dsalgo.AlgoPatterns.Arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * Prefix Sums (Cumulative Sums) is a preprocessing technique that allows for 
 * efficient computation of range sums, subarray sums, and related queries.
 * 
 * Consider problems where you need to compute cumulative sums or averages of 
 * elements in subarray or answer queries about subarray sums efficiently.
 * 
 * Look for tasks where precomputing sums or other aggregate values can help 
 * reduce the time complexity of operations involving subarrays.
 * 
 * Look for problems mentioning subarray sums, cumulative sums, or range sums, 
 * and hints that precomputing sums might optimize the solution.
 * 
 * Example:
 * Input: nums = [-2, 0, 3, -5, 2, -1], queries = [[0,2],[2,5],[0,5]]
 * Output: [1, -1, -3]
 * Explanation: Range sums using prefix sums: [1, -1, -3]
 */
public class PrefixSums {

    public static void main(String[] args) {
        // Test Prefix Sums problems
        System.out.println("=== PREFIX SUMS PROBLEMS TEST ===");
        
        // Test Range Sum Query
        int[] nums = {-2, 0, 3, -5, 2, -1};
        NumArray numArray = new NumArray(nums);
        System.out.println("Range sum [0,2]: " + numArray.sumRange(0, 2));
        System.out.println("Range sum [2,5]: " + numArray.sumRange(2, 5));
        
        // Test Subarray Sum Equals K
        int[] arr = {1, 1, 1};
        System.out.println("Subarrays with sum 2: " + subarraySum(arr, 2));
        
        // Test Product Less Than K
        int[] products = {10, 5, 2, 6};
        System.out.println("Subarrays with product < 100: " + numSubarrayProductLessThanK(products, 100));
        
        // Test 2D Range Sum
        int[][] matrix = {{3, 0, 1, 4, 2}, {5, 6, 3, 2, 1}, {1, 2, 0, 1, 5}, {4, 1, 0, 1, 7}, {1, 0, 3, 0, 5}};
        NumMatrix numMatrix = new NumMatrix(matrix);
        System.out.println("2D range sum [2,1,4,3]: " + numMatrix.sumRegion(2, 1, 4, 3));
    }

    // ==================================================================================
    //                          PREFIX SUMS PRACTICE PROBLEMS
    // ==================================================================================
    
    // ==================================================================================
    //                                  EASY PROBLEMS
    // ==================================================================================
    
    /**
     * PROBLEM: Range Sum Query - Immutable
     * DIFFICULTY: Easy
     * PATTERN: Basic Prefix Sums
     * 
     * DESCRIPTION:
     * Given an integer array nums, handle multiple queries of the following type:
     * Calculate the sum of the elements of nums between indices left and right 
     * inclusive where left <= right.
     * 
     * APPROACH:
     * 1. Precompute prefix sums during initialization
     * 2. Use prefix sums to answer range queries in O(1)
     * 3. Range sum [left, right] = prefixSum[right+1] - prefixSum[left]
     * 
     * TIME COMPLEXITY: O(n) preprocessing, O(1) query
     * SPACE COMPLEXITY: O(n)
     */
    static class NumArray {
        private int[] prefixSums;
        
        public NumArray(int[] nums) {
            prefixSums = new int[nums.length + 1];
            for (int i = 0; i < nums.length; i++) {
                prefixSums[i + 1] = prefixSums[i] + nums[i];
            }
        }
        
        public int sumRange(int left, int right) {
            return prefixSums[right + 1] - prefixSums[left];
        }
    }
    
    /**
     * PROBLEM: Running Sum of 1d Array
     * DIFFICULTY: Easy
     * PATTERN: Basic Prefix Sums
     * 
     * DESCRIPTION:
     * Given an array nums. The running sum of an array is defined as 
     * runningSum[i] = sum(nums[0]…nums[i]).
     * 
     * APPROACH:
     * 1. Create result array of same size
     * 2. Build running sum by adding current element to previous sum
     * 3. Can be done in-place to save space
     * 
     * TIME COMPLEXITY: O(n)
     * SPACE COMPLEXITY: O(1) if in-place, O(n) for new array
     */
    public static int[] runningSum(int[] nums) {
        int[] result = new int[nums.length];
        result[0] = nums[0];
        
        for (int i = 1; i < nums.length; i++) {
            result[i] = result[i - 1] + nums[i];
        }
        
        return result;
    }
    
    /**
     * PROBLEM: Find Pivot Index
     * DIFFICULTY: Easy
     * PATTERN: Prefix and Suffix Sums
     * 
     * DESCRIPTION:
     * Given an array of integers nums, calculate the pivot index of this array.
     * The pivot index is the index where the sum of all the numbers strictly to 
     * the left of the index is equal to the sum of all the numbers strictly to 
     * the index's right.
     * 
     * APPROACH:
     * 1. Calculate total sum of array
     * 2. Iterate through array maintaining left sum
     * 3. Right sum = total sum - left sum - current element
     * 4. Check if left sum equals right sum
     * 
     * TIME COMPLEXITY: O(n)
     * SPACE COMPLEXITY: O(1)
     */
    public static int pivotIndex(int[] nums) {
        int totalSum = 0;
        for (int num : nums) {
            totalSum += num;
        }
        
        int leftSum = 0;
        for (int i = 0; i < nums.length; i++) {
            int rightSum = totalSum - leftSum - nums[i];
            
            if (leftSum == rightSum) {
                return i;
            }
            
            leftSum += nums[i];
        }
        
        return -1;
    }
    
    /**
     * PROBLEM: Range Sum Query 2D - Immutable
     * DIFFICULTY: Easy
     * PATTERN: 2D Prefix Sums
     * 
     * DESCRIPTION:
     * Given a 2D matrix matrix, handle multiple queries of the following type:
     * Calculate the sum of the elements of matrix inside the rectangle defined 
     * by its upper left corner (row1, col1) and lower right corner (row2, col2).
     * 
     * APPROACH:
     * 1. Build 2D prefix sum matrix with padding
     * 2. Use inclusion-exclusion principle for range queries
     * 3. Sum = bottomRight - topBoundary - leftBoundary + topLeftCorner
     * 
     * TIME COMPLEXITY: O(mn) preprocessing, O(1) query
     * SPACE COMPLEXITY: O(mn)
     */
    static class NumMatrix {
        private int[][] prefixSums;
        
        public NumMatrix(int[][] matrix) {
            if (matrix.length == 0 || matrix[0].length == 0) return;
            
            int m = matrix.length, n = matrix[0].length;
            prefixSums = new int[m + 1][n + 1];
            
            for (int i = 1; i <= m; i++) {
                for (int j = 1; j <= n; j++) {
                    prefixSums[i][j] = matrix[i-1][j-1] 
                                     + prefixSums[i-1][j] 
                                     + prefixSums[i][j-1] 
                                     - prefixSums[i-1][j-1];
                }
            }
        }
        
        public int sumRegion(int row1, int col1, int row2, int col2) {
            return prefixSums[row2+1][col2+1] 
                 - prefixSums[row1][col2+1] 
                 - prefixSums[row2+1][col1] 
                 + prefixSums[row1][col1];
        }
    }
    
    // ==================================================================================
    //                                MEDIUM PROBLEMS
    // ==================================================================================
    
    /**
     * PROBLEM: Subarray Sum Equals K
     * DIFFICULTY: Medium
     * PATTERN: Prefix Sums with HashMap
     * 
     * DESCRIPTION:
     * Given an array of integers nums and an integer k, return the total number 
     * of continuous subarrays whose sum equals to k.
     * 
     * APPROACH:
     * 1. Use HashMap to store prefix sum frequencies
     * 2. For each position, check if (currentSum - k) exists in map
     * 3. This represents subarrays ending at current position with sum k
     * 4. Add current prefix sum to map
     * 
     * TIME COMPLEXITY: O(n)
     * SPACE COMPLEXITY: O(n)
     */
    public static int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> prefixSumCount = new HashMap<>();
        prefixSumCount.put(0, 1); // Empty subarray has sum 0
        
        int count = 0;
        int prefixSum = 0;
        
        for (int num : nums) {
            prefixSum += num;
            
            // Check if (prefixSum - k) exists
            if (prefixSumCount.containsKey(prefixSum - k)) {
                count += prefixSumCount.get(prefixSum - k);
            }
            
            // Update frequency of current prefix sum
            prefixSumCount.put(prefixSum, prefixSumCount.getOrDefault(prefixSum, 0) + 1);
        }
        
        return count;
    }
    
    /**
     * PROBLEM: Subarray Product Less Than K
     * DIFFICULTY: Medium
     * PATTERN: Sliding Window (related to prefix products)
     * 
     * DESCRIPTION:
     * Given an array of integers nums and an integer k, return the number of 
     * contiguous subarrays where the product of all the elements in the 
     * subarray is strictly less than k.
     * 
     * APPROACH:
     * 1. Use sliding window to maintain current product
     * 2. Expand window by including right element
     * 3. Contract window when product >= k
     * 4. Count all subarrays ending at right position
     * 
     * TIME COMPLEXITY: O(n)
     * SPACE COMPLEXITY: O(1)
     */
    public static int numSubarrayProductLessThanK(int[] nums, int k) {
        if (k <= 1) return 0;
        
        int left = 0, product = 1, count = 0;
        
        for (int right = 0; right < nums.length; right++) {
            product *= nums[right];
            
            // Contract window while product >= k
            while (product >= k) {
                product /= nums[left];
                left++;
            }
            
            // All subarrays ending at 'right' with start >= 'left' are valid
            count += right - left + 1;
        }
        
        return count;
    }
    
    /**
     * PROBLEM: Continuous Subarray Sum
     * DIFFICULTY: Medium
     * PATTERN: Prefix Sums with Modulo and HashMap
     * 
     * DESCRIPTION:
     * Given an integer array nums and an integer k, return true if nums has a 
     * continuous subarray of size at least two whose elements sum up to a 
     * multiple of k.
     * 
     * APPROACH:
     * 1. Use prefix sums with modulo k
     * 2. If two prefix sums have same remainder, subarray between them is divisible by k
     * 3. Ensure subarray length is at least 2
     * 4. Store remainder and its first occurrence index
     * 
     * TIME COMPLEXITY: O(n)
     * SPACE COMPLEXITY: O(min(n, k))
     */
    public static boolean checkSubarraySum(int[] nums, int k) {
        Map<Integer, Integer> remainderIndex = new HashMap<>();
        remainderIndex.put(0, -1); // Empty prefix has remainder 0
        
        int prefixSum = 0;
        
        for (int i = 0; i < nums.length; i++) {
            prefixSum += nums[i];
            int remainder = prefixSum % k;
            
            if (remainderIndex.containsKey(remainder)) {
                // Check if subarray length is at least 2
                if (i - remainderIndex.get(remainder) > 1) {
                    return true;
                }
            } else {
                remainderIndex.put(remainder, i);
            }
        }
        
        return false;
    }
    
    /**
     * PROBLEM: Subarray Sums Divisible by K
     * DIFFICULTY: Medium
     * PATTERN: Prefix Sums with Modulo and HashMap
     * 
     * DESCRIPTION:
     * Given an integer array nums and an integer k, return the number of 
     * non-empty subarrays that have a sum divisible by k.
     * 
     * APPROACH:
     * 1. Use prefix sums with modulo k
     * 2. Count frequency of each remainder
     * 3. For each remainder, choose 2 positions from its frequency (C(n,2))
     * 4. Handle negative remainders properly
     * 
     * TIME COMPLEXITY: O(n)
     * SPACE COMPLEXITY: O(k)
     */
    public static int subarraysDivByK(int[] nums, int k) {
        Map<Integer, Integer> remainderCount = new HashMap<>();
        remainderCount.put(0, 1); // Empty prefix has remainder 0
        
        int prefixSum = 0;
        int count = 0;
        
        for (int num : nums) {
            prefixSum += num;
            int remainder = ((prefixSum % k) + k) % k; // Handle negative numbers
            
            if (remainderCount.containsKey(remainder)) {
                count += remainderCount.get(remainder);
            }
            
            remainderCount.put(remainder, remainderCount.getOrDefault(remainder, 0) + 1);
        }
        
        return count;
    }
    
    /**
     * PROBLEM: Range Addition
     * DIFFICULTY: Medium
     * PATTERN: Difference Arrays
     * 
     * DESCRIPTION:
     * You are given an integer length and an array updates where 
     * updates[i] = [startIdx, endIdx, inc] which represent that you should add 
     * the value inc to subarray nums[startIdx...endIdx] (inclusive).
     * 
     * APPROACH:
     * 1. Use difference array to handle range updates efficiently
     * 2. For each update [start, end, val], increment diff[start] and decrement diff[end+1]
     * 3. Reconstruct final array using prefix sums of difference array
     * 
     * TIME COMPLEXITY: O(n + m) where m is number of updates
     * SPACE COMPLEXITY: O(n)
     */
    public static int[] getModifiedArray(int length, int[][] updates) {
        int[] diff = new int[length + 1];
        
        // Apply all updates to difference array
        for (int[] update : updates) {
            int start = update[0];
            int end = update[1];
            int val = update[2];
            
            diff[start] += val;
            if (end + 1 < diff.length) {
                diff[end + 1] -= val;
            }
        }
        
        // Reconstruct result using prefix sums
        int[] result = new int[length];
        result[0] = diff[0];
        for (int i = 1; i < length; i++) {
            result[i] = result[i-1] + diff[i];
        }
        
        return result;
    }
    
    /**
     * PROBLEM: XOR Queries of a Subarray
     * DIFFICULTY: Medium
     * PATTERN: Prefix XOR
     * 
     * DESCRIPTION:
     * Given the array arr of positive integers and the array queries where 
     * queries[i] = [Li, Ri], for each query i compute the XOR of elements 
     * from Li to Ri.
     * 
     * APPROACH:
     * 1. Build prefix XOR array
     * 2. XOR of range [L, R] = prefixXor[R+1] ^ prefixXor[L]
     * 3. Works because A ^ A = 0
     * 
     * TIME COMPLEXITY: O(n + m) where m is number of queries
     * SPACE COMPLEXITY: O(n)
     */
    public static int[] xorQueries(int[] arr, int[][] queries) {
        int[] prefixXor = new int[arr.length + 1];
        
        for (int i = 0; i < arr.length; i++) {
            prefixXor[i + 1] = prefixXor[i] ^ arr[i];
        }
        
        int[] result = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int left = queries[i][0];
            int right = queries[i][1];
            result[i] = prefixXor[right + 1] ^ prefixXor[left];
        }
        
        return result;
    }
    
    // ==================================================================================
    //                                 HARD PROBLEMS
    // ==================================================================================
    
    /**
     * PROBLEM: Count of Smaller Numbers After Self
     * DIFFICULTY: Hard
     * PATTERN: Modified Merge Sort with Index Tracking
     * 
     * DESCRIPTION:
     * You are given an integer array nums and you have to return a new counts 
     * array. The counts array has the property where counts[i] is the number 
     * of smaller elements to the right of nums[i].
     * 
     * APPROACH:
     * 1. Use modified merge sort with index tracking
     * 2. During merge, count elements from right array that are smaller
     * 3. Maintain original indices throughout sorting process
     * 
     * TIME COMPLEXITY: O(n log n)
     * SPACE COMPLEXITY: O(n)
     */
    public static List<Integer> countSmaller(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        int[][] pairs = new int[n][2];
        
        for (int i = 0; i < n; i++) {
            pairs[i] = new int[]{nums[i], i};
        }
        
        mergeSort(pairs, 0, n - 1, result);
        
        List<Integer> list = new ArrayList<>();
        for (int count : result) {
            list.add(count);
        }
        return list;
    }
    
    private static void mergeSort(int[][] pairs, int left, int right, int[] result) {
        if (left >= right) return;
        
        int mid = left + (right - left) / 2;
        mergeSort(pairs, left, mid, result);
        mergeSort(pairs, mid + 1, right, result);
        mergeAndCount(pairs, left, mid, right, result);
    }
    
    private static void mergeAndCount(int[][] pairs, int left, int mid, int right, int[] result) {
        int[][] temp = new int[right - left + 1][2];
        int i = left, j = mid + 1, k = 0;
        
        while (i <= mid && j <= right) {
            if (pairs[i][0] <= pairs[j][0]) {
                result[pairs[i][1]] += (j - mid - 1);
                temp[k++] = pairs[i++];
            } else {
                temp[k++] = pairs[j++];
            }
        }
        
        while (i <= mid) {
            result[pairs[i][1]] += (j - mid - 1);
            temp[k++] = pairs[i++];
        }
        
        while (j <= right) {
            temp[k++] = pairs[j++];
        }
        
        for (int idx = 0; idx < temp.length; idx++) {
            pairs[left + idx] = temp[idx];
        }
    }
    
    /**
     * PROBLEM: Range Sum Query - Mutable
     * DIFFICULTY: Hard
     * PATTERN: Binary Indexed Tree (Fenwick Tree)
     * 
     * DESCRIPTION:
     * Given an integer array nums, handle multiple queries of the following types:
     * 1. Update the value of an element in nums.
     * 2. Calculate the sum of the elements of nums between indices left and right.
     * 
     * APPROACH:
     * 1. Use Binary Indexed Tree for efficient updates and queries
     * 2. Both operations in O(log n) time
     * 3. Use bit manipulation for tree navigation
     * 
     * TIME COMPLEXITY: O(log n) for both update and query
     * SPACE COMPLEXITY: O(n)
     */
    static class NumArrayMutable {
        private int[] tree;
        private int[] nums;
        private int n;
        
        public NumArrayMutable(int[] nums) {
            this.nums = nums.clone();
            n = nums.length;
            tree = new int[n + 1];
            
            // Build tree
            for (int i = 0; i < n; i++) {
                updateBIT(i + 1, nums[i]);
            }
        }
        
        public void update(int index, int val) {
            int delta = val - nums[index];
            nums[index] = val;
            updateBIT(index + 1, delta);
        }
        
        public int sumRange(int left, int right) {
            return queryBIT(right + 1) - queryBIT(left);
        }
        
        private void updateBIT(int idx, int delta) {
            for (int i = idx; i <= n; i += i & (-i)) {
                tree[i] += delta;
            }
        }
        
        private int queryBIT(int idx) {
            int sum = 0;
            for (int i = idx; i > 0; i -= i & (-i)) {
                sum += tree[i];
            }
            return sum;
        }
    }
    
    /**
     * PROBLEM: Count of Range Sum
     * DIFFICULTY: Hard
     * PATTERN: Merge Sort with Prefix Sums
     * 
     * DESCRIPTION:
     * Given an integer array nums and two integers lower and upper, return the 
     * number of range sums that lie in [lower, upper] inclusive.
     * 
     * APPROACH:
     * 1. Build prefix sum array
     * 2. Use merge sort to count valid ranges efficiently
     * 3. For each position, count prefix sums in valid range
     * 
     * TIME COMPLEXITY: O(n log n)
     * SPACE COMPLEXITY: O(n)
     */
    public static int countRangeSum(int[] nums, int lower, int upper) {
        long[] prefixSums = new long[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            prefixSums[i + 1] = prefixSums[i] + nums[i];
        }
        
        return mergeSortAndCount(prefixSums, 0, prefixSums.length - 1, lower, upper);
    }
    
    private static int mergeSortAndCount(long[] sums, int left, int right, int lower, int upper) {
        if (left >= right) return 0;
        
        int mid = left + (right - left) / 2;
        int count = mergeSortAndCount(sums, left, mid, lower, upper) +
                   mergeSortAndCount(sums, mid + 1, right, lower, upper);
        
        // Count valid ranges
        int j = mid + 1, k = mid + 1;
        for (int i = left; i <= mid; i++) {
            while (j <= right && sums[j] - sums[i] < lower) j++;
            while (k <= right && sums[k] - sums[i] <= upper) k++;
            count += k - j;
        }
        
        // Merge
        long[] temp = new long[right - left + 1];
        int i = left, p = mid + 1, q = 0;
        
        while (i <= mid && p <= right) {
            if (sums[i] <= sums[p]) {
                temp[q++] = sums[i++];
            } else {
                temp[q++] = sums[p++];
            }
        }
        
        while (i <= mid) temp[q++] = sums[i++];
        while (p <= right) temp[q++] = sums[p++];
        
        for (int idx = 0; idx < temp.length; idx++) {
            sums[left + idx] = temp[idx];
        }
        
        return count;
    }
    
    // ==================================================================================
    //                               HELPER METHODS
    // ==================================================================================
    
    /**
     * Helper method to print array for debugging
     */
    private static void printArray(int[] arr) {
        System.out.println(Arrays.toString(arr));
    }
    
    /**
     * Helper method to print 2D array for debugging
     */
    private static void print2DArray(int[][] arr) {
        for (int[] row : arr) {
            System.out.println(Arrays.toString(row));
        }
    }
} 