package com.dsalgo.AlgoPatterns.BTBST;

import java.util.*;

/**
 * 🛤️ BINARY TREE PATH SUM & ROOT TO LEAF PATTERN - PRACTICE PROBLEMS
 * 
 * This class contains comprehensive implementations of binary tree path sum algorithms
 * for finding paths with specific target sums, enumerating all root-to-leaf paths,
 * calculating path-based values, and optimizing over tree paths. These algorithms
 * demonstrate recursive path tracking, backtracking techniques, state management,
 * and various optimization strategies for path-based tree problems.
 * 
 * Pattern Focus: Path tracking, sum calculation, recursive exploration, state management
 * Time Complexity: Generally O(n) to O(n²) depending on path requirements
 * Space Complexity: O(h) for recursion plus O(path_length) for path storage
 */

// Definition for a binary tree node
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

public class TreePathSum {
    
    // ============================================================================
    // 🟢 EASY PROBLEMS - Basic Path Sum Concepts
    // ============================================================================
    
    /**
     * 🟢 EASY: Path Sum
     * 
     * Problem: Check if any root-to-leaf path sums to target
     * LeetCode: https://leetcode.com/problems/path-sum/
     * 
     * Approach: Recursive traversal with target reduction
     * - Reduce target by current node value at each step
     * - Check if leaf node value equals remaining target
     * - Return true if any path satisfies the condition
     * 
     * Time: O(n), Space: O(h) for recursion stack
     */
    public static boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) return false;
        
        // Leaf node check - target must equal current node value
        if (root.left == null && root.right == null) {
            return root.val == targetSum;
        }
        
        // Recursive exploration with updated target
        int remaining = targetSum - root.val;
        return hasPathSum(root.left, remaining) || 
               hasPathSum(root.right, remaining);
    }
    
    /**
     * 🟢 EASY: Sum Root to Leaf Numbers
     * 
     * Problem: Treat each root-to-leaf path as a number and sum all numbers
     * LeetCode: https://leetcode.com/problems/sum-root-to-leaf-numbers/
     * 
     * Approach: DFS with number formation
     * - Build number by digit concatenation (multiply by 10 + current digit)
     * - At leaf nodes, return the formed number
     * - Sum results from all leaf nodes
     * 
     * Time: O(n), Space: O(h) for recursion
     */
    public static int sumNumbers(TreeNode root) {
        return dfsSum(root, 0);
    }
    
    private static int dfsSum(TreeNode node, int currentNumber) {
        if (node == null) return 0;
        
        // Build number: previous digits * 10 + current digit
        currentNumber = currentNumber * 10 + node.val;
        
        // Leaf node - return the formed number
        if (node.left == null && node.right == null) {
            return currentNumber;
        }
        
        // Sum results from both subtrees
        return dfsSum(node.left, currentNumber) + 
               dfsSum(node.right, currentNumber);
    }
    
    // ============================================================================
    // 🟡 MEDIUM PROBLEMS - Intermediate Path Analysis
    // ============================================================================
    
    /**
     * 🟡 MEDIUM: Path Sum II
     * 
     * Problem: Find all root-to-leaf paths that sum to target
     * LeetCode: https://leetcode.com/problems/path-sum-ii/
     * 
     * Approach: DFS with path tracking and backtracking
     * - Maintain current path during traversal
     * - Add path to result when target is reached at leaf
     * - Backtrack by removing last element after recursive calls
     * 
     * Time: O(n²) worst case (copying paths), Space: O(n) for paths
     */
    public static List<List<Integer>> pathSumII(TreeNode root, int targetSum) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> currentPath = new ArrayList<>();
        dfsPathSum(root, targetSum, currentPath, result);
        return result;
    }
    
    private static void dfsPathSum(TreeNode node, int remaining, 
                                 List<Integer> currentPath, 
                                 List<List<Integer>> result) {
        if (node == null) return;
        
        // Add current node to path
        currentPath.add(node.val);
        
        // Check if leaf and target reached
        if (node.left == null && node.right == null && remaining == node.val) {
            result.add(new ArrayList<>(currentPath)); // Copy current path
        }
        
        // Recursive exploration with updated remaining sum
        int newRemaining = remaining - node.val;
        dfsPathSum(node.left, newRemaining, currentPath, result);
        dfsPathSum(node.right, newRemaining, currentPath, result);
        
        // Backtrack - remove current node from path
        currentPath.remove(currentPath.size() - 1);
    }
    
    /**
     * 🟡 MEDIUM: Path Sum III
     * 
     * Problem: Count paths (not necessarily root-to-leaf) that sum to target
     * LeetCode: https://leetcode.com/problems/path-sum-iii/
     * 
     * Approach 1: Brute Force - for each node, count paths starting from it
     * - For each node, start new path sum calculation
     * - Recursively count paths from current node + paths in subtrees
     * 
     * Time: O(n²) worst case, Space: O(h)
     */
    public static int pathSumIII(TreeNode root, int targetSum) {
        if (root == null) return 0;
        
        // Count paths starting from current node + paths in subtrees
        return pathSumFrom(root, targetSum) + 
               pathSumIII(root.left, targetSum) + 
               pathSumIII(root.right, targetSum);
    }
    
    private static int pathSumFrom(TreeNode node, long targetSum) {
        if (node == null) return 0;
        
        int count = 0;
        if (node.val == targetSum) count = 1;
        
        long remaining = targetSum - node.val;
        count += pathSumFrom(node.left, remaining);
        count += pathSumFrom(node.right, remaining);
        
        return count;
    }
    
    /**
     * Path Sum III - Optimized with Prefix Sum
     * 
     * Approach 2: Prefix sum with HashMap for O(n) solution
     * - Use prefix sum technique to avoid redundant calculations
     * - Track frequency of prefix sums in HashMap
     * - For each node, check if (currentSum - target) exists in map
     * 
     * Time: O(n), Space: O(n)
     */
    public static int pathSumOptimized(TreeNode root, int targetSum) {
        Map<Long, Integer> prefixSums = new HashMap<>();
        prefixSums.put(0L, 1); // Base case: sum of 0 with 1 occurrence
        return dfsWithPrefix(root, 0L, targetSum, prefixSums);
    }
    
    private static int dfsWithPrefix(TreeNode node, long currentSum, int targetSum, 
                                   Map<Long, Integer> prefixSums) {
        if (node == null) return 0;
        
        currentSum += node.val;
        
        // Count paths ending at current node
        int pathCount = prefixSums.getOrDefault(currentSum - targetSum, 0);
        
        // Add current sum to map
        prefixSums.put(currentSum, prefixSums.getOrDefault(currentSum, 0) + 1);
        
        // Recurse on children
        pathCount += dfsWithPrefix(node.left, currentSum, targetSum, prefixSums);
        pathCount += dfsWithPrefix(node.right, currentSum, targetSum, prefixSums);
        
        // Backtrack - remove current sum from map
        prefixSums.put(currentSum, prefixSums.get(currentSum) - 1);
        
        return pathCount;
    }
    
    // ============================================================================
    // 🔴 HARD PROBLEMS - Advanced Path Optimization
    // ============================================================================
    
    /**
     * 🔴 HARD: Binary Tree Maximum Path Sum
     * 
     * Problem: Find maximum sum of any path in tree (any node to any node)
     * LeetCode: https://leetcode.com/problems/binary-tree-maximum-path-sum/
     * 
     * Approach: Post-order traversal with path consideration
     * - For each node, consider path passing through it
     * - Path through node = node.val + max_gain_left + max_gain_right
     * - Return max gain continuing from current node (not through it)
     * - Track global maximum across all possible paths
     * 
     * Time: O(n), Space: O(h)
     */
    public static int maxPathSum(TreeNode root) {
        int[] maxSum = {Integer.MIN_VALUE};
        maxPathSumHelper(root, maxSum);
        return maxSum[0];
    }
    
    private static int maxPathSumHelper(TreeNode node, int[] maxSum) {
        if (node == null) return 0;
        
        // Get max gain from left and right subtrees (ignore negative gains)
        int leftGain = Math.max(0, maxPathSumHelper(node.left, maxSum));
        int rightGain = Math.max(0, maxPathSumHelper(node.right, maxSum));
        
        // Calculate max path sum passing through current node
        int currentPathSum = node.val + leftGain + rightGain;
        
        // Update global maximum
        maxSum[0] = Math.max(maxSum[0], currentPathSum);
        
        // Return max gain that can be obtained by continuing from this node
        // (can only continue through one child, not both)
        return node.val + Math.max(leftGain, rightGain);
    }
    
    /**
     * 🔴 HARD: Minimum Path Sum (Root-to-Leaf)
     * 
     * Problem: Find minimum sum among all root-to-leaf paths
     * 
     * Approach: DFS with minimum tracking
     * - At each node, choose path with minimum sum
     * - Handle single-child cases carefully
     * - Return minimum sum from available paths
     * 
     * Time: O(n), Space: O(h)
     */
    public static int minPathSum(TreeNode root) {
        if (root == null) return 0;
        
        // Leaf node
        if (root.left == null && root.right == null) {
            return root.val;
        }
        
        // Calculate minimum from available subtrees
        int leftMin = (root.left != null) ? minPathSum(root.left) : Integer.MAX_VALUE;
        int rightMin = (root.right != null) ? minPathSum(root.right) : Integer.MAX_VALUE;
        
        return root.val + Math.min(leftMin, rightMin);
    }
    
    /**
     * 🔴 HARD: All Root-to-Leaf Paths with Properties
     * 
     * Problem: Find all root-to-leaf paths and their detailed properties
     * 
     * Approach: DFS with comprehensive path information tracking
     * - Track path, sum, length, min/max values
     * - Collect detailed information for each complete path
     * - Return comprehensive path analysis
     * 
     * Time: O(n * path_length), Space: O(n * path_length)
     */
    public static class PathInfo {
        List<Integer> path;
        int sum;
        int length;
        int minValue;
        int maxValue;
        double average;
        
        PathInfo(List<Integer> path, int sum, int length, int minValue, int maxValue) {
            this.path = new ArrayList<>(path);
            this.sum = sum;
            this.length = length;
            this.minValue = minValue;
            this.maxValue = maxValue;
            this.average = (double) sum / length;
        }
        
        @Override
        public String toString() {
            return String.format("Path: %s, Sum: %d, Length: %d, Min: %d, Max: %d, Avg: %.2f", 
                               path, sum, length, minValue, maxValue, average);
        }
    }
    
    public static List<PathInfo> getAllPathInfo(TreeNode root) {
        List<PathInfo> result = new ArrayList<>();
        List<Integer> currentPath = new ArrayList<>();
        dfsPathInfo(root, currentPath, result);
        return result;
    }
    
    private static void dfsPathInfo(TreeNode node, List<Integer> currentPath, 
                                  List<PathInfo> result) {
        if (node == null) return;
        
        currentPath.add(node.val);
        
        // Leaf node - create path info
        if (node.left == null && node.right == null) {
            int sum = currentPath.stream().mapToInt(Integer::intValue).sum();
            int length = currentPath.size();
            int minValue = currentPath.stream().mapToInt(Integer::intValue).min().orElse(0);
            int maxValue = currentPath.stream().mapToInt(Integer::intValue).max().orElse(0);
            
            result.add(new PathInfo(currentPath, sum, length, minValue, maxValue));
        }
        
        // Recurse on children
        dfsPathInfo(node.left, currentPath, result);
        dfsPathInfo(node.right, currentPath, result);
        
        // Backtrack
        currentPath.remove(currentPath.size() - 1);
    }
    
    /**
     * 🔴 HARD: Longest Path with Target Sum
     * 
     * Problem: Find the longest path that sums to target value
     * 
     * Approach: DFS with path length tracking
     * - Track current path length along with sum
     * - Update maximum length when target sum is found
     * - Consider paths starting from any node
     * 
     * Time: O(n²), Space: O(h)
     */
    public static int longestPathWithSum(TreeNode root, int targetSum) {
        int[] maxLength = {0};
        longestPathHelper(root, targetSum, 0, maxLength);
        return maxLength[0];
    }
    
    private static void longestPathHelper(TreeNode node, long targetSum, 
                                        int currentLength, int[] maxLength) {
        if (node == null) return;
        
        // Check if current node completes a path with target sum
        if (targetSum == node.val) {
            maxLength[0] = Math.max(maxLength[0], currentLength + 1);
        }
        
        // Continue path from current node
        long remaining = targetSum - node.val;
        longestPathHelper(node.left, remaining, currentLength + 1, maxLength);
        longestPathHelper(node.right, remaining, currentLength + 1, maxLength);
        
        // Start new paths from children (not extending current path)
        longestPathHelper(node.left, targetSum, 0, maxLength);
        longestPathHelper(node.right, targetSum, 0, maxLength);
    }
    
    /**
     * 🔴 HARD: Count Paths with Sum in Range
     * 
     * Problem: Count paths whose sum falls within a given range
     * 
     * Approach: DFS with range checking
     * - Track all possible path sums from each node
     * - Count paths that fall within specified range
     * - Use efficient pruning to avoid unnecessary exploration
     * 
     * Time: O(n²), Space: O(h)
     */
    public static int countPathsInRange(TreeNode root, int minSum, int maxSum) {
        return countPathsInRangeHelper(root, minSum, maxSum) +
               (root != null ? countPathsInRange(root.left, minSum, maxSum) +
                              countPathsInRange(root.right, minSum, maxSum) : 0);
    }
    
    private static int countPathsInRangeHelper(TreeNode node, int minSum, int maxSum) {
        if (node == null) return 0;
        
        int count = 0;
        
        // Check if single node is in range
        if (node.val >= minSum && node.val <= maxSum) {
            count++;
        }
        
        // Continue paths from current node
        count += countPathsInRangeFromNode(node.left, minSum - node.val, maxSum - node.val);
        count += countPathsInRangeFromNode(node.right, minSum - node.val, maxSum - node.val);
        
        return count;
    }
    
    private static int countPathsInRangeFromNode(TreeNode node, int minSum, int maxSum) {
        if (node == null) return 0;
        
        int count = 0;
        
        if (node.val >= minSum && node.val <= maxSum) {
            count++;
        }
        
        // Pruning: only continue if we can potentially reach the range
        if (node.val <= maxSum) {
            count += countPathsInRangeFromNode(node.left, minSum - node.val, maxSum - node.val);
            count += countPathsInRangeFromNode(node.right, minSum - node.val, maxSum - node.val);
        }
        
        return count;
    }
    
    /**
     * 🔴 HARD: Maximum Product Path (Root-to-Leaf)
     * 
     * Problem: Find root-to-leaf path with maximum product
     * 
     * Approach: DFS with product tracking
     * - Handle positive and negative numbers carefully
     * - Track both maximum and minimum products (negative numbers)
     * - Return maximum product among all root-to-leaf paths
     * 
     * Time: O(n), Space: O(h)
     */
    public static long maxProductPath(TreeNode root) {
        if (root == null) return 0;
        
        long[] result = {Long.MIN_VALUE};
        maxProductHelper(root, 1L, result);
        return result[0];
    }
    
    private static void maxProductHelper(TreeNode node, long currentProduct, long[] result) {
        if (node == null) return;
        
        currentProduct *= node.val;
        
        // Leaf node
        if (node.left == null && node.right == null) {
            result[0] = Math.max(result[0], currentProduct);
            return;
        }
        
        maxProductHelper(node.left, currentProduct, result);
        maxProductHelper(node.right, currentProduct, result);
    }
    
    // ============================================================================
    // 🧪 TESTING AND DEMONSTRATION
    // ============================================================================
    
    // Helper method to create test trees
    private static TreeNode createSampleTree() {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(11);
        root.left.left.left = new TreeNode(7);
        root.left.left.right = new TreeNode(2);
        root.right.left = new TreeNode(13);
        root.right.right = new TreeNode(4);
        root.right.right.right = new TreeNode(1);
        return root;
    }
    
    private static TreeNode createNumberTree() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        return root;
    }
    
    private static TreeNode createMaxPathTree() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        return root;
    }
    
    public static void main(String[] args) {
        System.out.println("🛤️ BINARY TREE PATH SUM & ROOT TO LEAF PATTERN - PRACTICE PROBLEMS");
        System.out.println("===================================================================");
        
        // Test Basic Problems
        System.out.println("\n🟢 BASIC PATH SUM PROBLEMS:");
        
        // Path Sum
        TreeNode sampleTree = createSampleTree();
        boolean hasPath22 = hasPathSum(sampleTree, 22);
        System.out.println("Has path sum 22: " + hasPath22); // true
        
        boolean hasPath21 = hasPathSum(sampleTree, 21);
        System.out.println("Has path sum 21: " + hasPath21); // false
        
        // Sum Root to Leaf Numbers
        TreeNode numberTree = createNumberTree();
        int sumNumbers = sumNumbers(numberTree);
        System.out.println("Sum of root-to-leaf numbers: " + sumNumbers); // 25 (12 + 13)
        
        // Test Intermediate Problems
        System.out.println("\n🟡 INTERMEDIATE PATH SUM PROBLEMS:");
        
        // Path Sum II
        List<List<Integer>> allPaths = pathSumII(sampleTree, 22);
        System.out.println("All paths with sum 22:");
        for (List<Integer> path : allPaths) {
            System.out.println("  " + path);
        }
        
        // Path Sum III
        int pathCount = pathSumIII(sampleTree, 8);
        System.out.println("Path count with sum 8: " + pathCount);
        
        int pathCountOptimized = pathSumOptimized(sampleTree, 8);
        System.out.println("Path count with sum 8 (optimized): " + pathCountOptimized);
        
        // Test Advanced Problems
        System.out.println("\n🔴 ADVANCED PATH SUM PROBLEMS:");
        
        // Binary Tree Maximum Path Sum
        TreeNode maxPathTree = createMaxPathTree();
        int maxSum = maxPathSum(maxPathTree);
        System.out.println("Maximum path sum: " + maxSum); // 6 (2 + 1 + 3)
        
        // Minimum Path Sum
        int minSum = minPathSum(sampleTree);
        System.out.println("Minimum path sum: " + minSum);
        
        // All Path Info
        List<PathInfo> pathInfos = getAllPathInfo(numberTree);
        System.out.println("Detailed path information:");
        for (PathInfo info : pathInfos) {
            System.out.println("  " + info);
        }
        
        // Longest Path with Sum
        int longestPath = longestPathWithSum(sampleTree, 4);
        System.out.println("Longest path with sum 4: " + longestPath);
        
        // Count Paths in Range
        int pathsInRange = countPathsInRange(sampleTree, 5, 15);
        System.out.println("Paths with sum in range [5, 15]: " + pathsInRange);
        
        // Maximum Product Path
        long maxProduct = maxProductPath(numberTree);
        System.out.println("Maximum product path: " + maxProduct);
        
        System.out.println("\n✅ Key Binary Tree Path Sum Principles:");
        System.out.println("1. Track path state and cumulative sums during traversal");
        System.out.println("2. Use backtracking for path enumeration problems");
        System.out.println("3. Handle leaf node detection carefully (both children null)");
        System.out.println("4. Consider different path definitions (root-to-leaf vs any-to-any)");
        System.out.println("5. Use prefix sum technique for optimization when applicable");
        System.out.println("6. Apply post-order traversal for path optimization problems");
        System.out.println("7. Handle edge cases like empty trees and integer overflow");
        System.out.println("8. Choose appropriate data structures for path representation");
    }
} 