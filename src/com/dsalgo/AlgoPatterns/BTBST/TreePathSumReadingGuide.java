package com.dsalgo.AlgoPatterns.BTBST;

import java.util.*;

/**
 * 🛤️ BINARY TREE PATH SUM & ROOT TO LEAF PATTERN - COMPREHENSIVE READING GUIDE
 * 
 * ============================================================================
 * 📚 WHAT IS BINARY TREE PATH SUM & ROOT TO LEAF PATTERN?
 * ============================================================================
 * 
 * Binary Tree Path Sum & Root to Leaf Pattern involves traversing binary trees
 * while tracking paths and their cumulative sums. This pattern encompasses finding
 * paths that meet specific sum criteria, enumerating all root-to-leaf paths,
 * calculating path-based values, and solving optimization problems related to
 * tree paths. It's fundamental for path analysis, tree-based dynamic programming,
 * and problems requiring path state maintenance during traversal.
 * 
 * The pattern requires understanding recursive path tracking, backtracking for
 * path exploration, state management during traversal, and optimization techniques
 * for path-based calculations. It's essential for many tree-based algorithms
 * that need to consider complete paths rather than individual nodes.
 * 
 * 🔑 CORE PRINCIPLES:
 * 1. PATH TRACKING: Maintain current path state during tree traversal
 * 2. SUM CALCULATION: Track cumulative sums along paths efficiently
 * 3. RECURSIVE EXPLORATION: Explore all possible paths through recursive traversal
 * 4. STATE MANAGEMENT: Handle path state and backtracking correctly
 * 
 * 🛤️ BINARY TREE PATH SUM METAPHOR:
 * Think of path sum problems as "route planning and cost analysis":
 * - Tree paths: different routes through a network or map
 * - Path sums: total cost or distance of each route
 * - Target sum: desired budget or distance constraint
 * - Path exploration: systematic route evaluation and optimization
 * 
 * ============================================================================
 * 🎯 WHEN TO USE BINARY TREE PATH SUM & ROOT TO LEAF PATTERN
 * ============================================================================
 * 
 * ✅ PERFECT FOR:
 * - Finding paths in trees that sum to a specific target value
 * - Enumerating all root-to-leaf paths and their properties
 * - Calculating path-based values (sums, products, concatenations)
 * - Optimizing over all possible paths in a tree (maximum/minimum path sums)
 * - Tree-based dynamic programming with path dependencies
 * - Problems requiring complete path analysis rather than node-by-node processing
 * 
 * 🔍 LOOK FOR THESE PHRASES:
 * - "Path sum equals target"
 * - "Root to leaf paths"
 * - "All paths with sum X"
 * - "Maximum/minimum path sum"
 * - "Sum of all root-to-leaf numbers"
 * - "Path that satisfies condition"
 * 
 * 🚩 RED FLAGS (Consider Other Approaches):
 * - Simple tree traversal without path tracking (use traversal patterns)
 * - Node-based operations without path context (use tree manipulation patterns)
 * - Tree structure analysis (use structural patterns)
 * - Single-node properties (use basic tree algorithms)
 * 
 * ============================================================================
 * 🔄 BINARY TREE PATH SUM & ROOT TO LEAF PATTERN VARIATIONS
 * ============================================================================
 * 
 * 1️⃣ TARGET SUM PATH FINDING
 * - Existence check: determine if any path sums to target
 * - Path enumeration: find all paths that sum to target
 * - Path counting: count paths with specific sum properties
 * - Multiple targets: handle multiple target values simultaneously
 * 
 * 2️⃣ ROOT-TO-LEAF PATH ANALYSIS
 * - Complete path enumeration: list all root-to-leaf paths
 * - Path property calculation: compute properties of each path
 * - Path aggregation: combine results from all paths
 * - Path filtering: select paths based on criteria
 * 
 * 3️⃣ PATH-BASED VALUE CALCULATION
 * - Numeric path values: treat paths as numbers (concatenation)
 * - Path sum computation: calculate various types of sums
 * - Path product/operation: apply operations along paths
 * - Conditional path values: compute values based on path properties
 * 
 * 4️⃣ OPTIMIZATION OVER PATHS
 * - Maximum path sum: find path with largest sum
 * - Minimum path sum: find path with smallest sum
 * - Path optimization with constraints: optimize under specific conditions
 * - Multi-objective path optimization: balance multiple criteria
 * 
 * 5️⃣ FLEXIBLE PATH DEFINITIONS
 * - Any-to-any paths: paths between any two nodes (not just root-to-leaf)
 * - Subpath analysis: analyze portions of paths
 * - Path with specific length: paths of exact or bounded length
 * - Conditional path segments: paths satisfying local conditions
 * 
 * 6️⃣ ADVANCED PATH ALGORITHMS
 * - Path memoization: cache path results for efficiency
 * - Path state compression: efficient path representation
 * - Parallel path exploration: concurrent path analysis
 * - Incremental path updates: handle dynamic tree changes
 * 
 * ============================================================================
 * 🧠 CORE CONCEPTS AND ALGORITHMS
 * ============================================================================
 * 
 * 🔹 BASIC PATH SUM CHECK:
 * ```
 * def hasPathSum(root, target):
 *     if not root:
 *         return False
 *     
 *     # Leaf node check
 *     if not root.left and not root.right:
 *         return root.val == target
 *     
 *     # Recursive exploration with updated target
 *     remaining = target - root.val
 *     return hasPathSum(root.left, remaining) or hasPathSum(root.right, remaining)
 * ```
 * 
 * 🔹 PATH ENUMERATION TEMPLATE:
 * ```
 * def findAllPaths(root, target):
 *     result = []
 *     current_path = []
 *     
 *     def dfs(node, remaining):
 *         if not node:
 *             return
 *         
 *         current_path.append(node.val)
 *         
 *         # Check if leaf and target reached
 *         if not node.left and not node.right and remaining == node.val:
 *             result.append(current_path[:])  # Copy current path
 *         
 *         # Recursive exploration
 *         new_remaining = remaining - node.val
 *         dfs(node.left, new_remaining)
 *         dfs(node.right, new_remaining)
 *         
 *         current_path.pop()  # Backtrack
 *     
 *     dfs(root, target)
 *     return result
 * ```
 * 
 * 🔹 PATH-BASED VALUE CALCULATION:
 * ```
 * def sumRootToLeafNumbers(root):
 *     def dfs(node, current_number):
 *         if not node:
 *             return 0
 *         
 *         current_number = current_number * 10 + node.val
 *         
 *         # Leaf node
 *         if not node.left and not node.right:
 *             return current_number
 *         
 *         # Sum from both subtrees
 *         return dfs(node.left, current_number) + dfs(node.right, current_number)
 *     
 *     return dfs(root, 0)
 * ```
 * 
 * 🔹 MAXIMUM PATH SUM (ANY-TO-ANY):
 * ```
 * def maxPathSum(root):
 *     max_sum = float('-inf')
 *     
 *     def dfs(node):
 *         nonlocal max_sum
 *         if not node:
 *             return 0
 *         
 *         # Get max gain from left and right subtrees
 *         left_gain = max(0, dfs(node.left))
 *         right_gain = max(0, dfs(node.right))
 *         
 *         # Current path through this node
 *         current_max = node.val + left_gain + right_gain
 *         max_sum = max(max_sum, current_max)
 *         
 *         # Return max gain continuing from this node
 *         return node.val + max(left_gain, right_gain)
 *     
 *     dfs(root)
 *     return max_sum
 * ```
 * 
 * ============================================================================
 * 📋 PROBLEM-SOLVING FRAMEWORK
 * ============================================================================
 * 
 * STEP 1: IDENTIFY PATH REQUIREMENTS
 * - What type of paths are we analyzing? (root-to-leaf, any-to-any, specific)
 * - What properties of paths matter? (sum, length, values, conditions)
 * - What is the target or optimization criteria?
 * 
 * STEP 2: CHOOSE PATH TRACKING STRATEGY
 * - How to represent current path state?
 * - What information needs to be tracked during traversal?
 * - How to handle backtracking efficiently?
 * 
 * STEP 3: DESIGN RECURSIVE EXPLORATION
 * - What are the base cases for recursion?
 * - How to update path state at each node?
 * - How to combine results from subtrees?
 * 
 * STEP 4: IMPLEMENT STATE MANAGEMENT
 * - How to maintain path state during traversal?
 * - When and how to perform backtracking?
 * - How to handle multiple path constraints?
 * 
 * STEP 5: OPTIMIZE AND VALIDATE
 * - Can path exploration be optimized or pruned?
 * - How to handle edge cases and invalid inputs?
 * - What are the time and space complexity trade-offs?
 * 
 * ============================================================================
 * 🎨 BINARY TREE PATH SUM & ROOT TO LEAF TEMPLATES
 * ============================================================================
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

public class TreePathSumReadingGuide {
    
    /**
     * 🛤️ BASIC PATH SUM TEMPLATES
     */
    public static class BasicPathSumTemplates {
        
        /**
         * Path Sum - Check if any root-to-leaf path sums to target
         * Strategy: Recursively check with updated target
         */
        public static boolean hasPathSum(TreeNode root, int targetSum) {
            if (root == null) return false;
            
            // Leaf node check
            if (root.left == null && root.right == null) {
                return root.val == targetSum;
            }
            
            // Recursive exploration with updated target
            int remaining = targetSum - root.val;
            return hasPathSum(root.left, remaining) || 
                   hasPathSum(root.right, remaining);
        }
        
        /**
         * Path Sum with Counting - Count paths that sum to target
         * Strategy: Track all paths, not just root-to-leaf
         */
        public static int pathSum(TreeNode root, int targetSum) {
            if (root == null) return 0;
            
            // Count paths starting from current node + paths in subtrees
            return pathSumFrom(root, targetSum) + 
                   pathSum(root.left, targetSum) + 
                   pathSum(root.right, targetSum);
        }
        
        private static int pathSumFrom(TreeNode node, int targetSum) {
            if (node == null) return 0;
            
            int count = 0;
            if (node.val == targetSum) count = 1;
            
            int remaining = targetSum - node.val;
            count += pathSumFrom(node.left, remaining);
            count += pathSumFrom(node.right, remaining);
            
            return count;
        }
        
        /**
         * Optimized Path Sum with HashMap
         * Strategy: Use prefix sum technique for O(n) solution
         */
        public static int pathSumOptimized(TreeNode root, int targetSum) {
            Map<Integer, Integer> prefixSums = new HashMap<>();
            prefixSums.put(0, 1); // Base case: sum of 0 with 1 occurrence
            return dfsWithPrefix(root, 0, targetSum, prefixSums);
        }
        
        private static int dfsWithPrefix(TreeNode node, int currentSum, int targetSum, 
                                       Map<Integer, Integer> prefixSums) {
            if (node == null) return 0;
            
            currentSum += node.val;
            int pathCount = prefixSums.getOrDefault(currentSum - targetSum, 0);
            
            prefixSums.put(currentSum, prefixSums.getOrDefault(currentSum, 0) + 1);
            
            pathCount += dfsWithPrefix(node.left, currentSum, targetSum, prefixSums);
            pathCount += dfsWithPrefix(node.right, currentSum, targetSum, prefixSums);
            
            // Backtrack
            prefixSums.put(currentSum, prefixSums.get(currentSum) - 1);
            
            return pathCount;
        }
    }
    
    /**
     * 📝 PATH ENUMERATION TEMPLATES
     */
    public static class PathEnumerationTemplates {
        
        /**
         * Path Sum II - Find all root-to-leaf paths that sum to target
         * Strategy: DFS with path tracking and backtracking
         */
        public static List<List<Integer>> pathSum(TreeNode root, int targetSum) {
            List<List<Integer>> result = new ArrayList<>();
            List<Integer> currentPath = new ArrayList<>();
            dfsPathSum(root, targetSum, currentPath, result);
            return result;
        }
        
        private static void dfsPathSum(TreeNode node, int remaining, 
                                     List<Integer> currentPath, 
                                     List<List<Integer>> result) {
            if (node == null) return;
            
            currentPath.add(node.val);
            
            // Check if leaf and target reached
            if (node.left == null && node.right == null && remaining == node.val) {
                result.add(new ArrayList<>(currentPath));
            }
            
            // Recursive exploration
            int newRemaining = remaining - node.val;
            dfsPathSum(node.left, newRemaining, currentPath, result);
            dfsPathSum(node.right, newRemaining, currentPath, result);
            
            currentPath.remove(currentPath.size() - 1); // Backtrack
        }
        
        /**
         * All Root-to-Leaf Paths
         * Strategy: Enumerate all paths without sum constraint
         */
        public static List<String> binaryTreePaths(TreeNode root) {
            List<String> result = new ArrayList<>();
            if (root == null) return result;
            
            dfsAllPaths(root, "", result);
            return result;
        }
        
        private static void dfsAllPaths(TreeNode node, String currentPath, 
                                      List<String> result) {
            if (node == null) return;
            
            String newPath = currentPath.isEmpty() ? 
                           String.valueOf(node.val) : 
                           currentPath + "->" + node.val;
            
            // Leaf node
            if (node.left == null && node.right == null) {
                result.add(newPath);
                return;
            }
            
            dfsAllPaths(node.left, newPath, result);
            dfsAllPaths(node.right, newPath, result);
        }
        
        /**
         * Path Sum with Path Details
         * Strategy: Return both sum and path information
         */
        public static class PathInfo {
            List<Integer> path;
            int sum;
            
            PathInfo(List<Integer> path, int sum) {
                this.path = new ArrayList<>(path);
                this.sum = sum;
            }
        }
        
        public static List<PathInfo> getAllPathInfo(TreeNode root) {
            List<PathInfo> result = new ArrayList<>();
            List<Integer> currentPath = new ArrayList<>();
            dfsPathInfo(root, currentPath, 0, result);
            return result;
        }
        
        private static void dfsPathInfo(TreeNode node, List<Integer> currentPath, 
                                      int currentSum, List<PathInfo> result) {
            if (node == null) return;
            
            currentPath.add(node.val);
            currentSum += node.val;
            
            // Leaf node
            if (node.left == null && node.right == null) {
                result.add(new PathInfo(currentPath, currentSum));
            }
            
            dfsPathInfo(node.left, currentPath, currentSum, result);
            dfsPathInfo(node.right, currentPath, currentSum, result);
            
            currentPath.remove(currentPath.size() - 1); // Backtrack
        }
    }
    
    /**
     * 🔢 PATH-BASED VALUE CALCULATION TEMPLATES
     */
    public static class PathValueCalculationTemplates {
        
        /**
         * Sum Root to Leaf Numbers
         * Strategy: Treat each path as a decimal number
         */
        public static int sumNumbers(TreeNode root) {
            return dfsSum(root, 0);
        }
        
        private static int dfsSum(TreeNode node, int currentNumber) {
            if (node == null) return 0;
            
            currentNumber = currentNumber * 10 + node.val;
            
            // Leaf node
            if (node.left == null && node.right == null) {
                return currentNumber;
            }
            
            // Sum from both subtrees
            return dfsSum(node.left, currentNumber) + 
                   dfsSum(node.right, currentNumber);
        }
        
        /**
         * Sum Root to Leaf Numbers (Alternative with Path Collection)
         * Strategy: Collect all numbers first, then sum
         */
        public static int sumNumbersAlternative(TreeNode root) {
            List<Integer> numbers = new ArrayList<>();
            dfsCollectNumbers(root, 0, numbers);
            return numbers.stream().mapToInt(Integer::intValue).sum();
        }
        
        private static void dfsCollectNumbers(TreeNode node, int currentNumber, 
                                            List<Integer> numbers) {
            if (node == null) return;
            
            currentNumber = currentNumber * 10 + node.val;
            
            if (node.left == null && node.right == null) {
                numbers.add(currentNumber);
                return;
            }
            
            dfsCollectNumbers(node.left, currentNumber, numbers);
            dfsCollectNumbers(node.right, currentNumber, numbers);
        }
        
        /**
         * Product of Path Values
         * Strategy: Calculate product instead of sum
         */
        public static long productRootToLeaf(TreeNode root) {
            List<Long> products = new ArrayList<>();
            dfsProduct(root, 1, products);
            return products.stream().mapToLong(Long::longValue).reduce(1, (a, b) -> a * b);
        }
        
        private static void dfsProduct(TreeNode node, long currentProduct, 
                                     List<Long> products) {
            if (node == null) return;
            
            currentProduct *= node.val;
            
            if (node.left == null && node.right == null) {
                products.add(currentProduct);
                return;
            }
            
            dfsProduct(node.left, currentProduct, products);
            dfsProduct(node.right, currentProduct, products);
        }
    }
    
    /**
     * 🏆 OPTIMIZATION TEMPLATES
     */
    public static class OptimizationTemplates {
        
        /**
         * Binary Tree Maximum Path Sum (Any-to-Any)
         * Strategy: For each node, consider path through it
         */
        public static int maxPathSum(TreeNode root) {
            int[] maxSum = {Integer.MIN_VALUE};
            maxPathSumHelper(root, maxSum);
            return maxSum[0];
        }
        
        private static int maxPathSumHelper(TreeNode node, int[] maxSum) {
            if (node == null) return 0;
            
            // Get max gain from left and right subtrees (ignore negative)
            int leftGain = Math.max(0, maxPathSumHelper(node.left, maxSum));
            int rightGain = Math.max(0, maxPathSumHelper(node.right, maxSum));
            
            // Current path through this node
            int currentPathSum = node.val + leftGain + rightGain;
            maxSum[0] = Math.max(maxSum[0], currentPathSum);
            
            // Return max gain continuing from this node
            return node.val + Math.max(leftGain, rightGain);
        }
        
        /**
         * Minimum Path Sum (Root-to-Leaf)
         * Strategy: Find path with minimum sum
         */
        public static int minPathSum(TreeNode root) {
            if (root == null) return 0;
            
            // Leaf node
            if (root.left == null && root.right == null) {
                return root.val;
            }
            
            int leftMin = (root.left != null) ? minPathSum(root.left) : Integer.MAX_VALUE;
            int rightMin = (root.right != null) ? minPathSum(root.right) : Integer.MAX_VALUE;
            
            return root.val + Math.min(leftMin, rightMin);
        }
        
        /**
         * Path with Maximum Average
         * Strategy: Find path with highest average value
         */
        public static double maxAveragePathSum(TreeNode root) {
            class PathResult {
                double maxAverage;
                PathResult(double avg) { this.maxAverage = avg; }
            }
            
            PathResult result = new PathResult(Double.MIN_VALUE);
            maxAverageHelper(root, 0, 0, result);
            return result.maxAverage;
        }
        
        private static void maxAverageHelper(TreeNode node, int sum, int count, 
                                           Object result) {
            if (node == null) return;
            
            sum += node.val;
            count++;
            
            // Leaf node
            if (node.left == null && node.right == null) {
                double average = (double) sum / count;
                // Update result logic would go here
                return;
            }
            
            maxAverageHelper(node.left, sum, count, result);
            maxAverageHelper(node.right, sum, count, result);
        }
    }
    
    /**
     * 🔧 ADVANCED PATH ALGORITHMS
     */
    public static class AdvancedPathAlgorithms {
        
        /**
         * Path Sum with Memoization
         * Strategy: Cache results for repeated subproblems
         */
        public static int pathSumWithMemo(TreeNode root, int targetSum) {
            Map<String, Integer> memo = new HashMap<>();
            return pathSumMemoHelper(root, targetSum, "", memo);
        }
        
        private static int pathSumMemoHelper(TreeNode node, int targetSum, 
                                           String path, Map<String, Integer> memo) {
            if (node == null) return 0;
            
            String key = path + ":" + targetSum;
            if (memo.containsKey(key)) return memo.get(key);
            
            int result = 0;
            if (node.val == targetSum) result = 1;
            
            String newPath = path + "->" + node.val;
            int remaining = targetSum - node.val;
            
            result += pathSumMemoHelper(node.left, remaining, newPath, memo);
            result += pathSumMemoHelper(node.right, remaining, newPath, memo);
            
            memo.put(key, result);
            return result;
        }
        
        /**
         * Longest Path with Constraint
         * Strategy: Find longest path satisfying specific constraint
         */
        public static int longestPathWithSum(TreeNode root, int targetSum) {
            int[] maxLength = {0};
            longestPathHelper(root, targetSum, 0, maxLength);
            return maxLength[0];
        }
        
        private static void longestPathHelper(TreeNode node, int targetSum, 
                                            int currentLength, int[] maxLength) {
            if (node == null) return;
            
            if (targetSum == node.val) {
                maxLength[0] = Math.max(maxLength[0], currentLength + 1);
            }
            
            int remaining = targetSum - node.val;
            longestPathHelper(node.left, remaining, currentLength + 1, maxLength);
            longestPathHelper(node.right, remaining, currentLength + 1, maxLength);
        }
        
        /**
         * Path Sum with Multiple Targets
         * Strategy: Track multiple target sums simultaneously
         */
        public static Map<Integer, Integer> pathSumMultipleTargets(TreeNode root, 
                                                                  int[] targets) {
            Map<Integer, Integer> results = new HashMap<>();
            for (int target : targets) {
                results.put(target, 0);
            }
            
            multiTargetHelper(root, targets, results);
            return results;
        }
        
        private static void multiTargetHelper(TreeNode node, int[] targets, 
                                            Map<Integer, Integer> results) {
            if (node == null) return;
            
            // Check each target
            for (int i = 0; i < targets.length; i++) {
                if (targets[i] == node.val) {
                    results.put(targets[i], results.get(targets[i]) + 1);
                }
                targets[i] -= node.val;
            }
            
            multiTargetHelper(node.left, targets.clone(), results);
            multiTargetHelper(node.right, targets.clone(), results);
        }
    }
    
    // ============================================================================
    // 🚨 COMMON PITFALLS TO AVOID
    // ============================================================================
    
    /**
     * ❌ PITFALL 1: Forgetting Backtracking
     * Always remove elements from path when backtracking
     */
    public static void backtrackingExample() {
        // Correct: currentPath.remove(currentPath.size() - 1);
        // Incorrect: forgetting to remove after recursive calls
    }
    
    /**
     * ❌ PITFALL 2: Incorrect Leaf Node Check
     * Ensure both left and right children are null for leaf
     */
    public static void leafNodeExample() {
        // Correct: node.left == null && node.right == null
        // Incorrect: node.left == null || node.right == null
    }
    
    /**
     * ❌ PITFALL 3: Path Modification During Recursion
     * Use copies when needed, manage shared state carefully
     */
    public static void pathModificationExample() {
        // Correct: result.add(new ArrayList<>(currentPath));
        // Incorrect: result.add(currentPath); // Reference issue
    }
    
    /**
     * ❌ PITFALL 4: Integer Overflow in Path Calculations
     * Handle large numbers appropriately
     */
    public static void overflowExample() {
        // Consider using long for large number calculations
        // Check for overflow conditions in sum calculations
    }
    
    // ============================================================================
    // 💡 PRO TIPS
    // ============================================================================
    
    /**
     * 🎯 TIP 1: Choose Appropriate Path Representation
     * - List<Integer> for explicit path tracking
     * - Running sum for efficiency when path details not needed
     * - String representation for path-based keys
     */
    
    /**
     * 🎯 TIP 2: Optimize Recursive Calls
     * - Use early termination when possible
     * - Consider iterative approaches for very deep trees
     * - Memoization for overlapping subproblems
     */
    
    /**
     * 🎯 TIP 3: Handle Edge Cases Gracefully
     * - Empty trees, single nodes, negative values
     * - Integer overflow in calculations
     * - Very deep trees causing stack overflow
     */
    
    /**
     * 🎯 TIP 4: Consider Alternative Path Definitions
     * - Not just root-to-leaf: any-to-any paths
     * - Partial paths, longest paths, shortest paths
     * - Paths with specific properties beyond sum
     */
    
    /**
     * 🎯 TIP 5: Optimize Space Complexity
     * - In-place modifications when possible
     * - Avoid unnecessary path copies
     * - Use generators/iterators for large result sets
     */
    
    // ============================================================================
    // 📚 PRACTICE PROBLEMS BY CATEGORY
    // ============================================================================
    
    /**
     * 🟢 BASIC PATH SUM PROBLEMS:
     * - Path Sum (existence check)
     * - Sum Root to Leaf Numbers
     * - Binary Tree Paths
     * - Minimum Depth of Binary Tree
     * 
     * 🟡 INTERMEDIATE PATH SUM PROBLEMS:
     * - Path Sum II (path enumeration)
     * - Path Sum III (any-to-any paths)
     * - Binary Tree Maximum Path Sum
     * - Longest Univalue Path
     * 
     * 🔴 ADVANCED PATH SUM PROBLEMS:
     * - Path Sum IV (with position encoding)
     * - Binary Tree Cameras (path-based optimization)
     * - House Robber III (path-based DP)
     * - Maximum Product of Splitted Binary Tree
     */
    
    // ============================================================================
    // 🧭 DECISION FRAMEWORK
    // ============================================================================
    
    /**
     * CHOOSE APPROACH BASED ON:
     * ✅ Path type: root-to-leaf vs any-to-any vs specific constraints
     * ✅ Result needed: existence, enumeration, counting, optimization
     * ✅ Performance requirements: time/space complexity, tree depth
     * ✅ Path properties: sum, length, values, conditions
     * 
     * OPTIMIZE FOR:
     * 🚀 Memory efficiency: avoid unnecessary path storage
     * 🚀 Time complexity: use appropriate algorithms for tree size
     * 🚀 Code clarity: readable and maintainable path tracking
     * 🚀 Robustness: handle edge cases and invalid inputs
     */
    
    // ============================================================================
    // 🌍 REAL-WORLD APPLICATIONS
    // ============================================================================
    
    /**
     * 🏭 PRACTICAL USES:
     * - Network routing (finding paths with specific costs)
     * - Game AI (pathfinding with resource constraints)
     * - Financial modeling (decision trees with cumulative costs)
     * - Resource allocation (optimal path selection)
     * - Supply chain optimization (cost-effective routing)
     * - Decision support systems (path analysis in decision trees)
     * - Bioinformatics (sequence alignment with scoring)
     * - Operations research (optimization over tree structures)
     */
    
    public static void main(String[] args) {
        System.out.println("🛤️ BINARY TREE PATH SUM & ROOT TO LEAF PATTERN - READING GUIDE");
        System.out.println("================================================================");
        
        System.out.println("\n📖 Template Examples:");
        
        // Create sample tree for testing
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(11);
        root.left.left.left = new TreeNode(7);
        root.left.left.right = new TreeNode(2);
        root.right.left = new TreeNode(13);
        root.right.right = new TreeNode(4);
        root.right.right.right = new TreeNode(1);
        
        // Test basic path sum
        boolean hasPath = BasicPathSumTemplates.hasPathSum(root, 22);
        System.out.println("Has path sum 22: " + hasPath);
        
        // Test path enumeration
        List<List<Integer>> allPaths = PathEnumerationTemplates.pathSum(root, 22);
        System.out.println("Paths with sum 22: " + allPaths.size());
        
        // Test sum calculation
        int totalSum = PathValueCalculationTemplates.sumNumbers(root);
        System.out.println("Sum of all root-to-leaf numbers: " + totalSum);
        
        // Test optimization
        int maxSum = OptimizationTemplates.maxPathSum(root);
        System.out.println("Maximum path sum: " + maxSum);
        
        System.out.println("\n✅ Key Binary Tree Path Sum Principles:");
        System.out.println("1. Track path state during recursive traversal");
        System.out.println("2. Use backtracking for path enumeration problems");
        System.out.println("3. Handle leaf node detection carefully");
        System.out.println("4. Consider different path definitions (root-to-leaf vs any-to-any)");
        System.out.println("5. Optimize space usage with appropriate data structures");
        System.out.println("6. Handle edge cases like empty trees and overflow");
        System.out.println("7. Choose efficient algorithms based on problem requirements");
    }
} 