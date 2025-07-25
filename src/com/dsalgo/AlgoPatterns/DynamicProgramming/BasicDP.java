package com.dsalgo.AlgoPatterns.DynamicProgramming;

import java.util.*;

/**
 * 🧠 BASIC DYNAMIC PROGRAMMING PATTERN - COMPREHENSIVE IMPLEMENTATION
 * 
 * ============================================================================
 * 📚 PATTERN OVERVIEW
 * ============================================================================
 * 
 * Basic Dynamic Programming Pattern solves problems by breaking them into
 * overlapping subproblems and storing results to avoid redundant computation.
 * This pattern forms the foundation for more complex DP techniques and is
 * essential for optimization problems with optimal substructure.
 * 
 * 🔑 CORE PRINCIPLES:
 * 1. OPTIMAL SUBSTRUCTURE: Optimal solution contains optimal solutions to subproblems
 * 2. OVERLAPPING SUBPROBLEMS: Same subproblems solved multiple times
 * 3. MEMOIZATION: Top-down approach with caching
 * 4. TABULATION: Bottom-up approach with iterative computation
 * 
 * ============================================================================
 * 🎯 APPLICATIONS: Optimization, counting, decision problems, sequence analysis
 * ============================================================================
 */

public class BasicDP {
    
    /**
     * Fibonacci Number - Classic DP Introduction
     * 
     * The Fibonacci numbers, commonly denoted F(n) form a sequence, called the 
     * Fibonacci sequence, such that each number is the sum of the two preceding 
     * ones, starting from 0 and 1.
     * 
     * Strategy: Demonstrate multiple DP approaches
     * Time: O(n), Space: O(n) for memoization, O(1) for optimized
     * 
     * LeetCode: https://leetcode.com/problems/fibonacci-number/
     */
    
    // Approach 1: Recursive with Memoization (Top-down)
    public static int fibMemo(int n) {
        Map<Integer, Integer> memo = new HashMap<>();
        return fibMemoHelper(n, memo);
    }
    
    private static int fibMemoHelper(int n, Map<Integer, Integer> memo) {
        if (n <= 1) return n;
        
        if (memo.containsKey(n)) {
            return memo.get(n);
        }
        
        int result = fibMemoHelper(n - 1, memo) + fibMemoHelper(n - 2, memo);
        memo.put(n, result);
        return result;
    }
    
    // Approach 2: Tabulation (Bottom-up)
    public static int fibTab(int n) {
        if (n <= 1) return n;
        
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        
        return dp[n];
    }
    
    // Approach 3: Space-Optimized (Rolling Variables)
    public static int fibOptimized(int n) {
        if (n <= 1) return n;
        
        int prev2 = 0, prev1 = 1;
        
        for (int i = 2; i <= n; i++) {
            int current = prev1 + prev2;
            prev2 = prev1;
            prev1 = current;
        }
        
        return prev1;
    }
    
    /**
     * Climbing Stairs - Classic DP Problem
     * 
     * You are climbing a staircase. It takes n steps to reach the top.
     * Each time you can either climb 1 or 2 steps. In how many distinct 
     * ways can you climb to the top?
     * 
     * Strategy: DP with state transition dp[i] = dp[i-1] + dp[i-2]
     * Time: O(n), Space: O(1) optimized
     * 
     * LeetCode: https://leetcode.com/problems/climbing-stairs/
     */
    public static int climbStairs(int n) {
        if (n <= 2) return n;
        
        int oneStep = 1, twoStep = 2;
        
        for (int i = 3; i <= n; i++) {
            int current = oneStep + twoStep;
            oneStep = twoStep;
            twoStep = current;
        }
        
        return twoStep;
    }
    
    /**
     * Min Cost Climbing Stairs - Cost Optimization
     * 
     * You are given an integer array cost where cost[i] is the cost of ith step 
     * on a staircase. Once you pay the cost, you can either climb one or two steps.
     * You can either start from the step with index 0, or the step with index 1.
     * Return the minimum cost to reach the top of the floor.
     * 
     * Strategy: DP with minimum cost calculation
     * Time: O(n), Space: O(1)
     * 
     * LeetCode: https://leetcode.com/problems/min-cost-climbing-stairs/
     */
    public static int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        if (n <= 2) return Math.min(cost[0], cost[1]);
        
        int prev2 = cost[0];
        int prev1 = cost[1];
        
        for (int i = 2; i < n; i++) {
            int current = cost[i] + Math.min(prev1, prev2);
            prev2 = prev1;
            prev1 = current;
        }
        
        return Math.min(prev1, prev2);
    }
    
    /**
     * House Robber - Maximum Sum with Constraints
     * 
     * You are a professional robber planning to rob houses along a street. 
     * Each house has a certain amount of money stashed, the only constraint 
     * stopping you from robbing each of them is that adjacent houses have 
     * security systems connected and it will automatically contact the police 
     * if two adjacent houses were broken into on the same night.
     * 
     * Strategy: DP with choice between rob/skip current house
     * Time: O(n), Space: O(1)
     * 
     * LeetCode: https://leetcode.com/problems/house-robber/
     */
    public static int rob(int[] nums) {
        if (nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        
        int robPrev = nums[0];
        int skipPrev = 0;
        
        for (int i = 1; i < nums.length; i++) {
            int robCurrent = skipPrev + nums[i];
            int skipCurrent = Math.max(robPrev, skipPrev);
            
            robPrev = robCurrent;
            skipPrev = skipCurrent;
        }
        
        return Math.max(robPrev, skipPrev);
    }
    
    /**
     * House Robber II - Circular Array Constraint
     * 
     * All houses at this place are arranged in a circle. That means the first 
     * house is the neighbor of the last one. Given an integer array nums 
     * representing the amount of money of each house, return the maximum amount 
     * of money you can rob tonight without alerting the police.
     * 
     * Strategy: Two cases - rob first house or rob last house, take maximum
     * Time: O(n), Space: O(1)
     * 
     * LeetCode: https://leetcode.com/problems/house-robber-ii/
     */
    public static int robCircular(int[] nums) {
        if (nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        if (nums.length == 2) return Math.max(nums[0], nums[1]);
        
        // Case 1: Rob houses 0 to n-2 (include first, exclude last)
        int robFirst = robLinear(nums, 0, nums.length - 2);
        
        // Case 2: Rob houses 1 to n-1 (exclude first, include last)
        int robLast = robLinear(nums, 1, nums.length - 1);
        
        return Math.max(robFirst, robLast);
    }
    
    private static int robLinear(int[] nums, int start, int end) {
        int robPrev = 0, skipPrev = 0;
        
        for (int i = start; i <= end; i++) {
            int robCurrent = skipPrev + nums[i];
            int skipCurrent = Math.max(robPrev, skipPrev);
            
            robPrev = robCurrent;
            skipPrev = skipCurrent;
        }
        
        return Math.max(robPrev, skipPrev);
    }
    
    /**
     * Delete and Earn - Transformed House Robber
     * 
     * You are given an integer array nums. You want to maximize the number of 
     * points you get by performing the following operation any number of times:
     * Pick any nums[i] and delete it to earn nums[i] points. Afterwards, you 
     * must delete every element equal to nums[i] - 1 and nums[i] + 1.
     * 
     * Strategy: Transform to house robber problem using frequency counting
     * Time: O(n + max(nums)), Space: O(max(nums))
     * 
     * LeetCode: https://leetcode.com/problems/delete-and-earn/
     */
    public static int deleteAndEarn(int[] nums) {
        if (nums.length == 0) return 0;
        
        // Find max value to create points array
        int maxVal = Arrays.stream(nums).max().orElse(0);
        int[] points = new int[maxVal + 1];
        
        // Calculate total points for each value
        for (int num : nums) {
            points[num] += num;
        }
        
        // Apply house robber logic
        int rob = 0, skip = 0;
        
        for (int i = 0; i <= maxVal; i++) {
            int robCurrent = skip + points[i];
            int skipCurrent = Math.max(rob, skip);
            
            rob = robCurrent;
            skip = skipCurrent;
        }
        
        return Math.max(rob, skip);
    }
    
    /**
     * Maximum Product Subarray - DP with Positive/Negative Tracking
     * 
     * Given an integer array nums, find a contiguous non-empty subarray within 
     * the array that has the largest product.
     * 
     * Strategy: Track both maximum and minimum products (for negative numbers)
     * Time: O(n), Space: O(1)
     * 
     * LeetCode: https://leetcode.com/problems/maximum-product-subarray/
     */
    public static int maxProduct(int[] nums) {
        if (nums.length == 0) return 0;
        
        int maxProd = nums[0];
        int minProd = nums[0];
        int result = nums[0];
        
        for (int i = 1; i < nums.length; i++) {
            int current = nums[i];
            
            // Calculate new max and min products
            int tempMax = Math.max(current, Math.max(maxProd * current, minProd * current));
            minProd = Math.min(current, Math.min(maxProd * current, minProd * current));
            maxProd = tempMax;
            
            result = Math.max(result, maxProd);
        }
        
        return result;
    }
    
    /**
     * Paint House - Multiple Choice DP
     * 
     * There is a row of n houses, where each house can be painted one of three 
     * colors: red, blue, or green. The cost of painting each house with a certain 
     * color is different. You have to paint all the houses such that no two 
     * adjacent houses have the same color.
     * 
     * Strategy: DP with state for each color choice
     * Time: O(n), Space: O(1)
     * 
     * LeetCode: https://leetcode.com/problems/paint-house/
     */
    public static int minCostPaintHouse(int[][] costs) {
        if (costs.length == 0) return 0;
        
        int red = costs[0][0];
        int blue = costs[0][1];
        int green = costs[0][2];
        
        for (int i = 1; i < costs.length; i++) {
            int newRed = costs[i][0] + Math.min(blue, green);
            int newBlue = costs[i][1] + Math.min(red, green);
            int newGreen = costs[i][2] + Math.min(red, blue);
            
            red = newRed;
            blue = newBlue;
            green = newGreen;
        }
        
        return Math.min(red, Math.min(blue, green));
    }
    
    /**
     * BONUS: Tribonacci Number - Extended Fibonacci
     * 
     * The Tribonacci sequence Tn is defined as follows:
     * T0 = 0, T1 = 1, T2 = 1, and Tn+3 = Tn + Tn+1 + Tn+2 for n >= 0.
     * 
     * Strategy: DP with three previous values
     * Time: O(n), Space: O(1)
     */
    public static int tribonacci(int n) {
        if (n == 0) return 0;
        if (n <= 2) return 1;
        
        int t0 = 0, t1 = 1, t2 = 1;
        
        for (int i = 3; i <= n; i++) {
            int current = t0 + t1 + t2;
            t0 = t1;
            t1 = t2;
            t2 = current;
        }
        
        return t2;
    }
    
    /**
     * BONUS: Jump Game - Reachability DP
     * 
     * You are given an integer array nums. You are initially positioned at the 
     * array's first index, and each element in the array represents your maximum 
     * jump length at that position. Return true if you can reach the last index.
     * 
     * Strategy: Greedy approach or DP tracking farthest reachable position
     * Time: O(n), Space: O(1)
     */
    public static boolean canJump(int[] nums) {
        int maxReach = 0;
        
        for (int i = 0; i < nums.length; i++) {
            if (i > maxReach) return false; // Can't reach current position
            
            maxReach = Math.max(maxReach, i + nums[i]);
            
            if (maxReach >= nums.length - 1) return true;
        }
        
        return maxReach >= nums.length - 1;
    }
    
    /**
     * BONUS: Jump Game II - Minimum Jumps
     * 
     * Given an array of non-negative integers nums, you are initially positioned 
     * at the first index. Each element represents your maximum jump length. Your 
     * goal is to reach the last index in the minimum number of jumps.
     * 
     * Strategy: Greedy approach tracking current and next reachable ranges
     * Time: O(n), Space: O(1)
     */
    public static int jump(int[] nums) {
        if (nums.length <= 1) return 0;
        
        int jumps = 0;
        int currentEnd = 0;
        int farthest = 0;
        
        for (int i = 0; i < nums.length - 1; i++) {
            farthest = Math.max(farthest, i + nums[i]);
            
            if (i == currentEnd) {
                jumps++;
                currentEnd = farthest;
            }
        }
        
        return jumps;
    }
    
    /**
     * BONUS: Decode Ways - String DP
     * 
     * A message containing letters from A-Z can be encoded into numbers using 
     * the mapping 'A' -> "1", 'B' -> "2", ... 'Z' -> "26".
     * Given a string s containing only digits, return the number of ways to decode it.
     * 
     * Strategy: DP considering single digit and two digit decodings
     * Time: O(n), Space: O(1)
     */
    public static int numDecodings(String s) {
        if (s.length() == 0 || s.charAt(0) == '0') return 0;
        
        int prev2 = 1; // dp[i-2]
        int prev1 = 1; // dp[i-1]
        
        for (int i = 1; i < s.length(); i++) {
            int current = 0;
            
            // Single digit decode
            if (s.charAt(i) != '0') {
                current += prev1;
            }
            
            // Two digit decode
            int twoDigit = Integer.parseInt(s.substring(i - 1, i + 1));
            if (twoDigit >= 10 && twoDigit <= 26) {
                current += prev2;
            }
            
            prev2 = prev1;
            prev1 = current;
        }
        
        return prev1;
    }
    
    public static void main(String[] args) {
        System.out.println("🧠 BASIC DYNAMIC PROGRAMMING - COMPREHENSIVE IMPLEMENTATION");
        System.out.println("===========================================================");
        
        // Test Fibonacci implementations
        System.out.println("\n🔢 TESTING FIBONACCI:");
        int n = 10;
        System.out.println("Fibonacci(" + n + "):");
        System.out.println("  Memoization: " + fibMemo(n));
        System.out.println("  Tabulation: " + fibTab(n));
        System.out.println("  Optimized: " + fibOptimized(n));
        
        // Test Climbing Stairs
        System.out.println("\n🪜 TESTING CLIMBING STAIRS:");
        int stairs = 5;
        System.out.println("Ways to climb " + stairs + " stairs: " + climbStairs(stairs));
        
        int[] cost = {10, 15, 20};
        System.out.println("Min cost climbing stairs: " + minCostClimbingStairs(cost));
        
        // Test House Robber variants
        System.out.println("\n🏠 TESTING HOUSE ROBBER:");
        int[] houses = {2, 7, 9, 3, 1};
        System.out.println("Max rob (linear): " + rob(houses));
        
        int[] circularHouses = {2, 3, 2};
        System.out.println("Max rob (circular): " + robCircular(circularHouses));
        
        // Test Delete and Earn
        System.out.println("\n💰 TESTING DELETE AND EARN:");
        int[] nums = {3, 4, 2};
        System.out.println("Max points from delete and earn: " + deleteAndEarn(nums));
        
        // Test Maximum Product Subarray
        System.out.println("\n✖️ TESTING MAXIMUM PRODUCT:");
        int[] products = {2, 3, -2, 4};
        System.out.println("Maximum product subarray: " + maxProduct(products));
        
        // Test Paint House
        System.out.println("\n🎨 TESTING PAINT HOUSE:");
        int[][] costs = {{17,2,17},{16,16,5},{14,3,19}};
        System.out.println("Min cost to paint houses: " + minCostPaintHouse(costs));
        
        // Test Tribonacci
        System.out.println("\n🔢 TESTING TRIBONACCI:");
        int tribN = 7;
        System.out.println("Tribonacci(" + tribN + "): " + tribonacci(tribN));
        
        // Test Jump Game
        System.out.println("\n🦘 TESTING JUMP GAME:");
        int[] jumpNums1 = {2, 3, 1, 1, 4};
        System.out.println("Can jump to end: " + canJump(jumpNums1));
        
        int[] jumpNums2 = {2, 3, 1, 1, 4};
        System.out.println("Minimum jumps: " + jump(jumpNums2));
        
        // Test Decode Ways
        System.out.println("\n🔤 TESTING DECODE WAYS:");
        String decodeStr = "226";
        System.out.println("Number of ways to decode '" + decodeStr + "': " + numDecodings(decodeStr));
        
        System.out.println("\n✅ Basic Dynamic Programming Pattern Completed!");
        System.out.println("Key Principles:");
        System.out.println("1. Identify optimal substructure and overlapping subproblems");
        System.out.println("2. Choose between memoization (top-down) and tabulation (bottom-up)");
        System.out.println("3. Optimize space complexity using rolling variables when possible");
        System.out.println("4. Handle base cases carefully to avoid index errors");
        System.out.println("5. Consider state transitions and dependencies between subproblems");
        System.out.println("6. Apply greedy optimization when DP state space can be reduced");
    }
} 