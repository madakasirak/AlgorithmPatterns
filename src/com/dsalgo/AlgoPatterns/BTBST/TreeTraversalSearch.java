package com.dsalgo.AlgoPatterns.BTBST;

import java.util.*;

/**
 * 🔍 BINARY TREE TRAVERSAL & SEARCH PATTERN - PRACTICE PROBLEMS
 * 
 * This class contains comprehensive implementations of binary tree traversal and search
 * algorithms for finding specific values, validating tree properties, performing complex
 * queries, and systematic tree exploration. These algorithms demonstrate efficient search
 * techniques, property validation methods, ancestral relationship queries, and advanced
 * traversal strategies optimized for different tree types and search requirements.
 * 
 * Pattern Focus: Tree search, validation, traversal, query processing
 * Time Complexity: Generally O(log n) for BST, O(n) for general trees
 * Space Complexity: O(h) for recursion where h is tree height
 */

// Definition for a binary tree node

public class TreeTraversalSearch {
    
    // ============================================================================
    // 🟢 EASY PROBLEMS - Basic Search and Traversal
    // ============================================================================
    
    /**
     * 🟢 EASY: Search in a Binary Search Tree
     * 
     * Problem: Find a node with given value in BST
     * LeetCode: https://leetcode.com/problems/search-in-a-binary-search-tree/
     * 
     * Approach: Leverage BST property for O(log n) search
     * - If target equals current node, return current node
     * - If target is smaller, search left subtree
     * - If target is larger, search right subtree
     * 
     * Time: O(log n) average, O(n) worst case, Space: O(h) for recursion
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
     * Search in BST - Iterative Approach
     * 
     * Approach: Iterative traversal to avoid recursion overhead
     * - Use while loop instead of recursion
     * - More space-efficient for very deep trees
     * 
     * Time: O(log n) average, O(n) worst case, Space: O(1)
     */
    public static TreeNode searchBSTIterative(TreeNode root, int val) {
        while (root != null && root.val != val) {
            root = val < root.val ? root.left : root.right;
        }
        return root;
    }
    
    /**
     * 🟢 EASY: Closest Binary Search Tree Value
     * 
     * Problem: Find value in BST closest to given target
     * LeetCode: https://leetcode.com/problems/closest-binary-search-tree-value/
     * 
     * Approach: Track closest value while traversing BST path
     * - Compare current value with target and update closest
     * - Move towards target using BST property
     * - Continue until reaching leaf or exact match
     * 
     * Time: O(log n) average, O(n) worst case, Space: O(1)
     */
    public static int closestValue(TreeNode root, double target) {
        int closest = root.val;
        
        while (root != null) {
            // Update closest if current value is closer to target
            if (Math.abs(root.val - target) < Math.abs(closest - target)) {
                closest = root.val;
            }
            
            // Move towards target using BST property
            root = target < root.val ? root.left : root.right;
        }
        
        return closest;
    }
    
    // ============================================================================
    // 🟡 MEDIUM PROBLEMS - Intermediate Tree Analysis
    // ============================================================================
    
    /**
     * 🟡 MEDIUM: Validate Binary Search Tree
     * 
     * Problem: Check if binary tree is a valid BST
     * LeetCode: https://leetcode.com/problems/validate-binary-search-tree/
     * 
     * Approach: Use bounds validation for each node
     * - Each node must be within valid range (min, max)
     * - Left subtree: all values < current node value
     * - Right subtree: all values > current node value
     * - Recursively validate with updated bounds
     * 
     * Time: O(n), Space: O(h) for recursion
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
     * Validate BST - Inorder Traversal Approach
     * 
     * Approach: Inorder traversal of BST should be strictly increasing
     * - Perform inorder traversal and collect values
     * - Check if resulting sequence is strictly increasing
     * - Alternative: track previous value during traversal
     * 
     * Time: O(n), Space: O(n) for storing values
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
     * Validate BST - Space Optimized Inorder
     * 
     * Approach: Track only previous value during inorder traversal
     * - Use class variable or array to track previous value
     * - Compare current with previous during traversal
     * - More space-efficient than storing all values
     * 
     * Time: O(n), Space: O(h) for recursion
     */
    public static boolean isValidBSTOptimized(TreeNode root) {
        return inorderValidate(root, new TreeNode[]{null});
    }
    
    private static boolean inorderValidate(TreeNode node, TreeNode[] prev) {
        if (node == null) return true;
        
        // Validate left subtree
        if (!inorderValidate(node.left, prev)) return false;
        
        // Check current node against previous
        if (prev[0] != null && node.val <= prev[0].val) return false;
        prev[0] = node;
        
        // Validate right subtree
        return inorderValidate(node.right, prev);
    }
    
    /**
     * 🟡 MEDIUM: Lowest Common Ancestor of a Binary Tree
     * 
     * Problem: Find lowest common ancestor of two nodes in binary tree
     * LeetCode: https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/
     * 
     * Approach: Post-order traversal to find LCA
     * - If current node is one of the target nodes, return it
     * - Recursively search in left and right subtrees
     * - If both subtrees return non-null, current node is LCA
     * - Otherwise, return the non-null subtree result
     * 
     * Time: O(n), Space: O(h) for recursion
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
     * LCA for Binary Search Tree (Optimized)
     * 
     * Approach: Use BST property for more efficient search
     * - If both nodes are smaller than current, LCA is in left subtree
     * - If both nodes are larger than current, LCA is in right subtree
     * - Otherwise, current node is the LCA
     * 
     * Time: O(log n) average, O(n) worst case, Space: O(h)
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
     * 🟡 MEDIUM: Subtree of Another Tree
     * 
     * Problem: Check if subRoot is a subtree of root
     * LeetCode: https://leetcode.com/problems/subtree-of-another-tree/
     * 
     * Approach: Check if subRoot matches any subtree of root
     * - For each node in root, check if subtree starting there equals subRoot
     * - Use tree equality function to compare subtrees
     * - Recursively check all nodes in root tree
     * 
     * Time: O(m * n) where m and n are tree sizes, Space: O(h)
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
     * Subtree Check - String Serialization Approach
     * 
     * Approach: Convert trees to strings and use string matching
     * - Serialize both trees to unique string representations
     * - Check if subRoot string is substring of root string
     * - Add delimiters to avoid false matches
     * 
     * Time: O(m + n), Space: O(m + n) for string storage
     */
    public static boolean isSubtreeString(TreeNode root, TreeNode subRoot) {
        String rootStr = serialize(root);
        String subStr = serialize(subRoot);
        return rootStr.contains(subStr);
    }
    
    private static String serialize(TreeNode node) {
        if (node == null) return ",null";
        return "," + node.val + serialize(node.left) + serialize(node.right);
    }
    
    // ============================================================================
    // 🔴 HARD PROBLEMS - Advanced Tree Operations
    // ============================================================================
    
    /**
     * 🔴 HARD: Range Search in BST
     * 
     * Problem: Find all values in BST within given range [low, high]
     * 
     * Approach: Use BST property to prune search space
     * - Only search left if current value > low
     * - Only search right if current value < high
     * - Add current value if it's within range
     * - Avoid unnecessary subtree exploration
     * 
     * Time: O(k + log n) where k is result size, Space: O(h + k)
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
    
    /**
     * 🔴 HARD: Kth Smallest Element in BST
     * 
     * Problem: Find kth smallest element in BST
     * 
     * Approach: Inorder traversal with counting
     * - Inorder traversal visits nodes in sorted order for BST
     * - Count nodes during traversal and return kth node
     * - Can optimize with early termination
     * 
     * Time: O(h + k), Space: O(h) for recursion
     */
    public static int kthSmallest(TreeNode root, int k) {
        int[] count = {0};
        int[] result = {0};
        kthSmallestHelper(root, k, count, result);
        return result[0];
    }
    
    private static boolean kthSmallestHelper(TreeNode node, int k, int[] count, int[] result) {
        if (node == null) return false;
        
        // Search left subtree
        if (kthSmallestHelper(node.left, k, count, result)) return true;
        
        // Process current node
        count[0]++;
        if (count[0] == k) {
            result[0] = node.val;
            return true;
        }
        
        // Search right subtree
        return kthSmallestHelper(node.right, k, count, result);
    }
    
    /**
     * 🔴 HARD: Binary Tree Vertical Order Traversal
     * 
     * Problem: Return vertical order traversal of binary tree
     * 
     * Approach: Level-order traversal with column tracking
     * - Assign column indices to nodes (root = 0, left = col-1, right = col+1)
     * - Use level-order traversal to maintain top-to-bottom order
     * - Group nodes by column index
     * - Sort columns and return results
     * 
     * Time: O(n log n), Space: O(n)
     */
    public static List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        
        Map<Integer, List<Integer>> columnMap = new TreeMap<>();
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        Queue<Integer> columnQueue = new LinkedList<>();
        
        nodeQueue.offer(root);
        columnQueue.offer(0);
        
        while (!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.poll();
            int col = columnQueue.poll();
            
            columnMap.computeIfAbsent(col, k -> new ArrayList<>()).add(node.val);
            
            if (node.left != null) {
                nodeQueue.offer(node.left);
                columnQueue.offer(col - 1);
            }
            
            if (node.right != null) {
                nodeQueue.offer(node.right);
                columnQueue.offer(col + 1);
            }
        }
        
        result.addAll(columnMap.values());
        return result;
    }
    
    /**
     * 🔴 HARD: Serialize and Deserialize Binary Tree
     * 
     * Problem: Design algorithm to serialize and deserialize binary tree
     * 
     * Approach: Preorder traversal with null markers
     * - Serialize using preorder traversal with special null markers
     * - Deserialize by reconstructing tree from preorder sequence
     * - Handle null nodes explicitly for complete reconstruction
     * 
     * Time: O(n) for both operations, Space: O(n)
     */
    public static class Codec {
        private static final String NULL_MARKER = "null";
        private static final String DELIMITER = ",";
        
        public String serialize(TreeNode root) {
            StringBuilder sb = new StringBuilder();
            serializeHelper(root, sb);
            return sb.toString();
        }
        
        private void serializeHelper(TreeNode node, StringBuilder sb) {
            if (node == null) {
                sb.append(NULL_MARKER).append(DELIMITER);
                return;
            }
            
            sb.append(node.val).append(DELIMITER);
            serializeHelper(node.left, sb);
            serializeHelper(node.right, sb);
        }
        
        public TreeNode deserialize(String data) {
            Queue<String> queue = new LinkedList<>(Arrays.asList(data.split(DELIMITER)));
            return deserializeHelper(queue);
        }
        
        private TreeNode deserializeHelper(Queue<String> queue) {
            String val = queue.poll();
            
            if (NULL_MARKER.equals(val)) return null;
            
            TreeNode node = new TreeNode(Integer.parseInt(val));
            node.left = deserializeHelper(queue);
            node.right = deserializeHelper(queue);
            
            return node;
        }
    }
    
    /**
     * 🔴 HARD: Morris Inorder Traversal
     * 
     * Problem: Inorder traversal with O(1) space complexity
     * 
     * Approach: Threading technique to avoid recursion/stack
     * - Create temporary links (threads) to enable traversal
     * - Use predecessor links to navigate without extra space
     * - Restore original tree structure during traversal
     * 
     * Time: O(n), Space: O(1)
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
                    // Create thread and go left
                    predecessor.right = current;
                    current = current.left;
                } else {
                    // Remove thread, process current, go right
                    predecessor.right = null;
                    result.add(current.val);
                    current = current.right;
                }
            }
        }
        
        return result;
    }
    
    /**
     * 🔴 HARD: Path Between Two Nodes
     * 
     * Problem: Find path between any two nodes in binary tree
     * 
     * Approach: Find LCA, then build paths from LCA to both nodes
     * - Find lowest common ancestor of the two nodes
     * - Find path from LCA to first node
     * - Find path from LCA to second node
     * - Combine paths: reverse(path_to_first) + path_to_second
     * 
     * Time: O(n), Space: O(h + path_length)
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
    
    // ============================================================================
    // 🧪 TESTING AND DEMONSTRATION
    // ============================================================================
    
    // Helper methods for creating test trees
    private static TreeNode createBST() {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(7);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        return root;
    }
    
    private static TreeNode createGeneralTree() {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(5);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(2);
        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(8);
        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(4);
        return root;
    }
    
    private static TreeNode createSubtree() {
        TreeNode subtree = new TreeNode(4);
        subtree.left = new TreeNode(1);
        subtree.right = new TreeNode(2);
        return subtree;
    }
    
    public static void main(String[] args) {
        System.out.println("🔍 BINARY TREE TRAVERSAL & SEARCH PATTERN - PRACTICE PROBLEMS");
        System.out.println("==============================================================");
        
        // Test Basic Problems
        System.out.println("\n🟢 BASIC TRAVERSAL & SEARCH PROBLEMS:");
        
        // Search in BST
        TreeNode bst = createBST();
        TreeNode searchResult = searchBST(bst, 2);
        System.out.println("Search for 2 in BST: " + (searchResult != null ? "Found" : "Not found"));
        
        TreeNode searchResultIterative = searchBSTIterative(bst, 3);
        System.out.println("Search for 3 in BST (iterative): " + (searchResultIterative != null ? "Found" : "Not found"));
        
        // Closest Value in BST
        int closest = closestValue(bst, 3.7);
        System.out.println("Closest value to 3.7 in BST: " + closest);
        
        // Test Intermediate Problems
        System.out.println("\n🟡 INTERMEDIATE TRAVERSAL & SEARCH PROBLEMS:");
        
        // Validate BST
        boolean isValid = isValidBST(bst);
        System.out.println("Is valid BST: " + isValid);
        
        boolean isValidInorder = isValidBSTInorder(bst);
        System.out.println("Is valid BST (inorder approach): " + isValidInorder);
        
        boolean isValidOptimized = isValidBSTOptimized(bst);
        System.out.println("Is valid BST (optimized): " + isValidOptimized);
        
        // Lowest Common Ancestor
        TreeNode generalTree = createGeneralTree();
        TreeNode node5 = generalTree.left;  // Node with value 5
        TreeNode node1 = generalTree.right; // Node with value 1
        TreeNode lca = lowestCommonAncestor(generalTree, node5, node1);
        System.out.println("LCA of nodes 5 and 1: " + (lca != null ? lca.val : "null"));
        
        // LCA for BST
        TreeNode lcaBST = lowestCommonAncestorBST(bst, bst.left.left, bst.left.right);
        System.out.println("LCA of 1 and 3 in BST: " + (lcaBST != null ? lcaBST.val : "null"));
        
        // Subtree Check
        TreeNode subtree = createSubtree();
        boolean isSubtreeResult = isSubtree(generalTree, subtree);
        System.out.println("Is subtree present: " + isSubtreeResult);
        
        boolean isSubtreeStringResult = isSubtreeString(generalTree, subtree);
        System.out.println("Is subtree present (string approach): " + isSubtreeStringResult);
        
        // Test Advanced Problems
        System.out.println("\n🔴 ADVANCED TRAVERSAL & SEARCH PROBLEMS:");
        
        // Range Search in BST
        List<Integer> rangeResult = rangeBST(bst, 2, 4);
        System.out.println("Values in range [2, 4]: " + rangeResult);
        
        // Kth Smallest in BST
        int kthSmallest = kthSmallest(bst, 3);
        System.out.println("3rd smallest element in BST: " + kthSmallest);
        
        // Vertical Order Traversal
        List<List<Integer>> verticalOrder = verticalOrder(generalTree);
        System.out.println("Vertical order traversal: " + verticalOrder);
        
        // Serialization and Deserialization
        Codec codec = new Codec();
        String serialized = codec.serialize(bst);
        System.out.println("Serialized BST: " + serialized);
        TreeNode deserialized = codec.deserialize(serialized);
        System.out.println("Deserialized successfully: " + (deserialized != null));
        
        // Morris Inorder Traversal
        List<Integer> morrisResult = morrisInorder(bst);
        System.out.println("Morris inorder traversal: " + morrisResult);
        
        // Path Between Nodes
        TreeNode node6 = generalTree.left.left;     // Node with value 6
        TreeNode node8 = generalTree.right.right;   // Node with value 8
        List<TreeNode> pathBetween = pathBetweenNodes(generalTree, node6, node8);
        System.out.print("Path between nodes 6 and 8: ");
        for (TreeNode node : pathBetween) {
            System.out.print(node.val + " ");
        }
        System.out.println();
        
        System.out.println("\n✅ Key Binary Tree Traversal & Search Principles:");
        System.out.println("1. Leverage BST properties for O(log n) search operations");
        System.out.println("2. Use bounds validation for tree property verification");
        System.out.println("3. Apply appropriate traversal strategy for specific goals");
        System.out.println("4. Handle edge cases systematically (null trees, single nodes)");
        System.out.println("5. Consider iterative approaches for deep trees to avoid stack overflow");
        System.out.println("6. Use post-order traversal for bottom-up analysis (LCA)");
        System.out.println("7. Optimize with early termination when possible");
        System.out.println("8. Choose between recursive and iterative based on space constraints");
    }
} 