package com.dsalgo.AlgoPatterns.BTBST;

import java.util.*;

/**
 * ✅ BINARY TREE VALIDATION & PROPERTIES PATTERN - COMPREHENSIVE READING GUIDE
 * 
 * ============================================================================
 * 📚 WHAT IS BINARY TREE VALIDATION & PROPERTIES PATTERN?
 * ============================================================================
 * 
 * Binary Tree Validation & Properties Pattern involves systematically verifying
 * tree characteristics, computing structural metrics, and validating specific
 * properties of binary trees. This pattern encompasses property validation
 * (BST ordering, balance, completeness), structural analysis (diameter, height,
 * width), sequence verification (consecutive values, path properties), and
 * comprehensive tree characterization for various constraints and requirements.
 * 
 * The pattern requires understanding tree properties, structural relationships,
 * validation algorithms, and metric computation techniques. It's essential for
 * tree-based data structure verification, constraint satisfaction, performance
 * analysis, and ensuring correctness of tree-based algorithms and applications.
 * 
 * 🔑 CORE PRINCIPLES:
 * 1. PROPERTY VERIFICATION: Systematically validate tree characteristics and constraints
 * 2. STRUCTURAL ANALYSIS: Compute tree metrics and dimensional properties
 * 3. CONSTRAINT SATISFACTION: Ensure trees meet specific requirements and conditions
 * 4. PERFORMANCE CHARACTERIZATION: Analyze tree efficiency and optimization potential
 * 
 * ✅ BINARY TREE VALIDATION & PROPERTIES METAPHOR:
 * Think of tree validation & properties as "building inspection and quality assurance":
 * - Tree structure: building architecture with floors, rooms, and connections
 * - Property validation: checking building codes, safety standards, and regulations
 * - Structural metrics: measuring dimensions, capacity, and stability
 * - Constraint verification: ensuring compliance with design specifications
 * 
 * ============================================================================
 * 🎯 WHEN TO USE BINARY TREE VALIDATION & PROPERTIES PATTERN
 * ============================================================================
 * 
 * ✅ PERFECT FOR:
 * - Validating tree properties and structural constraints
 * - Computing tree metrics like diameter, height, width, and node counts
 * - Verifying sequence properties and path characteristics
 * - Checking balance, completeness, and other structural requirements
 * - Tree quality assessment and performance analysis
 * - Constraint satisfaction in tree-based algorithms
 * 
 * 🔍 LOOK FOR THESE PHRASES:
 * - "Validate tree property"
 * - "Check if tree is balanced/complete"
 * - "Compute tree diameter/height"
 * - "Longest consecutive sequence"
 * - "Tree metrics and measurements"
 * - "Structural validation"
 * 
 * 🚩 RED FLAGS (Consider Other Approaches):
 * - Simple tree traversal (use traversal patterns)
 * - Tree construction or modification (use construction/manipulation patterns)
 * - Search operations (use search patterns)
 * - Path-based computations (use path sum patterns)
 * 
 * ============================================================================
 * 🔄 BINARY TREE VALIDATION & PROPERTIES PATTERN VARIATIONS
 * ============================================================================
 * 
 * 1️⃣ STRUCTURAL PROPERTY VALIDATION
 * - Balance checking: verify height balance constraints
 * - Completeness validation: check complete binary tree properties
 * - BST validation: verify binary search tree ordering
 * - Symmetry checking: validate tree symmetry and mirror properties
 * 
 * 2️⃣ TREE METRICS COMPUTATION
 * - Diameter calculation: find longest path between any two nodes
 * - Height measurement: compute maximum depth from root to leaves
 * - Width analysis: determine maximum width at any level
 * - Node counting: count nodes with specific properties or constraints
 * 
 * 3️⃣ SEQUENCE AND PATH PROPERTIES
 * - Consecutive sequence validation: check for consecutive value paths
 * - Path property verification: validate specific path characteristics
 * - Sum property checking: verify path sum constraints
 * - Pattern matching: identify specific value or structural patterns
 * 
 * 4️⃣ PERFORMANCE CHARACTERISTICS
 * - Efficiency analysis: assess tree performance for operations
 * - Balance factor computation: measure tree balance quality
 * - Optimization potential: identify improvement opportunities
 * - Complexity assessment: analyze time/space characteristics
 * 
 * 5️⃣ CONSTRAINT SATISFACTION
 * - Custom property validation: verify application-specific constraints
 * - Multi-criteria checking: validate multiple properties simultaneously
 * - Conditional validation: check properties under specific conditions
 * - Dynamic constraint verification: validate changing requirements
 * 
 * 6️⃣ ADVANCED PROPERTY ANALYSIS
 * - Statistical properties: compute tree statistics and distributions
 * - Topological characteristics: analyze tree topology and connectivity
 * - Comparative analysis: compare trees for similarity and differences
 * - Invariant verification: ensure tree invariants are maintained
 * 
 * ============================================================================
 * 🧠 CORE CONCEPTS AND ALGORITHMS
 * ============================================================================
 * 
 * 🔹 DIAMETER CALCULATION:
 * ```
 * def diameter(root):
 *     max_diameter = 0
 *     
 *     def height_and_diameter(node):
 *         nonlocal max_diameter
 *         if not node:
 *             return 0
 *         
 *         left_height = height_and_diameter(node.left)
 *         right_height = height_and_diameter(node.right)
 *         
 *         # Diameter through current node
 *         current_diameter = left_height + right_height
 *         max_diameter = max(max_diameter, current_diameter)
 *         
 *         return 1 + max(left_height, right_height)
 *     
 *     height_and_diameter(root)
 *     return max_diameter
 * ```
 * 
 * 🔹 BALANCE VALIDATION:
 * ```
 * def isBalanced(root):
 *     def check_height(node):
 *         if not node:
 *             return 0
 *         
 *         left_height = check_height(node.left)
 *         if left_height == -1:
 *             return -1  # Left subtree is unbalanced
 *         
 *         right_height = check_height(node.right)
 *         if right_height == -1:
 *             return -1  # Right subtree is unbalanced
 *         
 *         if abs(left_height - right_height) > 1:
 *             return -1  # Current node is unbalanced
 *         
 *         return 1 + max(left_height, right_height)
 *     
 *     return check_height(root) != -1
 * ```
 * 
 * 🔹 COMPLETE TREE NODE COUNTING:
 * ```
 * def countNodes(root):
 *     if not root:
 *         return 0
 *     
 *     left_depth = get_depth(root, True)
 *     right_depth = get_depth(root, False)
 *     
 *     if left_depth == right_depth:
 *         return (1 << left_depth) - 1  # Perfect binary tree
 *     
 *     return 1 + countNodes(root.left) + countNodes(root.right)
 * 
 * def get_depth(node, go_left):
 *     depth = 0
 *     while node:
 *         node = node.left if go_left else node.right
 *         depth += 1
 *     return depth
 * ```
 * 
 * ============================================================================
 * 📋 PROBLEM-SOLVING FRAMEWORK
 * ============================================================================
 * 
 * STEP 1: IDENTIFY PROPERTY OR METRIC TO VALIDATE/COMPUTE
 * - What specific property needs validation or computation?
 * - What are the constraints and requirements?
 * - What constitutes a valid or correct result?
 * 
 * STEP 2: CHOOSE VALIDATION STRATEGY
 * - How to systematically check the property?
 * - What traversal method is most appropriate?
 * - How to handle edge cases and invalid inputs?
 * 
 * STEP 3: DESIGN VERIFICATION ALGORITHM
 * - What are the base cases and termination conditions?
 * - How to combine results from subtrees?
 * - What optimization techniques can be applied?
 * 
 * STEP 4: IMPLEMENT EFFICIENT SOLUTION
 * - How to minimize time and space complexity?
 * - What data structures aid the validation process?
 * - How to handle early termination for efficiency?
 * 
 * STEP 5: VALIDATE CORRECTNESS AND OPTIMIZE
 * - How to verify the validation algorithm is correct?
 * - What are the performance characteristics?
 * - Can the algorithm be further optimized?
 * 
 * ============================================================================
 * 🎨 BINARY TREE VALIDATION & PROPERTIES TEMPLATES
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

public class TreeValidationPropertiesReadingGuide {
    
    /**
     * 📏 TREE METRICS COMPUTATION TEMPLATES
     */
    public static class TreeMetricsTemplates {
        
        /**
         * Diameter of Binary Tree
         * Strategy: Compute diameter while calculating height
         */
        public static int diameterOfBinaryTree(TreeNode root) {
            int[] maxDiameter = {0};
            calculateHeight(root, maxDiameter);
            return maxDiameter[0];
        }
        
        private static int calculateHeight(TreeNode node, int[] maxDiameter) {
            if (node == null) return 0;
            
            int leftHeight = calculateHeight(node.left, maxDiameter);
            int rightHeight = calculateHeight(node.right, maxDiameter);
            
            // Diameter through current node
            int currentDiameter = leftHeight + rightHeight;
            maxDiameter[0] = Math.max(maxDiameter[0], currentDiameter);
            
            // Return height of current subtree
            return 1 + Math.max(leftHeight, rightHeight);
        }
        
        /**
         * Maximum Depth/Height of Binary Tree
         * Strategy: Recursive depth computation
         */
        public static int maxDepth(TreeNode root) {
            if (root == null) return 0;
            return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
        }
        
        /**
         * Minimum Depth of Binary Tree
         * Strategy: BFS for shortest path to leaf
         */
        public static int minDepth(TreeNode root) {
            if (root == null) return 0;
            
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            int depth = 1;
            
            while (!queue.isEmpty()) {
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    TreeNode node = queue.poll();
                    
                    // First leaf found
                    if (node.left == null && node.right == null) {
                        return depth;
                    }
                    
                    if (node.left != null) queue.offer(node.left);
                    if (node.right != null) queue.offer(node.right);
                }
                depth++;
            }
            
            return depth;
        }
        
        /**
         * Maximum Width of Binary Tree
         * Strategy: Level-order traversal with position tracking
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
                    
                    if (i == 0) start = pos;
                    if (i == size - 1) end = pos;
                    
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
    }
    
    /**
     * ⚖️ BALANCE VALIDATION TEMPLATES
     */
    public static class BalanceValidationTemplates {
        
        /**
         * Balanced Binary Tree
         * Strategy: Check height difference at each node
         */
        public static boolean isBalanced(TreeNode root) {
            return checkHeight(root) != -1;
        }
        
        private static int checkHeight(TreeNode node) {
            if (node == null) return 0;
            
            int leftHeight = checkHeight(node.left);
            if (leftHeight == -1) return -1; // Left subtree unbalanced
            
            int rightHeight = checkHeight(node.right);
            if (rightHeight == -1) return -1; // Right subtree unbalanced
            
            // Check balance condition
            if (Math.abs(leftHeight - rightHeight) > 1) {
                return -1; // Current node unbalanced
            }
            
            return 1 + Math.max(leftHeight, rightHeight);
        }
        
        /**
         * AVL Tree Balance Factor
         * Strategy: Compute balance factor for AVL validation
         */
        public static boolean isAVLTree(TreeNode root) {
            return checkAVLProperty(root) != -1;
        }
        
        private static int checkAVLProperty(TreeNode node) {
            if (node == null) return 0;
            
            int leftHeight = checkAVLProperty(node.left);
            if (leftHeight == -1) return -1;
            
            int rightHeight = checkAVLProperty(node.right);
            if (rightHeight == -1) return -1;
            
            int balanceFactor = Math.abs(leftHeight - rightHeight);
            if (balanceFactor > 1) return -1; // AVL property violated
            
            return 1 + Math.max(leftHeight, rightHeight);
        }
        
        /**
         * Perfect Binary Tree Check
         * Strategy: Verify all levels are completely filled
         */
        public static boolean isPerfectBinaryTree(TreeNode root) {
            int depth = getDepth(root);
            return isPerfectHelper(root, depth, 0);
        }
        
        private static boolean isPerfectHelper(TreeNode node, int depth, int level) {
            if (node == null) return true;
            
            // Leaf node check
            if (node.left == null && node.right == null) {
                return level == depth - 1;
            }
            
            // Internal node must have both children
            if (node.left == null || node.right == null) {
                return false;
            }
            
            return isPerfectHelper(node.left, depth, level + 1) &&
                   isPerfectHelper(node.right, depth, level + 1);
        }
        
        private static int getDepth(TreeNode node) {
            if (node == null) return 0;
            return 1 + getDepth(node.left);
        }
    }
    
    /**
     * 🔢 NODE COUNTING TEMPLATES
     */
    public static class NodeCountingTemplates {
        
        /**
         * Count Complete Tree Nodes
         * Strategy: Use complete tree property for optimization
         */
        public static int countNodes(TreeNode root) {
            if (root == null) return 0;
            
            int leftDepth = getLeftDepth(root);
            int rightDepth = getRightDepth(root);
            
            if (leftDepth == rightDepth) {
                // Perfect binary tree
                return (1 << leftDepth) - 1;
            }
            
            // Not perfect, count recursively
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
         * Count Nodes with Specific Properties
         * Strategy: Traverse and count based on conditions
         */
        public static int countNodesWithValue(TreeNode root, int targetValue) {
            if (root == null) return 0;
            
            int count = (root.val == targetValue) ? 1 : 0;
            count += countNodesWithValue(root.left, targetValue);
            count += countNodesWithValue(root.right, targetValue);
            
            return count;
        }
        
        /**
         * Count Leaf Nodes
         * Strategy: Identify and count leaf nodes
         */
        public static int countLeaves(TreeNode root) {
            if (root == null) return 0;
            if (root.left == null && root.right == null) return 1;
            
            return countLeaves(root.left) + countLeaves(root.right);
        }
        
        /**
         * Count Internal Nodes
         * Strategy: Count non-leaf nodes
         */
        public static int countInternalNodes(TreeNode root) {
            if (root == null || (root.left == null && root.right == null)) {
                return 0;
            }
            
            return 1 + countInternalNodes(root.left) + countInternalNodes(root.right);
        }
    }
    
    /**
     * 🔗 SEQUENCE PROPERTY TEMPLATES
     */
    public static class SequencePropertyTemplates {
        
        /**
         * Longest Consecutive Sequence
         * Strategy: Track consecutive sequence length during traversal
         */
        public static int longestConsecutive(TreeNode root) {
            if (root == null) return 0;
            return Math.max(
                longestConsecutiveHelper(root, null, 0),
                Math.max(longestConsecutive(root.left), longestConsecutive(root.right))
            );
        }
        
        private static int longestConsecutiveHelper(TreeNode node, TreeNode parent, int length) {
            if (node == null) return length;
            
            int currentLength = (parent != null && node.val == parent.val + 1) ? length + 1 : 1;
            
            return Math.max(currentLength,
                Math.max(longestConsecutiveHelper(node.left, node, currentLength),
                        longestConsecutiveHelper(node.right, node, currentLength)));
        }
        
        /**
         * Longest Univalue Path
         * Strategy: Find longest path with same value
         */
        public static int longestUnivaluePath(TreeNode root) {
            int[] maxLength = {0};
            longestUnivalueHelper(root, maxLength);
            return maxLength[0];
        }
        
        private static int longestUnivalueHelper(TreeNode node, int[] maxLength) {
            if (node == null) return 0;
            
            int left = longestUnivalueHelper(node.left, maxLength);
            int right = longestUnivalueHelper(node.right, maxLength);
            
            int leftPath = 0, rightPath = 0;
            
            if (node.left != null && node.left.val == node.val) {
                leftPath = left + 1;
            }
            
            if (node.right != null && node.right.val == node.val) {
                rightPath = right + 1;
            }
            
            maxLength[0] = Math.max(maxLength[0], leftPath + rightPath);
            
            return Math.max(leftPath, rightPath);
        }
        
        /**
         * Increasing Order Search Tree
         * Strategy: Verify strictly increasing order
         */
        public static boolean isIncreasingBST(TreeNode root) {
            List<Integer> inorder = new ArrayList<>();
            inorderTraversal(root, inorder);
            
            for (int i = 1; i < inorder.size(); i++) {
                if (inorder.get(i) <= inorder.get(i - 1)) {
                    return false;
                }
            }
            return true;
        }
        
        private static void inorderTraversal(TreeNode node, List<Integer> result) {
            if (node != null) {
                inorderTraversal(node.left, result);
                result.add(node.val);
                inorderTraversal(node.right, result);
            }
        }
    }
    
    /**
     * 🏗️ STRUCTURAL VALIDATION TEMPLATES
     */
    public static class StructuralValidationTemplates {
        
        /**
         * Complete Binary Tree Validation
         * Strategy: Level-order traversal with null tracking
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
                    if (nullSeen) return false; // Non-null after null
                    
                    queue.offer(node.left);
                    queue.offer(node.right);
                }
            }
            
            return true;
        }
        
        /**
         * Full Binary Tree Validation
         * Strategy: Every node has 0 or 2 children
         */
        public static boolean isFullBinaryTree(TreeNode root) {
            if (root == null) return true;
            
            // Leaf node
            if (root.left == null && root.right == null) return true;
            
            // Internal node must have both children
            if (root.left != null && root.right != null) {
                return isFullBinaryTree(root.left) && isFullBinaryTree(root.right);
            }
            
            return false; // One child only
        }
        
        /**
         * Symmetric Tree Validation
         * Strategy: Compare left and right subtrees recursively
         */
        public static boolean isSymmetric(TreeNode root) {
            if (root == null) return true;
            return isSymmetricHelper(root.left, root.right);
        }
        
        private static boolean isSymmetricHelper(TreeNode left, TreeNode right) {
            if (left == null && right == null) return true;
            if (left == null || right == null) return false;
            
            return left.val == right.val &&
                   isSymmetricHelper(left.left, right.right) &&
                   isSymmetricHelper(left.right, right.left);
        }
        
        /**
         * Binary Search Tree Validation
         * Strategy: Check BST property with bounds
         */
        public static boolean isValidBST(TreeNode root) {
            return validateBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
        }
        
        private static boolean validateBST(TreeNode node, long minVal, long maxVal) {
            if (node == null) return true;
            
            if (node.val <= minVal || node.val >= maxVal) return false;
            
            return validateBST(node.left, minVal, node.val) &&
                   validateBST(node.right, node.val, maxVal);
        }
    }
    
    /**
     * 📊 ADVANCED PROPERTY ANALYSIS TEMPLATES
     */
    public static class AdvancedAnalysisTemplates {
        
        /**
         * Tree Complexity Analysis
         * Strategy: Compute various complexity metrics
         */
        public static class TreeComplexityMetrics {
            public int height;
            public int nodes;
            public int leaves;
            public int diameter;
            public boolean isBalanced;
            public boolean isComplete;
            public boolean isBST;
            
            public TreeComplexityMetrics(int height, int nodes, int leaves, int diameter,
                                       boolean isBalanced, boolean isComplete, boolean isBST) {
                this.height = height;
                this.nodes = nodes;
                this.leaves = leaves;
                this.diameter = diameter;
                this.isBalanced = isBalanced;
                this.isComplete = isComplete;
                this.isBST = isBST;
            }
        }
        
        public static TreeComplexityMetrics analyzeTree(TreeNode root) {
            int height = maxDepth(root);
            int nodes = countTotalNodes(root);
            int leaves = countLeaves(root);
            int diameter = diameterOfBinaryTree(root);
            boolean isBalanced = BalanceValidationTemplates.isBalanced(root);
            boolean isComplete = StructuralValidationTemplates.isCompleteTree(root);
            boolean isBST = StructuralValidationTemplates.isValidBST(root);
            
            return new TreeComplexityMetrics(height, nodes, leaves, diameter,
                                           isBalanced, isComplete, isBST);
        }
        
        private static int maxDepth(TreeNode root) {
            if (root == null) return 0;
            return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
        }
        
        private static int countTotalNodes(TreeNode root) {
            if (root == null) return 0;
            return 1 + countTotalNodes(root.left) + countTotalNodes(root.right);
        }
        
        private static int countLeaves(TreeNode root) {
            if (root == null) return 0;
            if (root.left == null && root.right == null) return 1;
            return countLeaves(root.left) + countLeaves(root.right);
        }
        
        private static int diameterOfBinaryTree(TreeNode root) {
            int[] maxDiameter = {0};
            calculateHeight(root, maxDiameter);
            return maxDiameter[0];
        }
        
        private static int calculateHeight(TreeNode node, int[] maxDiameter) {
            if (node == null) return 0;
            
            int leftHeight = calculateHeight(node.left, maxDiameter);
            int rightHeight = calculateHeight(node.right, maxDiameter);
            
            maxDiameter[0] = Math.max(maxDiameter[0], leftHeight + rightHeight);
            
            return 1 + Math.max(leftHeight, rightHeight);
        }
        
        /**
         * Tree Statistics Computation
         * Strategy: Compute statistical properties of tree values
         */
        public static class TreeStatistics {
            public double mean;
            public int min;
            public int max;
            public int sum;
            public int count;
            
            public TreeStatistics(double mean, int min, int max, int sum, int count) {
                this.mean = mean;
                this.min = min;
                this.max = max;
                this.sum = sum;
                this.count = count;
            }
        }
        
        public static TreeStatistics computeStatistics(TreeNode root) {
            if (root == null) return new TreeStatistics(0, 0, 0, 0, 0);
            
            int[] stats = {0, Integer.MAX_VALUE, Integer.MIN_VALUE, 0}; // count, min, max, sum
            computeStatsHelper(root, stats);
            
            double mean = stats[0] > 0 ? (double) stats[3] / stats[0] : 0;
            return new TreeStatistics(mean, stats[1], stats[2], stats[3], stats[0]);
        }
        
        private static void computeStatsHelper(TreeNode node, int[] stats) {
            if (node == null) return;
            
            stats[0]++; // count
            stats[1] = Math.min(stats[1], node.val); // min
            stats[2] = Math.max(stats[2], node.val); // max
            stats[3] += node.val; // sum
            
            computeStatsHelper(node.left, stats);
            computeStatsHelper(node.right, stats);
        }
    }
    
    // ============================================================================
    // 🚨 COMMON PITFALLS TO AVOID
    // ============================================================================
    
    /**
     * ❌ PITFALL 1: Incorrect Balance Definition
     * Ensure proper understanding of balance criteria
     */
    public static void balanceDefinitionExample() {
        // Correct: Height difference ≤ 1 for balanced tree
        // Incorrect: Confusing with perfect or complete trees
    }
    
    /**
     * ❌ PITFALL 2: Off-by-One Errors in Counting
     * Be careful with tree depth, height, and node counting
     */
    public static void countingErrorExample() {
        // Correct: Depth starts from 1, height includes root
        // Incorrect: Mixing 0-based and 1-based indexing
    }
    
    /**
     * ❌ PITFALL 3: Incomplete Property Validation
     * Validate all required conditions, not just local properties
     */
    public static void validationExample() {
        // Correct: Check global BST property with bounds
        // Incorrect: Only checking parent-child relationships
    }
    
    /**
     * ❌ PITFALL 4: Performance Issues with Repeated Calculations
     * Avoid redundant computations in tree analysis
     */
    public static void performanceExample() {
        // Use memoization or single-pass algorithms when possible
        // Combine multiple property checks in single traversal
    }
    
    // ============================================================================
    // 💡 PRO TIPS
    // ============================================================================
    
    /**
     * 🎯 TIP 1: Combine Multiple Properties in Single Traversal
     * - Compute height, balance, and other metrics simultaneously
     * - Use helper functions that return multiple values
     * - Minimize tree traversals for better performance
     */
    
    /**
     * 🎯 TIP 2: Use Early Termination for Validation
     * - Return immediately when property violation detected
     * - Use sentinel values (-1) to indicate invalid states
     * - Short-circuit evaluation for boolean combinations
     */
    
    /**
     * 🎯 TIP 3: Handle Edge Cases Systematically
     * - Null trees, single nodes, empty subtrees
     * - Integer overflow in calculations
     * - Very deep or very wide trees
     */
    
    /**
     * 🎯 TIP 4: Choose Optimal Algorithms for Tree Type
     * - Leverage complete tree properties for node counting
     * - Use BST properties for validation and search
     * - Apply appropriate traversal methods for different properties
     */
    
    /**
     * 🎯 TIP 5: Design Extensible Validation Frameworks
     * - Create composable validation functions
     * - Support custom property definitions
     * - Enable batch validation of multiple properties
     */
    
    // ============================================================================
    // 📚 PRACTICE PROBLEMS BY CATEGORY
    // ============================================================================
    
    /**
     * 🟢 BASIC VALIDATION PROBLEMS:
     * - Maximum Depth of Binary Tree
     * - Balanced Binary Tree
     * - Same Tree
     * - Symmetric Tree
     * 
     * 🟡 INTERMEDIATE VALIDATION PROBLEMS:
     * - Diameter of Binary Tree
     * - Count Complete Tree Nodes
     * - Validate Binary Search Tree
     * - Longest Consecutive Sequence
     * 
     * 🔴 ADVANCED VALIDATION PROBLEMS:
     * - Binary Tree Maximum Path Sum
     * - Serialize and Deserialize Binary Tree
     * - Count Univalue Subtrees
     * - Binary Tree Cameras
     */
    
    // ============================================================================
    // 🧭 DECISION FRAMEWORK
    // ============================================================================
    
    /**
     * CHOOSE APPROACH BASED ON:
     * ✅ Property type: structural, value-based, or performance-related
     * ✅ Tree characteristics: BST, complete, balanced, general
     * ✅ Validation requirements: single property vs multiple properties
     * ✅ Performance constraints: time/space complexity, tree size
     * 
     * OPTIMIZE FOR:
     * 🚀 Efficiency: combine multiple checks in single traversal
     * 🚀 Accuracy: ensure complete and correct validation
     * 🚀 Maintainability: design modular and extensible validation
     * 🚀 Robustness: handle edge cases and invalid inputs
     */
    
    // ============================================================================
    // 🌍 REAL-WORLD APPLICATIONS
    // ============================================================================
    
    /**
     * 🏭 PRACTICAL USES:
     * - Database index validation (B+ tree structure integrity)
     * - File system consistency checking (directory tree validation)
     * - Compiler optimization (expression tree analysis)
     * - Game development (decision tree validation)
     * - Machine learning (decision tree quality assessment)
     * - Network topology validation (tree-based network structures)
     * - Memory management (heap tree validation)
     * - Graphics rendering (scene graph validation)
     */
    
    public static void main(String[] args) {
        System.out.println("✅ BINARY TREE VALIDATION & PROPERTIES PATTERN - READING GUIDE");
        System.out.println("===============================================================");
        
        System.out.println("\n📖 Template Examples:");
        
        // Create sample tree for testing
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        
        // Test tree metrics
        int diameter = TreeMetricsTemplates.diameterOfBinaryTree(root);
        System.out.println("Tree diameter: " + diameter);
        
        int maxDepth = TreeMetricsTemplates.maxDepth(root);
        System.out.println("Maximum depth: " + maxDepth);
        
        int minDepth = TreeMetricsTemplates.minDepth(root);
        System.out.println("Minimum depth: " + minDepth);
        
        // Test balance validation
        boolean isBalanced = BalanceValidationTemplates.isBalanced(root);
        System.out.println("Is balanced: " + isBalanced);
        
        // Test node counting
        int nodeCount = NodeCountingTemplates.countNodes(root);
        System.out.println("Node count: " + nodeCount);
        
        int leafCount = NodeCountingTemplates.countLeaves(root);
        System.out.println("Leaf count: " + leafCount);
        
        // Test structural validation
        boolean isComplete = StructuralValidationTemplates.isCompleteTree(root);
        System.out.println("Is complete tree: " + isComplete);
        
        boolean isSymmetric = StructuralValidationTemplates.isSymmetric(root);
        System.out.println("Is symmetric: " + isSymmetric);
        
        // Test advanced analysis
        AdvancedAnalysisTemplates.TreeComplexityMetrics metrics = 
            AdvancedAnalysisTemplates.analyzeTree(root);
        System.out.println("Tree analysis - Height: " + metrics.height + 
                          ", Nodes: " + metrics.nodes + 
                          ", Leaves: " + metrics.leaves + 
                          ", Diameter: " + metrics.diameter);
        
        AdvancedAnalysisTemplates.TreeStatistics stats = 
            AdvancedAnalysisTemplates.computeStatistics(root);
        System.out.println("Tree statistics - Mean: " + stats.mean + 
                          ", Min: " + stats.min + 
                          ", Max: " + stats.max + 
                          ", Sum: " + stats.sum);
        
        System.out.println("\n✅ Key Binary Tree Validation & Properties Principles:");
        System.out.println("1. Combine multiple property checks in single traversal for efficiency");
        System.out.println("2. Use early termination with sentinel values for validation");
        System.out.println("3. Handle edge cases systematically (null trees, single nodes)");
        System.out.println("4. Leverage tree type properties for optimization (BST, complete, balanced)");
        System.out.println("5. Design modular and extensible validation frameworks");
        System.out.println("6. Choose appropriate traversal methods for different properties");
        System.out.println("7. Validate global properties, not just local relationships");
    }
} 