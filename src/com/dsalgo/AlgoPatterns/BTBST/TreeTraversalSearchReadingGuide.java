package com.dsalgo.AlgoPatterns.BTBST;

import java.util.*;

/**
 * 🔍 BINARY TREE TRAVERSAL & SEARCH PATTERN - COMPREHENSIVE READING GUIDE
 * 
 * ============================================================================
 * 📚 WHAT IS BINARY TREE TRAVERSAL & SEARCH PATTERN?
 * ============================================================================
 * 
 * Binary Tree Traversal & Search Pattern involves systematically exploring binary
 * trees to find specific values, validate properties, and perform complex queries.
 * This pattern encompasses various traversal techniques (inorder, preorder, postorder,
 * level-order), search algorithms optimized for different tree types (BST vs general),
 * tree validation methods, and advanced query operations like finding ancestors,
 * subtree matching, and proximity searches.
 * 
 * The pattern requires understanding tree properties, traversal characteristics,
 * search optimization techniques, and query algorithm design. It's essential for
 * tree-based data retrieval, validation, analysis, and complex tree operations
 * that require systematic exploration and intelligent search strategies.
 * 
 * 🔑 CORE PRINCIPLES:
 * 1. TRAVERSAL STRATEGY: Choose optimal traversal method for specific search goals
 * 2. SEARCH OPTIMIZATION: Leverage tree properties for efficient search algorithms
 * 3. PROPERTY VALIDATION: Verify tree characteristics through systematic checking
 * 4. QUERY PROCESSING: Handle complex tree queries with appropriate algorithms
 * 
 * 🔍 BINARY TREE TRAVERSAL & SEARCH METAPHOR:
 * Think of tree traversal & search as "library navigation and information retrieval":
 * - Tree structure: library organization with sections, shelves, and books
 * - Traversal methods: different strategies for exploring the library systematically
 * - Search algorithms: efficient ways to find specific information or validate organization
 * - Query processing: answering complex questions about library contents and structure
 * 
 * ============================================================================
 * 🎯 WHEN TO USE BINARY TREE TRAVERSAL & SEARCH PATTERN
 * ============================================================================
 * 
 * ✅ PERFECT FOR:
 * - Searching for specific values in binary trees with optimization
 * - Validating tree properties (BST, completeness, balance)
 * - Finding relationships between nodes (ancestors, descendants, proximity)
 * - Performing range queries and proximity searches in trees
 * - Tree comparison and subtree matching operations
 * - Complex tree analysis requiring systematic exploration
 * 
 * 🔍 LOOK FOR THESE PHRASES:
 * - "Search in binary tree/BST"
 * - "Validate binary search tree"
 * - "Lowest common ancestor"
 * - "Find closest/nearest value"
 * - "Subtree matching"
 * - "Tree traversal with conditions"
 * 
 * 🚩 RED FLAGS (Consider Other Approaches):
 * - Simple tree construction (use construction patterns)
 * - Tree modification without search (use manipulation patterns)
 * - Path-based problems (use path sum patterns)
 * - Tree structural transformation (use transformation patterns)
 * 
 * ============================================================================
 * 🔄 BINARY TREE TRAVERSAL & SEARCH PATTERN VARIATIONS
 * ============================================================================
 * 
 * 1️⃣ VALUE SEARCH ALGORITHMS
 * - BST search: leverage ordering property for O(log n) search
 * - General tree search: systematic exploration for any binary tree
 * - Proximity search: find values closest to target
 * - Range search: find all values within specified range
 * 
 * 2️⃣ TREE VALIDATION METHODS
 * - BST validation: verify ordering property throughout tree
 * - Completeness checking: validate tree structural properties
 * - Balance validation: check height balance constraints
 * - Custom property validation: verify application-specific constraints
 * 
 * 3️⃣ ANCESTRAL RELATIONSHIP QUERIES
 * - Lowest common ancestor: find deepest common parent
 * - Path between nodes: trace route between any two nodes
 * - Ancestor checking: verify if one node is ancestor of another
 * - Descendant enumeration: find all descendants of given node
 * 
 * 4️⃣ TRAVERSAL-BASED ANALYSIS
 * - Inorder traversal: sorted order for BSTs, systematic exploration
 * - Preorder traversal: parent-first exploration for tree copying
 * - Postorder traversal: children-first for tree deletion and aggregation
 * - Level-order traversal: breadth-first for shortest path problems
 * 
 * 5️⃣ SUBTREE AND PATTERN MATCHING
 * - Subtree detection: check if one tree is subtree of another
 * - Tree isomorphism: structural equivalence checking
 * - Pattern matching: find specific structural patterns
 * - Tree similarity: measure structural and value similarity
 * 
 * 6️⃣ ADVANCED SEARCH OPERATIONS
 * - Multi-criteria search: find nodes satisfying multiple conditions
 * - Conditional traversal: explore based on dynamic conditions
 * - Iterative deepening: progressive depth exploration
 * - Parallel search: concurrent exploration strategies
 * 
 * ============================================================================
 * 🧠 CORE CONCEPTS AND ALGORITHMS
 * ============================================================================
 * 
 * 🔹 BST SEARCH ALGORITHM:
 * ```
 * def searchBST(root, target):
 *     if not root or root.val == target:
 *         return root
 *     
 *     if target < root.val:
 *         return searchBST(root.left, target)
 *     else:
 *         return searchBST(root.right, target)
 * ```
 * 
 * 🔹 BST VALIDATION:
 * ```
 * def isValidBST(root):
 *     def validate(node, min_val, max_val):
 *         if not node:
 *             return True
 *         
 *         if node.val <= min_val or node.val >= max_val:
 *             return False
 *         
 *         return (validate(node.left, min_val, node.val) and
 *                 validate(node.right, node.val, max_val))
 *     
 *     return validate(root, float('-inf'), float('inf'))
 * ```
 * 
 * 🔹 LOWEST COMMON ANCESTOR:
 * ```
 * def lowestCommonAncestor(root, p, q):
 *     if not root or root == p or root == q:
 *         return root
 *     
 *     left = lowestCommonAncestor(root.left, p, q)
 *     right = lowestCommonAncestor(root.right, p, q)
 *     
 *     if left and right:
 *         return root  # Both nodes found in different subtrees
 *     
 *     return left if left else right
 * ```
 * 
 * 🔹 CLOSEST VALUE IN BST:
 * ```
 * def closestValue(root, target):
 *     closest = root.val
 *     
 *     while root:
 *         if abs(root.val - target) < abs(closest - target):
 *             closest = root.val
 *         
 *         root = root.left if target < root.val else root.right
 *     
 *     return closest
 * ```
 * 
 * ============================================================================
 * 📋 PROBLEM-SOLVING FRAMEWORK
 * ============================================================================
 * 
 * STEP 1: IDENTIFY SEARCH/TRAVERSAL REQUIREMENTS
 * - What type of search is needed? (value, validation, relationship)
 * - What tree properties can be leveraged? (BST, complete, balanced)
 * - What traversal strategy is most appropriate?
 * 
 * STEP 2: CHOOSE OPTIMAL ALGORITHM
 * - Leverage BST properties when available for O(log n) operations
 * - Use appropriate traversal method for systematic exploration
 * - Consider iterative vs recursive based on requirements
 * 
 * STEP 3: DESIGN SEARCH STRATEGY
 * - What are the termination conditions?
 * - How to handle edge cases and invalid inputs?
 * - What optimization techniques can be applied?
 * 
 * STEP 4: IMPLEMENT EFFICIENT SOLUTION
 * - How to minimize time and space complexity?
 * - What data structures aid the search process?
 * - How to handle concurrent or repeated searches?
 * 
 * STEP 5: VALIDATE AND OPTIMIZE
 * - How to verify correctness of search results?
 * - What are the performance characteristics?
 * - Can the algorithm be further optimized?
 * 
 * ============================================================================
 * 🎨 BINARY TREE TRAVERSAL & SEARCH TEMPLATES
 * ============================================================================
 */

// Definition for a binary tree node

public class TreeTraversalSearchReadingGuide {
    
    /**
     * 🔍 BST SEARCH TEMPLATES
     */
    public static class BSTSearchTemplates {
        
        /**
         * Search in Binary Search Tree
         * Strategy: Leverage BST property for O(log n) search
         */
        public static TreeNode searchBST(TreeNode root, int val) {
            if (root == null || root.val == val) {
                return root;
            }
            
            // Use BST property to choose search direction
            return val < root.val ? 
                   searchBST(root.left, val) : 
                   searchBST(root.right, val);
        }
        
        /**
         * Iterative BST Search
         * Strategy: Avoid recursion overhead with iterative approach
         */
        public static TreeNode searchBSTIterative(TreeNode root, int val) {
            while (root != null && root.val != val) {
                root = val < root.val ? root.left : root.right;
            }
            return root;
        }
        
        /**
         * Closest Value in BST
         * Strategy: Track closest value while traversing BST path
         */
        public static int closestValue(TreeNode root, double target) {
            int closest = root.val;
            
            while (root != null) {
                // Update closest if current value is closer
                if (Math.abs(root.val - target) < Math.abs(closest - target)) {
                    closest = root.val;
                }
                
                // Move towards target using BST property
                root = target < root.val ? root.left : root.right;
            }
            
            return closest;
        }
        
        /**
         * Range Search in BST
         * Strategy: Use BST property to prune search space
         */
        public static List<Integer> rangeBST(TreeNode root, int low, int high) {
            List<Integer> result = new ArrayList<>();
            rangeHelper(root, low, high, result);
            return result;
        }
        
        private static void rangeHelper(TreeNode node, int low, int high, List<Integer> result) {
            if (node == null) return;
            
            // Only search left if current value > low
            if (node.val > low) {
                rangeHelper(node.left, low, high, result);
            }
            
            // Add current value if in range
            if (node.val >= low && node.val <= high) {
                result.add(node.val);
            }
            
            // Only search right if current value < high
            if (node.val < high) {
                rangeHelper(node.right, low, high, result);
            }
        }
    }
    
    /**
     * ✅ TREE VALIDATION TEMPLATES
     */
    public static class TreeValidationTemplates {
        
        /**
         * Validate Binary Search Tree
         * Strategy: Check BST property with bounds validation
         */
        public static boolean isValidBST(TreeNode root) {
            return validateBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
        }
        
        private static boolean validateBST(TreeNode node, long minVal, long maxVal) {
            if (node == null) return true;
            
            // Check if current node violates BST property
            if (node.val <= minVal || node.val >= maxVal) {
                return false;
            }
            
            // Recursively validate subtrees with updated bounds
            return validateBST(node.left, minVal, node.val) &&
                   validateBST(node.right, node.val, maxVal);
        }
        
        /**
         * Validate BST using Inorder Traversal
         * Strategy: Inorder traversal of BST should be sorted
         */
        public static boolean isValidBSTInorder(TreeNode root) {
            List<Integer> inorder = new ArrayList<>();
            inorderTraversal(root, inorder);
            
            // Check if inorder sequence is strictly increasing
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
        
        /**
         * Validate Complete Binary Tree
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
                    // If we've seen null and encounter non-null, tree is not complete
                    if (nullSeen) return false;
                    
                    queue.offer(node.left);
                    queue.offer(node.right);
                }
            }
            
            return true;
        }
        
        /**
         * Validate Balanced Binary Tree
         * Strategy: Check height difference at each node
         */
        public static boolean isBalanced(TreeNode root) {
            return checkHeight(root) != -1;
        }
        
        private static int checkHeight(TreeNode node) {
            if (node == null) return 0;
            
            int leftHeight = checkHeight(node.left);
            if (leftHeight == -1) return -1; // Left subtree is unbalanced
            
            int rightHeight = checkHeight(node.right);
            if (rightHeight == -1) return -1; // Right subtree is unbalanced
            
            // Check if current node is balanced
            if (Math.abs(leftHeight - rightHeight) > 1) {
                return -1; // Current node is unbalanced
            }
            
            return Math.max(leftHeight, rightHeight) + 1;
        }
    }
    
    /**
     * 🌳 ANCESTRAL RELATIONSHIP TEMPLATES
     */
    public static class AncestralRelationshipTemplates {
        
        /**
         * Lowest Common Ancestor of Binary Tree
         * Strategy: Post-order traversal to find LCA
         */
        public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            if (root == null || root == p || root == q) {
                return root;
            }
            
            TreeNode left = lowestCommonAncestor(root.left, p, q);
            TreeNode right = lowestCommonAncestor(root.right, p, q);
            
            // If both nodes found in different subtrees, current node is LCA
            if (left != null && right != null) {
                return root;
            }
            
            // Return the subtree that contains the nodes
            return left != null ? left : right;
        }
        
        /**
         * Lowest Common Ancestor of BST
         * Strategy: Use BST property for optimized search
         */
        public static TreeNode lowestCommonAncestorBST(TreeNode root, TreeNode p, TreeNode q) {
            if (root == null) return null;
            
            // If both nodes are smaller, LCA is in left subtree
            if (p.val < root.val && q.val < root.val) {
                return lowestCommonAncestorBST(root.left, p, q);
            }
            
            // If both nodes are larger, LCA is in right subtree
            if (p.val > root.val && q.val > root.val) {
                return lowestCommonAncestorBST(root.right, p, q);
            }
            
            // Otherwise, current node is LCA
            return root;
        }
        
        /**
         * Find Path Between Two Nodes
         * Strategy: Find paths to both nodes, then compute path between them
         */
        public static List<TreeNode> pathBetweenNodes(TreeNode root, TreeNode start, TreeNode end) {
            TreeNode lca = lowestCommonAncestor(root, start, end);
            
            List<TreeNode> pathToStart = new ArrayList<>();
            List<TreeNode> pathToEnd = new ArrayList<>();
            
            findPath(lca, start, pathToStart);
            findPath(lca, end, pathToEnd);
            
            // Combine paths: reverse path to start + path to end
            List<TreeNode> result = new ArrayList<>();
            for (int i = pathToStart.size() - 1; i >= 0; i--) {
                result.add(pathToStart.get(i));
            }
            for (int i = 1; i < pathToEnd.size(); i++) { // Skip LCA to avoid duplication
                result.add(pathToEnd.get(i));
            }
            
            return result;
        }
        
        private static boolean findPath(TreeNode root, TreeNode target, List<TreeNode> path) {
            if (root == null) return false;
            
            path.add(root);
            
            if (root == target) return true;
            
            if (findPath(root.left, target, path) || findPath(root.right, target, path)) {
                return true;
            }
            
            path.remove(path.size() - 1); // Backtrack
            return false;
        }
        
        /**
         * Check if Node is Ancestor
         * Strategy: Search for descendant in ancestor's subtree
         */
        public static boolean isAncestor(TreeNode ancestor, TreeNode descendant) {
            if (ancestor == null) return false;
            if (ancestor == descendant) return true;
            
            return isAncestor(ancestor.left, descendant) || 
                   isAncestor(ancestor.right, descendant);
        }
    }
    
    /**
     * 🔄 TRAVERSAL ALGORITHM TEMPLATES
     */
    public static class TraversalAlgorithmTemplates {
        
        /**
         * Inorder Traversal (Recursive)
         * Strategy: Left -> Root -> Right
         */
        public static List<Integer> inorderTraversal(TreeNode root) {
            List<Integer> result = new ArrayList<>();
            inorderHelper(root, result);
            return result;
        }
        
        private static void inorderHelper(TreeNode node, List<Integer> result) {
            if (node != null) {
                inorderHelper(node.left, result);
                result.add(node.val);
                inorderHelper(node.right, result);
            }
        }
        
        /**
         * Inorder Traversal (Iterative)
         * Strategy: Use stack to simulate recursion
         */
        public static List<Integer> inorderTraversalIterative(TreeNode root) {
            List<Integer> result = new ArrayList<>();
            Stack<TreeNode> stack = new Stack<>();
            TreeNode current = root;
            
            while (current != null || !stack.isEmpty()) {
                // Go to leftmost node
                while (current != null) {
                    stack.push(current);
                    current = current.left;
                }
                
                // Process current node
                current = stack.pop();
                result.add(current.val);
                
                // Move to right subtree
                current = current.right;
            }
            
            return result;
        }
        
        /**
         * Level Order Traversal
         * Strategy: BFS using queue
         */
        public static List<List<Integer>> levelOrder(TreeNode root) {
            List<List<Integer>> result = new ArrayList<>();
            if (root == null) return result;
            
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            
            while (!queue.isEmpty()) {
                int levelSize = queue.size();
                List<Integer> currentLevel = new ArrayList<>();
                
                for (int i = 0; i < levelSize; i++) {
                    TreeNode node = queue.poll();
                    currentLevel.add(node.val);
                    
                    if (node.left != null) queue.offer(node.left);
                    if (node.right != null) queue.offer(node.right);
                }
                
                result.add(currentLevel);
            }
            
            return result;
        }
        
        /**
         * Morris Inorder Traversal
         * Strategy: O(1) space traversal using threading
         */
        public static List<Integer> morrisInorder(TreeNode root) {
            List<Integer> result = new ArrayList<>();
            TreeNode current = root;
            
            while (current != null) {
                if (current.left == null) {
                    // No left subtree, process current and go right
                    result.add(current.val);
                    current = current.right;
                } else {
                    // Find inorder predecessor
                    TreeNode predecessor = current.left;
                    while (predecessor.right != null && predecessor.right != current) {
                        predecessor = predecessor.right;
                    }
                    
                    if (predecessor.right == null) {
                        // Create thread
                        predecessor.right = current;
                        current = current.left;
                    } else {
                        // Remove thread and process current
                        predecessor.right = null;
                        result.add(current.val);
                        current = current.right;
                    }
                }
            }
            
            return result;
        }
    }
    
    /**
     * 🧩 SUBTREE AND PATTERN MATCHING TEMPLATES
     */
    public static class SubtreePatternTemplates {
        
        /**
         * Subtree of Another Tree
         * Strategy: Check if subRoot matches any subtree of root
         */
        public static boolean isSubtree(TreeNode root, TreeNode subRoot) {
            if (root == null) return false;
            if (isSameTree(root, subRoot)) return true;
            
            return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
        }
        
        private static boolean isSameTree(TreeNode p, TreeNode q) {
            if (p == null && q == null) return true;
            if (p == null || q == null) return false;
            
            return p.val == q.val && 
                   isSameTree(p.left, q.left) && 
                   isSameTree(p.right, q.right);
        }
        
        /**
         * Tree Serialization for Subtree Matching
         * Strategy: Convert trees to strings and use string matching
         */
        public static boolean isSubtreeString(TreeNode root, TreeNode subRoot) {
            String rootStr = serialize(root);
            String subStr = serialize(subRoot);
            return rootStr.contains(subStr);
        }
        
        private static String serialize(TreeNode node) {
            if (node == null) return "null";
            return "#" + node.val + " " + serialize(node.left) + " " + serialize(node.right);
        }
        
        /**
         * Find All Occurrences of Pattern
         * Strategy: Find all subtrees matching given pattern
         */
        public static List<TreeNode> findPattern(TreeNode root, TreeNode pattern) {
            List<TreeNode> matches = new ArrayList<>();
            findPatternHelper(root, pattern, matches);
            return matches;
        }
        
        private static void findPatternHelper(TreeNode node, TreeNode pattern, List<TreeNode> matches) {
            if (node == null) return;
            
            if (isSameTree(node, pattern)) {
                matches.add(node);
            }
            
            findPatternHelper(node.left, pattern, matches);
            findPatternHelper(node.right, pattern, matches);
        }
        
        /**
         * Tree Isomorphism Check
         * Strategy: Check if trees are structurally identical
         */
        public static boolean areIsomorphic(TreeNode root1, TreeNode root2) {
            if (root1 == null && root2 == null) return true;
            if (root1 == null || root2 == null) return false;
            
            // Check both normal and flipped configurations
            return (areIsomorphic(root1.left, root2.left) && areIsomorphic(root1.right, root2.right)) ||
                   (areIsomorphic(root1.left, root2.right) && areIsomorphic(root1.right, root2.left));
        }
    }
    
    /**
     * 🔬 ADVANCED SEARCH TEMPLATES
     */
    public static class AdvancedSearchTemplates {
        
        /**
         * Multi-Criteria Search
         * Strategy: Find nodes satisfying multiple conditions
         */
        public static List<TreeNode> multiCriteriaSearch(TreeNode root, int minVal, int maxVal, int maxDepth) {
            List<TreeNode> result = new ArrayList<>();
            multiSearchHelper(root, minVal, maxVal, maxDepth, 0, result);
            return result;
        }
        
        private static void multiSearchHelper(TreeNode node, int minVal, int maxVal, 
                                            int maxDepth, int currentDepth, List<TreeNode> result) {
            if (node == null || currentDepth > maxDepth) return;
            
            // Check if node satisfies all criteria
            if (node.val >= minVal && node.val <= maxVal) {
                result.add(node);
            }
            
            multiSearchHelper(node.left, minVal, maxVal, maxDepth, currentDepth + 1, result);
            multiSearchHelper(node.right, minVal, maxVal, maxDepth, currentDepth + 1, result);
        }
        
        /**
         * Conditional Traversal
         * Strategy: Traverse based on dynamic conditions
         */
        public static void conditionalTraversal(TreeNode root, java.util.function.Predicate<TreeNode> condition,
                                              java.util.function.Consumer<TreeNode> action) {
            if (root == null) return;
            
            if (condition.test(root)) {
                action.accept(root);
            }
            
            conditionalTraversal(root.left, condition, action);
            conditionalTraversal(root.right, condition, action);
        }
        
        /**
         * Iterative Deepening Search
         * Strategy: Progressive depth exploration
         */
        public static TreeNode iterativeDeepeningSearch(TreeNode root, int target) {
            for (int depth = 0; depth <= getMaxDepth(root); depth++) {
                TreeNode result = depthLimitedSearch(root, target, depth);
                if (result != null) return result;
            }
            return null;
        }
        
        private static TreeNode depthLimitedSearch(TreeNode node, int target, int depth) {
            if (node == null || depth < 0) return null;
            if (node.val == target) return node;
            
            TreeNode leftResult = depthLimitedSearch(node.left, target, depth - 1);
            if (leftResult != null) return leftResult;
            
            return depthLimitedSearch(node.right, target, depth - 1);
        }
        
        private static int getMaxDepth(TreeNode node) {
            if (node == null) return 0;
            return 1 + Math.max(getMaxDepth(node.left), getMaxDepth(node.right));
        }
        
        /**
         * Parallel Search Simulation
         * Strategy: Simulate concurrent search in different subtrees
         */
        public static TreeNode parallelSearch(TreeNode root, int target) {
            if (root == null) return null;
            if (root.val == target) return root;
            
            // Simulate parallel search in left and right subtrees
            TreeNode[] results = new TreeNode[2];
            
            // In real implementation, these would be separate threads
            results[0] = parallelSearch(root.left, target);
            results[1] = parallelSearch(root.right, target);
            
            // Return first non-null result
            return results[0] != null ? results[0] : results[1];
        }
    }
    
    // ============================================================================
    // 🚨 COMMON PITFALLS TO AVOID
    // ============================================================================
    
    /**
     * ❌ PITFALL 1: Not Leveraging BST Properties
     * Always use BST ordering for efficient search when available
     */
    public static void bstPropertyExample() {
        // Correct: O(log n) BST search using ordering
        // Incorrect: O(n) linear search ignoring BST property
    }
    
    /**
     * ❌ PITFALL 2: Incorrect Bounds in BST Validation
     * Use long or proper bounds to handle edge cases
     */
    public static void bstValidationExample() {
        // Correct: validateBST(node, Long.MIN_VALUE, Long.MAX_VALUE)
        // Incorrect: only checking immediate parent-child relationship
    }
    
    /**
     * ❌ PITFALL 3: Inefficient Tree Comparison
     * Use appropriate algorithms for different comparison types
     */
    public static void treeComparisonExample() {
        // Consider structural vs value-based comparison
        // Use serialization for complex pattern matching
    }
    
    /**
     * ❌ PITFALL 4: Stack Overflow in Deep Trees
     * Consider iterative approaches for very deep trees
     */
    public static void deepTreeExample() {
        // Use iterative traversal for extremely deep trees
        // Implement tail recursion where possible
    }
    
    // ============================================================================
    // 💡 PRO TIPS
    // ============================================================================
    
    /**
     * 🎯 TIP 1: Choose Optimal Traversal Strategy
     * - Inorder for BST operations and sorted processing
     * - Preorder for tree copying and serialization
     * - Postorder for tree deletion and bottom-up processing
     * - Level-order for shortest path and breadth-first analysis
     */
    
    /**
     * 🎯 TIP 2: Leverage Tree Properties for Optimization
     * - BST: O(log n) search, range queries, validation
     * - Complete trees: array representation, heap operations
     * - Balanced trees: guaranteed O(log n) height-based operations
     */
    
    /**
     * 🎯 TIP 3: Handle Edge Cases Systematically
     * - Null trees, single nodes, duplicate values
     * - Integer overflow in bounds checking
     * - Extremely deep or wide trees
     */
    
    /**
     * 🎯 TIP 4: Optimize for Common Use Cases
     * - Cache frequently accessed nodes
     * - Use appropriate data structures for search acceleration
     * - Consider preprocessing for repeated queries
     */
    
    /**
     * 🎯 TIP 5: Choose Between Recursive and Iterative
     * - Recursive: cleaner code, natural for tree operations
     * - Iterative: better space control, avoid stack overflow
     * - Morris: constant space for specific traversal needs
     */
    
    // ============================================================================
    // 📚 PRACTICE PROBLEMS BY CATEGORY
    // ============================================================================
    
    /**
     * 🟢 BASIC TRAVERSAL & SEARCH PROBLEMS:
     * - Search in a Binary Search Tree
     * - Binary Tree Inorder Traversal
     * - Same Tree
     * - Maximum Depth of Binary Tree
     * 
     * 🟡 INTERMEDIATE TRAVERSAL & SEARCH PROBLEMS:
     * - Validate Binary Search Tree
     * - Lowest Common Ancestor of a Binary Tree
     * - Subtree of Another Tree
     * - Closest Binary Search Tree Value
     * 
     * 🔴 ADVANCED TRAVERSAL & SEARCH PROBLEMS:
     * - Serialize and Deserialize Binary Tree
     * - Binary Tree Maximum Path Sum
     * - Recover Binary Search Tree
     * - Count Complete Tree Nodes
     */
    
    // ============================================================================
    // 🧭 DECISION FRAMEWORK
    // ============================================================================
    
    /**
     * CHOOSE APPROACH BASED ON:
     * ✅ Tree type: BST vs general binary tree vs specialized
     * ✅ Search criteria: exact match, range, proximity, validation
     * ✅ Performance requirements: time/space complexity, repeated queries
     * ✅ Tree characteristics: height, balance, completeness
     * 
     * OPTIMIZE FOR:
     * 🚀 Search efficiency: leverage tree properties for faster algorithms
     * 🚀 Space complexity: choose iterative vs recursive based on constraints
     * 🚀 Code clarity: balance optimization with maintainability
     * 🚀 Robustness: handle edge cases and invalid inputs gracefully
     */
    
    // ============================================================================
    // 🌍 REAL-WORLD APPLICATIONS
    // ============================================================================
    
    /**
     * 🏭 PRACTICAL USES:
     * - Database indexing (B+ tree search and range queries)
     * - File system navigation (directory tree traversal and search)
     * - Expression evaluation (syntax tree traversal and validation)
     * - Game AI (decision tree search and game state analysis)
     * - Compiler design (AST traversal and symbol table lookup)
     * - Geographic information systems (spatial tree search and proximity queries)
     * - Machine learning (decision tree traversal and prediction)
     * - Network routing (tree-based path finding and optimization)
     */
    
    public static void main(String[] args) {
        System.out.println("🔍 BINARY TREE TRAVERSAL & SEARCH PATTERN - READING GUIDE");
        System.out.println("=========================================================");
        
        System.out.println("\n📖 Template Examples:");
        
        // Create sample BST for testing
        TreeNode bst = new TreeNode(4);
        bst.left = new TreeNode(2);
        bst.right = new TreeNode(7);
        bst.left.left = new TreeNode(1);
        bst.left.right = new TreeNode(3);
        
        // Test BST search
        TreeNode searchResult = BSTSearchTemplates.searchBST(bst, 2);
        System.out.println("BST search for 2: " + (searchResult != null ? "Found" : "Not found"));
        
        // Test BST validation
        boolean isValid = TreeValidationTemplates.isValidBST(bst);
        System.out.println("Is valid BST: " + isValid);
        
        // Test closest value
        int closest = BSTSearchTemplates.closestValue(bst, 3.5);
        System.out.println("Closest value to 3.5: " + closest);
        
        // Test LCA
        TreeNode lca = AncestralRelationshipTemplates.lowestCommonAncestor(bst, bst.left.left, bst.left.right);
        System.out.println("LCA of 1 and 3: " + (lca != null ? lca.val : "null"));
        
        // Test traversals
        List<Integer> inorder = TraversalAlgorithmTemplates.inorderTraversal(bst);
        System.out.println("Inorder traversal: " + inorder);
        
        List<List<Integer>> levelOrder = TraversalAlgorithmTemplates.levelOrder(bst);
        System.out.println("Level order traversal: " + levelOrder);
        
        // Test subtree matching
        TreeNode subtree = new TreeNode(2);
        subtree.left = new TreeNode(1);
        subtree.right = new TreeNode(3);
        
        boolean isSubtree = SubtreePatternTemplates.isSubtree(bst, subtree);
        System.out.println("Is subtree: " + isSubtree);
        
        System.out.println("\n✅ Key Binary Tree Traversal & Search Principles:");
        System.out.println("1. Leverage tree properties (BST ordering) for efficient search");
        System.out.println("2. Choose appropriate traversal strategy for specific goals");
        System.out.println("3. Use bounds checking for tree validation problems");
        System.out.println("4. Consider iterative approaches for deep trees");
        System.out.println("5. Handle edge cases (null trees, single nodes) systematically");
        System.out.println("6. Optimize repeated queries with preprocessing");
        System.out.println("7. Balance code clarity with performance optimization");
    }
} 