package com.dsalgo.AlgoPatterns.Arrays;

/**
 * ==================================================================================
 *                         PREFIX SUMS PATTERN - READING GUIDE
 * ==================================================================================
 * 
 * OVERVIEW:
 * Prefix Sums (also known as Cumulative Sums) is a preprocessing technique that
 * allows for efficient computation of range sums, subarray sums, and related
 * queries. By precomputing cumulative values, we can answer range queries in
 * O(1) time instead of O(n), making it invaluable for problems involving
 * multiple range operations.
 * 
 * ==================================================================================
 *                            WHEN TO USE PREFIX SUMS
 * ==================================================================================
 * 
 * 🎯 PATTERN RECOGNITION:
 * ✅ Problems involving subarray sums or range sums
 * ✅ Multiple queries on the same array
 * ✅ Need to compute cumulative sums or averages
 * ✅ Finding subarrays with specific sum properties
 * ✅ Range sum queries (static or dynamic)
 * ✅ Optimization of operations involving subarrays
 * ✅ Problems mentioning "cumulative", "running total", "range"
 * ✅ Need to precompute aggregate values
 * ✅ 2D matrix range sum queries
 * 
 * 🚨 RED FLAGS (when NOT to use):
 * ❌ Single query on array (direct calculation might be simpler)
 * ❌ Frequent array modifications (unless using specialized data structures)
 * ❌ Need to maintain sorted order
 * ❌ Problems requiring element positions or ordering
 * 
 * ==================================================================================
 *                            PREFIX SUMS VARIATIONS
 * ==================================================================================
 */
public class PrefixSumsReadingGuide {

    /**
     * ================================================================================
     *                          VARIATION 1: BASIC PREFIX SUMS
     * ================================================================================
     * 
     * PATTERN:
     * 1. Create prefix sum array where prefixSum[i] = sum of elements [0...i]
     * 2. Range sum [i,j] = prefixSum[j] - prefixSum[i-1]
     * 3. Handle edge cases (i=0) carefully
     * 
     * USE CASES:
     * - Range sum queries
     * - Subarray sum problems
     * - Finding average of ranges
     * - Multiple queries on static array
     */
    
    // EXAMPLE: Range Sum Query - Immutable
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
     * ================================================================================
     *                         VARIATION 2: PREFIX SUMS WITH HASHMAP
     * ================================================================================
     * 
     * PATTERN:
     * 1. Use HashMap to store prefix sums and their frequencies/indices
     * 2. For subarray sum = target: prefixSum[j] - prefixSum[i] = target
     * 3. Look for prefixSum[j] - target in HashMap
     * 
     * USE CASES:
     * - Subarray sum equals target
     * - Count of subarrays with given sum
     * - Longest subarray with sum k
     * - Subarray sum divisible by k
     */
    
    // EXAMPLE: Subarray Sum Equals K
    public static int subarraySum(int[] nums, int k) {
        java.util.Map<Integer, Integer> prefixSumCount = new java.util.HashMap<>();
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
     * ================================================================================
     *                          VARIATION 3: 2D PREFIX SUMS
     * ================================================================================
     * 
     * PATTERN:
     * 1. Build 2D prefix sum matrix
     * 2. prefixSum[i][j] = sum of rectangle from (0,0) to (i,j)
     * 3. Range sum = prefixSum[r2][c2] - prefixSum[r1-1][c2] - prefixSum[r2][c1-1] + prefixSum[r1-1][c1-1]
     * 
     * USE CASES:
     * - 2D range sum queries
     * - Matrix subregion problems
     * - Image processing applications
     * - 2D cumulative frequency tables
     */
    
    // EXAMPLE: Range Sum Query 2D - Immutable
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

    /**
     * ================================================================================
     *                        VARIATION 4: DIFFERENCE ARRAYS
     * ================================================================================
     * 
     * PATTERN:
     * 1. Use difference array for range updates
     * 2. diff[i] = arr[i] - arr[i-1]
     * 3. Range update [l,r] by val: diff[l] += val, diff[r+1] -= val
     * 4. Reconstruct array using prefix sums of difference array
     * 
     * USE CASES:
     * - Multiple range updates
     * - Batch processing of range operations
     * - Flight booking systems
     * - Meeting room scheduling
     */
    
    // EXAMPLE: Range Addition
    public static int[] getModifiedArray(int length, int[][] updates) {
        int[] diff = new int[length + 1];
        
        // Apply all updates to difference array
        for (int[] update : updates) {
            int start = update[0];
            int end = update[1];
            int val = update[2];
            
            diff[start] += val;
            diff[end + 1] -= val;
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
     * ================================================================================
     *                      VARIATION 5: BINARY INDEXED TREE (FENWICK TREE)
     * ================================================================================
     * 
     * PATTERN:
     * 1. Support both range queries and point updates efficiently
     * 2. Use bit manipulation for tree navigation
     * 3. Both operations in O(log n) time
     * 
     * USE CASES:
     * - Range sum with frequent updates
     * - Dynamic cumulative frequency
     * - Order statistics
     * - Inversion counting
     */
    
    // EXAMPLE: Binary Indexed Tree Implementation
    static class BinaryIndexedTree {
        private int[] tree;
        private int n;
        
        public BinaryIndexedTree(int size) {
            n = size;
            tree = new int[n + 1];
        }
        
        public void update(int idx, int delta) {
            for (int i = idx; i <= n; i += i & (-i)) {
                tree[i] += delta;
            }
        }
        
        public int query(int idx) {
            int sum = 0;
            for (int i = idx; i > 0; i -= i & (-i)) {
                sum += tree[i];
            }
            return sum;
        }
        
        public int rangeQuery(int left, int right) {
            return query(right) - query(left - 1);
        }
    }

    /**
     * ================================================================================
     *                        VARIATION 6: SEGMENT TREE
     * ================================================================================
     * 
     * PATTERN:
     * 1. Build tree with each node storing aggregate of its range
     * 2. Support range queries and range updates
     * 3. Both operations in O(log n) time
     * 
     * USE CASES:
     * - Range minimum/maximum queries
     * - Range sum with range updates
     * - Lazy propagation for efficiency
     * - Complex aggregate functions
     */
    
    // EXAMPLE: Simple Segment Tree for Range Sum
    static class SegmentTree {
        private int[] tree;
        private int n;
        
        public SegmentTree(int[] nums) {
            n = nums.length;
            tree = new int[4 * n];
            build(nums, 0, 0, n - 1);
        }
        
        private void build(int[] nums, int node, int start, int end) {
            if (start == end) {
                tree[node] = nums[start];
            } else {
                int mid = (start + end) / 2;
                build(nums, 2 * node + 1, start, mid);
                build(nums, 2 * node + 2, mid + 1, end);
                tree[node] = tree[2 * node + 1] + tree[2 * node + 2];
            }
        }
        
        public int query(int left, int right) {
            return query(0, 0, n - 1, left, right);
        }
        
        private int query(int node, int start, int end, int left, int right) {
            if (right < start || end < left) return 0;
            if (left <= start && end <= right) return tree[node];
            
            int mid = (start + end) / 2;
            return query(2 * node + 1, start, mid, left, right) +
                   query(2 * node + 2, mid + 1, end, left, right);
        }
    }

    /**
     * ================================================================================
     *                           ADVANCED PREFIX SUM PATTERNS
     * ================================================================================
     */
    
    // PRODUCT PREFIX - For subarray products
    public static int numSubarrayProductLessThanK(int[] nums, int k) {
        if (k <= 1) return 0;
        
        int left = 0, product = 1, count = 0;
        
        for (int right = 0; right < nums.length; right++) {
            product *= nums[right];
            
            while (product >= k) {
                product /= nums[left];
                left++;
            }
            
            count += right - left + 1;
        }
        
        return count;
    }
    
    // XOR PREFIX - For subarray XOR queries
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

    /**
     * ================================================================================
     *                             COMMON PITFALLS & TIPS
     * ================================================================================
     * 
     * 🚨 COMMON MISTAKES:
     * 1. OFF-BY-ONE ERRORS: Incorrect indexing in prefix sum calculations
     * 2. EDGE CASES: Not handling empty ranges or single elements
     * 3. INTEGER OVERFLOW: Large sums exceeding integer limits
     * 4. INITIALIZATION: Forgetting to include 0 as first prefix sum
     * 5. RANGE BOUNDARIES: Incorrect handling of inclusive/exclusive ranges
     * 6. HASHMAP INITIALIZATION: Not adding prefixSum[0] = 0 for empty subarray
     * 
     * 💡 PRO TIPS:
     * 1. Always use 0-indexed arrays with 1-extra element for easier calculations
     * 2. For 2D problems, add padding row and column of zeros
     * 3. Use long instead of int for large sum calculations
     * 4. HashMap approach works well for "count/find" subarray problems
     * 5. Consider using difference arrays for multiple range updates
     * 6. Binary Indexed Tree when you need both queries and updates
     * 7. Draw examples to visualize prefix sum calculations
     * 
     * ================================================================================
     *                               PRACTICE PROBLEMS
     * ================================================================================
     * 
     * 🟢 EASY:
     * - Range Sum Query - Immutable
     * - Running Sum of 1d Array
     * - Find Pivot Index
     * - Subarray Sum Equals K
     * - Range Sum Query 2D - Immutable
     * 
     * 🟡 MEDIUM:
     * - Range Sum Query - Mutable
     * - Subarray Product Less Than K
     * - Continuous Subarray Sum
     * - Subarray Sums Divisible by K
     * - Maximum Size Subarray Sum Equals k
     * - Count Number of Nice Subarrays
     * - XOR Queries of a Subarray
     * - Range Addition
     * 
     * 🔴 HARD:
     * - Count of Smaller Numbers After Self
     * - Count of Range Sum
     * - Maximum Frequency Stack
     * - Range Sum Query 2D - Mutable
     * - Rectangle Area II
     * 
     * ================================================================================
     *                             DECISION FRAMEWORK
     * ================================================================================
     * 
     * ASK YOURSELF:
     * 1. Do I need multiple range sum queries? → Basic prefix sums
     * 2. Looking for subarrays with specific sum? → Prefix sums + HashMap
     * 3. Working with 2D matrix ranges? → 2D prefix sums
     * 4. Need both queries and updates? → Binary Indexed Tree or Segment Tree
     * 5. Multiple range updates then queries? → Difference arrays
     * 6. Complex aggregate functions? → Segment Tree
     * 7. Real-time updates with queries? → Binary Indexed Tree
     * 
     * COMPLEXITY ANALYSIS:
     * - Preprocessing: O(n) for 1D, O(mn) for 2D
     * - Range Query: O(1) for basic prefix sums
     * - Point Update: O(log n) for BIT/Segment Tree
     * - Range Update: O(1) for difference arrays, O(log n) for trees
     * 
     * SPACE COMPLEXITY:
     * - Basic prefix sums: O(n) or O(mn)
     * - HashMap approach: O(n) for storing prefix sums
     * - Trees: O(n) for structure
     * 
     * Remember: Prefix sums transform O(n) range queries into O(1) operations
     * by trading space for time through preprocessing!
     */
} 