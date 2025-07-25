package com.dsalgo.AlgoPatterns.DynamicProgramming;

import java.util.*;

/**
 * 🚀 ADVANCED DYNAMIC PROGRAMMING PATTERNS - COMPREHENSIVE IMPLEMENTATION
 * 
 * ============================================================================
 * 📚 MULTI-PATTERN OVERVIEW
 * ============================================================================
 * 
 * This file combines 6 advanced DP patterns:
 * 1️⃣ OPTIMAL SUBSTRUCTURE: LIS, Edit Distance, Coin Change
 * 2️⃣ INTERVAL/RANGE DP: Burst Balloons, Matrix Chain Multiplication
 * 3️⃣ KNAPSACK PROBLEMS: 0/1 Knapsack, Partition Sum, Target Sum
 * 4️⃣ PREFIX SUMS: Subarray Sum, Range Sum Queries
 * 5️⃣ COUNTING PROBLEMS: Unique Paths, Combinations, Subsequences
 * 6️⃣ INTERVAL PARTITIONING: Non-overlapping Intervals, Activity Selection
 * 
 * ============================================================================
 * 🎯 APPLICATIONS: Optimization, combinatorics, sequence analysis, scheduling
 * ============================================================================
 */

public class AdvancedDP {
    
    // ============================================================================
    // 🎯 OPTIMAL SUBSTRUCTURE PATTERN
    // ============================================================================
    
    /**
     * Longest Increasing Subsequence - Classic Optimal Substructure
     * 
     * Given an integer array nums, return the length of the longest strictly 
     * increasing subsequence.
     * 
     * Strategy: DP with O(n²) or Binary Search with O(n log n)
     * Time: O(n log n), Space: O(n)
     * 
     * LeetCode: https://leetcode.com/problems/longest-increasing-subsequence/
     */
    public static int lengthOfLIS(int[] nums) {
        List<Integer> tails = new ArrayList<>();
        
        for (int num : nums) {
            int pos = Collections.binarySearch(tails, num);
            if (pos < 0) {
                pos = -(pos + 1);
            }
            
            if (pos == tails.size()) {
                tails.add(num);
            } else {
                tails.set(pos, num);
            }
        }
        
        return tails.size();
    }
    
    /**
     * Edit Distance - Optimal Substructure with Two Sequences
     * 
     * Given two strings word1 and word2, return the minimum number of operations 
     * required to convert word1 to word2. You have the following three operations:
     * Insert, Delete, Replace a character.
     * 
     * Strategy: 2D DP with operation costs
     * Time: O(m * n), Space: O(min(m, n))
     * 
     * LeetCode: https://leetcode.com/problems/edit-distance/
     */
    public static int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        
        // Space optimization: use 1D array
        int[] prev = new int[n + 1];
        int[] curr = new int[n + 1];
        
        // Initialize base cases
        for (int j = 0; j <= n; j++) {
            prev[j] = j;
        }
        
        for (int i = 1; i <= m; i++) {
            curr[0] = i;
            
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    curr[j] = prev[j - 1];
                } else {
                    curr[j] = 1 + Math.min(prev[j], Math.min(curr[j - 1], prev[j - 1]));
                }
            }
            
            int[] temp = prev;
            prev = curr;
            curr = temp;
        }
        
        return prev[n];
    }
    
    /**
     * Coin Change - Optimal Substructure with Choices
     * 
     * You are given an integer array coins representing coins of different 
     * denominations and an integer amount representing a total amount of money.
     * Return the fewest number of coins that you need to make up that amount.
     * 
     * Strategy: DP with minimum choices
     * Time: O(amount * coins.length), Space: O(amount)
     * 
     * LeetCode: https://leetcode.com/problems/coin-change/
     */
    public static int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1); // Initialize with impossible value
        dp[0] = 0;
        
        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (coin <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        
        return dp[amount] > amount ? -1 : dp[amount];
    }
    
    // ============================================================================
    // 🎯 INTERVAL/RANGE DP PATTERN
    // ============================================================================
    
    /**
     * Burst Balloons - Range DP Classic
     * 
     * You are given n balloons, indexed from 0 to n - 1. Each balloon is painted 
     * with a number on it represented by the array nums. You are asked to burst 
     * all the balloons. If you burst balloon i you will get 
     * nums[i - 1] * nums[i] * nums[i + 1] coins.
     * 
     * Strategy: Range DP with last balloon to burst
     * Time: O(n³), Space: O(n²)
     * 
     * LeetCode: https://leetcode.com/problems/burst-balloons/
     */
    public static int maxCoins(int[] nums) {
        int n = nums.length;
        int[] newNums = new int[n + 2];
        newNums[0] = 1;
        newNums[n + 1] = 1;
        System.arraycopy(nums, 0, newNums, 1, n);
        
        int[][] dp = new int[n + 2][n + 2];
        
        for (int len = 2; len < n + 2; len++) {
            for (int left = 0; left < n + 2 - len; left++) {
                int right = left + len;
                
                for (int k = left + 1; k < right; k++) {
                    dp[left][right] = Math.max(dp[left][right],
                        dp[left][k] + dp[k][right] + 
                        newNums[left] * newNums[k] * newNums[right]);
                }
            }
        }
        
        return dp[0][n + 1];
    }
    
    /**
     * Matrix Chain Multiplication - Classic Range DP
     * 
     * Given a sequence of matrices, find the most efficient way to multiply 
     * these matrices together. The efficient way is the one that involves 
     * the least number of multiplications.
     * 
     * Strategy: Range DP to find optimal parenthesization
     * Time: O(n³), Space: O(n²)
     */
    public static int matrixChainMultiplication(int[] dims) {
        int n = dims.length - 1; // Number of matrices
        int[][] dp = new int[n][n];
        
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;
                dp[i][j] = Integer.MAX_VALUE;
                
                for (int k = i; k < j; k++) {
                    int cost = dp[i][k] + dp[k + 1][j] + 
                              dims[i] * dims[k + 1] * dims[j + 1];
                    dp[i][j] = Math.min(dp[i][j], cost);
                }
            }
        }
        
        return dp[0][n - 1];
    }
    
    // ============================================================================
    // 🎯 KNAPSACK PROBLEMS PATTERN
    // ============================================================================
    
    /**
     * 0/1 Knapsack - Classic Knapsack Problem
     * 
     * Given weights and values of n items, put these items in a knapsack of 
     * capacity W to get the maximum total value in the knapsack.
     * 
     * Strategy: 2D DP optimized to 1D
     * Time: O(n * W), Space: O(W)
     */
    public static int knapsack01(int[] weights, int[] values, int capacity) {
        int n = weights.length;
        int[] dp = new int[capacity + 1];
        
        for (int i = 0; i < n; i++) {
            for (int w = capacity; w >= weights[i]; w--) {
                dp[w] = Math.max(dp[w], dp[w - weights[i]] + values[i]);
            }
        }
        
        return dp[capacity];
    }
    
    /**
     * Partition Equal Subset Sum - Knapsack Variation
     * 
     * Given a non-empty array nums containing only positive integers, find if 
     * the array can be partitioned into two subsets such that the sum of 
     * elements in both subsets is equal.
     * 
     * Strategy: Knapsack to find subset with sum = total/2
     * Time: O(n * sum), Space: O(sum)
     * 
     * LeetCode: https://leetcode.com/problems/partition-equal-subset-sum/
     */
    public static boolean canPartition(int[] nums) {
        int sum = Arrays.stream(nums).sum();
        if (sum % 2 != 0) return false;
        
        int target = sum / 2;
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;
        
        for (int num : nums) {
            for (int j = target; j >= num; j--) {
                dp[j] = dp[j] || dp[j - num];
            }
        }
        
        return dp[target];
    }
    
    /**
     * Target Sum - Knapsack with +/- Operations
     * 
     * You are given an integer array nums and an integer target. You want to 
     * build an expression out of nums by adding one of the symbols '+' and '-' 
     * before each integer in nums and then concatenate all the integers.
     * 
     * Strategy: Transform to subset sum problem
     * Time: O(n * sum), Space: O(sum)
     * 
     * LeetCode: https://leetcode.com/problems/target-sum/
     */
    public static int findTargetSumWays(int[] nums, int target) {
        int sum = Arrays.stream(nums).sum();
        if (Math.abs(target) > sum || (sum + target) % 2 != 0) return 0;
        
        int subsetSum = (sum + target) / 2;
        int[] dp = new int[subsetSum + 1];
        dp[0] = 1;
        
        for (int num : nums) {
            for (int j = subsetSum; j >= num; j--) {
                dp[j] += dp[j - num];
            }
        }
        
        return dp[subsetSum];
    }
    
    // ============================================================================
    // 🎯 PREFIX SUMS PATTERN
    // ============================================================================
    
    /**
     * Subarray Sum Equals K - Prefix Sum with HashMap
     * 
     * Given an array of integers nums and an integer k, return the total number 
     * of continuous subarrays whose sum equals to k.
     * 
     * Strategy: Prefix sum with frequency counting
     * Time: O(n), Space: O(n)
     * 
     * LeetCode: https://leetcode.com/problems/subarray-sum-equals-k/
     */
    public static int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> prefixSumCount = new HashMap<>();
        prefixSumCount.put(0, 1);
        
        int prefixSum = 0;
        int count = 0;
        
        for (int num : nums) {
            prefixSum += num;
            
            if (prefixSumCount.containsKey(prefixSum - k)) {
                count += prefixSumCount.get(prefixSum - k);
            }
            
            prefixSumCount.put(prefixSum, prefixSumCount.getOrDefault(prefixSum, 0) + 1);
        }
        
        return count;
    }
    
    /**
     * Range Sum Query - Immutable Array with Prefix Sums
     * 
     * Given an integer array nums, handle multiple queries of the following type:
     * Calculate the sum of the elements of nums between indices left and right.
     * 
     * Strategy: Precompute prefix sums for O(1) queries
     * Time: O(1) per query, Space: O(n)
     */
    public static class NumArray {
        private int[] prefixSum;
        
        public NumArray(int[] nums) {
            prefixSum = new int[nums.length + 1];
            for (int i = 0; i < nums.length; i++) {
                prefixSum[i + 1] = prefixSum[i] + nums[i];
            }
        }
        
        public int sumRange(int left, int right) {
            return prefixSum[right + 1] - prefixSum[left];
        }
    }
    
    /**
     * Maximum Size Subarray Sum Equals k - Prefix Sum Optimization
     * 
     * Given an array nums and a target value k, find the maximum length of a 
     * subarray that sums to k. If there isn't one, return 0 instead.
     * 
     * Strategy: Prefix sum with first occurrence tracking
     * Time: O(n), Space: O(n)
     */
    public static int maxSubArrayLen(int[] nums, int k) {
        Map<Integer, Integer> prefixSumIndex = new HashMap<>();
        prefixSumIndex.put(0, -1);
        
        int prefixSum = 0;
        int maxLen = 0;
        
        for (int i = 0; i < nums.length; i++) {
            prefixSum += nums[i];
            
            if (prefixSumIndex.containsKey(prefixSum - k)) {
                maxLen = Math.max(maxLen, i - prefixSumIndex.get(prefixSum - k));
            }
            
            prefixSumIndex.putIfAbsent(prefixSum, i);
        }
        
        return maxLen;
    }
    
    // ============================================================================
    // 🎯 COUNTING PROBLEMS PATTERN
    // ============================================================================
    
    /**
     * Unique Paths - Grid Counting DP
     * 
     * A robot is located at the top-left corner of a m x n grid. The robot can 
     * only move either down or right at any point in time. The robot is trying 
     * to reach the bottom-right corner of the grid.
     * 
     * Strategy: Combinatorics or DP counting
     * Time: O(m * n), Space: O(n)
     * 
     * LeetCode: https://leetcode.com/problems/unique-paths/
     */
    public static int uniquePaths(int m, int n) {
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[j] += dp[j - 1];
            }
        }
        
        return dp[n - 1];
    }
    
    /**
     * Unique Paths II - Grid with Obstacles
     * 
     * A robot is located at the top-left corner of a m x n grid. Now consider 
     * if some obstacles are added to the grids. How many unique paths would there be?
     * 
     * Strategy: DP counting with obstacle handling
     * Time: O(m * n), Space: O(n)
     * 
     * LeetCode: https://leetcode.com/problems/unique-paths-ii/
     */
    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        
        if (obstacleGrid[0][0] == 1 || obstacleGrid[m - 1][n - 1] == 1) return 0;
        
        int[] dp = new int[n];
        dp[0] = 1;
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {
                    dp[j] = 0;
                } else if (j > 0) {
                    dp[j] += dp[j - 1];
                }
            }
        }
        
        return dp[n - 1];
    }
    
    /**
     * Number of Distinct Subsequences - String Counting DP
     * 
     * Given two strings s and t, return the number of distinct subsequences of 
     * s which equals t.
     * 
     * Strategy: 2D DP for subsequence counting
     * Time: O(m * n), Space: O(n)
     * 
     * LeetCode: https://leetcode.com/problems/distinct-subsequences/
     */
    public static int numDistinct(String s, String t) {
        int m = s.length();
        int n = t.length();
        
        int[] prev = new int[n + 1];
        int[] curr = new int[n + 1];
        prev[0] = 1;
        
        for (int i = 1; i <= m; i++) {
            curr[0] = 1;
            
            for (int j = 1; j <= n; j++) {
                curr[j] = prev[j];
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    curr[j] += prev[j - 1];
                }
            }
            
            int[] temp = prev;
            prev = curr;
            curr = temp;
        }
        
        return prev[n];
    }
    
    /**
     * Counting Bits - Binary Representation Counting
     * 
     * Given an integer n, return an array ans of length n + 1 such that for 
     * each i (0 <= i <= n), ans[i] is the number of 1's in the binary 
     * representation of i.
     * 
     * Strategy: DP using bit manipulation properties
     * Time: O(n), Space: O(n)
     * 
     * LeetCode: https://leetcode.com/problems/counting-bits/
     */
    public static int[] countBits(int n) {
        int[] dp = new int[n + 1];
        
        for (int i = 1; i <= n; i++) {
            dp[i] = dp[i >> 1] + (i & 1);
        }
        
        return dp;
    }
    
    // ============================================================================
    // 🎯 INTERVAL PARTITIONING PATTERN
    // ============================================================================
    
    /**
     * Non-overlapping Intervals - Greedy Interval Selection
     * 
     * Given an array of intervals, find the minimum number of intervals you 
     * need to remove to make the rest of the intervals non-overlapping.
     * 
     * Strategy: Greedy algorithm sorting by end time
     * Time: O(n log n), Space: O(1)
     * 
     * LeetCode: https://leetcode.com/problems/non-overlapping-intervals/
     */
    public static int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length == 0) return 0;
        
        Arrays.sort(intervals, (a, b) -> a[1] - b[1]);
        
        int count = 0;
        int lastEnd = intervals[0][1];
        
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] < lastEnd) {
                count++; // Remove current interval
            } else {
                lastEnd = intervals[i][1];
            }
        }
        
        return count;
    }
    
    /**
     * Merge Intervals - Interval Consolidation
     * 
     * Given an array of intervals where intervals[i] = [starti, endi], merge 
     * all overlapping intervals, and return an array of the non-overlapping 
     * intervals that cover all the intervals in the input.
     * 
     * Strategy: Sort and merge overlapping intervals
     * Time: O(n log n), Space: O(n)
     * 
     * LeetCode: https://leetcode.com/problems/merge-intervals/
     */
    public static int[][] merge(int[][] intervals) {
        if (intervals.length == 0) return new int[0][];
        
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        List<int[]> merged = new ArrayList<>();
        
        for (int[] interval : intervals) {
            if (merged.isEmpty() || merged.get(merged.size() - 1)[1] < interval[0]) {
                merged.add(interval);
            } else {
                merged.get(merged.size() - 1)[1] = Math.max(merged.get(merged.size() - 1)[1], interval[1]);
            }
        }
        
        return merged.toArray(new int[merged.size()][]);
    }
    
    /**
     * Partition Labels - String Partitioning
     * 
     * You are given a string s. We want to partition the string into as many 
     * parts as possible so that each letter appears in at most one part.
     * 
     * Strategy: Greedy partitioning with last occurrence tracking
     * Time: O(n), Space: O(1)
     * 
     * LeetCode: https://leetcode.com/problems/partition-labels/
     */
    public static List<Integer> partitionLabels(String s) {
        int[] lastIndex = new int[26];
        
        // Find last occurrence of each character
        for (int i = 0; i < s.length(); i++) {
            lastIndex[s.charAt(i) - 'a'] = i;
        }
        
        List<Integer> result = new ArrayList<>();
        int start = 0;
        int end = 0;
        
        for (int i = 0; i < s.length(); i++) {
            end = Math.max(end, lastIndex[s.charAt(i) - 'a']);
            
            if (i == end) {
                result.add(end - start + 1);
                start = end + 1;
            }
        }
        
        return result;
    }
    
    /**
     * Maximum Length of Pair Chain - Activity Selection
     * 
     * You are given an array of n pairs where pairs[i] = [lefti, righti] and 
     * lefti < righti. A pair p2 = [c, d] follows a pair p1 = [a, b] if b < c. 
     * A chain of pairs can be formed in this fashion.
     * 
     * Strategy: Greedy selection by end points
     * Time: O(n log n), Space: O(1)
     * 
     * LeetCode: https://leetcode.com/problems/maximum-length-of-pair-chain/
     */
    public static int findLongestChain(int[][] pairs) {
        Arrays.sort(pairs, (a, b) -> a[1] - b[1]);
        
        int count = 1;
        int lastEnd = pairs[0][1];
        
        for (int i = 1; i < pairs.length; i++) {
            if (pairs[i][0] > lastEnd) {
                count++;
                lastEnd = pairs[i][1];
            }
        }
        
        return count;
    }
    
    public static void main(String[] args) {
        System.out.println("🚀 ADVANCED DYNAMIC PROGRAMMING - COMPREHENSIVE IMPLEMENTATION");
        System.out.println("============================================================");
        
        // Test Optimal Substructure
        System.out.println("\n🎯 TESTING OPTIMAL SUBSTRUCTURE:");
        int[] nums1 = {10, 22, 9, 33, 21, 50, 41, 60};
        System.out.println("LIS length: " + lengthOfLIS(nums1)); // 5
        
        String word1 = "horse", word2 = "ros";
        System.out.println("Edit distance: " + minDistance(word1, word2)); // 3
        
        int[] coins = {1, 3, 4};
        System.out.println("Min coins for amount 6: " + coinChange(coins, 6)); // 2
        
        // Test Interval DP
        System.out.println("\n🎯 TESTING INTERVAL DP:");
        int[] balloons = {3, 1, 5, 8};
        System.out.println("Max coins from bursting balloons: " + maxCoins(balloons)); // 167
        
        int[] matrixDims = {1, 2, 3, 4};
        System.out.println("Matrix chain multiplication cost: " + matrixChainMultiplication(matrixDims)); // 18
        
        // Test Knapsack
        System.out.println("\n🎯 TESTING KNAPSACK:");
        int[] weights = {1, 3, 4, 5};
        int[] values = {1, 4, 5, 7};
        System.out.println("0/1 Knapsack max value: " + knapsack01(weights, values, 7)); // 9
        
        int[] partition = {1, 5, 11, 5};
        System.out.println("Can partition equal sum: " + canPartition(partition)); // true
        
        int[] targetNums = {1, 1, 1, 1, 1};
        System.out.println("Target sum ways: " + findTargetSumWays(targetNums, 3)); // 5
        
        // Test Prefix Sums
        System.out.println("\n🎯 TESTING PREFIX SUMS:");
        int[] subarrayNums = {1, 1, 1};
        System.out.println("Subarrays with sum 2: " + subarraySum(subarrayNums, 2)); // 2
        
        NumArray numArray = new NumArray(new int[]{-2, 0, 3, -5, 2, -1});
        System.out.println("Range sum [0,2]: " + numArray.sumRange(0, 2)); // 1
        System.out.println("Range sum [2,5]: " + numArray.sumRange(2, 5)); // -1
        
        // Test Counting Problems
        System.out.println("\n🎯 TESTING COUNTING PROBLEMS:");
        System.out.println("Unique paths 3x7: " + uniquePaths(3, 7)); // 28
        
        int[][] obstacles = {{0,0,0},{0,1,0},{0,0,0}};
        System.out.println("Unique paths with obstacles: " + uniquePathsWithObstacles(obstacles)); // 2
        
        String s = "rabbbit", t = "rabbit";
        System.out.println("Distinct subsequences: " + numDistinct(s, t)); // 3
        
        int[] bitCounts = countBits(5);
        System.out.println("Counting bits [0-5]: " + Arrays.toString(bitCounts)); // [0,1,1,2,1,2]
        
        // Test Interval Partitioning
        System.out.println("\n🎯 TESTING INTERVAL PARTITIONING:");
        int[][] intervals1 = {{1,2},{2,3},{3,4},{1,3}};
        System.out.println("Non-overlapping intervals to remove: " + eraseOverlapIntervals(intervals1)); // 1
        
        int[][] intervals2 = {{1,3},{2,6},{8,10},{15,18}};
        int[][] merged = merge(intervals2);
        System.out.println("Merged intervals: " + Arrays.deepToString(merged)); // [[1,6],[8,10],[15,18]]
        
        String partitionStr = "ababcbacadefegdehijhklij";
        List<Integer> partitions = partitionLabels(partitionStr);
        System.out.println("Partition labels: " + partitions); // [9,7,8]
        
        int[][] pairs = {{1,2},{2,3},{3,4}};
        System.out.println("Longest pair chain: " + findLongestChain(pairs)); // 2
        
        System.out.println("\n✅ Advanced Dynamic Programming Patterns Completed!");
        System.out.println("Key Principles:");
        System.out.println("🎯 Optimal Substructure: Break into optimal subproblems");
        System.out.println("🎯 Interval DP: Process ranges with optimal splitting points");
        System.out.println("🎯 Knapsack: Choice-based optimization with capacity constraints");
        System.out.println("🎯 Prefix Sums: Efficient range queries and subarray operations");
        System.out.println("🎯 Counting: Combinatorial problems with state transitions");
        System.out.println("🎯 Interval Partitioning: Greedy scheduling and merging strategies");
    }
} 