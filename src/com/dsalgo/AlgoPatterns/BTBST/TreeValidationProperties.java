package com.dsalgo.AlgoPatterns.BTBST;

import java.util.*;

/**
 * ✅ BINARY TREE VALIDATION & PROPERTIES PATTERN - PRACTICE PROBLEMS
 * 
 * This class contains comprehensive implementations of binary tree validation and
 * property analysis algorithms for verifying tree characteristics, computing structural
 * metrics, and validating specific constraints. These algorithms demonstrate systematic
 * property verification, structural analysis techniques, constraint satisfaction methods,
 * and comprehensive tree characterization for various validation and measurement requirements.
 * 
 * Pattern Focus: Property validation, structural analysis, constraint checking, metrics computation
 * Time Complexity: Generally O(n) for single-pass validation, O(log n) for optimized tree operations
 * Space Complexity: O(h) for recursion where h is tree height
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

public class TreeValidationProperties {
    
    // ============================================================================
    // 🟢 EASY PROBLEMS - Basic Property Validation
    // ============================================================================
    
    /**
     * 🟢 EASY: Balanced Binary Tree
     * 
     * Problem: Check if binary tree is height-balanced
     * LeetCode: https://leetcode.com/problems/balanced-binary-tree/
     * 
     * Approach: Check height difference at each node during traversal
     * - A tree is balanced if height difference between left and right subtrees ≤ 1
     * - Recursively check balance condition for all nodes
     * - Use -1 as sentinel value to indicate unbalanced state
     * - Combine height calculation with balance checking for efficiency
     * 
     * Time: O(n), Space: O(h) for recursion
     */
    public static boolean isBalanced(TreeNode root) {
        return checkHeight(root) != -1;
    }
    
    private static int checkHeight(TreeNode node) {
        if (node == null) return 0;
        
        // Check left subtree height and balance
        int leftHeight = checkHeight(node.left);
        if (leftHeight == -1) return -1; // Left subtree is unbalanced
        
        // Check right subtree height and balance
        int rightHeight = checkHeight(node.right);
        if (rightHeight == -1) return -1; // Right subtree is unbalanced
        
        // Check balance condition for current node
        if (Math.abs(leftHeight - rightHeight) > 1) {
            return -1; // Current node violates balance condition
        }
        
        // Return height of current subtree
        return 1 + Math.max(leftHeight, rightHeight);
    }
    
    /**
     * 🟢 EASY: Maximum Depth of Binary Tree
     * 
     * Problem: Find maximum depth (height) of binary tree
     * 
     * Approach: Recursive depth computation
     * - Base case: null node has depth 0
     * - Recursive case: 1 + max depth of left and right subtrees
     * - Simple and elegant recursive solution
     * 
     * Time: O(n), Space: O(h) for recursion
     */
    public static int maxDepth(TreeNode root) {
        if (root == null) return 0;
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }
    
    // ============================================================================
    // 🟡 MEDIUM PROBLEMS - Intermediate Property Analysis
    // ============================================================================
    
    /**
     * 🟡 MEDIUM: Diameter of Binary Tree
     * 
     * Problem: Find length of longest path between any two nodes
     * LeetCode: https://leetcode.com/problems/diameter-of-binary-tree/
     * 
     * Approach: Compute diameter while calculating height
     * - Diameter through any node = left_height + right_height
     * - Track maximum diameter found during traversal
     * - Combine height calculation with diameter computation
     * - Single-pass algorithm for optimal efficiency
     * 
     * Time: O(n), Space: O(h) for recursion
     */
    public static int diameterOfBinaryTree(TreeNode root) {
        int[] maxDiameter = {0};
        calculateHeight(root, maxDiameter);
        return maxDiameter[0];
    }
    
    private static int calculateHeight(TreeNode node, int[] maxDiameter) {
        if (node == null) return 0;
        
        // Calculate heights of left and right subtrees
        int leftHeight = calculateHeight(node.left, maxDiameter);
        int rightHeight = calculateHeight(node.right, maxDiameter);
        
        // Diameter through current node
        int currentDiameter = leftHeight + rightHeight;
        maxDiameter[0] = Math.max(maxDiameter[0], currentDiameter);
        
        // Return height of current subtree
        return 1 + Math.max(leftHeight, rightHeight);
    }
    
    /**
     * 🟡 MEDIUM: Count Complete Tree Nodes
     * 
     * Problem: Count nodes in complete binary tree efficiently
     * LeetCode: https://leetcode.com/problems/count-complete-tree-nodes/
     * 
     * Approach: Leverage complete tree property for optimization
     * - If left and right depths are equal, tree is perfect: count = 2^depth - 1
     * - Otherwise, recursively count left and right subtrees
     * - Use complete tree property to avoid full traversal when possible
     * - Achieves better than O(n) complexity for complete trees
     * 
     * Time: O(log²n), Space: O(log n) for recursion
     */
    public static int countNodes(TreeNode root) {
        if (root == null) return 0;
        
        int leftDepth = getLeftDepth(root);
        int rightDepth = getRightDepth(root);
        
        if (leftDepth == rightDepth) {
            // Perfect binary tree - use formula 2^depth - 1
            return (1 << leftDepth) - 1;
        }
        
        // Not perfect - count recursively
        return 1 + countNodes(root.left) + countNodes(root.right);
    }
    
    private static int getLeftDepth(TreeNode node) {
        int depth = 0;
        while (node != null) {
            node = node.left;
            depth++;
        }
        return depth;
    }
    
    private static int getRightDepth(TreeNode node) {
        int depth = 0;
        while (node != null) {
            node = node.right;
            depth++;
        }
        return depth;
    }
    
    /**
     * 🟡 MEDIUM: Binary Tree Longest Consecutive Sequence
     * 
     * Problem: Find length of longest consecutive sequence path
     * LeetCode: https://leetcode.com/problems/binary-tree-longest-consecutive-sequence/
     * 
     * Approach: Track consecutive sequence length during traversal
     * - Check if current node continues sequence from parent
     * - Track current sequence length and maximum found so far
     * - Reset sequence when consecutive property breaks
     * - Consider sequences starting from any node
     * 
     * Time: O(n), Space: O(h) for recursion
     */
    public static int longestConsecutive(TreeNode root) {
        if (root == null) return 0;
        return longestConsecutiveHelper(root, null, 0);
    }
    
    private static int longestConsecutiveHelper(TreeNode node, TreeNode parent, int length) {
        if (node == null) return length;
        
        // Check if current node continues sequence from parent
        int currentLength = (parent != null && node.val == parent.val + 1) ? length + 1 : 1;
        
        // Recursively check left and right subtrees
        int leftMax = longestConsecutiveHelper(node.left, node, currentLength);
        int rightMax = longestConsecutiveHelper(node.right, node, currentLength);
        
        // Return maximum of current length and subtree results
        return Math.max(currentLength, Math.max(leftMax, rightMax));
    }
    
    // ============================================================================
    // 🔴 HARD PROBLEMS - Advanced Property Analysis
    // ============================================================================
    
    /**
     * 🔴 HARD: Binary Tree Maximum Width
     * 
     * Problem: Find maximum width of binary tree at any level
     * 
     * Approach: Level-order traversal with position tracking
     * - Assign position indices to nodes (left child = 2*pos, right = 2*pos+1)
     * - Track leftmost and rightmost positions at each level
     * - Width = rightmost_position - leftmost_position + 1
     * - Handle potential integer overflow with position normalization
     * 
     * Time: O(n), Space: O(w) where w is maximum width
     */
    public static int widthOfBinaryTree(TreeNode root) {
        if (root == null) return 0;
        
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        Queue<Integer> posQueue = new LinkedList<>();
        
        nodeQueue.offer(root);
        posQueue.offer(0);
        int maxWidth = 1;
        
        while (!nodeQueue.isEmpty()) {
            int size = nodeQueue.size();
            int start = 0, end = 0;
            
            for (int i = 0; i < size; i++) {
                TreeNode node = nodeQueue.poll();
                int pos = posQueue.poll();
                
                if (i == 0) start = pos; // First node position
                if (i == size - 1) end = pos; // Last node position
                
                // Add children with calculated positions
                if (node.left != null) {
                    nodeQueue.offer(node.left);
                    posQueue.offer(2 * pos);
                }
                
                if (node.right != null) {
                    nodeQueue.offer(node.right);
                    posQueue.offer(2 * pos + 1);
                }
            }
            
            maxWidth = Math.max(maxWidth, end - start + 1);
        }
        
        return maxWidth;
    }
    
    /**
     * 🔴 HARD: Complete Binary Tree Validation
     * 
     * Problem: Check if binary tree is complete
     * 
     * Approach: Level-order traversal with null tracking
     * - Complete tree: all levels filled except possibly last, last level filled left-to-right
     * - Use level-order traversal adding null children
     * - Once null encountered, all subsequent nodes must be null
     * - Violation indicates incomplete tree
     * 
     * Time: O(n), Space: O(w) where w is maximum width
     */
    public static boolean isCompleteTree(TreeNode root) {
        if (root == null) return true;
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean nullSeen = false;
        
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            
            if (node == null) {
                nullSeen = true;
            } else {
                // If we've seen null and encounter non-null, tree is not complete
                if (nullSeen) return false;
                
                // Add children (including null children)
                queue.offer(node.left);
                queue.offer(node.right);
            }
        }
        
        return true;
    }
    
    /**
     * 🔴 HARD: Longest Univalue Path
     * 
     * Problem: Find longest path where all nodes have same value
     * 
     * Approach: Post-order traversal with path length tracking
     * - For each node, compute longest univalue path in left and right subtrees
     * - Path through current node = left_path + right_path (if values match)
     * - Return longest single-direction path for parent consideration
     * - Track global maximum throughout traversal
     * 
     * Time: O(n), Space: O(h) for recursion
     */
    public static int longestUnivaluePath(TreeNode root) {
        int[] maxLength = {0};
        longestUnivalueHelper(root, maxLength);
        return maxLength[0];
    }
    
    private static int longestUnivalueHelper(TreeNode node, int[] maxLength) {
        if (node == null) return 0;
        
        // Get path lengths from left and right subtrees
        int left = longestUnivalueHelper(node.left, maxLength);
        int right = longestUnivalueHelper(node.right, maxLength);
        
        int leftPath = 0, rightPath = 0;
        
        // Check if left child has same value
        if (node.left != null && node.left.val == node.val) {
            leftPath = left + 1;
        }
        
        // Check if right child has same value
        if (node.right != null && node.right.val == node.val) {
            rightPath = right + 1;
        }
        
        // Update global maximum (path through current node)
        maxLength[0] = Math.max(maxLength[0], leftPath + rightPath);
        
        // Return longest single-direction path
        return Math.max(leftPath, rightPath);
    }
    
    /**
     * 🔴 HARD: Count Univalue Subtrees
     * 
     * Problem: Count subtrees where all nodes have same value
     * 
     * Approach: Post-order traversal with subtree validation
     * - Check if subtree rooted at current node is univalue
     * - Subtree is univalue if all nodes have same value as root
     * - Use post-order to validate children before parent
     * - Count valid univalue subtrees during traversal
     * 
     * Time: O(n), Space: O(h) for recursion
     */
    public static int countUnivalSubtrees(TreeNode root) {
        int[] count = {0};
        isUnivalSubtree(root, count);
        return count[0];
    }
    
    private static boolean isUnivalSubtree(TreeNode node, int[] count) {
        if (node == null) return true;
        
        // Check if left and right subtrees are univalue
        boolean left = isUnivalSubtree(node.left, count);
        boolean right = isUnivalSubtree(node.right, count);
        
        // Check if current subtree is univalue
        if (left && right) {
            // Check left child value
            if (node.left != null && node.left.val != node.val) {
                return false;
            }
            
            // Check right child value
            if (node.right != null && node.right.val != node.val) {
                return false;
            }
            
            // Current subtree is univalue
            count[0]++;
            return true;
        }
        
        return false; // Current subtree is not univalue
    }
    
    /**
     * 🔴 HARD: Tree Isomorphism Check
     * 
     * Problem: Check if two trees are isomorphic (same structure)
     * 
     * Approach: Recursive structural comparison with flipping
     * - Trees are isomorphic if they have same structure
     * - Allow for left-right child swapping at any level
     * - Compare both normal and flipped configurations
     * - Use recursive approach to check all possibilities
     * 
     * Time: O(n), Space: O(h) for recursion
     */
    public static boolean areIsomorphic(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) return true;
        if (root1 == null || root2 == null) return false;
        if (root1.val != root2.val) return false;
        
        // Check both normal and flipped configurations
        return (areIsomorphic(root1.left, root2.left) && areIsomorphic(root1.right, root2.right)) ||
               (areIsomorphic(root1.left, root2.right) && areIsomorphic(root1.right, root2.left));
    }
    
    /**
     * 🔴 HARD: Tree Statistics Computation
     * 
     * Problem: Compute comprehensive statistics for tree values
     * 
     * Approach: Single traversal with running calculations
     * - Track count, sum, min, max during single tree traversal
     * - Calculate mean, variance, and other statistics
     * - Efficient single-pass algorithm for all statistics
     * - Handle empty tree edge case appropriately
     * 
     * Time: O(n), Space: O(h) for recursion
     */
    public static class TreeStatistics {
        public int count;
        public int sum;
        public int min;
        public int max;
        public double mean;
        
        public TreeStatistics(int count, int sum, int min, int max, double mean) {
            this.count = count;
            this.sum = sum;
            this.min = min;
            this.max = max;
            this.mean = mean;
        }
        
        @Override
        public String toString() {
            return String.format("Count: %d, Sum: %d, Min: %d, Max: %d, Mean: %.2f",
                               count, sum, min, max, mean);
        }
    }
    
    public static TreeStatistics computeTreeStatistics(TreeNode root) {
        if (root == null) return new TreeStatistics(0, 0, 0, 0, 0.0);
        
        int[] stats = {0, 0, Integer.MAX_VALUE, Integer.MIN_VALUE}; // count, sum, min, max
        computeStatsHelper(root, stats);
        
        double mean = stats[0] > 0 ? (double) stats[1] / stats[0] : 0.0;
        return new TreeStatistics(stats[0], stats[1], stats[2], stats[3], mean);
    }
    
    private static void computeStatsHelper(TreeNode node, int[] stats) {
        if (node == null) return;
        
        stats[0]++; // count
        stats[1] += node.val; // sum
        stats[2] = Math.min(stats[2], node.val); // min
        stats[3] = Math.max(stats[3], node.val); // max
        
        computeStatsHelper(node.left, stats);
        computeStatsHelper(node.right, stats);
    }
    
    // ============================================================================
    // 🧪 TESTING AND DEMONSTRATION
    // ============================================================================
    
    // Helper methods for creating test trees
    private static TreeNode createBalancedTree() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        return root;
    }
    
    private static TreeNode createUnbalancedTree() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.left.left = new TreeNode(4);
        return root;
    }
    
    private static TreeNode createCompleteTree() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        return root;
    }
    
    private static TreeNode createConsecutiveTree() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        return root;
    }
    
    private static TreeNode createUnivalueTree() {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(5);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(5);
        root.left.right = new TreeNode(5);
        return root;
    }
    
    public static void main(String[] args) {
        System.out.println("✅ BINARY TREE VALIDATION & PROPERTIES PATTERN - PRACTICE PROBLEMS");
        System.out.println("===================================================================");
        
        // Test Basic Problems
        System.out.println("\n🟢 BASIC PROPERTY VALIDATION PROBLEMS:");
        
        // Test Balanced Tree
        TreeNode balancedTree = createBalancedTree();
        TreeNode unbalancedTree = createUnbalancedTree();
        
        boolean isBalanced1 = isBalanced(balancedTree);
        boolean isBalanced2 = isBalanced(unbalancedTree);
        System.out.println("Balanced tree is balanced: " + isBalanced1); // true
        System.out.println("Unbalanced tree is balanced: " + isBalanced2); // false
        
        // Test Maximum Depth
        int maxDepth1 = maxDepth(balancedTree);
        int maxDepth2 = maxDepth(unbalancedTree);
        System.out.println("Balanced tree max depth: " + maxDepth1); // 3
        System.out.println("Unbalanced tree max depth: " + maxDepth2); // 4
        
        // Test Intermediate Problems
        System.out.println("\n🟡 INTERMEDIATE PROPERTY ANALYSIS PROBLEMS:");
        
        // Test Diameter
        int diameter1 = diameterOfBinaryTree(balancedTree);
        int diameter2 = diameterOfBinaryTree(unbalancedTree);
        System.out.println("Balanced tree diameter: " + diameter1); // 3
        System.out.println("Unbalanced tree diameter: " + diameter2); // 3
        
        // Test Complete Tree Node Count
        TreeNode completeTree = createCompleteTree();
        int nodeCount = countNodes(completeTree);
        System.out.println("Complete tree node count: " + nodeCount); // 6
        
        // Test Longest Consecutive Sequence
        TreeNode consecutiveTree = createConsecutiveTree();
        int longestConsec = longestConsecutive(consecutiveTree);
        System.out.println("Longest consecutive sequence: " + longestConsec); // 2
        
        // Test Advanced Problems
        System.out.println("\n🔴 ADVANCED PROPERTY ANALYSIS PROBLEMS:");
        
        // Test Maximum Width
        int maxWidth = widthOfBinaryTree(balancedTree);
        System.out.println("Maximum width: " + maxWidth); // 2
        
        // Test Complete Tree Validation
        boolean isComplete = isCompleteTree(completeTree);
        System.out.println("Is complete tree: " + isComplete); // true
        
        // Test Longest Univalue Path
        TreeNode univalueTree = createUnivalueTree();
        int longestUnivalue = longestUnivaluePath(univalueTree);
        System.out.println("Longest univalue path: " + longestUnivalue); // 4
        
        // Test Count Univalue Subtrees
        int univalueCount = countUnivalSubtrees(univalueTree);
        System.out.println("Univalue subtree count: " + univalueCount); // 5
        
        // Test Tree Isomorphism
        TreeNode tree1 = createBalancedTree();
        TreeNode tree2 = createBalancedTree();
        boolean isIsomorphic = areIsomorphic(tree1, tree2);
        System.out.println("Trees are isomorphic: " + isIsomorphic); // true
        
        // Test Tree Statistics
        TreeStatistics stats = computeTreeStatistics(balancedTree);
        System.out.println("Tree statistics: " + stats);
        
        System.out.println("\n✅ Key Binary Tree Validation & Properties Principles:");
        System.out.println("1. Combine multiple property checks in single traversal for efficiency");
        System.out.println("2. Use sentinel values (-1) for early termination in validation");
        System.out.println("3. Leverage tree type properties (complete, balanced) for optimization");
        System.out.println("4. Handle edge cases systematically (null trees, single nodes)");
        System.out.println("5. Design algorithms that validate global properties, not just local");
        System.out.println("6. Use post-order traversal for bottom-up property aggregation");
        System.out.println("7. Apply appropriate traversal methods for different property types");
        System.out.println("8. Optimize with early termination when property violations detected");
    }
} 